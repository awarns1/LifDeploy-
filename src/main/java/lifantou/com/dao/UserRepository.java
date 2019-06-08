package lifantou.com.dao;

import lifantou.com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

/*    @Query("select u from User u where u.confirmation_token= :x")
    public abstract User findUserByConfirmationToken(@Param("x") String paramString);
*/
    /*@Query(value = "",nativeQuery = true)
    public abstract User UserBycommandeid(@Param("x") String commande_id);*/
//    public User findUserByUsername(String username);

	User findByEmail(String email);

}
