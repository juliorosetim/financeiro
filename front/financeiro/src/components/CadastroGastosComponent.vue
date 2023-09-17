<template>
  <v-container>
    <card>
      <div class="form-cadastro-gastor">
        <div>
          <v-label for="deFatura">Descrição da fatura</v-label>
          <input type="text" id="deFatura" v-model="deFatura" required />
        </div>
        <div>
          <v-label for="deDescricao">Descrição personalizada</v-label>
          <input type="text" id="deDescricao" v-model="deDescricao" required />
        </div>    
        
        <div class="form-group">
          <v-label for="Grupo">Grupo</v-label>
                <v-select
                  v-model="selectedGrupo"
                  :items="grupos"
                  item.value="cdGrupo"
                  item-text="deGrupo"
                  item-title="deGrupo"
                />      
        </div>

        <div class="form-group">
          <v-label for="FormaPgto">Forma Pagamento</v-label>
                <v-select
                  outlined
                  dense
                  v-model="selectedFormaPagto"
                  :items="formasPagto"
                  item.value="cdFormaPagto"
                  item-text="deFormaPagto"
                  item-title="deFormaPagto"
                />      
        </div>      

        <div class="form-group">
          <v-label for="Cartao">Cartão</v-label>
                <v-select
                  outlined
                  dense
                  v-model="selectedCartao"
                  :items="cartoes"
                  item.value="cdCartao"
                  item-text="deCartao"
                  item-title="deCartao"
                />
        </div>    

        <div>
          <v-label for="qtdeParcela">Qtde de parcelas</v-label>
          <input type="number" id="qtdeParcela" v-model="qtdeParcela" required />
        </div>      

        <div>
          <v-label for="vlrTotal">Valor total</v-label>
          <input type="number" id="vlrTotal" v-model="vlrTotal" required />
        </div>    

        <div>
          <v-label for="dtPrimeiroParcela">Data Venc. primeira parcela</v-label>
          <input type="date" id="dtPrimeiroParcela" v-model="dtPrimeiroParcela" required />
        </div>    

        <v-btn @click="cadastrarGasto" style=" margin-right: 10px">Cadastrar</v-btn>
        <v-btn @click="cancelar">Cancelar</v-btn>          
      </div>
    </card>
  </v-container>
</template>

<script setup lang="ts">
  import axios from 'axios';
  import { ref, onMounted} from 'vue';
  import { Cartao } from '@/type/CartaoType'
  import { Grupo } from '@/type/GrupoType';
  import { FormaPagto } from '@/type/FormaPagtoType';

  const cartoes = ref<Cartao[]>([]);
  const grupos = ref<Grupo[]>([]);
  const formasPagto = ref<FormaPagto[]>([]);

  const selectedCartao = ref<Cartao | null>(null);
  const selectedGrupo = ref<Grupo | null>(null);
  const selectedFormaPagto = ref<FormaPagto | null>(null);

  const deFatura = ref('')
  const deDescricao = ref('')
  const qtdeParcela = ref('')
  const vlrTotal = ref('')
  const dtPrimeiroParcela = ref('')
  

  const fetchCartoes = async() => {
    axios.get('http://localhost:8081/api/cartao')
      .then((response) => {
        cartoes.value = response.data;        
      })
      .catch((error) => {
        console.error('Erro ao buscar a lista de cartões:', error);
      });
    }  
    
    const fetchGrupos = async() => {
      axios.get('http://localhost:8081/api/grupo')
      .then((response) => {
        grupos.value = response.data;        
      })
      .catch((error) => {
        console.error('Erro ao buscar a lista de grupos:', error);
      });
    }  

    const fetchFormasPagto = async() => {
    axios.get('http://localhost:8081/api/formapagto')
      .then((response) => {
        formasPagto.value = response.data;        
      })
      .catch((error) => {
        console.error('Erro ao buscar a lista de Formas de pagamentos:', error);
      });
    }      

  const cadastrarGasto = () => {
    console.log('Cadastrar gasto selectedCartao ', selectedCartao.value)
    console.log('Cadastrar gasto selectedGrupo ', selectedGrupo.value)
  }
    
  const cancelar = () => {
    return null
  }

  onMounted(() => {
    fetchCartoes();
    fetchGrupos();
    fetchFormasPagto();
  });
</script>

<style scoped>
  .form-cadastro-gastor {
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
  
  input[type="text"],
  input[type="password"],
  input[type="number"],
  input[type="date"],
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

.grid-usuarios {
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

.password {
  border: none;
  cursor: not-allowed;
}
  </style>  