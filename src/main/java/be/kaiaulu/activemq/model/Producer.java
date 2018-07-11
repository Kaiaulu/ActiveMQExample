package be.kaiaulu.activemq.model;

@FunctionalInterface
public interface Producer<T> {

    T apply();
}
