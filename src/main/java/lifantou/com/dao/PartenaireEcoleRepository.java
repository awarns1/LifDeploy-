package lifantou.com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lifantou.com.entity.PartenaireEcoles;

public interface PartenaireEcoleRepository extends JpaRepository<PartenaireEcoles, Long> {
	List<PartenaireEcoles> findPartenaireEcolesByPartenaireId(Long idPartenaire);
	List<PartenaireEcoles> findPartenaireEcolesByEcoleId(Long idEcole);
	PartenaireEcoles findPartenaireEcolesByEcoleIdAndPartenaireId(Long idEcole,Long idPartenaire);

}
