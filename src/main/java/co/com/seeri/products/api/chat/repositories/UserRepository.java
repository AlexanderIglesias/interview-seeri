package co.com.seeri.products.api.chat.repositories;

import co.com.seeri.products.api.chat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

}
