create table incomes
(
    id     bigserial
        constraint incomes_pk
            primary key,
    amount bigint not null
);
