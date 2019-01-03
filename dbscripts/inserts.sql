INSERT INTO public.genres (id, name, description) VALUES (1, 'DefaultGenre', 'Soo default');
INSERT INTO public.genres (id, name, description) VALUES (2, 'InterestingStuff', 'more interesting than default');

INSERT INTO public.books_readers (id, books_dictionary_id, readers_id, take_date, return_date) VALUES (1, 2, 1, '2019-01-01 12:14:57.712000', null);
INSERT INTO public.books_readers (id, books_dictionary_id, readers_id, take_date, return_date) VALUES (2, 3, 1, '2019-01-03 12:15:38.094000', '2019-01-07 12:15:52.595000');
INSERT INTO public.books_readers (id, books_dictionary_id, readers_id, take_date, return_date) VALUES (3, 4, 2, '2019-01-01 12:17:19.853000', '2019-01-03 12:18:25.995000');
INSERT INTO public.books_readers (id, books_dictionary_id, readers_id, take_date, return_date) VALUES (4, 5, 2, '2019-01-01 12:17:29.119000', null);
INSERT INTO public.books_readers (id, books_dictionary_id, readers_id, take_date, return_date) VALUES (5, 6, 2, '2019-01-03 12:17:36.874000', null);
INSERT INTO public.books_readers (id, books_dictionary_id, readers_id, take_date, return_date) VALUES (6, 7, 3, '2018-01-01 12:17:41.235000', null);
INSERT INTO public.books_readers (id, books_dictionary_id, readers_id, take_date, return_date) VALUES (7, 8, 3, '2018-01-03 12:17:52.160000', null);
INSERT INTO public.books_readers (id, books_dictionary_id, readers_id, take_date, return_date) VALUES (8, 9, 4, '2018-12-03 12:17:58.145000', '2019-01-03 12:18:09.579000');

INSERT INTO public.books_genres (id, genres_id, books_id) VALUES (2, 1, 2);
INSERT INTO public.books_genres (id, genres_id, books_id) VALUES (3, 2, 3);
INSERT INTO public.books_genres (id, genres_id, books_id) VALUES (4, 1, 4);
INSERT INTO public.books_genres (id, genres_id, books_id) VALUES (5, 2, 5);
INSERT INTO public.books_genres (id, genres_id, books_id) VALUES (6, 1, 6);
INSERT INTO public.books_genres (id, genres_id, books_id) VALUES (7, 2, 7);
INSERT INTO public.books_genres (id, genres_id, books_id) VALUES (8, 1, 8);
INSERT INTO public.books_genres (id, genres_id, books_id) VALUES (9, 2, 9);
INSERT INTO public.books_genres (id, genres_id, books_id) VALUES (10, 1, 10);

INSERT INTO public.readers (id, first_name, second_name, birth_date) VALUES (1, 'FirstReader', 'FirstReader', '2019-01-02 23:24:48.944000');
INSERT INTO public.readers (id, first_name, second_name, birth_date) VALUES (2, 'Second', 'Second', '2019-01-03 11:32:35.140000');
INSERT INTO public.readers (id, first_name, second_name, birth_date) VALUES (3, 'Third', 'Trtiy', '2019-01-03 11:32:54.486000');
INSERT INTO public.readers (id, first_name, second_name, birth_date) VALUES (4, 'Fourth', 'fourtH', '2019-01-03 11:33:35.309000');
INSERT INTO public.readers (id, first_name, second_name, birth_date) VALUES (5, 'Fith', 'Fith', '2019-01-03 11:33:54.361000');

INSERT INTO public.books (id, name, description, print_year) VALUES (2, 'FirstBook', 'FirstBook', 2001);
INSERT INTO public.books (id, name, description, print_year) VALUES (3, 'SecondBook', 'SecondBook', 2002);
INSERT INTO public.books (id, name, description, print_year) VALUES (4, 'ThirdBook', 'ThirdBook', 2003);
INSERT INTO public.books (id, name, description, print_year) VALUES (5, 'FOurthBook', 'FOurthBook', 2004);
INSERT INTO public.books (id, name, description, print_year) VALUES (6, 'FithBook', 'FithBook', 2005);
INSERT INTO public.books (id, name, description, print_year) VALUES (7, 'SixthBook', 'SixthBook', 2006);
INSERT INTO public.books (id, name, description, print_year) VALUES (8, 'SeventhBook', 'SeventhBook', 2007);
INSERT INTO public.books (id, name, description, print_year) VALUES (9, 'EighthBook', 'EighthBook', 2008);
INSERT INTO public.books (id, name, description, print_year) VALUES (10, 'NinthBook', 'NinthBook', 2009);

insert into public.authors (id, first_name, second_name, birth_date) values (3, 'FirstAuthor', 'FirstAuthor', '2019-01-03 11:35:06.312000');
insert into public.authors (id, first_name, second_name, birth_date) values (4, 'SecondAuthor', 'SecondAuthor', '2019-01-03 11:35:07.720000');
insert into public.authors (id, first_name, second_name, birth_date) values (5, 'ThirdAuthor', 'ThirdAuthor', '2019-01-03 11:35:08.518000');

INSERT INTO public.books_authors (id, authors_id, books_id) VALUES (1, 3, 2);
INSERT INTO public.books_authors (id, authors_id, books_id) VALUES (2, 3, 3);
INSERT INTO public.books_authors (id, authors_id, books_id) VALUES (3, 4, 4);
INSERT INTO public.books_authors (id, authors_id, books_id) VALUES (4, 4, 5);
INSERT INTO public.books_authors (id, authors_id, books_id) VALUES (5, 4, 6);
INSERT INTO public.books_authors (id, authors_id, books_id) VALUES (6, 5, 7);
INSERT INTO public.books_authors (id, authors_id, books_id) VALUES (7, 5, 8);
INSERT INTO public.books_authors (id, authors_id, books_id) VALUES (8, 5, 9);
INSERT INTO public.books_authors (id, authors_id, books_id) VALUES (9, 5, 10);
