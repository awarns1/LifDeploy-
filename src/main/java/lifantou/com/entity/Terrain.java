package lifantou.com.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "terrains")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Terrain implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    private Long id;
    private String description;
    private String typeProduction;
    private double longitude;
    private double latitude;
    @ManyToOne
    @JoinColumn(name = "producteur_id")
    private Producteur producteur;

}
