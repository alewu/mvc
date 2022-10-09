package com.ale.scheduling;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@Component
@Slf4j
public class MyTask {

    private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    public static AtomicInteger number = new AtomicInteger(0);
    public static ConcurrentHashMap<String, Integer> doingMap = new ConcurrentHashMap<>();

    @Scheduled(cron = "0/1 * * * * *")
    public void work() {
        // task execution logic

        for (int i = 0; i <10; i++){
            if (doingMap.containsKey(String.valueOf(i))) {
                continue;
            }
            if (number.get() >= 3) {
                return;
            }
            doingMap.put(String.valueOf(i), 1);
            int i1 = number.incrementAndGet();
            log.info("放入 map:{}, 加法 number:{}", i, i1);

            int finalI = i;
            fixedThreadPool.execute(() -> deal(finalI));

        }



    }

    private void deal(int i) {
        try {
            int i2 = RandomUtil.randomInt(10);
            TimeUnit.SECONDS.sleep(i2);
            doingMap.remove(String.valueOf(i));
            int i1 = number.decrementAndGet();
            log.info("时间：{}， 移除 map:{}减法 number :{}", i2, i, i1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}