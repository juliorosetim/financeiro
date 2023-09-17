drop table gastos;

CREATE TABLE gastos (
  cdGasto numeric(9) NOT NULL,
  deFatura varchar(100) NULL,
  deDescricao varchar(100) NULL,
  cdGrupo numeric(9) NULL,
  cdFormapagto numeric(9) NULL,
  cdCartao numeric(9) NULL,
  qtdeParcela int4 NULL,
  vlrtotal numeric(15, 2) NULL,
  cdusuario numeric(9) NULL,
  dtPrimeiraParcela date,
  CONSTRAINT gastos_pkey PRIMARY KEY (cdgasto)
);