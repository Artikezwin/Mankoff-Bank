package ru.evsmanko.mankoff.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evsmanko.mankoff.entity.Credit;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.repository.CreditRepository;
import ru.evsmanko.mankoff.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeronikaServiceImpl implements VeronikaService {

    private final CreditRepository creditRepository;
    private final UserRepository userRepository;

    @Override
    public Double calculateCreditByUser(long id) {
        List<Credit> credit = creditRepository.findAllByUserId(id);
        List<Double> amountList = credit.stream().map(Credit::getAmount).toList();
        if (!credit.isEmpty()) {
            Double sum = (double) 0;
            for (int i = 0; i < amountList.size(); i++) {
                sum += amountList.get(i);
            }
            return sum;
        }
        return null;
    }

    @Override
    public User getUserInformationById(long id) {
        return userRepository.findUserById(id);
    }
}
