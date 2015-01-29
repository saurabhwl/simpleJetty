package rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class APITest {

    Server jettyServer = new Server(8888);
    @Before
    public void setup () {
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");

            jettyServer.setHandler(context);

            ServletHolder jerseyServlet = context.addServlet(
                    org.glassfish.jersey.servlet.ServletContainer.class, "/*");
            jerseyServlet.setInitOrder(0);

            // Tells the Jersey Servlet which REST service/class to load.
            jerseyServlet.setInitParameter(
                    "jersey.config.server.provider.classnames",
                    API.class.getCanonicalName());

            try {
                jettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @After
    public void shutdown() {
        try {
            jettyServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testTest() throws Exception {
            Client restClient = ClientBuilder.newClient();
            //restClient.register(new CsrfProtectionFilter()); //register a filter, here a predefined one

            WebTarget target = restClient.target("http://localhost:8888/jsonServices/");
            // target.register(new CsrfProtectionFilter());//or register on a target
            WebTarget resourceTarget = target.path("/test"); //change the URI without affecting a root URI
            String responseString = resourceTarget.request("text/plain").get(String.class);
            System.out.println("Here is the response: "+responseString);
            assert(responseString.equals("Test"));
        }
    }
