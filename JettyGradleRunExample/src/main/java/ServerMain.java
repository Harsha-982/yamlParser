import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;

import java.net.URL;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        Server server=new Server(8080);
        WebAppContext webAppContext = new WebAppContext();
        //gets the resources for static files
        URL webAppDir =
                ServerMain.class.getClassLoader().getResource("META-INF/resources");
        webAppContext.setResourceBase(webAppDir.toURI().toString());

        //enables annotations so that sever sees classes annotated
        webAppContext.setConfigurations(new Configuration[]{
                new AnnotationConfiguration(),
                new WebInfConfiguration(),
        });
//  looks for the annotated classes in the "build.classes.java.main" directory
        webAppContext.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/build/classes/java/main/|.*\\.jar");
        // handles static resources
        webAppContext.addServlet(DefaultServlet.class, "/");

        server.setHandler(webAppContext);
        server.start();
        System.out.println("ServerStarted");
        server.join();
    }
}
