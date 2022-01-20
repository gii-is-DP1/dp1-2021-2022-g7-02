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
INSERT INTO users(id,name,lastname, username, email, password, enabled) VALUES (8,'Nabil','Fekir','elMago', 'nabilfekir8@email.com','123',FALSE);


INSERT INTO authorities(id,authority,username) VALUES (1,'admin','pabloespada');
INSERT INTO authorities(id,authority,username) VALUES (2,'admin','peras2');
INSERT INTO authorities(id,authority,username) VALUES (3,'admin','juangar2');
INSERT INTO authorities(id,authority,username) VALUES (4,'player','pabloespada2');
INSERT INTO authorities(id,authority,username) VALUES (5,'admin','mromalde');
INSERT INTO authorities(id,authority,username) VALUES (6,'admin','mario');
INSERT INTO authorities(id,authority,username) VALUES (7,'admin','javier');
--Play games
INSERT INTO achievement(id,name,description, number, type) VALUES (1,'Beginner', 'Play one game', 1, 0);
INSERT INTO achievement(id,name,description, number, type) VALUES (2,'Student', 'Play two games', 2, 0);
INSERT INTO achievement(id,name,description, number, type) VALUES (3,'Experienced', 'Play five games', 5, 0);
--Win games
INSERT INTO achievement(id,name,description, number, type) VALUES (4,'Cool', 'Win one game', 1, 1);
INSERT INTO achievement(id,name,description, number, type) VALUES (5,'Not Bad', 'Win two games', 2, 1);
INSERT INTO achievement(id,name,description, number, type) VALUES (6,'Pro', 'Win five games', 5, 1);
--Play with Heroe1
INSERT INTO achievement(id,name,description, number, type) VALUES (7,'Quack', 'Play one game with Taheral', 1, 2);
INSERT INTO achievement(id,name,description, number, type) VALUES (8,'Wizard', 'Play two games with Taheral', 2, 2);
INSERT INTO achievement(id,name,description, number, type) VALUES (9,'Witcher', 'Play Five games with Taheral', 5, 2);
--Play with Heroe2
INSERT INTO achievement(id,name,description, number, type) VALUES (10,'Archer', 'Play one game with Idril', 1, 3);
INSERT INTO achievement(id,name,description, number, type) VALUES (11,'Hunter', 'Play two games with Idril', 2, 3);
INSERT INTO achievement(id,name,description, number, type) VALUES (12,'Revenant', 'Play Five games with Idril', 5, 3);
--Play with Heroe3
INSERT INTO achievement(id,name,description, number, type) VALUES (13,'Thief', 'Play one game with Feldon', 1, 4);
INSERT INTO achievement(id,name,description, number, type) VALUES (14,'Ninja', 'Play two games with Feldon', 2, 4);
INSERT INTO achievement(id,name,description, number, type) VALUES (15,'Assasin', 'Play Five games with Feldon', 5, 4);
--Play with Heroe4
INSERT INTO achievement(id,name,description, number, type) VALUES (16,'Killer', 'Play one game with Lisavette', 1, 5);
INSERT INTO achievement(id,name,description, number, type) VALUES (17,'Warrior', 'Play two games with Lisavette', 2, 5);
INSERT INTO achievement(id,name,description, number, type) VALUES (18,'Boss', 'Play Five games with Lisavette', 5, 5);
--Play with Heroe5
INSERT INTO achievement(id,name,description, number, type) VALUES (19,'Quack', 'Play one game with Aranel', 1, 6);
INSERT INTO achievement(id,name,description, number, type) VALUES (20,'Wizard', 'Play two games with Aranel', 2, 6);
INSERT INTO achievement(id,name,description, number, type) VALUES (21,'Witcher', 'Play Five games with Aranel', 5, 6);
--Play with Heroe6
INSERT INTO achievement(id,name,description, number, type) VALUES (22,'Archer', 'Play one game with Beleth-Il', 1, 7);
INSERT INTO achievement(id,name,description, number, type) VALUES (23,'Hunter', 'Play two games with Beleth-Il', 2, 7);
INSERT INTO achievement(id,name,description, number, type) VALUES (24,'Revenant', 'Play Five games with Beleth-Il', 5, 7);
--Play with Heroe7
INSERT INTO achievement(id,name,description, number, type) VALUES (25,'Thief', 'Play one game with Neddia', 1, 8);
INSERT INTO achievement(id,name,description, number, type) VALUES (26,'Ninja', 'Play two games with Neddia', 2, 8);
INSERT INTO achievement(id,name,description, number, type) VALUES (27,'Assasin', 'Play Five games with Neddia', 5, 8);
--Play with Heroe8
INSERT INTO achievement(id,name,description, number, type) VALUES (28,'Killer', 'Play one game with Valerys', 1, 9);
INSERT INTO achievement(id,name,description, number, type) VALUES (29,'Warrior', 'Play two games with Valerys', 2, 9);
INSERT INTO achievement(id,name,description, number, type) VALUES (30,'Boss', 'Play Five games with Valerys', 5, 9);
--Gold
INSERT INTO achievement(id,name,description, number, type) VALUES (31,'Goblin', 'Obtain 10 gold coins', 10, 10);
INSERT INTO achievement(id,name,description, number, type) VALUES (32,'Leprechaun', 'Obtain 20 gold coins', 20, 10);
INSERT INTO achievement(id,name,description, number, type) VALUES (33,'Gollum', 'Obtain 50 gold coins', 50, 10);
--Glory
INSERT INTO achievement(id,name,description, number, type) VALUES (34,'Magno', 'Obtain 10 glory points', 10, 11);
INSERT INTO achievement(id,name,description, number, type) VALUES (35,'Atila', 'Obtain 20 glory points', 20, 11);
INSERT INTO achievement(id,name,description, number, type) VALUES (36,'Cesar', 'Obtain 50 glory points', 50, 11);

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
INSERT INTO skills(id,name,url,description,color) VALUES (3,'Disparo certero', '\resources\images\GreenSkills\DisparoCertero.png', 'Pierdes 1 carta - Finalizas el ataque','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (4,'Disparo Rapido', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (5,'Disparo Rapido', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (6,'Disparo Rapido', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (7,'Disparo Rapido', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (8,'Disparo Rapido', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (9,'Disparo Rapido', '\resources\images\GreenSkills\DisparoRapido.png', 'Roba una carta, si es Disparo Rapido usala. Sino, ponla en el fondo de tu mazo de habilidades','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (10,'En la diana', '\resources\images\GreenSkills\EnLaDiana.png', 'Gana una ficha de Gloria - Pierdes 1 carta','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (11,'Lluvia de Flechas', '\resources\images\GreenSkills\LluviaDeFlechas.png', 'Dañas a 2 enemigos y al heroe con menos Heridas','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (12,'Lluvia de Flechas', '\resources\images\GreenSkills\LluviaDeFlechas.png', 'Dañas a 2 enemigos y al heroe con menos Heridas','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (13,'Recoger Flechas', '\resources\images\GreenSkills\RecogerFlechas.png', 'Recupera una carta de Disparo Rapido de tu pila de Desgastes y baraja el mazo de nuevo','Verde');
INSERT INTO skills(id,name,url,description,color) VALUES (14,'Recoger Flechas', '\resources\images\GreenSkills\RecogerFlechas.png', 'Recupera una carta de Disparo Rapido de tu pila de Desgastes y baraja el mazo de nuevo','Verde');

--BLUE SKILLS
INSERT INTO skills(id,name,url,description,color) VALUES (15,'Ataque Brutal', '\resources\images\BlueSkills\AtaqueBrutal.png', 'Pierdes 1 carta', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (16,'Ataque Brutal', '\resources\images\BlueSkills\AtaqueBrutal.png', 'Pierdes 1 carta', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (17,'Carga con Escudo', '\resources\images\BlueSkills\CargaConEscudo.png', 'Previenes 2 de daño', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (18,'Doble Espadazo', '\resources\images\BlueSkills\DobleEspadazo.png', 'Pierdes 1 carta', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (19,'Doble Espadazo', '\resources\images\BlueSkills\DobleEspadazo.png', 'Pierdes 1 carta', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (20,'Escudo', '\resources\images\BlueSkills\Escudo.png', 'Previenes el daño de un unico enemigo - Finalizas el ataque', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (21,'Escudo', '\resources\images\BlueSkills\Escudo.png', 'Previenes el daño de un unico enemigo - Finalizas el ataque', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (22,'Espadazo', '\resources\images\BlueSkills\Espadazo.png', 'Roba 1 carta si es el primer Espadazo que juegas en este turno', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (23,'Espadazo', '\resources\images\BlueSkills\Espadazo.png', 'Roba 1 carta si es el primer Espadazo que juegas en este turno', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (24,'Espadazo', '\resources\images\BlueSkills\Espadazo.png', 'Roba 1 carta si es el primer Espadazo que juegas en este turno', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (25,'Espadazo', '\resources\images\BlueSkills\Espadazo.png', 'Roba 1 carta si es el primer Espadazo que juegas en este turno', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (26,'Paso Atras', '\resources\images\BlueSkills\PasoAtras.png', 'Roba 2 cartas', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (27,'Paso Atras', '\resources\images\BlueSkills\PasoAtras.png', 'Roba 2 cartas', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (28,'Todo o Nada', '\resources\images\BlueSkills\TodoONada.png', 'Roba 1 carta y agrega su daño al de esta carta, recupera la carta robada', 'Azul');
INSERT INTO skills(id,name,url,description,color) VALUES (29,'Voz de Aliento', '\resources\images\BlueSkills\VozDeAliento.png', 'Todos recuperan 2 cartas - Roba 1 carta y ganas 1 ficha de Gloria', 'Azul');

--PURPLE SKILLS
INSERT INTO skills(id,name,url,description,color) VALUES (30,'Aura Protectora', '\resources\images\PurpleSkills\AuraProtectora.png', 'Cancela todo el daño sufrido durante el proximo ataque de la Horda - Pierdes 1 carta por cada enemigo', 'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (31,'Bola de Fuego', '\resources\images\PurpleSkills\BolaDeFuego.png', 'Daña a todos los enemigos - El resto de heroes sufre 1 de Daño', 'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (32,'Disparo Gelido', '\resources\images\PurpleSkills\DisparoGelido.png','El enemigo afectado no causa daño este turno - Roba 1 carta' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (33,'Disparo Gelido', '\resources\images\PurpleSkills\DisparoGelido.png','El enemigo afectado no causa daño este turno - Roba 1 carta' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (34,'Flecha Corrosiva', '\resources\images\PurpleSkills\FlechaCorrosiva.png', 'Aumenta en 1 el daño de las siguientes cartas que causen daño a este enemigo - Pierdes 1 carta', 'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (35,'Golpe de baston', '\resources\images\PurpleSkills\GolpeDeBaston.png','Si ya utilizaste Golpe de Baston contra el mismo enemigo, el daño de esta carta es 2' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (36,'Golpe de baston', '\resources\images\PurpleSkills\GolpeDeBaston.png','Si ya utilizaste Golpe de Baston contra el mismo enemigo, el daño de esta carta es 2' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (37,'Golpe de baston', '\resources\images\PurpleSkills\GolpeDeBaston.png','Si ya utilizaste Golpe de Baston contra el mismo enemigo, el daño de esta carta es 2' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (38,'Golpe de baston', '\resources\images\PurpleSkills\GolpeDeBaston.png','Si ya utilizaste Golpe de Baston contra el mismo enemigo, el daño de esta carta es 2' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (39,'Orbe Curativo', '\resources\images\PurpleSkills\OrbeCurativo.png','Todos los Heroes recuperan 2 cartas - Eliminas 1 herida de tu Heroe' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (40,'Proyectil Igneo', '\resources\images\PurpleSkills\ProyectilIgneo.png','Ganas 1 ficha de Gloria' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (41,'Proyectil Igneo', '\resources\images\PurpleSkills\ProyectilIgneo.png','Ganas 1 ficha de Gloria' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (42,'Proyectil Igneo', '\resources\images\PurpleSkills\ProyectilIgneo.png','Ganas 1 ficha de Gloria' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (43,'Reconstitucion', '\resources\images\PurpleSkills\Reconstitucion.png','Roba 1 carta - Recuperas 2 cartas' ,'Morado');
INSERT INTO skills(id,name,url,description,color) VALUES (44,'Torrente de Luz', '\resources\images\PurpleSkills\TorrenteDeLuz.png','El resto de Heroes recuperan 2 cartas - Ganas 1 ficha de Gloria' ,'Morado');

--RED SKILLS
INSERT INTO skills(id,name,url,description,color) VALUES (45,'Al Corazon', '\resources\images\RedSkills\AlCorazon.png','Si derrotas un enemigo con este ataque, ganas 1 Moneda si no has jugado otro Al Corazon este turno - Pierdes 1 carta' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (46,'Al Corazon', '\resources\images\RedSkills\AlCorazon.png','Si derrotas un enemigo con este ataque, ganas 1 Moneda si no has jugado otro Al Corazon este turno - Pierdes 1 carta' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (47,'Ataque Furtivo', '\resources\images\RedSkills\AtaqueFurtivo.png','Si derrotas un enemigo con este ataque, ganas 1 Moneda si no has jugado otro Ataque Furtivo este turno' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (48,'Ataque Furtivo', '\resources\images\RedSkills\AtaqueFurtivo.png','Si derrotas un enemigo con este ataque, ganas 1 Moneda si no has jugado otro Ataque Furtivo este turno' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (49,'Ataque Furtivo', '\resources\images\RedSkills\AtaqueFurtivo.png','Si derrotas un enemigo con este ataque, ganas 1 Moneda si no has jugado otro Ataque Furtivo este turno' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (50,'Ballesta Precisa', '\resources\images\RedSkills\BallestaPrecisa.png','Si ya utilizaste Ballesta Precisa contra el mismo enemigo, el daño de esta carta es 3' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (51,'Ballesta Precisa', '\resources\images\RedSkills\BallestaPrecisa.png','Si ya utilizaste Ballesta Precisa contra el mismo enemigo, el daño de esta carta es 3' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (52,'Ballesta Precisa', '\resources\images\RedSkills\BallestaPrecisa.png','Si ya utilizaste Ballesta Precisa contra el mismo enemigo, el daño de esta carta es 3' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (53,'En las Sombras', '\resources\images\RedSkills\EnLasSombras.png','Previenes 2 puntos de daño' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (54,'En las Sombras', '\resources\images\RedSkills\EnLasSombras.png','Previenes 2 puntos de daño' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (55,'Engañar', '\resources\images\RedSkills\Engañar.png','El enemigo elegido no causa daño este turno. Debes gastar 2 Monedas para hacer uso de esta carta' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (56,'Robar Bolsillos', '\resources\images\RedSkills\RobarBolsillos.png','Roba 1 Moneda a cada Heroe' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (57,'Saqueo', '\resources\images\RedSkills\Saqueo.png','Gana 2 Monedas por cada Enemigo vivo' ,'Rojo');
INSERT INTO skills(id,name,url,description,color) VALUES (58,'Saqueo', '\resources\images\RedSkills\Saqueo.png','Gana 2 Monedas por cada Enemigo vivo' ,'Rojo');

--MARKET SKILLS
INSERT INTO skills(id,name,url,description) VALUES (59,'Daga Elfica 1', '\resources\images\market\DagaEfica.png', ' ');
INSERT INTO skills(id,name,url,description) VALUES (60,'Daga Elfica 2', '\resources\images\market\DagaEfica.png', ' ');
INSERT INTO skills(id,name,url,description) VALUES (61,'Alabarda Orca', '\resources\images\market\AlabardaOrca.png', ' ');
INSERT INTO skills(id,name,url,description) VALUES (62,'Armadura de Placas', '\resources\images\market\ArmaduradePlacas.png', 'Recupera 4 cartas');
INSERT INTO skills(id,name,url,description) VALUES (63,'Capa Elfica', '\resources\images\market\CapaElfica.png', 'El enemigo elegido no causa daño esta ronda');
INSERT INTO skills(id,name,url,description) VALUES (64,'Elixir de Concentracion 1', '\resources\images\market\ElixirdeConcentracion.png', 'Roba 3 cartas');
INSERT INTO skills(id,name,url,description) VALUES (65,'Elixir de Concentracion 2', '\resources\images\market\ElixirdeConcentracion.png', 'Roba 3 cartas');
INSERT INTO skills(id,name,url,description) VALUES (66,'Piedra de Amolar', '\resources\images\market\PiedradeAmolar.png', 'Hace 1 de daños a los 3 enemigos de la mesa');
INSERT INTO skills(id,name,url,description) VALUES (67,'Pocion Curativa 1', '\resources\images\market\PocionCurativa.png', 'Retira una ficha de herida');
INSERT INTO skills(id,name,url,description) VALUES (68,'Pocion Curativa 2', '\resources\images\market\PocionCurativa.png', 'Retira una ficha de herida');
INSERT INTO skills(id,name,url,description) VALUES (69,'Pocion Curativa 3', '\resources\images\market\PocionCurativa.png', 'Retira una ficha de herida');
INSERT INTO skills(id,name,url,description) VALUES (70,'Pocion Curativa 4', '\resources\images\market\PocionCurativa.png', 'Retira una ficha de herida');
INSERT INTO skills(id,name,url,description) VALUES (71,'Vial de Conjuracion 1', '\resources\images\market\VialdeConjuracion.png', 'Recupera 5 cartas');
INSERT INTO skills(id,name,url,description) VALUES (72,'Vial de Conjuracion 2', '\resources\images\market\VialdeConjuracion.png', 'Recupera 5 cartas');
INSERT INTO skills(id,name,url,description) VALUES (73,'Arco Compuesto', '\resources\images\market\ArcoCompuesto.png', ' ');


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



INSERT INTO market(id,name,url,cost,description) VALUES (1,'Daga Elfica 1', '\resources\images\market\DagaEfica.png', 3,' ');
INSERT INTO market(id,name,url,cost,description) VALUES (2,'Daga Elfica 2', '\resources\images\market\DagaEfica.png', 3,' ');
INSERT INTO market(id,name,url,cost,description) VALUES (3,'Alabarda Orca', '\resources\images\market\AlabardaOrca.png', 5,' ');
INSERT INTO market(id,name,url,cost,description) VALUES (4,'Armadura de Placas', '\resources\images\market\ArmaduradePlacas.png', 4,'Recupera 4 cartas');
INSERT INTO market(id,name,url,cost,description) VALUES (5,'Capa Elfica', '\resources\images\market\CapaElfica.png', 3,'El enemigo elegido no causa daño esta ronda');
INSERT INTO market(id,name,url,cost,description) VALUES (6,'Elixir de Concentracion 1', '\resources\images\market\ElixirdeConcentracion.png', 3,'Roba 3 cartas');
INSERT INTO market(id,name,url,cost,description) VALUES (7,'Elixir de Concentracion 2', '\resources\images\market\ElixirdeConcentracion.png', 3,'Roba 3 cartas');
INSERT INTO market(id,name,url,cost,description) VALUES (8,'Piedra de Amolar', '\resources\images\market\PiedradeAmolar.png', 4,'Hace 1 de daños a los 3 enemigos de la mesa');
INSERT INTO market(id,name,url,cost,description) VALUES (9,'Pocion Curativa 1', '\resources\images\market\PocionCurativa.png', 8,'Retira una ficha de herida');
INSERT INTO market(id,name,url,cost,description) VALUES (10,'Pocion Curativa 2', '\resources\images\market\PocionCurativa.png', 8,'Retira una ficha de herida');
INSERT INTO market(id,name,url,cost,description) VALUES (11,'Pocion Curativa 3', '\resources\images\market\PocionCurativa.png', 8,'Retira una ficha de herida');
INSERT INTO market(id,name,url,cost,description) VALUES (12,'Pocion Curativa 4', '\resources\images\market\PocionCurativa.png', 8,'Retira una ficha de herida');
INSERT INTO market(id,name,url,cost,description) VALUES (13,'Vial de Conjuracion 1', '\resources\images\market\VialdeConjuracion.png', 5,'Recupera una carta de tu pila');
INSERT INTO market(id,name,url,cost,description) VALUES (14,'Vial de Conjuracion 2', '\resources\images\market\VialdeConjuracion.png', 5,'Recupera una carta de tu pila');
INSERT INTO market(id,name,url,cost,description) VALUES (15,'Arco Compuesto', '\resources\images\market\ArcoCompuesto.png', 5,' ');


INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (1,'Simple Enemy', '\resources\images\enemies\simpleEnemy1.png', false, 2, 1, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (2,'Simple Enemy', '\resources\images\enemies\simpleEnemy1.png', false, 2, 1, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (3,'Simple Enemy', '\resources\images\enemies\simpleEnemy2.png', false, 3, 2, 2, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (4,'Simple Enemy', '\resources\images\enemies\simpleEnemy2.png', false, 3, 2, 2, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (5,'Simple Enemy', '\resources\images\enemies\simpleEnemy3.png', false, 3, 1, 0, 1);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (6,'Simple Enemy', '\resources\images\enemies\simpleEnemy3.png', false, 3, 1, 0, 1);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (7,'Simple Enemy', '\resources\images\enemies\simpleEnemy4.png', false, 4, 2, 1, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (8,'Simple Enemy', '\resources\images\enemies\simpleEnemy4.png', false, 4, 2, 1, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (9,'Simple Enemy', '\resources\images\enemies\simpleEnemy5.png', false, 5, 3, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (10,'Simple Enemy', '\resources\images\enemies\simpleEnemy5.png', false, 5, 3, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (11,'Simple Enemy', '\resources\images\enemies\simpleEnemy6.png', false, 6, 4, 3, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (12,'Simple Enemy', '\resources\images\enemies\simpleEnemy6.png', false, 6, 4, 3, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (13,'Boss 1', '\resources\images\enemies\boss1.png', true, 8, 4, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (14,'Boss 2', '\resources\images\enemies\boss2.png', true, 9, 5, 0, 0);
INSERT INTO enemies(id,name,url,is_boss, max_health,glory,extra_glory,extra_gold) VALUES (15,'Boss 3', '\resources\images\enemies\boss3.png', true, 10, 6, 0, 0);


--Actions

--damage
INSERT INTO Actions (id, type, cantidad) VALUES (1, 0, 0);
INSERT INTO Actions (id, type, cantidad) VALUES (2, 0, 1);
INSERT INTO Actions (id, type, cantidad) VALUES (3, 0, 2);
INSERT INTO Actions (id, type, cantidad) VALUES (4, 0, 3);
INSERT INTO Actions (id, type, cantidad) VALUES (5, 0, 4);
INSERT INTO Actions (id, type, cantidad) VALUES (6, 0, 5);
--draw
INSERT INTO Actions (id, type, cantidad) VALUES (7, 1, 0);
INSERT INTO Actions (id, type, cantidad) VALUES (8, 1, 1);
INSERT INTO Actions (id, type, cantidad) VALUES (9, 1, 2);
INSERT INTO Actions (id, type, cantidad) VALUES (10, 1, 3);
INSERT INTO Actions (id, type, cantidad) VALUES (11, 1, 4);
--recover
INSERT INTO Actions (id, type, cantidad) VALUES (12, 2, 0);
INSERT INTO Actions (id, type, cantidad) VALUES (13, 2, 1);
INSERT INTO Actions (id, type, cantidad) VALUES (14, 2, 2);
INSERT INTO Actions (id, type, cantidad) VALUES (15, 2, 3);
INSERT INTO Actions (id, type, cantidad) VALUES (71, 2, 4);
INSERT INTO Actions (id, type, cantidad) VALUES (72, 2, 5);
--gainglory
INSERT INTO Actions (id, type, cantidad) VALUES (16, 3, 0);
INSERT INTO Actions (id, type, cantidad) VALUES (17, 3, 1);
INSERT INTO Actions (id, type, cantidad) VALUES (18, 3, 2);
--gainGold
INSERT INTO Actions (id, type, cantidad) VALUES (19, 4, 0);
INSERT INTO Actions (id, type, cantidad) VALUES (20, 4, 1);
INSERT INTO Actions (id, type, cantidad) VALUES (21, 4, 2);
INSERT INTO Actions (id, type, cantidad) VALUES (22, 4, 3);
INSERT INTO Actions (id, type, cantidad) VALUES (23, 4, 4);
INSERT INTO Actions (id, type, cantidad) VALUES (24, 4, 5);
INSERT INTO Actions (id, type, cantidad) VALUES (25, 4, 6);
INSERT INTO Actions (id, type, cantidad) VALUES (26, 4, 7);
INSERT INTO Actions (id, type, cantidad) VALUES (27, 4, 8);
--loseGold
INSERT INTO Actions (id, type, cantidad) VALUES (28, 5, 0);
INSERT INTO Actions (id, type, cantidad) VALUES (29, 5, 1);
INSERT INTO Actions (id, type, cantidad) VALUES (30, 5, 2);
INSERT INTO Actions (id, type, cantidad) VALUES (31, 5, 3);
INSERT INTO Actions (id, type, cantidad) VALUES (32, 5, 4);
--gainLife
INSERT INTO Actions (id, type, cantidad) VALUES (33, 6, 0);
INSERT INTO Actions (id, type, cantidad) VALUES (34, 6, 1);
--Defense
INSERT INTO Actions (id, type, cantidad) VALUES (35, 7, 0);
INSERT INTO Actions (id, type, cantidad) VALUES (36, 7, 1);
INSERT INTO Actions (id, type, cantidad) VALUES (37, 7, 2);
INSERT INTO Actions (id, type, cantidad) VALUES (38, 7, 3);
INSERT INTO Actions (id, type, cantidad) VALUES (39, 7, 4);
INSERT INTO Actions (id, type, cantidad) VALUES (40, 7, 5);
INSERT INTO Actions (id, type, cantidad) VALUES (41, 7, 6);
INSERT INTO Actions (id, type, cantidad) VALUES (42, 7, 7);
INSERT INTO Actions (id, type, cantidad) VALUES (43, 7, 8);
INSERT INTO Actions (id, type, cantidad) VALUES (44, 7, 9);
INSERT INTO Actions (id, type, cantidad) VALUES (45, 7, 10);
--Discard
INSERT INTO Actions (id, type, cantidad) VALUES (46, 8, 0);
INSERT INTO Actions (id, type, cantidad) VALUES (47, 8, 1);
INSERT INTO Actions (id, type, cantidad) VALUES (48, 8, 2);
INSERT INTO Actions (id, type, cantidad) VALUES (49, 8, 3);
INSERT INTO Actions (id, type, cantidad) VALUES (50, 8, 4);
INSERT INTO Actions (id, type, cantidad) VALUES (51, 8, 5);
INSERT INTO Actions (id, type, cantidad) VALUES (52, 8, 6);
INSERT INTO Actions (id, type, cantidad) VALUES (53, 8, 7);
INSERT INTO Actions (id, type, cantidad) VALUES (54, 8, 8);
INSERT INTO Actions (id, type, cantidad) VALUES (55, 8, 9);
INSERT INTO Actions (id, type, cantidad) VALUES (56, 8, 10);
INSERT INTO Actions (id, type, cantidad) VALUES (57, 8, 11);
INSERT INTO Actions (id, type, cantidad) VALUES (58, 8, 12);
INSERT INTO Actions (id, type, cantidad) VALUES (59, 8, 13);
INSERT INTO Actions (id, type, cantidad) VALUES (60, 8, 14);
INSERT INTO Actions (id, type, cantidad) VALUES (61, 8, 15);
INSERT INTO Actions (id, type, cantidad) VALUES (62, 8, 16);
INSERT INTO Actions (id, type, cantidad) VALUES (63, 8, 17);
INSERT INTO Actions (id, type, cantidad) VALUES (64, 8, 18);
INSERT INTO Actions (id, type, cantidad) VALUES (65, 8, 19);
INSERT INTO Actions (id, type, cantidad) VALUES (66, 8, 20);
INSERT INTO Actions (id, type, cantidad) VALUES (67, 8, 21);
INSERT INTO Actions (id, type, cantidad) VALUES (68, 8, 22);
--endAttackPhase
INSERT INTO Actions (id, type, cantidad) VALUES (69, 9, 0);
INSERT INTO Actions (id, type, cantidad) VALUES (70, 9, 1);



--ACTION_CARDS

--Actions Green SkillCards

    --compañero lobo
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 1);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (37, 1);

    --disparo certero
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (4, 2);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (47, 2);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (70, 2);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (4, 3);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (47, 3);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (70, 3);

    --disparo rápido
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 4);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 4);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 5);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 5);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 6);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 6);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 7);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 7);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 8);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 8);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 9);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 9);

    --en la diana
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (5, 10);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (17, 10);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (36, 10);

    --luvia de flechas
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 11);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (48, 11);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 12);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (48, 12);

    --recoger flechas

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (13, 13);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 13);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (13, 14);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 14);

--Actions Blue SkillCards

    --ataque brutal
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (4, 15);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (47, 15);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (4, 16);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (47, 16);

    --carga con escudo
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 17);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (37, 17);

    --doble espadazo
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 18);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (47, 18);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 19);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (47, 19);

    --escudo
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (36, 20);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (37, 20);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (38, 20);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (39, 20);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (40, 20);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (41, 20);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (42, 20);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (43, 20);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (44, 20);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (45, 20);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (70, 20);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (36, 21);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (37, 21);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (38, 21);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (39, 21);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (40, 21);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (41, 21);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (42, 21);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (43, 21);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (44, 21);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (45, 21);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (70, 21);

    --espadazo
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 22);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 22);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 23);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 23);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 24);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 24);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 25);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 25);

    --paso atrás
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (9, 26);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (9, 27);

    --todo o nada
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 28);   
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (13, 28);  --añade el daño de la carta robada arriba

    --voz de aliento
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (14, 29);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 29);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (17, 29);

--Actions Purple SkillCards

    --aura protectora
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (14, 30);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (45, 30);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (45, 30);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (45, 30);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (47, 30);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (48, 30);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (49, 30);

    --bola de fuego
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 31);   --el resto de jugadores recibe 1 de daño

    --disparo gélido
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 32);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (45, 32);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 32);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 33);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (45, 33);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 33);

    --flecha corrosiva
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 34);      --añade 1 de daño a las 3 cartas siguientes
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (47, 34);

    --golpe de bastón
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 35);   --si este enemigo ya recibió golpe de bastón, el daño es 2 en vez de 1

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 36);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 37);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 38);

    --orbe curativo
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (14, 39);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (34, 39);

    --proyectil ígneo
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 40);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (17, 40);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 41);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (17, 41);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 42);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (17, 42);

    --resconstitución
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (8, 43);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (14, 43);

    --torrente de luz

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 44);  -- el resto recupera 2 cartas
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (17, 44);


