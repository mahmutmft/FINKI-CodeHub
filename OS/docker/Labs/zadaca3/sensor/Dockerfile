FROM eclipse-temurin:latest

WORKDIR /app

RUN mkdir -p sensor

COPY TemperatureSensor.java sensor/

RUN javac sensor/TemperatureSensor.java

CMD ["java", "sensor.TemperatureSensor"]
