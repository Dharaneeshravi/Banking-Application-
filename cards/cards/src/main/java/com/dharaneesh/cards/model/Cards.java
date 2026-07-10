package com.dharaneesh.cards.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter  @Setter  @ToString @AllArgsConstructor @NoArgsConstructor
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;


    @Column(name = "mobile_number")
    private String mobileNumber;


    @Column(name = "card_number")
    private String cardNumber;


    @Column(name = "card_type")
    private String cardType;


    @Column(name = "total_limit")
    private Integer totalLimit;


    @Column(name = "amount_used")
    private Integer amountUsed;


    @Column(name = "available_amount")
    private Integer availableAmount;

}
