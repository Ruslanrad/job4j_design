create table users(
	id serial primary key,
	name varchar(255),
	birthday Date,
	has_children BOOLEAN DEFAULT(FALSE) NOT NULL
);

insert into users (name, birthday, has_children) values ('Ruslan', '2000-10-10', TRUE);

select * from users;

update users set name = 'Artur';

delete from users;