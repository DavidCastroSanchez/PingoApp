# PingoApp
Aplicacion desarrollada por David Castro para el concurso de Huawei HSD developers

Mi nombre es David Castro Sanchez y soy alumno de 4 año del grado en ingeniería telemática de la universidad politécnica de Madrid.
Esta especialidad de las telecomunicaciones me abrió una pequeña ventana al mundo de la programación y desde entonces me enamoré de 
ella y empecé a aprender a programar y desarrollar aplicaciones móviles de manera autodidacta y pensé que esta oportunidad que nos 
ofrecía Huawei era perfecta para poner en práctica los conocimientos adquiridos.
Cuando vi la oportunidad de participar en el concurso no lo dude, y tras pensarlo un tiempo se me ocurrió la idea de desarrollar una
aplicación con juegos para jugar estando de fiesta o en ambientes informales para jóvenes (y no tan jóvenes). El objetivo con esta 
aplicación era crear un MVP (producto mínimo viable por sus siglas en ingles) para disponer de una beta de la aplicación para su testeo.
La aplicación esta compuesta por 4 juegos (estos son los iniciales, pero está planeado desarrollar al menos dos más).
Pingo- Un juego de cartas en el que se van sacando cartas de un mazo de cartas las cuales tienen una prueba o acción que realizar. 
El Juego- Un juego para jugar con amigos con diferentes pruebas, preguntas, retos…
Autopista- Un juego solitario en el que cada error se paga, el jugador deberá adivinar si la siguiente carta que salga del mazo es de 
mayor o menor valor que la señalada por la flecha
Yo Nunca-Un juego clásico, pero con dos versiones una versión clásica con preguntas divertidas graciosas o comprometidas y una versión 
erótica con preguntas más calientes y personales.

Toda la aplicación tiene soporte multilenguaje. El idioma predeterminado es el español y tiene soporte para los dispositivos que tengan 
el idioma ingles por defecto (Los idiomas elegidos son español e inglés pero los ficheros de String dan soporte a todo el contenido escrito 
de la aplicación siendo muy sencillo la traducción a otros idiomas).
Todos los juegos cuentan con un botón de como jugar en el que aparece un pop up con las normas y mecánicas de cada juego.
Se ha creado una dirección de correo electrónico para la comunicación aplicación-usuarios, se ha creado un activity para el envió de correos 
(funcionalidad presente en los botones de ayuda del menú principal y los botones de sugerencia de los juegos que dispongan de él), esta 
funcionalidad esta pensada para renovar de una manera activa y gradual el contenido de la aplicación, así como el establecimiento de un canal
para el intercambio de mensajes entre el usuario y el desarrollador. El mensaje que quiera enviar el usuario será enviado a la dirección de 
correo que introduzca en el formulario (con un texto que confirma la recepción de su mensaje) a través de la dirección de la aplicación 
(además en copia oculta se incluye la propia dirección de la aplicación para tener un registro de estos mensajes).Para los juegos que involucran
cartas se ha creado una clase para poder crear objetos de tipo carta (estos objetos tienen atributos que no son usados en los juegos actuales,
pero serán necesarios para los nuevos juegos que se están desarrollando y que se lanzaran a modo de actualización).
En cuanto a los kits de desarrollo de Huawei para el desarrollo de esta app se ha hecho uso de los kits de anuncios y de analíticas.
El kit de analíticas nos ofrecerá la información predeterminada del estado de la aplicación y entre otros su impacto en el mercado y los usuarios.
El kit de anuncios ofrece la posibilidad de publicar anuncios y monetizar la aplicación es por ello por lo que se han incluido anuncios de tipo 
banner e intersticial. Los anuncios de tipo intersticial están presentes en los juegos de yo nunca, El juego y autopista y se presentan una vez 
por partida cuando se da un numero de iteraciones con el juego (en el caso de yo nunca y el juego se lanzan los intersticial a partir de la décima
iteración con el botón siguiente y en el caso de autopista a partir de la primera iteración del primer botón).
Por último esta planificado incluir los kits de in-app purchase (a la publicación de la aplicación) para hacer de pago algunos juegos (en formato
de suscripción mensual) y el kit de Huawei-id para identificar a los usuarios permitiendo crear una BBDD y una comunidad alrededor de la aplicación.

