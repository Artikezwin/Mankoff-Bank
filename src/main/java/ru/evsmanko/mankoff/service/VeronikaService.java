package ru.evsmanko.mankoff.service;

import ru.evsmanko.mankoff.entity.PaymentEntity;
import ru.evsmanko.mankoff.entity.User;

import java.util.List;

public interface VeronikaService {
    Double calculateCreditByUser(long id);
    User getUserInformationById(long id);
    List<PaymentEntity> getAllPayments();
    List<PaymentEntity> getAllByShopperId(long shopperId);
    PaymentEntity savePayment(PaymentEntity payment);

}
