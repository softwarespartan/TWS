package com.tws;

/**
 * Associated with EWrapper:reqMktData
 */

public final class MarketDepthL1Update {
    public final int    reqId    ;
    public final int    position ;
    public final int    operation;
    public final int    side     ;
    public final double price    ;
    public final int    size     ;


    public MarketDepthL1Update(int reqId, int position, int operation, int side, double price, int size) {
        this.reqId     = reqId    ;
        this.position  = position ;
        this.operation = operation;
        this.side      = side     ;
        this.price     = price    ;
        this.size      = size     ;
    }

    @Override
    public String toString(){
        return    this.reqId     + " "
                + this.position  + " "
                + this.operation + " "
                + this.side      + " "
                + this.price     + " "
                + this.size;
    }
}
