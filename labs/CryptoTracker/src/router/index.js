import HomePageComponent from "../components/HomePageComponent.vue";
import LoginComponent from "../components/LoginComponent.vue";
import RegisterComponent from "../components/RegisterComponent.vue";
import InvestInCoinsComponent from "../components/InvestInCoinsComponent.vue";

import { createRouter, createWebHistory } from "vue-router";
import WalletComponent from "../components/WalletComponent.vue";
import PortfolioComponent from "../components/PortfolioComponent.vue";
const routes = [
    {path: '/', component: HomePageComponent},
    {path: '/login', component: LoginComponent},
    {path: '/register', component: RegisterComponent},
    {path: '/invest/:coinName', component: InvestInCoinsComponent},
    {path: '/wallet', component: WalletComponent},
    {path: '/portfolio', component: PortfolioComponent}
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
  });

export default router;
