package com.korek.odl;

import com.korek.odl.model.json.Node;
import com.korek.odl.model.json.NodeConnector;
import com.korek.odl.model.json.Nodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class OdlApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdlApplication.class, args);
    }

}
