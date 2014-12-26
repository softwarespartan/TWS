package com.tws;

import com.ib.client.Contract ;
import com.ib.client.Execution;

public class ExecutionDetails {

    public final Contract  contract ;
    public final Execution execution;

    public ExecutionDetails(Contract contract, Execution execution) {
        this.contract = contract; this.execution = execution;
    }

    @Override
    public String toString(){
        return
                "orderId = "           + execution.m_orderId      + "\n"
              + "clientId = "          + execution.m_clientId     + "\n"
              + "conid = "             + contract.m_conId         + "\n"
              + "symbol = "            + contract.m_symbol        + "\n"
              + "secType = "           + contract.m_secType       + "\n"
              + "expiry = "            + contract.m_expiry        + "\n"
              + "strike = "            + contract.m_strike        + "\n"
              + "right = "             + contract.m_right         + "\n"
              + "multiplier = "        + contract.m_multiplier    + "\n"
              + "exchange = "          + contract.m_exchange      + "\n"
              + "primaryExch = "       + contract.m_primaryExch   + "\n"
              + "currency = "          + contract.m_currency      + "\n"
              + "localSymbol = "       + contract.m_localSymbol   + "\n"
              + "tradingClass = "      + contract.m_tradingClass  + "\n"
              + "execId = "            + execution.m_execId       + "\n"
              + "time = "              + execution.m_time         + "\n"
              + "acctNumber = "        + execution.m_acctNumber   + "\n"
              + "executionExchange = " + execution.m_exchange     + "\n"
              + "side = "              + execution.m_side         + "\n"
              + "shares = "            + execution.m_shares       + "\n"
              + "price = "             + execution.m_price        + "\n"
              + "permId = "            + execution.m_permId       + "\n"
              + "liquidation = "       + execution.m_liquidation  + "\n"
              + "cumQty = "            + execution.m_cumQty       + "\n"
              + "avgPrice = "          + execution.m_avgPrice     + "\n"
              + "orderRef = "          + execution.m_orderRef     + "\n"
              + "evRule = "            + execution.m_evRule       + "\n"
              + "evMultiplier = "      + execution.m_evMultiplier + "\n";
    }
}
