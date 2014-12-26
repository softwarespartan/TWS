package com.examples;

import com.ib.client.EClientSocket;
import com.ib.client.ExecutionFilter;

public class ExecutionDetails {
    public static void main(String[] args){

        // create connection object for to communicate with TWS
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // auto associate TWS order with API
        eClientSocket.reqAutoOpenOrders(true);

        // get all the execution entries so init empty filter
        ExecutionFilter filter = new ExecutionFilter();

        // request execution details
        eClientSocket.reqExecutions(0,filter);
    }
}
