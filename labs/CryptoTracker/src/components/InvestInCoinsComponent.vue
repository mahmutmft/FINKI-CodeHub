<script setup>
import { useRoute } from 'vue-router'
import { useCoinsStore } from '../store/coinsStore'
import { onMounted, ref, watch, computed } from 'vue'
import { Line } from "vue-chartjs"
import ChartService from "../service/chartService"
import { Chart, registerables } from "chart.js"
import { auth, db } from "../firebase/firebase"
import { doc, setDoc, collection, getDocs, updateDoc, query, where, getDoc, addDoc } from "firebase/firestore"

Chart.register(...registerables)

const route = useRoute()
const coinName = route.params.coinName
const coin = useCoinsStore().coinDataTop50.find((coin) => coin.name === coinName)

const chartData = ref({})
const selectedCoinData = ref("1MTH")

const showBuyModal = ref(false)
const showSellModal = ref(false)
const mode = ref("buy") // 'buy' or 'sell'
const tradeType = ref("amount") // 'amount' or 'usd'
const inputValue = ref("")
const calculatedValue = ref(0)
const currentBalance = ref(0)
const walletBalance = ref(0)
const successMessage = ref("")
const countdown = ref(10)
const countdownActive = ref(false)
const showCountdownModal = ref(false)

const chartOptions = {
  responsive: true,
  interaction: { mode: "index", intersect: true },
  plugins: {
    tooltip: {
      enabled: true,
      callbacks: {
        label: context => `Price: $${formatPrice(context.parsed.y)}`
      }
    }
  },
  scales: {
    x: { display: true },
    y: { display: true }
  }
}

const fetchChartData = async () => {
  try {
    const response = await ChartService.getChart(coin.id, selectedCoinData.value)
    const chartArray = response.data?.data
    if (chartArray && chartArray.length > 0) {
      const prices = chartArray.map((entry) => ({
        rawDate: new Date(entry.date),
        date: new Date(entry.date).toLocaleTimeString(),
        price: parseFloat(entry.priceUsd),
      }))

      prices.sort((a, b) => a.rawDate - b.rawDate)
      const horizontalThreshold = prices[0].price
      const labels = prices.map(e => e.date)

      chartData.value[coin.id] = {
        labels,
        datasets: [
          {
            label: `${coin.symbol} (Above Threshold)`,
            backgroundColor: "rgba(30, 203, 79, 0.3)",
            borderColor: "#1ECB4F",
            data: prices.map(p => p.price > horizontalThreshold ? p.price : null),
            borderWidth: 2,
            pointRadius: 0,
            pointHoverRadius: 6,
            fill: false,
          },
          {
            label: `${coin.symbol} (Below Threshold)`,
            backgroundColor: "rgba(239, 68, 68, 0.3)",
            borderColor: "#EF4444",
            data: prices.map(p => p.price <= horizontalThreshold ? p.price : null),
            borderWidth: 2,
            pointRadius: 0,
            pointHoverRadius: 6,
            fill: false,
          },
          {
            label: `Threshold (${horizontalThreshold})`,
            data: new Array(labels.length).fill(horizontalThreshold),
            borderColor: "#FFD700",
            borderWidth: 1,
            borderDash: [10, 5],
            pointRadius: 0,
            fill: false,
          },
        ]
      }
    }
  } catch (error) {
    console.error("Chart error:", error)
  }
}

onMounted(() => {
  if (coin && coin.id) fetchChartData()
})

const fetchBalance = async () => {
  const user = auth.currentUser
  if (!user) return

  const coinRef = collection(db, 'users', user.uid, 'cryptoCoins')
  const q = query(coinRef, where("coinId", "==", coin.id))
  const snapshot = await getDocs(q)

  currentBalance.value = !snapshot.empty ? snapshot.docs[0].data().amountOwned : 0

  const userSnap = await getDoc(doc(db, "users", user.uid))
  walletBalance.value = userSnap.exists() ? userSnap.data().balance : 0
}

const updateWalletBalance = async (uid, delta) => {
  const userRef = doc(db, "users", uid)
  const userSnap = await getDoc(userRef)
  if (userSnap.exists()) {
    const newBalance = (userSnap.data().balance || 0) + delta
    await updateDoc(userRef, { balance: newBalance })
    walletBalance.value = newBalance
  }
}

const addTransaction = async (uid, amount, description, type) => {
  await addDoc(collection(db, "users", uid, "transactions"), {
    amount,
    description,
    type,
    timestamp: Date.now()
  })
}

const openBuyModal = async () => {
  mode.value = "buy"
  tradeType.value = "amount"
  inputValue.value = ""
  calculatedValue.value = 0
  showBuyModal.value = true
  await fetchBalance()
}

