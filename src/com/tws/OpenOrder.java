package com.tws;

import com.ib.client.Contract;
import com.ib.client.EWrapperMsgGenerator;
import com.ib.client.Order;
import com.ib.client.OrderState;

/**
 * Associated with EWrapper:openOrder
 */

public class OpenOrder {

    public final int        orderId   ;
    public final Contract   contract  ;
    public final Order      order     ;
    public final OrderState orderState;

    public OpenOrder(
                     int        orderId   ,
                     Contract   contract  ,
                     Order      order     ,
                     OrderState orderState
    ) {
        this.orderId    = orderId   ;
        this.contract   = contract  ;
        this.order      = order     ;
        this.orderState = orderState;
    }

    @Override
    public String toString(){
        return EWrapperMsgGenerator.openOrder(this.orderId   ,
                                              this.contract  ,
                                              this.order     ,
                                              this.orderState
        );
    }
}
