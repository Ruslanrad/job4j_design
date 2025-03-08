create table car_bodies(
	id serial primary key,
	name varchar(255)
);

create table car_engines(
	id serial primary key,
	name varchar(255)
);

create table car_transmissions(
	id serial primary key,
	name varchar(255)
);

create table cars(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('Седан');
insert into car_bodies(name) values('Хэтчбек');
insert into car_bodies(name) values('Купе');
insert into car_bodies(name) values('Спорт');

insert into car_engines(name) values('V1');
insert into car_engines(name) values('V2');
insert into car_engines(name) values('V3');
insert into car_engines(name) values('A10');
insert into car_engines(name) values('A11');

insert into car_transmissions(name) values('Механика');
insert into car_transmissions(name) values('Автомат');
insert into car_transmissions(name) values('Робот');

insert into cars (name, body_id, engine_id, transmission_id) values ('Ауди А6', 1, 2, null);
insert into cars (name, body_id, engine_id, transmission_id) values ('Ауди А8', 2, 3, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('Ауди А7', 3, null, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('Ауди Q5', 2, 5, 3);
insert into cars (name, body_id, engine_id, transmission_id) values ('Ауди Q8', null, 4, 3);
insert into cars (name, body_id, engine_id, transmission_id) values ('Лада гранта', null, 2, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('Лада веста', 1, 4, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('Лада X-ray', 1, null, null);
insert into cars (name, body_id, engine_id, transmission_id) values ('Лада спорт', null, null, null);
insert into cars (name, body_id, engine_id, transmission_id) values ('bmw m5', 3, 2, null);
insert into cars (name, body_id, engine_id, transmission_id) values ('bmw m3', 2, 2, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('bmw m8', 1, 3, 3);
insert into cars (name, body_id, engine_id, transmission_id) values ('lifan x60', null, 2, null);
insert into cars (name, body_id, engine_id, transmission_id) values ('lifan x50', 2, 5, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('lifan x70', 2, null, 3);

select cars.id, cars.name as car_name, car_bodies.name as body_name, car_engines.name as engine_name, car_transmissions.name as transmission_name from cars
left join car_bodies on cars.body_id = car_bodies.id
left join car_engines on cars.engine_id = car_engines.id
left join car_transmissions on cars.transmission_id = car_transmissions.id;

select car_bodies.name from car_bodies
left join cars on cars.body_id = car_bodies.id
group by car_bodies.name
having count(cars.body_id) = 0;

select car_engines.name from car_engines
left join cars on cars.engine_id = car_engines.id
group by car_engines.name
having count(cars.engine_id) = 0;

select car_transmissions.name from car_transmissions
left join cars on cars.transmission_id = car_transmissions.id
group by car_transmissions.name
having count(cars.transmission_id) = 0