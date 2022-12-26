package ru.evsmanko.mankoff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.evsmanko.mankoff.entity.Debit;
import ru.evsmanko.mankoff.repository.DebitRepository;

import java.util.List;

@Service
public class ArtemServiceImpl implements ArtemService {
    private final DebitRepository debitRepository;

    @Autowired
    public ArtemServiceImpl(DebitRepository debitRepository) {
        this.debitRepository = debitRepository;
    }

    @Override
    public float averageIncome(long id) {
        float sum = 0;
        List<Debit> debitList = debitRepository.findAllByUserId(id);
        for (int i = 0; i < debitList.size(); i++) {
            sum += debitList.get(i).getAmount();
        }

        return sum / debitList.size();
    }
}
