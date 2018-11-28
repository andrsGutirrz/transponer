-- basado en https://www.solingest.com/almacenar-contrasenas-en-mysql

use transponer;


create table transponer.login(
    id INT AUTO_INCREMENT not null,
	username varchar(30) not null,
	clave varchar(40) not null,
	activo boolean not null, 
	CONSTRAINT PK_transponer_login PRIMARY KEY (id)
);

insert into transponer.login(username,clave,activo) values ('ricardo',SHA('ricardo'),1);

select * from transponer.login;