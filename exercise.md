Vamos a realizar un jueguecillo sobre El Hobbit.
Consiste en lo siguiente:
    - Tenemos tres personajes: Gandalf, Thorin y Bilbo. Gandalf puede hacer las
pruebas de magia, Thorin las de fuerza y Bilbo las de habilidad. Los tres
parten con una capacidad máxima respectiva de 50. Estos datos están
inicialmente en la base de datos. No hará falta hacer un CRUD de ellos.
    - Se genera un tablero de 20 casillas en las que habrá escondidas 20 pruebas
(una por casilla). Las pruebas pueden ser de los 3 tipos (magia, fuerza o
habilidad) y la cantidad de esfuerzo necesario para lograrla. Esta cantidad de
esfuerzo serán los siguientes números posibles: 5, 10, 15, 20, 25, 30, 35, 40,
45 y 50. Estas pruebas también están ya en la base de datos pero debemos
dar la posibilidad de generar con factorías una cantidad determinada de
ellas.
    - Una vez generado el tablero (los tableros), comienza el juego. Consiste en
destapar casillas y realizar las pruebas (por el héroe correspondiente y si le
queda poder) de forma que:
    o Si el poder del héroe es mayor que el necesario para realizar la prueba
    se logra la prueba al 90 %.
    o Si es igual se logra al 70%.
    o Si es menor se consigue al 50%.
    o Si no se logra, ese héroe pierde toda su capacidad y quedará inactivo.
    Si se logra pierde la capacidad necesaria para lograr la prueba (se
    resta).
    o Si al héroe no le quedara poder para afrontar la prueba se debe dar
    por perdida.
    - Ganamos cuando hemos logrado destapar la mitad de las casillas y vive, al
    menos, un héroe.
    - Perdemos si nos quedamos sin héroes o si hemos destapado 5 casillas
    seguidas perdiendo.
Piensa los verbos y forma de pasar los datos necesarios para que cada usuario
pueda tener su propia simulación asociada y vaya comprobando el estado de la
simulación y el resultado de su elección de casilla. Solo una simulación activa por
usuario