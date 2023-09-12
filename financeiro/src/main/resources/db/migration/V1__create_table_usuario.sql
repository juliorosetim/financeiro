create table usuario(
  cdUsuario decimal(9,0) not null primary key,
  deUsuario varchar(50),
  senha varchar(30),
  flAtivo char(1)
);