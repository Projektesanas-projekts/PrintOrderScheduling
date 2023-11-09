# API
## User actions
### Authenticate user
POST - localhost:8080/api/user/authenticate
```java
boolean authenticate(@RequestParam String username, @RequestParam String password);
```

Returns:<br>
- True - authentication succeeded
- False - authentication failed, incorrect username or password
<br><br>
### Create user
POST - localhost:8080/api/user/create
```java
boolean createUser(@RequestParam String username, @RequestParam String password);
```

Returns:<br>
- True - user creation successful
- False - user creation failed, possibly already exists
<br><br>
### Delete user
POST - localhost:8080/api/user/delete
```java
boolean deleteUser(@RequestParam String username);
```

Returns:<br>
- True - user deleted successfully
- False - user deleting failed, possibly because user doesn't exist
<br><br>
### Get user entity
POST - localhost:8080/api/user/entity
```java
boolean getUser(@RequestParam String username);
```

Returns:<br>
- User entity - success
- Null - No such user exists
<br><br>