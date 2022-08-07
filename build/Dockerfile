FROM openjdk:8-jdk-alpine
WORKDIR /app
ADD . /app
EXPOSE 9001
#不能把java -jar放在一起，否则会出现$PATH找不到java -jar的问题
CMD ["java", "-jar", "task-0.0.1-SNAPSHOT.jar"]