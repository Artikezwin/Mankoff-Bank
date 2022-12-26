package ru.evsmanko.mankoff.testService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.evsmanko.mankoff.controller.ArtemController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArtemRestTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ArtemController controller;
    @Test
    public void test() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("hello")));
    }
    /*@Test
    @DisplayName("Интеграционный тест контроллера")
    public void test(){
        Debit debit= given()
                .spec(new RequestSpecBuilder().setPort(port)
                        .setBasePath("/Artem")
                        .setBaseUri(URL).build())
                .get("/average").then().statusCode(HttpStatus.OK.value())
                .extract().as(Debit.class);

        assertEquals(debit.getUser().getFirstName(), "");
        /*assertEquals(debitList.get(0).getUser().getFirstName(), "");
        assertEquals(debitList.get(0).getUser().getLastName(), "");
        assertEquals(debitList.get(0).getUser().getPhone(), "");
    }*/
}
