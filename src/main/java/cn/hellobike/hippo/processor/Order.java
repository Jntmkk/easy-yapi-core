package cn.hellobike.hippo.processor;

public interface Order {
    int LAST = Integer.MAX_VALUE;
    int FIRST = Integer.MIN_VALUE;

    int getOrder();
}
