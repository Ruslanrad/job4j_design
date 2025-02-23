create table users(
	id serial primary key,
	name varchar(255),
	surname varchar(255)
);

create table contacs(
	id serial primary key,
	phone int,
	telegrane varchar(255),
	users_id int references users(id) unique
);