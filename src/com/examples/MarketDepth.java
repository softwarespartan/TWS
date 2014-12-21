package com.examples;

import com.ib.client.*;
import com.tws.ContractFactory;

import java.util.HashMap;
import java.util.concurrent.*;

public class MarketDepth {

    public int reqId = 0;

    public final BlockingQueue<ContractDetails> results = new ArrayBlockingQueue<ContractDetails>(1000);

    public final HashMap contractIdMap = new HashMap();

    // create connection object for to communicate with TWS
    public final EClientSocket eClientSocket = new EClientSocket(new EWrapper() {

        @Override
        public void tickPrice(int tickerId, int field, double price, int canAutoExecute) {

        }

        @Override
        public void tickSize(int tickerId, int field, int size) {

        }

        @Override
        public void tickOptionComputation(int tickerId, int field, double impliedVol, double delta, double optPrice, double pvDividend, double gamma, double vega, double theta, double undPrice) {

        }

        @Override
        public void tickGeneric(int tickerId, int tickType, double value) {

        }

        @Override
        public void tickString(int tickerId, int tickType, String value) {

        }

        @Override
        public void tickEFP(int tickerId, int tickType, double basisPoints, String formattedBasisPoints, double impliedFuture, int holdDays, String futureExpiry, double dividendImpact, double dividendsToExpiry) {

        }

        @Override
        public void orderStatus(int orderId, String status, int filled, int remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {

        }

        @Override
        public void openOrder(int orderId, Contract contract, Order order, OrderState orderState) {

        }

        @Override
        public void openOrderEnd() {

        }

        @Override
        public void updateAccountValue(String key, String value, String currency, String accountName) {

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
            results.add(contractDetails);
        }

        @Override
        public void bondContractDetails(int reqId, ContractDetails contractDetails) {

        }

        @Override
        public void contractDetailsEnd(int reqId) {
            System.out.println("contractDetailEnd was called");
        }

        @Override
        public void execDetails(int reqId, Contract contract, Execution execution) {

        }

        @Override
        public void execDetailsEnd(int reqId) {

        }

        @Override
        public void updateMktDepth(int tickerId, int position, int operation, int side, double price, int size) {

            String exchange = Integer.toString(tickerId);

            if ( contractIdMap.containsKey(tickerId) ) exchange = (String) contractIdMap.get(tickerId);

            System.out.println("updateMktDepth:  "+exchange+" "+position+" "+operation+" "+side+" "+price+" "+size);

        }

        @Override
        public void updateMktDepthL2(int tickerId, int position, String marketMaker, int operation, int side, double price, int size) {

        }

        @Override
        public void updateNewsBulletin(int msgId, int msgType, String message, String origExchange) {

        }

        @Override
        public void managedAccounts(String accountsList) {

        }

        @Override
        public void receiveFA(int faDataType, String xml) {

        }

        @Override
        public void historicalData(int reqId, String date, double open, double high, double low, double close, int volume, int count, double WAP, boolean hasGaps) {

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

        }

        @Override
        public void positionEnd() {

        }

        @Override
        public void accountSummary(int reqId, String account, String tag, String value, String currency) {

        }

        @Override
        public void accountSummaryEnd(int reqId) {

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
            e.printStackTrace(); results.add(new ContractDetails());
        }

        @Override
        public void error(String str) {
            System.out.println(str); results.add(new ContractDetails());
        }

        @Override
        public void error(int id, int errorCode, String errorMsg) {
            System.out.println(" "+id+" "+errorCode+": "+errorMsg);

            if (id != -1) results.add(new ContractDetails());
        }

        @Override
        public void connectionClosed() {
            System.out.println("connection closed ...");
        }
    });

    public final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ContractDetails getContractDetails(Contract contract) throws InterruptedException,ExecutionException {

        this.eClientSocket.reqContractDetails(0,contract);

        ContractDetails contractDetails =  executorService.submit( () -> results.take() ).get();

        return contractDetails;
    }

    public void reqMarketDepth(String symbol, int numRows) throws ExecutionException, InterruptedException {

        Contract contract = ContractFactory.GenericStockContract(symbol);

        ContractDetails contractDetails = this.getContractDetails(contract);

        int id = this.reqId++;

        for (String exchange : contractDetails.m_validExchanges.split(",")) {

            System.out.println("requesting market depth from exchange: "+exchange);

            contract.m_primaryExch = exchange;
            contract.m_exchange    = exchange;

            this.contractIdMap.put(id,exchange);

            this.eClientSocket.reqMktDepth(id, contract, numRows, null);

            id = id + 1;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MarketDepth marketDepth = new MarketDepth();

        // try to connect to TWS
        marketDepth.eClientSocket.eConnect("127.0.0.1", 7496, 0);

        // initialize a contract for symbols to BUY
        //Contract contract = ContractFactory.GenericStockContract("FB");

        //"SMART","ISE","CHX","ARCA","ISLAND","VWAP","IBSX","DRCTEDGE","BEX","BATS","EDGEA","LAVA","CSFBALGO","JEFFALGO","BYX","IEX","TPLUS2","PSX"

        // set the exchange
        //contract.m_exchange = ""; contract.m_primaryExch = "";

        //ContractDetails contractDetails = marketDepth.getContractDetails(contract);

        //if ( contractDetails.m_summary.m_symbol != null)
        //    System.out.println(com.ib.client.EWrapperMsgGenerator.contractDetails(0,contractDetails));

        marketDepth.reqMarketDepth("SPY",5);

        //marketDepth.executorService.shutdown();



        // place the order
        //eClientSocket.reqMktDepth(6352, contract, 10, null);

//        // set the exchange
//        contract.m_exchange = "ARCA";
//
//        // place the order
//        eClientSocket.reqMktDepth(6353, contract, 10, null);
//
//        // set the exchange
//        contract.m_exchange = "BATS";
//
//        // place the order
//        eClientSocket.reqMktDepth(6354, contract, 10, null);
//
//        // set the exchange
//        contract.m_exchange = "EDGEA";
//
//        // place the order
//        eClientSocket.reqMktDepth(6355, contract, 10, null);
    }
}
