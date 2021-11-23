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


INSERT INTO authorities(id,authority,username) VALUES (1,'admin','pabloespada');
INSERT INTO authorities(id,authority,username) VALUES (2,'admin','peras2');
INSERT INTO authorities(id,authority,username) VALUES (3,'admin','juangar2');
INSERT INTO authorities(id,authority,username) VALUES (4,'player','pabloespada2');
INSERT INTO authorities(id,authority,username) VALUES (5,'admin','mromalde');
INSERT INTO authorities(id,authority,username) VALUES (6,'admin','mario');


INSERT INTO games(id,creator,date,duration,is_in_progress,is_public,join_code) VALUES (1,1,'2021-11-03',1000,TRUE,TRUE,'1e7be91755b7497080849ef0910c044d');
INSERT INTO games(id,creator,date,duration,is_in_progress,is_public,join_code) VALUES (2,2,'2021-11-05',3600,FALSE,FALSE,'99ae49ed18bf458e89576a2d313a6e70');
INSERT INTO games_users(fk_user, fk_game) VALUES(1,1);
INSERT INTO games_users(fk_user, fk_game) VALUES(2,2);

--INSERT INTO GAMES_USERS (FK_GAME,FK_USER) VALUES (1,2);
--INSERT INTO GAMES_USERS (FK_GAME,FK_USER) VALUES (1,3);
--INSERT INTO GAMES_USERS (FK_GAME,FK_USER) VALUES (1,4);


INSERT INTO achievement(id,name,description) VALUES (1,'Sherif', 'Play two games');


INSERT INTO heroes(id,name,url,life,skill,color,deckid) VALUES (1,'Taheral','https:',2, 'Al realizar la maniobra de evasión, coge dos monedas por cada carta que descartes. Una vez por partida.','Morado',1);
INSERT INTO heroes(id,name,url,life,skill,color,deckid) VALUES (2,'Idril','https:',3, 'En cualquier momento puedes mirar las tres cartas inferiores de la horda y devolverlas al mazo en el orden que prefieras. Dos veces por partida.','Verde',2);
INSERT INTO heroes(id,name,url,life,skill,color,deckid) VALUES (3,'Feldon','https:',2, 'Al resolver el ataque de la horda, tan solo pierde la mitad de cartas de las indicadas por el daño. Una vez por partida.','Rojo',3);
INSERT INTO heroes(id,name,url,life,skill,color,deckid) VALUES (4,'Lisavette','https:',3, 'Mientras un heroe se enfrenta a la horda, utiliza un escudo para prevenirle del daño del enemigo. Roba hasta dos monedas de ese heroe. Dos veces por partida.','Azul',4);
INSERT INTO heroes(id,name,url,life,skill,color,deckid) VALUES (5,'Aranel','https:',2, 'En cualquier momento, busca una carta del mazo de Habilidad y sustituyela por una de tu mano. Acto seguido baraja el mazo. Una vez por partida.','Morado',1);
INSERT INTO heroes(id,name,url,life,skill,color,deckid) VALUES (6,'Beleth-Il','https:',3, 'Cuando utilices la carta Disparo Rapido, la primera carta fallida que robes podrás recuperarla y robar otra. Dos veces por partida.','Verde',2);
INSERT INTO heroes(id,name,url,life,skill,color,deckid) VALUES (7,'Neddia','https:',2, 'En cualquier momento, busca en el mazo de mercado hasta 2 cartas y sustituyelas por cartas que ya estivieron disponibles. Acto seguido baraja el mazo. Una vez por partida.','Rojo',3);
INSERT INTO heroes(id,name,url,life,skill,color,deckid) VALUES (8,'Valerys','https:',3, 'Al resolver el ataque de la horda sobre otro heroe, tu recibes el daño y ganas 1 ficha de gloria. Dos veces por partida.','Azul',4);

INSERT INTO scenes(id,name,url,description) VALUES (1,'Campo de batalla', 'https:','Cada enemigo vencido aportara 1 moneda adicional al héroe que lo derrotó' );
INSERT INTO scenes(id,name,url,description) VALUES (2,'Lágrimas de Aradiel', 'https:','Una vez por turno, el héroe activo puede utilizar una carta de la mano de otro héroe (elegida al azar). Para hacerlo deberá entregarle 1 ficha de Gloria. Tras aplicar la carta, se descarta en la pila de Desgaste del propietario y este roba una nueva' );
INSERT INTO scenes(id,name,url,description) VALUES (3,'Lodazal de Kalern', 'https:','Al comienzo del turno de cada héroe, el jugador de su izquierda elige un enemigo. El enemigo elegido vuelve a la parte inferior del mazo (si tuviera heridas, se descartan');
INSERT INTO scenes(id,name,url,description) VALUES (4,'Mercado de Lotharion', 'https:','Mientras este sea el escenario activo, todos los articulos del mercado cuestan una Moneda menos');


INSERT INTO skills(id,name,url,description,deckid) VALUES (1,'Compañero Lobo', 'https:', 'Previene 2 puntos de daño', 2);
INSERT INTO skills(id,name,url,description,deckid) VALUES (2,'Disparo certero', 'https:', 'Pierdes 1 carta - Finalizas el ataque', 2);
INSERT INTO skills(id,name,url,description,deckid) VALUES (3,'En la diana', 'https:', 'Gana 1 ficha de Gloria - Pierdes 1 carta', 2);



