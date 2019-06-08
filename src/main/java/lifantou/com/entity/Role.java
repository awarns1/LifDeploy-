package lifantou.com.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    private Long id;
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRole")
    private List<AccesApp> accesAppList;

}
