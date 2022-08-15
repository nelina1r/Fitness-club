package ru.t1.dedov.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.t1.dedov.model.entity.UserAction;
import ru.t1.dedov.service.interfaces.UserActionService;

import java.time.LocalDateTime;

@Aspect
@Component
public class UserActionAspect {

    private final UserActionService userActionService;

    @Autowired
    public UserActionAspect(UserActionService userActionService){
        this.userActionService = userActionService;
    }

    @Pointcut("within(ru.t1.dedov.controller..*)")
    public void controllerMethods() {
    }

    @Before("controllerMethods()")
    public void controllerMethodsParameters(JoinPoint joinPoint) {
        UserAction userAction = new UserAction();
        userAction.setMethodArgs(joinPoint.getArgs());
        userAction.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        userAction.setActionDateTime(LocalDateTime.now());
        userAction.setMethodName(joinPoint.getSignature().getName());
        userActionService.saveLog(userAction);
    }
}
