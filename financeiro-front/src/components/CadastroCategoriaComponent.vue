<template>
  <div class="flex h-screen justify-center">
    <div class="m-2 h-screen w-[600px]">
      <div class="m-2 h-8 w-full">
        <p class="text-verde-50 text-xl mb-3 w-full text-center">
          Cadastro de Categorias
        </p>
      </div>

      <div class="m-2 h-20 w-full px-6">
        <p>
          <input
            type="text"
            id="deCategoria"
            v-model="deCategoria"
            required
            placeholder="Categoria"
            class="text-sm text-gray-base w-full mr-3 py-5 px-4 h-2 border border-gray-200 rounded mb-2"
          />
        </p>
      </div>
      <div class="m-2 h-20 w-full flex justify-center">
        <ButtonCustom
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

      <div>
        <table>
          <thead>
            <tr>
              <th>Categoria</th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="categoria in categorias" :key="categoria.cdCategoria">
              <td class="w-full">{{ categoria.deCategoria }}</td>
              <td class="w-56">
                <span @click="exibirCategoria(categoria)"
                  ><SvgIcon type="mdi" :path="mdiEyeOutline"></SvgIcon
                ></span>
              </td>
              <td class="w-56">
                <span
                  class="button-grid"
                  @click="
                    categoria.cdCategoria !== undefined
                      ? excluirCategoria(categoria.cdCategoria)
                      : null
                  "
                >
                  <SvgIcon type="mdi" :path="mdiDeleteOutline"></SvgIcon>
                </span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import axios from "axios";
import { ref, onMounted } from "vue";
import ButtonCustom from "./ButtonCustom.vue";
import { Categoria } from "@/type/Categoria";
import SvgIcon from "@jamescoyle/vue-icon";
import { mdiDeleteOutline, mdiEyeOutline } from "@mdi/js";

const deCategoria = ref("");

const categorias = ref<Categoria[]>([]);

const cadastrarCategoria = () => {
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

const exibirCategoria = (categoria: Categoria) => {
  deCategoria.value = categoria.deCategoria;
};

onMounted(() => {
  fetchCategorias();
});
</script>
