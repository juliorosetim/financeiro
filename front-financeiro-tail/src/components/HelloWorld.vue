<template>
  <v-card>
    <div
      class="relative flex w-4/5 flex-col rounded-xl bg-white bg-clip-border text-gray-700 shadow-md"
    >
      <h2>Cadastro de categoria</h2>
      <p class="input-container">
        <InputCustom
          type="text"
          id="deCategoria"
          v-model="deCategoria"
          required
          placeholder="Categoria"
          text="Nome"
          @input="onInputChange"
        />
      </p>
      <ButtonCustom text="Cadastrar" @click="cadastrarCategoria" />
      <ButtonCustom text="Cancelar" @click="cancelar" />
    </div>
  </v-card>

  <v-card>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
      <h2 style="padding-bottom: 10px">Lista de Categorias</h2>
      <v-simple-table
        class="w-full text-sm text-left text-gray-500 dark:text-gray-400"
      >
        <template v-slot:default>
          <thead
            class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
          >
            <tr>
              <th scope="col" class="px-6 py-3">Categoria</th>
              <th scope="col" class="px-6 py-3"></th>
              <th scope="col" class="px-6 py-3"></th>
            </tr>
          </thead>
          <tbody>
            <tr
              class="bg-white border-b dark:border-verde-50 hover:bg-verde-50 dark:hover:bg-verde-50"
              v-for="categoria in categorias"
              :key="categoria.cdCategoria"
            >
              <td class="px-6 py-4">{{ categoria.deCategoria }}</td>
              <td class="px-6 py-4">
                <span class="button-grid" @click="exibirCategoria(categoria)">
                  <IconView />
                </span>
              </td>
              <td class="px-6 py-4">
                <span
                  class="button-grid"
                  @click="
                    categoria.cdCategoria !== undefined
                      ? excluirCategoria(categoria.cdCategoria)
                      : null
                  "
                >
                  <IconDele />
                </span>
              </td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </v-card>
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
