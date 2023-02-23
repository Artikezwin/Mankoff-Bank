package ru.evsmanko.mankoff.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class PaymentDTO {
    private long id;
    private long shopperId;
    private long MCC;
    private BigDecimal amount;
    private Timestamp timeStamp;
}
