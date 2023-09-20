<template>
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
</script>

<style scoped>
.classnav {
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 10px 20px;
  background-color: #007bff;
}

.classnav a {
  color: hwb(0 100% 0%);
  font-size: 18px;
  text-decoration: none;
}

.classnav a:hover {
  color: hsl(211, 85%, 28%);
}

.classnav a.active {
  color: #007bff;
}
</style>
