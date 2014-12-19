package com.examples;

import com.ib.client.EClientSocket;
import com.ib.controller.AccountSummaryTag;
import com.tws.EmptyWrapper;

public class AccountSummary {
    public static void main(String[] args){

        // create connection object for to communicate with TWS
        EClientSocket eClientSocket = new EClientSocket(new EmptyWrapper());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // build CSV string of attributes to request
        StringBuilder attrStr = new StringBuilder();
        for (AccountSummaryTag tag : AccountSummaryTag.values()) {
            if (attrStr.length() > 0) { attrStr.append(','); }
            attrStr.append(tag);
        }

        System.out.println(attrStr);

        // request account summary
        eClientSocket.reqAccountSummary(0, "All", attrStr.toString());
    }
}
