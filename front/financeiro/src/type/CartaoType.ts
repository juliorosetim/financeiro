export type Cartao = {
  cdCartao: number;
  deCartao: string;
  diaVirada: number;
  diaVencimento: number;
}

export type CartaoClear = {
  cdCartao: null;
  deCartao: "";
  diaVirada: null;
  diaVencimento: null;
}
