package lifantou.com.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "choix_producteur")
public class ChoixProducteur {
    @Id
    @GeneratedValue
    private Long id;
    private Integer quantite;
    private boolean etatPaiement;
    @ManyToOne
    private LigneCommande ligneCommande;
    @ManyToOne
    private ProduitProducteur produitProducteur;
    


}
