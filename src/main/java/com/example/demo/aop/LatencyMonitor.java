package com.example.demo.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** メソッドの実行速度を計測する用のマーカアノテーション. 実際の計測は {@link com.example.demo.aop.LatencyMonitorAop} で実行. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LatencyMonitor {}
