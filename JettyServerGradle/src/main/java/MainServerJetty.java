
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;

public class MainServerJetty {
    public static void main(String[] args) throws Exception {
        Server server=new Server(8080);
        WebAppContext context=new WebAppContext();
        server.setHandler(context);

//        URL webAppDir= MainServerJetty.class.getClassLoader().getResource(".");
//        context.setResourceBase(webAppDir.toURI().toString());

        context.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/");

        server.start();
        System.out.println("Server Started");
        server.join();
    }
}
