<template>
  <v-container>
    <div class="form">
      <card>
        <div class="form-cadastro">
          <h2>Lançamento</h2>

          <div class="input-group">
            <input
              v-model="deDescricao"
              id="deDescricao"
              type="text"
              name="text"
              required
              autocomplete="off"
              class="input"
            />
            <label class="user-label">Descrição do Lançamento</label>
          </div>

          <div class="input-group">
            <v-select
              clearable
              outlined
              v-model="tpLancamento"
              :items="tiposLancamentos"
              item-value="tpLancamento"
              item-text="deGrupo"
              item-title="deGrupo"
              label="Tipo de Lançamento"
              density="compact"
              hide-details="true"
              variant="outlined"
            />
          </div>

          <div class="input-group">
            <v-select
              clearable
              outlined
              v-model="selectedGrupo"
              :items="grupos"
              item-value="cdGrupo"
              item-text="deGrupo"
              item-title="deGrupo"
              label="Grupo"
              density="compact"
              hide-details="true"
              return-object
              variant="outlined"
            />
          </div>

          <div class="input-group">
            <v-select
              clearable
              v-model="selectedCategoria"
              :items="categorias"
              item-value="cdCategoria"
              item-text="deCategoria"
              item-title="deCategoria"
              label="Categoria"
              density="compact"
              hide-details="true"
              return-object
              variant="outlined"
            />
          </div>

          <div class="input-group">
            <v-select
              clearable
              outlined
              dense
              v-model="selectedFormaPagto"
              :items="formasPagto"
              item-value="cdFormaPagto"
              item-text="deFormaPagto"
              item-title="deFormaPagto"
              label="Forma Pagamento"
              density="compact"
              hide-details="true"
              return-object
              variant="outlined"
            />
          </div>

          <div v-show="habilitaCartao()" class="input-group">
            <v-select
              clearable
              outlined
              dense
              v-model="selectedCartao"
              :items="cartoes"
              item-value="cdCartao"
              item-text="deCartao"
              item-title="deCartao"
              label="Cartão"
              density="compact"
              hide-details="true"
              return-object
              variant="outlined"
            />
          </div>

          <div class="input-group">
            <input
              class="input"
              type="number"
              id="vlrTotal"
              v-model="vlrTotal"
              required
              autocomplete="off"
            />
            <label class="user-label">Valor total</label>
          </div>

          <div v-show="!isReceita()" class="input-group">
            <input
              class="input"
              type="number"
              id="qtdeParcela"
              v-model="qtdeParcela"
              required
              autocomplete="off"
            />
            <label class="user-label">Quantidade de parcelas</label>
          </div>

          <div class="input-group">
            <v-label for="dtLancamento"></v-label>
            <input
              class="input"
              type="date"
              id="dtLancamento"
              v-model="dtLancamento"
              required
              autocomplete="off"
            />
            <label class="user-label">Data de lançamento</label>
          </div>

          <div class="input-group">
            <input
              class="input"
              type="text"
              id="deFatura"
              v-model="deFatura"
              required
            />
            <label class="user-label">Observação</label>
          </div>
          <div>
            <v-checkbox-btn
              label="Pago"
              true-value="S"
              false-value="N"
              v-model="pago"
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

      <v-card style="padding-top: 30px">
        <div class="grid-gastos">
          <h2 style="padding-bottom: 10px">Lista de Lançamentos</h2>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left" style="width: 35%">
                    Descrição do Lançamento
                  </th>
                  <th class="text-left" style="width: 10%">Grupo</th>
                  <th class="text-left" style="width: 10%">Categoria</th>
                  <th class="text-left" style="width: 10%">Valor</th>
                  <th class="text-left" style="width: 10%"></th>
                  <th class="text-left" style="width: 10%"></th>
                  <th class="text-left" style="width: 10%"></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="gasto in gastos" :key="gasto.cdGasto">
                  <td>{{ gasto.deDescricao }}</td>
                  <td>{{ gasto.grupo.deGrupo }}</td>
                  <td>{{ gasto.categoria.deCategoria }}</td>
                  <td>{{ formatarValorMonetario(gasto.vlrTotal) }}</td>
                  <td>
                    <span class="button-grid" @click="exibirParcelas(gasto)"
                      ><v-icon>mdi-eye</v-icon></span
                    >
                  </td>
                  <td>
                    <span class="button-grid" @click="exibirGasto(gasto)"
                      ><v-icon>mdi mdi-text-box-edit-outline</v-icon></span
                    >
                  </td>
                  <td>
                    <span
                      class="button-grid"
                      @click="
                        gasto.cdGasto !== undefined
                          ? excluirGasto(gasto.cdGasto)
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

      <ModalParcelas
        :parcelas="parcelasGasto"
        :showModal="showModal"
        @fecharModal="fecharModal"
      />
    </div>
  </v-container>
