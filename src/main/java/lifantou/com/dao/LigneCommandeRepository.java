package lifantou.com.dao;

import lifantou.com.entity.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande,Long> {
	public LigneCommande findLigneCommandeByRef(String ref);
	//All cmd pas encours(anterieur)
	//public List<LigneCommande> findLigneCommandeByEtatCmdIsNotInAndEtatPaiement(int etatcmd,boolean etatPaiement);
	public List<LigneCommande> findLigneCommandeByEtatCmdIsNotIn(int etatcmd);
	//All cmd encours
	public List<LigneCommande> findLigneCommandeByEtatCmd(int etatcmd);
	
	//cmd ecole pas encours(anterieur)
	public List<LigneCommande> findLigneCommandeByEcoleIdAndEtatCmdIsNotIn(Long idEcole,int etatcmd);
	//cmd ecole encours
	public List<LigneCommande> findLigneCommandeByEcoleIdAndEtatCmd(Long idEcole,int etatCmd);
	
	public List<LigneCommande> findLigneCommandeByEtatPaiement(boolean etatPaiement);
	public List<LigneCommande> findLigneCommandeByEcoleIdAndEtatPaiement(Long idEcole,boolean etatPaiement);
	
	public List<LigneCommande> findLigneCommandeByEcoleId(Long idEcole);
}
