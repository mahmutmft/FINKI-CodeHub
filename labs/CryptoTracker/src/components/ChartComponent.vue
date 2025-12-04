<script setup>
import { ref, onMounted } from "vue";
import { Line } from "vue-chartjs";
import ChartService from "../service/chartService";
import { Chart, registerables } from "chart.js";

Chart.register(...registerables);

const selectedCoin = ref("BTC");
const selectedCoinData = ref("1DAY");

const btcData = ref({
  labels: [],
  datasets: [
    {
      label: "Цена во USD",
      backgroundColor: "rgba(255, 206, 86, 0.5)",
      borderColor: "rgba(255, 206, 86, 1)",
      borderWidth: 2,
      data: [],
    },
  ],
});

// const chartOptions = ref({
//   responsive: true,
//   plugins: {
//     legend: {
//       display: true,
//     },
//   },
// });


const chartOptions = {
  responsive: true,
  interaction: {
    mode: 'index',    // Прикажува податоци за сите серии на оската X
    intersect: false, // Прикажува податоци дури и кога не се директно на точката
  },
  plugins: {
    tooltip: {
      enabled: true,  // Активирај tooltip
      mode: 'index',
      intersect: true,
      callbacks: {
        label: function (context) {
          return `Price: $${context.parsed.y.toFixed(2)}`;
        },
      },
    },
  },
  scales: {
    x: {
      display: true,
    },
    y: {
      display: true,
    },
  },
};


// Fetch chart data based on the selected coin and period
const fetchChartData = async () => {
  try {
    const response = await ChartService.getChart(selectedCoin.value, selectedCoinData.value);

    const prices = response.data.map((entry) => ({
      date: selectedCoinData.value === "1DAY"
        ? new Date(entry.time_period_start).toLocaleTimeString() 
        : new Date(entry.time_period_start).toLocaleDateString(), 
      price: entry.rate_close,
    }));

    btcData.value = {
      labels: prices.map((entry) => entry.date),
      datasets: [
        {
          label: `${selectedCoin.value} Цена во USD`,
          backgroundColor: "rgba(255, 206, 86, 0.5)",
          borderColor: "rgba(255, 206, 86, 1)",
          borderWidth: 2,
          fill: true,
          data: prices.map((entry) => entry.price),
        },
      ],
    };
  } catch (error) {
    console.error("Грешка при преземање на податоци:", error);
  }
};

// Fetch data when component mounts
onMounted(fetchChartData);
</script>

<template>
  <div className="bg-[#1B2028] rounded-[10px] p-[20px] w-[780px] ">
    <h1 className="font-bold text-white">Popular Coins</h1>
    <div >
      <select v-model="selectedCoin" @change="fetchChartData" className="border-none bg-[#454151] text-white font-bold p-[5px]">
        <option value="BTC">BitCoin</option>
        <option value="LTC">LiteCoin</option>
        <option value="ETH">Ethereum</option>
        <option value="SOL">Solana</option>
      </select>
      <select v-model="selectedCoinData" @change="fetchChartData" className="border-none bg-[#454151] text-white font-bold ml-[10px] p-[5px]">
        <option value="1DAY">24 HOURS</option>
        <option value="7DAY">7 DAYS</option>
        <option value="1MTH">1 MONTH</option>
      </select>
      <Line :data="btcData" :options="chartOptions" />
    </div>
  </div>
</template>
