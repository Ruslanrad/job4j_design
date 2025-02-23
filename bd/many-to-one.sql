create table manufacture(
	id serial primary key,
	name varchar(255)
);

create table machines(
	id serial primary key,
	name varchar(255),
	id_maker int references manufacture(id)
);

insert into manufacture(name) values ('DMG_mori');
insert into machines(name, id_maker) values ('CTX800', 1);

select * from manufacture;
select * from machines where id in(select id_maker from manufacture);