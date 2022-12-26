package ru.evsmanko.mankoff.testService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.evsmanko.mankoff.controller.ArtemController;
import ru.evsmanko.mankoff.entity.Debit;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.repository.DebitRepository;
import ru.evsmanko.mankoff.service.ArtemServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArtemTest {
    @Mock
    private DebitRepository debitRepository;
    @InjectMocks
    private ArtemServiceImpl artemService;
    @Mock
    private ArtemServiceImpl artemServiceForController;
    @InjectMocks
    private ArtemController artemController;
    private final long USER_ID = 2;
    private List<Debit> debitList;

    @DisplayName("Тест сервиса")
    @Test
    public void testSetvice() {
        debitList = List.of(
                new Debit(USER_ID, new User(USER_ID, "Артём", "Слуцкий", "12098"), 100),
                new Debit(USER_ID, new User(USER_ID, "Артём", "Слуцкий", "12098"), 256)
        );
        when(debitRepository.findAllByUserId(USER_ID)).thenReturn(debitList);
        assertEquals(artemService.averageIncome(USER_ID), (100 + 256) / 2.0);
    }

    @DisplayName("Тест контроллера")
    @Test
    public void testController() {
        debitList = List.of(
                new Debit(USER_ID, new User(USER_ID, "Артём", "Слуцкий", "12098"), 100),
                new Debit(USER_ID, new User(USER_ID, "Артём", "Слуцкий", "12098"), 256)
        );
        when(debitRepository.findAllByUserId(USER_ID)).thenReturn(debitList);

        float average = artemService.averageIncome(USER_ID);
        when(artemServiceForController.averageIncome(USER_ID)).thenReturn(
                average
        );
        ResponseEntity<Float> response = artemController.getAverageIncome(USER_ID);
        assertEquals(response, new ResponseEntity<>(artemServiceForController.averageIncome(USER_ID), HttpStatus.OK));
    }
}
