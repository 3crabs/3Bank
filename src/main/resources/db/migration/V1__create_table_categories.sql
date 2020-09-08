create table categories
(
    id          bigserial
        constraint categories_pk
            primary key,
    name        varchar(32) not null,
    category_id bigint
        constraint categories_categories_id_fk
            references categories
            on delete cascade
);
