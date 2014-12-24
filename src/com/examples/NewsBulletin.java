package com.examples;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.tws.ContractFactory;

public class NewsBulletin {

    public static void main(String[] args){
        // create connection obj to TWS with noisy handler
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // request the market depth
        eClientSocket.reqNewsBulletins(true);
    }
}
