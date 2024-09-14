package vn.com.ecotechgroup.erp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.OrderProduct;
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private long id;
    private LocalDateTime createAt;
    private List<OrderProduct> orderProduct = new ArrayList<>();
    private Customer customer = new Customer();
    private PaymentType paymentType = new PaymentType();
    private String description;
    private User userModified;
    private LocalDateTime last_modified_date;
    private long totalPrice;
    private User userOrdered;
    private LocalDateTime confirmAt;
    private User confirmByUser;

}
