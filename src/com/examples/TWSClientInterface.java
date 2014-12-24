package com.examples;

import com.ib.client.*;

public class TWSClientInterface implements EWrapper{

    @Override
    public void tickPrice(int tickerId, int field, double price, int canAutoExecute) {
        System.out.println("TWSClientInterface:tickPrice -- "+tickerId+" "+field+":"+TickType.getField(field)+" "+price);
    }

    @Override
    public void tickSize(int tickerId, int field, int size) {
        System.out.println("TWSClientInterface:tickSize -- "+tickerId+" "+field+":"+TickType.getField(field)+" "+size);
    }

    @Override
    public void tickOptionComputation(int tickerId, int field, double impliedVol, double delta, double optPrice, double pvDividend, double gamma, double vega, double theta, double undPrice) {

    }

    @Override
    public void tickGeneric(int tickerId, int tickType, double value) {
        System.out.println("TWSClientInterface:tickGeneric -- "+tickerId+" "+tickType+":"+TickType.getField(tickType)+" "+value);
    }

    @Override
    public void tickString(int tickerId, int tickType, String value) {
        System.out.println("TWSClientInterface:tickString -- "+tickerId+" "+tickType+":"+TickType.getField(tickType)+" "+value);
    }

    @Override
    public void tickEFP(int tickerId, int tickType, double basisPoints, String formattedBasisPoints, double impliedFuture, int holdDays, String futureExpiry, double dividendImpact, double dividendsToExpiry) {

    }

