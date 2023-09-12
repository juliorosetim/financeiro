// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '',
        name: 'Home',
        component: () => import(/* webpackChunkName: "home" */ '@/views/Home.vue'),
      },
      {
        path: '/cadastroUsuario',
        name: 'cadastroUsuario',
        component: () => import(/* webpackChunkName: "cadastroUsuario" */ '@/views/CadastroUsuario.vue'),
      },    
      {
        path: '/cadastroCartao',
        name: 'cadastroCartao',
        component: () => import(/* webpackChunkName: "cadastroCartao" */ '@/views/CadastroCartao.vue'),
      },     
      {
        path: '/cadastroFormaPgto',
        name: 'cadastroFormaPgto',
        component: () => import(/* webpackChunkName: "cadastroFormaPgto" */ '@/views/CadastroFormaPgto.vue'),
      },          
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
