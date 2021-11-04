# No Time For Heroes

No Time For Heroes es un juego de rol en el que no hay tablero, siendo basado en héroes que luchan contra una horda de monstruos liderada por un boss final, tratando de vencer a la horda, y posteriormente siendo el héroe con más gloria.

Es un juego fácil y rápido en el cual pasar un buen rato con amigos de forma on-line.

# Desarrollo de la partida

En una partida puede haber desde 2 hasta 4 jugadores, desarrollándose de la siguiente manera:
- Para empezar, cada jugador elige un héroe, no pudiendo haber dos del mismo tipo.
- Después se eligen dos cartas de la mano para ver quien es el jugador que empieza, siendo este el que tenga más ataque en las 2 cartas elegidas; estas dos cartas se descartan y se robarán 2 nuevas. Si hay empate, se decidirá por la edad de los jugadores, comenzando el mayor.
- Preparamos en la mesa 5 cartas de mercado del mazo destinado a estas, 3 cartas de enemigos del mazo de horda y una carta de escenario. La última carta de este mazo deberá ser el boss final.

Empieza el juego.

Los turnos se dividen en 3 fases:
- 1º Fase: el héroe utiliza sus cartas de habilidades para matar a los enemigos de la horda, cada vez que utiliza una carta de habilidad, esta va al mazo de descarte. Sus cartas irán infligiendo daño a los enemigos hasta matarlos,siempre y cuando este daño supere a sus puntos de fortaleza. Cada enemigo derrotado otorga unos puntos de gloria al jugador, y puede otorgar además puntos de gloria y/o monedas extras, que no se conocerán hasta derrotar al enemigo. 
- 2º Fase: al terminar el turno, cada enemigo inflige al héroe un daño equivalente a la suma de las vidas que queden en las cartas de horda en el campo, (si haces daño a un enemigo, pero no lo matas, te hace el daño de su vida menos la vida que le has quitado).
El daño que recibe el héroe se traduce en el número de cartas del mazo de habilidades que tienes que enviar al mazo de descarte. Si no tienes suficientes cartas en el mazo, se voltea el mazo de descarte, se baraja y se restablece tu mazo de habilidades, pero el héroe pierde 1 punto de vida; si se queda sin vidas el héroe correspondiente morirá y no participará más en el juego.
- 3º Fase: es el turno de comprar en el mercado, utilizando las monedas que has recibido al matar a la horda, o de habilidades de ciertas cartas. Puedes comprar cartas de mercado, que suelen ser tanto ayudas como mejoras o armas para hacer más daño. Cada carta de mercado cuenta como una carta de habilidad más en el mazo.

Una vez terminadas las 3 fases, llega el turno al siguiente héroe. Se repondrán las cartas de mercado, hasta llegar a 5, y se añadirá un número de enemigos dependiendo de cuantos hayan sido derrotados. Si todos los enemigos de la mesa son derrotados en este turno, la carta de escenario cambiará, y se sacarán tres cartas nuevas del mazo de la horda. Si al finalizar aún quedan 1 o 2 enemigos con vida, se colocará una nueva carta de la horda.

- Las partidas suelen durar en torno a 20-30 minutos, y terminan cuando se mata al boss final de la horda.

Para elegir al vencedor de entre los héroes, se hará un recuento del número de fichas de gloria que tiene cada uno (estas fichas se consiguen con el botín de matar a las cartas de horda, o se te otorgan con las diferentes habilidades del mazo o del propio héroe) y el que tenga más será el vencedor.

# Contenido de la aplicación

En nuestra aplicación podras jugar de forma online con gente de todo el mundo, además de poder disfrutar del juego también tenemos disponibles todo tipo de logros los cuales haran una experiencia mas atractiva las partidas online, estos logros de iran actualizando y subiendo nuevos a lo largo de los días. También se incluye un apartado de estadísticas en las cuales se ve el desarrollo de tu perfil como jugador, asi podrás analizar y estudiar tu forma de jugar para identificar puntos débiles y mejorarlos. 
La aplicación contiene rankings de mejores jugadores, esto supone un gran incentivo para la gente mas Pro, estar en el top semanal sera uno de los retos mas dificiles para el jugador.

# Apartado de comunicación

La aplicación forma parte de un proyecto de 6 alumnos de la universidad de Sevilla, si se encuentra algun bug podéis contactar con cualquiera de nosotros.

# Apartado tecnológico

Este proyecto esta desarrollado en Java, Maven y Loombook.
