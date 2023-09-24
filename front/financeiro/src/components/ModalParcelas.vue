<template>
  <v-dialog v-model="props.showModal" max-width="1200px">
    <v-card>
      <v-card-title>
        <span class="headline">Detalhes das Parcelas</span>
      </v-card-title>
      <v-card-text>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left" style="width: 40%">Descrição fatura</th>
                <th class="text-left" style="width: 35%">
                  Descrição Personalizada
                </th>
                <th class="text-left" style="width: 8%">Parcela</th>
                <th class="text-left" style="width: 25%">Cartão</th>
                <th class="text-left" style="width: 25%">Categoria</th>
                <th class="text-left" style="width: 25%">Vencimento</th>
                <th class="text-left" style="width: 10%">Valor</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="parcela in props.parcelas" :key="parcela.cdParcela">
                <td>{{ parcela.deFatura }}</td>
                <td>{{ parcela.deDescricao }}</td>
                <td>{{ parcela.nuParcela }} / {{ parcela.qtdeParcela }}</td>
                <td>{{ parcela.deCartao }}</td>
                <td>{{ parcela.deCategoria }}</td>
                <td>{{ dataFormatada(parcela.dtVencimento) }}</td>
                <td>{{ formatarValorMonetario(parcela.vlrParcela) }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" @click="fecharModal">Fechar</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
import { PropType } from "vue";
import { ParcelasGastosDto } from "@/type/ParcelasGastosDto";
import { format } from "date-fns";

const emits = defineEmits(["fecharModal"]);

const props = defineProps({
  parcelas: {
    required: true,
    type: Array as PropType<ParcelasGastosDto[]>,
  },
  showModal: {
    type: Boolean,
    default: false,
  },
});

const dataFormatada = (dtVencimento: string) => {
  return dtVencimento ? format(new Date(dtVencimento), "dd/MM/yyyy") : "";
};

const formatarValorMonetario = (valor: number) => {
  return valor.toLocaleString("pt-BR", {
    style: "currency",
    currency: "BRL",
  });
};

const fecharModal = () => {
  emits("fecharModal");
};
</script>
