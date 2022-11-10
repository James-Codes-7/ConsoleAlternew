package view;

public class Display {
    private static Display display;
    public static Display getInstance() {
        if (display == null) display = new Display();
        return display;
    }
    public void alert(String message) {
        System.out.println(message);
    }
}
