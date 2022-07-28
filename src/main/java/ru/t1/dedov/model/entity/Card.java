package ru.t1.dedov.model.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_of_purchase", nullable = false)
    private LocalDateTime dateOfPurchase;

    @Column(name = "date_of_expiration", nullable = false)
    private LocalDateTime dateOfExpiration;

    @Column(name = "price", scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne
    @NotNull
    private Client client;

    @ManyToMany
    @ToString.Exclude
    private Set<TrainingType> trainingTypes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Card card = (Card) o;
        return id != null && Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
