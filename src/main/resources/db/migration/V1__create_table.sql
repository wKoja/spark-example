create table CUSTOMERS(
    id int not null AUTO_INCREMENT PRIMARY KEY,
    uuid varchar(100) not null,
    name varchar(150) not null,
    email varchar(150),
    birth_date date,
    cpf varchar(11) not null,
    gender varchar(1),
    created_at timestamp,
    updated_at timestamp
);

create table ADDRESSES(
    address_id int not null AUTO_INCREMENT PRIMARY KEY,
    customer_id int not null,
    state text not null,
    city text not null,
    neighborhood text not null,
    zipcode text not null,
    street text ,
    number text,
    additional_info text,
    main boolean,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(id)
);

insert into CUSTOMERS (id, uuid,
                       name, email, birth_date,
                       cpf, gender, created_at, updated_at)
VALUES (null, UUID(),
        'William', 'email@teste.com',
        DATE('1996-03-22'), '12345678910', 'M',
        timestamp(now()), null);

insert into ADDRESSES (address_id, customer_id, state, city,
                       neighborhood, zipcode, street,
                      number, additional_info, main)
                      VALUES (null, 1, 'SC', 'Floripa',
                              'Campeche', '88031000', 'Rua',
                              '123', 'Comp.', 1);
insert into ADDRESSES (address_id, customer_id, state, city,
                       neighborhood, zipcode, street,
                       number, additional_info, main)
                       VALUES (null, 1, 'SC', 'Floripa',
                               'Trindade', '88036000', 'Rua',
                               '321', 'Comp. B', 0);

insert into ADDRESSES (address_id, customer_id, state, city,
                       neighborhood, zipcode, street,
                       number, additional_info, main)
                       VALUES (null, 1, 'SC', 'Floripa',
                               'Pantanal', '88036020', 'Rua',
                               '231', 'Comp. C', 0);
