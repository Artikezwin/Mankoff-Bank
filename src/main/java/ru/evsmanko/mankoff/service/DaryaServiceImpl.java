package ru.evsmanko.mankoff.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ru.evsmanko.mankoff.entity.Debit;
import ru.evsmanko.mankoff.repository.DebitRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class DaryaServiceImpl implements DaryaService {
    private DebitRepository debitRepository;
    private UserRepository userRepository;

    @Override
    public double debitAccountsBalance() {
        double overallBalance = 0;
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {
            overallBalance += userDebitAccountBalance(userList[i].getId());
        }
        return overallBalance;
    }

    public double userDebitAccountBalance(long id) {
        double sum = 0;
        List<Debit> debitList = debitRepository.findAllByUserId(id);
        for (int i = 0; i < debitList.size(); i++) {
            sum += debitList.get(i).getAmount();
        }
        return sum;
    }
}

