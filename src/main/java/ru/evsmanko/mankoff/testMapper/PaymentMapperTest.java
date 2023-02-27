package ru.evsmanko.mankoff.testMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.evsmanko.mankoff.dto.PaymentDTO;
import ru.evsmanko.mankoff.entity.PaymentEntity;
import ru.evsmanko.mankoff.service.mapper.PaymentMapper;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;

    @DisplayName("Тест на работу маппера (из entity-сущности в DTO). Ожидаемый результат получен")
    @Test
    public void testPaymentToPaymentDto() {
        PaymentEntity payment = new PaymentEntity(
                1L,
                2L,
                4526,
                new BigDecimal("452.30"),
                new Timestamp(currentTimeMillis())
        );
        PaymentDTO paymentDTO = paymentMapper.paymentToPaymentDto(payment);
        assertNotNull(paymentDTO);
        assertEquals(payment.getId(), paymentDTO.getId());
        assertEquals(payment.getShopperId(), paymentDTO.getShopperId());
        assertEquals(payment.getMCC(), paymentDTO.getMCC());
        assertEquals(payment.getAmount(), paymentDTO.getAmount());
        assertEquals(payment.getTimeStamp(), paymentDTO.getTimeStamp());
    }

    @DisplayName("Тест на работу маппера (из DTO в entity-сущность). Ожидаемый результат получен")
    @Test
    public void testPaymentDtoToPayment() {
        PaymentDTO paymentDto = new PaymentDTO(
                2L,
                3L,
                4545,
                new BigDecimal("120000.10"),
                new Timestamp(currentTimeMillis())
        );
        PaymentEntity payment1 = paymentMapper.paymentDtoToPayment(paymentDto);
        assertNotNull(payment1);
        assertEquals(paymentDto.getId(), payment1.getId());
        assertEquals(paymentDto.getShopperId(), payment1.getShopperId());
        assertEquals(paymentDto.getMCC(), payment1.getMCC());
        assertEquals(paymentDto.getAmount(), payment1.getAmount());
        assertEquals(paymentDto.getTimeStamp(), payment1.getTimeStamp());
    }
}
