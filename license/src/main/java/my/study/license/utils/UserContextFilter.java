package my.study.license.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static my.study.license.utils.UserContext.*;


@Component
@Slf4j
public class UserContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        UserContext userContext = UserContextHolder.getContext();
        userContext.setCorrelationId(request.getHeader(CORRELATION_ID) );
        userContext.setUserId(request.getHeader(USER_ID));
        userContext.setAuthToken(request.getHeader(AUTH_TOKEN));
        userContext.setOrganizationId(request.getHeader(ORGANIZATION_ID));

        log.warn("UserContextFilter Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

        chain.doFilter(servletRequest, servletResponse);
    }

}
