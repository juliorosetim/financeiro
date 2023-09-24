<template>
  <v-container>
    <!-- Seção do formulário -->
    <v-card>
      <div class="form-cadastro">
        <h2>Cadastro de Cartões</h2>

        <p class="input-container">
          <input
            type="text"
            placeholder="Cartão"
            name="deCartao"
            id="deCartao"
            class="input"
            autocomplete="deCartao"
            v-model="deCartao"
          />
          <!-- <label class="input-label" for="text">Cartão</label> -->
        </p>

        <p class="input-container">
          <input
            type="text"
            placeholder="Dia virada"
            name="diaVirada"
            id="diaVirada"
            class="input"
            autocomplete="diaVirada"
            v-model="diaVirada"
          />
          <!-- <label class="input-label" for="text">Name</label> -->
        </p>

        <p class="input-container">
          <input
            type="text"
            placeholder="Dia vencimento"
            name="diaVencimento"
            id="diaVencimento"
            class="input"
            autocomplete="diaVencimento"
            v-model="diaVencimento"
          />
        </p>

        <button class="button-custom" @click="cadastrarCartao">
          Cadastrar
        </button>
        <button class="button-custom" @click="cancelar">Cancelar</button>
      </div>
    </v-card>

    <!-- Seção da grid de usuários -->
    <v-card>
      <div class="grid-cartoes">
        <h2>Lista de Cartões</h2>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left" style="width: 100px">Código</th>
                <th class="text-left" style="width: 700px">Nome</th>
                <th class="text-left" style="width: 100px">Dia virada</th>
                <th class="text-left" style="width: 100px">Vencimento</th>
                <th class="text-left"></th>
                <th class="text-left"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="cartao in cartoes" :key="cartao.cdCartao">
                <td>{{ cartao.cdCartao }}</td>
                <td>{{ cartao.deCartao }}</td>
                <td>{{ cartao.diaVirada }}</td>
                <td>{{ cartao.diaVencimento }}</td>
                <td>
                  <span class="button-grid" @click="exibirCartao(cartao)"
                    ><v-icon>mdi mdi-text-box-edit-outline</v-icon></span
                  >
                </td>
                <td>
                  <span
                    class="button-grid"
                    @click="
                      cartao.cdCartao !== undefined
                        ? excluirCartao(cartao.cdCartao)
                        : null
                    "
                  >
                    <v-icon>mdi-delete</v-icon>
                  </span>
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
import { Cartao } from "@/type/CartaoType";
import "@/assets/css/form-styles.css";

const deCartao = ref("");
const diaVirada = ref("");
const diaVencimento = ref("");

const cartoes = ref<Cartao[]>([]);

const cadastrarCartao = () => {
  if (deCartao.value === "") {
    alert("Preencha o campo Cartão");
    return;
  }

  if (diaVirada.value === "") {
    alert("Preencha o campo Dia virada do Cartão");
    return;
  }

  if (diaVencimento.value === "") {
    alert("Preencha o campo Vencimento do Cartão");
    return;
  }

  axios
    .post("http://localhost:8081/api/cartao", {
      deCartao: deCartao.value,
      diaVirada: diaVirada.value,
      diaVencimento: diaVencimento.value,
    })
    .then((response) => {
      console.log("Cartão cadastrado com sucesso!", response.data);
      deCartao.value = "";
      diaVirada.value = "";
      diaVencimento.value = "";

      fetchCartoes();
    })
    .catch((error) => {
      console.error("Erro ao cadastrar cartão:", error);
    });
};

const cancelar = () => {
  deCartao.value = "";
  diaVirada.value = "";
  diaVencimento.value = "";
};

const fetchCartoes = () => {
  axios
    .get("http://localhost:8081/api/cartao")
    .then((response) => {
      cartoes.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar a lista de cart~es:", error);
    });
};

const excluirCartao = (cdCartao: number) => {
  axios
    .delete(`http://localhost:8081/api/cartao/${cdCartao}`)
    .then(() => {
      console.log("Cartão excluído com sucesso!");
      fetchCartoes();
    })
    .catch((error) => {
      console.error("Erro ao excluir cartão:", error);
    });
};

const exibirCartao = (cartao: Cartao) => {
  deCartao.value = cartao.deCartao;
  diaVirada.value = cartao.diaVirada;
  diaVencimento.value = cartao.diaVencimento;
};

onMounted(() => {
  fetchCartoes();
});
</script>

