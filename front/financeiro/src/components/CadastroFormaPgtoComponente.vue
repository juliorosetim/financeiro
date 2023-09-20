<template>
  <v-container>
    <!-- Seção do formulário -->
    <v-card>
      <div class="form-cadastro">
        <h2>Cadastro Forma Pagamento</h2>
        <div class="input-container">
          <input
            type="text"
            id="deFormaPagto"
            v-model="deFormaPagto"
            required
            class="input"
            placeholder="Forma de pagamento"
          />
        </div>
        <button
          class="button-custom"
          @click="cadastrarFormaPagto"
          style="margin-right: 10px"
        >
          Cadastrar
        </button>
        <button class="button-custom" @click="cancelar">Cancelar</button>
      </div>
    </v-card>

    <v-card>
      <div class="grid-forma-pagto">
        <h2>Lista de formas de pagamentos</h2>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left" style="width: 100px">Código</th>
                <th class="text-left" style="width: 1000px">Forma pagto</th>
                <th class="text-left"></th>
                <th class="text-left"></th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="formaPagto in formasPagto"
                :key="formaPagto.cdFormaPagto"
              >
                <td>{{ formaPagto.cdFormaPagto }}</td>
                <td>{{ formaPagto.deFormaPagto }}</td>
                <td>
                  <v-btn
                    class="button-grid"
                    @click="exibirFormaPagto(formaPagto)"
                    ><v-icon>mdi-eye</v-icon></v-btn
                  >
                </td>
                <td>
                  <v-btn
                    class="button-grid"
                    @click="
                      formaPagto.cdFormaPagto !== undefined
                        ? excluirFormaPagto(formaPagto.cdFormaPagto)
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
import { FormaPagto } from "@/type/FormaPagtoType";
import "@/assets/css/form-styles.css";

const deFormaPagto = ref("");

const formasPagto = ref<FormaPagto[]>([]);

const cadastrarFormaPagto = () => {
  if (deFormaPagto.value === "") {
    alert("Preencha o campo forma de pagamento!");
    return;
  }

  axios
    .post("http://localhost:8081/api/formapagto", {
      deFormaPagto: deFormaPagto.value,
    })
    .then((response) => {
      console.log("Forma pagto cadastrado com sucesso!", response.data);
      deFormaPagto.value = "";

      fetchFormaPagto();
    })
    .catch((error) => {
      console.error("Erro ao cadastrar forma de pagamento:", error);
    });
};

const cancelar = () => {
  deFormaPagto.value = "";
};

const fetchFormaPagto = () => {
  axios
    .get("http://localhost:8081/api/formapagto")
    .then((response) => {
      formasPagto.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar a lista de formas de pagamento:", error);
    });
};

const excluirFormaPagto = (cdFormaPagto: number) => {
  axios
    .delete(`http://localhost:8081/api/formapagto/${cdFormaPagto}`)
    .then(() => {
      console.log("Forma de pagamento excluída com sucesso!");
      fetchFormaPagto();
    })
    .catch((error) => {
      console.error("Erro ao excluir forma de pagamento:", error);
    });
};

const exibirFormaPagto = (formaPagto: FormaPagto) => {
  deFormaPagto.value = formaPagto.deFormaPagto;
};

onMounted(() => {
  fetchFormaPagto();
});
</script>

