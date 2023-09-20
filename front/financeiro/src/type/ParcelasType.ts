export type Parcelas = {
  cdparcela: number;
  qtdeParcela: number;
  vlrParcela: number;
  vlrTotal: number;
  dtVencimento: string;
  conferido: string;
  nuParcela: number;
  gasto: {
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

}
