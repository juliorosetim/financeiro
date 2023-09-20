export type Gasto = {
  deFatura: string
  deDescricao: string
  grupo: {
    cdGrupo: number
  },
  formaPagto: {
    cdFormaPagto: number
  },
  cartao: {
    cdCartao: number
  },
  qtdeParcela: number
  nuParcela: number
  vlrParcela: number
  vlrTotal: number
  dtPrimeiraParcela: string;
  usuario: {
    cdUsuario: number
  }
}
