package com.example.demo.Interceptor;

import com.example.demo.annotation.Authorize;
import com.example.demo.annotation.NonAuthorize;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthorizationHandlerInterceptor implements HandlerInterceptor {

  @Autowired UserService userService;

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {

    // 実行されるメソッドを取得
    Method method = null;
    if (handler instanceof HandlerMethod) {
      method = ((HandlerMethod) handler).getMethod();
    } else {
      return true;
    }
    // @NonAuthorizeが付与されているか確認
    if (AnnotationUtils.findAnnotation(method, NonAuthorize.class) != null) {
      // 付与されている場合は認可せずに終了
      return true;
    }
    // メソッドに対応するControllerを取得
    Class<?> controller = method.getDeclaringClass();
    // Controllerまたはメソッドに@Authorizeが付与されているか確認
    if (AnnotationUtils.findAnnotation(controller, Authorize.class) != null
        || AnnotationUtils.findAnnotation(method, Authorize.class) != null) {
      // 付与されている場合は認可処理を実行
      if (!authorize(request)) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
      }
    }
    return true;
  }

  public boolean authorize(HttpServletRequest request) {
    // Authorizationの値を取得
    String authorization = request.getHeader("Authorization");
    if (authorization == null || authorization.isEmpty()) {
      return false;
    }
    // Bearer tokenの形式であることをチェック
    if (authorization.indexOf("Bearer ") != 0) {
      return false;
    }
    // トークンを取得しidを取得する
    String token = authorization.substring(7);
    // トークンからユーザ情報取得
    User user = userService.searchUser(token);
    // TODO idの検証を行う
    if (StringUtils.isEmpty(user.getName())) {
      return false;
    }
    return true;
  }
}
