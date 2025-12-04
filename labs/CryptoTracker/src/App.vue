<script setup>
import NavbarComponent from './components/NavbarComponent.vue';
import { useCoinsStore } from './store/coinsStore'
import { onMounted } from 'vue';


const coinStore = useCoinsStore()
onMounted(() => {
  coinStore.fetchCoins()
})

</script>

<template>
  
  <NavbarComponent/>
  <router-view></router-view>
  </template>

