--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.3

-- Started on 2019-08-24 14:32:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

DROP DATABASE IF EXISTS public.bookstore;
CREATE DATABASE public.bookstore /*!40100 DEFAULT CHARACTER SET utf8 */;


CREATE TABLE public.book (
  id          bigint                NOT NULL,
  name        character varying(50) NOT NULL,
  author      character varying(50) NOT NULL,
  description character varying(250),
  cost        numeric(10, 2)        NOT NULL,
  count       integer               NOT NULL
);

CREATE TABLE public.shop (
  id   bigint                NOT NULL,
  name character varying(50) NOT NULL,
  cash numeric(10, 2)        NOT NULL
);

CREATE TABLE public.shop_book (
  shop_id bigint NOT NULL,
  book_id bigint NOT NULL
);

CREATE TABLE public."user" (
  id   bigint                NOT NULL,
  pass character varying(50) NOT NULL,
  name character varying(50) NOT NULL,
  cash numeric(10, 2)        NOT NULL
);

CREATE TABLE public.user_book (
  user_id bigint NOT NULL,
  book_id bigint NOT NULL
);


ALTER TABLE public.book
  ADD CONSTRAINT book_pkey PRIMARY KEY (id);


ALTER TABLE public.shop
  ADD CONSTRAINT shop_pkey PRIMARY KEY (id);


ALTER TABLE public."user"
  ADD CONSTRAINT user_pkey PRIMARY KEY (id);

CREATE INDEX fk_book_to_shop
  ON public.shop_book
  USING btree (book_id NULLS FIRST);

CREATE INDEX fk_book_to_user
  ON public.user_book
  USING btree (book_id NULLS FIRST);

CREATE INDEX fk_shop_to_book
  ON public.shop_book
  USING btree (shop_id NULLS FIRST);

CREATE INDEX fk_user_to_book
  ON public.user_book
  USING btree (user_id NULLS FIRST);


ALTER TABLE public.shop_book
  ADD CONSTRAINT fk_book_to_shop FOREIGN KEY (book_id) REFERENCES public.book (id);


ALTER TABLE public.user_book
  ADD CONSTRAINT fk_book_to_user FOREIGN KEY (book_id) REFERENCES public.book (id);


ALTER TABLE public.shop_book
  ADD CONSTRAINT fk_shop_to_book FOREIGN KEY (shop_id) REFERENCES public.shop (id);


ALTER TABLE public.user_book
  ADD CONSTRAINT fk_user_to_book FOREIGN KEY (user_id) REFERENCES public."user" (id);

-- --------------------------------------------------------

INSERT INTO public.book (id, name, author, description, cost, count)
VALUES (1, 'The Dark Tower: The Gunslinger', 'Stephen King',
        'The gunslinger comes across a hut in the desert owned by a farmer named Brown...', 24.00, 6);
INSERT INTO public.book (id, name, author, description, cost, count)
VALUES (2, 'Needful Things', 'Stephen King',
        'A new shop named "Needful Things" opens in the town of Castle Rock, sparking the curiosity of its citizens...',
        14.00, 7);
INSERT INTO public.book (id, name, author, description, cost, count)
VALUES
  (3, 'A Game of Thrones', 'George R. R. Martin', 'A Game of Thrones is the first novel in A Song of Ice and Fire...',
   19.50, 9);
INSERT INTO public.book (id, name, author, description, cost, count)
VALUES (4, 'Hyperion', 'Dan Simmons', 'The first book of the tetralogy "Songs of Hyperion....', 8.00, 11);
INSERT INTO public.book (id, name, author, description, cost, count)
VALUES (5, 'Pet Sematary', 'Stephen King',
        'Louis Creed, a doctor from Chicago, moves to a large house near the small town of Ludlow with his family...',
        7.00, 14);

INSERT INTO public.shop (id, name, cash) VALUES (1, 'OZ', 1020.00);
INSERT INTO public.shop (id, name, cash) VALUES (2, 'Biblio', 1572.00);

INSERT INTO public."user" (id, pass, name, cash) VALUES (1, 'pass123', 'Ivan', 54.00);
INSERT INTO public."user" (id, pass, name, cash) VALUES (2, 'pass456', 'Olga', 154.00);
INSERT INTO public."user" (id, pass, name, cash) VALUES (3, 'pass789', 'Alex', 126.00);
INSERT INTO public."user" (id, pass, name, cash) VALUES (4, 'rita123', 'Rita', 465.00);
INSERT INTO public."user" (id, pass, name, cash) VALUES (5, 'michael456', 'Michael', 213.00);

INSERT INTO public.shop_book (shop_id, book_id) VALUES (1, 1);
INSERT INTO public.shop_book (shop_id, book_id) VALUES (1, 2);
INSERT INTO public.shop_book (shop_id, book_id) VALUES (1, 3);
INSERT INTO public.shop_book (shop_id, book_id) VALUES (1, 4);
INSERT INTO public.shop_book (shop_id, book_id) VALUES (1, 5);
INSERT INTO public.shop_book (shop_id, book_id) VALUES (2, 2);
INSERT INTO public.shop_book (shop_id, book_id) VALUES (2, 3);
INSERT INTO public.shop_book (shop_id, book_id) VALUES (2, 5);

--
-- PostgreSQL database dump complete
--

