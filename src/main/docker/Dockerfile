FROM openjdk:11

WORKDIR /app

COPY target/product-ms-0.0.1-SNAPSHOT.jar /app/product-ms.jar

CMD ["java", "-jar", "product-ms.jar"]

RUN ls -l /app

RUN echo '\n\n * * * Imagem gerada com sucesso * * * \n'