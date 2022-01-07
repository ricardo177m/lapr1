// Projeto LAPR1 - Aplicação para a empresa MSP
// 2021-2022 LEI, Turma 1DI
//
// Realizado por:
// André Barros - 1211299
// Tomás Russo - 1211288
// João Caseiro - 1211334
// Ricardo Moreira - 1211285

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static final Scanner kbScanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, ParseException {
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
                    break;

                case 2:
                    mostrarDadosDiarios(leituraDeDatas(), datas, acumuladoInfetados, acumuladoHospitalizados,
                            acumuladoUCI, acumuladoMortes);
                    pressioneEnterParaCont();
                    break;
                case 3:
                    mostrarDadosSemanais(leituraDeDatas(), datas);
                case 4:
                    System.out.println("Não implementado.");
                    pressioneEnterParaCont();
                    break;

                case 5:
                    System.out.println("1º intervalo:");
                    String[] intervalo1 = leituraDeDatas();
                    String[] intervalo2;

                    // calcular intervalo de dias do intervalo1
                    long diasIntervalo1 = calcularDiasEntreIntervalo(intervalo1);

                    boolean flag;
                    do {
                        flag = true;
                        System.out.println("\n2º intervalo:");
                        intervalo2 = leituraDeDatas();

                        // o intervalo de dias do intervalo2 tem de ser igual a 2
                        long diasIntervalo2 = calcularDiasEntreIntervalo(intervalo2);

                        if(diasIntervalo1 != diasIntervalo2) {
                            flag = false;
                            System.out.println("\nErro: O número de dias entre o intervalo 1 é diferente do intervalo 2!");
                            pressioneEnterParaCont();
                        }
                    } while (!flag);

                    // o intervalo1 e intervalo2 têm o mesmo número de dias
                    // calcular as diferenças, a média e o desvio padrão para cada período

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

    public static String[] leituraDeDatas() throws ParseException {
        String[] leituraDatas = new String[2];
        boolean verificado;

        do {
            System.out.print("Insira a data inicial: ");
            leituraDatas[0] = kbScanner.nextLine();
            System.out.print("Insira a data final: ");
            leituraDatas[1] = kbScanner.nextLine();
            verificado = verificarDatas(leituraDatas[0], leituraDatas[1]);

            if (!verificado)
                System.out.println("\nErro: datas inválidas!\n");
        } while (!verificado);

        return leituraDatas;
    }

    public static void mostrarDadosDiarios(String[] leituraDeDatas, ArrayList<String> datas,
                                           ArrayList<Integer> acumuladoInfetados, ArrayList<Integer> acumuladoHospitalizados,
                                           ArrayList<Integer> acumuladoUCI, ArrayList<Integer> acumuladoMortes) {

        int indexData1 = datas.indexOf(leituraDeDatas[0]);
        int indexData2 = datas.indexOf(leituraDeDatas[1]);

        System.out.printf("Data %15s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n", "");
        for (int i = 0; i <= indexData2 - indexData1; i++) {
            int novosInfetados = dadosDiariosNovos(acumuladoInfetados, leituraDeDatas, datas).get(i);
            int novosHospitalizacoes = dadosDiariosNovos(acumuladoHospitalizados, leituraDeDatas, datas).get(i);
            int novosUCI = dadosDiariosNovos(acumuladoUCI, leituraDeDatas, datas).get(i);
            int novosMortes = dadosDiariosNovos(acumuladoMortes, leituraDeDatas, datas).get(i);
            System.out.printf("%s %25s | %21.10s | %9.10s | %12.10s \n", datas.get(i + indexData1), novosInfetados,
                    novosHospitalizacoes, novosUCI, novosMortes);
        }
    }

    public static ArrayList<Integer> dadosDiariosNovos(ArrayList<Integer> dados, String[] leituraDeDatas,
                                                       ArrayList<String> datas) {
        ArrayList<Integer> dadosNovos = new ArrayList<>();
        int indexData1 = datas.indexOf(leituraDeDatas[0]);
        int indexData2 = datas.indexOf(leituraDeDatas[1]);
        for (int i = indexData1; i <= indexData2; i++) {
            dadosNovos.add(i - 1 < 0 ? 0 : dados.get(i) - dados.get(i - 1));
        }
        return dadosNovos;
    }

    public static void mostrarDadosSemanais(String[] leituraDeDatas, ArrayList<String> datas) throws ParseException {
        // mostrar dados das semanas entre as datas pretendidas, ou seja, por exemplo:
        // dados da 1 semn, dados da 2 semn, dados da 3 semn
        // 2020-04-01 2020-04-02 -> Não é uma semana completa
        // 2020-04-1 2020-04-17 -> mostra 2 semanas 1-15
        // 1 semana, subtrair acumulado do ult dia semana com 1 dia semn

        if (verificarSemana(leituraDeDatas, datas)) {
            System.out.printf("%s %25s | %21.10s | %9.10s | %12.10s \n");
        } else {
            do {
                System.out.println("Introduza datas que contenham pelo menos 1 semana");
                leituraDeDatas();
                verificarSemana(leituraDeDatas, datas);
            } while (!verificarSemana(leituraDeDatas, datas));
        }
    }

    public static boolean verificarSemana(String[] leituraDeDatas, ArrayList<String> datas) {
        boolean verificado = false;
        int indexData1 = datas.indexOf(leituraDeDatas[0]);
        int indexData2 = datas.indexOf(leituraDeDatas[1]);
        if (((indexData2 + 1) - (indexData1 - 1)) % 6 == 0) {
            verificado = true;
        }
        return verificado;
    }

    public static void mostrarDadosMensais() {
    }

    public static boolean verificarDatas(String data1, String data2) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        Date dataObj1 = df.parse(data1);
        Date dataObj2 = df.parse(data2);

        return dataObj1.before(dataObj2);
    }

    public static long calcularDiasEntreIntervalo(String[] intervalo) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataObj1 = sdf.parse(intervalo[0]);
        Date dataObj2 = sdf.parse(intervalo[1]);

        long msDif = Math.abs(dataObj2.getTime() - dataObj1.getTime());
        long numDias = TimeUnit.DAYS.convert(msDif, TimeUnit.MILLISECONDS);

        return numDias;
    }
}
