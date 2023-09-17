
CREATE TABLE parcelas (
  cdParcela numeric(9) not null,
  cdgasto numeric(9) NOT NULL,
  qtdeparcela int4 NULL,
  vlrparcela numeric(15, 2) NULL,
  vlrtotal numeric(15, 2) NULL,
  dtvencimento date NULL,
  conferido char(1) NULL,
  nuparcela integer NULL,
  CONSTRAINT pkParcela PRIMARY KEY (cdParcela),
  constraint fkGastos foreign key(cdGasto) references gastos(cdGasto)
);
