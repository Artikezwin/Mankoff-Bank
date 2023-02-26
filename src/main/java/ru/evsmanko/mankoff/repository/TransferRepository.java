package ru.evsmanko.mankoff.repository;

import ru.evsmanko.mankoff.entity.Transfer;

import java.util.List;

public interface TransferRepository {
    List<Transfer> findAll();

    List<Transfer> findTransfersBySenderId(long senderId);

    Transfer save(Transfer transfer);

}
