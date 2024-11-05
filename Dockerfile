# define a imagem base do java
FROM openjdk:17-jdk-alpine

# define o diretório de no container
VOLUME /tmp

# copia arquivo jar do projeto para dentro de container
COPY target/mypilas-0.0.1-SNAPSHOT.jar app.jar

# define o comando para executar o projeto
ENTRYPOINT ["java","-jar","/app.jar"]
