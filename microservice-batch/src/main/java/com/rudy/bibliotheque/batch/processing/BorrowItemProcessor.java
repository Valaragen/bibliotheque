package com.rudy.bibliotheque.batch.processing;

import com.rudy.bibliotheque.batch.model.Borrow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * Class used to process objects
 */
public class BorrowItemProcessor implements ItemProcessor<Borrow, MimeMessage> {

    private static final Logger log = LoggerFactory.getLogger(BorrowItemProcessor.class);

    @Autowired
    private JavaMailSender mailSender;

    private String sender;

    public BorrowItemProcessor(String sender) {
        this.sender = sender;
    }

    @Override
    public MimeMessage process(Borrow borrow) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(sender);
        helper.setTo(borrow.getUser().getEmail());
        helper.setCc(sender);
        helper.setSubject("Test");
        helper.setText("You just received a test message");

        log.info("Preparing message for: " + borrow.getUser().getEmail());

        return message;
    }

}
