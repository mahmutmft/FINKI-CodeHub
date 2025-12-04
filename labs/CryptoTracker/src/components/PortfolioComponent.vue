<script setup>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import { onAuthStateChanged } from "firebase/auth";
import { auth, db } from "../firebase/firebase";
import { collection, getDocs } from "firebase/firestore";
import { useCoinsStore } from "../store/coinsStore";
const isLoggedIn = ref(false);

const isLoading = ref(true);
const router = useRouter();

const userCoins = ref([])

const totalCoinsValue = ref(0);

const coinsStore = useCoinsStore();
const coins = coinsStore.coinDataTop50;
const filteredcoins = ref([]);

const getPriceUsd = (symbol) => {
  const match = filteredcoins.value.find(coin => coin.symbol === symbol);
  return match ? Number(match.priceUsd).toFixed(2) : "N/A";
};

onMounted(() =>{
  onAuthStateChanged(auth, async (user) => {
    if (user) {
      isLoggedIn.value = true;
      const coinsCollectionRef = collection(db, 'users', user.uid, 'cryptoCoins');
      const coinsSnapshot = await getDocs(coinsCollectionRef)

      userCoins.value = coinsSnapshot.docs.map(doc => ({
        id: doc.id,
        ...doc.data()
      }))

      const userSymbols = userCoins.value.map(coin => coin.symbol);  
      filteredcoins.value = coins.filter(coin =>userSymbols.includes(coin.symbol))


      totalCoinsValue.value = userCoins.value.reduce((acc, coin) =>  {
        return acc + coin.amountOwned * Number(getPriceUsd(coin.symbol))
      }, 0);
      }
    else {
      isLoggedIn.value = false;
    }
    isLoading.value = false;
  })
})

const handleInvest = (coinName) => {
  if (isLoggedIn.value) {
    router.push({ path: `/invest/${coinName}` });
  } else {
    router.push("/register");
  }
};




</script>
<template>
  <div>
    <div class="text-white text-center mt-[20px] w-[800px] rounded-[10px] p-[20px] mx-[150px] font-bold text-2xl bg-[#1B2028] mx-auto ">
    <h1>YOUR TOTAL BALANCE</h1>
    <p>{{ totalCoinsValue.toFixed(2) }}</p>
  </div>
    <div v-for="coin in userCoins" class="text-white text-center mt-[20px] w-[800px] rounded-[10px] p-[20px] mx-[150px] flex justify-between border bg-[#1B2028] mx-auto transform hover:scale-105 transition">
      <img :src="`/static/${(coin.symbol).toLowerCase()}.png`" alt="icon" class="w-16"/>
      <div class="w-[200px] text-white text-2xl"><p class="cursor-pointer"  @click="handleInvest(coin.coinName)">{{ coin.coinName }}</p></div>
      <div class="w-[200px] text-[white text-2xl">
        <div ><p>${{ (coin.amountOwned *  getPriceUsd(coin.symbol)).toFixed(2) }}USD</p></div>
        <div>
          <p>{{ Number(coin.amountOwned).toFixed(4) }} {{ coin.symbol }}</p>
        </div>
      </div>
      <p class="w-[200px]">1{{ coin.symbol }} = ${{ getPriceUsd(coin.symbol) }}</p>
    </div>
  </div>
</template>
