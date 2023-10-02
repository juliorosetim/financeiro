<template>
  <v-container>
    <h1>Julio</h1>
    <ButtonCustom text="teste"></ButtonCustom>
  </v-container>
</template>


<script setup lang="ts">
import axios from "axios";
import ButtonCustom from "./ButtonCustom.vue";
import InputCustom from "./InputCustom.vue";
import IconDele from "./IconDelete.vue";
import IconView from "./IconView.vue";
import { ref, onMounted } from "vue";
import { Categoria } from "@/types/Categoria";

const deCategoria = ref("");

const categorias = ref<Categoria[]>([]);

const onInputChange = () => {
  console.log("Valor de deCategoria:", deCategoria.value);
};

const cadastrarCategoria = () => {
  console.log(`deCategoria ${deCategoria.value}`);
  console.log("comparação ", deCategoria.value == "");

  if (deCategoria.value === "") {
    alert("Preencha o campo Categoria");
    return;
  }

  axios
    .post("http://localhost:8081/api/categoria", {
      deCategoria: deCategoria.value,
    })
    .then((response) => {
      console.log("Categoria cadastrada com sucesso!", response.data);
      deCategoria.value = "";

      fetchCategorias();
    })
    .catch((error) => {
      console.error("Erro ao cadastrar Categoria:", error);
    });
};

const cancelar = () => {
  deCategoria.value = "";
};

const fetchCategorias = () => {
  axios
    .get("http://localhost:8081/api/categoria")
    .then((response) => {
      categorias.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar a lista de Categorias:", error);
    });
};

const exibirCategoria = (categoria: Categoria) => {
  deCategoria.value = categoria.deCategoria;
};

const excluirCategoria = (cdCategoria: number) => {
  axios
    .delete(`http://localhost:8081/api/categoria/${cdCategoria}`)
    .then(() => {
      console.log("Categoria excluída com sucesso!");
      fetchCategorias();
    })
    .catch((error) => {
      console.error("Erro ao excluir Categoria:", error);
    });
};

onMounted(() => {
  fetchCategorias();
});
</script>
