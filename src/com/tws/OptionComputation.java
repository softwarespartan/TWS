package com.tws;

import com.ib.client.EWrapperMsgGenerator;

public class OptionComputation {

    public final int    reqId      ;
    public final int    tickType   ;
    public final double impliedVol ;
    public final double delta      ;
    public final double optPrice   ;
    public final double pvDividend ;
    public final double gamma      ;
    public final double vega       ;
    public final double theta      ;
    public final double undPrice   ;

    public OptionComputation(
            int    reqId      ,
            int    tickType   ,
            double impliedVol ,
            double delta      ,
            double optPrice   ,
            double pvDividend ,
            double gamma      ,
            double vega       ,
            double theta      ,
            double undPrice
    ) {
        this.reqId      = reqId      ;
        this.tickType   = tickType   ;
        this.impliedVol = impliedVol ;
        this.delta      = delta      ;
        this.optPrice   = optPrice   ;
        this.pvDividend = pvDividend ;
        this.gamma      = gamma      ;
        this.vega       = vega       ;
        this.theta      = theta      ;
        this.undPrice   = undPrice   ;
    }

    @Override
    public String toString(){
        return EWrapperMsgGenerator.tickOptionComputation(
                this.reqId      ,
                this.tickType   ,
                this.impliedVol ,
                this.delta      ,
                this.optPrice   ,
                this.pvDividend ,
                this.gamma      ,
                this.vega       ,
                this.theta      ,
                this.undPrice
        );
    }
}