</template>

<script setup lang="ts">
import axios from "axios";
import { ref, onMounted } from "vue";
import { Cartao } from "@/type/CartaoType";
import { Grupo } from "@/type/GrupoType";
import { FormaPagto } from "@/type/FormaPagtoType";
import "@/assets/css/form-styles.css";
import { format } from "date-fns";
import { Categoria } from "@/type/CategoriaType";
import { Gasto } from "@/type/GastoType";
import { ParcelasGastosDto } from "@/type/ParcelasGastosDto";
import ModalParcelas from "./ModalParcelas.vue";

const cartoes = ref<Cartao[]>([]);
const grupos = ref<Grupo[]>([]);
const formasPagto = ref<FormaPagto[]>([]);
const categorias = ref<Categoria[]>([]);
const gastos = ref<Gasto[]>([]);

const selectedCartao = ref<Cartao | null>(null);
const selectedGrupo = ref<Grupo | null>(null);
const selectedFormaPagto = ref<FormaPagto | null>(null);
const selectedCategoria = ref<Categoria | null>(null);

const gastoSelected = ref<Gasto | null>(null);

const isEditing = ref(false);
const parcelasGasto = ref<ParcelasGastosDto[] | null>(null);

const cdGasto = ref<number | null>(null);
const deFatura = ref("");
const deDescricao = ref("");
const qtdeParcela = ref<number | null>(null);
const vlrTotal = ref<number | null>(null);
const pago = ref("");
const tpLancamento = ref("");

const showModal = ref(false);

const tiposLancamentos = ref(["Receita", "Despesa"]);

function habilitaCartao(): boolean {
  return selectedFormaPagto.value?.tipo == "Cartão";
}

function isReceita(): boolean {
  return tpLancamento.value == "Receita";
}

const obterDataAtualFormatada = () => {
  const dataAtual = new Date();
  return format(dataAtual, "yyyy-MM-dd");
};

const dtLancamento = ref(obterDataAtualFormatada());

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

const fetchCategoria = async () => {
  axios
    .get("http://localhost:8081/api/categoria")
    .then((response) => {
      categorias.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar a lista de categorias", error);
    });
};

const fetchGastos = async () => {
  axios
    .get("http://localhost:8081/api/gastos")
    .then((response) => {
      gastos.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar a lista de gastos", error);
    });
};

const fetchParcelas = async (cdGasto: number) => {
  axios
    .get(`http://localhost:8081/api/parcelas/parcelas-por-gasto/${cdGasto}`)
    .then((response) => {
      parcelasGasto.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar a lista de gastos", error);
    });
};

