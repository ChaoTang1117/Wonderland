package chao.wonderland.repository;

import chao.wonderland.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface  UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT u FROM User u WHERE u.userId = ?1 ")
    User find(String userId);

}
