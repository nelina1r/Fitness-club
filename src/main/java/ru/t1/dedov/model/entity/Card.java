package ru.t1.dedov.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE card SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
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
    private Client client;

    @ManyToMany
    @ToString.Exclude
    @JsonBackReference
    private Set<TrainingType> trainingTypes;

    private boolean deleted = Boolean.FALSE;

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
