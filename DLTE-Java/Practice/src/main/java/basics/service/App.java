package basics.service;

/**
 * Hello world!
 *
 */
public class App {
    public void performOperations() {
        int value = 5;
        add(value, 3);

    }

    private void add(int original, int other) {
        int result1=original+other;
        square(result1);
        System.out.println("sum= "+result1);
    }

    private void square(int number) {
        System.out.println("square= "+(number*number));
        return;
    }


    public static void main(String[] args) {
        // Example usage
        App myObject = new App();
        myObject.performOperations();
        System.out.println("Main");
    }
}