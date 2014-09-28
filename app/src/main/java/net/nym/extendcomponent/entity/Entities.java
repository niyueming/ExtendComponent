package net.nym.extendcomponent.entity;

import java.util.ArrayList;

/**
 * 类Entities
 * <p>实体s
 *
 * @author nym
 * @version 2013-6-20
 * @time 下午6:16:15
 * @see Entity
 */
public class Entities<T extends Entity> {
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
}
