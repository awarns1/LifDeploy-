package lifantou.com.dao;
import lifantou.com.entity.Producteur;
import lifantou.com.entity.ProduitProducteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ProduitProducteurRepository extends JpaRepository<ProduitProducteur,Long> {
    public List<ProduitProducteur> findProduitProducteurByProducteur(Producteur producteur);
    public List<ProduitProducteur> findProduitProducteurByProducteurId(Long id);
   /* public List<ProduitProducteur>findProduitProducteurByProducteurUsernameEquals(String username);*/
    public List<ProduitProducteur>findProduitProducteurByProduitId(Long id);
    public List<ProduitProducteur>findProduitProducteurByQuantiteStockGreaterThan(int seuilStock);
    public List<ProduitProducteur> findProduitProducteursByProducteurRegionZoneId(Long idZone);
    public ProduitProducteur findProduitProducteurByProducteurIdAndProduitId(Long producteur_id,Long produit_id);
    public ProduitProducteur findProduitProducteurById(Long id);
}
