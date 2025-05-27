package Blog.api.demo.Model;



import Blog.api.demo.Model.Audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "company")
public class Company extends UserDateAudit {
    private static final long serialVersionUID = 1L;

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "catch_phrase")
    private String catchPhrase;

    @Column(name = "bs")
    private String bs;

    @OneToOne(mappedBy = "company")
    private User user;


    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }


}
