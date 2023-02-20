package ru.evsmanko.mankoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.evsmanko.mankoff.entity.PaymentEntity;

import java.util.ArrayList;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    ArrayList<PaymentEntity> findAll();
    ArrayList<PaymentEntity> findAllByShopperId(long shopperId);
    PaymentEntity save(PaymentEntity payment);
}
