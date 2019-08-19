FROM java:8
COPY build/libs/*.jar /var/www/java/
WORKDIR /var/www/java
EXPOSE 8888
CMD ["java", "-jar", "GuideToGalaxy-1.0-SNAPSHOT.jar"]