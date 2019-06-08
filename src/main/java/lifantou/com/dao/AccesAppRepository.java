package lifantou.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import lifantou.com.entity.AccesApp;

public interface AccesAppRepository extends JpaRepository<AccesApp, Long>{
	AccesApp findByUsername(String username);
	AccesApp findByToken(String token);
	AccesApp findByIdUserId(Long id);
}
