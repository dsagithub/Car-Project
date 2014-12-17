source projectofinal.sql
insert into users(username,userpass,email) values('yifei',MD5('yifei'),'yifeige@estudiant.upc.edu');
insert into users(username,userpass,email) values('ruben',MD5('ruben'),'rubendario@estudiant.upc.edu');
insert into users(username,userpass,email) values('nestor',MD5('nestor'),'nestorcuadrado@estudiant.upc.edu');
insert into users(username,userpass,email) values('david',MD5('david'),'davidmartinez@estudiant.upc.edu');

select sleep(1);insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values('yifei','4000','23665','calle portbou 136');
select sleep(1);insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values('nestor','3009','12005','Una calle cerca de mi casa');
select sleep(1);insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values('yifei','19447','868674','parking SB de Pl.Catalunya');
select sleep(1);insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values('ruben','63388','9965237','cerca de Port Vell');
select sleep(1);insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values('yifei','41.00000001','35.000079','cerca de Port Vell');

select sleep(1);insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values('yifei','54854','23665','calle francesc macia 136');
select sleep(1);insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values('nestor','3129','12235','Una calle cerca de la casa de mi abuela');
select sleep(1);insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values('yifei','123517','868674','parking SB de Pl.España');
select sleep(1);insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values('ruben','125474','0236587','cerca de Port cocoro');
select sleep(1);insert into posiciones (username,coordenadaX,coordenadaY,descripcion) values('ruben','41.00000001','35.000079','cerca de Port perro perrisimo');


select sleep(1); insert into opiniones(username,idposicion,content,precio) values ('yifei','1','Una zona muy amplia para aparcar los coches grandes','Gratis');
select sleep(1); insert into opiniones(username,idposicion,content,precio) values ('nestor','2','Un parking con poca plaza en el mediodia','2Euro/hora');
select sleep(1); insert into opiniones(username,idposicion,content,precio) values ('yifei','3','Muy cerca de Razzmatazz','Zona azul 1.5/3horas');
select sleep(1); insert into opiniones(username,idposicion,content,precio) values ('ruben','4','Demasiado trafico en el verano','Gratis');


select sleep(1);insert into favoritos(idposicion,username,descripcion) values ('1','yifei','mejor sitio por la zona del trabajo');
select sleep(1);insert into favoritos(idposicion,username,descripcion) values ('3','yifei','plaza amplia y cerca de casa');
select sleep(1);insert into favoritos(idposicion,username,descripcion) values ('4','ruben','bueno lugar para dejar el coche antes de salir la fiesta');



