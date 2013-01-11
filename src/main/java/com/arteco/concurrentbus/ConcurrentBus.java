package com.arteco.concurrentbus;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import com.arteco.concurrentbus.event.Event;
import com.arteco.concurrentbus.event.TerminateEvent;
import com.arteco.concurrentbus.handler.EventHandler;
import com.arteco.concurrentbus.handler.HandlerRunnable;

public class ConcurrentBus<T extends Event> {

	private Set<HandlerRunnable<T>> handlers = new ConcurrentSkipListSet<HandlerRunnable<T>>();
	private int capacity;

	public void terminate() throws InterruptedException {
		post(new TerminateEvent());
		for (HandlerRunnable<T> handler : handlers) {
			handler.join();
		}
	}

	public void post(Event t) {
		for (HandlerRunnable<T> handler : handlers) {
			try {
				handler.queue.put(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public ConcurrentBus(int capacity) {
		this.capacity = capacity;
	}

	public void addHandler(EventHandler<T> handler) {
		HandlerRunnable<T> handlerRunable = new HandlerRunnable<T>(capacity, handler);
		this.handlers.add(handlerRunable);
		handlerRunable.start();
	}

}
