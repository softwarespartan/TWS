package com.tws;
/**
 *  Associated with EWrapper:tickGeneric and EWrapper:tickString
 */
public final class MarketMetadata {
    public final double reqId ;
    public final int    tickId;
    public final String key   ;
    public final String value ;

    public MarketMetadata(int reqId, int tickId, String key, String value){
        this.reqId = reqId; this.tickId = tickId; this.key = key; this.value = value;
    }

    @Override
    public String toString(){
        return this.key+":"+this.value;
    }
}
