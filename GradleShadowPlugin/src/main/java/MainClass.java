
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;


import java.net.URL;

public class MainClass {
    public static void main(String[] args) throws Exception {
        Server server =new Server(8080);

        /* working perfect with 8080

        ServletHandler  servletHandler=new ServletHandler();
        servletHandler.addServletWithMapping(HelloWorld.class,"/*" );
        server.setHandler(servletHandler);

        */
        WebAppContext  context=new WebAppContext();
        context.setContextPath("/");
        URL webDir =
                MainClass.class.getClassLoader().getResource("META-INF/resources");
        context.setResourceBase(webDir.toURI().toString());


        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*jstl.*\\.jar$");

        ClassList classlist =ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");

        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");
        server.setHandler(context);

        System.out.println("Server Started ! ");
        server.start();
        server.join();

    }
}
