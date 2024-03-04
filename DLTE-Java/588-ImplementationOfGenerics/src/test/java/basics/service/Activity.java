package basics.service;

import java.util.Arrays;

public interface Activity {
    T[] myObjects;
    public abstract String insertNewRecord(T objects);
    default void viewAll()
    {
        System.out.println(Arrays.toString(myObjects));
    }
    public abstract T read(int index);
    public abstract String delete(int index);
    public abstract void update(int index,T object);
}
