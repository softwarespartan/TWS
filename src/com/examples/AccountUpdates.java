package com.examples;

import com.ib.client.EClientSocket;

public class AccountUpdates {
    public static void main(String[] args){

        // create connection object for to communicate with TWS
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // request account summary
        eClientSocket.reqAccountUpdates(true,"DU207406");
    }
}
