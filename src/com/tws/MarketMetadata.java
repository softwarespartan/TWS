package com.tws;
/**
 *  Associated with EWrapper:tickGeneric and EWrapper:tickString
 */
public final class MarketMetadata {
    public final double reqId;
    public final String key  ;
    public final String value;

    public MarketMetadata(int reqId, String key, String value){
        this.reqId = reqId; this.key = key; this.value = value;
    }

    @Override
    public String toString(){
        return this.key+":"+this.value;
    }
}
