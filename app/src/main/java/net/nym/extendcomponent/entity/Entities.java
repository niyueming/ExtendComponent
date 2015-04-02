package net.nym.extendcomponent.entity;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 类Entities
 * <p>实体s
 *
 * @author nym
 * @version 2013-6-20
 * @time 下午6:16:15
 * @see Entity
 */
public class Entities<T extends Entity> implements Iterable<T>{
    ArrayList<T> list;

    public Entities() {
        list = new ArrayList<T>();
    }


    public ArrayList<T> getList() {
        return list;
    }

    public void clear() {
        list.clear();
    }

    public void add(T object) {
        list.add(object);
    }

    public void addAll(Entities<T> object) {
        list.addAll(object.getList());
    }

    public T get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean removeEntity(T object) {
        return list.remove(object);
    }

    public void removeEntity(int index) {
        list.remove(index);
    }

    public T remove(int position) {
        return list.remove(position);
    }
    public boolean remove(T object) {
        return list.remove(object);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString()).append(",");
        }
        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }


    @Override
    public Iterator<T> iterator() {
        return new SimpleListIterator();
    }

    private class SimpleListIterator implements Iterator<T> {
        int pos = -1;

        int expectedModCount;

        int lastPosition = -1;

        SimpleListIterator() {
            expectedModCount = Entities.this.size();
        }

        public boolean hasNext() {
            return pos + 1 < size();
        }

        public T next() {
            if (expectedModCount == Entities.this.size()) {
                try {
                    T result = get(pos + 1);
                    lastPosition = ++pos;
                    return result;
                } catch (IndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }
            throw new ConcurrentModificationException();
        }

        public void remove() {
            if (this.lastPosition == -1) {
                throw new IllegalStateException();
            }

            if (expectedModCount != Entities.this.size()) {
                throw new ConcurrentModificationException();
            }

            try {
                Entities.this.remove(lastPosition);
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }

            expectedModCount = Entities.this.size();
            if (pos == lastPosition) {
                pos--;
            }
            lastPosition = -1;
        }
    }
}
