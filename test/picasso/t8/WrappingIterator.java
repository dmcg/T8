package picasso.t8;

import java.util.Iterator;

public abstract class WrappingIterator<T, U> implements Iterator<T> {
    private final Iterator<U> wrapped;

    public WrappingIterator(Iterator<U> wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public boolean hasNext() {
        return wrapped.hasNext();
    }

    @Override
    public T next() {
        return map(wrapped.next());
    }

    protected abstract T map(U thing);

    @Override
    public void remove() { throw new UnsupportedOperationException(); }
}
