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


## Order actions

### Create order
POST - localhost:8080/api/order/create
```java
boolean createOrder(@RequestParam OrderDTO orderDTO);
```
Returns:<br>
- True - success
- False - failed, possibly already exists
<br><br>

### Delete order
POST - localhost:8080/api/order/delete
```java
boolean deleteOrder(@RequestParam Long id);
```
Returns:<br>
- True - success
- False - failed, possibly doesn't exist
<br><br>

### Get order
POST - localhost:8080/api/order/entity
```java
OrderDTO getOrder(@RequestParam Long id);
```
Returns:<br>
- orderDTO - success
- null - failed, possibly entity doesn't exist
<br><br>

### Get all orders
POST - localhost:8080/api/order/all
```java
List<OrderDTO> getAllOrders();
```
Returns:<br>
- List of orderDTOs - success
- null - failed, possibly no orders exist
<br><br>

### Change order status
POST - localhost:8080/api/order/change/status
```java
Boolean changeOrderStatus(@RequestParam Long id, @RequestParam String status);
```
Returns:<br>
- True - success
- False - failed, order doesn't exist
<br><br>

### Process order
POST - localhost:8080/api/order/process
```java
String processOrder();
```

Returns:<br>
- String - success
- null - failed, possibly no orders with status Waiting
<br><br>

## Objects
### OrderDTO
```java
    private Integer amount;
    private Integer pageCount;
    private String coverType;
    private String bookName;
    private String bindingType;
    private String format;
    private BigDecimal sizeX;
    private BigDecimal sizeY;
```