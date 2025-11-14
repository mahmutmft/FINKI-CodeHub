# Exercise 05: Command Dispatcher (TCP)

**Protocol:** TCP  
**Port:** 5005

## Description

The server accepts the following commands:
- `TIME`
- `DATE`
- `UPPER <text>`
- `LOWER <text>`

## How to Run

```bash
javac Server.java
javac Client.java

# In terminal 1
java Server

# In terminal 2
java Client
```

### Example Protocol

    Client sends: UPPER hello world

    Server replies: HELLO WORLD

    Client sends: TIME

    Server replies: 13:57:08