package lv.rtustudents.projektesanasprojekts.controllers;

import lv.rtustudents.projektesanasprojekts.dtos.OrderDTO;
import lv.rtustudents.projektesanasprojekts.models.Order;
import lv.rtustudents.projektesanasprojekts.services.OrderService;
import lv.rtustudents.projektesanasprojekts.services.ProcessingService;
import lv.rtustudents.projektesanasprojekts.utils.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    OrderService orderService;
    ProcessingService processingService;

    OrderController(OrderService orderService, ProcessingService processingService) {
        this.orderService = orderService;
        this.processingService = processingService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderDTO orderDTO,
                                               @RequestParam Long userId) {
        Order order = Converter.orderDTOtoEntity(orderDTO);
        Boolean response = orderService.createOrder(order);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/change/status")
    public ResponseEntity<Boolean> changeOrderStatus(@RequestParam Long id, @RequestParam String status) {
        Boolean response = orderService.changeOrderStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteOrder(@RequestParam Long id) {
        Boolean response = orderService.deleteOrder(id);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/entity")
    public ResponseEntity<OrderDTO> getOrder(@RequestParam Long id) {
        OrderDTO response = orderService.getOrder(id);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders(@RequestParam Long usrId) {
        List<OrderDTO> response = orderService.getAllOrders(usrId);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/process")
    public ResponseEntity<String> processOrders() {
        String response = processingService.process();
        return ResponseEntity.ok(response);
    }
}
