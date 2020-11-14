package core.data.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "exchange")
@Getter
@Setter
@NoArgsConstructor
public class ExchangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "value")
    private Float value;
    @Column(name = "currency_from")
    private String currencyFrom;
    @Column(name = "currency_to")
    private String currencyTo;

    public ExchangeEntity(Long userId, Float value, String currencyFrom, String currencyTo) {
        this.userId = userId;
        this.value = value;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }
}
