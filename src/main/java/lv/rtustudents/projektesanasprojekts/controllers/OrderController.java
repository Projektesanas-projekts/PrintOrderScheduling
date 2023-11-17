package lv.rtustudents.projektesanasprojekts.controllers;

import lv.rtustudents.projektesanasprojekts.dtos.OrderDTO;
import lv.rtustudents.projektesanasprojekts.models.Order;
import lv.rtustudents.projektesanasprojekts.services.OrderService;
import lv.rtustudents.projektesanasprojekts.utils.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity<Boolean> createOrder(@RequestParam OrderDTO orderDTO) {
        Order order = Converter.orderDTOtoEntity(orderDTO);
        Boolean response = orderService.createOrder(order);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteOrder(@RequestParam Long id) {
        Boolean response = orderService.deleteOrder(id);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/entity")
    public ResponseEntity<OrderDTO> getOrder(@RequestParam Long id) {
        OrderDTO response = orderService.getOrder(id);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> response = orderService.getAllOrders();
        return ResponseEntity.ok(response);
    }
}
