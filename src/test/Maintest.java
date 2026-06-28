package src.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class Maintest {

    @Test
    public void testSalirDelSistema() {

        String entrada = "0\n";
        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida));

        Main.main(new String[]{});

        String resultado = salida.toString();

        assertTrue(resultado.contains("Saliendo"));
        assertTrue(resultado.contains("Sistema cerrado correctamente"));
    }

    @Test
    public void testOpcionIncorrecta() {

        String entrada = "99\n0\n";
        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida));

        Main.main(new String[]{});

        String resultado = salida.toString();

        assertTrue(resultado.contains("Opción no válida"));
    }

    @Test
    public void testEntradaTexto() {

        String entrada = "abc\n0\n";
        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida));

        Main.main(new String[]{});

        String resultado = salida.toString();

        assertTrue(resultado.contains("Opción no válida"));
    }

}