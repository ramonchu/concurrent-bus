package com.arteco.concurrentbus.handler;

public interface EventHandler<T> {
	public void onEvent(T newT);
}
