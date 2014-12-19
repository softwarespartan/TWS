package com.tws;

import java.util.Date;

/**
 * Created by abelbrown on 12/8/14.
 */
public class RealtimeBar {

    public class Event extends java.util.EventObject implements com.tws.TWSEvent{

        public final Bar  bar ;
        public final Date date;

        Event(Object obj, Bar bar) { super(obj); this.bar = bar; this.date = new Date(); }
    }

    public interface Listener extends java.util.EventListener {
        void realtimeBar(Event event);
    }
}
