package vn.com.ecotechgroup.erp.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import vn.com.ecotechgroup.erp.entity.Region;
import vn.com.ecotechgroup.erp.entity.Role;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {

    private long id;
    @NotBlank(message = "Không để trống!")
    @Length(max = 45, message = "Ít hơn 45 ký tự!")
    private String userName;
    @Length(max = 45, message = "Ít hơn 45 ký tự!")
    private String firstName;
    @Length(max = 45, message = "Ít hơn 45 ký tự!")
    private String lastName;
    private String fullName;
    @Length(max = 45, message = "Ít hơn 45 ký tự!")
    private String mobilePhone;
    @Length(max = 1000, message = "Ít hơn 1000 ký tự!")
    private String description;
    private boolean enable;
    private boolean nonExpired;
    private boolean nonLock;
    private boolean pwNonExpired;
    private List<Role> listRole;
    private List<Region> regions;

}
