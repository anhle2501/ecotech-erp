package vn.com.ecotechgroup.erp.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.com.ecotechgroup.erp.entity.User;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class AuditableData {

	@JsonManagedReference
	@CreatedBy
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", updatable = false)
	protected User createdBy;

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	protected LocalDateTime createdDate;

	@JsonManagedReference
	@LastModifiedBy
	@OneToOne
	@JoinColumn(name = "last_modified_by")
	protected User lastModifiedBy;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	protected LocalDateTime lastModifiedDate;

}
