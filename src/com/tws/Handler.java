package com.tws;

import com.ib.client.*;
import com.ib.client.ContractDetails;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Handler extends EmptyWrapper{

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public final ConcurrentHashMap<UUID, TWSEvent> eventQueue = new ConcurrentHashMap<>();



    private final Set<NotificationListener> notificationListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> realTimeBarListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> historicalDataListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> positionListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> accountSummaryListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> accountUpdateListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> marketDataListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> marketMetadataListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> marketDepthListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> contractDetailsListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> openOrderListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());

    private final Set<NotificationListener> orderStatusListeners
            = java.util.Collections.newSetFromMap(new ConcurrentHashMap<>());



    private final ConcurrentHashMap<Integer,HistoricalDataEvent> historicalDataMap = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<Integer,PositionsEvent>      positionMap       = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<Integer,AccountSummaryEvent> accountSummaryMap = new ConcurrentHashMap<>();



    public TWSEvent getEvent(UUID uuid){ return this.eventQueue.get(uuid); }



    public void addNotificationListener      (NotificationListener listener) {
        System.out.println("notification listener has been added");
        this.notificationListeners.add(listener);
    }

    public void removeNotificationListener   (NotificationListener listener) {
        System.out.println("notificationlistener has been removed");
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
        System.out.println("realtime bar listener has been added");
        this.positionListeners.add(listener);
    }

    public void removePositionListener       (NotificationListener listener) {
        System.out.println("realtime bar listener has been removed");
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
        System.out.println("market depth listener has been added");
        this.contractDetailsListeners.add(listener);
    }

    public void removeContractDetailsListener(NotificationListener listener) {
        System.out.println("market depth listener has been removed");
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



    private void notify(Set<NotificationListener> listeners, NotificationEvent event){

        // submit notification event
        //this.executorService.submit( new NotificationAction( listeners,event ) );

        // route everything to notification listeners for now
        this.executorService.submit( new NotificationAction( this.notificationListeners, event ) );
    }



    public class Event<T>          extends java.util.EventObject implements com.tws.TWSEvent {
        public final T    data;
        public final Date date;

        public Event(Object source, T data) { super(source); this.data = data; this.date = new Date(); }
    }

    public class AggregateEvent<T> extends java.util.EventObject implements com.tws.TWSEvent {
        public final Set<T> data;
        public final Date   date;

        AggregateEvent(Object obj){ super(obj); this.data = new HashSet<>(); this.date = new Date(); }
    }



    @Override
    public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count)
    {

        // create a random uuid for this event
        final UUID uuid = UUID.randomUUID();

        // put realtime bar event in the event queue with or without listeners
        this.eventQueue.put(uuid,new RealTimeBarEvent(
                    this,new Bar(reqId,time,open,high,low,close,volume,wap,count)
                )
        );

        // notify realtime bar listeners
        this.notify(this.realTimeBarListeners,new NotificationEvent(this,uuid));

        // comfort signal
        System.out.println("realtimeBar NotificationAction has been submitted");
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
            this.historicalDataMap.put(integerReqId, new HistoricalDataEvent(this));
            System.out.println("Initializing historical data mapping for reqId "+integerReqId);
        }

        // if we're finished then create event and notification
        if (date.startsWith("finished")){

            // generate unique event id for this
            final UUID uuid = UUID.randomUUID();

            // queue this up for notification
            this.eventQueue.put(uuid,this.historicalDataMap.get(integerReqId));

            // remove from the data map
            this.historicalDataMap.remove(integerReqId);

            // submit notification event
            this.executorService.submit(
                    new NotificationAction(
                            this.notificationListeners,new NotificationEvent(this,uuid)
                    )
            );

            System.out.println("NotificationAction for historical data request "+integerReqId+" has been submitted ...");

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
        HistoricalDataEvent(Object obj) { super(obj); }
    }



    @Override
    public void position(String account, Contract contract, int pos, double avgCost) {

        // box the request id for hashcode in map
        final Integer integerReqId = new Integer(0);

        // next check if there is an entry in the historical data map for this request id
        if (!this.positionMap.containsKey(integerReqId)){
            this.positionMap.put(integerReqId, new PositionsEvent(this));
            System.out.println("Initializing position mapping for reqId "+integerReqId);
        }

        // add this position in the appropriate position event (i.e. build the event call by call)
        this.positionMap.get(integerReqId).data.add(new Position(account, contract, pos, avgCost));
    }

    @Override
    public void positionEnd()                                                        {

        // box the request id for hashcode in map
        final Integer integerReqId = new Integer(0);

        // generate unique event id for this
        final UUID uuid = UUID.randomUUID();

        // queue this up for notification
        this.eventQueue.put(uuid,this.positionMap.get(integerReqId));

        // remove from the data map
        this.positionMap.remove(integerReqId);

        // submit notification event
        this.notify(this.positionListeners,new NotificationEvent(this,uuid));

        // comfort signal
        System.out.println("NotificationAction for position request has been submitted ...");
    }

    public final class PositionsEvent extends AggregateEvent<Position>               {
        PositionsEvent(Object obj) { super(obj); }
    }



    @Override
    public void accountSummary(int reqId, String account, String key, String value, String currency) {

        // box the request id for hashcode in map
        final Integer integerReqId = new Integer(reqId);

        // next check if there is an entry in the historical data map for this request id
        if (!this.accountSummaryMap.containsKey(integerReqId)){
            this.accountSummaryMap.put(integerReqId, new AccountSummaryEvent( this ));
            System.out.println("Initializing account summary mapping for reqId "+integerReqId);
        }

        // add this position in the appropriate event (i.e. build the event call by call)
        this.accountSummaryMap.get(integerReqId).data.add(new AccountAttribute(key,value,currency,account));
    }

    @Override
    public void accountSummaryEnd(int reqId)                                                         {

        // box the request id for hashcode in map
        final Integer integerReqId = new Integer(reqId);

        // generate unique event id for this
        final UUID uuid = UUID.randomUUID();

        // queue this up for notification
        this.eventQueue.put(uuid,this.accountSummaryMap.get(integerReqId));

        // remove from the data map
        this.accountSummaryMap.remove(integerReqId);

        // submit notification event
        this.notify(this.accountSummaryListeners,new NotificationEvent(this,uuid));

        // comfort signal
        System.out.println("NotificationAction for account summary request has been submitted ...");
    }

    public final class AccountSummaryEvent extends AggregateEvent<AccountAttribute>                  {
        public AccountSummaryEvent(Object obj) { super(obj); }
    }



    @Override
    public void updateAccountValue(String key, String value, String currency, String accountName) {

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put( uuid,
                new AccountUpdateEvent(
                        this,new AccountAttribute(key,value,currency,accountName)
                )
        );

        // notify market depth listeners
        this.notify(this.accountUpdateListeners,new NotificationEvent(this,uuid));
    }

    public final class AccountUpdateEvent extends Event<AccountAttribute>                         {
        public AccountUpdateEvent(Object source, AccountAttribute data) { super(source, data); }
    }



    @Override
    public void tickPrice(int reqId, int field, double price, int canAutoExecute) {

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put(uuid,new MarketDataEvent(
                        this,new MarketData(reqId,TickType.getField(field),price)
                )
        );

        // notify market data bar listeners
        this.notify(this.marketDataListeners,new NotificationEvent(this,uuid));

        // comfort signal
        System.out.println("market data NotificationAction has been submitted");
    }

    @Override
    public void tickSize(int reqId, int field, int size)                          {

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put(uuid,new MarketDataEvent(
                        this,new MarketData(reqId,TickType.getField(field),size)
                )
        );

        // notify realtime bar listeners
        this.notify(this.marketDataListeners,new NotificationEvent(this,uuid));

        // comfort signal
        System.out.println("market data NotificationAction has been submitted");
    }

    public final class MarketDataEvent extends Event<MarketData>                  {
        public MarketDataEvent(Object source, MarketData data) { super(source, data); }
    }



    @Override
    public void tickGeneric(int reqId, int tickType, double value)      {

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put(uuid,new MarketMetadataEvent(
                        this,new MarketMetadata(reqId,TickType.getField(tickType),Double.toString(value))
                )
        );

        // notify realtime bar listeners
        this.notify(this.marketMetadataListeners,new NotificationEvent(this,uuid));

        // comfort signal
        System.out.println("market metadata NotificationAction has been submitted");
    }

    @Override
    public void tickString(int reqId, int tickType, String value)       {

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put(uuid,new MarketMetadataEvent(
                        this,new MarketMetadata(reqId,TickType.getField(tickType),value)
                )
        );

        // notify realtime bar listeners
        this.notify(this.marketMetadataListeners,new NotificationEvent(this,uuid));

        // comfort signal
        System.out.println("market metadata NotificationAction has been submitted");
    }

    public final class MarketMetadataEvent extends Event<MarketMetadata>{
        public MarketMetadataEvent(Object source, MarketMetadata data) { super(source, data); }
    }



    @Override
    public void updateMktDepth  (int reqId, int position,                     int operation, int side, double price, int size) {

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put(uuid,new MarketDepthEvent(
                        this,new MarketDepth(reqId,position,operation,side,price,size)
                )
        );

        // notify market depth listeners
        this.notify(this.marketDepthListeners,new NotificationEvent(this,uuid));
    }

    @Override
    public void updateMktDepthL2(int reqId, int position, String marketMaker, int operation, int side, double price, int size) {

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put(uuid,new MarketDepthEvent(
                        this,new MarketDepth(reqId,position,marketMaker,operation,side,price,size)
                )
        );

        // notify market depth listeners
        this.notify(this.marketDepthListeners,new NotificationEvent(this,uuid));
    }

    public final class MarketDepthEvent extends Event<MarketDepth>                                                             {
        public MarketDepthEvent(Object source, MarketDepth data) { super(source, data); }
    }



    @Override
    public void contractDetails(int reqId, com.ib.client.ContractDetails contractDetails) {

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put(uuid,new ContractDetailsEvent(
                        this,new com.tws.ContractDetails(reqId,contractDetails)
                )
        );

        // notify market depth listeners
        this.notify(this.contractDetailsListeners,new NotificationEvent(this,uuid));
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

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put( uuid,
                new OpenOrderEvent( this,
                        new OpenOrder(orderId,contract,order,orderState)
                )
        );

        // notify market depth listeners
        this.notify(this.openOrderListeners,new NotificationEvent(this,uuid));
    }

    public final class OpenOrderEvent extends Event<OpenOrder>{
        public OpenOrderEvent(Object source, OpenOrder data)  { super(source, data); }
    }



    @Override
    public void orderStatus(int orderId, String status, int filled, int remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {

        // create a UUID for this event
        final UUID uuid = java.util.UUID.randomUUID();

        // put market data event in the event queue with or without listeners
        this.eventQueue.put( uuid,
                new OrderStatusEvent( this,
                        new OrderStatus(orderId,status,filled,remaining,avgFillPrice,permId,parentId,lastFillPrice,clientId,whyHeld)
                )
        );

        // notify market depth listeners
        this.notify(this.orderStatusListeners,new NotificationEvent(this,uuid));
    }

    public final class OrderStatusEvent extends Event<OrderStatus>{
        public OrderStatusEvent(Object source, OrderStatus data)  { super(source, data); }
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
            this.listeners.stream().forEach(l -> l.TWSNotification(this.event));
        }
    }

}
