package com.examples;

import com.ib.client.EClientSocket;

public class CancelAllOrders {

    public static void main(String[] args){

        // create connection obj to TWS with noisy handler
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // sign up for order info
        //eClientSocket.reqAllOpenOrders();

        // nuke all orders
        eClientSocket.reqGlobalCancel();
    }
}
