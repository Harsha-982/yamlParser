import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

public class MainServer
{
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);


        WebAppContext context = new WebAppContext();
        String basePath="src/main/webapp";

        File warFile = new File("out/artifacts/JettyWarAppEngine_war/JettyWarAppEngine.war");
        context.setWar(warFile.getAbsolutePath());
        context.setContextPath("/");
//        context.setWar("src/main/web");
//        context.setDescriptor(basePath+"/WEB-INF/web.xml");
//        context.setResourceBase(basePath);

        ClassList classList=new ClassList();
        ClassList classList1 =classList.setServerDefault(server);

        classList1.addAfter(
                "org.eclipse.jetty.webapp.FragmentConfiguration",
                "org.eclipse.jetty.plus.webapp.EnvConfiguration",
                "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classList1.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");

        server.setHandler(context);
        server.start();
        server.join();
    }
}
