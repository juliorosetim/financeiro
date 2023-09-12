create table gastos(
  cdGasto decimal(9,0) not null primary key,
  deFatura varchar(100), -- descrição igual esta na fatura do cartão
  deDescricao varchar(100), -- descrição livre, para dizer o que é
  cdGrupo decimal(9,0),  -- chave do grupo
  cdFormaPagto decimal(9,0),-- chave da forma de pagamento
  cdCartao decimal(9,0), --  chave da tabela cartao
  qtdeParcela integer,
  vlrParcela decimal(15,2),
  vlrTotal decimal(15,2),
  dtVencimento date,
  conferido char(1), -- informa se o gasto lançado aqui já foi conferido com a fatura
  cdUsuario decimal(9,0) -- usuario que fez o lançamento do respectivo gasto
)