package com.tws;

/**
 * Associated with EWrapper:tickPrice and EWrapper:tickSize
 */
public final class MarketData {
    public final double reqId ;
    public final int    tickId;
    public final String key   ;
    public final double value ;

    public MarketData(int reqId, int tickId, String key, double value){
        this.reqId = reqId; this.tickId = tickId; this.key = key; this.value = value;
    }

    @Override
    public String toString(){
        return this.key+":"+this.value;
    }
}
