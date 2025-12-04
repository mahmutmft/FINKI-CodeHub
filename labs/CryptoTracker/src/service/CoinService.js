
import axios from "axios";
import API_KEY from "./key";



class CoinService{

    static getCoins(){
        return axios.get(`https://rest.coincap.io/v3/assets?apiKey=${API_KEY}`)
    }
}

export default CoinService;

