<script setup>
import 'vue3-carousel/carousel.css';
import { ref, onMounted, onBeforeMount } from "vue";
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel';
import CoinService from '../service/CoinService';

// const coinData = ref([]);

// const coinData2 = ref([]);


// const config = {
//   height: 200,
//   itemsToShow: 4,
//   gap: 5,
//   breakpoints: {
//     320: { itemsToShow: 1 },
//     768: { itemsToShow: 2 },
//     1024: { itemsToShow: 3 },
//     1280: { itemsToShow: 4 },
//   },
//   wrapAround: true,
// };

// const fetchAllCoinsData = async () => {
//   try {

//     const myCoins = ['BTC', 'DOGE', 'ETH', 'SOL', 'LTC'];


//     const response = await CoinService.getCoins(); 
//     const cryptoCoins = response.data.data.filter((coin) => myCoins.includes(coin.symbol)); 
//     const cryptoCoins2 = response.data.data.slice(0, 50);
    
//     console.log(cryptoCoins)

//     console.log(response.data.data);

   

   

//     coinData.value = cryptoCoins
//     coinData2.value = cryptoCoins2;
//   } catch (error) {
//     console.error("Error fetching coins:", error);
//   }
// };

// onBeforeMount(fetchAllCoinsData);

// onMounted(() => {
//   fetchAllCoinsData();
// });
</script>

<template>

  <Carousel v-bind="config" :loop="true" :navigation="true" :pagination="true">
    <Slide v-for="coin in coinData2" :key="coin.symobl">
      <div class="bg-[#1B2028] w-[300px] rounded-[10px] p-[20px]">
        <div class="flex gap-[10px] items-center">
          <img :src="`/static/${coin.symbol.toLowerCase()}.png`" alt="icon" class="w-12" />
          <div>
            <h3 class="font-bold text-white">{{ coin.name }}</h3>
            <span class="text-white">{{ coin.symbol}}</span>
          </div>
        </div>
        <div class="flex justify-between items-center pt-[30px]">
          <div>
            <h5 class="text-white font-bold">${{ Number(coin.priceUsd).toFixed(2)}}</h5>
            <p  :class="(coin.changePercent24Hr) < 0 ? 'font-bold text-red-500' : 'font-bold text-[#1ECB4F]'">{{ Number(coin.changePercent24Hr).toFixed(2)}}%</p>
          </div>
          <div>
            <!-- <img :src="`/static/${coin.symbol.toLowerCase()}-vector.png`" class="w-22" /> -->
            <!-- <img :src="`/static/btc-vector.png`" class="w-22" /> -->
             <button class="bg-logoBlue text-textWhite px-[20px] py-[10px] rounded-lg font-bold hover:bg-opacity-90 transition">BUY</button>
          </div>
        </div>
      </div>
    </Slide>

    <template #addons>
      <Navigation />
      <Pagination />
    </template>
    
  </Carousel>
</template>