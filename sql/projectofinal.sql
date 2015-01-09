drop database if exists cardb;
create database cardb;

use cardb;

create table users(
username varchar(50) not null primary key,
userpass char(32) not null,
email    varchar(255) not null
);

create table user_roles (
	username			varchar(20) not null,
	rolename 			varchar(20) not null,
	foreign key(username) references users(username) on delete cascade,
	primary key (username, rolename)
);

create table posiciones(
idposicion int not null auto_increment primary key,
username varchar(50) not null,
coordenadaX double not null,
coordenadaY double not null,
descripcion varchar(500),
fecha timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP,
foreign key(username) references users(username)
);

create table opiniones(
idopinion  int not null auto_increment primary key,
username varchar(50) not null,
idposicion int not null,
content    varchar(50000),
precio     varchar(80),
fecha      timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP,
foreign key(username) references users(username) on delete cascade,
foreign key(idposicion) references posiciones(idposicion) on delete cascade
);

create table favoritos(
idfavorito int not null auto_increment primary key,
idposicion int not null,
username varchar(50) not null,
descripcion varchar(500),
fecha timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP,
foreign key(idposicion) references posiciones(idposicion) on delete cascade,
foreign key(username) references users(username)
);



