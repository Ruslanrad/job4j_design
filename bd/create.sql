create table users(
	id serial primary key,
	name varchar(255),
	id_roles int references roles(id)
);

create table roles(
	id serial primary key,
	name varchar(255)
);

create table rules(
	id serial primary key,
	rule varchar(255)
);

create table roles_rules(
	id serial primary key,
	id_roles int references roles(id),
	id_rules int references rules(id)
);

create table items(
	id serial primary key,
	name varchar(255),
	id_user int references users(id),
	id_categorie references categories(id),
	id_state references states(id)
);

create table comments(
	id serial primary key,
	comment varchar(255),
	id_item int references items(id)
);

create table attachs(
	id serial primary key,
	name varchar(255),
	id_item int references items(id)
);

create table states(
	id serial primary key,
	state varchar(255)
);

create table categories(
	id serial primary key,
	name varchar(255)
);

