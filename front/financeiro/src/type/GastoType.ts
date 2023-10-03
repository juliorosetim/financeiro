export type Gasto = {
  cdGasto: number;
  deFatura: string
  deDescricao: string
  grupo: {
    cdGrupo: number
    deGrupo: string
  },
  categoria: {
    cdCategoria: number
    deCategoria: string
  },
  formaPagto: {
    cdFormaPagto: number
    deFormaPagto: string
    tipo: string
  },
  cartao: {
    cdCartao: number
    deCartao: string
    diaVirada: number
    diaVencimento: number
  },
  qtdeParcela: number
  nuParcela: number
  vlrParcela: number
  vlrTotal: number
  dtLancamento: string;
  usuario: {
    cdUsuario: number
    deUsuario: string
  },
  pago: string;
  tpLancamento: string;
}
