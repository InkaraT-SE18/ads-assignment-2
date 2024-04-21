import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {
    private Object[] array;
    private int size;

    public MyArrayList() {
        this.array = new Object[10]; // Initial capacity
        this.size = 0;
    }

    @Override
    public void add(T item) {
        if (size == array.length) {
            expandCapacity();
        }
        array[size++] = item;
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        array[index] = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (size == array.length) {
            expandCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(size, item);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) array[index];
    }

    @Override
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return (T) array[0];
    }

    @Override
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return (T) array[size - 1];
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    @Override
    public void removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        remove(0);
    }

    @Override
    public void removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        remove(size - 1);
    }

    @Override
    public void sort() {
        Arrays.sort(array, 0, size);
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int LastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(array[i], object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void expandCapacity() {
        int newCapacity = array.length * 2;
        array = Arrays.copyOf(array, newCapacity);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[currentIndex++];
            }
        };
    }
}
