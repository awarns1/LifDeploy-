package lifantou.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "paiments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Paiementecole implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private Date datePaiement;
	private double montantVerse;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ligneCmd_id")
	@JsonIgnore
	private LigneCommande ligneCommande;

}
