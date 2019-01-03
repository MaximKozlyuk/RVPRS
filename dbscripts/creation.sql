create table authors
(
  id          serial not null
    constraint authors_pkey
      primary key,
  first_name  varchar(50),
  second_name varchar(50),
  birth_date  timestamp
);

alter table authors
  owner to max;

create table books
(
  id          serial not null
    constraint book_pkey
      primary key,
  name        varchar(50),
  description varchar(200),
  print_year  integer
);

alter table books
  owner to max;

create table genres
(
  id          serial not null
    constraint genres_pkey
      primary key,
  name        varchar(20),
  description varchar
);

alter table genres
  owner to max;

create table readers
(
  id          serial not null
    constraint readers_pkey
      primary key,
  first_name  varchar(50),
  second_name varchar(50),
  birth_date  timestamp
);

alter table readers
  owner to max;

create table books_authors
(
  id         serial not null
    constraint books_authors_pkey
      primary key,
  authors_id integer
    constraint books_authors_authors_id_fk
      references authors,
  books_id   integer
    constraint books_authors_books_id_fk
      references books
);

alter table books_authors
  owner to max;

create table books_genres
(
  id        serial not null
    constraint books_genres_pkey
      primary key,
  genres_id integer
    constraint books_genres_genres_id_fk
      references genres,
  books_id  integer
    constraint books_genres_books_id_fk
      references books
);

alter table books_genres
  owner to max;

create table books_readers
(
  id                  serial not null
    constraint books_readers_pkey
      primary key,
  books_dictionary_id integer
    constraint books_readers_books_id_fk
      references books,
  readers_id          integer
    constraint books_readers_readers_id_fk
      references readers,
  take_date           timestamp,
  return_date         timestamp
);

alter table books_readers
  owner to max;