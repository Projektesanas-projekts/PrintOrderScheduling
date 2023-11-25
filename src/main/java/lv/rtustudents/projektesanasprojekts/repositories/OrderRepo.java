package lv.rtustudents.projektesanasprojekts.repositories;

import lv.rtustudents.projektesanasprojekts.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    public List<Order> findAllByStatus(String status);
}
