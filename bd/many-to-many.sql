create table author(
	id serial primary key,
	name varchar(255),
	surname varchar(255)
);

create table books(
	id serial primary key,
	name varchar(255)
);

create table author_books(
	id serial primary key,
	id_author int references author(id),
	id_books int references books(id)
);