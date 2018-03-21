package io.mart.checker;

import jersey.repackaged.com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Request;
import org.junit.ComparisonFailure;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Slf4j
public class HealthChecker {

    public void checkMicroserviceStatuses(Set<String> tags) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("health_check")
                .build();

        ExecutorService scheduler = Executors
                .newSingleThreadExecutor(namedThreadFactory);

        Runnable task = () -> {
            getServiceLinks(tags)
                    .forEach(this::checkStatusForParticularService);
        };
        scheduler.submit(task);
    }

    private void checkStatusForParticularService(String healthLink) {
        log.info("Checking health status, {}", healthLink);
        try {
            String s = Request.Get(healthLink)
                    .execute().returnContent().asString();
            assertThat(s).contains("UP");
            log.info(s);
        } catch (IOException e) {
            log.error("Connection to Health service refused");
            System.exit(1);
        } catch (ComparisonFailure e) {
            log.error("Health status is not UP");
            System.exit(1);
        }
    }

    private List<String> getServiceLinks(Set<String> tags) {
        List<String> urls = new ArrayList<>();
        for (String tag : tags) {
            switch (tag) {
                case "@api" :
                    urls.add("http://localhost:8080/health");
                    break;
                case "@balancer" :
                    urls.add("http://localhost:8090/health");
                    break;
            }
        }
        return urls;
    }


}
