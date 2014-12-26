package com.examples;

import com.ib.client.EClientSocket;
import com.ib.client.ExecutionFilter;

public class NextOrderId {

    public static void main(String[] args){

        // create connection object for to communicate with TWS
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // ask for the next order id
        //eClientSocket.reqIds(1);
    }
}
