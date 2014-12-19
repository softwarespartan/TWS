package com.tws;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public final class Bar {

	public static final SimpleDateFormat DateTimeFormat
			= new SimpleDateFormat( "yyyyMMdd HH:mm:ss");

	public final double reqId ;
	public final double time  ;
	public final double high  ;
	public final double low   ;
	public final double open  ;
	public final double close ;
	public final double wap   ;
	public final double volume;
	public final double count ;

	public Bar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count)
	{
		// NOTE: time in seconds since 1970 Jan 1 00:00:00 GMT, volume in units of 100

		this.reqId  = reqId ;
		this.time   = time  ;
		this.high   = high  ;
		this.low    = low   ;
		this.open   = open  ;
		this.close  = close ;
		this.wap    = wap   ;
		this.volume = volume;
		this.count  = count ;
	}

	public Bar (int reqId, String time, double open, double high, double low, double close, long volume, double wap, int count)
			throws ParseException {

		this(reqId,Bar.DateTimeFormat.parse((time.length()==8)?time+="  12:00:00":time).getTime()/1000,open,high,low,close,volume,wap,count);

	}

	public String dtstr() {
		return DateTimeFormat.format( new Date((long)this.time * 1000) );
	}

	@Override public String toString() {
		return String.format(
				"%7d %s %9.2f %9.2f %9.2f %9.2f %5d %5d",
				(int)this.reqId,dtstr(), this.open, this.high, this.low, this.close, (int)this.volume, (int)this.count
		);
	}

	public static void main(String[] args) throws ParseException {

		System.out.println( new Bar(0,1417719790,75.35,75.37,75.35,75.36,12,75.36,11));

		System.out.println( new Bar(0,"20141204",75.35,75.37,75.35,75.36,12,75.36,11));
	}
}