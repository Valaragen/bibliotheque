package com.rudy.biliotheque.mbook.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("configs")
public class ApplicationPropertiesConfig {
    private int loanTimeInDays;

}
