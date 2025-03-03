create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price int
);

insert into type(name) values('мясо');
insert into type(name) values('сыр');
insert into type(name) values('молочные продукты');

insert into product(name, type_id, expired_date, price) values('говядина', 1, '2025-04-01', 800);
insert into product(name, type_id, expired_date, price) values('свинина', 1, '2025-03-15', 400);
insert into product(name, type_id, expired_date, price) values('курица', 1, '2025-01-01', 300);
insert into product(name, type_id, expired_date, price) values('гауда', 2, '2025-05-15', 800);
insert into product(name, type_id, expired_date, price) values('пармезан', 2, '2025-04-19', 600);
insert into product(name, type_id, expired_date, price) values('фета', 2, '2025-03-01', 450);
insert into product(name, type_id, expired_date, price) values('моцарелла', 2, '2025-06-02', 300);
insert into product(name, type_id, expired_date, price) values('рикотта', 2, '2025-08-05', 400);
insert into product(name, type_id, expired_date, price) values('рокфор', 2, '2025-10-07',750);
insert into product(name, type_id, expired_date, price) values('йогурт', 3, '2025-04-01', 150);
insert into product(name, type_id, expired_date, price) values('молоко', 3, '2025-02-27', 80);
insert into product(name, type_id, expired_date, price) values('молоко - весёлый молочник', 3, '2025-02-25', 80);
insert into product(name, type_id, expired_date, price) values('молоко - село зеленое', 3, '2025-02-28', 80);
insert into product(name, type_id, expired_date, price) values('Лучшее молоко', 3, '2025-02-23', 80);
insert into product(name, type_id, expired_date, price) values('кефир  - весёлый молочник', 3, '2024-03-10', 90);
insert into product(name, type_id, expired_date, price) values('кефир - село зеленое', 3, '2026-03-10', 190);
insert into product(name, type_id, expired_date, price) values('кефир - молочная станция', 3, '2025-01-10', 95);
insert into product(name, type_id, expired_date, price) values('Лучший кефир', 3, '2025-03-10', 60);
insert into product(name, type_id, expired_date, price) values('кефир бавлинский', 3, '2025-03-5', 70);
insert into product(name, type_id, expired_date, price) values('кефир уфимский', 3, '2025-05-1', 50);

select product.name from product join type on product.type_id = type.id where type.name = 'сыр';

select name from product where name LIKE '%молоко%';

select name from product where expired_date < '2025-03-03';

select name from product where price in (select max(price) from product);

select type.name, count(product.name) as количество from product join type on product.type_id = type.id
group by type.name;

select type.name from product join type on product.type_id = type.id
group by type.name
having count(product.name) < 10;

select type.name as "Тип продукта", product.name as "Название продукта" from product join type on product.type_id = type.id
order by type.name desc;