package vn.com.ecotechgroup.erp.entity;

import jakarta.persistence.*;
import lombok.*;

import vn.com.ecotechgroup.erp.annotation.UniqueField;

import vn.com.ecotechgroup.erp.repository.RegionRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "region", schema = "ecotechgroup_erp", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "name")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Region implements Comparable<Region> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false, unique = true)
//    @UniqueField(repository = RegionRepository.class, fieldName = "name")
    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToMany
    @JoinTable(name = "customer_region", schema = "ecotechgroup_erp",
            joinColumns = @JoinColumn(name = "region_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    @ToString.Exclude
    private Set<Customer> customers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "region_user", schema = "ecotechgroup_erp",
            joinColumns = @JoinColumn(name = "region_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;
        return id == region.id && Objects.equals(name, region.name) && Objects.equals(description, region.description);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(description);
        return result;
    }

    @Override
    public int compareTo(Region o) {
        if (o.getId() != this.getId()) return -1;
        if (o.getName().compareTo(this.getName()) != 0) return -1;
        if (o.getDescription().compareTo(this.getDescription()) != 0) return -1;
        return 0;
    }
}
