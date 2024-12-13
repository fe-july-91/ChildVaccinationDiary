package team.project.smtp;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class MultiMailService {
    private final JavaMailSender gmailMailSender;
    private final JavaMailSender onlineMailSender;
    private final JavaMailSender mailUaMailSender;
    private final JavaMailSender ukrNetMailSender;
    private final JavaMailSender iuamailSender;
    private final JavaMailSender metaUaMailSender;
    private final JavaMailSender yahooMailSender;
    private final JavaMailSender outlookMailSender;

    public MultiMailService(
            @Qualifier("gmailMailSender") JavaMailSender gmailMailSender,
            @Qualifier("onlineMailSender") JavaMailSender onlineMailSender,
            @Qualifier("mailUaMailSender") JavaMailSender mailUaMailSender,
            @Qualifier("ukrNetMailSender") JavaMailSender ukrNetMailSender,
            @Qualifier("iUaMailSender") JavaMailSender iuamailSender,
            @Qualifier("metaUaMailSender") JavaMailSender metaUaMailSender,
            @Qualifier("yahooMailSender") JavaMailSender yahooMailSender,
            @Qualifier("outlookMailSender") JavaMailSender outlookMailSender
    ) {
        this.gmailMailSender = gmailMailSender;
        this.onlineMailSender = onlineMailSender;
        this.mailUaMailSender = mailUaMailSender;
        this.ukrNetMailSender = ukrNetMailSender;
        this.iuamailSender = iuamailSender;
        this.metaUaMailSender = metaUaMailSender;
        this.yahooMailSender = yahooMailSender;
        this.outlookMailSender = outlookMailSender;
    }

    public void sendEmail(String to, String subject, String text, String provider)
            throws MessagingException {
        JavaMailSender mailSender = chooseMailSender(provider);

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        mailSender.send(message);
    }

    private JavaMailSender chooseMailSender(String provider) {
        return switch (provider.toLowerCase()) {
            case "gmail" -> gmailMailSender;
            case "online" -> onlineMailSender;
            case "mail.ua" -> mailUaMailSender;
            case "ukr.net" -> ukrNetMailSender;
            case "i.ua" -> iuamailSender;
            case "meta.ua" -> metaUaMailSender;
            case "yahoo" -> yahooMailSender;
            case "outlook" -> outlookMailSender;
            default -> throw new IllegalArgumentException("Unknown provider: " + provider);
        };
    }
}
