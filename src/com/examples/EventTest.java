package com.examples;

public class EventTest
{
    private java.util.Vector data = new java.util.Vector();

    public synchronized void addMyTestListener   (MyTestListener lis) {
        data.addElement   (lis);
    }
    public synchronized void removeMyTestListener(MyTestListener lis) {
        data.removeElement(lis);
    }

    public interface MyTestListener extends java.util.EventListener {
        void testEvent(MyTestEvent event);
    }
    public class MyTestEvent extends java.util.EventObject {
        private static final long serialVersionUID = 1L;
        public float oldValue,newValue;        
        MyTestEvent(Object obj, float oldValue, float newValue) {
            super(obj);
            this.oldValue = oldValue;
            this.newValue = newValue;
        }
    }
    public void notifyMyTest() {
        for (int i=0; i<data.size(); i++) {
            MyTestEvent event = new MyTestEvent(this, 0, 1);
            ((MyTestListener)data.elementAt(i)).testEvent(event);
        }
    }
}