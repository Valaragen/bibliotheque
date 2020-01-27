package com.rudy.bibliotheque.batch.config;

import com.rudy.bibliotheque.batch.model.Borrow;
import com.rudy.bibliotheque.batch.processing.BorrowItemProcessor;
import com.rudy.bibliotheque.batch.processing.JobCompletionNotificationListener;
import com.rudy.bibliotheque.batch.processing.MailBatchItemWriter;
import com.rudy.bibliotheque.batch.repository.BorrowRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BorrowRepository borrowRepository;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${config.batch.data}")
    public String data;

    @Value("${config.batch.attachment}")
    private String attachment;

    @Value("${config.batch.notifications.email}")
    private String email;


    @Bean
    public ItemReader<Borrow> reader() {
        RepositoryItemReader<Borrow> reader = new RepositoryItemReader<>();
        reader.setRepository(borrowRepository);
        reader.setMethodName("findAllByLoanEndDateBefore");
        List param = new ArrayList();
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        param.add(date);
        reader.setArguments(param);

        return reader;
    }

    @Bean
    public BorrowItemProcessor processor() {
        return new BorrowItemProcessor(sender, attachment);
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
                .<Borrow, MimeMessage> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }




}
