package com.tws;

import com.ib.client.TagValue;

import java.util.Vector;

/**
 * Associated with EWrapper:reqContractDetails
 */

public class ContractDetails {

    public final int                           reqId          ;
    public final com.ib.client.ContractDetails contractDetails;

    public ContractDetails(int reqId, com.ib.client.ContractDetails contractDetails) {
        this.reqId           = reqId          ;
        this.contractDetails = contractDetails;
    }

    @Override
    public String toString() {
        String msg = "conid = "           + this.contractDetails.m_summary.m_conId        + "\n"
                   + "symbol = "          + this.contractDetails.m_summary.m_symbol       + "\n"
                   + "secType = "         + this.contractDetails.m_summary.m_secType      + "\n"
                   + "expiry = "          + this.contractDetails.m_summary.m_expiry       + "\n"
                   + "strike = "          + this.contractDetails.m_summary.m_strike       + "\n"
                   + "right = "           + this.contractDetails.m_summary.m_right        + "\n"
                   + "multiplier = "      + this.contractDetails.m_summary.m_multiplier   + "\n"
                   + "exchange = "        + this.contractDetails.m_summary.m_exchange     + "\n"
                   + "primaryExch = "     + this.contractDetails.m_summary.m_primaryExch  + "\n"
                   + "currency = "        + this.contractDetails.m_summary.m_currency     + "\n"
                   + "localSymbol = "     + this.contractDetails.m_summary.m_localSymbol  + "\n"
                   + "tradingClass = "    + this.contractDetails.m_summary.m_tradingClass + "\n"
                   + "marketName = "      + this.contractDetails.m_marketName             + "\n"
                   + "minTick = "         + this.contractDetails.m_minTick                + "\n"
                   + "price magnifier = " + this.contractDetails.m_priceMagnifier         + "\n"
                   + "orderTypes = "      + this.contractDetails.m_orderTypes             + "\n"
                   + "validExchanges = "  + this.contractDetails.m_validExchanges         + "\n"
                   + "underConId = "      + this.contractDetails.m_underConId             + "\n"
                   + "longName = "        + this.contractDetails.m_longName               + "\n"
                   + "contractMonth = "   + this.contractDetails.m_contractMonth          + "\n"
                   + "industry = "        + this.contractDetails.m_industry               + "\n"
                   + "category = "        + this.contractDetails.m_category               + "\n"
                   + "subcategory = "     + this.contractDetails.m_subcategory            + "\n"
                   + "timeZoneId = "      + this.contractDetails.m_timeZoneId             + "\n"
                   + "tradingHours = "    + this.contractDetails.m_tradingHours           + "\n"
                   + "liquidHours = "     + this.contractDetails.m_liquidHours            + "\n"
                   + "evRule = "          + this.contractDetails.m_evRule                 + "\n"
                   + "evMultiplier = "    + this.contractDetails.m_evMultiplier           + "\n";

        String sec = "secIdList={";
        if (contractDetails.m_secIdList != null) {
            Vector secIdList = contractDetails.m_secIdList;
            for (int i = 0; i < secIdList.size(); ++i) {
                TagValue param = (TagValue)secIdList.elementAt(i);
                if (i > 0) { sec += ","; }
                sec += param.m_tag + "=" + param.m_value;
            }
        }

        return msg+sec+"}\n";
    }
}
