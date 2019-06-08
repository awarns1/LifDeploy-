package lifantou.com.entity;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "regions")
@Data
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    private Long id;
    private String nomRegion;
    private String sigle;
    @ManyToOne
    @JoinColumn(name = "zone_id")
    @JsonIgnore
    private Zone zone;
    @OneToMany(mappedBy="region",fetch=FetchType.LAZY)
    @JsonIgnore
    private Collection<User> users=new ArrayList<>();
}
