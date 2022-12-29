package ru.evsmanko.mankoff.testController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.evsmanko.mankoff.controller.VeronikaController;
import ru.evsmanko.mankoff.service.VeronikaServiceImpl;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VeronikaControllerUnitTest {

    @Mock
    private VeronikaServiceImpl veronikaService;
    @InjectMocks
    private VeronikaController veronikaController;

    @DisplayName("Unit-тест для проверки работы контроллера. Ожидаемые рез-ты получены")
    @Test
    public void testVeronikaController_CalculateCreditByUser() {
        long userId = 1;
        when(veronikaService.calculateCreditByUser(userId)).thenReturn(450000.0);
        ResponseEntity<Double> amount = veronikaController.calculateCreditByUser(userId);
        Assertions.assertEquals(amount, new ResponseEntity<>(450000.0, HttpStatus.OK));
    }
}