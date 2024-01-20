package odev;

public class Program {

    public static void main(String[] args) {
        Lexical lexical = new Lexical();
        // console'dan çalıştırınca "Deneme.java" yerine args[0] olacak -> lexical.calculate(args[0]);
        lexical.calculate("D:\\eclipse projects\\odev\\src\\odev\\Deneme.java");
        System.out.println(lexical);
    }
}
