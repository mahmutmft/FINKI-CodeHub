<script setup>
import 'vue3-carousel/carousel.css';
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel';
import 'primeicons/primeicons.css'
import { useCoinsStore } from "../store/coinsStore";
import { useRouter } from 'vue-router';
import {ref, onMounted} from 'vue'
import { auth } from "../firebase/firebase"; 
import { onAuthStateChanged } from 'firebase/auth';


const coinStore = useCoinsStore();
const isLoggedIn = ref(false);
const router = useRouter();
const isLoading = ref(true);

const config = {
  height: 450,
  itemsToShow: 4,
  gap: 5,
  breakpoints: {
    320: { itemsToShow: 1 },
    768: { itemsToShow: 2 },
    1024: { itemsToShow: 3 },
    1280: { itemsToShow: 4 },
  },
  wrapAround: true,
};

onMounted(() =>{
  onAuthStateChanged(auth, (user) => {
    if (user) {
      isLoggedIn.value = true;
      }
    else {
      isLoggedIn.value = false;
    }
    isLoading.value = false;
  })

});

const handleInvest = (coinName) => {
  if (isLoggedIn.value) {
    router.push({ path: `/invest/${coinName}` });
  } else {
    router.push("/register");
  }
};
</script>

<template>
<Carousel v-bind="config" :loop="true" :navigation="true" :pagination="true">
    <Slide v-for="coin in coinStore.coinDataTop50" :key="coin">
      <div class="bg-[#1B2028] w-[350px] h-[310px] rounded-[10px] p-[20px] transition-transform duration-300 hover:-translate-y-2 hover:shadow-lg group">
        <div class="flex gap-[10px] items-center mb-[10px]">
          <img :src="`/static/${coin.symbol.toLowerCase()}.png`" alt="icon" class="w-12" />
          <div>
            <h3 class="font-bold text-white">{{ coin.symbol }}</h3>
            <span class="text-white">{{ coin.name }}</span>
          </div>
        </div>
        <hr class=" mt-[10px]">
        <div class=" items-center mt-[10px] text-white">
          
          <h4 class="text-gray-400 mt-[30px]">Lorem ipsum dolor sit amet, consectetur adipiscing elit sit vitae donec aliquam pretium sociis ultrices aenean faucibus congue mattis penatibus malesuada convallis.</h4>
          <!-- <router-link to="/register">Invest in {{ coin.name }} <span class=" mt-[20px] pi pi-arrow-right transition-transform duration-300 group-hover:translate-x-[5px]"></span></router-link> -->
          <button
            @click="handleInvest(coin.name)"
            class="mt-[20px] text-logoBlue font-semibold hover:underline"
          >
            Invest in {{ coin.name }}
            <span class="pi pi-arrow-right ml-2 transition-transform duration-300 group-hover:translate-x-[5px]"></span>
          </button>
        </div>
      </div>
    </Slide>

    <template #addons>
      <Navigation />
      <Pagination />
    </template>
    
  </Carousel>

</template>