--Actions Red SkillCards

    --al corazón
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (5, 45);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 45);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (47, 45);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (5, 46);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 46);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (47, 46);

    --ataque furtivo
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 47);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 47);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 48);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 48);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 49);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 49);

    --bayesta precisa
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 50);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (4, 50);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 51);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (4, 51);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (3, 52);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (4, 52);

    --en las sombras
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 53);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (37, 53);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (2, 54);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (37, 54);

    --engañar
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (45, 55);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (30, 55);

    --robar bolsillos
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 56);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 56);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 56);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (29, 56); --a
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (29, 56); --los
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (29, 56);  --otros

    --saqueo
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 57);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 57);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 57);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (17, 57);

INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 58);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 58);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (20, 58);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (17, 58);

--Actions of MarketCard
    --daga elfica
INSERT INTO actions_skill_card (fk_skillCard,fk_actions) VALUES (59, 3);
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (60, 3);
    --alabarda orca
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (61, 5);
    --armadura de placas
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (62, 71);
    --capa elfica
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (36, 63);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (37, 63);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (38, 63);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (39, 63);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (40, 63);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (41, 63);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (42, 63);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (43, 63);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (44, 63);
INSERT INTO actions_skill_card (fk_actions, fk_skillCard) VALUES (45, 63);
    --elixir de concetración
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (64, 10);
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (65, 10);
    --piedra de amolar
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (66, 2);
    --pocion curativa
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (67, 34);
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (68, 34);
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (69, 34);
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (70, 34);
    --vial de conjuracion
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (71, 72);
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (72, 72);
    --arco compuesto
