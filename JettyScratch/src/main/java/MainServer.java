import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.Configuration.ClassList;

import java.io.File;

public class MainServer
{
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        WebAppContext context = new WebAppContext();
        String basePath="src/main/webapp";
        context.setContextPath("/");
        System.out.print(basePath);
        context.setDescriptor(basePath+"/WEB-INF/web.xml");
        context.setResourceBase(basePath);

        ClassList classList=new ClassList();
        ClassList classList1 =classList.setServerDefault(server);

        classList1.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");


        server.setHandler(context);
        server.start();
        server.join();
    }
}
