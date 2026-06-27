public class FizzBuzzTest {

    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz();

        if (!fizzBuzz.convertir(1).equals("1")) {
            System.out.println("x Prueba fallo");
        } else {
            System.out.println("◄ Prueba correcta");
        }

    }

}