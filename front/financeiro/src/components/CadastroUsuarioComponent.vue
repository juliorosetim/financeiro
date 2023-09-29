<template>
  <v-container>
    <!-- Seção do formulário -->
    <v-card>
      <div class="form-cadastro">
        <h2>Cadastro de Usuário</h2>
        <div class="input-container">
          <input
            class="input"
            type="text"
            id="nome"
            v-model="nome"
            required
            placeholder="Nome"
          />
        </div>
        <div class="input-container">
          <input
            class="input"
            type="password"
            id="senha"
            v-model="senha"
            required
            placeholder="Senha"
          />
        </div>
        <div class="input-container">
          <input
            class="input"
            type="password"
            id="confirmacao-senha"
            v-model="confirmacaoSenha"
            required
            placeholder="Confirme a senha"
          />
        </div>
        <div class="input-container">
          <!-- <input class="input" type="checkbox" id="ativo" v-model="ativo" /> -->
          <div class="checkbox-apple">
            <input class="yep" id="check-apple" type="checkbox" />
            <label for="check-apple"></label>
          </div>
        </div>
        <button
          class="button-custom"
          @click="cadastrarUsuario"
          style="margin-right: 10px"
        >
          Cadastrar
        </button>
        <button class="button-custom" @click="cancelar">Cancelar</button>
      </div>
    </v-card>

    <v-card style="padding-top: 30px">
      <div class="grid-usuarios">
        <h2 style="padding-bottom: 10px">Lista de Usuários</h2>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left" style="width: 700px">Nome</th>
                <!-- <th class="text-left" style="width: 100px;">Senha</th> -->
                <th class="text-left" style="width: 100px"></th>
                <th class="text-left" style="width: 100px">Ativo</th>
                <th class="text-left"></th>
                <th class="text-left"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="usuario in usuarios" :key="usuario.cdUsuario">
                <td>{{ usuario.deUsuario }}</td>
                <td></td>
                <td>{{ usuario.flAtivo }}</td>
                <td>
                  <span class="button-grid" @click="exibirUsuario(usuario)"
                    ><v-icon>mdi mdi-text-box-edit-outline</v-icon></span
                  >
                </td>
                <td>
                  <span
                    class="button-grid"
                    @click="
                      usuario.cdUsuario !== undefined
                        ? excluirUsuario(usuario.cdUsuario)
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
import "@/assets/css/form-styles.css";
import axios from "axios";
import { ref, onMounted } from "vue";
import Usuario from "@/type/usuarioType";

const nome = ref(<string>"");
const senha = ref("");
const confirmacaoSenha = ref("");
const ativo = ref(false);

const usuarios = ref<Usuario[]>([]);

const cancelar = () => {
  nome.value = "";
  senha.value = "";
  confirmacaoSenha.value = "";
  ativo.value = false;
};

const cadastrarUsuario = () => {
  axios
    .post("http://localhost:8081/api/usuario", {
      deUsuario: nome.value,
      senha: senha.value,
      flAtivo: ativo.value ? "S" : "N",
    })
    .then((response) => {
      console.log("Usuário cadastrado com sucesso!", response.data);
      nome.value = "";
      senha.value = "";
      confirmacaoSenha.value = "";
      ativo.value = false;

      fetchUsuarios();
    })
    .catch((error) => {
      console.error("Erro ao cadastrar usuário:", error);
    });
};

const exibirUsuario = (usuario: Usuario) => {
  nome.value = usuario.deUsuario != undefined ? usuario.deUsuario : "";
  senha.value = usuario.senha != undefined ? usuario.senha : "";
  confirmacaoSenha.value = usuario.senha != undefined ? usuario.senha : "";
  ativo.value = usuario.flAtivo === "S";
};

const fetchUsuarios = () => {
  axios
    .get("http://localhost:8081/api/usuario")
    .then((response) => {
      usuarios.value = response.data;
    })
    .catch((error) => {
      console.error("Erro ao buscar a lista de usuários:", error);
    });
};

const excluirUsuario = (cdUsuario: number) => {
  axios
    .delete(`http://localhost:8081/api/usuario/${cdUsuario}`)
    .then(() => {
      console.log("Usuário excluído com sucesso!");
      fetchUsuarios();
    })
    .catch((error) => {
      console.error("Erro ao excluir usuário:", error);
    });
};

onMounted(() => {
  fetchUsuarios();
});
</script>


