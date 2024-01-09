package lv.rtustudents.projektesanasprojekts.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "cover_type")
    private String coverType;

    @Column(name = "binding_type")
    private String bindingType;

    @Column(name = "format")
    private String format;

    @Column(name = "sizex")
    private BigDecimal sizeX;

    @Column(name = "sizey")
    private BigDecimal sizeY;

    @Column(name = "status")
    private String status;

    @Column(name = "cuttingTimePer")
    private Float cuttingTimePer;

    @Column(name = "bindingTimePer")
    private Float bindingTimePer;

    @Column(name = "coveringTimePer")
    private Float coveringTimePer;
}
