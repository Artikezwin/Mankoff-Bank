package ru.evsmanko.mankoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.evsmanko.mankoff.entity.Transfer;


import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findAll();
    List<Transfer> findTransfersBySenderId(long senderId);
    Transfer save(Transfer transfer);

}
