package basics.service;

import java.io.IOException;

public interface Activity<T> {

     void create(T Object)throws IOException;

}
