use casestudy;
select * from feeders;
select * from user_roles;
select id as user_id, 'USER' from users;
insert into user_roles ( user_id, user_role )  ( select id as user_id , 'USER' from users );
insert into user_roles ( user_id, user_role ) values (4,'ADMIN');

SELECT * from feeder_snakes;
select s.species, f.name, f.size, fs.quantity, fs.feeding_date
from feeder_snakes fs, feeders f, snakes s 
where fs.feeder_id=f.id and fs.snake_id = s.id;

insert into users ( email, first_name, last_name, password ) values ( 'eheilig@perscholas.org', 'Eric', 'Heilig', 'password');
insert into users ( email, first_name, last_name, password ) values ( 'boom@gmail.com', 'Eriko', 'VanHelsing', 'password');
insert into users ( email, first_name, last_name, password ) values ( 'Bam@bambam.come', 'Eirik', 'Helsingski', 'password');
insert into users ( email, first_name, last_name, password ) values ( 'bang@gmail.com', 'Erik', 'Van', 'password');

update users set password = "$2a$12$nnrT/t19EUoAZx1KGYphyehcuAFVxhYLQ2uL4/.SOdkrZXTJnQpVi" where id > 0;


use capstone;
select * from users;
insert into users ( email, first_name, last_name, password ) values ( 'eheilig@perscholas.org', 'Eric', 'Heilig', 'password');
insert into users ( email, first_name, last_name, password ) values ( 'boom@gmail.com', 'Boy', 'VanHelsing', 'password');
insert into users ( email, first_name, last_name, password ) values ( 'Bam@bambam.come', 'Hem', 'Helsingski', 'password');
insert into users ( email, first_name, last_name, password ) values ( 'bang@gmail.com', 'Ops', 'Van', 'password');
