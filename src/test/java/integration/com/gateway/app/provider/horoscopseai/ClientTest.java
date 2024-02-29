package integration.com.gateway.app.provider.horoscopseai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gateway.app.AppApplication;
import com.gateway.app.provider.horoscopeai.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientTest {


    @Autowired
    Client client;

    String url = "https://horoscopes-ai.p.rapidapi.com/get_horoscope/scorpio/weekly/general/en";
    @Test
    void get() throws JsonProcessingException {
        var result = client.daily("leo");
        assertTrue(result.length()>0);

    }
}