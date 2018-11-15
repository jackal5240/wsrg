package com.ogloba.ew.wsrg.doc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DocIndexController {

    @GetMapping({"/doc/guide", "/doc/javadoc"})
    public String index(HttpServletRequest request) {

        //String requestUri = request.getRequestURI();
        String requestUrl = request.getRequestURL().toString();

        if (requestUrl.endsWith("/")) {
            return "redirect:" + requestUrl + "index.html";
        } else {
            return "redirect:" + requestUrl + "/index.html";
        }
    }
}
