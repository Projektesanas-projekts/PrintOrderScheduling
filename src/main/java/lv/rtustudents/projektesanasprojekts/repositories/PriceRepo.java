package lv.rtustudents.projektesanasprojekts.repositories;

import lv.rtustudents.projektesanasprojekts.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepo extends JpaRepository<Price, Long> {
}
