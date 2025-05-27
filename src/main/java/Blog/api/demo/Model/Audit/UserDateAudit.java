package Blog.api.demo.Model.Audit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;


@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@JsonIgnoreProperties(
        value = { "createdBy", "updatedBy" }, // fix casing here
        allowGetters = true
)
public abstract class UserDateAudit extends DateAudit {
    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(updatable = false)
    @JsonIgnore
    private Long createdBy;

    @LastModifiedBy
    @JsonIgnore
    private Long updatedBy;
}

