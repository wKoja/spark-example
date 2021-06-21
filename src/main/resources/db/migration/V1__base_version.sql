create table ADDRESSES(
    id int not null,
    state text not null,
    city text not null,
    neighborhood text not null,
    zipcode text not null,
    street text ,
    number text,
    additional_info text,
    main boolean
);

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
