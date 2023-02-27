package ru.evsmanko.mankoff.service.mapper;

import org.mapstruct.Mapper;
import ru.evsmanko.mankoff.dto.PaymentDTO;
import ru.evsmanko.mankoff.entity.PaymentEntity;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDTO paymentToPaymentDto(PaymentEntity paymentEntity);
    PaymentEntity paymentDtoToPayment(PaymentDTO paymentDTO);
}
