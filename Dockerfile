FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/twitter-analytics.jar app.jar
EXPOSE 5000
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]