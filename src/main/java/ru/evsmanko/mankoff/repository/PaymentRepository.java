package ru.evsmanko.mankoff.repository;

import org.springframework.stereotype.Repository;
import ru.evsmanko.mankoff.entity.PaymentEntity;

import java.util.List;

@Repository
public interface PaymentRepository {
    List<PaymentEntity> findAll();
    List<PaymentEntity> findAllByShopperId(long shopperId);
    PaymentEntity save(PaymentEntity payment);
}
