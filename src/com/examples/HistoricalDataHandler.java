package com.examples;

import com.tws.EmptyWrapper;

import java.util.concurrent.atomic.AtomicBoolean;

public class HistoricalDataHandler extends EmptyWrapper {

    private final StringBuilder stringBuilder;
    private final AtomicBoolean requestStatus;

    public HistoricalDataHandler(){
        super();
        this.stringBuilder = new StringBuilder();
        this.requestStatus = new AtomicBoolean(false);
    }

    @Override
    public void historicalData(int reqId, String date, double open, double high, double low, double close, int volume, int count, double WAP, boolean hasGaps) {

        // NOTE: this function gets called once for each epoch of data returned

        // check for finish
        if (date.startsWith("finished"))
        {
            this.requestStatus.set(true); return;
        }

        this.stringBuilder.append(reqId ); this.stringBuilder.append(" ");
        this.stringBuilder.append(date  ); this.stringBuilder.append(" ");
        this.stringBuilder.append(open  ); this.stringBuilder.append(" ");
        this.stringBuilder.append(high  ); this.stringBuilder.append(" ");
        this.stringBuilder.append(low   ); this.stringBuilder.append(" ");
        this.stringBuilder.append(close ); this.stringBuilder.append(" ");
        this.stringBuilder.append(volume); this.stringBuilder.append(" ");
        this.stringBuilder.append(count ); this.stringBuilder.append(" ");
        this.stringBuilder.append(WAP   ); this.stringBuilder.append(" ");
    }

    public boolean isFinished() {
        return this.requestStatus.get();
    }

    public String getResult(){

        while(!this.isFinished()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this.stringBuilder.toString();
    }
}
