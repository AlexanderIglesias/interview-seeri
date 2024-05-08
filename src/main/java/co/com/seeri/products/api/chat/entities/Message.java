package co.com.seeri.products.api.chat.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String multimediaUrl;
    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;
}
