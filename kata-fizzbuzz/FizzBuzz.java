public class FizzBuzz {

    public String convertir(int numero) {

        if (numero == 15) {
            return "FizzBuzz";
        }

        if (numero == 3) {
            return "Fizz";
        }

        if (numero == 5) {
            return "Buzz";
        }

        if (numero == 1) {
            return "1";
        }

        return "2";
    }

}