<template>
  <v-container>
    <!-- Seção do formulário -->
    <v-card>
      <div class="form-cadastro-cartoes">
        <h2>Cadastro de Cartões</h2>
        <div class="form-group">
          <v-label for="nome">Cartão:</v-label>
          <input type="text" id="nome" v-model="deCartao" required />
        </div>
        <div class="form-group">
          <v-label for="diaVirada">Dia Virada:</v-label>
          <input type="text" id="diaVirada" v-model="diaVirada" required />
        </div>
        <div class="form-group">
          <v-label for="diaVencimento">Vencimento</v-label>
          <input
            type="text"
            id="diaVencimento"
            v-model="diaVencimento"
            required
          />
        </div>
        <v-btn @click="cadastrarCartao" style=" margin-right: 10px">Cadastrar</v-btn>
        <v-btn @click="cancelar">Cancelar</v-btn>
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
                <th class="text-left" style="width: 100px;" >Código</th>
                <th class="text-left" style="width: 700px;">Nome</th>
                <th class="text-left" style="width: 100px;">Dia virada</th>
                <th class="text-left" style="width: 100px;">Vencimento</th>
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
                <td><v-btn @click="exibirCartao(cartao)">Exibir</v-btn></td>
                <td>
                  <v-btn @click="excluirCartao(cartao.cdCartao)">
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

import axios from 'axios';
import { ref, onMounted } from 'vue';

const deCartao = ref('');
const diaVirada = ref('');
const diaVencimento = ref('');

const cartoes = ref([]);


const cadastrarCartao = () => {
  if(deCartao.value === "") {
    alert("Preencha o campo Cartão")
    return
  }

  if(diaVirada.value === "") {
    alert("Preencha o campo Dia virada do Cartão")
    return
  }
  
  if(diaVencimento.value === "") {
    alert("Preencha o campo Vencimento do Cartão")
    return
  }  

  axios.post('http://localhost:8081/api/cartao', {
    deCartao: deCartao.value,
    diaVirada: diaVirada.value,
    diaVencimento: diaVencimento.value,
  })
    .then((response) => {
      console.log('Cartão cadastrado com sucesso!', response.data);
      deCartao.value = '';
      diaVirada.value = '';
      diaVencimento.value = '';

      fetchCartoes();
    })
    .catch((error) => {
      console.error('Erro ao cadastrar cartão:', error);
    });   
};

const cancelar = () => {
  deCartao.value = '';
  diaVirada.value = '';
  diaVencimento.value = '';
}

const fetchCartoes = () => {
  axios.get('http://localhost:8081/api/cartao')
    .then((response) => {
      cartoes.value = response.data;
    })
    .catch((error) => {
      console.error('Erro ao buscar a lista de cart~es:', error);
    });
};

const excluirCartao = (cdCartao) => {
  axios.delete(`http://localhost:8081/api/cartao/${cdCartao}`)
    .then(() => {
      console.log('Cartão excluído com sucesso!');
      fetchCartoes();
    })
    .catch((error) => {
      console.error('Erro ao excluir cartão:', error);
    });
};

const exibirCartao = (cartao) => {
  deCartao.value = cartao.deCartao;
  diaVirada.value = cartao.diaVirada;
  diaVencimento.value = cartao.diaVencimento;
};

onMounted(() => {
  fetchCartoes();
});

</script>

<style scoped>
  .form-cadastro-cartoes {
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