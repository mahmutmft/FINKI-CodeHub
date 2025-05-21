# Exercise 02: Math Quiz (TCP)

**Protocol:** TCP  
**Port:** 5002

## Description

The server sends a math question to the client. The client must answer, and the server responds with feedback.

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

    Server sends: What is 6 + 7?

    Client replies: 13

    Server responds: Correct! or Incorrect.