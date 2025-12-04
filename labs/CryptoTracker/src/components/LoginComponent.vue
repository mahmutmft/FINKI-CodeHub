<script setup>
import { ref } from "vue";
import { loginUser } from "../service/authService";
import { useRouter } from 'vue-router';
import { auth, db } from "../firebase/firebase"; 
import { signInAnonymously, signInWithEmailAndPassword } from 'firebase/auth';
import { getDocs, doc, getDoc } from "firebase/firestore";
import { collection, query, where } from "firebase/firestore";
import { updateProfile } from "firebase/auth";


const router = useRouter();

const step = ref(1);
const form = ref({
  emailOrUsername: "",
  password: "",
});

const isValidInput = ref(false);
const error = ref("");

const validateInput = () => {
  isValidInput.value = form.value.emailOrUsername.trim().length > 0;
};

const proceedToPassword = () => {
  if (isValidInput.value) {
    step.value = 2;
  }
};


const currentUser = ref(null);

// const login = async () => {
//   try {
//     const userCredential = await signInAnonymously(auth);
    
//     currentUser.value = userCredential.user;

//     router.push("/"); 
//   } catch (error) {
//     error.value = error.message;  
//   }
// };



const login = async () => {
  try {
    let emailToUse = form.value.emailOrUsername;

    // Ако внесеното НЕ е валиден email -> претпоставуваме дека е username
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(emailToUse)) {
      // Пребарај го email-от од Firestore со username
      const usersRef = collection(db, "users");
      const q = query(usersRef, where("username", "==", form.value.emailOrUsername));
      const querySnapshot = await getDocs(q);

      if (querySnapshot.empty) {
        throw new Error("No user found with this username.");
      }

      // Земи го првиот резултат (бидејќи username треба да е уникатен)
      emailToUse = querySnapshot.docs[0].data().email;
    }

    // Користи го emailToUse за логирање
    const userCredential = await signInWithEmailAndPassword(auth, emailToUse, form.value.password);
    const user = userCredential.user;

    // Превземи го displayName од Firestore
    const docRef = doc(db, 'users', user.uid);
    const docSnap = await getDoc(docRef);

    if (docSnap.exists()) {
      const userData = docSnap.data();
      await updateProfile(user, {
        displayName: userData.username,
      });
    }

    router.push('/');
  } catch (error) {
    console.error('Error during login:', error);
    error.value = error.message;
  }
};



</script>

<template>
  <div class="min-h-screen flex items-center justify-center bg-darkBlue p-4">
    <div class="bg-lightBlue p-6 rounded-2xl shadow-lg w-full max-w-md">
      <h2 class="text-textWhite text-2xl font-bold text-center mb-6">
        Sign In to Your Account
      </h2>

      <form v-if="step === 1" @submit.prevent="proceedToPassword" class="space-y-4">
        <div>
          <label class="text-textWhite block mb-1">Email or Username</label>
          <input
            v-model="form.emailOrUsername"
            @input="validateInput"
            type="text"
            class="w-full px-4 py-3 bg-darkBlue text-textWhite rounded-lg border border-lightGray focus:outline-none focus:ring-2 focus:ring-logoBlue"
            required
          />
        </div>

        <button
          type="submit"
          :disabled="!isValidInput"
          class="w-full py-3 rounded-lg font-bold transition"
          :class="isValidInput ? 'bg-logoBlue text-textWhite hover:bg-opacity-90' : 'bg-gray-500 text-gray-300 cursor-not-allowed'"
        >
          Continue
        </button>
      </form>

      <form v-if="step === 2" @submit.prevent="login" class="space-y-4">
        <div>
          <label class="text-textWhite block mb-1">Password</label>
          <input
            v-model="form.password"
            type="password"
            class="w-full px-4 py-3 bg-darkBlue text-textWhite rounded-lg border border-lightGray focus:outline-none focus:ring-2 focus:ring-logoBlue"
            required
          />
        </div>

        <button
          type="submit"
          class="w-full bg-logoBlue text-textWhite py-3 rounded-lg font-bold hover:bg-opacity-90 transition"
        >
          Log In
        </button>
      </form>
    </div>
  </div>
  <p v-if="error" class="text-red-500">{{ error }}</p>
</template>
