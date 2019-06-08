package lifantou.com.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produits")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produit implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String nomProduit;
	private Integer prixUnitaire;
	private String unite;
	@Column(nullable = true)
	private Integer stockGlobal;
	@Lob
	@JsonIgnore
	private byte[] photoBytes;
	@Size(max = 100)
	@Column(name = "original_name")
	private String originalName;
	private Integer productionNat;

	@OneToMany
	@JsonIgnore
	private List<ProduitProducteur> produitProducteur = new ArrayList<>();

}
