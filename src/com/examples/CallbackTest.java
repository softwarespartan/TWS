package com.examples;

import com.tws.EmptyWrapper;

public class CallbackTest extends EmptyWrapper {

    public TWSListener object;

    public class RealTimeBarEvent extends java.util.EventObject {
        public final double reqId ;
        public final double time  ;
        public final double open  ;
        public final double high  ;
        public final double low   ;
        public final double close ;
        public final double volume;
        public final double wap   ;
        public final double count ;

        RealTimeBarEvent(Object obj, int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count) {
            super(obj);
            this.reqId  = reqId ;
            this.time   = time  ;
            this.open   = open  ;
            this.high   = high  ;
            this.low    = low   ;
            this.close  = close ;
            this.volume = volume;
            this.wap    = wap   ;
            this.count  = count ;
        }
    }

    public interface TWSListener extends java.util.EventListener {
        void realtimeBar(RealTimeBarEvent event);
    }

    public void addTWSListener   (TWSListener listener) {
        System.out.println("Listener has been added ...");
        this.object = listener;
    }

    public void removeTWSListener(TWSListener listener) {
        System.out.println("remove Listener has been called ...");
        this.object = null    ;
    }

    @Override
    public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count){

        // make sure we don't call on null
        if (this.object == null) {return;}

        // comfort signal
        System.out.println("calling func ...");

        // make the call
        this.object.realtimeBar(
                new RealTimeBarEvent(this, reqId,time,open,high,low,close,volume,wap,count)
        );
    }
}
