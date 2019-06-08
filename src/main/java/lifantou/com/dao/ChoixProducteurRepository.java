package lifantou.com.dao;

import lifantou.com.entity.ChoixProducteur;
import lifantou.com.entity.LigneCommande;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoixProducteurRepository extends JpaRepository<ChoixProducteur,Long> {
	List<ChoixProducteur> findChoixProducteursByLigneCommande(LigneCommande lCmd);	
	public List<ChoixProducteur> findChoixProducteursByProduitProducteurProducteurIdAndLigneCommandeEtatCmd(Long idProducteur,int etat);
	public List<ChoixProducteur> findChoixProducteursByProduitProducteurProducteurIdAndLigneCommandeEtatCmdNotIn(Long idProducteur,int etat);
	public List<ChoixProducteur> findChoixProducteursByProduitProducteurProducteurId(Long idProducteur);
	
	public List<ChoixProducteur> findChoixProducteursByLigneCommandeId(Long idLigneCmd);
	public List<ChoixProducteur> findChoixProducteursByLigneCommandeEtatPaiement(boolean etatPaiement);
	public List<ChoixProducteur> findChoixProducteursByProduitProducteurProducteurIdAndEtatPaiement(Long idLigneCmd,boolean etatPaiement);
}
