package com.examples;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.tws.ContractFactory;

public class HistoricalData {
    public static void main(String[] args){

        // create TWS connection object linked to message handler interface
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1",7496,0);

        // create a generic contract for getting historical data
        Contract FB   = ContractFactory.GenericStockContract("FB");

        // make the request for some data for this contract
        eClientSocket.reqHistoricalData(1000001,FB,"20141128 16:00:00","1 Y","1 day","TRADES",0,1,null);
    }
}
