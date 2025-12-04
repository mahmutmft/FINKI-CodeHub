<script setup>
import CardsComponent from './CardsComponent.vue';
import ChartComponent from './ChartComponent.vue';
import CoinCardsComponent from './CoinCardsComponent.vue';
import LiveMarketComponent from './LiveMarketComponent.vue';
import MyPortfolio from './MyPortfolio.vue';
import PopularCoinsChart from './PopularCoinsChart.vue';
import {ref, onMounted} from 'vue'
import { useRoute } from 'vue-router';
import {auth} from '../firebase/firebase';
import { onAuthStateChanged } from 'firebase/auth';


const route = useRoute()
const isLoggedIn = ref(false);

const isLoading = ref(true);

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
})

</script>

<template>

<div v-if="!isLoading && !isLoggedIn" class="mt-[20px] text-center w-[700px] mx-auto p-[30px] rounded-[10px] relative overflow-hidden">
    <div class="absolute inset-0 rounded-[10px]"
             style="background: linear-gradient(90deg, rgba(27,32,40,0) 0%, 
      rgba(27,32,40,1) 40%, 
      rgba(27,32,40,1) 60%, 
      rgba(27,32,40,0) 100%);"></div>
        <div class="relative z-10">
            <h1 class="text-white font-bold text-6xl">Buy, sell and trade crypto like never before</h1>
            <h3 class="text-white mt-[15px]">Lorem ipsum dolor sit amet, consectetur adipiscing elit elit, a, eget tristique metus, turpis vestibulum, pretium donec cras at sit et, etiam ac venenatis ut eu eu eu sollicitudin.</h3>
            <div class="mt-[30px]">
                <router-link to="/register" class="bg-logoBlue text-textWhite px-[20px] py-[10px] rounded-lg font-bold hover:bg-opacity-90 transition">GET STARTED</router-link>

            </div>
        </div>
    </div>


    <div className="mt-[50px]">
        <!-- <CardsComponent/> -->
        <CoinCardsComponent/>
    </div>
    <div class="flex flex-col lg:flex-row gap-[20px] justify-center mt-[85px]">
        <MyPortfolio/>
        <PopularCoinsChart/>
    </div>
    <LiveMarketComponent class="mt-[50px]"/>
</template>