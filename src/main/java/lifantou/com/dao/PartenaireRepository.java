package lifantou.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifantou.com.entity.Partenaire;

public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
    /*
     * @Query("select somme(d.montant) from Partenaire d") public double
     * getSommeMontantPartenaire();
     */
}
