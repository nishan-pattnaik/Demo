package com.nishan;

import java.util.LinkedList;
import java.util.List;
//cc
public class PC {
	private List<Integer>li = new LinkedList<Integer>();
	public int CAPACITY = 1;
	int count = 0;
	
	public void produce() throws InterruptedException {
		while(true) {
			synchronized(this) {
				if(li.size() == CAPACITY) {
				    wait();
				}
				li.add(count);
				System.out.println("Produce:" + count);
				count++;
				notifyAll();
				Thread.sleep(1000);
			}
		}
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			synchronized(this) {
				if(li.isEmpty()) {
					wait();
				}
				Integer result = li.remove(0);
				System.out.println("Consume:" + result);
				//count--;
				notifyAll();
				Thread.sleep(1000);
			}
		}
	}

	public static void main(String[] args) {
		PC pc = new PC();
		//TODO Auto-generated method stub
		Thread t1 = new Thread(() -> {
			try {
			   pc.produce();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
		});
		
		Thread t2 = new Thread(() -> {
			try {
			   pc.consume();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
		});
        t1.start();
        t2.start();
	}

}
