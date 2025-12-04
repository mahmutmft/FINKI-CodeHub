<script setup>
import { onMounted, ref } from "vue";
import CoinService from "../service/CoinService";
import { useRouter } from "vue-router";
import { useCoinsStore } from "../store/coinsStore";
import { onAuthStateChanged } from "firebase/auth";
import { auth, db } from "../firebase/firebase";
import { collection, getDocs } from "firebase/firestore";
import firebase from "firebase/compat/app";

const isLoggedIn = ref(false);

const isLoading = ref(true);
const router = useRouter();

const coinsStore = useCoinsStore();
const coins = coinsStore.coinDataTop50;
const filteredcoins = ref([]);

const userCoins = ref([])

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
      filteredcoins.value = coins.filter(coin => userSymbols.includes(coin.symbol));

      console.log("User's coins: ", userCoins.value)
      }
    else {
      isLoggedIn.value = false;
    }
    isLoading.value = false;
  })
})

const getPriceUsd = (symbol) => {
  const match = filteredcoins.value.find(coin => coin.symbol === symbol);
  return match ? Number(match.priceUsd) : 0;
};

const scrollToSearch = () => {
  const input = document.getElementById('myInput');
  if (input) {
    input.scrollIntoView({ behavior: 'smooth', block: 'center' });
    input.focus();
  } else {
    // Ако search bar не е на оваа страница, прво направи навигација
    router.push('/').then(() => {
      setTimeout(() => {
        const input = document.getElementById('myInput');
        if (input) {
          input.scrollIntoView({ behavior: 'smooth', block: 'center' });
          input.focus();
        }
      }, 300); // чека малку за да се вчита компонентата
    });
  }
};

const handleInvest = (coinName) => {
  if (isLoggedIn.value) {
    router.push({ path: `/invest/${coinName}` });
  } else {
    router.push("/register");
  }
};


</script>

<template>
  <div class="relative bg-[#1B2028] w-[400px] rounded-[10px] p-[20px] ml-[50px] h-[636px] pt-[30px] overflow-hidden">
    <h1 class="text-white font-bold text-2xl">My Portfolio</h1>

    <div v-if="!isLoggedIn" class="absolute top-0 left-0 w-full h-full flex flex-col items-center justify-center z-10">
      <p class="text-lg font-semibold text-white">
        <router-link to="/login" class="text-logoBlue font-bold text-xl hover:underline">
        Sign in
      </router-link> to access your portfolio.
      </p>
      <p class="text-sm text-gray-400 mt-2">
        or <router-link to="/register" class="text-logoBlue hover:underline">register</router-link> to get your own.
      </p>
    </div>

    <div :class="{ 'blur-md': !isLoggedIn }" class="relative">
      <div v-if="userCoins.length === 0" class="text-center mt-[210px]">
        <p class="text-lg font-semibold text-white">Invest in Coins <a @click="scrollToSearch" class="text-lg font-semibold text-logoBlue underline hover:text-logoBlue transition">now</a>!</p>
      </div>
      
      <div v-else class="max-h-[636px] overflow-y-auto scrollbar-thin scrollbar-thumb-[#333] scrollbar-track-transparent">
        <div v-for="coin in userCoins" :key="coin.id" @click="handleInvest(coin.coinName)" class="flex gap-[10px] pt-[20px] text-white hover:bg-gray-700 rounded-lg transition-all cursor-pointer">
          <div>
            <img :src="`/static/${coin.symbol.toLowerCase()}.png`" class="w-12"/>
          </div>
          <div class="flex gap-[80px]">
            <div class="w-[75px]">
              <h1>{{ coin.coinName }}</h1>
              <h3 class="text-[#454151]">${{ (coin.amountOwned * getPriceUsd(coin.symbol)).toFixed(2) }}</h3>
            </div>
            <h3>{{Number(coin.amountOwned).toFixed(4)}} {{ coin.symbol }}</h3>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
