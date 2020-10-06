package drl_safe;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class App 
{
    public static void main( final String[] args)
    {
        // From the kie services, a container is created from the classpath
        KieServices ks = KieServices.get();
        KieContainer kc = ks.getKieClasspathContainer();

        // Create a session from the drl_safe specification in the kmodule.XML file.
        KieSession ksession = kc.newKieSession("drl_safe_KS");

        // and fire the rules
        ksession.fireAllRules();

        // and then dispose the session
        ksession.dispose();
    }
}