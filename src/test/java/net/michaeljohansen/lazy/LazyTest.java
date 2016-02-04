package net.michaeljohansen.lazy;

import org.junit.Test;

import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LazyTest {
    @Test
    public void onlyInitializedOnce() throws Exception {
        Supplier<Counter> supplier = Lazy.of(Counter::new);

        supplier.get();
        supplier.get();
        supplier.get();

        assertThat(Counter.counter, is(1));

    }

    private static class Counter {
        private static int counter = 0;

        public Counter() {
            counter++;
        }
    }
}
