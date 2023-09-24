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
      {
        path: '/cadastroGrupo',
        name: 'cadastroGrupo',
        component: () => import(/* webpackChunkName: "cadastroGrupo" */ '@/views/CadastroGrupo.vue'),
      },
      {
        path: '/cadastroGastos',
        name: 'cadastroGastos',
        component: () => import(/* webpackChunkName: "cadastroGrupo" */ '@/views/CadastroGastos.vue'),
      },
      {
        path: '/cadastroCategoria',
        name: 'cadastroCategoria',
        component: () => import(/* webpackChunkName: "cadastroCategoria" */ '@/views/CadastroCategoria.vue'),
      },
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
