package com.tws;

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
}
