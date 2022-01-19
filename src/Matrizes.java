public class Matrizes {
    public static boolean compararMatrizes(double[][] matriz1, double[][] matriz2) {
        boolean flag = true;

        if (matriz1.length != matriz2.length || matriz1[0].length != matriz2[0].length) {
            flag = false;
        } else {
            for (int i = 0; i < matriz1.length; i++) {
                for (int j = 0; j < matriz1[0].length; j++) {
                    if (matriz1[i][j] != matriz2[i][j]) {
                        flag = false;
                        break;
                    }
                }
            }
        }

        return flag;
    }

    public static double[][] elevarMatriz(double[][] matriz, long nth) {
        double[][] result = matriz;
        for (int n = 1; n < nth; ++n)
            result = multiplicarMatrizes(result, matriz);
        return result;
    }

    public static double[][] multiplicarMatrizes(double[][] matriz, double[][] matriz1) {
        double[][] temp = new double[matriz.length][matriz[0].length];
        double sum;

        for (int i = 0; i < matriz.length; i++) {
            temp[i] = new double[matriz[i].length];
            for (int j = 0; j < matriz1[i].length; j++) {
                sum = 0;
                for (int l = 0; l < matriz.length; l++) {
                    sum += matriz[i][l] * matriz1[l][j];
                }
                temp[i][j] = sum;
            }
        }
        matriz = temp;

        return matriz;
    }

    // todo
    public static double[][] matrizInversaL(double[][] matriz) {
        double[][] inversaL = new double[3][3];

        for (int i = 0; i < inversaL.length; i++) {
            for (int j = 0; j < inversaL[i].length; j++) {
                inversaL[i][j] = 0.0;
            }
        }
        for (int i = inversaL.length - 1; i >= 0; i--) {
            inversaL[i][i] = matriz[i][i];
            for (int j = i; j >= 0; j--) {
                inversaL[i][j] = matriz[i][j] / inversaL[i][i];
            }
        }
        return inversaL;
    }

    public static double[][] preencherDiagonalMatriz(double dig) {
        double[][] matrizDiagonal = new double[3][3];

        for (int i = 0; i < matrizDiagonal.length; ++i) {
            for (int j = 0; j < matrizDiagonal[i].length; ++j) {
                if (i == j) {
                    matrizDiagonal[i][j] = dig;
                } else {
                    matrizDiagonal[i][j] = 0;
                }
            }
        }
        return matrizDiagonal;
    }

    public static void decomposicaoCrout(double[][] matrizL, double[][] matrizU, double[][] matriz) {
        for (int k = 0; k < matriz.length; ++k) {
            double suma = 0;
            for (int p = 0; p < k; ++p) {
                suma += matrizL[k][p] * matrizU[p][k];
            }
            matrizL[k][k] = matriz[k][k] - suma;
            for (int i = k + 1; i < matriz.length; ++i) {
                double suma2 = 0;
                for (int p = 0; p < k; ++p) {
                    suma2 += matrizL[i][p] * matrizU[p][k];
                }
                matrizL[i][k] = (matriz[i][k] - suma2) / matrizU[k][k];
            }
            for (int j = k + 1; j < matriz.length; ++j) {
                double suma3 = 0;
                for (int p = 0; p < k; ++p) {
                    suma3 += matrizL[k][p] * matrizU[p][j];
                }
                matrizU[k][j] = (matriz[k][j] - suma3) / matrizL[k][k];
            }
        }
    }
}