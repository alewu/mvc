package com.ale.retry;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Retryer 提供了构造方法，用来创建一个指定规则的 Retryer：
 * 这个方法可以通过传入尝试时间策略、停止重试策略、重试间隔等待策略、重试阻塞策略、拒绝策略等策略
 * 来指定一个请求的重试如何进行。
 * RetryerBuilder的retryIfXXX()方法用来设置在什么情况下进行重试，总体上可以分为根据执行异常进行重试和根据方法执行结果进行重试两类。
 * @author alewu
 * @date 2020/8/7
 */
@Component
public class CustomRetryerBuilder {

    public <T> Retryer<T> build() {
        return RetryerBuilder.<T>newBuilder().
                //retryIf 重试条件
                        retryIfExceptionOfType(NullPointerException.class).
                        retryIfRuntimeException().
                        retryIfException().
                //                        retryIfResult().
                //重试等待策略
                        withWaitStrategy(WaitStrategies.incrementingWait(3, TimeUnit.SECONDS, 3, TimeUnit.SECONDS)).
                //重试停止策略
                        withStopStrategy(StopStrategies.stopAfterAttempt(5)).
                //注册监听
                        withRetryListener(new MyRetryListener()).build();
    }
}
