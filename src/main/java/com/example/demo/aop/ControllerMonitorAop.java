package com.example.demo.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class ControllerMonitorAop {

  /**
   * Aroundアノテーションにより、指定したメソッドの前後に処理を追加する Aroundアノテーションの引数には、Pointcut式を指定
   *
   * @param jp 横断的な処理を挿入する場所
   * @return 指定したメソッドの戻り値
   * @throws Throwable
   */
  @Around("execution(* com.example.demo..*Controller.*(..))")
  public Object writeLog(ProceedingJoinPoint jp) throws Throwable {
    // 返却オブジェクトを定義
    Object returnObj = null;
    // 指定したクラスの指定したメソッド名・戻り値を取得
    String signature = jp.getSignature().toString();
    // 開始ログを出力
    log.debug("start writeLog : " + signature);
    long start = System.currentTimeMillis(); // メソッド開始前のシステム時刻
    // 指定したクラスの指定したメソッドを実行
    returnObj = jp.proceed();
    long end = System.currentTimeMillis(); // メソッド終了後のシステム時刻

    log.debug(
        "{} {}   method latency: {} ms." // メソッドの実行時間の出力
        ,
        jp.getTarget().getClass().getName(),
        jp.getSignature().getName(),
        end - start);
    // 引数を取得しログに出力
    log.debug("args : " + getArgStr(jp));
    // 戻り値を取得しログに出力
    log.debug("return : " + returnObj);
    // メソッド名を取得しログに出力
    String methodName = ((MethodSignature) jp.getSignature()).getMethod().getName();
    log.debug("Method name : " + methodName);
    // リクエストURLを取得しログに出力
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    log.debug("request URL : " + request.getRequestURL().toString());
    // 終了ログを出力
    log.debug("end writeLog : " + signature);
    // 指定したクラスの指定したメソッドの戻り値を返却
    // このように実行しないと、Controllerクラスの場合、次画面遷移が行えない
    return returnObj;
  }

  /**
   * 指定したメソッドの引数の文字列を取得する
   *
   * @param jp 横断的な処理を挿入する場所
   * @return 指定したメソッドの引数
   */
  private String getArgStr(JoinPoint jp) {
    StringBuilder sb = new StringBuilder();
    Object[] args = jp.getArgs();
    if (args.length > 0) {
      for (Object arg : args) {
        sb.append(arg + ", ");
      }
      sb.delete(sb.length() - 2, sb.length() - 1);
    } else {
      sb.append("(empty)");
    }
    return sb.toString();
  }
}
