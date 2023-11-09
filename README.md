# API
## User actions
### Authenticate user
POST - localhost:8080/api/authenticate
```java
boolean authenticate(@RequestParam String username, @RequestParam String password);
```

Returns:<br>
- True - authentication succeeded
- False - authentication failed, incorrect username or password

### Create user
POST - localhost:8080/api/createUser
```java
boolean createUser(@RequestParam String username, @RequestParam String password);
```

Returns:<br>
- True - user creation successful
- False - user creation failed, possibly already exists

### Delete user
POST - localhost:8080/api/deleteUser
```java
boolean deleteUser(@RequestParam String username);
```

Returns:<br>
- True - user deleted successfully
- False - user deleting failed, possibly because user doesn't exist