/*
-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');
INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');
INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');
INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);
INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');
*/

INSERT INTO users(id,name,lastname, username, email, password, enabled) VALUES (1,'Juan','Garcia','juangar2', 'juan@hotmail.com','a23aze',TRUE);
INSERT INTO users(id,name,lastname, username, email, password, enabled) VALUES (2,'Alberto','Perez','peras2', 'Alberto@hotmail.com','123',TRUE);
INSERT INTO users(id,name,lastname, username, email, password, enabled) VALUES (3,'Pablo','Espada','pabloespada', 'pablo@email.com','123',TRUE);
INSERT INTO users(id,name,lastname, username, email, password, enabled) VALUES (4,'Pablo','Espada','pabloespada2', 'pablo2@email.com','123',TRUE);
INSERT INTO users(id,name,lastname, username, email, password, enabled) VALUES (5,'Mario','Rodriguez','mario', 'mario@email.com','123',TRUE);
INSERT INTO users(id,name,lastname, username, email, password, enabled) VALUES (6,'Miguel Angel','Romalde','mromalde', 'mromalde@email.com','123',TRUE);
INSERT INTO users(id,name,lastname, username, email, password, enabled) VALUES (7,'Javier','Martínez','javier', 'javmarjae@email.com','123',TRUE);


INSERT INTO authorities(id,authority,username) VALUES (1,'admin','pabloespada');
INSERT INTO authorities(id,authority,username) VALUES (2,'admin','peras2');
INSERT INTO authorities(id,authority,username) VALUES (3,'admin','juangar2');
INSERT INTO authorities(id,authority,username) VALUES (4,'player','pabloespada2');
INSERT INTO authorities(id,authority,username) VALUES (5,'admin','mromalde');
INSERT INTO authorities(id,authority,username) VALUES (6,'admin','mario');
INSERT INTO authorities(id,authority,username) VALUES (7,'admin','javier');

INSERT INTO achievement(id,name,description) VALUES (1,'Sherif', 'Play two games');


INSERT INTO heroes(id,name,url,max_health,skill,color) VALUES (1,'Taheral','\resources\images\Taheral.png',2, 'Al realizar la maniobra de evasión, coge dos monedas por cada carta que descartes. Una vez por partida.','Morado');
INSERT INTO heroes(id,name,url,max_health,skill,color) VALUES (2,'Idril','\resources\images\Idril.png',3, 'En cualquier momento puedes mirar las tres cartas inferiores de la horda y devolverlas al mazo en el orden que prefieras. Dos veces por partida.','Verde');
INSERT INTO heroes(id,name,url,max_health,skill,color) VALUES (3,'Feldon','\resources\images\Feldon.png',2, 'Al resolver el ataque de la horda, tan solo pierde la mitad de cartas de las indicadas por el daño. Una vez por partida.','Rojo');
INSERT INTO heroes(id,name,url,max_health,skill,color) VALUES (4,'Lisavette','\resources\images\Lisavette.png',3, 'Mientras un heroe se enfrenta a la horda, utiliza un escudo para prevenirle del daño del enemigo. Roba hasta dos monedas de ese heroe. Dos veces por partida.','Azul');
INSERT INTO heroes(id,name,url,max_health,skill,color) VALUES (5,'Aranel','\resources\images\Aranel.png',2, 'En cualquier momento, busca una carta del mazo de Habilidad y sustituyela por una de tu mano. Acto seguido baraja el mazo. Una vez por partida.','Morado');
INSERT INTO heroes(id,name,url,max_health,skill,color) VALUES (6,'Beleth-Il','\resources\images\Beleth.png',3, 'Cuando utilices la carta Disparo Rapido, la primera carta fallida que robes podrás recuperarla y robar otra. Dos veces por partida.','Verde');
INSERT INTO heroes(id,name,url,max_health,skill,color) VALUES (7,'Neddia','\resources\images\Neddia.png',2, 'En cualquier momento, busca en el mazo de mercado hasta 2 cartas y sustituyelas por cartas que ya estivieron disponibles. Acto seguido baraja el mazo. Una vez por partida.','Rojo');
INSERT INTO heroes(id,name,url,max_health,skill,color) VALUES (8,'Valerys','\resources\images\Valerys.png',3, 'Al resolver el ataque de la horda sobre otro heroe, tu recibes el daño y ganas 1 ficha de gloria. Dos veces por partida.','Azul');


