package lv.rtustudents.projektesanasprojekts.utils;

import lv.rtustudents.projektesanasprojekts.dtos.OrderDTO;
import lv.rtustudents.projektesanasprojekts.models.Order;

public class Converter {
    public static Order orderDTOtoEntity(OrderDTO orderDTO) {
        Order order = new Order();

        order.setId(orderDTO.getId());
        order.setUserId(orderDTO.getUserId());
        order.setBookName(orderDTO.getBookName());
        order.setAmount(orderDTO.getAmount());
        order.setPageCount(orderDTO.getPageCount());
        order.setCoverType(orderDTO.getCoverType());
        order.setBindingType(orderDTO.getBindingType());
        order.setFormat(orderDTO.getFormat());
        order.setSizeX(orderDTO.getSizeX());
        order.setSizeY(orderDTO.getSizeY());
        order.setStatus(orderDTO.getStatus());

        return order;
    }

    public static OrderDTO orderEntitytoDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setBookName(order.getBookName());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setPageCount(order.getPageCount());
        orderDTO.setCoverType(order.getCoverType());
        orderDTO.setBindingType(order.getBindingType());
        orderDTO.setFormat(order.getFormat());
        orderDTO.setSizeX(order.getSizeX());
        orderDTO.setSizeY(order.getSizeY());
        orderDTO.setStatus(order.getStatus());

        return orderDTO;
    }
}
