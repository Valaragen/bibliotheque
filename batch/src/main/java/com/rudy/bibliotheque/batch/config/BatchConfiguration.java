package com.rudy.bibliotheque.batch.config;

import com.rudy.bibliotheque.batch.DTO.BorrowDTO;
import com.rudy.bibliotheque.batch.processing.BorrowItemProcessor;
import com.rudy.bibliotheque.batch.processing.JobCompletionNotificationListener;
import com.rudy.bibliotheque.batch.processing.MailBatchItemWriter;
import com.rudy.bibliotheque.batch.proxy.MicroserviceBookProxy;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.internet.MimeMessage;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public MicroserviceBookProxy microserviceBookProxy;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${config.batch.notifications.email}")
    private String email;


    @Bean
    public ItemReader<BorrowDTO> reader() {
        return new ListItemReader<>(microserviceBookProxy.getAllNonReturnedExpiredLoans());
    }

    @Bean
    public BorrowItemProcessor processor() {
        return new BorrowItemProcessor(sender);
    }

    @Bean
    public MailBatchItemWriter writer() {
        MailBatchItemWriter writer = new MailBatchItemWriter();
        return writer;
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener(email);
    }

    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<BorrowDTO, MimeMessage> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }




}
