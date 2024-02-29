package integration.com.gateway.app.controllers;

import com.gateway.app.AppApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HoroscopeControllerRXTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    void handsetInfo() throws JSONException, InterruptedException {

        String[] k = new String[1000000];

        for(int i = 0 ; i<k.length ; i++){
            k[i] = ""+i;
        }
        List<String> t = Arrays.asList(k);

        var observable = Observable.from(t);

        var result = observable.subscribeOn(Schedulers.io())
                .doOnCompleted(() -> System.out.println("completed"))
                .subscribe(word -> System.out.println(word + " , ThreadName : " + Thread.currentThread().getName()));


        for(int i =0 ; i<100 ; i++){
            Thread.sleep(1000);
            if(result.isUnsubscribed()){
                System.exit(0);
            }
        }


    }

    @Test
    void capabilities() {
    }

    @Test
    void image() {
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}