package com.charles.listener;
/**
 * Simple test program
 * @author Charles
 *
 */
public class ListenerProxyTest {
	private interface Callback {
		public void callback(String message);
	}

	// create callback proxy
	private final ListenerProxy callbackProxy = new ListenerProxy(Callback.class);

	// get the multi-caster from proxy
	private final Callback callbacks = (Callback) callbackProxy.getMulticaster();

	public ListenerProxyTest() {}

	public void addCallback(Callback callback) {
		this.callbackProxy.addListener(callback);
	}

	public void invokeCallback(String message) {
		this.callbacks.callback(message);
	}

	public static void main(String[] args) {
		ListenerProxyTest test = new ListenerProxyTest();

		// add call back
		test.addCallback(message -> System.out.println("callback 1: " + message));
		test.addCallback(message -> System.out.println("callback 2: " + message));

		// invoke call back
		test.invokeCallback("Hello World");
	}
}
