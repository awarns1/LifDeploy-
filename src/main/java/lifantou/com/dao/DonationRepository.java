package lifantou.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifantou.com.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long>{
	/*@Query("select somme(d.montant) from Donation d")
    public double getSommeMontantDonation();*/
	
	Donation findDonationByToken(String token);
}
