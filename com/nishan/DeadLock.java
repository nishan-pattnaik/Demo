package com.nishan;

public class DeadLock {
	private final Object a = new Object();
	private final Object b = new Object();
	
	
	public void one() {
		while(true) {
			synchronized(a) {
				System.out.println(1);
			    synchronized(b) {
			    	System.out.println("Inside one");
			    }
			}
		}
	}
	
	public void two() {
		while(true) {
			synchronized(b) {
				System.out.println(2);
			    synchronized(a) {
			    	System.out.println("Inside two");
			    }
			}
		}
	}
	
	public static void main(String[] args) {
		DeadLock deadLock = new DeadLock();
		Thread t1 = new Thread(() -> deadLock.one());
		Thread t2 = new Thread(() -> deadLock.two());
		t1.start();
		t2.start();
	}

}
