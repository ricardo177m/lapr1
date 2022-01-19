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

    // todo - função não está pronta
    private static boolean test_matrizInversaL(double[][] matriz, double[][] expectedRes) {
        // double[][] result = Matrizes.calcularInversaL(matriz);
        // return Matrizes.compararMatrizes(result, expectedRes);
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

    private static boolean test_calcularDiasEntreInvervalo(String[] intervalo, long expectedRes) {
        return Main.calcularDiasEntreIntervalo(intervalo) == expectedRes;
    }

    private static boolean test_validarDatas(String[] datas, boolean[] expectedRes) {
        boolean flag = true;
        for (int i = 0; i < datas.length; i++) {
            if ((Main.verificarData1(datas[i]) || Main.verificarData2(datas[i])) != expectedRes[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private static boolean test_subtrairIdentidadeComMatriz(double[][] matriz1, double[][] expectedRes) {
        double[][] res = Matrizes.subtrairIdentidadeComMatriz(matriz1);
        return Matrizes.compararMatrizes(res, expectedRes);
    }

    public static void runTestes() {
        int testCount = 0;
        int okCount = 0;

        System.out.println("\nTestes:");
        System.out.println("- Matrizes");

        // Teste 1 - comparar matrizes
        double[][] matriz1 = { { 1, 3, 2 }, { 4, 5, 3 }, { 9, 3, 1 } };
        boolean test1 = test_compararMatrizes(matriz1);
        printTestResult("compararMatrizes", ++testCount, test1);
        if (test1)
            okCount++;

        // Teste 2 - multiplicar matrizes
        double[][] matriz2 = { { 1, -3, 2 }, { 5, -1, 1 }, { 3, 0, 2 } };
        double[][] matriz1Mult2 = { { 22, -6, 9 }, { 38, -17, 19 }, { 27, -30, 23 } };
        boolean test2 = test_multiplicarMatrizes(matriz1, matriz2, matriz1Mult2);
        printTestResult("multiplicarMatrizes", ++testCount, test2);
        if (test2)
            okCount++;

        // Teste 3 - elevar matriz
        double[][] matrizTest4_expectedRes = { { 244, 252, 147 }, { 469, 461, 266 }, { 462, 399, 223 } };
        boolean test3 = test_elevarMatriz(matriz1, 3, matrizTest4_expectedRes);
        printTestResult("elevarMatriz", ++testCount, test3);
        if (test3)
            okCount++;

        // Teste 4 - matriz inversa L
        double[][] inversaMatriz1 = { { 4, -3, 1 }, { -23, 17, -5 }, { 33, -24, 7 } };
        boolean test4 = test_matrizInversaL(matriz1, inversaMatriz1);
        printTestResult("matrizInversaL", ++testCount, test4);
        if (test4)
            okCount++;

        // Teste 5 - preencher diagonal matriz
        double[][] matrizTest6_expectedRes = { { 3, 0, 0 }, { 0, 3, 0 }, { 0, 0, 3 } };
        boolean test5 = test_preencherDiagonalMatriz(3, matrizTest6_expectedRes);
        printTestResult("preencherDiagonalMatriz", ++testCount, test5);
        if (test5)
            okCount++;

        // todo Teste 6 - decomposição Crout
        boolean test6 = test_decomposicaoCrout();
        printTestResult("decomposicaoCrout", ++testCount, test6);
        if (test6)
            okCount++;

        // Teste 7 - subtrair matrizes
        double[][] test7_expectedRes = { { 0, 3, -2 }, { -5, 2, -1 }, { -3, 0, -1 } };
        boolean test7 = test_subtrairIdentidadeComMatriz(matriz2, test7_expectedRes);
        printTestResult("subtrairIdentidadeComMatriz", ++testCount, test7);
        if (test7)
            okCount++;

        System.out.println("- Main");

        // Teste 8 - calcular número de semanas
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2021, 3, 6, 0, 0, 0);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2021, 5, 27, 0, 0, 0);
        boolean test8 = test_calcularNumSemanas(cal1.getTime(), cal2.getTime(), 11);
        printTestResult("calcularNumSemanas", ++testCount, test8);
        if (test8)
            okCount++;

        // Teste 9 - calcular dias entre intervalo
        String[] intervalo = { "2020-01-02", "2021-01-03" };
        boolean test9 = test_calcularDiasEntreInvervalo(intervalo, 368);
        printTestResult("calcularDiasEntreIntervalo", ++testCount, test9);
        if (test9)
            okCount++;

        // Teste 10 - validar datas
        String[] datas = { "2020-01-02", "01-04-2020", "1-4-2020", "2020-04-051", "2020/01/04" };
        boolean[] test10_expectedRes = { true, true, false, false, false };
        boolean test10 = test_validarDatas(datas, test10_expectedRes);
        printTestResult("validararDatas", ++testCount, test10);
        if (test10)
            okCount++;

        System.out.printf("\n%s/%s testes efetuados com sucesso.\n", okCount, testCount);
    }

    private static void printTestResult(String testName, int testCount, boolean testRes) {
        System.out.printf("%5s %-30s %s\n", "[" + testCount + "]", testName, (testRes ? "OK" : "NOT OK"));
    }

    public static void printMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%5s", matriz[i][j]);
            }
            System.out.println();
        }
    }
}
