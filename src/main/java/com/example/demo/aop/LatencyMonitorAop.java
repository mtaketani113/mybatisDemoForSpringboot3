package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LatencyMonitorAop {

    @Around("@annotation(com.example.demo.aop.LatencyMonitor)")
    public Object monitor(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis(); // メソッド開始前のシステム時刻
        Object proceed = joinPoint.proceed(); // メソッド実行
        long end = System.currentTimeMillis(); // メソッド終了後のシステム時刻
        log.info("{} {}   method latency: {} ms." // メソッドの実行時間の出力
                , joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(),
                end - start);
        return proceed; // メソッドの内容を返却
    }
}
