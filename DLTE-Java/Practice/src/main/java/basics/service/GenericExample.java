package basics.service;


    class Box<T>{
        private T item;
        public T getItem() {
            return item;
        }
        public void setItem(T item) {
            this.item = item;
        }
    }
    public class GenericExample{
        public static void main(String[] args) {
            Box<String> box=new Box<>();
            box.setItem("Entities");
            String string=box.getItem();
            System.out.println("box content is"+string);
        }
    }


