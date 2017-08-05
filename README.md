# JavaTool
Some useful tool written in java

## Callback Support
```
Simple Proxy class to avoid writing for loop to trigger multiple callbacks/listeners 
```
**Before**
```
// onMessage1()
for(Callback cb: callbackList) {
  cb.onMessage1();
}

// onMessage2()
for(Callback cb: callbackList) {
  cb.onMessage2();
}
```
**Now**
```
Callback cb = (Callback) proxyClass.getMulticaster();

// onMessage1()
cb.onMessage1();

// onMessage2()
cb.onMessage2();
```
