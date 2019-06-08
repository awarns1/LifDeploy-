package lifantou.com.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("PROD")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producteur extends User {
	private static final long serialVersionUID = 1L;
	private String nomProduct;
	private String prenomProduct;
	private double longitudeProduct;
	private double latitudeProduct;

	public Producteur(String identifiant, String email, String tel, String adresse, Region region) {
		super(identifiant, email, tel, adresse, region);
	}

	@OneToMany(mappedBy = "producteur", fetch = FetchType.LAZY)
	private Collection<Terrain> terrains = new ArrayList<>();	

}
