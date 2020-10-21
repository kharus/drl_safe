package drl_safe;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.rule.Agenda;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class AppTest {

    @Test
    public void validateStockAllocationScenario1() {
        KieSession ksession = createDefaultSession();

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
        agenda.getAgendaGroup( "terminate" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "post-terminate" ).setFocus();
        ksession.fireAllRules();
        agenda.getAgendaGroup( "results" ).setFocus();
        ksession.fireAllRules();


        Integer stockAllocationA = fetchStockAllocation(ksession, "Investor A");
        Integer stockAllocationB = fetchStockAllocation(ksession, "Investor B");

        assertThat(stockAllocationA).isEqualTo(588235);
        assertThat(stockAllocationB).isEqualTo(1176470);
    }

    private Integer fetchStockAllocation(KieSession ksession, String investorName) {
        QueryResults results = ksession.getQueryResults( "safeOfInvestor",investorName);

        List<QueryResultsRow> result =
                StreamSupport.stream(results.spliterator(), false)
                        .collect(Collectors.toList());
        assertThat(results).hasSize(1);

        return (Integer) result.get(0).get("$issuedStock");
    }

    protected KieSession createDefaultSession() {
        KieServices ks = KieServices.get();
        KieContainer kc = ks.getKieClasspathContainer();

        return kc.newKieSession("drl_safe_KS");
    }
}