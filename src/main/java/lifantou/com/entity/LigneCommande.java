package lifantou.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commandes_produits")
public class LigneCommande implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String ref;
	@Temporal(TemporalType.DATE)
	private Date dateCmd;
	@Temporal(TemporalType.DATE)
	private Date dateLivraison;
	private Integer montantTotal;
	private boolean etatPaiement;
	private Integer etatCmd;
	private Integer quantite;

	@ManyToOne
	@JoinColumn(name = "ecole_id")
	@JsonIgnore
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User ecole;

	@OneToMany(mappedBy = "ligneCommande")
	@IndexColumn(name = "paiementecoles")
	private Collection<Paiementecole> paiementecoles = new ArrayList<>();
	@OneToOne
	@JoinColumn(name = "produit_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Produit produit;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<ChoixProducteur> choixProducteurList;

}
