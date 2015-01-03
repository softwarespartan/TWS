package com.tws;

import com.ib.client.*;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.*;

public class Handler extends EmptyWrapper{

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public final ConcurrentHashMap<UUID, TWSEvent> eventQueue = new ConcurrentHashMap<>();



    private final Set<NotificationListener> notificationListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> realTimeBarListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> historicalDataListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> positionListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> accountSummaryListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> accountUpdateListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> portfolioUpdateListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> marketDataListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> marketMetadataListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> marketDepthListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> contractDetailsListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> openOrderListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> orderStatusListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> executionDetailsListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> nextOrderIdListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> errorListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());

    private final Set<NotificationListener> scannerDataListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<NotificationListener,Boolean>());



    private final ConcurrentHashMap<Integer,HistoricalDataEvent> historicalDataMap = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<Integer,PositionsEvent>      positionMap       = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<Integer,AccountSummaryEvent> accountSummaryMap = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<Integer,ScannerDataEvent>    scannerDataMap    = new ConcurrentHashMap<>();



    public void addNotificationListener      (NotificationListener listener) {
        System.out.println("notification listener has been added");
        this.notificationListeners.add(listener);
    }

    public void removeNotificationListener   (NotificationListener listener) {
        System.out.println("notification listener has been removed");
        this.notificationListeners.remove(listener);
    }



    public void addHistoricalDataListener    (NotificationListener listener) {
        System.out.println("historical data listener has been added");
        this.historicalDataListeners.add(listener);
    }

    public void removeHistoricalDataListener (NotificationListener listener) {
        System.out.println("historical data listener has been removed");
        this.historicalDataListeners.remove(listener);
    }



    public void addRealTimeBarListener       (NotificationListener listener) {
        System.out.println("realtime bar listener has been added");
        this.realTimeBarListeners.add(listener);
    }

    public void removeRealTimeBarListener    (NotificationListener listener) {
        System.out.println("realtime bar listener has been removed");
        this.realTimeBarListeners.remove(listener);
    }



    public void addPositionListener          (NotificationListener listener) {
        System.out.println("position listener has been added");
        this.positionListeners.add(listener);
    }

    public void removePositionListener       (NotificationListener listener) {
        System.out.println("position listener has been removed");
        this.positionListeners.remove(listener);
    }



    public void addAccountUpdateListener     (NotificationListener listener) {
        System.out.println("account update listener has been added");
        this.accountUpdateListeners.add(listener);
    }

    public void removeAccountUdateListener   (NotificationListener listener) {
        System.out.println("account update listener has been removed");
        this.accountUpdateListeners.remove(listener);
    }



    public void addPortfolioUpdateListener   (NotificationListener listener) {
        System.out.println("portfolio update listener has been added");
        this.portfolioUpdateListeners.add(listener);
    }

    public void removePortfolioUdateListener (NotificationListener listener) {
        System.out.println("portfolio update listener has been removed");
        this.portfolioUpdateListeners.remove(listener);
    }



    public void addAccountSummaryListener    (NotificationListener listener) {
        System.out.println("account summary listener has been added");
        this.accountSummaryListeners.add(listener);
    }

    public void removeAccountSummaryListener (NotificationListener listener) {
        System.out.println("account summary listener has been removed");
        this.accountSummaryListeners.remove(listener);
    }



    public void addMarketDataListener        (NotificationListener listener) {
        System.out.println("market data listener has been added");
        this.marketDataListeners.add(listener);
    }

    public void removeMarketDataListener     (NotificationListener listener) {
        System.out.println("market data listener has been removed");
        this.marketDataListeners.remove(listener);
    }



    public void addMarketMetadataListener    (NotificationListener listener) {
        System.out.println("market metadata listener has been added");
        this.marketMetadataListeners.add(listener);
    }

    public void removeMarketMetadataListener (NotificationListener listener) {
        System.out.println("market metadata listener has been removed");
        this.marketMetadataListeners.remove(listener);
    }



    public void addMarketDepthListener       (NotificationListener listener) {
        System.out.println("market depth listener has been added");
        this.marketDepthListeners.add(listener);
    }

    public void removeMarketDepthListener    (NotificationListener listener) {
        System.out.println("market depth listener has been removed");
        this.marketDepthListeners.remove(listener);
    }



    public void addContractDetailsListener   (NotificationListener listener) {
        System.out.println("contract details listener has been added");
        this.contractDetailsListeners.add(listener);
    }

    public void removeContractDetailsListener(NotificationListener listener) {
        System.out.println("contract details listener has been removed");
        this.contractDetailsListeners.remove(listener);
    }



    public void addOpenOrderListener         (NotificationListener listener) {
        System.out.println("open order listener has been added");
        this.openOrderListeners.add(listener);
    }

    public void removeOpenOrderListener      (NotificationListener listener) {
        System.out.println("open order listener has been removed");
        this.openOrderListeners.remove(listener);
    }



    public void addOrderStatusListener       (NotificationListener listener) {
        System.out.println("order status listener has been added");
        this.orderStatusListeners.add(listener);
    }

    public void removeOrderStatusListener    (NotificationListener listener) {
        System.out.println("order status listener has been removed");
        this.orderStatusListeners.remove(listener);
    }



    public void addExecutionDetailsListener  (NotificationListener listener) {
        System.out.println("execution details listener has been added");
        this.executionDetailsListeners.add(listener);
    }

    public void removeExecutionDetilsListener(NotificationListener listener) {
        System.out.println("execution details listener has been removed");
        this.executionDetailsListeners.remove(listener);
    }



    public void addNextOrderIdListener       (NotificationListener listener) {
        System.out.println("nextOrderId listener has been added");
        this.nextOrderIdListeners.add(listener);
    }

    public void removeNextOrderIdListener    (NotificationListener listener) {
        System.out.println("nextOrderId listener has been removed");
        this.nextOrderIdListeners.remove(listener);
    }



    public void addErrorListener             (NotificationListener listener) {
        System.out.println("error listener has been added");
        this.errorListeners.add(listener);
    }

    public void removeErrorListener          (NotificationListener listener) {
        System.out.println("error listener has been removed");
        this.errorListeners.remove(listener);
    }



    public void addScannerDataListener       (NotificationListener listener) {
        System.out.println("error listener has been added");
        this.errorListeners.add(listener);
    }

    public void removeScannerDataListener    (NotificationListener listener) {
        System.out.println("error listener has been removed");
        this.errorListeners.remove(listener);
    }



    public TWSEvent getEvent(UUID uuid){ return this.eventQueue.get(uuid); }



    private void notify(Set<NotificationListener> listeners, NotificationEvent event)     {

        // submit notification event
        //this.executorService.submit( new NotificationAction( listeners,event ) );

        // route everything to notification listeners for now
        try {
            this.executorService.submit(new NotificationAction(this.notificationListeners, event));
        }catch(RejectedExecutionException e){
            // no op (?)
        }
    }

    private void processEvent(com.tws.TWSEvent event, Set<NotificationListener> listeners){

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put( uuid, event );

        // notify market depth listeners
        this.notify(listeners,new NotificationEvent(this,uuid));
    }

    public void shutdown(){

        // get the ball rolling
        this.executorService.shutdown();

        try {
            // Wait a while for existing tasks to terminate
            if (!this.executorService.awaitTermination(2, TimeUnit.SECONDS)) {

                // Cancel currently executing tasks
                this.executorService.shutdownNow();

                // Wait a while for tasks to respond to being cancelled
                if (!this.executorService.awaitTermination(2, TimeUnit.SECONDS))
                    System.err.println("handler executor service did not terminate");
            }
        } catch (InterruptedException ie) {

            // (Re-)Cancel if current thread also interrupted
            this.executorService.shutdownNow();

            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }



    public final class     NotificationEvent    extends    java.util.EventObject   {
        public final UUID    uuid   ;
        public NotificationEvent(Handler obj, UUID uuid) { super(obj); this.uuid = uuid; }
    }

    public       interface NotificationListener extends    java.util.EventListener {
        void TWSNotification(NotificationEvent event);
    }

    public final class     NotificationAction   implements Runnable                {
        private final Set<NotificationListener> listeners ;
        private final NotificationEvent         event     ;

        public NotificationAction(Set<NotificationListener> l,NotificationEvent e){
            this.listeners = l; this.event = e;
        }

        @Override
        public void run() {

            /** Java 8
            this.listeners.stream().forEach(l -> l.TWSNotification(this.event));
             */

            for (NotificationListener l : this.listeners){
                l.TWSNotification(this.event);
            }
        }
    }



    public class Event<T>          extends java.util.EventObject implements com.tws.TWSEvent {
        public final T    data;
        public final Date date;

        public Event(Object source, T data) { super(source); this.data = data; this.date = new Date(); }
    }

    public class AggregateEvent<T> extends java.util.EventObject implements com.tws.TWSEvent {
        public final int    reqId;
        public final Set<T> data ;
        public final Date   date ;

        AggregateEvent(Object obj,int reqId){
            super(obj); this.reqId = reqId;  this.data = new HashSet<>(); this.date = new Date();
        }
    }



    @Override
    public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count) {
        this.processEvent(
                new RealTimeBarEvent(
                    this,new Bar(reqId,time,open,high,low,close,volume,wap,count)
                ),
                this.realTimeBarListeners
        );
    }

    public final class RealTimeBarEvent extends Event<Bar> {
        RealTimeBarEvent(Object obj, Bar bar) { super(obj,bar); }
    }



    @Override
    public void historicalData(int reqId, String date, double open, double high, double low, double close, int volume, int count, double wap, boolean hasGaps) {

        // box the request id for hashcode in map
        final Integer integerReqId = new Integer(reqId);

        // next check if there is an entry in the historical data map for this request id
        if (!this.historicalDataMap.containsKey(integerReqId)){
            this.historicalDataMap.put(integerReqId, new HistoricalDataEvent(this,reqId));
        }

        // if we're finished then create event and notification
        if (date.startsWith("finished")){

            // extract the historical data event from the map
            HistoricalDataEvent historicalDataEvent = this.historicalDataMap.get(integerReqId);

            // remove from the data map
            this.historicalDataMap.remove(integerReqId);

            // queue this up for notification
            this.processEvent( historicalDataEvent, this.historicalDataListeners);

            // we're done
            return;
        }

        try{
            // add this bar in the appropriate historical data event (i.e. build the event call by call)
            this.historicalDataMap.get(integerReqId).data
                    .add(new Bar(reqId, date, open, high, low, close, volume, wap, count));
        } catch (ParseException e){

            // there was a problem parsing the date string
            e.printStackTrace();
        }
    }

    public final class HistoricalDataEvent extends AggregateEvent<Bar> {
        HistoricalDataEvent(Object obj,int reqId) { super(obj,reqId); }
    }



    @Override
    public void position(String account, Contract contract, int pos, double avgCost) {

        // next check if there is an entry in the historical data map for this request id
        // NOTE:  for positions always set request id to 0 [yuk, i know].
        if (!this.positionMap.containsKey(0)){
            this.positionMap.put(0, new PositionsEvent(this,0));
        }

        // add this position in the appropriate position event (i.e. build the event call by call)
        this.positionMap.get(0).data.add(new Position(account, contract, pos, avgCost));
    }

    @Override
    public void positionEnd()                                                        {

        // extract positions event from the map
        PositionsEvent positionsEvent = this.positionMap.get(0);

        // remove from the data map
        this.positionMap.remove(0);

        // queue this up for notification
        this.processEvent(positionsEvent,this.positionListeners);
    }

    public final class PositionsEvent extends AggregateEvent<Position>               {
        PositionsEvent(Object obj,int reqId) { super(obj,reqId); }
    }



    @Override
    public void accountSummary(int reqId, String account, String key, String value, String currency) {

        // next check if there is an entry in the historical data map for this request id
        if (!this.accountSummaryMap.containsKey(reqId)){
            this.accountSummaryMap.put(reqId, new AccountSummaryEvent(this,reqId));
        }

        // add this position in the appropriate event (i.e. build the event call by call)
        this.accountSummaryMap.get(reqId).data.add(new AccountAttribute(key,value,currency,account));
    }

    @Override
    public void accountSummaryEnd(int reqId)                                                         {

        // extract the final aggregated event from the map
        AccountSummaryEvent summaryEvent = this.accountSummaryMap.get(reqId);

        // remove from the event map
        this.accountSummaryMap.remove(reqId);

        // process the event
        this.processEvent( summaryEvent, this.accountSummaryListeners);
    }

    public final class AccountSummaryEvent extends AggregateEvent<AccountAttribute>                  {
        public AccountSummaryEvent(Object obj,int reqId) { super(obj,reqId); }
    }



    @Override
    public void updateAccountValue(String key, String value, String currency, String accountName) {
        this.processEvent(
                new AccountUpdateEvent( this,
                        new AccountAttribute(key,value,currency,accountName)
                ),
                this.accountUpdateListeners
        );
    }

    public final class AccountUpdateEvent extends Event<AccountAttribute>                         {
        public AccountUpdateEvent(Object source, AccountAttribute data) { super(source, data); }
    }



    @Override
    public void updatePortfolio(Contract contract, int position, double marketPrice, double marketValue, double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {
        this.processEvent(
                new PortfolioUpdateEvent( this,
                        new PortfolioUpdate(contract,position,marketPrice,marketValue,averageCost,unrealizedPNL,realizedPNL,accountName)
                ),
                this.portfolioUpdateListeners
        );
    }

    public final class PortfolioUpdateEvent extends Event<PortfolioUpdate>{
        public PortfolioUpdateEvent(Object source, PortfolioUpdate data) { super(source, data); }
    }



    @Override
    public void tickPrice(int reqId, int field, double price, int canAutoExecute) {

        if (field < 10) {
            this.processEvent(
                    new MarketDataEvent(this,
                            new MarketData(reqId, field, TickType.getField(field), price)
                    ),
                    this.marketDataListeners
            );
        } else {
            this.processEvent(
                    new MarketMetadataEvent(this,
                            new MarketMetadata(reqId, field, TickType.getField(field), Double.toString(price))
                    ),
                    this.marketMetadataListeners
            );
        }
    }

    @Override
    public void tickSize(int reqId, int field, int size)                          {

        if (field < 10) {
            this.processEvent(
                    new MarketDataEvent(this,
                            new MarketData(reqId, field, TickType.getField(field), size)
                    ),
                    this.marketDataListeners
            );
        } else {
            this.processEvent(
                    new MarketMetadataEvent(this,
                            new MarketMetadata(reqId, field, TickType.getField(field), Integer.toString(size))
                    ),
                    this.marketMetadataListeners
            );
        }
    }

    public final class MarketDataEvent extends Event<MarketData>                  {
        public MarketDataEvent(Object source, MarketData data) { super(source, data); }
    }



    @Override
    public void tickGeneric(int reqId, int tickType, double value)      {
        this.processEvent(
                new MarketMetadataEvent(
                        this,new MarketMetadata(reqId,tickType,TickType.getField(tickType),Double.toString(value))
                ),
                this.marketMetadataListeners
        );
    }

    @Override
    public void tickString(int reqId, int tickType, String value)       {
        this.processEvent(
                new MarketMetadataEvent(
                        this,new MarketMetadata(reqId,tickType,TickType.getField(tickType),value)
                ),
                this.marketMetadataListeners
        );
    }

    public final class MarketMetadataEvent extends Event<MarketMetadata>{
        public MarketMetadataEvent(Object source, MarketMetadata data) { super(source, data); }
    }



    @Override
    public void updateMktDepth  (int reqId, int position,                     int operation, int side, double price, int size) {

        this.processEvent(
                new MarketDepthEvent(
                        this, new MarketDepth(reqId, position, operation, side, price, size)
                ),
                this.marketDepthListeners
        );
    }

    @Override
    public void updateMktDepthL2(int reqId, int position, String marketMaker, int operation, int side, double price, int size) {
        this.processEvent(
                new MarketDepthEvent(
                        this,new MarketDepth(reqId,position,marketMaker,operation,side,price,size)
                ),
                this.marketDepthListeners
        );
    }

    public final class MarketDepthEvent extends Event<MarketDepth>                                                             {
        public MarketDepthEvent(Object source, MarketDepth data) { super(source, data); }
    }



    @Override
    public void contractDetails(int reqId, com.ib.client.ContractDetails contractDetails) {
        this.processEvent(
                new ContractDetailsEvent(
                        this,new com.tws.ContractDetails(reqId,contractDetails)
                ),
                this.contractDetailsListeners
        );
    }

    @Override
    public void contractDetailsEnd(int reqId)                                             {
        // no op
    }

    public final class ContractDetailsEvent extends Event<com.tws.ContractDetails>        {
        public ContractDetailsEvent(Object source, com.tws.ContractDetails data) { super(source, data); }
    }



    @Override
    public void openOrder(int orderId, Contract contract, Order order, OrderState orderState){
        this.processEvent(
                new OpenOrderEvent( this,
                        new OpenOrder(orderId,contract,order,orderState)
                ),
                this.openOrderListeners
        );
    }

    public final class OpenOrderEvent extends Event<OpenOrder>                               {
        public OpenOrderEvent(Object source, OpenOrder data)  { super(source, data); }
    }



    @Override
    public void orderStatus(int orderId, String status, int filled, int remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {
        this.processEvent(
                new OrderStatusEvent( this,
                        new OrderStatus(orderId,status,filled,remaining,avgFillPrice,permId,parentId,lastFillPrice,clientId,whyHeld)
                ),
                this.orderStatusListeners
        );
    }

    public final class OrderStatusEvent extends Event<OrderStatus>{
        public OrderStatusEvent(Object source, OrderStatus data)  { super(source, data); }
    }



    @Override
    public void execDetails(int reqId, Contract contract, Execution execution){
        this.processEvent(
                new ExecutionDetailsEvent(this,
                        new ExecutionDetails(contract, execution)
                ),
                this.executionDetailsListeners
        );
    }

    public final class ExecutionDetailsEvent extends Event<ExecutionDetails>  {
        public ExecutionDetailsEvent(Object source, ExecutionDetails data) { super(source, data); }
    }



    @Override
    public void nextValidId(int orderId)                           {
        this.processEvent(
                new NextOrderIdEvent( this,
                        new NextOrderId(orderId)
                ),
                this.nextOrderIdListeners
        );
    }

    public final class NextOrderIdEvent extends Event<NextOrderId> {
        public NextOrderIdEvent(Object source, NextOrderId data) { super(source, data); }
    }



    @Override
    public void scannerData(int reqId, int rank, com.ib.client.ContractDetails contractDetails, String distance, String benchmark, String projection, String legsStr) {

        Integer integerReqId = new Integer(reqId);

        // next check if there is an entry in the historical data map for this request id
        if (!this.scannerDataMap.containsKey(integerReqId)){
            this.scannerDataMap.put(integerReqId, new ScannerDataEvent(this,reqId));
        }

        // add this position in the appropriate event (i.e. build the event call by call)
        this.scannerDataMap.get(integerReqId)
                .data.add( new ScannerData(rank,contractDetails,distance,benchmark,projection,legsStr));
    }

    @Override
    public void scannerDataEnd(int reqId)                                  {

        // extract the final aggregated event from the map
        ScannerDataEvent event = this.scannerDataMap.get(reqId);

        // remove from the event map
        this.scannerDataMap.remove(reqId);

        // process the event
        this.processEvent( event, this.scannerDataListeners);
    }

    public final class ScannerDataEvent extends AggregateEvent<ScannerData>{
        ScannerDataEvent(Object obj, int reqId) { super(obj, reqId); }
    }



    @Override
    public void error(Exception e)                            {
        this.processEvent(
                new ErrorEvent(this,
                        new com.tws.Error(e)
                ),
                this.errorListeners
        );
    }

    @Override
    public void error(String errorMessage)                    {
        this.processEvent(
                new ErrorEvent(this,
                        new com.tws.Error(errorMessage)
                ),
                this.errorListeners
        );
    }

    @Override
    public void error(int id, int errorCode, String errorMsg) {
        this.processEvent(
                new ErrorEvent(this,
                        new com.tws.Error(id, errorCode, errorMsg)
                ),
                this.errorListeners
        );
    }

    public final class ErrorEvent extends Event<com.tws.Error>{
        public ErrorEvent(Object source, Error data) { super(source, data); }
    }
}
