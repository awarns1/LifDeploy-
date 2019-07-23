package lifantou.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifantou.com.entity.Ecole;

public interface EcoleRepository extends JpaRepository<Ecole, Long> {
    /*
     * @Query("select somme(d.montant) from ecole d") public double
     * getSommeMontantecole();
     */
}
