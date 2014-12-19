package com.tws;

import com.ib.client.Contract;

/**
 * Associated with EWrapper:reqPositions
 */

public final class Position {

    public final String   account    ;
    public final Contract contract   ;
    public final int      position   ;
    public final double   averageCost;

    public Position(String account, Contract contract, int position, double averageCost){
        this.account = account; this.contract = contract; this.position = position; this.averageCost = averageCost;
    }

    @Override
    public String toString(){
        return this.contract.m_symbol+" "+this.position+" "+this.averageCost;
    }
}
