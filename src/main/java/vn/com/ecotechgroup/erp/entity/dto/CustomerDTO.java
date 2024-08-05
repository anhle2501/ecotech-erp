package vn.com.ecotechgroup.erp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private long id;
    private String code;
    private String name;
    private String address;
    private String phone;
    private String taxCode;
    private String description;
    private UserDTO createdBy;
    private LocalDateTime createdDate;
    private UserDTO idUserBelong;
}
