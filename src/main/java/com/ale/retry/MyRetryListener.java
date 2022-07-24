package com.ale.retry;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 当发生重试之后，如我们需要做一些额外的处理动作，比如发送告警邮件，记录下重试的次数、结果等信息，那么可以使用RetryListener。
 * 每次重试之后，guava-retrying会自动回调我们注册的监听。也可以注册多个RetryListener，会按照注册顺序依次调用。
 * @author alewu
 * @date 2020/7/31
 */
@Slf4j
public class MyRetryListener implements RetryListener {
    @Override
    public <V> void onRetry(Attempt<V> attempt) {
        // 距离第一次重试的延迟
        log.info("retry times: {}, Delay Since First Attempt {} ms", attempt.getAttemptNumber(),
                 attempt.getDelaySinceFirstAttempt());

        // 是什么原因导致异常
        if (attempt.hasException()) {
            log.warn("Exception Cause: {}", attempt.getExceptionCause().toString());
        } else {// 正常返回时的结果
            log.info("result {}", attempt.getResult());
        }

    }
}
