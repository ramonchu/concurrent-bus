package com.arteco.concurrentbus;

import com.arteco.concurrentbus.event.SimpleEvent;
import com.arteco.concurrentbus.handler.EventHandler;

public class SimpleEventHandler implements EventHandler<SimpleEvent> {

	public int messages = 0;

	public void onEvent(SimpleEvent newT) {
		System.out.println(newT.toString());
		messages++;

	}

}
