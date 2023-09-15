package it.academy.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationServlet extends HttpServlet {
    private final List<Transport> transports = new ArrayList<>();

    private List<Transport> getTransports() {
        transports.add(new Transport("motorcycle", "Ninja ZX-14"));
        transports.add(new Transport("automobile", "Audi Q7"));
        transports.add(new Transport("minibus", "Sprinter264"));
        return transports;
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final List transportValue = Collections.singletonList(getTransports().stream()
                .map(Transport::toString)
                .reduce(String::concat));

        response.setContentType("text/html");

        try (final PrintWriter writer = response.getWriter()) {
            writer.println(transportValue);
        }
    }


    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        String transportValue = null;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {
            transportValue = reader.lines()
                    .reduce("", String::concat);
        }
        response.setContentType("text/html");

        try (final PrintWriter writer = response.getWriter()) {
            writer.println("Body: " + transportValue);
        }
    }

}
