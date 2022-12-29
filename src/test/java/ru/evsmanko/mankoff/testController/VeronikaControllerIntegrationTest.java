package ru.evsmanko.mankoff.testController;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.evsmanko.mankoff.controller.VeronikaController;
import ru.evsmanko.mankoff.service.VeronikaServiceImpl;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VeronikaController.class)
class VeronikaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VeronikaServiceImpl veronikaService;

    @DisplayName("Интеграционный тест для проверки работы контроллера. Ожидаемые рез-ты получены")
    @Test
    public void testVeronikaController_CalculateCreditByUser_thenStatus200() throws Exception {
        long userId = 1;
        when(veronikaService.calculateCreditByUser(userId)).thenReturn(Double.valueOf("450000.0"));
        mockMvc.perform(get("/calculate/credits?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("450000.0")));
    }
}

    /*@DisplayName("Интеграционный тест для проверки исключения")
    @Test
    public void testVeronikaController_CalculateCreditByUser_thenStatus404() throws Exception {
        long userId = 1;
        when(veronikaService.calculateCreditByUser(userId)).thenReturn(Double.valueOf("450000"));
        mockMvc.perform(get("/calculate/credits?id=2"))
                .andExpect(status().isNotFound());
    } */
