// Projeto LAPR1 - Aplicação para a empresa MSP
// 2021-2022 LEI, Turma 1DI
//
// Realizado por:
// André Barros(1211299)
// Tomás Russo(1211288)
// João Caseiro(1211334)
// Ricardo Moreira(1211285)

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static final Scanner kbScanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> datas = new ArrayList<String>();
        ArrayList<Integer> acumuladoNaoInfetados = new ArrayList<Integer>();
        ArrayList<Integer> acumuladoInfetados = new ArrayList<Integer>();
        ArrayList<Integer> acumuladoHospitalizados = new ArrayList<Integer>();
        ArrayList<Integer> acumuladoUCI = new ArrayList<Integer>();
        ArrayList<Integer> acumuladoMortes = new ArrayList<Integer>();
        int selecaoUtilizador;

        // Início do programa
        do {
            selecaoUtilizador = menu();

            System.out.println();

            switch (selecaoUtilizador) {
                case 1:
                    lerFicheiro(selecionarFicheiro(), datas, acumuladoNaoInfetados, acumuladoInfetados,
                            acumuladoHospitalizados,
                            acumuladoUCI, acumuladoMortes);

                    for (int i = 0; i < datas.size(); i++) {
                        System.out.println(acumuladoMortes.get(i));
                    }

                    pressioneEnterParaCont();
                    break;

                case 2:
                case 3:
                case 4:
                case 5:
                    System.out.println("Não implementado.");
                    pressioneEnterParaCont();
                    break;

                case 0:
                    // terminar a execução do programa
                    break;

                default:
                    System.out.println("Opção inválida.");
                    pressioneEnterParaCont();
                    break;
            }
        } while (selecaoUtilizador != 0);
    }

    public static int menu() {
        int selecaoUtilizador;

        // Apresentação do menu
        System.out.println("Bem-vindo! Por favor escolha uma opção:\n");
        System.out.println("  1. Carregar ficheiro");
        System.out.println("  2. Visualizar dados diários");
        System.out.println("  3. Visualizar dados semanais");
        System.out.println("  4. Visualizar dados mensais");
        System.out.println("  5. Comparar intervalo de datas");
        System.out.println("  0. Sair\n");

        System.out.print("> ");

        selecaoUtilizador = kbScanner.nextInt();
        kbScanner.nextLine();
        return selecaoUtilizador;
    }

    public static void pressioneEnterParaCont() {
        System.out.print("\nPressione ENTER para continuar... ");
        kbScanner.nextLine();
    }

    public static void lerFicheiro(String filePath, ArrayList<String> datas, ArrayList<Integer> acumuladoNaoInfetados,
            ArrayList<Integer> acumuladoInfetados, ArrayList<Integer> acumuladoHospitalizados,
            ArrayList<Integer> acumuladoUCI, ArrayList<Integer> acumuladoMortes) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(filePath));
        String linha = fileScanner.nextLine();
        String[] dados;

        while (fileScanner.hasNextLine()) {
            linha = fileScanner.nextLine();
            dados = linha.split(",");
            datas.add(dados[0]);
            acumuladoNaoInfetados.add(Integer.parseInt(dados[1]));
            acumuladoInfetados.add(Integer.parseInt(dados[2]));
            acumuladoHospitalizados.add(Integer.parseInt(dados[3]));
            acumuladoUCI.add(Integer.parseInt(dados[4]));
            acumuladoMortes.add(Integer.parseInt(dados[5]));
        }
    }

    public static String selecionarFicheiro() {
        System.out.print("Insira o caminho absoluto do ficheiro: ");
        return kbScanner.nextLine();
    }

    public static boolean verificarDatas(String data1, String data2) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        Date dataObj1 = df.parse(data1);
        Date dataObj2 = df.parse(data2);

        return dataObj1.before(dataObj2);
    }
}
