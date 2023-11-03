package br.edu.ifpb.decexpress.infra.securityDec;

import br.edu.ifpb.decexpress.utils.exception.AuthTokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;
import java.util.logging.Logger;

@Component
public class DecExpressInterceptor implements HandlerInterceptor {

    @Autowired
    AuthTokenService tokenService;
    public static final Logger logger = Logger.getLogger(DecExpressInterceptor.class.getName());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.hasMethodAnnotation(SecurityDec.class)
                    || handlerMethod.getBeanType().isAnnotationPresent(SecurityDec.class)) {
                SecurityDec anotationSecurity = handlerMethod.getMethodAnnotation(SecurityDec.class);
                String authorizationHeader = request.getHeader("Authorization");
                if (authorizationHeader != null && !Objects.isNull(anotationSecurity)) {
                    if (!tokenService.authToken(new TokenDTO(authorizationHeader))) {
                        throw new AuthTokenException("erro.nao.autorizado");
                    }
                    UsuarioLogadoDTO usuarioLogadoDTO = tokenService.obterInfosUserLogado();
                    if (!usuarioLogadoDTO.isAdmin() && !anotationSecurity.accessAllowed()) {
                        throw new AuthTokenException("erro.nao.tem.permissao");
                    }
                } else {
                    throw new AuthTokenException("erro.ao.obter.token");
                }
                return true;
            }
        }
        return true;
    }
}
