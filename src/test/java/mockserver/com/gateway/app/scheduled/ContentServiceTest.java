package mockserver.com.gateway.app.scheduled;

import com.gateway.app.AppApplication;
import com.gateway.app.scheduled.ContentService;
import com.gateway.app.services.HoroscopeDailyService;
import com.gateway.app.services.HoroscopeMonthlyService;
import com.gateway.app.services.HoroscopeWeeklyService;
import mockserver.com.gateway.app.MockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContentServiceTest extends MockServer {

    @Autowired
    private ContentService contentService;

    @Autowired
    private HoroscopeDailyService horoscopeDailyService;

    @Autowired
    private HoroscopeWeeklyService horoscopeWeeklyService;

    @Autowired
    private HoroscopeMonthlyService horoscopeMonthlyService;

    @BeforeEach
    public void setupMockServer() {
        mockServer = ClientAndServer.startClientAndServer(port_testing);
    }

    @AfterEach
    public void tearDownServer() {
        mockServer.stop();
    }

    @Test
    void acquireContentDaily() throws IOException, InterruptedException {
        final var resource = new ClassPathResource("horoscopeai.get.daily.json");
        final var mockedResponse = Files.readString(Path.of(resource.getURI()));

        //horoscopeDailyService.deleteAll();

        // daily
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


        contentService.acquireDailyContent();

        Thread.sleep(10000);
    }

    @Test
    void acquireContentWeekly() throws IOException, InterruptedException {
        final var resource = new ClassPathResource("horoscopeai.get.weekly.json");
        final var mockedResponse = Files.readString(Path.of(resource.getURI()));

       // horoscopeWeeklyService.deleteAll();

        // weekly
        mockServer.when(
                request()
                        .withMethod(HttpMethod.GET.name())
                        .withPath("/get_horoscope/*.*/weekly/general/en")
        ).respond(
                response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(mockedResponse)
        );


        contentService.acquireWeeklyContent();


        Thread.sleep(10000);
    }

    @Test
    void acquireContentMonthly() throws IOException, InterruptedException {
        final var resource = new ClassPathResource("horoscopeai.get.monthly.json");
        final var mockedResponse = Files.readString(Path.of(resource.getURI()));

        //horoscopeMonthlyService.deleteAll();

        // monthly
        mockServer.when(
                request()
                        .withMethod(HttpMethod.GET.name())
                        .withPath("/get_horoscope/*.*/monthly/general/en")
        ).respond(
                response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(mockedResponse)
        );


        contentService.acquireMonthlyContent();

        Thread.sleep(10000);
    }


}