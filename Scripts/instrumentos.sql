/*
	JUNIO 2019
	TABLA PARA GUARDAR LOS INSTRUMENTOS DE LAS EVALUACIONES DOCENTES
	VICERECTORIA DE DOCENCIA, UNA COSTA RICA
*/

#use transponer;
use transponer2;

CREATE TABLE INSTRUMENTOS (
	CODIGO VARCHAR(10),
	DESCRIPCION VARCHAR(1000),
	CONSTRAINT PK_CODIGO_INSTRUMENTOS PRIMARY KEY (CODIGO)
); 


INSERT INTO INSTRUMENTOS VALUES 
('IGENER18','sin descripcion'),
('EDDECEG3','sin descripcion'),
('EVDBIM18','sin descripcion'),
('ECIDEA18','sin descripcion'),
('EDDLAB18','sin descripcion'),
('EDDMVLC5','sin descripcion'),
('EDDMVCC5','sin descripcion'),
('POSGR-03','sin descripcion'),
('EDD_3RCI','sin descripcion')