--GREEN SKILLS
INSERT INTO skills(id,name,url,description,color) VALUES (1,'Compañero Lobo', '\resources\images\GreenSkills\CompañeroLobo.png', 'Previene 2 puntos de daño', 'Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (2,'Disparo certero', '\resources\images\GreenSkills\DisparoCertero.png', 'Pierdes 1 carta - Finalizas el ataque','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (3,'Disparo certero 2', '\resources\images\GreenSkills\DisparoCertero.png', 'Pierdes 1 carta - Finalizas el ataque','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (4,'Disparo Rapido', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (5,'Disparo Rapido 2', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (6,'Disparo Rapido 3', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (7,'Disparo Rapido 4', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (8,'Disparo Rapido 5', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (9,'Disparo Rapido 6', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (10,'En la diana', '\resources\images\GreenSkills\EnLaDiana.png', 'Gana una ficha de Gloria - Pierdes 1 carta','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (11,'Lluvia de Flechas', '\resources\images\GreenSkills\LluviaDeFlechas.png', 'Dañas a 2 enemigos y al heroe con menos Heridas','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (12,'Lluvia de Flechas 2', '\resources\images\GreenSkills\LluviaDeFlechas.png', 'Dañas a 2 enemigos y al heroe con menos Heridas','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (13,'Recoger Flechas', '\resources\images\GreenSkills\RecogerFlechas.png', 'Recupera una carta de Disparo Rapido de tu pila de Desgastes y baraja el mazo de nuevo','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (14,'Recoger Flechas 2', '\resources\images\GreenSkills\RecogerFlechas.png', 'Recupera una carta de Disparo Rapido de tu pila de Desgastes y baraja el mazo de nuevo','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (15,'Supervivencia', '\resources\images\GreenSkills\Supervivencia.png', 'Cambia uno de los enemigos en el campo de batalla por la carta inferior','Verde');

--BLUE SKILLS
INSERT INTO skills(id,name,url,description,color) VALUES (16,'Ataque Brutal', '\resources\images\BlueSkills\AtaqueBrutal.png', 'Pierdes 1 carta', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (17,'Ataque Brutal 2', '\resources\images\BlueSkills\AtaqueBrutal.png', 'Pierdes 1 carta', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (18,'Carga con Escudo', '\resources\images\BlueSkills\CargaConEscudo.png', 'Previenes 2 de daño', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (19,'Doble Espadazo', '\resources\images\BlueSkills\DobleEspadazo.png', 'Pierdes 1 carta', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (20,'Doble Espadazo', '\resources\images\BlueSkills\DobleEspadazo.png', 'Pierdes 1 carta', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (21,'Escudo', '\resources\images\BlueSkills\Escudo.png', 'Previenes el daño de un unico enemigo - Finalizas el ataque', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (22,'Escudo 2', '\resources\images\BlueSkills\Escudo.png', 'Previenes el daño de un unico enemigo - Finalizas el ataque', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (23,'Espadazo', '\resources\images\BlueSkills\Espadazo.png', 'Roba 1 carta si es el primer Espadazo que juegas en este turno', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (24,'Espadazo 2', '\resources\images\BlueSkills\Espadazo.png', 'Roba 1 carta si es el primer Espadazo que juegas en este turno', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (25,'Espadazo 3', '\resources\images\BlueSkills\Espadazo.png', 'Roba 1 carta si es el primer Espadazo que juegas en este turno', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (26,'Espadazo 4', '\resources\images\BlueSkills\Espadazo.png', 'Roba 1 carta si es el primer Espadazo que juegas en este turno', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (27,'Paso Atras', '\resources\images\BlueSkills\PasoAtras.png', 'Roba 2 cartas', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (28,'Paso Atras 2', '\resources\images\BlueSkills\PasoAtras.png', 'Roba 2 cartas', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (29,'Todo o Nada', '\resources\images\BlueSkills\TodoONada.png', 'Roba 1 carta y agrega su daño al de esta carta, recupera la carta robada', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (30,'Voz de Aliento', '\resources\images\BlueSkills\VozDeAliento.png', 'Todos recuperan 2 cartas - Roba 1 carta y ganas 1 ficha de Gloria', 'Azul');

--PURPLE SKILLS
INSERT INTO skills(id,name,url,description,color) VALUES (31,'Aura Protectora', '\resources\images\PurpleSkills\AuraProtectora.png', 'Cancela todo el daño sufrido durante el proximo ataque de la Horda - Pierdes 1 carta por cada enemigo', 'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (32,'Bola de Fuego', '\resources\images\PurpleSkills\BolaDeFuego.png', 'Daña a todos los enemigos - El resto de heroes sufre 1 de Daño', 'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (33,'Disparo Gelido', '\resources\images\PurpleSkills\DisparoGelido.png','El enemigo afectado no causa daño este turno - Roba 1 carta' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (34,'Disparo Gelido 2', '\resources\images\PurpleSkills\DisparoGelido.png','El enemigo afectado no causa daño este turno - Roba 1 carta' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (35,'Flecha Corrosiva', '\resources\images\PurpleSkills\FlechaCorrosiva.png', 'Aumenta en 1 el daño de las siguientes cartas que causen daño a este enemigo - Pierdes 1 carta', 'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (36,'Golpe de baston', '\resources\images\PurpleSkills\GolpeDeBaston.png','Si ya utilizaste Golpe de Baston contra el mismo enemigo, el daño de esta carta es 2' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (37,'Golpe de baston 2', '\resources\images\PurpleSkills\GolpeDeBaston.png','Si ya utilizaste Golpe de Baston contra el mismo enemigo, el daño de esta carta es 2' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (38,'Golpe de baston 3', '\resources\images\PurpleSkills\GolpeDeBaston.png','Si ya utilizaste Golpe de Baston contra el mismo enemigo, el daño de esta carta es 2' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (39,'Golpe de baston 4', '\resources\images\PurpleSkills\GolpeDeBaston.png','Si ya utilizaste Golpe de Baston contra el mismo enemigo, el daño de esta carta es 2' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (40,'Orbe Curativo', '\resources\images\PurpleSkills\OrbeCurativo.png','Todos los Heroes recuperan 2 cartas - Eliminas 1 herida de tu Heroe' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (41,'Proyectil Igneo', '\resources\images\PurpleSkills\ProyectilIgneo.png','Ganas 1 ficha de Gloria' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (42,'Proyectil Igneo 2', '\resources\images\PurpleSkills\ProyectilIgneo.png','Ganas 1 ficha de Gloria' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (43,'Proyectil Igneo 3', '\resources\images\PurpleSkills\ProyectilIgneo.png','Ganas 1 ficha de Gloria' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (44,'Reconstitucion', '\resources\images\PurpleSkills\Reconstitucion.png','Roba 1 carta - Recuperas 2 cartas' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (45,'Torrente de Luz', '\resources\images\PurpleSkills\TorrenteDeLuz.png','El resto de Heroes recuperan 2 cartas - Ganas 1 ficha de Gloria' ,'Morado');

--RED SKILLS
INSERT INTO skills(id,name,url,description,color) VALUES (46,'Al Corazon', '\resources\images\RedSkills\AlCorazon.png','Si derrotas un enemigo con este ataque, ganas 1 Moneda si no has jugado otro Al Corazon este turno - Pierdes 1 carta' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (47,'Al Corazon 2', '\resources\images\RedSkills\AlCorazon.png','Si derrotas un enemigo con este ataque, ganas 1 Moneda si no has jugado otro Al Corazon este turno - Pierdes 1 carta' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (48,'Ataque Furtivo', '\resources\images\RedSkills\AtaqueFurtivo.png','Si derrotas un enemigo con este ataque, ganas 1 Moneda si no has jugado otro Ataque Furtivo este turno' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (49,'Ataque Furtivo 2', '\resources\images\RedSkills\AtaqueFurtivo.png','Si derrotas un enemigo con este ataque, ganas 1 Moneda si no has jugado otro Ataque Furtivo este turno' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (50,'Ataque Furtivo 3', '\resources\images\RedSkills\AtaqueFurtivo.png','Si derrotas un enemigo con este ataque, ganas 1 Moneda si no has jugado otro Ataque Furtivo este turno' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (51,'Ballesta Precisa', '\resources\images\RedSkills\BallestaPrecisa.png','Si ya utilizaste Ballesta Precisa contra el mismo enemigo, el daño de esta carta es 3' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (52,'Ballesta Precisa 2', '\resources\images\RedSkills\BallestaPrecisa.png','Si ya utilizaste Ballesta Precisa contra el mismo enemigo, el daño de esta carta es 3' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (53,'Ballesta Precisa 3', '\resources\images\RedSkills\BallestaPrecisa.png','Si ya utilizaste Ballesta Precisa contra el mismo enemigo, el daño de esta carta es 3' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (54,'En las Sombras', '\resources\images\RedSkills\EnLasSombras.png','Previenes 2 puntos de daño' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (55,'En las Sombras 2', '\resources\images\RedSkills\EnLasSombras.png','Previenes 2 puntos de daño' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (56,'Engañar', '\resources\images\RedSkills\Engañar.png','El enemigo elegido no causa daño este turno. Debes gastar 2 Monedas para hacer uso de esta carta' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (57,'Robar Bolsillos', '\resources\images\RedSkills\RobarBolsillos.png','Roba 1 Moneda a cada Heroe' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (58,'Saqueo', '\resources\images\RedSkills\Saqueo.png','Gana 2 Monedas por cada Enemigo vivo' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (59,'Saqueo 2', '\resources\images\RedSkills\Saqueo.png','Gana 2 Monedas por cada Enemigo vivo' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (60,'Trampa', '\resources\images\RedSkills\Trampa.png','Situa esta carta frente a ti. Al resolver el ataque de la Horda derrotas al enemigo mas fuerte. El botin de ese enemigo se pierde' ,'Rojo');



--ASIGNAR SKILLS A HEROES
INSERT INTO heroes_skills(fk_heroe, fk_skill) VALUES (1,1);
INSERT INTO heroes_skills(fk_heroe, fk_skill) VALUES (5,1);
INSERT INTO heroes_skills(fk_heroe, fk_skill) VALUES (4,2);
INSERT INTO heroes_skills(fk_heroe, fk_skill) VALUES (8,2);
INSERT INTO heroes_skills(fk_heroe, fk_skill) VALUES (3,3);
INSERT INTO heroes_skills(fk_heroe, fk_skill) VALUES (7,3);

INSERT INTO scenes(id,name,url,description) VALUES (1,'Campo de batalla', 'https:','Cada enemigo vencido aportara 1 moneda adicional al héroe que lo derrotó' );
INSERT INTO scenes(id,name,url,description) VALUES (2,'Lágrimas de Aradiel', 'https:','Una vez por turno, el héroe activo puede utilizar una carta de la mano de otro héroe (elegida al azar). Para hacerlo deberá entregarle 1 ficha de Gloria. Tras aplicar la carta, se descarta en la pila de Desgaste del propietario y este roba una nueva' );
INSERT INTO scenes(id,name,url,description) VALUES (3,'Lodazal de Kalern', 'https:','Al comienzo del turno de cada héroe, el jugador de su izquierda elige un enemigo. El enemigo elegido vuelve a la parte inferior del mazo (si tuviera heridas, se descartan');
INSERT INTO scenes(id,name,url,description) VALUES (4,'Mercado de Lotharion', 'https:','Mientras este sea el escenario activo, todos los articulos del mercado cuestan una Moneda menos');



INSERT INTO market(id,name,url,cost,description) VALUES (1,'Daga Elfica', '\resources\images\market\DagaEfica.png', 3,'Si el heroe tiene (mano), recupera esta carta despues de jugarla');
INSERT INTO market(id,name,url,cost,description) VALUES (2,'Alabarda Orca', '\resources\images\market\AlabardaOrca.png', 5,' ');
INSERT INTO market(id,name,url,cost,description) VALUES (3,'Armadura de Placas', '\resources\images\market\ArmaduradePlacas.png', 4,'Recupera 4 cartas');
INSERT INTO market(id,name,url,cost,description) VALUES (4,'Capa Elfica', '\resources\images\market\CapaElfica.png', 3,'El enemigo elegido no causa daño esta ronda');
INSERT INTO market(id,name,url,cost,description) VALUES (5,'Elixir de Concentracion', '\resources\images\market\ElixirdeConcentracion.png', 3,'Roba 3 cartas');
INSERT INTO market(id,name,url,cost,description) VALUES (6,'Piedra de Amolar', '\resources\images\market\PiedradeAmolar.png', 4,'+1 al daño de la carta');
INSERT INTO market(id,name,url,cost,description) VALUES (7,'Pocion Curativa', '\resources\images\market\PocionCurativa.png', 8,'Retira una ficha de herida');
INSERT INTO market(id,name,url,cost,description) VALUES (8,'Vialde de Conjuracion', '\resources\images\market\VialdeConjuracion.png', 5,'Busca una carta en tu pila');
INSERT INTO market(id,name,url,cost,description) VALUES (9,'Arco Compuesto', '\resources\images\market\ArcoCompuesto.png', 5,' ');


INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (1,'Simple Enemy', '\resources\images\enemies\simpleEnemy1.png', false, 2, 1, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (2,'Simple Enemy', '\resources\images\enemies\simpleEnemy1.png', false, 2, 1, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (3,'Simple Enemy', '\resources\images\enemies\simpleEnemy2.png', false, 3, 2, 2, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (4,'Simple Enemy', '\resources\images\enemies\simpleEnemy2.png', false, 3, 2, 2, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (5,'Simple Enemy', '\resources\images\enemies\simpleEnemy3.png', false, 3, 1, 0, 1);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (6,'Simple Enemy', '\resources\images\enemies\simpleEnemy3.png', false, 3, 1, 0, 1);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (7,'Simple Enemy', '\resources\images\enemies\simpleEnemy4.png', false, 4, 2, 1, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (8,'Simple Enemy', '\resources\images\enemies\simpleEnemy4.png', false, 4, 2, 1, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (9,'Simple Enemy', '\resources\images\enemies\simpleEnemy5.png', false, 3, 3, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (10,'Simple Enemy', '\resources\images\enemies\simpleEnemy5.png', false, 3, 3, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (11,'Simple Enemy', '\resources\images\enemies\simpleEnemy6.png', false, 6, 4, 3, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (12,'Simple Enemy', '\resources\images\enemies\simpleEnemy6.png', false, 6, 4, 3, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (13,'Boss 1', '\resources\images\enemies\boss1.png', true, 8, 4, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (14,'Boss 2', '\resources\images\enemies\boss2.png', true, 9, 5, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (15,'Boss 3', '\resources\images\enemies\boss3.png', true, 10, 6, 0, 0);


INSERT INTO games(id,creator,date,duration,is_in_progress,is_public,join_code,winner) VALUES (1,1,'2021-11-03',1000,FALSE,TRUE,'1e7be91755b7497080849ef0910c044d',null);
INSERT INTO games(id,creator,date,duration,is_in_progress,is_public,join_code,winner) VALUES (2,2,'2021-11-05',3600,FALSE,FALSE,'99ae49ed18bf458e89576a2d313a6e70',null);
INSERT INTO games(id,creator,date,duration,is_in_progress,is_public,join_code,winner) VALUES (3,3,'2021-11-05',null,FALSE,TRUE,'99ae43ed18bf458e89576b2d313a6e70',null);
INSERT INTO games(id,creator,date,duration,is_in_progress,is_public,join_code,winner, user_playing, game_state) VALUES (4,6,'2021-11-06',null,TRUE,TRUE,'99ae43ed18bf488e84576a2d313a6e71',null,3, 0);

INSERT INTO games_users(fk_game,fk_user) VALUES(1,1);
INSERT INTO games_users(fk_game,fk_user) VALUES(2,2);
INSERT INTO games_users(fk_game,fk_user,glory,gold,has_escape_token,winner,heroe_id) VALUES(4,3,0,0,TRUE,null,8);   --pabloespada
INSERT INTO games_users(fk_game,fk_user,glory,gold,has_escape_token,winner,heroe_id) VALUES(4,6,0,0,TRUE,null,3);   --mromalde


INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (3,2,1); --Habilidad 2 añadida al jugador 6 (pabloespada) en la partida 4 

/*
INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (4,3,1); --Habilidad 3 añadida al jugador 3 (mromalde) en la partida 4 
INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (4,3,1); --Habilidad 3 añadida al jugador 3 (mromalde) en la partida 4 
INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (4,3,1); --Habilidad 3 añadida al jugador 3 (mromalde) en la partida 4 
INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (4,3,2); --Habilidad 3 añadida al jugador 3 (mromalde) en la partida 4 
INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (4,2,1); --Habilidad 3 añadida al jugador 3 (mromalde) en la partida 4 
*/


