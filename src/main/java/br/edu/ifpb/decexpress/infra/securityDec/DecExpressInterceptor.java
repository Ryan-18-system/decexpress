package br.edu.ifpb.decexpress.infra.securityDec;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DecExpressInterceptor implements HandlerInterceptor {

    public static final Logger logger = Logger.getLogger(DecExpressInterceptor.class.getName());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.hasMethodAnnotation(SecurityDec.class)
                    || handlerMethod.getBeanType().isAnnotationPresent(SecurityDec.class)) {
                logger.log(Level.INFO,"Chegou");
                return true; // Continua o fluxo de execução
            }
        }
        return true;
    }
}
