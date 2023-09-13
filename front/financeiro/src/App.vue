<template>
    <v-nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/cadastroUsuario">Usuário</router-link> |
      <router-link to="/cadastroCartao">Cartão</router-link> |
      <router-link to="/cadastroFormaPgto">Forma Pgto</router-link> |
      <router-link to="/cadastroGrupo">Grupo</router-link> |
      <router-link to="/cadastroGastos">Gastos</router-link> |
    </v-nav>
    <router-view/>
</template>

<script setup lang="ts">
  
</script>
