insert into users(name, id_roles) values('Petr', 1);
insert into users(name, id_roles) values('Ruslan', 2);
insert into users(name, id_roles) values('Sergey', 2);
insert into users(name, id_roles) values('Vasya', 3);
insert into users(name, id_roles) values('Masha', 3);
insert into users(name, id_roles) values('Olya', 3);


insert into roles(name) values('admin');
insert into roles(name) values('manager');
insert into roles(name) values('user');

insert into rules(rule) values('edit');
insert into rules(rule) values('create');
insert into rules(rule) values('delete');
insert into rules(rule) values('complete');

insert into role_rules(id_roles, id_rules) values(1, 1);
insert into role_rules(id_roles, id_rules) values(1, 3);
insert into role_rules(id_roles, id_rules) values(2, 1);
insert into role_rules(id_roles, id_rules) values(2, 3);
insert into role_rules(id_roles, id_rules) values(2, 4);
insert into role_rules(id_roles, id_rules) values(3, 1);
insert into role_rules(id_roles, id_rules) values(3, 2);

insert into items(name, id_user, id_ categorie, id_state) values('Computer_#1', 4, 1, 1);
insert into items(name, id_user, id_ categorie, id_state) values('Laptop_#9', 5, 2, 3);
insert into items(name, id_user, id_ categorie, id_state) values('Samsung_x100', 6, 3, 2);

insert into attachs(name, id_item) values('passport', 1);
insert into attachs(name, id_item) values('manual', 2);
insert into attachs(name, id_item) values('receipt', 3);

insert into states(state) values('created');
insert into states(state) values('hold');
insert into states(state) values('completed');

insert into categories(name) values('Computers');
insert into categories(name) values('Laptop');
insert into categories(name) values('Smartphone');