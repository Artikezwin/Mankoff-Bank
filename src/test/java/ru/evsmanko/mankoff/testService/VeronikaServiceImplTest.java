package ru.evsmanko.mankoff.testService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.evsmanko.mankoff.entity.Credit;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.repository.CreditRepository;
import ru.evsmanko.mankoff.service.VeronikaServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VeronikaServiceImplTest {

    @Mock
    private CreditRepository creditRepository;
    @InjectMocks
    private VeronikaServiceImpl veronikaService;

    @DisplayName("Тест для проверки сервиса по подсчёту задолженностей пользователя по его id. Ожидаемые рез-ты получены")
    @Test
    void testCalculateCreditByUser() {
        long userId = 1;
        User user = new User(userId, "Иванов", "Николай", "88005553535");
        Credit credit1 = new Credit(1, user,250000);
        Credit credit2 = new Credit(2, user,200000);
        when(creditRepository.findAllByUserId(userId)).thenReturn(List.of(credit1,credit2));
        double AmountList = veronikaService.calculateCreditByUser(userId);
        Assertions.assertEquals(AmountList,450000);
    }
}