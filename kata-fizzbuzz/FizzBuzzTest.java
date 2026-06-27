public class FizzBuzzTest {

    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz();

        if (!fizzBuzz.convertir(1).equals("1")) {
            System.out.println("Prueba 1 fallo");
        } else {
            System.out.println("Prueba 1 correcta");
        }

        if (!fizzBuzz.convertir(2).equals("2")) {
            System.out.println("Prueba 2 fallo");
        } else {
            System.out.println("Prueba 2 correcta");
        }

        if (!fizzBuzz.convertir(3).equals("Fizz")) {
            System.out.println("Prueba 3 fallo");
        } else {
            System.out.println("Prueba 3 correcta");
        }

    }

}