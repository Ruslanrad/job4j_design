psql --username=postgres


create table staff
(
    id    serial primary key,
    name  varchar(50),
    age integer,   
	salary integer
);

insert into staff (name, age, salary)
values ('user_1', 21, 20020);
insert into staff (name, age, salary)
values ('user_2', 22, 35010);
insert into staff (name, age, salary)
values ('user_3', 33, 15030);


--!!!!!!!!!!!!!!!read uncommitted!!!!!!!!!!!!!!!!!!!!!!
--window1
--1)
start transaction;
set session transaction isolation level read uncommitted;

--2)
insert into staff (name, age, salary) VALUES ('user_4', 55, 123456);
delete from staff where age = 22;
update staff set salary = 10000 where name = 'user_3';
select * from staff;

--window2
--1)
start transaction;
set session transaction isolation level read uncommitted;

--2)
select * from staff;
--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


--!!!!!!!!!!!!!!!!!read committed!!!!!!!!!!!!!!!!!!!!!!!
--window1
--1)
begin transaction;
--2)
insert into staff (name, age, salary) VALUES ('user_4', 55, 123456);
delete from staff where age = 22;
update staff set salary = 5000 where name = 'user_3';
select * from staff;
--3)
commit;

--window2
--1)
begin transaction;
--2)
select * from staff;
--3)
select * from staff;
--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



--!!!!!!!!!!!!!!!!!!!repeatable read_блокировка и ошибка!!!!!!!!!!!!!!!!!!!!!
--window1
--1)
begin transaction isolation level repeatable read;
select * from staff;
--2)
insert into staff (name, age, salary) VALUES ('user_5', 65, 30000);
delete from staff where age = 21;
update staff set salary = 10000 where name = 'user_3';
--3)
commit;
rollback;

--window2
--1)
begin transaction isolation level repeatable read;
select * from staff;
--2)
update staff set salary = 15000 where name = 'user_3';
--3)
select * from staff;

--!!!!!!!!!!!!!!!!!!!repeatable read!!!!!!!!!!!!!!!!!!!!!
--window1
--1)
begin transaction isolation level repeatable read;
select * from staff;
--2)
insert into staff (name, age, salary) VALUES ('user_6', 75, 40000);
delete from staff where age = 65;
update staff set salary = 20000 where name = 'user_3';
--3)
rollback;
--4)
select * from staff;

--window2
--1)
begin transaction isolation level repeatable read;
select * from staff;
--2)
update staff set salary = 25000 where name = 'user_3';
--3)
select * from staff;
--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!





--!!!!!!!!!!!!!!!!!!!serializable!!!!!!!!!!!!!!!!!!!!!
--window1
--1)
begin transaction isolation level serializable;
select sum(salary) from staff;
update staff set salary = 10 where name = 'user_3';
--2
commit;

--window2
--1)
begin transaction isolation level serializable;
select sum(salary) from staff;
update staff set salary = 19000 where name = 'user_4';
--2
commit;
