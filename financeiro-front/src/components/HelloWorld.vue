<template>
  <div>
    <!-- <div class="greetings">
      <h1>Julio</h1>

      <ButtonCustom text="" />
    </div> -->

    <v-card>
      <div class="form-cadastro">
        <h2>Cadastro de categoria</h2>
        <p class="input-container">
          <input
            type="text"
            id="deCategoria"
            v-model="deCategoria"
            required
            class="input"
            placeholder="Categoria"
          />
        </p>
        <ButtonCustom
          class="button-custom"
          @click="cadastrarCategoria"
          style="margin-right: 10px"
          text="Cadastrar"
        >
          Cadastrar
        </ButtonCustom>
        <ButtonCustom
          class="button-custom"
          @click="cancelar"
          text="Cancelar"
        ></ButtonCustom>
      </div>
    </v-card>
  </div>
</template>

<script setup lang="ts">
import ButtonCustom from "./ButtonCustom.vue";
</script>
