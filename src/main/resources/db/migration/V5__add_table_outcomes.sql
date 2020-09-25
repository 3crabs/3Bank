create table outcomes
(
    id          bigserial
        constraint outcomes_pk
            primary key,
    amount      bigint not null,
    created     date   not null,
    category_id bigint
);
