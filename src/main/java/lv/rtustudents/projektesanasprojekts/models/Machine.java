package lv.rtustudents.projektesanasprojekts.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Machines")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "timePerUnit")
    private BigDecimal timePerUnit;

    @Column(name = "pricePerUnit")
    private BigDecimal pricePerUnit;
}
