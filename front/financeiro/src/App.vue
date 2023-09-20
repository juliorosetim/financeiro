<!-- <template>
  <v-nav class="classnav">
    <router-link to="/">Home</router-link>
    <router-link to="/cadastroUsuario">Usuário</router-link>
    <router-link to="/cadastroCartao">Cartão</router-link>
    <router-link to="/cadastroFormaPgto">Forma Pgto</router-link>
    <router-link to="/cadastroGrupo">Grupo</router-link>
    <router-link to="/cadastroGastos">Gastos</router-link>
  </v-nav>
  <router-view />
</template>

<script setup lang="ts">
</script> -->

<template>
  <v-card>
    <v-layout>
      <v-navigation-drawer
        v-model="drawer"
        :rail="rail"
        permanent
        @click="rail = false"
      >
        <v-list-item
          prepend-avatar="https://randomuser.me/api/portraits/men/85.jpg"
          title="Usuário teste"
          nav
        >
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
            prepend-icon="mdi-account-group-outline"
            title="Forma Pagto"
            value="formaPagto"
            :to="{ path: '/cadastroFormaPgto' }"
          >
          </v-list-item>

          <v-list-item
            prepend-icon="mdi-account-group-outline"
            title="Grupo"
            value="grupo"
            :to="{ path: '/cadastroGrupo' }"
          ></v-list-item>
          <v-list-item
            prepend-icon="mdi-local-atm"
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
// import path from "path";
import { ref } from "vue";
import { mdiAccountGroupOutline } from "@mdi/font";

const drawer = ref("");
const rail = ref("");
</script>

<style scoped>
.classnav {
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 10px 20px;
  background-color: #560bad;
}

.classnav a {
  color: hwb(0 100% 0%);
  font-size: 18px;
  text-decoration: none;
}

.classnav a:hover {
  color: #c692f7;
}

.classnav a.active {
  color: #c692f7;
}
</style>
