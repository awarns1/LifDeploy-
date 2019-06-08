package lifantou.com.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue("PART")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Partenaire extends User {
	private static final long serialVersionUID = 1L;
	private String nomStructure;
	private String domaineActivite;
	private boolean etat;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "partenaire")
	@JsonIgnore
	private List<PartenaireEcoles> partenaireEcoleList;

	public Partenaire(String identifiant, String email, String tel, String adresse, Region region) {
		super(identifiant, email, tel, adresse, region);
	}
}
