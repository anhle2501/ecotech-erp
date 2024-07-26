package vn.com.ecotechgroup.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String fullName;
    private String mobilePhone;
    private String description;
}
