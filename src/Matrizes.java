public class Matrizes {
    public static double[][] elevarMatriz(double[][] matriz, long nth) {
        double[][] result = matriz;
        for (int n = 1; n < nth; ++ n)
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
                    sum += matriz[i][l] * matriz1[l][j] ;
                }
                temp[i][j] = sum ;
            }
        }
        matriz = temp;

        return matriz;
    }

    // todo
    public static int[][] calcularInversa() {
        return null;
    }
}