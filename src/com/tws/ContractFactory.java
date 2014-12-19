package com.tws;

import com.ib.client.Contract;
import com.ib.controller.Types;

public class ContractFactory {

    public static Contract GenericContract     (String symbol){

        // init an empty contract
        Contract contract = new Contract();

        // set the symbol for the contract
        contract.m_symbol = symbol;

        // fill in the contract with default values
        contract.m_conId            = 0         ;
        contract.m_secType          = null      ;
        contract.m_expiry           = ""        ;
        contract.m_strike           = 0.0       ;
        contract.m_right            = ""        ;
        contract.m_multiplier       = ""        ;
        contract.m_exchange         = "SMART"   ;
        contract.m_primaryExch      = "ISLAND"  ;
        contract.m_currency         = "USD"     ;
        contract.m_localSymbol      = ""        ;
        contract.m_tradingClass     = ""        ;
        contract.m_secIdType        = ""        ;
        contract.m_secId            = null      ;
        contract.m_underComp        = null      ;
        contract.m_comboLegsDescrip = null      ;
        contract.m_comboLegs        = null      ;
        contract.m_includeExpired   = false     ;

        // that's all folks
        return contract;
    }

    public static Contract GenericStockContract(String symbol){

        // init an empty contract
        Contract contract = ContractFactory.GenericContract(symbol);

        // set the security type to stock
        contract.m_secType = Types.SecType.STK.toString();

        // that's a [w]rap
        return contract;
    }


}
