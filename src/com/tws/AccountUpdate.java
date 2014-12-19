package com.tws;

/**
 * Associated with EWrapper:reqAccountUpdates
 */

public final class AccountUpdate {

    public final String account  ;
    public final String key      ;
    public final String value    ;
    public final String currency ;

    public AccountUpdate(String key, String value, String currency, String account){
        this.account = account; this.key = key; this.value = value; this.currency = currency;
    }
}
