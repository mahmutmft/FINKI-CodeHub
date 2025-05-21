FROM eclipse-temurin:latest

WORKDIR /app

RUN mkdir -p monitor

COPY TemperatureMonitor.java monitor/

RUN javac monitor/TemperatureMonitor.java

CMD ["java", "monitor.TemperatureMonitor"]