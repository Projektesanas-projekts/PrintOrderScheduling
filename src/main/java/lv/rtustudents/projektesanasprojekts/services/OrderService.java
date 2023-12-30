package lv.rtustudents.projektesanasprojekts.services;

import lombok.extern.slf4j.Slf4j;
import lv.rtustudents.projektesanasprojekts.dtos.OrderDTO;
import lv.rtustudents.projektesanasprojekts.models.Order;
import lv.rtustudents.projektesanasprojekts.repositories.OrderRepo;
import lv.rtustudents.projektesanasprojekts.utils.Constants;
import lv.rtustudents.projektesanasprojekts.utils.Converter;
import lv.rtustudents.projektesanasprojekts.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        if (order.getPageCount() < Constants.MINIMUM_PAGE_COUNT) {
            log.warn("CREATE ORDER | Page count was less than minimum");
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

    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
        List<Order> orders = orderRepo.findAll();

        for (Order order : orders) {
            orderDTOs.add(Converter.orderEntitytoDTO(order));
        }

        return orderDTOs;
    }

    public boolean changeOrderStatus(Long id, String status) {
        Optional<Order> order = orderRepo.findById(id);

        if (!Validator.validateStatus(status)) {
            log.warn("CHANGE ORDER STATUS | Status was not valid");
            return false;
        }

        if (order.isPresent()) {
            order.get().setStatus(status);
            orderRepo.save(order.get());
            return true;
        } else {
            log.warn("CHANGE ORDER STATUS | Order with id " + id + " is not present");
            return false;
        }
    }
}
