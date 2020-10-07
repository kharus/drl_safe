# Drools Implementation of Simple Agreement for Future Equity

This is an implementation of Y-Combinator's [SAFE (Simple Agreement for Future Equity)](https://www.ycombinator.com/documents/)
in
[Drools](https:https://drools.org/) Rule Language (DRL).
It is intended for demonstration and educational purposes only, and should
not be used for any real-world purpose.

It is written by Jason Morris for the Singapore Management University Centre for Computational
Law.

The .drl file implements the examples in the "Post Money Safe User Guide" Appendix II, Example 1,
parts 1) and 2).

The data is added to the database as a rule that is triggered at the start of the calculation:

```
// Rule to Create Data For Testing
rule "Test Scenario"
    agenda-group "start"
    when
        // Left empty so rule will always trigger.
    then
        // System.out.println("Creating test data.\n");
        Person investor_a = new Person();
        investor_a.setName("Investor A");
        Person investor_b = new Person();
        investor_b.setName("Investor B");
        Company company = new Company();
        company.setName("ABC Inc.");
        ArrayList<RWEvent> events = new ArrayList<RWEvent>();
        company.setEvents(events);
        SAFE investor_a_safe = new SAFE();
        investor_a_safe.setInvestor(investor_a);
        investor_a_safe.setCompany(company);
        investor_a_safe.setPurchase_amount(200000);
        investor_a_safe.setPost_money_valuation_cap(4000000);
        investor_a_safe.setDiscount_rate(1.0);
        SAFE investor_b_safe = new SAFE();
        investor_b_safe.setInvestor(investor_b);
        investor_b_safe.setCompany(company);
        investor_b_safe.setPurchase_amount(800000);
        investor_b_safe.setPost_money_valuation_cap(8000000);
        investor_b_safe.setDiscount_rate(1.0);
        company.setPreferred_share_price(1.1144);
        company.setCapital_stock_issued(9250000);
        company.setIssued_options(300000);
        company.setPromised_options(350000);
        company.setUnissued_options(100000);

        RWEvent event = new RWEvent();
        event.setType(RWEvent_Type.BONA_FIDE_TRANSACTION);
        company.getEvents().add(event);
        
        insert(company);
        insert(investor_a_safe);
        insert(investor_b_safe);
        insert(event);
end
```

When the application is run, it returns the following output (ommitting compilation warnings).

```
Investor B purchased a safe from ABC Inc..
Investor B is obliged to provide documentation.
Investor B is entitled to 0.0 in cash.
Investor B is entitled to 1176470 preferred stock.
These requirements will be met on: Issuance of capital stock.


Investor A purchased a safe from ABC Inc..
Investor A is obliged to provide documentation.
Investor A is entitled to 0.0 in cash.
Investor A is entitled to 588235 preferred stock.
These requirements will be met on: Issuance of capital stock.
```
