# API
## Create user
POST - localhost:8080/api/createUser
```java
boolean createUser(@RequestParam String username, @RequestParam String password);
```

Returns:<br>
- True - user creation successful
- False - user creation failed, possibly already exists

## Authenticate user
POST - localhost:8080/api/authenticate
```java
boolean authenticate(@RequestParam String username, @RequestParam String password);
```

Returns:<br>
- True - authentication succeeded
- False - authentication failed, incorrect username or password