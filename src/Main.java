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
import java.util.Calendar;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {
    static final Scanner kbScanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        int selecaoUtilizador;
        String[] datas;
        int[] acumuladoNaoInfetados;
        int[] acumuladoInfetados;
        int[] acumuladoHospitalizados;
        int[] acumuladoUCI;
        int[] acumuladoMortes;


        System.out.println("Bem Vindo! Por favor siga os passos abaixo:\n");
        String caminhoFicheiro = selecionarFicheiro();
        int numeroLinhas = tamanhoLinhasFicheiro(caminhoFicheiro);
        datas = new String[numeroLinhas];
        acumuladoNaoInfetados = new int[numeroLinhas];
        acumuladoInfetados = new int[numeroLinhas];
        acumuladoHospitalizados = new int[numeroLinhas];
        acumuladoUCI = new int[numeroLinhas];
        acumuladoMortes = new int[numeroLinhas];
        lerFicheiro(caminhoFicheiro,datas, acumuladoNaoInfetados, acumuladoInfetados,
                acumuladoHospitalizados,
                acumuladoUCI, acumuladoMortes);


        // Início do programa
        do {
            selecaoUtilizador = menu();
            System.out.println();

            switch (selecaoUtilizador) {
                case 1:
                    lerFicheiro(caminhoFicheiro,datas, acumuladoNaoInfetados, acumuladoInfetados,
                            acumuladoHospitalizados,
                            acumuladoUCI, acumuladoMortes);
                    pressioneEnterParaCont();

                    break;

                case 2:
                    mostrarDadosDiarios(leituraDeDatas(),datas, acumuladoInfetados,  acumuladoHospitalizados, acumuladoUCI, acumuladoMortes);
                    pressioneEnterParaCont();
                    break;
                case 3:
                    mostrarDadosSemanais(leituraDeDatas(), datas, acumuladoInfetados, acumuladoHospitalizados,
                            acumuladoUCI, acumuladoMortes);
                    pressioneEnterParaCont();
                    break;
                case 4:
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

    public static int tamanhoLinhasFicheiro(String filePath) throws FileNotFoundException {
        Scanner file = new Scanner(new File(filePath));

        int linhas = 0;

        while (file.hasNextLine()) {
            linhas++;
            file.nextLine();
        }

        return linhas - 1;
    }


    public static int menu (){
        int selecaoUtilizador;
        // Apresentação do menu
        System.out.println("Por favor escolha uma opção:\n");
        System.out.println("1. Carregar ficheiro");
        System.out.println("2. Visualizar dados diários");
        System.out.println("3. Visualizar dados semanais");
        System.out.println("4. Visualizar dados mensais");
        System.out.println("5. Comparar intervalo de datas");
        System.out.println("0. Sair\n");

        System.out.print("> ");

        selecaoUtilizador = kbScanner.nextInt();
        kbScanner.nextLine();
        return selecaoUtilizador;
    }


    public static void pressioneEnterParaCont() {
        System.out.print("\nPressione ENTER para continuar... ");
        kbScanner.nextLine();
    }

    public static void lerFicheiro(String filePath, String[] datas, int[] acumuladoNaoInfetados,
                                   int[] acumuladoInfetados,int[] acumuladoHospitalizados,
                                   int[] acumuladoUCI,int[] acumuladoMortes) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filePath));
        String linha = scanner.nextLine();
        String[] dados;
        int indice = 0;
        while (scanner.hasNextLine()) {
            linha = scanner.nextLine();
            dados = linha.split(",");
            datas[indice] = dados[0];
            acumuladoNaoInfetados[indice] = Integer.parseInt(dados[1]);
            acumuladoInfetados[indice] = Integer.parseInt(dados[2]);
            acumuladoHospitalizados[indice] = Integer.parseInt(dados[3]);
            acumuladoUCI[indice] = Integer.parseInt(dados[4]);
            acumuladoMortes[indice] = Integer.parseInt(dados[5]);

            indice++;
        }
    }

    public static String selecionarFicheiro() {
        System.out.print("Insira o caminho absoluto do ficheiro: ");
        return kbScanner.nextLine();
    }

    public static String[] leituraDeDatas () throws ParseException {
        String[] leituraDatas = new String[2];
        boolean verificado;
        do {
            System.out.println("Insira a data inicial:");
            leituraDatas[0] = kbScanner.nextLine();
            System.out.println("Insira a data final:");
            leituraDatas[1] = kbScanner.nextLine();
            verificado = verificarDatas(leituraDatas[0],leituraDatas[1]);

            if (!verificado) {
                System.out.println("\n----Introduza datas corretas----\n");
            }

        } while (!verificado);

        return leituraDatas;
    }

    public static void mostrarDadosDiarios(String[] leituraDeDatas, String[] datas, int[] acumuladoInfetados, int[] acumuladoHospitalizados,
                                           int[] acumuladoUCI, int[] acumuladoMortes) {

        int indexData1 = Arrays.asList(datas).indexOf(leituraDeDatas[0]);
        int indexData2 = Arrays.asList(datas).indexOf(leituraDeDatas[1]);

        System.out.printf("Data %15s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n" , "" );
        for (int i = 0; i <= indexData2-indexData1; i++) {
            int[] novosInfetados = dadosDiariosNovos(acumuladoInfetados,leituraDeDatas,datas);
            int[] novosHospitalizacoes = dadosDiariosNovos(acumuladoHospitalizados, leituraDeDatas, datas);
            int[] novosUCI = dadosDiariosNovos(acumuladoUCI, leituraDeDatas, datas);
            int[] novosMortes = dadosDiariosNovos(acumuladoMortes, leituraDeDatas, datas);
            System.out.printf("%s %25s | %21.10s | %9.10s | %12.10s \n", datas[i+indexData1],novosInfetados[i],novosHospitalizacoes[i],novosUCI[i],novosMortes[i]);
        }
    }

    public static int[] dadosDiariosNovos (int[] dados,String[] leituraDeDatas, String[] datas) {
        int indexData1 = Arrays.asList(datas).indexOf(leituraDeDatas[0]);
        int indexData2 = Arrays.asList(datas).indexOf(leituraDeDatas[1]);

        int[] dadosNovos =  new int[indexData2-indexData1+1];

        for (int i = indexData1; i <= indexData2; i++) {
            if (i-1<0) {
                dadosNovos[i]=0;
            } else {
                dadosNovos[i] = dados[i]-dados[i-1];
            }
        }
        return dadosNovos;
    }

    public static void mostrarDadosSemanais(String[] leituraDeDatas, String[] datas, int[] acumuladoInfetados, int[] acumuladoHospitalizados,
                                            int[] acumuladoUCI, int[] acumuladoMortes) throws ParseException {
        // mostrar dados das semanas entre as datas pretendidas, ou seja, por exemplo: dados da 1 semn, dados da 2 semn, dados da 3 semn
        // 2020-04-01 2020-04-02 -> Não é uma semana completa
        // 2020-04-1 2020-04-17 -> mostra 2 semanas 1-15
        // 1 semana, subtrair acumulado do ult dia semana com 1 dia semn
        int indexData1 = Arrays.asList(datas).indexOf(verificarSemanaSegunda(leituraDeDatas));
        int indexData2 = Arrays.asList(datas).indexOf(verificarSemanaDomingo(leituraDeDatas));
        int id2_id1 = (indexData2-indexData1)/7;
        int numeroSemanas = Math.round(id2_id1)+1;

        if (numeroSemanas == 0) {
            System.out.println("Introduza datas que contenham pelo menos 1 semana");
        } else {
            System.out.printf("%19s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n" , "" );
            for (int i = 0; i < numeroSemanas; i++) {
                int[] novosInfetados = dadosSemanaisNovos(acumuladoInfetados,numeroSemanas,indexData2,indexData1);
                int[] novosHospitalizacoes = dadosSemanaisNovos(acumuladoHospitalizados,numeroSemanas,indexData2,indexData1);
                int[] novosUCI = dadosSemanaisNovos(acumuladoUCI, numeroSemanas,indexData2,indexData1);
                int[] novosMortes = dadosSemanaisNovos(acumuladoMortes,numeroSemanas,indexData2,indexData1);
                System.out.printf("Semana " + (1+i) + ": %25s | %21.10s | %9.10s | %12.10s \n", novosInfetados[i],novosHospitalizacoes[i],novosUCI[i],novosMortes[i]);
            }
        }
    }

    public static int[] dadosSemanaisNovos (int[] dados, int numeroSemanas,int indexData2,int indexData1) {

        int[] dadosNovos =  new int[numeroSemanas];

        if (indexData1<=indexData2) {
            for (int j = 0; j < numeroSemanas ; j++) {
                if (indexData1<=indexData2) {
                    dadosNovos[j] = dados[indexData1 + 6] - dados[indexData1];
                    indexData1 = indexData1 + 7;
                }
            }
        }
        return dadosNovos;
    }

    public static String verificarSemanaSegunda (String[] leituraDeDatas) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Calendar data = Calendar.getInstance();
        Date inicio = formatter.parse(leituraDeDatas[0]);
        data.setTime(inicio);

        while ( data.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            data.add(Calendar.DAY_OF_WEEK,1);
        }

        return leituraDeDatas[0] = formatter.format((data.getTime()));
    }

    public static String verificarSemanaDomingo (String[] leituraDeDatas) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Calendar dataFinal =  Calendar.getInstance();

        Date fim = formatter.parse(leituraDeDatas[1]);
        dataFinal.setTime(fim);

        while (dataFinal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            dataFinal.add(Calendar.DAY_OF_WEEK,-(dataFinal.get(Calendar.DAY_OF_WEEK)-1));
        }

        return leituraDeDatas[1] = formatter.format(dataFinal.getTime());
    }

   /* public static int numeroSemanas(String[] leituraDeDatas) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Calendar data = Calendar.getInstance();
        Calendar dataFinal = Calendar.getInstance();

        data.setTime();
        dataFinal.setTime();


        return dataFinal.get(Calendar.WEEK_OF_YEAR) - data.get(Calendar.WEEK_OF_YEAR);
    } */



    public static void mostrarDadosMensais () {

    }


    public static boolean verificarDatas(String data1, String data2) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);

        Date dataObj1 = df.parse(data1);
        Date dataObj2 = df.parse(data2);

        return dataObj1.before(dataObj2);
    }

    public static long calcularDiasEntreIntervalo(String[] intervalo) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataObj1 = sdf.parse(intervalo[0]);
        Date dataObj2 = sdf.parse(intervalo[1]);

        long msDif = Math.abs(dataObj2.getTime() - dataObj1.getTime());
        long numDias = TimeUnit.DAYS.convert(msDif, TimeUnit.MILLISECONDS);

        return numDias;
    }
}