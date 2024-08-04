package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Check;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.com.ecotechgroup.erp.audit.AuditableData;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_type", schema = "ecotechgroup_erp")
@Getter
@Setter
@SuperBuilder
public class PaymentType extends AuditableData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@NotBlank(message = "Không được để trống!")
	@Column(length = 45)
	private String name;

	@Column(length = 1000)
	@Size(max = 1000, message = "Độ dài quá 1000 ký tự !")
	private String description;

	@Column(name="debt_day", columnDefinition = "integer default 0")
	@Max(value = 255, message = "Nợ tối đa 255 ngày!")
	@Min(value = 0, message = "Nợ tối thiểu 0")
	private int debtDay;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PaymentType that = (PaymentType) o;
		return id == that.id && debtDay == that.debtDay && Objects.equals(name, that.name) && Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {
		int result = Long.hashCode(id);
		result = 31 * result + Objects.hashCode(name);
		result = 31 * result + Objects.hashCode(description);
		result = 31 * result + debtDay;
		return result;
	}
}
