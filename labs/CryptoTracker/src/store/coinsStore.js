import {defineStore} from 'pinia'
import { ref } from 'vue'
import CoinService from '../service/CoinService'

export const useCoinsStore = defineStore('coin', ()=>{
    
    const coinDataTop50 = ref([])

    const fetchCoins = async () => {
        try{
            const response = await CoinService.getCoins()
            const coins = response.data.data.slice(0, 50);
            coinDataTop50.value = coins
        }catch(error){
            console.error('Error fetching coins:',error)
        }
    }
    return {coinDataTop50, fetchCoins}
})