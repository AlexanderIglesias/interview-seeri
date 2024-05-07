package co.com.seeri.products.api.chat.repositories;

import co.com.seeri.products.api.chat.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    List<Conversation> findByUsersId(Long userId);

}
