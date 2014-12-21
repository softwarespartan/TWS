package com.examples;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.tws.ContractFactory;
import com.ib.client.ContractDetails;

public class GetContractDetails {

    public static void main(String[] args) {

        // create connection object for to communicate with TWS
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // initialize a contract for symbols to BUY
        Contract contract = ContractFactory.GenericStockContract("SPY");

        eClientSocket.reqContractDetails(0,contract);
    }
}
