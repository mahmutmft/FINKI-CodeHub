# Exercise 07: Multi-Step Registration (TCP)

**Protocol:** TCP  
**Port:** 5007

## Description

The server guides the client through a registration process:
1. Username
2. Password
3. Email (validated format)

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

    Server: Enter username:

    Client: mahmut

    Server: Enter password:

    Client: secret

    Server: Enter email:

    Client: mahmut@example.com

    Server: Registration Complete.