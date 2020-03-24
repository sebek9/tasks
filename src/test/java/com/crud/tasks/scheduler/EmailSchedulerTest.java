package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Test
    public void sendInformationEmail() {
        long size = 2;
        String link = " task";
        if(size > 1) {
            link = " tasks";
        }

        Mail mail = new Mail("sebastian.kalinkowski@gmail.com", "Tasks: Once a day email",
                "Currently in database you got: " + size + link,null);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        simpleEmailService.send(mail);

        verify(javaMailSender, times(1)).send(ArgumentMatchers.any(MimeMessagePreparator.class));
    }
}
