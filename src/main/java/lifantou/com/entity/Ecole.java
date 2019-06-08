package lifantou.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ECOL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ecole extends User {
	private static final long serialVersionUID = 1L;
	private String nomEcole;
	private String departement;
	private double longitudeEcole;
	private double latitudeEcole;
	private Integer nbEleve;
	private String nomGest;
	private String prenomGest;
	private String activiteGest;
	private Integer budgetAnnuel;

	public Ecole(String identifiant, String email, String tel, String adresse, Region region) {
		super(identifiant, email, tel, adresse, region);
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ecole")
	@JsonIgnore
	private List<PartenaireEcoles> partenaireEcoleList;

	@OneToMany(mappedBy = "ecole", fetch = FetchType.EAGER)
	@JsonIgnore
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	// @LazyCollection(LazyCollectionOption.FALSE)
	private List<LigneCommande> ligneCommande;
}
