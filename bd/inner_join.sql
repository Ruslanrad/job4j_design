create table product(
	id serial primary key,
	maker varchar(255),
);

create table pc(
	id serial primary key,
	model varchar(255),
	price int,
	maker_id references product(id)
);

create table printer(
	id serial primary key,
	model varchar(255),
	price int,
	maker_id references product(id)
);

insert into product(maker) values('samsung');
insert into product(maker) values('hp');
insert into product(maker) values('lg');
insert into product(maker) values('acer');

insert into pc(model, price, maker_id) values ('H12', 10000, 1);
insert into pc(model, price, maker_id) values ('B23', 15000, 1);
insert into pc(model, price, maker_id) values ('K16', 50000, 3);
insert into pc(model, price, maker_id) values ('P551', 20000, 4);

insert into printer(model, price, maker_id) values ('Printer_G5', 5000, 2);
insert into printer(model, price, maker_id) values ('Printer_A155', 8000, 3);
insert into printer(model, price, maker_id) values ('Printer_B715', 5500, 4);

select maker_pc.maker, pc.model
from product as maker_pc join pc on maker_pc.id = pc.maker_id;

select maker_pc.maker as Производитель, pc.model as "Модель компьютера", pc.price as Цена
from product as maker_pc join pc on maker_pc.id = pc.maker_id;

select maker_printer.maker as Производитель, model_printer.model as "Модель принтера"
from product as maker_printer join printer as model_printer on maker_printer.id = model_printer.maker_id;