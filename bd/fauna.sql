create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('fish', 30000, '1800-05-14');
insert into fauna(name, avg_age, discovery_date) values('bird', 15000, '1500-03-11');
insert into fauna(name, avg_age, discovery_date) values('insects', 300, '900-11-25');
insert into fauna(name, avg_age, discovery_date) values('wolаs', 19000, '1990-01-10');
insert into fauna(name, avg_age, discovery_date) values('elefants', 50000, '2000-09-28')
insert into fauna(name, avg_age, discovery_date) values('dogs', 10000, null);

select * from fauna where name='fish';

select * from fauna where avg_age between 10000 and 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';