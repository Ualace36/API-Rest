create table ocorrencia(
id bigint not null auto_increment,
entregaId bigint not null,
descricao text not null,
dataRegistro datetime not null,

primary key (id)
);

alter table ocorrencia add constraint fk_ocorrencia_entrega
foreign key (entregaId) references entrega (id);