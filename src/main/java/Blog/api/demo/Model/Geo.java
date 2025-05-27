package Blog.api.demo.Model;


import Blog.api.demo.Model.Audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "geo")
public class Geo extends UserDateAudit {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @OneToOne(mappedBy = "geo")
    private Address address;

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }


}

