-- basado en https://www.solingest.com/almacenar-contrasenas-en-mysql
-- create database transponer;

use transponer;

create table transponer.usuarios(
    id INT AUTO_INCREMENT not null,
	username varchar(30) not null UNIQUE,
	clave varchar(40) not null,
	activo boolean not null, 
	CONSTRAINT PK_transponer_usuarios PRIMARY KEY (id)
);

insert into transponer.usuarios(username,clave,activo) values ('ricardo',SHA1('ricardo'),1);

select * from transponer.usuarios;