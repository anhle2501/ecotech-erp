package vn.com.ecotechgroup.erp.entity.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import org.mapstruct.factory.Mappers;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.entity.dto.UserDTO;
import vn.com.ecotechgroup.erp.service.RoleService;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    // partial update
    @Mapping(target = "password", ignore = true) // Ignoring password field during mapping
    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);


}
