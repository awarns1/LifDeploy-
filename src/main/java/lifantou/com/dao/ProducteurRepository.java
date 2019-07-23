package lifantou.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifantou.com.entity.Producteur;

public interface ProducteurRepository extends JpaRepository<Producteur, Long> {
    /*
     * @Query("select somme(d.montant) from Producteur d") public double
     * getSommeMontantProducteur();
     */
}
