create table categoria(
  cdCategoria decimal(9,0) not null primary key,
  deCategoria varchar(50)
);

CREATE SEQUENCE categoria_seq
    INCREMENT BY 1
    START WITH 1;