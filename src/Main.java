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
import java.util.Scanner;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Main {
    static final Scanner kbScanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        String[] datas;
        int[] acumuladoNaoInfetados;
        int[] acumuladoInfetados;
        int[] acumuladoHospitalizados;
        int[] acumuladoUCI;
        int[] acumuladoMortes;
        int numeroLinhas;
        String caminhoFicheiro;

        int tipoFicheiro = menuInicial();

        do {
            if (tipoFicheiro == 1) {
                caminhoFicheiro = selecionarFicheiro();
                numeroLinhas = tamanhoLinhasFicheiro(caminhoFicheiro);
                datas = new String[numeroLinhas];
                acumuladoNaoInfetados = new int[numeroLinhas];
                acumuladoInfetados = new int[numeroLinhas];
                acumuladoHospitalizados = new int[numeroLinhas];
                acumuladoUCI = new int[numeroLinhas];
                acumuladoMortes = new int[numeroLinhas];
                funcionamentoProgramaOpcao1(caminhoFicheiro,datas, acumuladoNaoInfetados,acumuladoInfetados, acumuladoHospitalizados, acumuladoUCI, acumuladoMortes);

            } else if (tipoFicheiro == 2) {
                caminhoFicheiro = selecionarFicheiro();
                numeroLinhas = tamanhoLinhasFicheiro(caminhoFicheiro);
                datas = new String[numeroLinhas];
                int[] totaisNaoInfetado = new int[numeroLinhas];
                int[] totaisInfetado = new int[numeroLinhas];
                int[] totaisUCI = new int[numeroLinhas];
                int[] totaisHospitalizados = new int[numeroLinhas];
                int[] obitos = new int[numeroLinhas];
                funcionamentoProgramaOpcao2(caminhoFicheiro,datas,totaisNaoInfetado,totaisInfetado,totaisHospitalizados,totaisUCI,obitos);

            } else {
                System.out.println("Inválido");
                tipoFicheiro = menuInicial();
            }
        } while (tipoFicheiro != 1 && tipoFicheiro != 2);
    }

    //-------------------------------------------Funcionamento Aplicação----------------------------------------------//

    public static void funcionamentoProgramaOpcao1 (String file,String[] datas,int[] acumuladoNaoInfetados,int[] acumuladoInfetados,int[] acumuladoHospitalizados,int[] acumuladoUCI,int[] acumuladoMortes) throws FileNotFoundException, ParseException {
        int selecaoUtilizador;
        String caminhoFicheiro = file;
        lerFicheiro(caminhoFicheiro,datas,acumuladoNaoInfetados,acumuladoInfetados,acumuladoHospitalizados,acumuladoUCI,acumuladoMortes);
        do {
            selecaoUtilizador = menu();
            switch (selecaoUtilizador) {
                case 1:
                    caminhoFicheiro = selecionarFicheiro();
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
                    mostrarDadosMensais(leituraDeDatas(),datas,acumuladoInfetados,acumuladoHospitalizados,acumuladoUCI,acumuladoMortes);
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
                    System.out.println("Obrigado pela preferência!");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    pressioneEnterParaCont();
                    break;
            }
        } while (selecaoUtilizador!=0);
    }

     public static void funcionamentoProgramaOpcao2 (String file,String[] datas,int[] totaisNaoInfetados,int[] totaisInfetado, int[] totaisHospitalizados,int[] totaisUCI,int[] obitos) throws FileNotFoundException {
        int selecaoUtilizador;
        String caminhoFicheiro = file;
        lerFicheiro(caminhoFicheiro,datas,totaisNaoInfetados,totaisInfetado,totaisHospitalizados,totaisUCI,obitos);
        do {
            selecaoUtilizador = menu();
            switch (selecaoUtilizador) {
                case 1:
                    caminhoFicheiro = selecionarFicheiro();
                    lerFicheiro(caminhoFicheiro,datas,totaisNaoInfetados,totaisInfetado,
                            totaisHospitalizados,
                            totaisUCI, obitos);
                    pressioneEnterParaCont();
                    break;
                case 2:
                    mostrarDadosTotaisDiarios(leituraDeDatas(),datas, totaisInfetado, totaisHospitalizados, totaisUCI, obitos );
                    pressioneEnterParaCont();
                    break;

                case 3:
                    mostrarDadosTotaisSemanais(leituraDeDatas(), datas, totaisNaoInfetados, totaisInfetado,
                            totaisUCI, obitos);
                    pressioneEnterParaCont();
                    break;

                /*case 4:
                    mostrarDadosTotaisMensais(leituraDeDatas(), datas, totaisNaoInfetados, totaisInfetado,
                            totaisUCI, obito);
                    pressioneEnterParaCont();
                    break;

                case 5:
                    System.out.println("1º intervalo:");
                    String[] intervalo1 = leituraDeDatas();
                    String[] intervalo2;
                    // calcular intervalo de dias do intervalo1
                    // long diasIntervalo1 = calcularDiasEntreIntervalo(intervalo1);
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
                    break; */
                case 0:
                    // terminar a execução do programa
                    System.out.println("Obrigado pela preferência!");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    pressioneEnterParaCont();
                    break;
            }
        } while (selecaoUtilizador!=0);
    }

    public static int menuInicial() {
        System.out.println("\n\nBem Vindo! Por favor siga os passos abaixo:\n");
        System.out.println("Introduza o tipo de dados que pretende analisar:");
        System.out.println("1. Acumulados");
        System.out.println("2. Totais");
        System.out.print("> ");
        return kbScanner.nextInt();
    }

    public static void menuEscolherQtdDados () {
        System.out.println("Introduza uma opção:\n");
        System.out.println("1. Só um tipo de dados");
        System.out.println("2. Todo o tipo de dados");
        System.out.println("> ");
        int opcao = kbScanner.nextInt();

        if (opcao == 2) {
            menu();
        } else if (opcao == 1){
            System.out.println("Introduza o tipo de dados que quer visualizar:\n");
            System.out.println("1. Infetados");
            System.out.println("2. Hospitalizações");
            System.out.println("3. UCI");
            System.out.println("4. Mortes");
            kbScanner.nextInt();
        }
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
        System.out.println("\nPor favor escolha uma opção:\n");
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
        System.out.println("Ficheiro lido com sucesso!");
    }

    public static String selecionarFicheiro() {
        String caminho;
        do {
            kbScanner.nextLine();
            System.out.print("\nInsira o caminho absoluto do ficheiro: ");
            caminho = kbScanner.nextLine();
            if (!(new File(caminho)).isFile()) {
                System.out.println("ERRO: Ficheiro não encontrado. Por favor, insira o caminho absoluto de um ficheiro válido.\n");
            }
        } while (!(new File(caminho).isFile()));

        return caminho;
    }

    public static String[] leituraDeDatas () {
        String[] leituraDatas = new String[2];
        boolean dataEValida;
        boolean dataFinalDepoisQueInicial;
        do {
            do {
                System.out.print("Insira a data inicial (AAAA-MM-DD): ");
                leituraDatas[0] = kbScanner.nextLine();
                if (!verificarData(leituraDatas[0])) {
                    dataEValida = false;
                    System.out.println("ERRO: Formato de data inválido. Por favor, insira a data no formato (AAAA-MM-DD).\n");
                } else {
                    dataEValida = true;
                }
            } while (!dataEValida);

            do {
                System.out.print("Insira a data final (AAAA-MM-DD): ");
                leituraDatas[1] = kbScanner.nextLine();

                if (!verificarData(leituraDatas[1])) {
                    dataEValida = false;
                    System.out.println("ERRO: Formato de data inválido. Por favor, insira a data no formato (AAAA-MM-DD).\n");
                } else {
                    dataEValida = true;
                }
            } while (!dataEValida);

            if (stringParaDate(leituraDatas[0]).after(stringParaDate(leituraDatas[1]))) {
                dataFinalDepoisQueInicial = false;
                System.out.println("ERRO: Intervalo de datas impossível. Por favor, insira um intervalo válido.\n");
            } else {
                dataFinalDepoisQueInicial = true;
            }
        } while (!dataFinalDepoisQueInicial);
        return leituraDatas;
    }

    public static int indexData (Date leituraDeDatas,String[] datas) {

        int len = datas.length;
        int i = 0;

        while (i < len) {
            if (leituraDeDatas.equals(stringParaDate(datas[i]))) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

    public static Date stringParaDate (String leituraDeData) {
        String[] date = leituraDeData.split("-");

        int ano = Integer.parseInt(date[0]);
        int mes = Integer.parseInt(date[1]);
        int dia = Integer.parseInt(date[2]);

        if (date[0].length() == 4){
            ano = Integer.parseInt(date[0]);
            mes = Integer.parseInt(date[1]);
            dia = Integer.parseInt(date[2]);

        } else if (date[2].length() == 4) {
            ano = Integer.parseInt(date[2]);
            mes = Integer.parseInt(date[1]);
            dia = Integer.parseInt(date[0]);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(ano,mes-1,dia,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);

        return  calendar.getTime();
    }

    //--------------------------------------------Analise Diária Novos Casos------------------------------------------//

    public static void mostrarDadosDiarios(String[] leituraDeDatas, String[] datas, int[] acumuladoInfetados, int[] acumuladoHospitalizados, int[] acumuladoUCI, int[] acumuladoMortes) {

        int indexData1 = indexData(stringParaDate(leituraDeDatas[0]),datas);
        int indexData2 = indexData(stringParaDate(leituraDeDatas[1]),datas);

        System.out.printf("\nData %15s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n" , "" );
        for (int i = 0; i <= indexData2-indexData1; i++) {
            int[] novosInfetados = dadosDiariosNovos(acumuladoInfetados,leituraDeDatas,datas);
            int[] novosHospitalizacoes = dadosDiariosNovos(acumuladoHospitalizados, leituraDeDatas, datas);
            int[] novosUCI = dadosDiariosNovos(acumuladoUCI, leituraDeDatas, datas);
            int[] novosMortes = dadosDiariosNovos(acumuladoMortes, leituraDeDatas, datas);
            if (novosInfetados[i]==-1 && novosHospitalizacoes[i]==-1 && novosUCI[i]==-1 && novosMortes[i]==-1) {
                System.out.printf("%s %25s | %21.10s | %9.10s | %12.10s \n", datas[i],"Sem dados","Sem dados","Sem dados","Sem dados");
            } else {
                System.out.printf("%s %25s | %21.10s | %9.10s | %12.10s \n", datas[i + indexData1], novosInfetados[i], novosHospitalizacoes[i], novosUCI[i], novosMortes[i]);
            }
        }
    }

    public static int[] dadosDiariosNovos (int[] dados,String[] leituraDeDatas, String[] datas) {
        int indexData1 = indexData(stringParaDate(leituraDeDatas[0]),datas);
        int indexData2 = indexData(stringParaDate(leituraDeDatas[1]),datas);
        int indice = 0;
        int[] dadosNovos =  new int[(indexData2-indexData1)+1];

        for (int i = indexData1; i <= indexData2; i++) {
            if (i-1<0) {
                dadosNovos[indice]= -1;
            } else {
                dadosNovos[indice] = dados[i]-dados[i-1];

            }
            indice++;
        }
        return dadosNovos;
    }

    //--------------------------------------------Analise Diária Total Casos------------------------------------------//

    public static void mostrarDadosTotaisDiarios (String[] leituraDeDatas,String[] datas, int[] dadosInfetados, int[] dadosHospitalizados, int[] dadosUCI, int[] dadosMortes) {
        int indexData1 = indexData(verificarSemanaSegunda(stringParaDate(leituraDeDatas[0])),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDate(leituraDeDatas[1])),datas);

        System.out.printf("Data %15s Total Infetados | Total Hospitalizações | Total UCI | Total Mortes\n" , "" );
        for (int i = 0; i <= indexData2-indexData1; i++) {
            System.out.printf("%s %25s | %21.10s | %9.10s | %12.10s \n", datas[i+indexData1],dadosInfetados[i],dadosHospitalizados[i],dadosUCI[i],dadosMortes[i]);
        }
    }

    //--------------------------------------------Analise Semanal Novos Casos-----------------------------------------------------//

    public static void mostrarDadosSemanais(String[] leituraDeDatas, String[] datas, int[] acumuladoInfetados, int[] acumuladoHospitalizados,
                                            int[] acumuladoUCI, int[] acumuladoMortes)  {
        // mostrar dados das semanas entre as datas pretendidas, ou seja, por exemplo: dados da 1 semn, dados da 2 semn, dados da 3 semn
        // 2020-04-01 2020-04-02 -> Não é uma semana completa
        // 2020-04-1 2020-04-17 -> mostra 2 semanas 1-15
        // 1 semana, subtrair acumulado do ult dia semana com 1 dia semn
        int indexData1 = indexData(verificarSemanaSegunda(stringParaDate(leituraDeDatas[0])),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDate(leituraDeDatas[1])),datas);
        int numeroSemanas = calcularNumSemanas(stringParaDate(leituraDeDatas[0]),stringParaDate(leituraDeDatas[1]));

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

        for (int j = 0; j < numeroSemanas ; j++) {
            if (indexData1<=indexData2) {
                dadosNovos[j] = dados[indexData1 + 6] - dados[indexData1];
                indexData1 = indexData1 + 7;
            }
        }
        return dadosNovos;
    }

    public static Date verificarSemanaSegunda (Date inicio) {

        Calendar data = Calendar.getInstance() ;
        data.setTime(inicio);

        while (data.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            data.add(Calendar.DAY_OF_WEEK,1);
        }

        return (data.getTime());
    }

    public static Date verificarSemanaDomingo (Date fim)  {
        Calendar dataFinal =  Calendar.getInstance();

        dataFinal.setTime(fim);

        while (dataFinal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            dataFinal.add(Calendar.DAY_OF_WEEK,-(dataFinal.get(Calendar.DAY_OF_WEEK)-1));
        }

        return (dataFinal.getTime());
    }


    public static int calcularNumSemanas(Date inicio, Date fim) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(inicio);
        cal2.setTime(fim);

        limparHoras(cal1);
        limparHoras(cal2);

        inicioDaSemana(cal1);
        inicioDaSemana(cal2);

        int numSemanas = 0;

        while (cal1.compareTo(cal2) < 0) {
            cal1.add(Calendar.WEEK_OF_YEAR, 1);
            numSemanas++;
        }
        return numSemanas-1;
    }

    public static int inicioDaSemana(Calendar calendar) {
        int primeiroDiaSemana = calendar.getFirstDayOfWeek();
        calendar.set(Calendar.DAY_OF_WEEK, primeiroDiaSemana);
        return primeiroDiaSemana;
    }

    public static void limparHoras(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    //--------------------------------------------Analise Semanal Total Casos-----------------------------------------//

    public static void mostrarDadosTotaisSemanais (String[] leituraDeDatas,String[] datas, int[] dadosInfetados, int[] dadosHospitalizados, int[] dadosUCI, int[] dadosMortes){
        int indexData1 = indexData(verificarSemanaSegunda(stringParaDate(leituraDeDatas[0])),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDate(leituraDeDatas[1])),datas);
        int numeroSemanas = calcularNumSemanas(stringParaDate(leituraDeDatas[0]),stringParaDate(leituraDeDatas[1]));

        if (numeroSemanas == 0) {
            System.out.println("Introduza datas que contenham pelo menos 1 semana");
        } else {
            System.out.printf("%19s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n" , "" );
            for (int i = 0; i < numeroSemanas; i++) {
                int[] totaisInfetados = dadosTotaisSemanaisNovos(dadosInfetados,numeroSemanas,indexData2,indexData1);
                int[] totaisHospitalizacoes = dadosTotaisSemanaisNovos(dadosHospitalizados,numeroSemanas,indexData2,indexData1);
                int[] totaisUCI = dadosTotaisSemanaisNovos(dadosUCI, numeroSemanas,indexData2,indexData1);
                int[] totaisMortes = dadosTotaisSemanaisNovos(dadosMortes,numeroSemanas,indexData2,indexData1);
                System.out.printf("Semana " + (1+i) + ": %25s | %21.10s | %9.10s | %12.10s \n", totaisInfetados[i],totaisHospitalizacoes[i],totaisUCI[i],totaisMortes[i]);
            }
        }
    }

    public static int[] dadosTotaisSemanaisNovos (int[] dados, int numeroSemanas,int indexData2,int indexData1) {

        int[] dadosNovos =  new int[numeroSemanas];

        for (int j = 0; j < numeroSemanas ; j++) {
            if (indexData1<=indexData2) {
                dadosNovos[j] = dados[indexData1 + 6] - dados[indexData1];
                indexData1 = indexData1 + 7;
            }
        }
        return dadosNovos;
    }


    // ------------------------------------------Analise Mensal Novos Casos-------------------------------------------------------//

    public static void mostrarDadosMensais (String[] leituraDeDatas,String[] datas, int[] acumuladoInfetados, int[] acumuladoHospitalizados,
                                            int[] acumuladoUCI, int[] acumuladoMortes) throws ParseException {
        // 4 semanas -> 1 mes
        // Dados para mostrar = acumulado ult dia do mes - acumulado pri dia do mes
        int indexData1 = indexData(primeiroDiaMesValido(stringParaDate(leituraDeDatas[0])),datas);
        int indexData2 = indexData(ultimoDiaMesValido(stringParaDate(leituraDeDatas[1])),datas);
        int numeroMeses = numeroMeses(leituraDeDatas);

        if (numeroMeses == 0) {
            System.out.println("Introduza datas que contenham pelo menos 1 mês");
        } else {
            System.out.printf("%16s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n" , "" );
            for (int i = 0; i < numeroMeses; i++) {
                int[] novosInfetados = dadosMensaisNovos(acumuladoInfetados,numeroMeses,indexData2,indexData1,datas);
                int[] novosHospitalizacoes = dadosMensaisNovos(acumuladoHospitalizados,numeroMeses,indexData2,indexData1,datas);
                int[] novosUCI = dadosMensaisNovos(acumuladoUCI, numeroMeses,indexData2,indexData1,datas);
                int[] novosMortes = dadosMensaisNovos(acumuladoMortes,numeroMeses,indexData2,indexData1,datas);
                System.out.printf("Mês " + (1+i) + ": %25s | %21.10s | %9.10s | %12.10s \n", novosInfetados[i],novosHospitalizacoes[i],novosUCI[i],novosMortes[i]);
            }
        }
    }

    public static int[] dadosMensaisNovos (int[] dados,int numeroMeses,int index2,int index1,String[] datas) {
        int[] dadosNovos =  new int[numeroMeses];

        Calendar calendar = Calendar.getInstance();
        int diasNoMes = calendar.getActualMaximum(Calendar.MONTH);

        if (numeroMeses==1){
            dadosNovos[0]=dados[index2]-dados[index1];
        } else {
            for (int i = 0; i < numeroMeses; i++) {
                calendar.add(Calendar.MONTH,1);
                if(index1<=index2) {
                    dadosNovos[i] = dados[index1 + (diasNoMes)-1] - dados[index1];
                    index1 = index1 + diasNoMes;
                }
            }
        }
        return dadosNovos;
    }

    public static int numeroMeses(String[] leituraDeDatas)  {

        Calendar mesesInicial = Calendar.getInstance();
        Calendar mesFinal = Calendar.getInstance();
        Date inicio = primeiroDiaMesValido(stringParaDate(leituraDeDatas[0]));
        Date fim = ultimoDiaMesValido(stringParaDate(leituraDeDatas[1]));
        mesesInicial.setTime(inicio);
        mesFinal.setTime(fim);

        int diferencaDeAnos = mesFinal.get(Calendar.YEAR)-mesesInicial.get(Calendar.YEAR);
        int diferencaDeMeses = mesFinal.get(Calendar.MONTH)-mesesInicial.get(Calendar.MONTH);

        int numeroMeses = diferencaDeAnos*12 + diferencaDeMeses;
        return numeroMeses+1;
    }


    public static Date primeiroDiaMesValido (Date inicio)  {
        Calendar data = Calendar.getInstance();

        data.setTime(inicio);

        while (data.get(Calendar.DAY_OF_MONTH) != 1) {
            data.add(Calendar.DAY_OF_MONTH,1);
        }

        return  ((data.getTime()));
    }

    public static Date ultimoDiaMesValido (Date fim) {

        Calendar dataFinal =  Calendar.getInstance();

        dataFinal.setTime(fim);

        while (dataFinal.get(Calendar.DAY_OF_MONTH) != 31 && dataFinal.get(Calendar.DAY_OF_MONTH) != 30 ) {
            dataFinal.add(Calendar.DAY_OF_MONTH,-1);
        }

        return dataFinal.getTime();
    }


    //-----------------------------------------------------------------------------------------------------------------//

    public static boolean verificarData(String data) {
        return data.matches("\\d{4}-\\d{2}-\\d{2}");
    }

     public static long calcularDiasEntreIntervalo(String[] intervalo)  {

         Date inicio = primeiroDiaMesValido(stringParaDate(intervalo[0]));
         Date fim = ultimoDiaMesValido(stringParaDate(intervalo[1]));

         int daysdiff = 0;
         long diff = fim.getTime() - inicio.getTime();
         long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
         daysdiff = (int) diffDays;

         System.out.println(daysdiff);
         return daysdiff;
    }
}
