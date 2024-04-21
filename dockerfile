FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/pik-app.jar /opt/pik-app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar pik-app.jar