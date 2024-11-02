create TABLE person(
        id int auto_increment primary key,
        name varchar(255) not null,
        cpf varchar(255) not null unique,
        email varchar(255)not null unique
);