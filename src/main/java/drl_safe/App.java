package drl_safe;

import java.util.ArrayList;
import java.util.Date;

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

        KieSession ksession = kc.newKieSession("drl_safe_KS");
     
        // Generate the data
        Company company = new Company();
        company.setName("ABC Inc.");
        SAFE test_safe = new SAFE();
        test_safe.setCompany(company);
        test_safe.setPurchase_amount(1000000);
        RWEvent event = new RWEvent();
        event.setSafe_type(SAFE_Event_Type.DISSOLUTION_EVENT);
        company.getEvents().add(event);

        // Insert the data into the database.
        ksession.insert( test_safe );

        // and fire the rules
        ksession.fireAllRules();

        // and then dispose the session
        ksession.dispose();
    }

    public static class Company
    {
        private String      name;
        private Double      preferred_share_price; // The current preferred share price, not a historical value.
        private Integer     capital_stock_issued;
        private Double      converting_securities;
        private Integer     issued_options;
        private Integer     promised_options;
        private Integer     unissued_options;
        private Integer     capitalization;
        private Integer     liquidity_capitalization;
        private ArrayList<RWEvent> events;

        public Company() {
            this.events = new ArrayList<RWEvent>();
        }

        public ArrayList<RWEvent> getEvents() {
            return this.events;
        }

        public void setEvents(ArrayList<RWEvent> events) {
            this.events = events;
        }

        public Integer getCapitalization() {
            return this.capitalization;
        }

        public Integer getLiquidity_capitalization() {
            return this.liquidity_capitalization;
        }

        public void setCapitalization(Integer amount) {
            this.capitalization = amount;
        }

        public void setLiquidity_capitalization(Integer amount) {
            this.liquidity_capitalization = amount;
        }

        public Integer getCapital_stock_issued() {
            return this.capital_stock_issued;
        }

        public Double getConverting_securities() {
            return this.converting_securities;
        }

        public Integer getIssued_options() {
            return this.issued_options;
        }

        public Integer getPromised_options() {
            return this.promised_options;
        }

        public Integer getUnissued_options() {
            return this.unissued_options;
        }

        public void setCapital_stock_issued(Integer amount) {
            this.capital_stock_issued = amount;
        }

        public void setConverting_securities(Double amount) {
            this.converting_securities = amount;
        }

        public void setIssued_options(Integer amount) {
            this.issued_options = amount;
        }

        public void setPromised_options(Integer amount) {
            this.promised_options = amount;
        }

        public void setUnissued_options(Integer amount) {
            this.unissued_options = amount;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) { 
            this.name = name;
        }

        public Double getPreferred_share_price() {
            return this.preferred_share_price;
        }

        public void setPreferred_share_price(Double price) {
            this.preferred_share_price = price;
        }
    }

    public static class Person
    {
        private String      name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    enum RWEvent_Type {
        INITIAL_PUBLIC_OFFERING,
        DIRECT_LISTING,
        CHANGE_OF_CONTROL,
        VOLUNTARY_TERMINATION_OF_OPERATIONS,
        OTHER_LIQUIDATION_ETC,
        GENERAL_ASSIGNMENT_FOR_BENEFIT_OF_CREDITORS,
        BONA_FIDE_TRANSACTION
    }

    enum SAFE_Event_Type {
        LIQUIDITY_EVENT,
        DISSOLUTION_EVENT,
        EQUITY_FINANCING
    }

    public static class RWEvent
    {
        private RWEvent_Type    type;
        private Date            date;
        private SAFE_Event_Type safe_type;
        private boolean         intended_to_qualify_tax_free;
        private boolean         security_holders_given_choice_of_form_and_amount;

        public void setSecurity_holders_given_choice_of_form_and_amount(boolean value){
            this.security_holders_given_choice_of_form_and_amount = value;
        }

        public boolean getSecurity_holders_given_choice_of_form_and_amount() { 
            return this.security_holders_given_choice_of_form_and_amount;
        }

        public void setIntended_to_qualify_tax_free(boolean value) {
            this.intended_to_qualify_tax_free = value;
        }

        public boolean getIntended_to_qualify_tax_free() {
            return this.intended_to_qualify_tax_free;
        }

        public SAFE_Event_Type getSafe_type() {
            return this.safe_type;
        }

        public void setSafe_type(SAFE_Event_Type type) {
            this.safe_type = type;
        }

        public Date getDate(){
            return this.date;
        }

        public RWEvent_Type getType() {
            return this.type;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public void setType(RWEvent_Type type) {
            this.type = type;
        }
    }

    public static class SAFE
    {
        private Person      investor;
        private Company     company;
        private Integer     purchase_amount;
        private Integer     post_money_valuation_cap;
        private Double      discount_rate;
        private SAFE_Result safe_result;
        private Date        date_terminated;
        private boolean     terminated;
        private Double      safe_price;
        private Double      liquidity_price;
        private Double      conversion_price;
        private Double      conversion_amount;
        private Integer      cash_out_amount;
        private RWEvent     terminating_event;

        public RWEvent getTerminating_event() {
            return this.terminating_event;
        }

        public void setTerminating_event(RWEvent event) {
            this.terminating_event = event;
        }

        public Double getSafe_price () {
            return this.safe_price;
        }

        public void setSafe_price (Double value) {
            this.safe_price = value;
        }

        public Double getLiquidity_price () {
            return this.liquidity_price;
        }

        public void setLiquidity_price (Double value) {
            this.liquidity_price = value;
        }

        public Double getConversion_price () {
            return this.conversion_price;
        }

        public void setConversion_price (Double value) {
            this.conversion_price = value;
        }

        public Double getConversion_amount () {
            return this.conversion_amount;
        }

        public void setConversion_amount (Double value) {
            this.conversion_amount = value;
        }

        public Integer getCash_out_amount () {
            return this.cash_out_amount;
        }

        public void setCash_out_amount (Integer value) {
            this.cash_out_amount = value;
        }

        public void setTerminated(boolean value) {
            this.terminated = value;
        }

        public boolean getTerminated() {
            return this.terminated;
        }

        public void setDate_terminated(Date date) {
            this.date_terminated = date;
        }

        public Date getDate_terminated() {
            return this.date_terminated;
        }

        public SAFE_Result getSafe_result() {
            return this.safe_result;
        }

        public void setSafe_result(SAFE_Result result) { 
            this.safe_result = result;
        }

        public Person getInvestor() {
            return this.investor;
        }

        public Company getCompany() {
            return this.company;
        }

        public Integer getPurchase_amount() {
            return this.purchase_amount;
        }

        public Integer getPost_money_valuation_cap() {
            return this.post_money_valuation_cap;
        }

        public Double getDiscount_rate() {
            return this.discount_rate;
        }

        public void setInvestor(Person investor) {
            this.investor = investor;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public void setPurchase_amount(Integer amount) {
            this.purchase_amount = amount;
        }

        public void setPost_money_valuation_cap(Integer amount) {
            this.post_money_valuation_cap = amount;
        }

        public void setDiscount_rate(Double rate) {
            this.discount_rate = rate;
        }
    }

    public static class SAFE_Result
    {
        private boolean     investor_provides_documents;
        private boolean     investor_choice_form_and_amount;
        private boolean     board_may_reduce_cash_portion;
        private boolean     safe_operates_as_preferred_stock;
        private String      terminating_event;
        private Double     cash_amount_due;
        private Double     safe_preferred_stock_due;

        
        public void getInvestor_provides_documents(boolean value){
            this.investor_provides_documents = value;
        }

        public void setInvestor_choice_form_and_amount(boolean value) {
            this.investor_choice_form_and_amount = value;
        }

        public void setBoard_may_reduce_cash_portion(boolean value) {
            this.board_may_reduce_cash_portion = value;
        }

        public void setSafe_operates_as_preferred_stock(boolean value) {
            this.safe_operates_as_preferred_stock = value;
        }

        public void setTerminating_event(String event) {
            this.terminating_event = event;
        }

        public void setCash_amount_due(Double amount) {
            this.cash_amount_due = amount;
        }

        public void setSafe_preferred_stock_due(Double amount) {
            this.safe_preferred_stock_due = amount;
        }

        public boolean getInvestor_provides_documents(){
            return this.investor_provides_documents;
        }

        public boolean getInvestor_choice_form_and_amount() {
            return this.investor_choice_form_and_amount;
        }

        public boolean getBoard_may_reduce_cash_portion() {
            return this.board_may_reduce_cash_portion;
        }

        public boolean getSafe_operates_as_preferred_stock() {
            return this.safe_operates_as_preferred_stock;
        }

        public String getTerminating_event() {
            return this.terminating_event;
        }

        public Double getCash_amount_due() {
            return this.cash_amount_due;
        }

        public Double getSafe_preferred_stock_due() {
            return this.safe_preferred_stock_due;
        }

    }
}