package lv.rtustudents.projektesanasprojekts.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    @Column(name = "book_name")
    private String bookName;

    @Getter
    @Setter
    @Column(name = "amount")
    private Integer amount;

    @Getter
    @Setter
    @Column(name = "page_count")
    private Integer pageCount;

    @Getter
    @Setter
    @Column(name = "cover_type")
    private String coverType;

    @Getter
    @Setter
    @Column(name = "binding_type")
    private String bindingType;

    @Getter
    @Setter
    @Column(name = "format")
    private String format;

    @Getter
    @Setter
    @Column(name = "sizex")
    private BigDecimal sizeX;

    @Getter
    @Setter
    @Column(name = "sizey")
    private BigDecimal sizeY;

}
