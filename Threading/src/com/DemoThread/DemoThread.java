package com.DemoThread;

class PrintAllNumber {
	private boolean isOddTrue = true;

	synchronized public void oddPrint(int number) {
		try {

			while (!isOddTrue) {
				wait();
			}
			System.out.println(number);
			notify();
			isOddTrue = false;

		} catch (InterruptedException ie) {
			ie.printStackTrace();
			// TODO: handle exception
		}
	}
	synchronized public void evenPrint(int number)
	{
		try {
			while(isOddTrue)
			{
				wait();
			}
			System.out.println(number);
			notify();
			isOddTrue=true;
		}catch (InterruptedException ie) {
			ie.printStackTrace();
			// TODO: handle exception
		}
	}
}

class OddThread extends Thread{
	private PrintAllNumber print;
	public OddThread(PrintAllNumber print) {
		this.print=print;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
	for(int i=1;i<=19;i+=2)
	{
		print.oddPrint(i);
	}
	}
}

class EvenThread extends Thread{
	private PrintAllNumber print;
	public EvenThread(PrintAllNumber print) {
		this.print=print;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	for(int i=2;i<=20;i+=2)
	{
		print.evenPrint(i);
	}
	}
}
public class DemoThread {
	public static void main(String[] args) {
PrintAllNumber print=new PrintAllNumber();
OddThread objTh1=new OddThread(print);
EvenThread objTh2=new EvenThread(print);
objTh1.start();
objTh2.start();
	}
}
