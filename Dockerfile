FROM openjdk:11.0-jre
WORKDIR /home/container
ADD target/JMusicBot-*-All.jar ./JMusicBot.jar
CMD ["java", "-Dnogui=true", "-jar", "JMusicBot.jar"]
