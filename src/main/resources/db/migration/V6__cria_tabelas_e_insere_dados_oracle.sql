-- Criação da tabela person
CREATE TABLE person (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR2(255 CHAR) NOT NULL,
    cpf VARCHAR2(255 CHAR) NOT NULL UNIQUE,
    email VARCHAR2(255 CHAR) NOT NULL UNIQUE
);

-- Criação da tabela account
CREATE TABLE account (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    balance NUMBER(10,2) NOT NULL,
    account_number VARCHAR2(255 CHAR) NOT NULL,
    person_id NUMBER NOT NULL,
    CONSTRAINT fk_account_person FOREIGN KEY (person_id) REFERENCES person(id)
);

-- Criação da tabela account_transaction
CREATE TABLE account_transaction (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    type VARCHAR2(10 CHAR) NOT NULL,
    amount NUMBER(10,2),
    origin VARCHAR2(255 CHAR),
    destination VARCHAR2(255 CHAR),
    account_number VARCHAR2(255 CHAR)
);

-- Inserts de dados na tabela person
INSERT INTO person (name, cpf, email) VALUES ('Klaus', '281.082.930-65', 'klaus@email.com');
INSERT INTO person (name, cpf, email) VALUES ('Stefan', '096.933.440-06', 'stefan@email.com');
INSERT INTO person (name, cpf, email) VALUES ('Damon', '602.723.070-33', 'damon@email.com');
INSERT INTO person (name, cpf, email) VALUES ('Rick', '267.206.700-77', 'rick@email.com');
INSERT INTO person (name, cpf, email) VALUES ('Caroline', '859.663.460-62', 'caroline@email.com');
INSERT INTO person (name, cpf, email) VALUES ('Helena', '881.572.060-06', 'helena@email.com');
INSERT INTO person (name, cpf, email) VALUES ('Matt', '298.553.390-29', 'matt@email.com');
INSERT INTO person (name, cpf, email) VALUES ('Bonnie', '684.201.540-59', 'bonnie@email.com');

-- Inserts de dados na tabela account
INSERT INTO account (balance, account_number, person_id) VALUES (0.00, '5773-3133-585332-1', 1);
INSERT INTO account (balance, account_number, person_id) VALUES (0.00, '5773-3133-585332-2', 2);
INSERT INTO account (balance, account_number, person_id) VALUES (0.00, '5773-3133-585332-3', 3);
INSERT INTO account (balance, account_number, person_id) VALUES (100.00, '5773-3133-585332-4', 4);
INSERT INTO account (balance, account_number, person_id) VALUES (0.00, '5773-3133-585332-5', 5);
INSERT INTO account (balance, account_number, person_id) VALUES (0.00, '5773-3133-585332-6', 6);
INSERT INTO account (balance, account_number, person_id) VALUES (0.00, '5773-3133-585332-7', 7);
INSERT INTO account (balance, account_number, person_id) VALUES (0.00, '5773-3133-585332-8', 8);