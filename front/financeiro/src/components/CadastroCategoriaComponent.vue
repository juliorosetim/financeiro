<template>
  <v-container>
    <!-- Seção do formulário -->
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
        <button
          class="button-custom"
          @click="cadastrarCategoria"
          style="margin-right: 10px"
        >
          Cadastrar
        </button>
        <button class="button-custom" @click="cancelar">Cancelar</button>
      </div>
    </v-card>

    <v-card>
      <div class="grid-Categorias">
        <h2>Lista de Categorias</h2>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left" style="width: 100px">Código</th>
                <th class="text-left" style="width: 1000px">Categoria</th>
                <th class="text-left"></th>
                <th class="text-left"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="categoria in categorias" :key="categoria.cdCategoria">
                <td>{{ categoria.cdCategoria }}</td>
                <td>{{ categoria.deCategoria }}</td>
                <td>
                  <v-btn class="button-grid" @click="exibirCategoria(categoria)"
                    ><v-icon>mdi mdi-text-box-edit-outline</v-icon></v-btn
                  >
                </td>
                <td>
                  <v-btn
                    class="button-grid"
                    @click="
                      categoria.cdCategoria !== undefined
                        ? excluirCategoria(categoria.cdCategoria)
                        : null
                    "
                  >
                    <v-icon>mdi-delete</v-icon>
                  </v-btn>
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </div>
    </v-card>
  </v-container>
</template>

  <script setup lang="ts">
import axios from "axios";
import { ref, onMounted } from "vue";
import { Categoria } from "@/type/CategoriaType";
import "@/assets/css/form-styles.css";

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
