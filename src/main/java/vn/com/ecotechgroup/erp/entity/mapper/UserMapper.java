package vn.com.ecotechgroup.erp.entity.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.com.ecotechgroup.erp.entity.Region;
import vn.com.ecotechgroup.erp.entity.Role;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.entity.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface UserMapper {
//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//    @Mapping(source = "listRole", target = "listRole", qualifiedByName = "mapRoles")
//    @Mapping(source = "regions", target = "regions", qualifiedByName = "mapRegions")
    UserDTO userToUserDTO(User user);

//    @Mapping(source = "listRole", target = "listRole", qualifiedByName = "mapRoleNames")
//    @Mapping(source = "regions", target = "regions", qualifiedByName = "mapRegionDTOs")
    User userDTOToUser(UserDTO userDTO);

    // partial update
    @Mapping(target = "password", ignore = true) // Ignoring password field during mapping
    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);

//    @Named("mapRoles")
//    default List<String> mapRoles(List<Role> roles) {
//        return roles.stream().map(Role::getName).collect(Collectors.toList());
//    }
//
//    @Named("mapRoleNames")
//    default List<Role> mapRoleNames(List<String> roleNames) {
//        return roleNames.stream().map(name -> {
//            Role role = new Role();
//            role.setName(name);
//            return role;
//        }).collect(Collectors.toList());
//    }

//    @Named("mapRegions")
//    default List<RegionDTO> mapRegions(List<Region> regions) {
//        return regions.stream().map(region -> new RegionDTO(region.getId(), region.getName(), region.getDescription())).collect(Collectors.toList());
//    }
//
//    @Named("mapRegionDTOs")
//    default List<Region> mapRegionDTOs(List<RegionDTO> regionDTOs) {
//        return regionDTOs.stream().map(dto -> new Region(dto.getId(), dto.getName(), dto.getDescription(), new HashSet<>(), new HashSet<>())).collect(Collectors.toList());
//    }
}
