package com.example.mock;


import com.example.mock.dto.Sms;
import com.example.mock.dto.StatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class Scheduler {
    private static final String SENDER_1 = "234100200300";
    private static final String SENDER_2 = "234100200301";
    private static final String SENDER_3 = "234100200302";
    private static final String RECIPIENT_1 = "48700800999";
    private static final String RECIPIENT_2 = "48700801000";
    private static final String RECIPIENT_3 = "48700801001";
    private static final String OK_MESSAGE = """
            Dzień dobry,
            prosimy o aktualizaję danych w serwisie google. Możesz to zrobić z poziomu aplikacji lub na
            https:google.com/login
            """;
    private static final String PHISHING_MESSAGE = """
            Dzień dobry,
            W związku z audtytem, nadzór fiansowy w naszym banku proszą o potwierdzenie danych pd adresem:
            https:www.m-bank.pl.ng/personal-data
            """;

    private static final List<Sms> smss = List.of(
            Sms.builder().sender(SENDER_1).recipient(RECIPIENT_1).message(OK_MESSAGE).build(),
            Sms.builder().sender(SENDER_2).recipient(RECIPIENT_2).message(OK_MESSAGE).build(),
            Sms.builder().sender(SENDER_3).recipient(RECIPIENT_3).message(PHISHING_MESSAGE).build()
    );
    private final SenderService senderService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduleTaskUsingCronExpression() {
        //todo could be changed to quartz
        smss.forEach(sms -> {
            StatusEnum smsStatus = senderService.sendSms(sms);
            log.info("Sent SMS from {} to {} with status {}.", sms.getSender(), sms.getRecipient(), smsStatus);
        });
    }
}
