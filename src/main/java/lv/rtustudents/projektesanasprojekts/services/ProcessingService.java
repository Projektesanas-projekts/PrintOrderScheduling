package lv.rtustudents.projektesanasprojekts.services;

import lombok.extern.slf4j.Slf4j;
import lv.rtustudents.projektesanasprojekts.models.Order;
import lv.rtustudents.projektesanasprojekts.repositories.OrderRepo;
import lv.rtustudents.projektesanasprojekts.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProcessingService {

    OrderRepo orderRepo;

    ProcessingService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public boolean process() {
        log.info("Processing started");
        List<Order> orders = orderRepo.findAllByStatus(Constants.STATUS_WAITING);

        if (orders.isEmpty()) {
            log.warn("PROCESS ORDERS | No orders in waiting status");
            return false;
        }

        // TODO: Algorithm to maximize profit using orders and machinery prices

        log.info("Processing finished");
        return true;
    }
}
