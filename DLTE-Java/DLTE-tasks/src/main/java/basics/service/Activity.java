package basics.service;
import java.util.Arrays;
public interface Activity {
    public abstract T read(int index);
    public abstract String delete(int index);
    public abstract void update(int index,T object);
}
