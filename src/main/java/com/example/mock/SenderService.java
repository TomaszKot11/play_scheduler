package com.example.mock;

import com.example.mock.dto.Sms;
import com.example.mock.dto.StatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class SenderService {
    private final RestTemplate restTemplate;

    public StatusEnum sendSms(Sms sms) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity<Sms> httpEntity = new HttpEntity<>(sms, headers);
        return restTemplate.exchange("http://localhost:8082/send", POST, httpEntity, StatusEnum.class).getBody();
    }

}
