package com.tws;

/**
 * Associated with EWrapper:nextValidId
 *
 * NOTE: EWrapper:nextValidId is called when new connection is established with TWS
 *
 */

public class NextOrderId {

    public final int nextOrderId;

    public NextOrderId(int nextOrderId) { this.nextOrderId = nextOrderId; }

    @Override
    public String toString(){ return "nextOrderId = "+ this.nextOrderId; }
}
