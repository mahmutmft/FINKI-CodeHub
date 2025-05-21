1. Create volumes named temperature and temperaturelevel:

```
docker volume create temperature
docker volume create temperaturelevel
```

2. The files temperature.txt and temperaturelevel.txt will be created automatically by the Java applications when they run.

3. Create a network named temperature-level-network with bridge driver:

```
docker network create --driver bridge temperature-level-network
```

4. The environment variables are already defined in the docker-compose.yml file:

```
LOW_TEMPERATURE=5
MEDIUM_TEMPERATURE=19
HIGH_TEMPERATURE=35
```

5. Start Docker Compose:

```
docker-compose up --build
```

6. List the contents of temperature and temperaturelevel volumes:

```
docker volume inspect temperature
docker volume inspect temperaturelevel
```

7. View the IPv4 addresses of the containers in the temperature-level-network:

```
docker network inspect temperature-level-network
```

8. The services don't expose any ports to the host, so there are no port mappings.

9. Print the environment variables of both services:

```
docker exec temperature_sensor env | grep TEMPERATURE
docker exec temperature_monitor env | grep TEMPERATURE
```

10. Change the environment variable values by editing the docker-compose.yml file:

```
LOW_TEMPERATURE=10
MEDIUM_TEMPERATURE=25
HIGH_TEMPERATURE=40
```

11. Restart Docker Compose:

```
docker-compose down
docker-compose up --build
```

These commands should help you complete all the tasks in the assignment. Let me know if you need any clarification!
