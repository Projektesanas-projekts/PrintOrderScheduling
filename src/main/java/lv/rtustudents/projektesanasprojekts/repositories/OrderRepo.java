package lv.rtustudents.projektesanasprojekts.repositories;

import lv.rtustudents.projektesanasprojekts.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
