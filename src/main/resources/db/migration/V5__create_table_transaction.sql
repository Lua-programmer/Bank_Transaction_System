create TABLE transaction(
        id int auto_increment primary key,
        type varchar(10) not null,
        amount decimal(10,2),
        origin varchar(255),
        destination varchar(255),
        account_number varchar(255)
);