package ru.evsmanko.mankoff.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evsmanko.mankoff.entity.Credit;
import ru.evsmanko.mankoff.repository.CreditRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeronikaServiceImpl implements VeronikaService {

    private final CreditRepository creditRepository;

    @Override
    public double calculateCreditByUser(long id) {
        List<Credit> credit = creditRepository.findAllByUserId(id);
        List<Double> AmountList = credit.stream().map(Credit::getAmount).collect(Collectors.toList());
        double sum = 0;
        for (int i = 0; i < AmountList.size(); i++) {
            sum += AmountList.get(i);
        }
        return sum;
    }
}
