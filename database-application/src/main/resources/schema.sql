DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id    varchar(32)         not null,
    title varchar(50) not null,
    price double      not null,
    primary key (id)
);
