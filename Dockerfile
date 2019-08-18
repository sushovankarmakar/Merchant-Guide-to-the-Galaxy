FROM java:8
COPY build/libs/*.jar /var/www/java/
WORKDIR /var/www/java
EXPOSE 8888
RUN chmod 777 GuideToGalaxy-1.0-SNAPSHOT.jar
CMD ["java", "-jar", "GuideToGalaxy-1.0-SNAPSHOT.jar"]