# Primer trabajo práctico del ramo Fullstack
# Consiste en mostrar una pelicula a través de us ID o simplemente mostrar todas las peliculas que se encuentran en base de datos.
# El servicio está contruido con Java 21 y Springboot 3.2.3.
# Todas las dependencias del servicio se descargarán atutomáticamente con maven.
# Dependencias utilizadas: Springweb, Devtools, Lombok, JPA, Oracle JDBC.
# La información con las peliculas se encuentra en el archivo movie.csm que está ubicado en: src/main/resources/movie.scm, es importante insertar estos datos en la base de datos para obtener la información requerida a través de los endpoints. 
# Endpoints para consumir el servicio:
# http://localhost:8080/peliculas Devuelve una lista con todas las peliculas.
# http://localhost:8080/peliculas/1 Devuelve una pelicula por su id.
# Peliculas que se listarán

# [
#    {
#        "id": 1,
#        "title": "Interstellar",
#        "year": "2014",
#        "director": "Christopher Nolan",
#        "genre": "Science fiction",
#        "synopsis": "A group of scientists and explorers, led by Cooper, embark on a space journey to find a place with the necessary conditions to replace Earth and start a new life there. Earth is coming to an end and this group needs to find a planet beyond our     
#         galaxy that will guarantee the future of the human race."
#    },
#    {
#        "id": 2,
#        "title": "Inception",
#        "year": "2010",
#        "director": "Christopher Nolan",
#        "genre": "Science fiction",
#        "synopsis": "Dom Cobb (Leonardo DiCaprio) is a thief with an uncanny ability to enter people's dreams and steal the secrets of their subconscious. His ability has made him a hot commodity in the world of corporate espionage, but it has come at a great cost to 
#         the people he loves. Cobb has a chance to be forgiven when he is given an impossible task: plant an idea in a person's mind. If he succeeds, it will be the perfect crime, but an enemy anticipates his moves."
#    },
#    {
#        "id": 3,
#        "title": "Arrival",
#        "year": "2016",
#        "director": "Denis Villeneuve",
#        "genre": "Science fiction",
#        "synopsis": "Twelve alien spacecraft begin arriving on our planet. The military high command asks for the help of an expert linguist to try to find out if the aliens come in peace or pose a threat. Little by little the woman will try to learn to communicate 
#         with the strange invaders, who have their own language, to find out the true and mysterious reason for the extraterrestrial visit."
#    },
#    {
#        "id": 4,
#        "title": "The Martian",
#        "year": "2015",
#        "director": "Ridley Scott",
#        "genre": "Science fiction",
#        "synopsis": "A space explorer is trapped on Mars after being abandoned by his crew members, who thought he had died in a storm. With almost no resources and only his wits, he will try to survive while NASA, on the one hand, and his crew members, on their own, 
#         try to rescue him."
#   },
#    {
#        "id": 5,
#        "title": "The Matrix",
#        "year": "1999",
#        "director": "Lana Wachowski, Lilly Wachowski",
#        "genre": "Science fiction",
#        "synopsis": "Computer programmer Thomas Anderson, better known in the hacker world as Neo, is targeted by the dreaded Agent Smith. Two other hackers, Trinity and Morpheus, contact Neo to help him escape. The Matrix possesses you. Follow the white rabbit."
#    }
# ]
