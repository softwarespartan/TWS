package com.tws;

import com.ib.client.Order;
import com.ib.controller.OrderType;
import com.ib.controller.Types;

public class OrderFactory {

    public static Order GenericLimitOrder(String account,String action, int quantity, double price){

        // init new order
        Order order = new Order();

        // set the order type to limit
        order.m_orderType = "LMT";

        // set the account id
        order.m_account = account;

        // set the order type
        switch (action.toUpperCase()){
            case "BUY" : order.m_action = "BUY" ; break;
            case "SELL": order.m_action = "SELL"; break;
            default:
                System.out.println("invalid order action: "+action);
                order.m_action = "";
        }

        // set the numer of shares
        order.m_totalQuantity = quantity;

        // set the limit price
        order.m_lmtPrice = price;

        // that's it ..
        return order;
    }

    public static void main(String[] args){

        Order order = OrderFactory.GenericLimitOrder("DU207406","BUY",100,200.2);

        System.out.println(order.m_account+" "+order.m_orderType+" "+order.m_action+" "+order.m_totalQuantity+" "+order.m_lmtPrice);
    }
}
