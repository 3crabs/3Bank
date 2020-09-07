FROM openjdk:14-alpine
COPY build/libs/free_bank-*-all.jar free_bank.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "free_bank.jar"]