package lifantou.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifantou.com.entity.FaireUnDon;

public interface FaireUnDonRepository extends JpaRepository<FaireUnDon, Long> {
    /*
     * @Query("select somme(d.montant) from FaireUnDon d") public double
     * getSommeMontantFaireUnDon();
     */

    FaireUnDon findFaireUnDonByToken(String token);
}
