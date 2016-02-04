package net.michaeljohansen.lazy;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T>{
    private final Object[] lock = new Object[0];
    private Supplier<T> innerSupplier;
    private boolean initialized;

    public Lazy(Supplier<T> supplier) {
        this.innerSupplier = () -> {
            synchronized (lock){
                if(!initialized){
                    T t = supplier.get();
                    this.initialized = true;
                    this.innerSupplier = () -> t;
                }
            }
            return this.innerSupplier.get();
        };
    }

    public static <T> Supplier<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }

    @Override
    public T get() {
        return innerSupplier.get();
    }
}
