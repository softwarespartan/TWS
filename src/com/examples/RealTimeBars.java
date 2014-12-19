package com.examples;

import java.util.Vector;

import com.ib.client.Contract;
import com.ib.client.TagValue;
import com.ib.client.EClientSocket;
import com.tws.ContractFactory;

public class RealTimeBars {
    public static void main(String[] args){

        // create connection object for to communicate with TWS
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1",7496,0);

        // initialize a contract for symbols to watch
        Contract FB   = ContractFactory.GenericStockContract("FB");
        Contract BABA = ContractFactory.GenericStockContract("BABA");

        // request real-time bars
        eClientSocket.reqRealTimeBars(0,FB  ,0,"TRADES",false,new Vector<TagValue>());
        eClientSocket.reqRealTimeBars(1,BABA,0,"TRADES",false,new Vector<TagValue>());
    }
}
