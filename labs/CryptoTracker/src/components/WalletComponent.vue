<script setup>
import { ref, onMounted } from "vue";
import { auth, db } from "../firebase/firebase";
import { doc, getDoc, updateDoc, collection, addDoc, getDocs } from "firebase/firestore";
import { onAuthStateChanged } from "firebase/auth"; 

const balance = ref(0);
const displayedBalance = ref(0);
const addAmount = ref('');
const transactions = ref([]);
const errorMessage = ref('');
const successMessage = ref('');
const showConfirm = ref(false);
const confirmAmount = ref(0);
const balanceFlash = ref(false);
const isProcessing = ref(false);


const fetchWallet = async () => {
  const user = auth.currentUser;
  if (!user) return;

  const userRef = doc(db, "users", user.uid);
  const userSnap = await getDoc(userRef);

  if (userSnap.exists()) {
    const data = userSnap.data();
    balance.value = data.balance || 0;
    displayedBalance.value = data.balance || 0;
  }

  const transRef = collection(db, "users", user.uid, "transactions");
  const transSnap = await getDocs(transRef);
  transactions.value = transSnap.docs
    .map(doc => doc.data())
    .sort((a, b) => b.timestamp - a.timestamp);
};

const confirmAddFunds = () => {
  const amount = parseFloat(addAmount.value);
  if (!auth.currentUser || !addAmount.value || isNaN(amount) || amount <= 0) {
    errorMessage.value = "Please enter a valid positive amount.";
    successMessage.value = '';
    return;
  }

  if (amount >= 100000) {
    confirmAmount.value = amount;
    showConfirm.value = true;
    return;
  }

  addFunds(amount);
};

const addFunds = async (amount) => {
  if (isProcessing.value) return; // Prevent spamming
  isProcessing.value = true;

  try {
    const user = auth.currentUser;
    if (!user) return;

    const newBalance = balance.value + amount;

    const userRef = doc(db, "users", user.uid);
    await updateDoc(userRef, {
      balance: newBalance,
    });

    const transRef = collection(db, "users", user.uid, "transactions");

    // Check if a similar transaction already exists within the last 5 seconds (optional, added protection)
    const now = Date.now();
    const recentDuplicate = transactions.value.find(
      tx => tx.amount === amount && now - tx.timestamp < 5000
    );
    if (!recentDuplicate) {
      await addDoc(transRef, {
        type: "Add Funds",
        description: "Added Funds",
        amount,
        timestamp: now,
      });
    }

    errorMessage.value = '';
    successMessage.value = `Successfully added $${amount.toFixed(2)}!`;
    setTimeout(() => successMessage.value = '', 3000);

    addAmount.value = '';
    showConfirm.value = false;

    balanceFlash.value = true;
    setTimeout(() => {
      balanceFlash.value = false;
    }, 1000);

    await fetchWallet();
  } catch (err) {
    console.error("Error adding funds:", err);
    errorMessage.value = "Something went wrong. Please try again.";
  } finally {
    isProcessing.value = false;
  }
};


const cancelConfirm = () => {
  showConfirm.value = false;
  confirmAmount.value = 0;
};

onMounted(() => {
  onAuthStateChanged(auth, (user) => {
    if (user) {
      fetchWallet(); // Now it's safe to call this
    }
  });
});
</script>

<template>
    <div class="min-h-screen flex items-center justify-center p-4 text-white relative">
        <div class="bg-[#1B2028] p-6 rounded-2xl w-full max-w-xl">
        <h2 class="text-2xl font-bold mb-4">Your Demo Wallet</h2>
        <p class="mb-2">
            Balance: 
            <span 
              :class="{
                'text-[#1ECB4F]': !balanceFlash,
                'text-[#B0F5A5]': balanceFlash
              }"
              class="font-bold transition-colors duration-500 ease-out"
            >${{ displayedBalance.toFixed(2) }}</span>
        </p>

        <div class="flex items-center gap-4 mb-2">
            <input
            v-model="addAmount"
            type="text"
            placeholder="Enter amount"
            class="bg-darkBlue text-white px-4 py-2 rounded-lg border border-lightGray focus:outline-none w-full appearance-none -webkit-appearance-none"/>
            <button
            @click="confirmAddFunds"
            class="bg-logoBlue text-white px-4 py-2 rounded-lg font-bold"
            >
            Add Funds
            </button>
        </div>

        <p v-if="errorMessage" class="text-red-400 text-sm mb-2">{{ errorMessage }}</p>

        <transition name="fade">
            <p v-if="successMessage" class="text-green-400 text-sm mb-2">{{ successMessage }}</p>
        </transition>

        <h3 class="text-lg font-bold mt-6 mb-2">Transaction History</h3>
        <div v-if="transactions.length" class="space-y-2 max-h-[200px] overflow-y-auto">
            <div v-for="tx in transactions" :key="tx.timestamp" class="text-sm bg-[#2A2F3A] p-2 rounded-lg flex justify-between items-center">
              <div>
                <span :class="{ 'text-[#1ECB4F]': tx.type === 'Sell', 'text-[#e11d48]': tx.type === 'Buy', 'text-[#B0F5A5]': tx.type === 'Add Funds'}" class="font-bold">
                  <span class="inline-block w-20 text-right">
                    {{ tx.type === 'Buy' ? `-$${Math.abs(tx.amount).toFixed(2)}` : `+$${Math.abs(tx.amount).toFixed(2)}` }}
                  </span>
                  </span>
                <span class="text-gray-400"> - {{ new Date(tx.timestamp).toLocaleString() }}</span>
              </div>
              <span class="text-gray-300 text-right whitespace-nowrap ml-4">
                {{ tx.description }}
              </span>
            </div>
        </div>
        <p v-else class="text-gray-400">No transactions yet.</p>
        </div>

        <!-- Confirmation Popup -->
        <div
        v-if="showConfirm"
        class="absolute inset-0 bg-black bg-opacity-60 flex items-center justify-center z-50"
        >
        <div class="bg-[#2A2F3A] p-6 rounded-2xl w-full max-w-md text-center">
            <p class="mb-4 text-lg">Are you sure you want to add <span class="font-bold">${{ confirmAmount.toFixed(2) }}</span>?</p>
            <div class="flex justify-center gap-4">
            <button
                @click="addFunds(confirmAmount)"
                class="bg-green-500 hover:bg-green-600 px-4 py-2 rounded-lg font-bold"
            >
                Yes, I'm sure!
            </button>
            <button
                @click="cancelConfirm"
                class="bg-gray-500 hover:bg-gray-600 px-4 py-2 rounded-lg font-bold"
            >
                No, Cancel
            </button>
            </div>
        </div>
        </div>
    </div>
</template>

<style scoped>
.fade-enter-active, .fade-leave-active {
transition: opacity 0.5s ease;
}
.fade-enter-from, .fade-leave-to {
opacity: 0;
}
</style>