const openSellModal = async () => {
  mode.value = "sell"
  tradeType.value = "amount"
  inputValue.value = ""
  calculatedValue.value = 0
  showSellModal.value = true
  await fetchBalance()
}

const closeModal = () => {
  showBuyModal.value = false
  showSellModal.value = false
  inputValue.value = ""
  calculatedValue.value = 0
  tradeType.value = "amount"
}

const updateMaxBuyAmount = () => {
  const price = parseFloat(coin.priceUsd)
  if (tradeType.value === "amount") {
    inputValue.value = walletBalance.value / price
  } else if (tradeType.value === "usd") {
    inputValue.value = walletBalance.value
  }
}

const updateMaxSellAmount = () => {
  const price = parseFloat(coin.priceUsd)
  if (tradeType.value === "amount") {
    inputValue.value = currentBalance.value
  } else if (tradeType.value === "usd") {
    inputValue.value = currentBalance.value * price
  }
}

const startCountdown = () => {
  countdown.value = 10
  countdownActive.value = true
  showCountdownModal.value = true

  const interval = setInterval(() => {
    if (countdown.value > 0) {
      countdown.value--
    } else {
      clearInterval(interval)
      closeCountdownModal()
    }
  }, 1000)
}

const closeCountdownModal = () => {
  countdownActive.value = false
  showCountdownModal.value = false
  successMessage.value = ""
}

const isBuyDisabled = computed(() => {
  const input = parseFloat(inputValue.value)
  const price = parseFloat(coin.priceUsd)
  if (isNaN(input) || input <= 0) return true
  const usdRequired = tradeType.value === "amount" ? input * price : input
  return usdRequired > walletBalance.value
})

const isSellDisabled = computed(() => {
  const input = parseFloat(inputValue.value)
  if (isNaN(input) || input <= 0) return true
  let valueInUSD = 0
  if (tradeType.value === "amount") {
    valueInUSD = input * coin.priceUsd
  } else if (tradeType.value === "usd") {
    valueInUSD = input
  }

  return valueInUSD > (currentBalance.value * coin.priceUsd)
})

watch([inputValue, tradeType, mode], () => {
  const input = parseFloat(inputValue.value)
  const price = parseFloat(coin.priceUsd)
  if (isNaN(input) || input <= 0) {
    calculatedValue.value = 0
    return
  }

  if (tradeType.value === "amount") {
    calculatedValue.value = input * price
  } else {
    calculatedValue.value = input / price
  }
})

const isProcessing = ref(false)

const confirmBuy = async () => {
  if (isProcessing.value) return 

  isProcessing.value = true
  try{
    const user = auth.currentUser
    const price = parseFloat(coin.priceUsd)
    const input = parseFloat(inputValue.value)

    if (!user || isNaN(input) || input <= 0){
      alert("Invalid input")
      return 
    }
    const amountToBuy = tradeType.value === "amount" ? input : input / price
    const valueUsd = amountToBuy * price

    const userRef = doc(db, "users", user.uid)
    const userSnap = await getDoc(userRef)
    
    if (!userSnap.exists()) {
      alert("User not found.")
      return 
    }

    const freshBalance = userSnap.exists() ? userSnap.data().balance : 0

    if (valueUsd > freshBalance) {
      alert("You do not have enough funds. Try refreshing.")
      return 
    }

    const coinRef = collection(db, 'users', user.uid, 'cryptoCoins')
    const q = query(coinRef, where("coinId", "==", coin.id))
    const snapshot = await getDocs(q)

    if (!snapshot.empty) {
      const docId = snapshot.docs[0].id
      const data = snapshot.docs[0].data()
      await updateDoc(doc(db, 'users', user.uid, 'cryptoCoins', docId), {
        amountOwned: data.amountOwned + amountToBuy,
        valueUsd: (data.amountOwned + amountToBuy) * price
      })
    } else {
      await addDoc(coinRef, {
        coinId: coin.id,
        coinName: coin.name,
        symbol: coin.symbol,
        amountOwned: amountToBuy,
        valueUsd: valueUsd
      })
    }

    await updateWalletBalance(user.uid, -valueUsd)
    await addTransaction(user.uid, -valueUsd, `Bought ${coin.name}`, "Buy")

    await fetchBalance()

    successMessage.value = `You successfully bought ${amountToBuy.toFixed(8)} ${coin.symbol} for $${valueUsd.toFixed(2)}`
    startCountdown()

  } catch (error){
      console.error("Error during purchase:", error)
      alert("Something went wrong. Please try again.")
    } 
    finally{
      isProcessing.value = false  
    }
}  

