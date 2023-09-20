<template>
  <v-container>
    <!-- Seção do formulário -->
    <v-card>
      <div class="form-cadastro-forma-pagto">
        <h2>Cadastro Forma Pagamento</h2>
        <div class="form-group">
          <v-label for="nome">Forma de pagamento:</v-label>
          <input
            type="text"
            id="deFormaPagto"
            v-model="deFormaPagto"
            required
          />
        </div>
        <v-btn @click="cadastrarFormaPagto" style="margin-right: 10px"
          >Cadastrar</v-btn
        >
        <v-btn @click="cancelar">Cancelar</v-btn>
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
                  <v-btn @click="exibirFormaPagto(formaPagto)">Exibir</v-btn>
                </td>
                <td>
                  <v-btn
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

<style scoped>
.form-cadastro-forma-pagto {
  max-width: 400px;
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
}

input[type="text"] {
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

.grid-forma-pagto {
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
