package lv.rtustudents.projektesanasprojekts.services;

import lombok.extern.slf4j.Slf4j;
import lv.rtustudents.projektesanasprojekts.dtos.OrderDTO;
import lv.rtustudents.projektesanasprojekts.models.Order;
import lv.rtustudents.projektesanasprojekts.repositories.OrderRepo;
import lv.rtustudents.projektesanasprojekts.utils.Converter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class OrderService {

    OrderRepo orderRepo;

    OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public boolean createOrder(Order order) {
        if (order == null) {
            log.warn("CREATE ORDER | OrderDTO was null");
            return false;
        }

        try {
            orderRepo.save(order);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean deleteOrder(Long id) {
        Optional<Order> order;

        if (id == null) {
            log.warn("DELETE ORDER | Id parameter was null");
            return false;
        }

        order = orderRepo.findById(id);

        if (order.isPresent()) {
            orderRepo.delete(order.get());
            return true;
        } else {
            log.warn("DELETE ORDER | Order is not present");
            return false;
        }
    }

    public OrderDTO getOrder(Long id) {
        Optional<Order> order;

        if (id == null) {
            log.warn("GET ORDER | id parameter was null");
            return null;
        }

        order = orderRepo.findById(id);

        if (order.isPresent()) {
            return Converter.orderEntitytoDTO(order.get());
        } else {
            log.warn("GET ORDER | Order is not present");
            return null;
        }
    }


}