const confirmSell = async () => {
  if (isProcessing.value) return
  
  isProcessing.value = true
  
  try{
    const user = auth.currentUser
    const input = parseFloat(inputValue.value)
    const price = parseFloat(coin.priceUsd)

    if (!user || isNaN(input) || input <= 0){
      alert("Invalid input")
      return
    } 
    
    let amountToSell = 0
    let valueUsd = 0

    if (tradeType.value === "amount") {
      if (input > currentBalance.value){
        alert("Insufficient holdings")
        return
      }
      amountToSell = input
      valueUsd = amountToSell * price
    }

    else if (tradeType.value === "usd") {
      valueUsd = input

      if (valueUsd > currentBalance.value * price){ 
        alert("Insufficient funds to sell by USD")
        return 
      }
      amountToSell = valueUsd / price
    }

    const coinRef = collection(db, 'users', user.uid, 'cryptoCoins')
    const q = query(coinRef, where("coinId", "==", coin.id))
    const snapshot = await getDocs(q)

    if (!snapshot.empty) {
      const docId = snapshot.docs[0].id
      const data = snapshot.docs[0].data()

      const freshAmountOwned = data.amountOwned
      if (amountToSell > freshAmountOwned) {
        alert("You do not have enough to sell. Try refreshing.")
        return 
      }
      const updatedAmount = currentBalance.value - amountToSell
      const updatedValue = updatedAmount * price
      const usdEarned = valueUsd

      await updateDoc(doc(db, 'users', user.uid, 'cryptoCoins', docId), {
        amountOwned: updatedAmount,
        valueUsd: updatedValue
      })

      await updateWalletBalance(user.uid, usdEarned)
      await addTransaction(user.uid, usdEarned, `Sold ${coin.name}`, "Sell")

      await fetchBalance();

      successMessage.value = `You successfully sold ${amountToSell.toFixed(8)} ${coin.symbol} for $${usdEarned.toFixed(2)}`
      startCountdown()
    } else {
      alert("You don't own this coin")
      return 
    }
  } catch (error){
      console.error("Error during sell:", error)
      alert("Something went wrong. Please try again.")
    } 
    finally{
      isProcessing.value = false
    }
}
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
  <div class="min-h-screen flex items-center justify-center">
    <div class="text-center">
      <h1 class="text-white font-bold text-3xl mb-4">Invest in {{ coinName }}</h1>
      <h1 class="text-white">{{ coin.symbol }}</h1>
      <h2 class="text-white">${{ formatPrice(Number(coin.priceUsd)) }}</h2>
      <p
        :class="coin.changePercent24Hr < 0 ? 'font-bold text-red-500' : 'font-bold text-[#1ECB4F]'"
      >
        {{ Number(coin.changePercent24Hr).toFixed(2) }}%
      </p>

      <div
        v-if="coin && chartData[coin.id]"
        class="w-[1000px] bg-[#1B2028] rounded-[10px] p-[20px] mt-6 mb-6"
      >
        <Line :data="chartData[coin.id]" :options="chartOptions" />
      </div>

      <div class="flex justify-center gap-4 mb-4">
        <button @click="openBuyModal" class="bg-[#1ECB4F] text-white px-5 py-2 rounded-lg font-bold">
          Buy
        </button>
        <button @click="openSellModal" class="bg-red-500 text-white px-5 py-2 rounded-lg font-bold">
          Sell
        </button>
      </div>

      <!-- Buy Modal -->
      <div v-if="showBuyModal" class="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50">
        <div class="bg-[#1B2028] rounded-2xl p-6 w-[400px] text-white relative">
          <div class="flex justify-between items-start mb-4">
            <div class="flex flex-col items-start">
              <h2 class="text-xl font-bold">Buy {{ coin.symbol }}</h2>
              <h3 class="text-sm text-gray-300 mt-1">Current Price: ${{ formatPrice(Number(coin.priceUsd)) }}</h3>
              <p class="mt-2">Current Balance: ${{ walletBalance.toFixed(2) }}</p>
            </div>
            <button @click="closeModal" class="text-xl">&times;</button>
          </div>

          <div class="flex justify-center gap-4 mb-4">
            <button
              @click="tradeType = 'amount'; inputValue = ''"
              :class="tradeType === 'amount' ? 'bg-[#1ECB4F]' : 'bg-gray-700'"
              class="px-4 py-2 rounded-full font-bold w-1/2"
            >
              By Amount
            </button>
            <button
              @click="tradeType = 'usd'; inputValue = ''"
              :class="tradeType === 'usd' ? 'bg-[#1ECB4F]' : 'bg-gray-700'"
              class="px-4 py-2 rounded-full font-bold w-1/2"
            >
              By USD
            </button>
          </div>

          <div class="mb-4">
            <input
              v-model="inputValue"
              type="number"
              placeholder="Enter value"
              class="w-full px-4 py-2 rounded-lg bg-gray-800 text-white"
            />
          </div>

          <button
            @click="updateMaxBuyAmount"
            class="w-full bg-gray-700 text-white font-bold py-2 rounded-lg"
          >
            Max
          </button>

          <div class="mb-6 text-center">
            <p v-if="tradeType === 'amount'">
              You will pay: ${{ formatPrice(calculatedValue) }}
            </p>
            <p v-else>
              You will receive: {{ formatPrice(calculatedValue) }} {{ coin.symbol }}
            </p>
          </div>
          <p v-if="tradeType==='amount' && calculatedValue > walletBalance" class="text-red-500 text-sm mb-2">
            Insufficient amount in portfolio!
          </p>
          <p v-if="tradeType==='usd' && inputValue > walletBalance" class="text-red-500 text-sm mb-2">
            Insufficient funds in wallet!
          </p>

          <button
            @click="confirmBuy"
            :disabled="isBuyDisabled"
            :class="[
              'w-full font-bold py-2 rounded-lg',
              isBuyDisabled ? 'bg-gray-500 cursor-not-allowed' : 'bg-[#1ECB4F] hover:bg-green-600 text-white'
            ]"
          >
            Confirm Purchase
          </button>
        </div>
      </div>

      <!-- Sell Modal -->
      <div v-if="showSellModal" class="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50">
        <div class="bg-[#1B2028] rounded-2xl p-6 w-[400px] text-white relative">
          <div class="flex justify-between items-start mb-4">
            <div class="flex flex-col items-start">
              <h2 class="text-xl font-bold">Sell {{ coin.symbol }}</h2>
              <h3 class="text-sm text-gray-300 mt-1">Current Price: ${{ formatPrice(Number(coin.priceUsd)) }}</h3>
              <p v-if="tradeType === 'amount'" class="mt-2">Amount Owned: {{ formatPrice(currentBalance) }} {{ coin.symbol }}</p>
              <p v-else class="mt-2">Current Balance: ${{ formatPrice(Number(currentBalance*coin.priceUsd)) }}</p>
            </div>
            <button @click="closeModal" class="text-xl">&times;</button>
          </div>

          <div class="flex justify-center gap-4 mb-4">
            <button
              @click="tradeType = 'amount'; inputValue = ''"
              :class="tradeType === 'amount' ? 'bg-[#1ECB4F]' : 'bg-gray-700'"
              class="px-4 py-2 rounded-full font-bold w-1/2"
            >
              By Amount
            </button>
            <button
              @click="tradeType = 'usd'; inputValue = ''"
              :class="tradeType === 'usd' ? 'bg-[#1ECB4F]' : 'bg-gray-700'"
              class="px-4 py-2 rounded-full font-bold w-1/2"
            >
              By USD
            </button>
          </div>

          <div class="mb-4">
            <input
              v-model="inputValue"
              type="number"
              placeholder="Enter value"
              class="w-full px-4 py-2 rounded-lg bg-gray-800 text-white"
            />
          </div>
          
          <button
            @click="updateMaxSellAmount"
            class="w-full bg-gray-700 text-white font-bold py-2 rounded-lg"
          >
            ALL
          </button>

          <div class="mb-6 text-center">
            <p v-if="tradeType === 'amount'">
              You will receive: ${{ formatPrice(inputValue * coin.priceUsd)}}
            </p>
            <p v-else>
              You will sell: {{ formatPrice(calculatedValue) }} {{ coin.symbol }}
            </p>
          </div>
          <p v-if="tradeType === 'amount' && inputValue > currentBalance" class="text-red-500 text-sm mb-2">
            Insufficient amount owned to sell!
          </p>
          <p v-if="tradeType === 'usd' && inputValue > currentBalance*coin.priceUsd" class="text-red-500 text-sm mb-2">
            Insufficient amount owned in USD to sell!
          </p>

          <button
            @click="confirmSell"
            :disabled="isSellDisabled"
            :class="[
              'w-full font-bold py-2 rounded-lg',
              isSellDisabled ? 'bg-gray-500 cursor-not-allowed' : 'bg-red-500 hover:bg-red-600 text-white'
            ]"
          >
            Confirm Sell
          </button>
        </div>
      </div>
    </div>
  </div>
  <div v-if="showCountdownModal" class="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50">
    <div class="bg-[#1B2028] rounded-2xl p-6 w-[400px] text-white text-center relative">
      <h2 class="text-xl font-bold mb-4">Transaction Successful!</h2>
      
      <p class="text-lg mb-2">{{ successMessage }}</p>
      <p class="text-sm text-gray-400">This popup will close in {{ countdown }} seconds.</p>

      <button @click="closeCountdownModal" class="mt-4 bg-red-500 text-white font-bold py-2 px-4 rounded-lg">
        Close Now
      </button>
    </div>
  </div>

</template>
