package com.tws;

import com.ib.client.EWrapperMsgGenerator;

public class EFP {
    public final int    reqId               ;
    public final int    tickType            ;
    public final double basisPoints         ;
    public final String formattedBasisPoints;
    public final double impliedFuture       ;
    public final int    holdDays            ;
    public final String futureExpiry        ;
    public final double dividendImpact      ;
    public final double dividendsToExpiry   ;

    public EFP(
            int    reqId               ,
            int    tickType            ,
            double basisPoints         ,
            String formattedBasisPoints,
            double impliedFuture       ,
            int    holdDays            ,
            String futureExpiry        ,
            double dividendImpact      ,
            double dividendsToExpiry
    ) {
        this.reqId                = reqId               ;
        this.tickType             = tickType            ;
        this.basisPoints          = basisPoints         ;
        this.formattedBasisPoints = formattedBasisPoints;
        this.impliedFuture        = impliedFuture       ;
        this.holdDays             = holdDays            ;
        this.futureExpiry         = futureExpiry        ;
        this.dividendImpact       = dividendImpact      ;
        this.dividendsToExpiry    = dividendsToExpiry   ;
    }

    public String toString(){
        return EWrapperMsgGenerator.tickEFP(
                this.reqId               ,
                this.tickType            ,
                this.basisPoints         ,
                this.formattedBasisPoints,
                this.impliedFuture       ,
                this.holdDays            ,
                this.futureExpiry        ,
                this.dividendImpact      ,
                this.dividendsToExpiry
        );
    }
}