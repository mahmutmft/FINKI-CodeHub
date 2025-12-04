<script setup>
import { ref, onMounted, computed } from "vue";
import { Line } from "vue-chartjs";
import ChartService from "../service/chartService";
import { Chart, registerables } from "chart.js";
import { useCoinsStore } from "../store/coinsStore";
Chart.register(...registerables);

const coinsStore = useCoinsStore();

const topCoins = computed(() => {
  return [...coinsStore.coinDataTop50]
    .sort((a, b) => parseFloat(b.volumeUsd24Hr) - parseFloat(a.volumeUsd24Hr))
    .slice(0, 4);
});

const coinColors2 = ref({});
const colors = [
  "rgba(75, 192, 192, 0.5)",
  "rgba(54, 162, 235, 0.5)",
  "rgba(255, 99, 132, 0.5)",
  "rgba(255, 206, 86, 0.5)",
];

const selectedCoinData = ref("1DAY");
const chartData = ref({});

const chartOptions = {
  responsive: true,
  interaction: {
    mode: "index",
    intersect: true,
  },
  plugins: {
    tooltip: {
      enabled: true,
      mode: "index",
      intersect: true,
      callbacks: {
        label: function (context) {
          return `Price: $${formatPrice(context.parsed.y)}`;
        },
      },
    },
  },
  scales: {
    x: { display: false },
    y: { display: true },
  },
};

const fetchChartData = async () => {
  for (const coin of topCoins.value) {
    try {
      const response = await ChartService.getChart(coin.id, selectedCoinData.value);

      const chartArray = response.data?.data;

      if (chartArray && chartArray.length > 0) {
        const prices = chartArray.map((entry) => ({
          date: new Date(entry.date).toLocaleTimeString(),
          price: parseFloat(entry.priceUsd),
        }));

        chartData.value[coin.id] = {
          labels: prices.map((entry) => entry.date),
          datasets: [
            {
              label: `${coin.symbol} Цена во USD`,
              backgroundColor: coinColors2.value[coin.id].backgroundColor,
              borderColor: coinColors2.value[coin.id].borderColor,
              borderWidth: 2,
              fill: true,
              data: prices.map((entry) => entry.price),
            },
          ],
        };
      } else {
        console.error(`There is no chartArray data for ${coin.id}`);
      }
    } catch (error) {
      console.error("Error fetching chart data for " + coin.id, error);
    }
  }
};

onMounted(() => {
  topCoins.value.forEach((coin, index) => {
    coinColors2.value[coin.id] = {
      backgroundColor: colors[index],
      borderColor: colors[index].replace("0.5", "1"),
    };
  });

  fetchChartData();
});

function formatPrice(price) {
  if (price >= 1.01) return price.toFixed(2)

  const [_, decimals] = price.toString().split(".")
  if (!decimals) return price.toFixed(2)

  const firstNonZeroIndex = decimals.search(/[^0]/)

  if (firstNonZeroIndex >= 4) {
    return price.toFixed(8)
  } else if (firstNonZeroIndex >= 2) {
    return price.toFixed(4)
  } else {
    return price.toFixed(2)
  }
}
</script>

<template>
  <div className="bg-[#1B2028] rounded-[10px] p-[20px] w-[1000px] flex flex-wrap justify-around">
    <h1 className="font-bold text-white w-full text-center mb-4 text-3xl">
      Today's most popular coins chart
    </h1>
    <div>
<!--      v-model go cuva izbraniot interval-->
<!--      @change gi obnovuva graficite-->
      <select v-model="selectedCoinData" @change="fetchChartData">
        <option value="1DAY">Last 24 hours (1DAY)</option>
        <option value="7DAY">Last 7 days (7DAY)</option>
        <option value="1MTH">Last 1 month (1MTH)</option>
      </select>
    </div>
    <div v-for="coin in topCoins" :key="coin.id" className="w-[45%] mb-6">
      <h2 class="text-white font-bold text-center mb-2">{{ coin.symbol }} Price</h2>
      <Line
        v-if="chartData[coin.id] && chartData[coin.id].labels.length > 0"
        :data="chartData[coin.id]"
        :options="chartOptions"
      />
      <p v-else class="text-white text-center">
        Error fetching chart data for {{ coin.symbol }}
      </p>
    </div>
  </div>
</template>
