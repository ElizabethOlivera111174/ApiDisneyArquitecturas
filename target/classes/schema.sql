create sequence hibernate_sequence start with 1 increment by 1;
create table character (id bigint not null, name varchar(255), image varchar(255), age integer not null, weight float, history varchar(255), primary key (id));
create table character_movie (character_id bigint not null, movie_id bigint not null, primary key (character_id, movie_id));
create table genre (id bigint not null, name varchar(255), image varchar(255), primary key (id));
create table movie (id bigint not null, title varchar(255), image varchar(255), creation_date date, rating integer, primary key (id));
create table movie_genre (movie_id bigint not null, genre_id bigint not null, primary key (movie_id, genre_id));
alter table character_movie add constraint FKcww072p9tq9k4den5ci54a3eb foreign key (movie_id) references movie;
alter table character_movie add constraint FKeea614wcon5ebvxen5vtj2pr7 foreign key (character_id) references character;
alter table movie_genre add constraint FK86p3roa187k12avqfl28klp1q foreign key (genre_id) references genre;
alter table movie_genre add constraint FKp6vjabv2e2435at1hnuxg64yv foreign key (movie_id) references movie;