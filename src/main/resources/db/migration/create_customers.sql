create table CUSTOMERS(
    id int not null,
    address_id int not null,
    uuid varchar(100) not null,
    name varchar(150) not null,
    email varchar(150),
    birth_date date,
    cpf varchar(11) not null,
    created_at timestamp,
    updated_at timestamp
);