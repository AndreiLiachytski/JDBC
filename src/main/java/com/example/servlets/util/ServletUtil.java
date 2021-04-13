package com.example.servlets.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtil {

    public static PrintWriter getWriter(final HttpServletResponse response) throws IOException {
        return response.getWriter();
    }
}