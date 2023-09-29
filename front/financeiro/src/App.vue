<template>
  <v-card>
    <v-layout>
      <v-app-bar prominent>
        <v-app-bar-nav-icon
          variant="text"
          @click.stop="drawer = !drawer"
          color="white"
        ></v-app-bar-nav-icon>

        <v-toolbar-title>FinanCah</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn variant="text" icon="mdi-magnify"></v-btn>

        <v-btn variant="text" icon="mdi-filter"></v-btn>

        <v-btn variant="text" icon="mdi-dots-vertical"></v-btn>
      </v-app-bar>

      <v-navigation-drawer v-model="drawer" location="top" temporary>
        <v-list density="compact" nav>
          <v-list-item
            prepend-icon="mdi mdi-swap-horizontal-variant"
            title="Transações"
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
            title="Lançamentos"
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

const drawer = ref<Boolean>(false);
</script>
