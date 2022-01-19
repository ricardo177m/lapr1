import java.util.Calendar;
import java.util.Date;

public class Tests {
    public static void main() {
        runTestes();
    }

    private static boolean test_calcularNumSemanas(Date arg1, Date arg2, int expectedRes) {
        return Main.calcularNumSemanas(arg1, arg2) == expectedRes;
    }

    private static boolean test_compararMatrizes(double[][] matriz) {
        return Matrizes.compararMatrizes(matriz, matriz);
    }

    private static boolean test_multiplicarMatrizes(double[][] matriz1, double[][] matriz2, double[][] expectedRes) {
        double[][] result = Matrizes.multiplicarMatrizes(matriz1, matriz2);
        return Matrizes.compararMatrizes(result, expectedRes);
    }

    private static boolean test_elevarMatriz(double[][] matriz, long nth, double[][] expectedRes) {
        double[][] result = Matrizes.elevarMatriz(matriz, nth);
        return Matrizes.compararMatrizes(result, expectedRes);
    }

    // todo
    private static boolean test_matrizInversaL() {
        return false;
    }

    // private static boolean test_preencherDiagonalMatriz() {}
    // private static boolean test_decomposicaoCrout() {}

    public static void runTestes() {
        System.out.println("Testes:");

        // Teste 1 - calcular número de semanas
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2021, 3, 6, 0, 0, 0);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2021, 5, 27, 0, 0, 0);
        System.out.println("[1] calcularNumSemanas: "
                + (test_calcularNumSemanas(cal1.getTime(), cal2.getTime(), 11) ? "OK" : "NOT OK"));

        // Teste 2 - comparar matrizes
        double[][] matriz1 = { { 1, 3, 2 }, { 4, 5, 3 }, { 9, 3, 1 } };
        System.out.println("[2] compararMatrizes: " + (test_compararMatrizes(matriz1) ? "OK" : "NOT OK"));

        // Teste 3 - multiplicar matrizes
        double[][] matriz2 = { { 1, -3, 2 }, { 5, -1, 1 }, { 3, 0, 2 } };
        double[][] matriz1Mult2 = { { 22, -6, 9 }, { 38, -17, 19 }, { 27, -30, 23 } };
        System.out.println("[3] multiplicarMatrizes: " + (test_multiplicarMatrizes(matriz1, matriz2, matriz1Mult2) ? "OK" : "NOT OK"));

        // Teste 4 - elevar matriz
        double[][] matriz3 = { { 244, 252, 147 }, { 469, 461, 266 }, { 462, 399, 223 } };
        System.out.println("[4] elevarMatriz: " + (test_elevarMatriz(matriz1, 3, matriz3) ? "OK" : "NOT OK"));

        // todo Teste 5 - matriz inversa L
        System.out.println("[5] matrizInversaL: " + (test_matrizInversaL() ? "OK" : "NOT OK"));

        // Teste 6 - preencher diagonal matriz
        // double

        // Teste 7 - decomposição Crout
    }

}
