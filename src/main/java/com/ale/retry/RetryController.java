package com.ale.retry;

import com.github.rholder.retry.RetryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author alewu
 * @date 2020/7/31
 */
@RestController
@RequestMapping("/retry")
@Slf4j
@RequiredArgsConstructor
public class RetryController {
    private final CustomRetryerBuilder customRetryerBuilder;
    private final CustomCallable customCallable;

    /**
     * @author alewu
     * @date 2020/7/31 13:42
     */
    @GetMapping("")
    public ResponseEntity<String> retry() {
        StopWatch sw = new StopWatch("RetryController.retry");
        sw.start("StopWatch");
        try {
            Boolean result = customRetryerBuilder.<Boolean>build().call(customCallable);
            sw.stop();
            log.info("call:{}, {} ms", result, sw.getLastTaskTimeMillis());
        } catch (
                ExecutionException e) {
            log.error("execute retry failed", e);
        } catch (
                RetryException e) {
            log.error("retry failed", e);
        }
        return ResponseEntity.ok("");
    }
}
