package ru.t1.dedov.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_of_purchase")
    private LocalDateTime dateOfPurchase;

    @Column(name = "date_of_expiration")
    private LocalDateTime dateOfExpiration;

    @Column(name = "price")
    private Integer price;

    @OneToOne(mappedBy = "card")
    private Client client;
}
