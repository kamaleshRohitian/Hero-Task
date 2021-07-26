create table hero(id int primary key,hero varchar(30) );
create table users
(
id int not null auto_increment ,
username varchar(45) not null primary key,
password varchar(30) not null,
authority varchar(30) not null,
);

