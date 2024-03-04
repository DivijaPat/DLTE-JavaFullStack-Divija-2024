package basics.service;

import java.util.ArrayList;
import java.util.List;

public class MyBankDatabase <T> implements Activity<T>{
    private List<T> data;
   public MyBankDatabase(){
       this.data= new ArrayList<>();
   }
   public T create(T object){
       if(object!=null)
           data.add(object);
       return object;
   }
   public T read(int id)throws NullPointerException{
       if(data!=null && id>=0 && id<data.size()){
           return data.get(id);
       }
       return null;
   }
   public void update(int id,T updateObject){
       if(data!=null && id>0 && id<data.size())
           data.set(id,updateObject);
   }
   public void delete(int id)throws NullPointerException{
       if(data!=null && id>=0 && id<data.size()){
           data.remove(id);
       }
   }
}
