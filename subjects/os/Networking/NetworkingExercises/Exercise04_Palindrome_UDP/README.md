# Exercise 04: Palindrome Checker (UDP)

**Protocol:** UDP  
**Port:** 5004

## Description

The server checks if a received word is a palindrome.

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

    Client sends: madam

    Server replies: Yes, it's a palindrome.

    Client sends: apple

    Server replies: No, it's not a palindrome.