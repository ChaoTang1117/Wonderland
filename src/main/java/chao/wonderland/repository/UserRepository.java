package chao.wonderland.repository;

import chao.wonderland.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

public interface  UserRepository extends JpaRepository<User, Integer> {

//    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query(value = "SELECT u FROM User u WHERE u.userId = ?1 ")
    User find(String userId);
}
