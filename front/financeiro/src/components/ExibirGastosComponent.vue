<template>
  <v-container>
    <!-- Seção do formulário -->
    <v-card>
      <div class="form-cadastro">
        <h2>Transações</h2>
        <div class="input-group">
          <input
            class="input"
            type="date"
            id="dtFiltro"
            v-model="dtFiltro"
            required
          />
          <label class="user-label">Lançamentos até o dia</label>
        </div>
        <button class="button-custom" @click="fetchGastos">Filtrar</button>
      </div>
    </v-card>

    <v-card style="padding-top: 30px">
      <div class="grid-gastos">
        <h2 style="padding-bottom: 10px">Lista de Lançamentos</h2>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left" style="width: 15%">
                  Descrição do Lançamento
                </th>
                <th class="text-left" style="width: 20%">Parcela</th>
                <th class="text-left" style="width: 25%">Cartão</th>
                <th class="text-left" style="width: 25%">Categoria</th>
                <th class="text-left" style="width: 10%">Valor</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="parcela in parcelasGastos" :key="parcela.cdParcela">
                <td>{{ parcela.deDescricao }}</td>
                <td>{{ parcela.nuParcela }} / {{ parcela.qtdeParcela }}</td>
                <td>{{ parcela.deCartao }}</td>
                <td>{{ parcela.deCategoria }}</td>
                <td>{{ formatarValorMonetario(parcela.vlrParcela) }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </div>
    </v-card>
  </v-container>
</template>

<script setup lang="ts">
import "@/assets/css/form-styles.css";
import axios from "axios";
import { ref, onMounted } from "vue";
// import { VDataTable } from "vuetify/labs/VDataTable";
import { ParcelasGastosDto } from "@/type/ParcelasGastosDto";

const dtFiltro = ref("");

// const headers = [
//   {
//     title: "Descrição da fatura",
//     align: "start",
//     sortable: false,
//     key: "deFatura",
//     width: "40%",
//   },
//   {
//     title: "Descrição Personalizada",
//     align: "start",
//     key: "deDescricao",
//     width: "30%",
//   },
//   { title: "Parcela", align: "end", key: "nuParcela", width: "10%" },
//   { title: "Parcelas", align: "start", key: "qtdeParcela", width: "10%" },
//   { title: "Valor", align: "start", key: "vlrParcela", width: "10%" },
// ];

const parcelasGastos = ref<ParcelasGastosDto[]>([]);

const fetchGastos = () => {
  axios
    .get(
      `http://localhost:8081/api/parcelas/parcelas-por-datas/${dtFiltro.value}`
    )
    .then((response) => {
      parcelasGastos.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar gastos:", error);
    });
};

onMounted(() => {
  const currentDate = new Date();
  currentDate.setMonth(currentDate.getMonth() + 1);
  const year = currentDate.getFullYear();
  const month = String(currentDate.getMonth() + 1).padStart(2, "0");
  const day = String(currentDate.getDate()).padStart(2, "0");

  // Crie a string da data no formato "YYYY-MM-DD"
  const formattedDate = `${year}-${month}-${day}`;

  // Atribua a data formatada ao v-model
  dtFiltro.value = formattedDate;

  fetchGastos();
});

const formatarValorMonetario = (valor: number) => {
  // Formatando o valor com duas casas decimais
  return valor.toLocaleString("pt-BR", {
    style: "currency",
    currency: "BRL",
  });
};
</script>

<style scoped>
.form-cadastro-gastos {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 10px;
}

label {
  display: block;
  font-weight: bold;
  color: #000;
}

input[type="text"],
input[type="password"],
input[type="checkbox"] {
  width: 100%;
  padding: 8px;
  margin-top: 3px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

button[type="submit"] {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 3px;
  padding: 10px 20px;
  cursor: pointer;
  font-weight: bold;
}

button[type="submit"]:hover {
  background-color: #0056b3;
}

.grid-cartoes {
  margin-top: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

th,
td {
  text-align: left;
  padding: 10px;
}

thead {
  background-color: #007bff;
  color: #fff;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

.v-btn {
  background-color: #007bff;
  color: #fff;
  padding: 5px 10px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.v-btn:hover {
  background-color: #0056b3;
}
</style>
