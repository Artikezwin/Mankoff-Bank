package ru.evsmanko.mankoff.service;

import ru.evsmanko.mankoff.entity.PaymentEntity;
import ru.evsmanko.mankoff.entity.User;

import java.util.ArrayList;

public interface VeronikaService {
    Double calculateCreditByUser(long id);
    User getUserInformationById(long id);
    ArrayList<PaymentEntity> getAllPayments();
    ArrayList<PaymentEntity> getAllByShopperId(long shopperId);
    PaymentEntity savePayment(PaymentEntity payment);

}
