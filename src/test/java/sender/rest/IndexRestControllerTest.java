package sender.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import sender.core.request.SendPaymentRequest;
import sender.core.response.SendPaymentResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IndexRestControllerTest {

    @Test
    void shouldSend() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080";
        SendPaymentRequest request = new SendPaymentRequest(
                "payer",
                "receiver",
                "vecajurmalasgatve1b@gmail.com",
                "99.99",
                "description");
        HttpEntity<SendPaymentRequest> httpRequest = new HttpEntity<>(request);
        SendPaymentResponse response = restTemplate.postForObject(url, httpRequest, SendPaymentResponse.class);
        assertNotNull(response);
        assertNull(response.getErrors());
    }

    @Test
    void shouldNotSend() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080";
        SendPaymentRequest request = new SendPaymentRequest(
                "  ",
                "receiver",
                "mail",
                "amount",
                "");
        HttpEntity<SendPaymentRequest> httpRequest = new HttpEntity<>(request);
        SendPaymentResponse response = restTemplate.postForObject(url, httpRequest, SendPaymentResponse.class);
        assertNotNull(response);
        assertEquals(3, response.getErrors().size());
        assertNotNull(response.getErrors());
    }

}
