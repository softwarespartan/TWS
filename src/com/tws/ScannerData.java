package com.tws;

/**
 * Associated with EWrapper:scannerData
 */

public class ScannerData {

    public final int                           rank           ;
    public final com.ib.client.ContractDetails contractDetails;
    public final String                        distance       ;
    public final String                        benchmark      ;
    public final String                        projection     ;
    public final String                        legsStr        ;

    public ScannerData(
                       int                           rank           ,
                       com.ib.client.ContractDetails contractDetails,
                       String                        distance       ,
                       String                        benchmark      ,
                       String                        projection     ,
                       String                        legsStr
    ) {
        this.rank            = rank           ;
        this.contractDetails = contractDetails;
        this.distance        = distance       ;
        this.benchmark       = benchmark      ;
        this.projection      = projection     ;
        this.legsStr          = legsStr       ;
    }

    @Override
    public String toString(){
        return  " rank="         + this.rank                                     +
                " symbol="       + this.contractDetails.m_summary.m_symbol       +
                " secType="      + this.contractDetails.m_summary.m_secType      +
                " expiry="       + this.contractDetails.m_summary.m_expiry       +
                " strike="       + this.contractDetails.m_summary.m_strike       +
                " right="        + this.contractDetails.m_summary.m_right        +
                " exchange="     + this.contractDetails.m_summary.m_exchange     +
                " currency="     + this.contractDetails.m_summary.m_currency     +
                " localSymbol="  + this.contractDetails.m_summary.m_localSymbol  +
                " marketName="   + this.contractDetails.m_marketName             +
                " tradingClass=" + this.contractDetails.m_summary.m_tradingClass +
                " distance="     + this.distance                                 +
                " benchmark="    + this.benchmark                                +
                " projection="   + this.projection                               +
                " legsStr="      + this.legsStr                                  ;
    }
}
