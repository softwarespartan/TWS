package com.tws;

/**
 * Associated with EWrapper:reqAccountUpdates
 */

public final class AccountAttribute {

    public final String account  ;
    public final String key      ;
    public final String value    ;
    public final String currency ;

    public AccountAttribute(String key, String value, String currency, String account){
        this.account = account; this.key = key; this.value = value; this.currency = currency;
    }

    @Override
    public String toString(){
        return this.account+" "+this.key+" "+this.value+" "+this.currency;
    }
}
