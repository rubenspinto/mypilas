# define a imagem base do java
FROM eclipse-temurin:17-jdk-alpine

RUN apk update && apk upgrade --no-cache

# define o diretório de no container
VOLUME /tmp

# copia arquivo jar do projeto para dentro de container
COPY target/mypilas.jar app.jar

# define o comando para executar o projeto
ENTRYPOINT ["java","-jar","/app.jar"]

# define a porta que o container vai expor
EXPOSE 8080
# define o nome do container
LABEL name="mypilas"
# define a versão do container
LABEL version="0.0.1"
# define a descrição do container
LABEL description="Container para o projeto mypilas"