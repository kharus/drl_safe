package drl_safe;

import org.kie.api.KieServices;
// import org.kie.api.event.rule.DebugAgendaEventListener;
// import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;

import java.util.Collection;


public class App 
{
    public static void main( final String[] args)
    {
        // From the kie services, a container is created from the classpath
        KieServices ks = KieServices.get();
        KieContainer kc = ks.getKieClasspathContainer();

        // Create a session from the drl_safe specification in the kmodule.XML file.
        KieSession ksession = kc.newKieSession("drl_safe_KS");

        //ksession.addEventListener( new DebugRuleRuntimeEventListener() );
        
        //ksession.addEventListener( new DebugAgendaEventListener() );

      
        Agenda agenda = ksession.getAgenda();
        agenda.getAgendaGroup( "start" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "conversions" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "capitalization" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "prices 1" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "prices 2" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "pre-terminate" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "post-terminate" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "results" ).setFocus();
        ksession.fireAllRules();
      
        // and fire the rules
        // ksession.fireAllRules();

        // Put the resulting data in a variable for debugging purposes.
        Collection<? extends Object> objects = ksession.getObjects();

        // and then dispose the session
        ksession.dispose();
    }
}