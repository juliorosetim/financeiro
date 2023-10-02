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

      <!-- <div>
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
      </div> -->

      <div class="m-2 h-8 w-full">
        <p class="text-verde-50 text-xl mb-3 w-full text-center">
          Categorias Cadastradas
        </p>
      </div>

      <div class="flex items-center justify-center">
        <div class="col-span-full">
          <div class="overflow-auto lg:overflow-visible">
            <table
              class="table text-verde-100 border-separate space-y-6 text-sm"
            >
              <thead class="text-verde-100 bg-verde-50">
                <tr>
                  <th class="p-3">Categoria</th>
                  <th class="p-3 text-left"></th>
                  <th class="p-3 text-left"></th>
                  <th class="p-3 text-left"></th>
                  <th class="p-3 text-left"></th>
                </tr>
              </thead>
              <tbody>
                <tr
                  class="bg-verde-50"
                  v-for="categoria in categorias"
                  :key="categoria.cdCategoria"
                >
                  <td class="p-3">
                    <div class="flex align-items-center">
                      <div class="ml-3">
                        <div class="">{{ categoria.deCategoria }}</div>
                      </div>
                    </div>
                  </td>
                  <td class="p-3 flex">
                    <span @click="exibirCategoria(categoria)"
                      ><SvgIcon type="mdi" :path="mdiEyeOutline"></SvgIcon
                    ></span>

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
                <!-- <tr class="bg-gray-800">
                  <td class="p-3">
                    <div class="flex align-items-center">
                      <img
                        class="rounded-full h-12 w-12 object-cover"
                        src="https://images.unsplash.com/photo-1423784346385-c1d4dac9893a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
                        alt="unsplash image"
                      />
                      <div class="ml-3">
                        <div class="">Realme</div>
                        <div class="text-gray-500">mail@rgmail.com</div>
                      </div>
                    </div>
                  </td>
                  <td class="p-3">Technology</td>
                  <td class="p-3 font-bold">200.00$</td>
                  <td class="p-3">
                    <span class="bg-red-400 text-gray-50 rounded-md px-2"
                      >no stock</span
                    >
                  </td>
                  <td class="p-3">
                    <a href="#" class="text-gray-400 hover:text-gray-100 mr-2">
                      <i class="material-icons-outlined text-base"
                        >visibility</i
                      >
                    </a>
                    <a href="#" class="text-gray-400 hover:text-gray-100 mx-2">
                      <i class="material-icons-outlined text-base">edit</i>
                    </a>
                    <a href="#" class="text-gray-400 hover:text-gray-100 ml-2">
                      <i class="material-icons-round text-base"
                        >delete_outline</i
                      >
                    </a>
                  </td>
                </tr>
                <tr class="bg-gray-800">
                  <td class="p-3">
                    <div class="flex align-items-center">
                      <img
                        class="rounded-full h-12 w-12 object-cover"
                        src="https://images.unsplash.com/photo-1600856209923-34372e319a5d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2135&q=80"
                        alt="unsplash image"
                      />
                      <div class="ml-3">
                        <div class="">Samsung</div>
                        <div class="text-gray-500">mail@rgmail.com</div>
                      </div>
                    </div>
                  </td>
                  <td class="p-3">Technology</td>
                  <td class="p-3 font-bold">200.00$</td>
                  <td class="p-3">
                    <span class="bg-yellow-400 text-gray-50 rounded-md px-2"
                      >start sale</span
                    >
                  </td>
                  <td class="p-3">
                    <a href="#" class="text-gray-400 hover:text-gray-100 mr-2">
                      <i class="material-icons-outlined text-base"
                        >visibility</i
                      >
                    </a>
                    <a href="#" class="text-gray-400 hover:text-gray-100 mx-2">
                      <i class="material-icons-outlined text-base">edit</i>
                    </a>
                    <a href="#" class="text-gray-400 hover:text-gray-100 ml-2">
                      <i class="material-icons-round text-base"
                        >delete_outline</i
                      >
                    </a>
                  </td>
                </tr> -->
              </tbody>
            </table>
          </div>
        </div>
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

<style scoped>
.table {
  border-spacing: 0 5px;
}

i {
  font-size: 1rem !important;
}

.table tr {
  border-radius: 20px;
}

tr td:nth-child(n + 5),
tr th:nth-child(n + 5) {
  border-radius: 0 0.625rem 0.625rem 0;
}

tr td:nth-child(1),
tr th:nth-child(1) {
  border-radius: 0.625rem 0 0 0.625rem;
}
</style>
