<template>
  <v-container>
    <!-- Seção do formulário -->
    <v-card>
      <div class="form-cadastro-usuario">
        <h2>Cadastro de Usuário</h2>
        <div class="form-group">
          <v-label for="nome">Nome:</v-label>
          <input type="text" id="nome" v-model="nome" required />
        </div>
        <div class="form-group">
          <v-label for="senha">Senha:</v-label>
          <input type="password" id="senha" v-model="senha" required />
        </div>
        <div class="form-group">
          <v-label for="confirmacao-senha">Confirmação de Senha:</v-label>
          <input
            type="password"
            id="confirmacao-senha"
            v-model="confirmacaoSenha"
            required
          />
        </div>
        <div class="form-group">
          <v-label for="ativo">Ativo:</v-label>
          <input type="checkbox" id="ativo" v-model="ativo" />
        </div>
        <v-btn @click="cadastrarUsuario">Cadastrar</v-btn>
        <v-btn @click="cancelar">Cancelar</v-btn>
      </div>
    </v-card>

    <!-- Seção da grid de usuários -->
    <v-card>
      <div class="grid-usuarios">
        <h2>Lista de Usuários</h2>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">Código</th>
                <th class="text-left">Nome</th>
                <th class="text-left">Senha</th>
                <th class="text-left">Ativo</th>
                <th class="text-left">Ações</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="usuario in usuarios" :key="usuario.cdUsuario">
                <td>{{ usuario.cdUsuario }}</td>
                <td>{{ usuario.deUsuario }}</td>
                <td>{{ usuario.senha }}</td>
                <td>{{ usuario.flAtivo }}</td>
                <td>
                  <v-btn @click="exibirUsuario(usuario)">Exibir</v-btn>
                  <v-btn @click="excluirUsuario(usuario.cdUsuario)">
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

const nome = ref('');
const senha = ref('');
const confirmacaoSenha = ref('');
const ativo = ref(false);

const usuarios = ref([]);

const cancelar = () => {
  nome.value = '';
  senha.value = '';
  confirmacaoSenha.value = '';
  ativo.value = false;  
}

const cadastrarUsuario = () => {
  // Validar e enviar o cadastro do usuário (código que você já possui)

  // Após o cadastro, atualize a lista de usuários
  axios.post('http://localhost:8081/api/usuario', {
    deUsuario: nome.value,
    senha: senha.value,
    flAtivo: ativo.value ? 'S' : 'N',
  })
    .then((response) => {
      console.log('Usuário cadastrado com sucesso!', response.data);
      // Limpar os campos do formulário ou redirecionar para outra página, se necessário
      nome.value = '';
      senha.value = '';
      confirmacaoSenha.value = '';
      ativo.value = false;

      // Atualizar a lista de usuários após o cadastro
      fetchUsuarios();
    })
    .catch((error) => {
      console.error('Erro ao cadastrar usuário:', error);
    });    
};

const exibirUsuario = (usuario) => {
  // Preencher os campos do formulário com os detalhes do usuário clicado
  nome.value = usuario.deUsuario;
  senha.value = usuario.senha;
  confirmacaoSenha.value = usuario.senha;
  ativo.value = usuario.flAtivo === 'S';
};

const fetchUsuarios = () => {
  // Buscar a lista de usuários
  axios.get('http://localhost:8081/api/usuario')
    .then((response) => {
      usuarios.value = response.data;
    })
    .catch((error) => {
      console.error('Erro ao buscar a lista de usuários:', error);
    });
};

const excluirUsuario = (cdUsuario) => {
  // Enviar uma solicitação DELETE para o endpoint de exclusão com o cdUsuario
  axios.delete(`http://localhost:8081/api/usuario/${cdUsuario}`)
    .then(() => {
      console.log('Usuário excluído com sucesso!');
      // Após a exclusão, atualizar a lista de usuários
      fetchUsuarios();
    })
    .catch((error) => {
      console.error('Erro ao excluir usuário:', error);
    });
};

// Carregar a lista de usuários quando o componente é montado
onMounted(() => {
  fetchUsuarios();
});
</script>

<style scoped>
  .form-cadastro-usuario {
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

/* Estilos para a seção da grid de usuários */
.grid-usuarios {
  margin-top: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Estilos para a tabela */
th,
td {
  text-align: left;
  padding: 10px;
}

thead {
  background-color: #007bff;
  color: #fff;
}

/* Estilos para as linhas alternadas da tabela */
tr:nth-child(even) {
  background-color: #f2f2f2;
}

/* Estilos para o botão de visualização */
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