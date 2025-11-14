# Exercise 03: Simple Authentication (TCP)

**Protocol:** TCP  
**Port:** 5003

## Description

The client sends a `LOGIN username password` message. The server checks the credentials.

## How to Run

```bash
javac Server.java
javac Client.java

# In terminal 1
java Server

# In terminal 

```

### Example Protocol

    Client sends: LOGIN alice 1234

    Server replies: SUCCESS or FAILURE