    @Override
    public void orderStatus(int orderId, String status, int filled, int remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {
        System.out.println(EWrapperMsgGenerator.orderStatus(orderId,status,filled,remaining,avgFillPrice,permId,parentId,lastFillPrice,clientId,whyHeld));
    }

    @Override
    public void openOrder(int orderId, Contract contract, Order order, OrderState orderState) {
        System.out.println(EWrapperMsgGenerator.openOrder(orderId,contract,order,orderState));
    }

    @Override
    public void openOrderEnd() {
        System.out.println("TWSClientInterface:openOrderEnd was called");
    }

    @Override
    public void updateAccountValue(String key, String value, String currency, String accountName) {
        String msg = "  account = "  + accountName
                   + "  tag = "      + key
                   + "  value = "    + value
                   + "  currency = " + currency;

        System.out.println("TWSClientInterface:updateAccountValue -- "+msg);
    }

    @Override
    public void updatePortfolio(Contract contract, int position, double marketPrice, double marketValue, double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {

    }

    @Override
    public void updateAccountTime(String timeStamp) {

    }

    @Override
    public void accountDownloadEnd(String accountName) {

    }

    @Override
    public void nextValidId(int orderId) {

    }

    @Override
    public void contractDetails(int reqId, ContractDetails contractDetails) {

        String msg = "marketName = "   + contractDetails.m_marketName     + "\n"
                + "minTick = "         + contractDetails.m_minTick        + "\n"
                + "price magnifier = " + contractDetails.m_priceMagnifier + "\n"
                + "orderTypes = "      + contractDetails.m_orderTypes     + "\n"
                + "validExchanges = "  + contractDetails.m_validExchanges + "\n"
                + "underConId = "      + contractDetails.m_underConId     + "\n"
                + "longName = "        + contractDetails.m_longName       + "\n"
                + "contractMonth = "   + contractDetails.m_contractMonth  + "\n"
                + "industry = "        + contractDetails.m_industry       + "\n"
                + "category = "        + contractDetails.m_category       + "\n"
                + "subcategory = "     + contractDetails.m_subcategory    + "\n"
                + "timeZoneId = "      + contractDetails.m_timeZoneId     + "\n"
                + "tradingHours = "    + contractDetails.m_tradingHours   + "\n"
                + "liquidHours = "     + contractDetails.m_liquidHours    + "\n"
                + "evRule = "          + contractDetails.m_evRule         + "\n"
                + "evMultiplier = "    + contractDetails.m_evMultiplier   + "\n";
        System.out.println(msg);

    }

    @Override
    public void bondContractDetails(int reqId, ContractDetails contractDetails) {

    }


    @Override
    public void contractDetailsEnd(int reqId) {
        System.out.println("TWSClientInterface:contractDetailEnd was called");
    }

    @Override
    public void execDetails(int reqId, Contract contract, Execution execution) {
        String msg = " ---- Execution Details begin ----\n"
                + "reqId = "             + reqId                    + "\n"
                + "orderId = "           + execution.m_orderId      + "\n"
                + "clientId = "          + execution.m_clientId     + "\n"
                + "conid = "             + contract.m_conId         + "\n"
                + "symbol = "            + contract.m_symbol        + "\n"
                + "secType = "           + contract.m_secType       + "\n"
                + "expiry = "            + contract.m_expiry        + "\n"
                + "strike = "            + contract.m_strike        + "\n"
                + "right = "             + contract.m_right         + "\n"
                + "multiplier = "        + contract.m_multiplier    + "\n"
                + "exchange = "          + contract.m_exchange      + "\n"
                + "primaryExch = "       + contract.m_primaryExch   + "\n"
                + "currency = "          + contract.m_currency      + "\n"
                + "localSymbol = "       + contract.m_localSymbol   + "\n"
                + "tradingClass = "      + contract.m_tradingClass  + "\n"
                + "execId = "            + execution.m_execId       + "\n"
                + "time = "              + execution.m_time         + "\n"
                + "acctNumber = "        + execution.m_acctNumber   + "\n"
                + "executionExchange = " + execution.m_exchange     + "\n"
                + "side = "              + execution.m_side         + "\n"
                + "shares = "            + execution.m_shares       + "\n"
                + "price = "             + execution.m_price        + "\n"
                + "permId = "            + execution.m_permId       + "\n"
                + "liquidation = "       + execution.m_liquidation  + "\n"
                + "cumQty = "            + execution.m_cumQty       + "\n"
                + "avgPrice = "          + execution.m_avgPrice     + "\n"
                + "orderRef = "          + execution.m_orderRef     + "\n"
                + "evRule = "            + execution.m_evRule       + "\n"
                + "evMultiplier = "      + execution.m_evMultiplier + "\n"
                + " ---- Execution Details end ----\n";
    }

    @Override
    public void execDetailsEnd(int reqId) {
        System.out.println("TWSClientInterface:execDetailsEnd()  was called");
    }

    @Override
    public void updateMktDepth(int tickerId, int position, int operation, int side, double price, int size) {
        System.out.println("TWSClientInterface:updateMktDepth -- "+tickerId+" "+position+" "+operation+" "+side+" "+price+" "+side);
    }

    @Override
    public void updateMktDepthL2(int tickerId, int position, String marketMaker, int operation, int side, double price, int size) {
        System.out.println("TWSClientInterface:updateMktDepthL2 -- "+tickerId+" "+position+" "+marketMaker+" "+operation+" "+side+" "+price+" "+side);
    }

    @Override
    public void updateNewsBulletin(int msgId, int msgType, String message, String origExchange) {
        System.out.println("MsgId=" + msgId + " :: MsgType=" + msgType +  " :: Origin=" + origExchange + " :: Message=" + message);
    }

    @Override
    public void managedAccounts(String accountsList) {

    }

    @Override
    public void receiveFA(int faDataType, String xml) {

    }

    @Override
    public void historicalData(int reqId, String date, double open, double high, double low, double close, int volume, int count, double WAP, boolean hasGaps) {

        // NOTE: this function gets called once for each epoch of data returned

        // create a string with an epoch of historical data
        String output = " id=       " + reqId  +
                        " date =    " + date   +
                        " open =    " + open   +
                        " high =    " + high   +
                        " low =     " + low    +
                        " close =   " + close  +
                        " volume =  " + volume +
                        " count =   " + count  +
                        " WAP =     " + WAP    +
                        " hasGaps = " + hasGaps;

        // jaber-jaw ...
        System.out.println(output);
    }

    @Override
    public void scannerParameters(String xml) {

    }

    @Override
    public void scannerData(int reqId, int rank, ContractDetails contractDetails, String distance, String benchmark, String projection, String legsStr) {

    }

    @Override
    public void scannerDataEnd(int reqId) {

    }

    @Override
    public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count) {

        // create a string with all the real-time bar information
        String output = " id    = " + reqId  +
                        " time  = " + time   +
                        " open  = " + open   +
                        " high  = " + high   +
                        " low   = " + low    +
                        " close = " + close  +
                        " volume= " + volume +
                        " WAP   = " + wap    +
                        " count = " + count  ;

        // blab about it
        System.out.println(output);
    }

    @Override
    public void currentTime(long time) {

    }

    @Override
    public void fundamentalData(int reqId, String data) {

    }

    @Override
    public void deltaNeutralValidation(int reqId, UnderComp underComp) {

    }

    @Override
    public void tickSnapshotEnd(int reqId) {

    }

    @Override
    public void marketDataType(int reqId, int marketDataType) {

    }

    @Override
    public void commissionReport(CommissionReport commissionReport) {

    }

    @Override
    public void position(String account, Contract contract, int pos, double avgCost) {
        String msg = " ---- Position begin ----\n"
                + "account = " + account + "\n"
                + "conid = " + contract.m_conId + "\n"
                + "symbol = " + contract.m_symbol + "\n"
                + "secType = " + contract.m_secType + "\n"
                + "expiry = " + contract.m_expiry + "\n"
                + "strike = " + contract.m_strike + "\n"
                + "right = " + contract.m_right + "\n"
                + "multiplier = " + contract.m_multiplier + "\n"
                + "exchange = " + contract.m_exchange + "\n"
                + "primaryExch = " + contract.m_primaryExch + "\n"
                + "currency = " + contract.m_currency + "\n"
                + "localSymbol = " + contract.m_localSymbol + "\n"
                + "tradingClass = " + contract.m_tradingClass + "\n"
                + "position = " + Util.IntMaxString(pos) + "\n"
                + "averageCost = " + Util.DoubleMaxString(avgCost) + "\n"
                + " ---- Position end ----\n";
        System.out.println(msg);
    }

    @Override
    public void positionEnd() {
        System.out.println("TWSClientInterface:positionEnd()  was called");

    }

    @Override
    public void accountSummary(int reqId, String account, String tag, String value, String currency) {
        String msg = "  reqId = "    + reqId
                + "  account = "  + account
                + "  tag = "      + tag
                + "  value = "    + value
                + "  currency = " + currency;

        System.out.println(msg);
    }

    @Override
    public void accountSummaryEnd(int reqId) {
        System.out.println("TWSClientInterface:accountSummaryEnd(reqId)  was called");
    }

    @Override
    public void verifyMessageAPI(String apiData) {

    }

    @Override
    public void verifyCompleted(boolean isSuccessful, String errorText) {

    }

    @Override
    public void displayGroupList(int reqId, String groups) {

    }

    @Override
    public void displayGroupUpdated(int reqId, String contractInfo) {

    }

    @Override
    public void error(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void error(String str) {
        System.out.println(str);
    }

    @Override
    public void error(int id, int errorCode, String errorMsg) {
        System.out.println(" "+id+" "+errorCode+": "+errorMsg);
    }

    @Override
    public void connectionClosed() {
        System.out.println("connection closed ...");
    }
}
