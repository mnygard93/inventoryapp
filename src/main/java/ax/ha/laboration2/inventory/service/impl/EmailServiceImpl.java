package ax.ha.laboration2.inventory.service.impl;

import ax.ha.laboration2.inventory.model.Product;
import ax.ha.laboration2.inventory.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final static Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final static String INVENTORY_APP = "hainventoryapp@gmail.com";

    private final static String SUBJECT = "Shopping List";

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean sendEmail(final List<Product> products, final String email) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        final LocalDateTime now = LocalDateTime.now();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(INVENTORY_APP);
        simpleMailMessage.setSubject(SUBJECT + " " + dtf.format(now).toLowerCase() + ":");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(assembleMail(products));
        try {
            javaMailSender.send(simpleMailMessage);
        }
        catch (final MailSendException | MailParseException | MailAuthenticationException e) {
            LOG.error(e.getMessage());
            return false;
        }
        return true;
    }

    private String assembleMail(List<Product> products) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Products out of stock: ").append("\n\n\n");
        for(Product product : products) {
            sb.append("* ").append(product.getDescription()).append("\n");
        }
        return sb.toString();
    }
}
