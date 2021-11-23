package com.company.application.core.security;

import com.vaadin.flow.server.HandlerHelper;
import com.vaadin.flow.shared.ApplicationConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

/**
 * @author Thorsten Zieres, 1297197
 */
public class SecurityUtils {
    private SecurityUtils() { }

    static boolean isFrameworkInternalRequest(HttpServletRequest request) {
        final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
        return parameterValue != null
                && Stream.of(HandlerHelper.RequestType.values()).anyMatch(r -> r.getIdentifier().equals(parameterValue));
    }
}
