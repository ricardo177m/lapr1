import java.util.Calendar;
import java.util.Date;

public class Tests {
    private static int NUM_TESTS = 7;

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

    private static boolean test_preencherDiagonalMatriz(double num, double[][] expectedRes) {
        double[][] res = Matrizes.preencherDiagonalMatriz(num);
        return Matrizes.compararMatrizes(res, expectedRes);
    }

    // todo
    private static boolean test_decomposicaoCrout() {
        return false;
    }

    public static void runTestes() {
        int count = 0;

        System.out.println("\nTestes:");

        // Teste 1 - calcular número de semanas
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2021, 3, 6, 0, 0, 0);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2021, 5, 27, 0, 0, 0);
        boolean test1 = test_calcularNumSemanas(cal1.getTime(), cal2.getTime(), 11);
        System.out.println("[1] calcularNumSemanas: "
                + (test1 ? "OK" : "NOT OK"));
        if (test1) count++;

        // Teste 2 - comparar matrizes
        double[][] matriz1 = { { 1, 3, 2 }, { 4, 5, 3 }, { 9, 3, 1 } };
        boolean test2 = test_compararMatrizes(matriz1);
        System.out.println("[2] compararMatrizes: " + (test2 ? "OK" : "NOT OK"));
        if (test2) count++;

        // Teste 3 - multiplicar matrizes
        double[][] matriz2 = { { 1, -3, 2 }, { 5, -1, 1 }, { 3, 0, 2 } };
        double[][] matriz1Mult2 = { { 22, -6, 9 }, { 38, -17, 19 }, { 27, -30, 23 } };
        boolean test3 = test_multiplicarMatrizes(matriz1, matriz2, matriz1Mult2);
        System.out.println("[3] multiplicarMatrizes: "
                + (test3 ? "OK" : "NOT OK"));
        if (test3) count++;

        // Teste 4 - elevar matriz
        double[][] matrizTest4_expectedRes = { { 244, 252, 147 }, { 469, 461, 266 }, { 462, 399, 223 } };
        boolean test4 = test_elevarMatriz(matriz1, 3, matrizTest4_expectedRes);
        System.out.println(
                "[4] elevarMatriz: " + (test4 ? "OK" : "NOT OK"));
        if (test4) count++;

        // todo Teste 5 - matriz inversa L
        boolean test5 = test_matrizInversaL();
        System.out.println("[5] matrizInversaL: " + (test5 ? "OK" : "NOT OK"));
        if (test5) count++;

        // Teste 6 - preencher diagonal matriz
        double[][] matrizTest6_expectedRes = { { 3, 0, 0 }, { 0, 3, 0 }, { 0, 0, 3 } };
        boolean test6 = test_preencherDiagonalMatriz(3, matrizTest6_expectedRes);
        System.out.println("[6] preencherDiagonalMatriz: "
                + (test6 ? "OK" : "NOT OK"));
        if (test6) count++;


        // todo Teste 7 - decomposição Crout
        boolean test7 = test_decomposicaoCrout();
        System.out.println("[7] decomposicaoCrout: " + (test7 ? "OK" : "NOT OK"));
        if (test7) count++;

        System.out.printf("\n%s/%s testes efetuados com sucesso.\n", count, NUM_TESTS);
    }

    private static void printMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%4s", matriz[i][j]);
            }
            System.out.println();
        }
    }

}
