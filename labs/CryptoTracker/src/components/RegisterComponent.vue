<script setup>
import { ref, onMounted } from "vue";
import { auth, db } from "../firebase/firebase";
import { createUserWithEmailAndPassword, signInAnonymously, updateProfile, signOut } from "firebase/auth";
import { doc, setDoc } from "firebase/firestore";
import { useRouter } from "vue-router";

const router = useRouter();

const step = ref(1);
const form = ref({
  email: "",
  fullName: "",
  username: "",
  password: "",
  confirmPassword: "",
});

const errors = ref({
  passwordMismatch: false,
  firebaseError: "",
});

const isEmailValid = ref(false);
const currentUser = ref(null);
const validateEmail = () => {
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  isEmailValid.value = emailPattern.test(form.value.email);
};

const proceedToNextStep = () => {
  if (isEmailValid.value) {
    step.value = 2;
  }
};

const register = async () => {
  errors.value.passwordMismatch = form.value.password !== form.value.confirmPassword;
  errors.value.firebaseError = "";

  if (errors.value.passwordMismatch) return;

  try {
    // 1. Создај го корисникот анонимно во Firebase Auth
    //const userCredential = await signInAnonymously(auth);
    const userCredential = await createUserWithEmailAndPassword(auth, form.value.email,
    form.value.password);


    // 2. Опционално, ажурирај го display name (например, username)
    await updateProfile(userCredential.user, {
      displayName: form.value.username,
    });

    // 3. Запиши дополнителни податоци во Firestore
    await setDoc(doc(db, "users", userCredential.user.uid), {
      fullName: form.value.fullName,
      username: form.value.username,
      email: form.value.email,
      //password: form.value.password,  // If you need to store the password, but be aware of security risks
    });

    // 4. Логирај го корисникот
    currentUser.value = userCredential.user; // Ажурирај го `currentUser`

    // 5. Пренасочи на dashboard или домашна страница
    router.push("/"); // Може да биде домашна страница

  } catch (error) {
    errors.value.firebaseError = error.message;
    console.error("Firebase Error:", error);
  }
};

const logout = async () => {
  try {
    await signOut(auth);
    currentUser.value = null; // Исчисти го корисникот
    router.push("/"); // Пренасочи на почетната страница
  } catch (error) {
    console.error("Error during sign-out:", error);
  }
};

onMounted(() => {
  auth.onAuthStateChanged((user) => {
    currentUser.value = user; // Ажурирај го `currentUser` ако е логиран
  });
});
</script>

<template>
  <div class="min-h-screen flex items-center justify-center bg-darkBlue p-4">
    <div class="bg-lightBlue p-6 rounded-2xl shadow-lg w-full max-w-md">
      <h2 class="text-textWhite text-2xl font-bold text-center mb-6">
        Create Your Account
      </h2>

      <form v-if="step === 1" @submit.prevent="proceedToNextStep" class="space-y-4">
        <div>
          <label class="text-textWhite block mb-1">Email</label>
          <input
            v-model="form.email"
            @input="validateEmail"
            type="email"
            class="w-full px-4 py-3 bg-darkBlue text-textWhite rounded-lg border border-lightGray focus:outline-none focus:ring-2 focus:ring-logoBlue"
            required
          />
        </div>

        <button
          type="submit"
          :disabled="!isEmailValid"
          class="w-full py-3 rounded-lg font-bold transition"
          :class="isEmailValid ? 'bg-logoBlue text-textWhite hover:bg-opacity-90' : 'bg-gray-500 text-gray-300 cursor-not-allowed'"
        >
          Continue
        </button>
      </form>
      <p class="text-textWhite text-center mt-4">Already have an account? <router-link to="/login" class="text-logoBlue hover:underline">Sign in</router-link></p>
      <form v-if="step === 2" @submit.prevent="register" class="space-y-4">
        <div>
          <label class="text-textWhite block mb-1">Full Name</label>
          <input
            v-model="form.fullName"
            type="text"
            class="w-full px-4 py-3 bg-darkBlue text-textWhite rounded-lg border border-lightGray focus:outline-none focus:ring-2 focus:ring-logoBlue"
            required
          />
        </div>

        <div>
          <label class="text-textWhite block mb-1">Username</label>
          <input
            v-model="form.username"
            type="text"
            class="w-full px-4 py-3 bg-darkBlue text-textWhite rounded-lg border border-lightGray focus:outline-none focus:ring-2 focus:ring-logoBlue"
            required
          />
        </div>

        <div>
          <label class="text-textWhite block mb-1">Password</label>
          <input
            v-model="form.password"
            type="password"
            class="w-full px-4 py-3 bg-darkBlue text-textWhite rounded-lg border border-lightGray focus:outline-none focus:ring-2 focus:ring-logoBlue"
            required
          />
        </div>

        <div>
          <label class="text-textWhite block mb-1">Confirm Password</label>
          <input
            v-model="form.confirmPassword"
            type="password"
            class="w-full px-4 py-3 bg-darkBlue text-textWhite rounded-lg border border-lightGray focus:outline-none focus:ring-2 focus:ring-logoBlue"
            required
          />
          <p v-if="errors.passwordMismatch" class="text-red-500 text-sm mt-1">
            Passwords do not match
          </p>
        </div>

        <button
          type="submit"
          class="w-full bg-logoBlue text-textWhite py-3 rounded-lg font-bold hover:bg-opacity-90 transition"
        >
          Sign Up
        </button>
      </form>
    </div>
  </div>
</template>
