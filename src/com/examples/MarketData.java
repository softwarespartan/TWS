package com.examples;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.tws.ContractFactory;
import com.tws.EmptyWrapper;

public class MarketData {
    public static void main(String[] args) {

        // create connection object for to communicate with TWS
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // initialize a contract for symbols to watch
        Contract contract = ContractFactory.GenericStockContract("SPY");

        // set the exchange
        contract.m_exchange = "ARCA"; contract.m_primaryExch = "ARCA";

        // specify all the generic tick data we're interested in
        String genericTickList = "100,101,105,106,107,125,165,166," +
                                 "225,232,221,233,236,258,47,291," +
                                 "293,294,295,318,370,370,377,377," +
                                 "381,384,384,387,388,391,407,411," +
                                 "428,439,439,456,59,459,460,499," +
                                 "506,511,512,104,513,514,515,516,517";

        // realistically maybe something like 100,101,106,233

        // start the market data subscription for the contract
        eClientSocket.reqMktData(0,contract,genericTickList,false,null);
    }
}
