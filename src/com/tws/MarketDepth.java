package com.tws;

/**
 * Associated with EWrapper:reqMktDepth
 */

public final class MarketDepth {
    public final int    reqId      ;
    public final int    position   ;
    public final String marketMaker;
    public final int    operation  ;
    public final int    side       ;
    public final double price      ;
    public final int    size       ;


    // EWrapper:updateMktDepth
    public MarketDepth(int reqId, int position, int operation, int side, double price, int size) {
        this(reqId,position,null,operation,side,price,size);
    }

    // EWrapper:updateMktDepthL2
    public MarketDepth(int reqId, int position, String marketMaker, int operation, int side, double price, int size) {
        this.reqId       = reqId      ;
        this.position    = position   ;
        this.marketMaker = marketMaker;
        this.operation   = operation  ;
        this.side        = side       ;
        this.price       = price      ;
        this.size        = size       ;
    }

    @Override
    public String toString(){
        return    this.reqId       + " "
                + this.position    + " "
                + this.marketMaker + " "
                + this.operation   + " "
                + this.side        + " "
                + this.price       + " "
                + this.size;
    }
}

