package lv.rtustudents.projektesanasprojekts.dtos;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDTO {

    private long id;
    private Long userId;
    private Integer amount;
    private Integer pageCount;
    private String coverType;
    private String bookName;
    private String bindingType;
    private String format;
    private BigDecimal sizeX;
    private BigDecimal sizeY;
    private String status;
}
