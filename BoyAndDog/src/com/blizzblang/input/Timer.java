package com.blizzblang.input;

public class Timer 
{
	private long Start=0;
	private long End=0;
	private boolean Started=false;
	private boolean Ended=false;
	public Timer()
	{
		
	}
	public void start()
	{
		Start = getTime()+0;
		Started=true;
	}
	public long getDifference()
	{
		return End-Start;
	}
	public double getSecondsDifference()
	{
		return getDifference()*0.001;
	}
	public void stop()
	{
		End = getTime()+0;
		Ended=true;
	}
	public void reset()
	{
		Start=0;
		End=0;
		Started=false;
		Ended=false;
	}
	public long getTime()
	{
		return System.currentTimeMillis();
	}
}
