package com.arteco.concurrentbus.handler;

import java.util.concurrent.ArrayBlockingQueue;

import com.arteco.concurrentbus.event.Event;
import com.arteco.concurrentbus.event.TerminateEvent;

public class HandlerRunnable<T extends Event> extends Thread implements Comparable<HandlerRunnable<T>> {

	private EventHandler<T> handler;
	public ArrayBlockingQueue<Event> queue;

	public HandlerRunnable(int capacity, EventHandler<T> handler) {
		this.handler = handler;
		this.queue = new ArrayBlockingQueue<Event>(capacity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		try {
			while (true) {
				Event newT = queue.take();
				if (newT instanceof TerminateEvent)
					break;
				handler.onEvent((T) newT);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int compareTo(HandlerRunnable<T> o) {
		return this.toString().compareTo(o.toString());
	}

}
