create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values('computer', 8000);
insert into devices(name, price) values('smartphone', 3000);
insert into devices(name, price) values('printer', 1000);
insert into devices(name, price) values('laptop', 1500);
insert into devices(name, price) values('notebook', 9000);
insert into devices(name, price) values('iphone', 12000);
insert into devices(name, price) values('ipad', 15000);

insert into people(name) values('Petya');
insert into people(name) values('Olya');
insert into people(name) values('Vasya');
insert into people(name) values('Alina');

insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(3, 2);
insert into devices_people(device_id, people_id) values(1, 3);
insert into devices_people(device_id, people_id) values(2, 3);
insert into devices_people(device_id, people_id) values(3, 3);
insert into devices_people(device_id, people_id) values(4, 3);
insert into devices_people(device_id, people_id) values(1, 4);
insert into devices_people(device_id, people_id) values(2, 4);

create view people_name_have_avg_price_more_5000
as
select p.name as people_name, avg(d.price) as avg_price
from devices_people as dp join people as p on p.id = dp.people_id
join devices as d on d.id = dp.device_id
group by p.name
having avg(d.price) > 5000;

select * from people_name_have_avg_price_more_5000;

alter view people_name_have_avg_price_more_5000 rename to people_name_have_avg_price_more_5000_rename;

drop view people_name_have_avg_price_more_5000_rename;