package com.tws;

/**
 * Associated with:
 *
 *     EWrapper:error(Exception e)
 *     EWrapper:error(String message)
 *     EWrapper:error(int id, int errorCode, String errorMsg)
 */
public class Error {

    public final Integer   id          ;
    public final Integer   errorCode   ;
    public final String    errorMessage;
    public final Exception exception   ;

    private Error(Integer id, Integer errorCode, String errorMessage, Exception exception) {
        this.id           = id          ;
        this.errorCode    = errorCode   ;
        this.errorMessage = errorMessage;
        this.exception    = exception   ;
    }

    public Error(String    message  ){ this( null,null,message,null      ); }

    public Error(Exception exception){ this( null,null,null   ,exception ); }

    public Error(int id, int errorCode, String errorMessage){
        this(id,errorCode,errorMessage,null);
    }

    @Override
    public String toString(){

        if (this.id != null && this.errorCode != null && this.errorMessage != null){
            return this.id + " " + this.errorCode + " " + this.errorMessage;
        }

        else if (this.id == null && this.errorCode == null && this.errorMessage != null){
            return this.errorMessage;
        }

        else if (this.exception != null) {
            return this.exception.toString();
        }

        else {
            return "TWS ERROR HAS OCCURRED";
        }
    }
}
