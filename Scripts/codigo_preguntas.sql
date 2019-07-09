/*
	JUNIO 2019
	TABLA PARA GUARDAR LOS CODIGOS DE LAS PREGUNTAS DE LAS EVALUACIONES DOCENTES
	VICERECTORIA DE DOCENCIA, UNA COSTA RICA
*/

use transponer;
#use transponer2;

CREATE TABLE CODIGO_PREGUNTAS(
	CODIGO VARCHAR(10),
	/* CODIGO_INSTRUMENTO VARCHAR(10), */
	DESCRIPCION VARCHAR(1000),
	CONSTRAINT PK_CODIGO_PREGUNTAS PRIMARY KEY (CODIGO)
	/*, FOREIGN KEY (CODIGO_INSTRUMENTO) REFERENCES INSTRUMENTOS(CODIGO) */
);


INSERT INTO CODIGO_PREGUNTAS VALUES
('AEV03','sin descripcion'),
('AEV04','sin descripcion'),
('AEV28','sin descripcion'),
('AEV06','sin descripcion'),
('AEV07','sin descripcion'),
('IGR01','sin descripcion'),
('IGR02','sin descripcion'),
('IG01','sin descripcion'),
('IG02','sin descripcion'),
('IG03','sin descripcion'),
('IG04','sin descripcion'),
('IG05','sin descripcion'),
('IG06','sin descripcion'),
('IG07','sin descripcion'),
('IG08','sin descripcion'),
('IG09','sin descripcion'),
('IG10','sin descripcion'),
('IG11','sin descripcion'),
('IG12','sin descripcion'),
('IG13','sin descripcion'),
('IG14','sin descripcion'),
('IG15','sin descripcion'),
('IG16','sin descripcion'),
('IG17','sin descripcion'),
('IG18','sin descripcion'),
('IG19','sin descripcion'),
('IG20','sin descripcion'),
('IG21','sin descripcion'),
('IGR07','sin descripcion'),
('INF01','sin descripcion'),
('INF23','sin descripcion'),
('IGR05','sin descripcion'),
('IGR06','sin descripcion'),
('GNI18','sin descripcion'),
('INF22','sin descripcion'),
('IED03','sin descripcion'),
('AEV01','sin descripcion'),
('AEV02','sin descripcion'),
('AEV05','sin descripcion'),
('EG01','sin descripcion'),
('EG02','sin descripcion'),
('EG03','sin descripcion'),
('EG04','sin descripcion'),
('EG05','sin descripcion'),
('EG06','sin descripcion'),
('EG07','sin descripcion'),
('EG08','sin descripcion'),
('EG09','sin descripcion'),
('EG10','sin descripcion'),
('EG11','sin descripcion'),
('EG12','sin descripcion'),
('EG13','sin descripcion'),
('EG14','sin descripcion'),
('EG15','sin descripcion'),
('EG16','sin descripcion'),
('EG17','sin descripcion'),
('EG18','sin descripcion'),
('EG19','sin descripcion'),
('EG20','sin descripcion'),
('EG21','sin descripcion'),
('EG22','sin descripcion'),
('AEV10','sin descripcion'),
('EVB01','sin descripcion'),
('EVB02','sin descripcion'),
('EVB03','sin descripcion'),
('EVB04','sin descripcion'),
('EVB05','sin descripcion'),
('EVB06','sin descripcion'),
('EVB07','sin descripcion'),
('EVB08','sin descripcion'),
('EVB09','sin descripcion'),
('EVB10','sin descripcion'),
('EVB11','sin descripcion'),
('EVB12','sin descripcion'),
('EVB13','sin descripcion'),
('EVB14','sin descripcion'),
('EVB15','sin descripcion'),
('EVB16','sin descripcion'),
('EVB17','sin descripcion'),
('EC201','sin descripcion'),
('EC202','sin descripcion'),
('EC203','sin descripcion'),
('EC204','sin descripcion'),
('EC205','sin descripcion'),
('EC206','sin descripcion'),
('EC207','sin descripcion'),
('EC208','sin descripcion'),
('EC209','sin descripcion'),
('EC210','sin descripcion'),
('EC211','sin descripcion'),
('EC212','sin descripcion'),
('EC213','sin descripcion'),
('EC214','sin descripcion'),
('EC215','sin descripcion'),
('EC216','sin descripcion'),
('EC217','sin descripcion'),
('EC218','sin descripcion'),
('EL01','sin descripcion'),
('EL02','sin descripcion'),
('EL03','sin descripcion'),
('EL04','sin descripcion'),
('EL05','sin descripcion'),
('EL06','sin descripcion'),
('EL07','sin descripcion'),
('EL08','sin descripcion'),
('EL09','sin descripcion'),
('EL10','sin descripcion'),
('EL11','sin descripcion'),
('EL12','sin descripcion'),
('EL13','sin descripcion'),
('EL14','sin descripcion'),
('EL15','sin descripcion'),
('EL16','sin descripcion'),
('EL17','sin descripcion'),
('EL18','sin descripcion'),
('EL19','sin descripcion'),
('EL20','sin descripcion'),
('EL21','sin descripcion'),
('EL22','sin descripcion'),
('IND-4','sin descripcion'),
('MVLC1','sin descripcion'),
('MVLC2','sin descripcion'),
('MVLC3','sin descripcion'),
('MVLC4','sin descripcion'),
('MVLC5','sin descripcion'),
('MVLC6','sin descripcion'),
('MVLC7','sin descripcion'),
('MVLC8','sin descripcion'),
('MVLC9','sin descripcion'),
('EC219','sin descripcion'),
('EC220','sin descripcion'),
('CCO03','sin descripcion'),
('CCO04','sin descripcion'),
('CCO05','sin descripcion'),
('CCO06','sin descripcion'),
('CCO07','sin descripcion'),
('CCO08','sin descripcion'),
('CCO09','sin descripcion'),
('CCO10','sin descripcion'),
('CCO11','sin descripcion'),
('CCO12','sin descripcion'),
('CCO13','sin descripcion'),
('CCO14','sin descripcion'),
('CCO15','sin descripcion'),
('CCO16','sin descripcion'),
('CCO17','sin descripcion'),
('CCO18','sin descripcion'),
('CCO19','sin descripcion'),
('CCO20','sin descripcion'),
('IND-0','sin descripcion'),
('IND-1','sin descripcion'),
('CCO01','sin descripcion'),
('CCO02','sin descripcion'),
('IND-2','sin descripcion'),
('PGR16','sin descripcion'),
('PGR17','sin descripcion'),
('PG181','sin descripcion'),
('PGR19','sin descripcion'),
('PGR20','sin descripcion'),
('PGR21','sin descripcion'),
('PGR22','sin descripcion'),
('EGL36','sin descripcion'),
('INF03','sin descripcion'),
('EGL33','sin descripcion'),
('GN_IN','sin descripcion'),
('INF02','sin descripcion'),
('IED02','sin descripcion'),
('AVE01','sin descripcion'),
('AVE02','sin descripcion'),
('AVE03','sin descripcion'),
('AVE04','sin descripcion'),
('AVE05','sin descripcion'),
('AVE06','sin descripcion'),
('AVE07','sin descripcion'),
('AVE08','sin descripcion'),
('PGR01','sin descripcion'),
('PGR02','sin descripcion'),
('PGR03','sin descripcion'),
('PGR04','sin descripcion'),
('PGR05','sin descripcion'),
('PGR06','sin descripcion'),
('PGR07','sin descripcion'),
('PGR08','sin descripcion'),
('PGR09','sin descripcion'),
('PGR10','sin descripcion'),
('PGR11','sin descripcion'),
('PGR12','sin descripcion'),
('PGR13','sin descripcion'),
('PGR14','sin descripcion'),
('PGR15','sin descripcion'),
('EGL34','sin descripcion'),
('EGL09', 'sin descripcion'),
('EGL10', 'sin descripcion'),
('EGL11', 'sin descripcion'),
('EGL12', 'sin descripcion'),
('EGL13', 'sin descripcion'),
('EGL14', 'sin descripcion'),
('EGL15', 'sin descripcion'),
('EGL16', 'sin descripcion'),
('EGL17', 'sin descripcion'),
('EGL18', 'sin descripcion'),
('EGL19', 'sin descripcion'),
('EGL20', 'sin descripcion'),
('EGL21', 'sin descripcion'),
('EGL22', 'sin descripcion'),
('EGL23', 'sin descripcion'),
('EGL24', 'sin descripcion'),
('EGL25', 'sin descripcion'),
('EGL26', 'sin descripcion'),
('EGL27', 'sin descripcion'),
('EGL28', 'sin descripcion'),
('EGL29', 'sin descripcion'),
('EGL30', 'sin descripcion'),
('EGL31', 'sin descripcion'),
('EGL32', 'sin descripcion'),
('EGL35', 'sin descripcion'),
('INF04', 'sin descripcion'),
('INF05', 'sin descripcion'),
('TCI35', 'sin descripcion'),
('TCI36', 'sin descripcion'),
('TCI37', 'sin descripcion'),
('TCI38', 'sin descripcion'),
('JUSTF', 'sin descripcion')
