package com.examples;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.ib.client.Order;
import com.tws.ContractFactory;
import com.tws.OrderFactory;

public class MarketDepth {
    public static void main(String[] args) {

        // create connection object for to communicate with TWS
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // initialize a contract for symbols to BUY
        Contract contract = ContractFactory.GenericStockContract("IBM");


        //"SMART","ISE","CHX","ARCA","ISLAND","VWAP","IBSX","DRCTEDGE","BEX","BATS","EDGEA","LAVA","CSFBALGO","JEFFALGO","BYX","IEX","TPLUS2","PSX"

        // set the exchange
        contract.m_exchange = "NYSE"; //contract.m_primaryExch = "ARCA";

        // place the order
        eClientSocket.reqMktDepth(6352, contract, 10, null);

//        // set the exchange
//        contract.m_exchange = "ARCA";
//
//        // place the order
//        eClientSocket.reqMktDepth(6353, contract, 10, null);
//
//        // set the exchange
//        contract.m_exchange = "BATS";
//
//        // place the order
//        eClientSocket.reqMktDepth(6354, contract, 10, null);
//
//        // set the exchange
//        contract.m_exchange = "EDGEA";
//
//        // place the order
//        eClientSocket.reqMktDepth(6355, contract, 10, null);
    }
}
