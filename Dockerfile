FROM openjdk:11.0.13-jre-slim
COPY /pkg/*.jar /img/app.jar
WORKDIR /img/
ENV APP_ENV=dev
EXPOSE 6666
CMD ["java", "-jar", "app.jar --spring.profiles.active=${APP_ENV}"]