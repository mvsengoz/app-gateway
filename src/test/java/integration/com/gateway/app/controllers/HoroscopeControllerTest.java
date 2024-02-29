package integration.com.gateway.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gateway.app.AppApplication;
import com.gateway.app.dto.MessageDto;
import com.gateway.app.dto.SignDto;
import com.gateway.app.model.Sign;
import com.gateway.app.model.EnumSigns;
import com.gateway.app.repositories.ISignRepository;
import integration.com.gateway.app.Util;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HoroscopeControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    @Autowired
    ISignRepository signRepository;

    HttpHeaders headers ;

    ObjectMapper mapper = new ObjectMapper();

    public HoroscopeControllerTest(@Value(value = "${rest.api.auth.user}") String user,
                                   @Value(value = "${rest.api.auth.password}") String password) {
        this.headers = Util.getSecurityHeaders(user, password);
    }

    @Test
    void getAllSigns() throws IOException
    {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/gateway_s/signs"),
                HttpMethod.GET, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        List<SignDto> resultAsList = mapper.readValue(response.getBody(), new TypeReference<List<SignDto>>(){});

        Assert.assertEquals(12, resultAsList.size());
    }

    @Test
    void populateHoroscopes() throws JSONException {

        signRepository.deleteAll();

        Arrays.stream(EnumSigns.values()).map(h -> new Sign(h.getName(), h.getStartDay(), h.getEndDay()))
                .forEach(k->{
                    HttpEntity<Sign> entity = new HttpEntity<>(k, headers);

                    ResponseEntity<String> response = restTemplate.exchange(
                            createURLWithPort("/gateway_s/sign"),
                            HttpMethod.POST, entity, String.class);

                    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
                 });

    }

    @Test
    void sendMessage() throws JSONException {


        var message = new MessageDto("name", "email", "message", LocalDateTime.now());


        HttpEntity<MessageDto> entity = new HttpEntity<>(message, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/gateway_h/message"),
                HttpMethod.POST, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());


    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}