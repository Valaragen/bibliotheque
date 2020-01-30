package com.rudy.bibliotheque.batch.processing;

import com.rudy.bibliotheque.batch.DTO.BorrowDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * Class used to process objects
 */
public class BorrowItemProcessor implements ItemProcessor<BorrowDTO, MimeMessage> {

    private static final Logger log = LoggerFactory.getLogger(BorrowItemProcessor.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    private String sender;

    public BorrowItemProcessor(String sender) {
        this.sender = sender;
    }

    @Override
    public MimeMessage process(BorrowDTO borrowDTO) throws Exception {
        // Prepare the evaluation context
        final Context ctx = new Context(Locale.FRANCE);
        ctx.setVariable("bookName", borrowDTO.getBookName());
        ctx.setVariable("username", borrowDTO.getUserUsername());
        ctx.setVariable("loanEndDate", borrowDTO.getLoanEndDate());

        final MimeMessage message = mailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message, true , "UTF-8");

        helper.setFrom(sender);
        helper.setTo(borrowDTO.getUserEmail());
        helper.setCc(sender);

        // Create the TEXT subject using Thymeleaf
        final String content = this.templateEngine.process("email-subject.txt", ctx);
        helper.setSubject(content);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-body.html", ctx);
        helper.setText(htmlContent, true); // true = isHtml

        log.info("Preparing message for: " + borrowDTO.getUserEmail());

        return message;
    }

}
