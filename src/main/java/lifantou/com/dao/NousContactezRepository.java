package lifantou.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lifantou.com.entity.NousContactez;

public interface NousContactezRepository extends JpaRepository<NousContactez, Long> {
}
