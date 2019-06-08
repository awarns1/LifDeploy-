package lifantou.com.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "produit_producteur")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProduitProducteur implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private Integer quantiteStock;
	@Temporal(TemporalType.DATE)
	private Date dateProduction;

	@ManyToOne
	@JoinColumn(name = "produit_id")
	private Produit produit;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producteur_id")
	private User producteur;

}
