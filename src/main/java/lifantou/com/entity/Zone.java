package lifantou.com.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name ="zones")
@Data
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Zone implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    private Long id;
    private String libelle;
    @OneToMany(mappedBy="zone",fetch=FetchType.LAZY)
    private Collection<Region> regions=new ArrayList<>();
}
