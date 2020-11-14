package core.data.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ExchangeRequest {
    private Long userId;
    private Float value;
    private String currencyFrom;
    private String currencyTo;
}
