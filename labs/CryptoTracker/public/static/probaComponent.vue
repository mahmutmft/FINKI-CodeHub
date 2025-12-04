<!-- <script setup>
import 'vue3-carousel/carousel.css';
import { ref, onMounted } from "vue";
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel';
import ChartService from "../service/chartService";

const coins = ref(["BTC", "ETH", "LTC", "SOL", "ADA", "DOT", "DOGE"]);
const coinData = ref([]);

const config = {
  height: 200,
  itemsToShow: 4,
  gap: 5,
  breakpoints: {
    320: { itemsToShow: 1 },
    768: { itemsToShow: 2 },
    1024: { itemsToShow: 3 },
    1280: { itemsToShow: 4 },
  },
};

// 🚀 Фетчирање на цените за сите монети
const fetchAllCoinsData = async () => {
  try {
    const allData = await Promise.all(
      coins.value.map(async (coin.id) => {
        const response = await ChartService.getChart(coin.id);
        const latestEntry = response.data[response.data.length - 1];
        return {
          name: coin.id,
          price: `$${latestEntry.rate_close.toFixed(2)}`,
          change: calculateChange(response.data),
        };
      })
    );
    coinData.value = allData;
  } catch (error) {
    console.error("Грешка при вчитување на податоците:", error);
  }
};

// 📈 Калкулација на промената во цена
const calculateChange = (data) => {
  const firstPrice = data[0].rate_close;
  const lastPrice = data[data.length - 1].rate_close;
  const changePercent = (((lastPrice - firstPrice) / firstPrice) * 100).toFixed(2);
  return `${changePercent > 0 ? "+" : ""}${changePercent}%`;
};

onMounted(fetchAllCoinsData);
</script>

<template>
  <Carousel v-bind="config">
    <Slide v-for="coin.id in coinData" :key="coin.id.name">
      <div class="bg-[#1B2028] w-[300px] rounded-[10px] p-[20px]">
        <div class="flex gap-[10px] items-center">
          <img :src="`/static/${coin.id.name.toLowerCase()}.png`" alt="icon" class="w-12" />
            <h1>{{ coin.id.name.toLowerCase() }}</h1>
          <div>
            <h3 class="font-bold text-white">{{ coin.id.name }}</h3>
            <span class="text-white">{{ coin.id.name }}</span>
          </div>
        </div>
        <div class="flex justify-between items-center pt-[30px]">
          <h3 :class="coin.id.change.includes('+') ? 'text-[#1ECB4F]' : 'text-[#FF4D4D]'">
            {{ coin.id.change }}
          </h3>
          <img src="/src/static/btc-vector.png" class="w-12"/>
        </div>
      </div>
    </Slide>

    <template #addons>
      <Navigation />
      <Pagination />
    </template>
  </Carousel>
</template> -->
