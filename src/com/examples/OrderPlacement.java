package com.examples;


import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.ib.client.Order;
import com.tws.ContractFactory;
import com.tws.OrderFactory;

public class OrderPlacement {
    public static void main(String[] args) {

        // create connection object for to communicate with TWS
        EClientSocket eClientSocket = new EClientSocket(new TWSClientInterface());

        // try to connect to TWS
        eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // initialize a contract for symbols to BUY
        Contract SPY = ContractFactory.GenericStockContract("SPY");

        // create an order to specify account info, order type, price, etc
        Order order = OrderFactory.GenericLimitOrder("DU207406", "BUY", 100, 198.5);

        // place the order
        eClientSocket.placeOrder(8840,SPY,order);
    }
}
