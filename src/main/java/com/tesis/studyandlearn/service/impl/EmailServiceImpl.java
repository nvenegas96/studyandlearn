package com.tesis.studyandlearn.service.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.tesis.studyandlearn.model.UserEntity;
import com.tesis.studyandlearn.model.dto.EmailDTO;
import com.tesis.studyandlearn.repository.UserRepository;
import com.tesis.studyandlearn.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private UserRepository userRepository;

    @Value("${email.from}")
    private String fromEmail;

    @Value("${email.apikey}")
    private String apiKey;

    private boolean sendEmail(EmailDTO emailDTO) {
        Email from = new Email(fromEmail);
        Email to = new Email(getUserEmail(emailDTO));
        Content content = new Content("text/plain", emailDTO.getBody());
        Mail mail = new Mail(from, emailDTO.getSubject(), to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            log.info(
                    "Send email response " +
                            response.getStatusCode() +
                            " : " +
                            response.getBody() +
                            " : " +
                            response.getHeaders()
            );
            return true;
        } catch (IOException ex) {
            log.error("Error to send email", ex);
        }
        return false;
    }

    private String getUserEmail(EmailDTO emailDTO) {
        if (emailDTO.getEmail().isEmpty()) {
            return userRepository.findById(emailDTO.getUserId().intValue()).getEmail();
        }
        return emailDTO.getEmail();
    }

    @Override
    public boolean welcomeEmail(int userId) {
        UserEntity userEntity = userRepository.findById(userId);
        String subject = "Bienvenido " + userEntity.getName() + " a Study and Learn";
        String body = "Su registro ha finalizado con éxito, para ingresar utilice como usuario su correo " + userEntity.getEmail();
        String email = userEntity.getEmail();
        return sendEmail(
                new EmailDTO(
                        null,
                        email,
                        subject,
                        body
                )
        );
    }

    @Override
    public boolean changeScheduleStatusEmail(int userId, String status, String date) {
        UserEntity userEntity = userRepository.findById(userId);
        String subject = "Cambio de estado en reserva " + date;
        String body = "Su reserva para el " + date + " ha sido actualizada a " + status;
        String email = userEntity.getEmail();
        return sendEmail(
                new EmailDTO(
                        null,
                        email,
                        subject,
                        body
                )
        );
    }

    @Override
    public boolean changeUserProfile(int userId) {
        UserEntity userEntity = userRepository.findById(userId);
        String subject = "Edición de cuenta ";
        String body = "Sus datos han sido actualizado exitosamente!";
        String email = userEntity.getEmail();
        return sendEmail(
                new EmailDTO(
                        null,
                        email,
                        subject,
                        body
                )
        );
    }
}
