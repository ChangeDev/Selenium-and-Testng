package com.taoqi;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.backoff.Sleeper;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ChangFeng on 2017/12/19.
 */
public class RetryTest {

    public static void main(String[] args) {
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);
        Sleeper sleeper = new Sleeper() {
            private long timeToSleep = 0;

            @Override
            public void sleep(long timeout) throws InterruptedException {
                if (timeToSleep == 0) {
                    timeToSleep = timeout;
                } else {
                    timeToSleep = (long) (timeToSleep * Math.E);
                }
                System.out.println("sleeping for: " + timeToSleep);
                Thread.sleep(timeToSleep);
            }
        };
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy().withSleeper(sleeper);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.execute(new RetryCallback<Void, ResourceAccessException>() {
            @Override
            public Void doWithRetry(RetryContext retryContext) throws ResourceAccessException {
                System.out.println(">RetryCount: " + retryContext.getRetryCount());
                new RestTemplate().getForObject("https://unreachable.host", String.class);
                return null;
            }
        });
    }
}
