package com.examples;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.tws.ContractFactory;

public class MarketDepth {
    public static void main(String[] args) {

        // create connection obj to TWS with noisy handler
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // initialize a contract for symbol
        Contract contract = ContractFactory.GenericStockContract("SPY");

        // set the exchange
        contract.m_exchange = "ARCA";  contract.m_primaryExch = "ARCA";

        // request the market depth
        eClientSocket.reqMktDepth(0, contract, 5, null);
    }
}
