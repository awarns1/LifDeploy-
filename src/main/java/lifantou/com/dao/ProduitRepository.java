package lifantou.com.dao;

import lifantou.com.entity.Produit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProduitRepository extends JpaRepository<Produit,Long> {
	List<Produit> findProduitsByStockGlobalGreaterThan(Integer stockGlobal);
	List<Produit> findProduitsByNomProduitContainingAndStockGlobalGreaterThan(String param1,Integer stockGlobal);

}
