export type Cartao = {
    cdCartao: number;
    deCartao: string;
    diaVirada: string;
    diaVencimento: string;
}

export type CartaoClear = {
    cdCartao: null;
    deCartao: "";
    diaVirada: null;
    diaVencimento: null;    
}