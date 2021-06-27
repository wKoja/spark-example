create table ADDRESSES(
    address_id int not null AUTO_INCREMENT PRIMARY KEY,
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
    id int not null AUTO_INCREMENT PRIMARY KEY,
    address_id int not null,
    uuid varchar(100) not null,
    name varchar(150) not null,
    email varchar(150),
    birth_date date,
    cpf varchar(11) not null,
    gender varchar(1),
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES ADDRESSES(address_id)
);

insert into ADDRESSES (address_id, state, city,
                      neighborhood, zipcode, street,
                      number, additional_info, main)
                      VALUES (1, 'SC', 'Floripa',
                              'Campeche', '88031000', 'Rua',
                              '123', 'Comp.', 1);

insert into CUSTOMERS (id, address_id, uuid,
                       name, email, birth_date,
                       cpf, gender, created_at, updated_at)
                       VALUES (1, 1, UUID(),
                               'William', 'email@teste.com',
                               DATE('1996-03-22'), '12345678910', 'M',
                               timestamp(now()), null);