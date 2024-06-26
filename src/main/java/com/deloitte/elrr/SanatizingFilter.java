package com.deloitte.elrr;

import fr.spacefox.confusablehomoglyphs.Confusables;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
/**
 * 
 */
@Component
public class SanatizingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        WrappedHttp httpRequest;

        StringBuilder body = new StringBuilder();
        for (String line : request.getReader().lines().toList()) {
            if (InputSanatizer.isValidInput(line)) {
                body.append(line);
                body.append('\n');

            } else {
                // need to log bad request. Might be best to continue processing
                // and report all bad lines. / complete body
                httpResponse.sendError(HttpStatus.BAD_REQUEST.value(),
                        "Illegal line in request body: " + line);
            }
        }
        if (httpResponse.isCommitted())
            return;

        httpRequest = new WrappedHttp((HttpServletRequest) request,
                body.toString());
        httpRequest.getParameterMap(); // might help to cache parameters for
                                       // future filter chain

        // below we check each parameter and parameter values for any invalid
        // strings
        httpRequest.getParameterNames().asIterator().forEachRemaining(param -> {
            String paramVal = request.getParameter(param);
            if (!InputSanatizer.isValidInput(paramVal)
                    || !InputSanatizer.isValidInput(param)) {
                try {
                    httpResponse.sendError(HttpStatus.BAD_REQUEST.value(),
                            "Illegal Parameter Value");
                    return;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        try {
            if (hasHomoGlyphs(httpRequest)) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Request body contains homoglyphs.");
                return;
            }
        } catch (Exception e) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Malformed request body");
            return;
        }
        chain.doFilter(httpRequest, response);
    }

    private static boolean hasHomoGlyphs(WrappedHttp httpRequest) {

        if (httpRequest.getBody().isEmpty())
            return false;
        Confusables confusables = Confusables.fromInternal();
        JSONObject jsonObject = new JSONObject(httpRequest.getBody());
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = (String) jsonObject.get(key);
            boolean dangerousKey = confusables.isDangerous(key);
            boolean dangerousValue = confusables.isDangerous(value);
            if (dangerousKey || dangerousValue) {
                return true;
            }
        }
        return false;
    }

}
