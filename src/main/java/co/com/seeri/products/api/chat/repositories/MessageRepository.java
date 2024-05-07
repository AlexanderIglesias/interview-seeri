package co.com.seeri.products.api.chat.repositories;

import co.com.seeri.products.api.chat.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findAllByConversationId(Pageable pageable, Long conversationId);

}