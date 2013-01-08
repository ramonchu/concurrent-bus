package com.arteco.concurrentbus;

import junit.framework.Assert;

import org.junit.Test;

import com.arteco.concurrentbus.event.SimpleEvent;

public class TestCase {

	@Test
	public void testBus() throws InterruptedException {

		SimpleEventHandler handler1 = new SimpleEventHandler();
		SimpleEventHandler handler2 = new SimpleEventHandler();

		ConcurrentBus<SimpleEvent> b = new ConcurrentBus<SimpleEvent>(128);
		b.addHandler(handler1);
		b.addHandler(handler2);

		b.post(new SimpleEvent());
		b.post(new SimpleEvent());
		b.post(new SimpleEvent());
		b.post(new SimpleEvent());
		b.post(new SimpleEvent());
		b.post(new SimpleEvent());
		b.post(new SimpleEvent());
		b.post(new SimpleEvent());
		b.post(new SimpleEvent());
		b.post(new SimpleEvent());

		b.terminate();

		Assert.assertTrue(handler1.messages == 10);
		Assert.assertTrue(handler2.messages == 10);
	}
}
