package picasso.t8;

import java.util.Iterator;

public abstract class WrappingIterable<T, U> implements Iterable<T> {
    private final Iterable<U> wrapped;

    public WrappingIterable(Iterable<U> wrapped) {
        this.wrapped = wrapped;
    }

    public Iterator<T> iterator() {
        return new WrappingIterator<T,U>(wrapped.iterator()) {
            @Override protected T map(U thing) {
                return WrappingIterable.this.map(thing);
            }
        };
    }

    protected abstract T map(U thing);
}
