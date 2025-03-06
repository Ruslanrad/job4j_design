create table departments
(
    id serial primary key,
	name varchar(255)
);

create table employees
(
    id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) values('Контрукторы');
insert into departments(name) values('Технологи');
insert into departments(name) values('Менеджеры');

insert into employees(name, department_id) values('Petya', 1);
insert into employees(name, department_id) values('Vasya', null);
insert into employees(name, department_id) values('Olya', 2);
insert into employees(name, department_id) values('Misha', 2);
insert into employees(name, department_id) values('Albert', 1);
insert into employees(name, department_id) values('Ruslan', null);
insert into employees(name, department_id) values('Gulnaz', 1);

select * from departments left join employees on departments.id = employees.department_id;

select * from departments right join employees on departments.id = employees.department_id;

select * from departments full join employees on departments.id = employees.department_id;

select * from departments cross join employees;

select departments.name
from departments left join employees on departments.id = employees.department_id
group by departments.name
having count(employees.name) = 0;

select * from departments right join employees on departments.id = employees.department_id where department_id is not null;

select * from departments right join employees on departments.id = employees.department_id where department_id is not null;

create table teens(
	id serial primary key,
	name varchar(255),
	gender BOOLEAN DEFAULT(true) NOT NULL
);

insert into teens(name, gender) values('Petya', true);
insert into teens(name, gender) values('Vasya', true);
insert into teens(name, gender) values('Olya', false);
insert into teens(name, gender) values('Ruslan', true);
insert into teens(name, gender) values('Gulnaz', false);
insert into teens(name, gender) values('Oleg', true);
insert into teens(name, gender) values('Rustam', true);
insert into teens(name, gender) values('Viktoriya', false);
insert into teens(name, gender) values('Leysan', false);
insert into teens(name, gender) values('Aliya', false);

select t1.name, t2.name
from teens as t1 cross join teens as t2
where t1.gender != t2.gender and t1.id < t2.id;