package com.luxoft.bankapp.service.audit;

import com.luxoft.bankapp.service.audit.events.AccountEvent;
import com.luxoft.bankapp.service.audit.events.BalanceEvent;
import com.luxoft.bankapp.service.audit.events.DepositEvent;
import com.luxoft.bankapp.service.audit.events.WithdrawEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuditService implements Audit
{
    private List<AccountEvent> events;

    public AuditService()
    {
        this.events = new ArrayList<>(100);
    }

    public List<AccountEvent> getEvents()
    {
        return new ArrayList<>(events);
    }

    @Override
    public void auditDeposit(long accountId,
                             double amount)
    {
        System.out.println("ACCOUNT ID: "
                + accountId + " DEPOSIT: " + amount);

        this.events.add(new DepositEvent(accountId, amount));
    }

    @Override
    public void auditWithdraw(long accountId,
                              double amount, WithdrawState state)
    {
        System.out.println("ACCOUNT ID: "
                + accountId + " " + state
                + " WITHDRAWAL: " + amount);

        this.events.add(new WithdrawEvent(accountId, amount));
    }

    @Override
    public void auditBalance(long accountId)
    {
        System.out.println("ACCOUNT ID: "
                + accountId + " BALANCE CHECK");

        this.events.add(new BalanceEvent(accountId));
    }

}
