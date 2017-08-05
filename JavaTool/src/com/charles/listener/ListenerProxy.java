package com.charles.listener;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Proxy class to support multiple listeners callback with no return value
 * @author Charles
 *
 */
public class ListenerProxy {
	private final List<Object> listeners = new CopyOnWriteArrayList<Object>();
    private final Object multicaster;

    public ListenerProxy(Class<?> listenerClass) {
        multicaster = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class<?>[] { listenerClass }, new ListenerInvocationHandler());
    }

    public Object getMulticaster() {
        return multicaster;
    }

    private class ListenerInvocationHandler implements InvocationHandler {

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        	// toString method
            if (method.getName().equals("toString") && args.length == 0) {
                return getClass().getSimpleName() + "@" + System.identityHashCode(proxy);
            } 
            // static method
            else if (method.getDeclaringClass() == Object.class) {
                return method.invoke(proxy, args);
            }
            for (Object listener : listeners) {
                method.invoke(listener, args);
            }
            return null;
        }
    }

    public void addListener(Object listener) {
    	if(!listeners.contains(listener))
    		listeners.add(listener);
    }

    public void removeListener(Object listener) {
    	if(listeners.contains(listener))
    		listeners.remove(listener);
    }
    
    public void clear() {
    	listeners.clear();
    }
}