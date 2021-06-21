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