<template>
  <v-card>
    <v-layout>
      <v-navigation-drawer
        v-model="drawer"
        :rail="rail"
        permanent
        @click="rail = false"
      >
        <!-- <v-list-item
          prepend-avatar="https://randomuser.me/api/portraits/men/85.jpg"
          title="Usuário teste"
          nav
        > -->
        <v-list-item title="Usuário teste" nav>
          <template v-slot:append>
            <v-btn
              variant="text"
              icon="mdi-chevron-left"
              @click.stop="rail = !rail"
            ></v-btn>
          </template>
        </v-list-item>

        <v-divider></v-divider>

        <v-list density="compact" nav>
          <v-list-item
            prepend-icon="mdi-home-city"
            title="Home"
            value="home"
            :to="{ path: '/' }"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-account"
            title="Usuário"
            value="cadastroUsuario"
            :to="{ path: '/cadastroUsuario' }"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-credit-card"
            title="Cartão"
            value="cadastroCartao"
            :to="{ path: '/cadastroCartao' }"
          ></v-list-item>

          <v-list-item
            prepend-icon="mdi mdi-cash-register"
            title="Forma Pagto"
            value="formaPagto"
            :to="{ path: '/cadastroFormaPgto' }"
          >
          </v-list-item>

          <v-list-item
            prepend-icon="mdi mdi-select-group"
            title="Grupo"
            value="grupo"
            :to="{ path: '/cadastroGrupo' }"
          ></v-list-item>

          <v-list-item
            prepend-icon="mdi mdi-view-dashboard-edit-outline"
            title="Categoria"
            value="categoria"
            :to="{ path: '/cadastroCategoria' }"
          ></v-list-item>

          <v-list-item
            prepend-icon="mdi-cash-sync"
            title="Gastos"
            value="gastos"
            :to="{ path: '/cadastroGastos' }"
          ></v-list-item>
        </v-list>
      </v-navigation-drawer>

      <v-main style="height: max-content">
        <router-view></router-view>
      </v-main>
    </v-layout>
  </v-card>
</template>

<script setup lang="ts">
import { ref } from "vue";
import "@/assets/css/form-styles.css";

const drawer = ref("");
const rail = ref("");
</script>
