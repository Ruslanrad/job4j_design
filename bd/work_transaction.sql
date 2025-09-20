create table staff
(
    id       serial primary key,
    name     varchar(50),
    position varchar(50),
    age integer,
    salary integer
);

insert into staff (name, position, age, salary)
values ('Petya', 'operator', 22, 50000);
insert into staff (name, position, age, salary)
values ('Ruslan', 'manager', 25, 30000);
insert into staff (name, position, age, salary)
values ('Nastya', 'enjineer', 21, 80000);

start transaction;

insert into staff (name, position, age, salary)
values ('Katya', 'cleaner', 18, 20000);
update staff set salary = 40000 where name = 'Ruslan';

savepoint first_savepoint;

delete from staff where age<22;
insert into staff (name, position, age, salary)
values ('Vladimir', 'Senior Manager', 35, 100000);

savepoint second_savepoint;

rollback to first_savepoint;

insert into staff (name, position, age, salary)
values ('Masha', 'cleaner', 19, 22000);

delete from staff where age<35;

release savepoint first_savepoint;



