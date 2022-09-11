create database "telegramUsers"
    with owner postgres;

\connect telegramUsers;
create schema users;
alter schema users owner to postgres;

create table users.users
(
    user_id      bigint not null
        primary key,
    city         varchar(255),
    created_date timestamp(3),
    page_size    integer,
    updated_date timestamp(3)
);

alter table users.users
    owner to postgres;