package com.examples;

import com.ib.client.EClientSocket;

public class ScannerSubscription {
    public static void main(String[] args){

        // create connection object for to communicate with TWS
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        com.ib.client.ScannerSubscription scannerSubscription = new com.ib.client.ScannerSubscription();

        scannerSubscription.numberOfRows   (15              );
        scannerSubscription.instrument     ("STK"          );
        scannerSubscription.scanCode       ("TOP_PERC_GAIN");
        scannerSubscription.locationCode   ("STK.ARCA" );
        scannerSubscription.stockTypeFilter("CORP,ADR,ETF,CEF,REIT");
        scannerSubscription.abovePrice(1);

        eClientSocket.reqScannerSubscription(0,scannerSubscription,null);
        //eClientSocket.reqScannerParameters();
    }
}
