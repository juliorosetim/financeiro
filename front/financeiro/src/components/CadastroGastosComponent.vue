<template>
  <v-container>
    <card>
      <div class="form-cadastro">
        <div class="input-container">
          <input
            class="input"
            type="text"
            id="deFatura"
            v-model="deFatura"
            required
            placeholder="Descrição da fatura"
          />
        </div>
        <div class="input-container">
          <v-label for="deDescricao"></v-label>
          <input
            class="input"
            type="text"
            id="deDescricao"
            v-model="deDescricao"
            required
            placeholder="Descrição personalizada"
          />
        </div>

        <div class="input-container">
          <v-select
            outlined
            dense
            v-model="selectedGrupo"
            :items="grupos"
            item-value="cdGrupo"
            item-text="deGrupo"
            item-title="deGrupo"
            label="Grupo"
            density="comfortable"
            class="select"
          />
        </div>

        <div class="input-container">
          <v-select
            outlined
            dense
            v-model="selectedFormaPagto"
            :items="formasPagto"
            item-value="cdFormaPagto"
            item-text="deFormaPagto"
            item-title="deFormaPagto"
            label="Forma Pagamento"
            density="comfortable"
            class="select"
          />
        </div>

        <div class="input-container">
          <v-select
            outlined
            dense
            v-model="selectedCartao"
            :items="cartoes"
            item-value="cdCartao"
            item-text="deCartao"
            item-title="deCartao"
            label="Cartão"
            density="comfortable"
            class="select"
          />
        </div>

        <div class="input-container">
          <input
            class="input"
            type="number"
            id="qtdeParcela"
            v-model="qtdeParcela"
            placeholder="Qtde de parcelas"
            required
          />
        </div>

        <div class="input-container">
          <input
            class="input"
            type="number"
            id="vlrTotal"
            v-model="vlrTotal"
            required
            placeholder="Valor total"
          />
        </div>

        <div class="input-container">
          <v-label for="dtPrimeiraParcela"></v-label>
          <input
            class="input"
            type="date"
            id="dtPrimeiraParcela"
            v-model="dtPrimeiraParcela"
            required
            placeholder="Data Venc. primeira parcela"
          />
        </div>

        <button
          class="button-custom"
          @click="cadastrarGasto"
          style="margin-right: 10px"
        >
          Cadastrar
        </button>
        <button class="button-custom" @click="cancelar">Cancelar</button>
      </div>
    </card>
  </v-container>
</template>

<script setup lang="ts">
import axios from "axios";
import { ref, onMounted } from "vue";
import { Cartao } from "@/type/CartaoType";
import { Grupo } from "@/type/GrupoType";
import { FormaPagto } from "@/type/FormaPagtoType";
import "@/assets/css/form-styles.css";

const cartoes = ref<Cartao[]>([]);
const grupos = ref<Grupo[]>([]);
const formasPagto = ref<FormaPagto[]>([]);

const selectedCartao = ref<Cartao | null>(null);
const selectedGrupo = ref<Grupo | null>(null);
const selectedFormaPagto = ref<FormaPagto | null>(null);

const deFatura = ref("");
const deDescricao = ref("");
const qtdeParcela = ref("");
const vlrTotal = ref("");
const dtPrimeiraParcela = ref("");

const fetchCartoes = async () => {
  axios
    .get("http://localhost:8081/api/cartao")
    .then((response) => {
      cartoes.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar a lista de cartões:", error);
    });
};

const fetchGrupos = async () => {
  axios
    .get("http://localhost:8081/api/grupo")
    .then((response) => {
      grupos.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar a lista de grupos:", error);
    });
};

const fetchFormasPagto = async () => {
  axios
    .get("http://localhost:8081/api/formapagto")
    .then((response) => {
      formasPagto.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar a lista de Formas de pagamentos:", error);
    });
};

const cadastrarGasto = () => {
  axios
    .post("http://localhost:8081/api/gastos", {
      deFatura: deFatura.value,
      deDescricao: deDescricao.value,
      grupo: {
        cdGrupo: selectedGrupo.value,
      },
      formaPagto: {
        cdFormaPagto: selectedFormaPagto.value,
      },
      cartao: {
        cdCartao: selectedCartao.value,
      },
      qtdeParcela: qtdeParcela.value,
      vlrParcela: 0,
      vlrTotal: vlrTotal.value,
      dtPrimeiraParcela: dtPrimeiraParcela.value,
      usuario: {
        cdUsuario: 1,
      },
    })
    .then((response) => {
      console.log("Salvo com sucesso");
      deFatura.value = "";
      deDescricao.value = "";
      selectedGrupo.value = null;
      selectedFormaPagto.value = null;
      selectedCartao.value = null;
      qtdeParcela.value = "";
      vlrTotal.value = "";
      dtPrimeiraParcela.value = "";
    })
    .catch((error) => {
      console.log("Erro ao salvar o gasto");
    });
};

const cancelar = () => {
  return null;
};

onMounted(() => {
  fetchCartoes();
  fetchGrupos();
  fetchFormasPagto();

  qtdeParcela.value = "1";
});
</script>
