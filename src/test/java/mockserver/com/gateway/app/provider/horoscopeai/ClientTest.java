package mockserver.com.gateway.app.provider.horoscopeai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gateway.app.AppApplication;
import mockserver.com.gateway.app.MockServer;
import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


@SpringBootTest(classes = AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientTest extends MockServer {



    @BeforeEach
    public void setupMockServer() {
        mockServer = ClientAndServer.startClientAndServer(port_testing);
    }

    @AfterEach
    public void tearDownServer() {
        mockServer.stop();
    }

    @Test
    void get() throws JsonProcessingException, IOException {
        final var resource = new ClassPathResource("horoscopeai.get.daily.json");
        final var mockedResponse = Files.readString(Path.of(resource.getURI()));


        mockServer.when(
                request()
                        .withMethod(HttpMethod.GET.name())
                        .withPath("/get_horoscope/*.*/today/general/en")
        ).respond(
                response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(mockedResponse)
        );

        // var result = client.daily(EnumHoroscope.ARIES.getName().toLowerCase());
        var result = client.daily("leo");
        //   assertTrue(result.general().get(0).length()>0);


        //  var t =  communicationClient.test(url.concat(String.format(CONTEXT_MONTHLY, sign)), new HttpEntity<>(headers), String.class);
        //var t =  communicationClient.getBackendResponse("hello", "hello");

    }
}