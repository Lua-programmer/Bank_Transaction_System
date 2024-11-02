create TABLE account(
        id int auto_increment primary key,
        balance decimal(10,2) not null,
        account_number varchar(255) not null,
        person_id int not null,
        FOREIGN KEY (person_id) REFERENCES person(id)
);