const cadastrarGasto = () => {
  if (!isEditing.value) {
    console.log("não editando");
    axios
      .post("http://localhost:8081/api/gastos", {
        deFatura: deFatura.value,
        deDescricao: deDescricao.value,
        grupo: selectedGrupo.value,
        categoria: selectedCategoria.value,
        formaPagto: selectedFormaPagto.value,
        cartao: selectedCartao.value,
        qtdeParcela: qtdeParcela.value,
        vlrParcela: 0,
        vlrTotal: vlrTotal.value,
        dtLancamento: dtLancamento.value,
        pago: pago.value,
        tpLancamento: tpLancamento.value,
        usuario: {
          cdUsuario: 1,
        },
      })
      .then((response) => {
        console.log("Salvo com sucesso");
        deFatura.value = "";
        deDescricao.value = "";
        selectedGrupo.value = null;
        selectedCategoria.value = null;
        selectedFormaPagto.value = null;
        selectedCartao.value = null;
        qtdeParcela.value = 1;
        vlrTotal.value = 0;
        dtLancamento.value = obterDataAtualFormatada();
        pago.value = "";
        tpLancamento.value = "";
        fetchGastos();
      })
      .catch((error) => {
        console.log("Erro ao salvar o gasto");
      });
  } else {
    console.log("editando");
    axios
      .put("http://localhost:8081/api/gastos", {
        cdGasto: cdGasto.value,
        deFatura: deFatura.value,
        deDescricao: deDescricao.value,
        grupo: selectedGrupo.value,
        categoria: selectedCategoria.value,
        formaPagto: selectedFormaPagto.value,
        cartao: selectedCartao.value,
        qtdeParcela: qtdeParcela.value,
        vlrParcela: 0,
        vlrTotal: vlrTotal.value,
        dtLancamento: dtLancamento.value,
        pago: pago.value,
        tpLancamento: tpLancamento.value,
        usuario: {
          cdUsuario: 1,
        },
      })
      .then((response) => {
        console.log("Salvo com sucesso");
        cdGasto.value = null;
        deFatura.value = "";
        deDescricao.value = "";
        selectedGrupo.value = null;
        selectedCategoria.value = null;
        selectedFormaPagto.value = null;
        selectedCartao.value = null;
        qtdeParcela.value = null;
        vlrTotal.value = null;
        pago.value = "";
        dtLancamento.value = obterDataAtualFormatada();
        tpLancamento.value = "";
        fetchGastos();
      })
      .catch((error) => {
        console.log("Erro ao salvar o gasto");
      });
  }

  isEditing.value = false;
};

function cancelar() {
  cdGasto.value = null;
  deFatura.value = "";
  deDescricao.value = "";
  selectedGrupo.value = null;
  selectedCategoria.value = null;
  selectedFormaPagto.value = null;
  selectedCartao.value = null;
  qtdeParcela.value = null;
  vlrTotal.value = null;
  dtLancamento.value = obterDataAtualFormatada();
  isEditing.value = false;
  pago.value = "";
  tpLancamento.value = "";
}

const excluirGasto = (cdGasto: number) => {
  axios
    .delete(`http://localhost:8081/api/gastos/${cdGasto}`)
    .then(() => {
      console.log("Gasto excluído com sucesso!");
      fetchGastos();
    })
    .catch((error) => {
      console.error("Erro ao excluir gasto:", error);
    });
};

const exibirGasto = (gasto: Gasto) => {
  cdGasto.value = gasto.cdGasto;
  deFatura.value = gasto.deFatura;
  deDescricao.value = gasto.deDescricao;
  selectedGrupo.value = { ...gasto.grupo };
  selectedCategoria.value = { ...gasto.categoria };
  selectedFormaPagto.value = { ...gasto.formaPagto };
  selectedCartao.value = { ...gasto.cartao };
  qtdeParcela.value = gasto.qtdeParcela;
  vlrTotal.value = gasto.vlrTotal;
  dtLancamento.value = gasto.dtLancamento;
  pago.value = gasto.pago;
  tpLancamento.value = gasto.tpLancamento;

  isEditing.value = true;
};

const dataFormatada = (dtVencimento: string) => {
  return dtVencimento ? format(new Date(dtVencimento), "dd/MM/yyyy") : "";
};

const formatarValorMonetario = (valor: number) => {
  return valor.toLocaleString("pt-BR", {
    style: "currency",
    currency: "BRL",
  });
};

onMounted(() => {
  fetchCartoes();
  fetchGrupos();
  fetchFormasPagto();
  fetchGastos();
  fetchCategoria();

  isEditing.value = false;
  dtLancamento.value = obterDataAtualFormatada();
});

const exibirParcelas = (gasto: Gasto) => {
  gastoSelected.value = gasto;

  fetchParcelas(gastoSelected.value.cdGasto);

  showModal.value = true;
};

const fecharModal = () => {
  showModal.value = false;
};
</script>
