--1--
create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();

--2--

create
or replace function tax()
    returns trigger as
$$
    BEGIN
		NEW.price := NEW.price * 1.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    before insert
    on products
    for each row
    execute procedure tax();

--3--

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create
or replace function history()
	returns trigger as
$$
	BEGIN
		insert into history_of_price (name, price, date) values(NEW.name, NEW.price, current_timestamp);
		return new;
	END;
$$
language plpgsql;

create trigger insert_history
	after insert
	on products
	for each row
	execute function history();
