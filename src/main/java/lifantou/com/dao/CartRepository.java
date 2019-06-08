package lifantou.com.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lifantou.com.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	public List<Cart> findCartByEcoleId(Long id);
}