INSERT INTO actions_skill_card (fk_skillCard, fk_actions) VALUES (73, 5);


--Games

INSERT INTO games(id,creator,date,duration,is_in_progress,is_public,join_code,winner) VALUES (1,1,'2021-11-03',1000,FALSE,TRUE,'1e7be91755b7497080849ef0910c044d',null);
INSERT INTO games(id,creator,date,duration,is_in_progress,is_public,join_code,winner) VALUES (2,2,'2021-11-05',3600,FALSE,FALSE,'99ae49ed18bf458e89576a2d313a6e70',null);
INSERT INTO games(id,creator,date,duration,is_in_progress,is_public,join_code,winner) VALUES (3,3,'2021-11-05',null,FALSE,TRUE,'99ae43ed18bf458e89576b2d313a6e70',null);

INSERT INTO games_users(fk_game,fk_user) VALUES(1,1);
INSERT INTO games_users(fk_game,fk_user) VALUES(2,2);

/*
INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (4,3,1); --Habilidad 3 añadida al jugador 3 (mromalde) en la partida 4 
INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (4,3,1); --Habilidad 3 añadida al jugador 3 (mromalde) en la partida 4 
INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (4,3,1); --Habilidad 3 añadida al jugador 3 (mromalde) en la partida 4 
INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (4,3,2); --Habilidad 3 añadida al jugador 3 (mromalde) en la partida 4 
INSERT INTO GAMES_USERS_SKILL_CARDS(GAME_USER_ID, SKILL_CARDS_ID, SKILL_STATE) VALUES (4,2,1); --Habilidad 3 añadida al jugador 3 (mromalde) en la partida 4 
*/