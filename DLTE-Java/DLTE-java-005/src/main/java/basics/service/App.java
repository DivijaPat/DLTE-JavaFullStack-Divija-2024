package basics.service;

/**
 * Hello world!
 *
 */
public class App 
{
    public static  void main( String[] args )

    {

        int arr[]={1,2,3};
        final String greetings="Hello,";
        String modifiedGreetings=greetings+"world!";
        System.out.println(modifiedGreetings);
        for(final int i:arr){
            System.out.println(i);
        }
    }


}
















//class NewClass
//{
//    public static void main(String args[]){
//     final NewClass newClass=new NewClass();
//     String newString=newClass.Display()+" morning";
//    }
//    public String Display()
//    {
//        return "Good ";
//    }
//}
