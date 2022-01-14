// Projeto LAPR1 - Aplicação para a empresa MSP
// 2021-2022 LEI, Turma 1DI
//
// Realizado por:
// André Barros - 1211299
// Tomás Russo - 1211288
// João Caseiro - 1211334
// Ricardo Moreira - 1211285

import java.io.*;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;

public class Main {
    static final Scanner kbScanner = new Scanner(System.in);
    static final int NUMERO_DADOS_COMPARACAO = 2;
    static final int NUMERO_FICHEIRO_DIFERENTE =2;
    static final int NUMERO_DADOS_DIFERENTES = 5;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // Matriz de dados:
        // abcDados[tipoDeDado][indiceDado]
        //
        // tipoDeDado:
        // 0 - não infetados
        // 1 - infetados
        // 2 - hospitalizado
        // 3 - UCI
        // 4 - mortes

        String[] acumuladoDatas;
        int[][] acumuladoDados;

        String[] totalDatas;
        int[][] totalDados;

        boolean[] jaleuFicheiros = new boolean[NUMERO_FICHEIRO_DIFERENTE];

        System.out.println("\nBem-vindo!");
        System.out.println("Para continuar, necessita de carregar pelo menos um ficheiro.\n");
        String opcaoTipoFicheiro = selecionarTipoFicheiro();
        String caminhoFicheiro = selecionarFicheiro();
        String selecaoMenu;
        switch (opcaoTipoFicheiro) {
            case "1":
                int numLinhas = tamanhoLinhasFicheiro(caminhoFicheiro);
                acumuladoDatas = new String[numLinhas];
                acumuladoDatas = lerDatas(caminhoFicheiro,numLinhas,acumuladoDatas);
                acumuladoDados = new int[NUMERO_DADOS_DIFERENTES][numLinhas];
                acumuladoDados = lerDados(caminhoFicheiro,numLinhas,acumuladoDados);
                jaleuFicheiros[0] = true;
                pressioneEnterParaCont();
                selecaoMenu = menu();
                do {
                    executaOpcao(selecaoMenu, jaleuFicheiros, acumuladoDatas, acumuladoDados);
                    selecaoMenu = menu();
                } while (!selecaoMenu.equals("0"));
                break;
            case "2":
                numLinhas = tamanhoLinhasFicheiro(caminhoFicheiro);
                totalDatas = new String[numLinhas];
                totalDatas = lerDatas(caminhoFicheiro,numLinhas,totalDatas);
                totalDados = new int[NUMERO_DADOS_DIFERENTES][numLinhas];
                totalDados = lerDados(caminhoFicheiro,numLinhas,totalDados);
                jaleuFicheiros[1] = true;
                selecaoMenu = menu();
                do {
                    executaOpcao(selecaoMenu, jaleuFicheiros, totalDatas, totalDados);
                    selecaoMenu = menu();
                } while (!selecaoMenu.equals("0"));
                break;
        }
}

//-------------------------------------------Funcionamento Aplicação----------------------------------------------//

    public static String[] lerDatas (String caminhoFicheiro,int numeroLinhas,String[] dadosFicheiro) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoFicheiro));
        String[] datas = new String[numeroLinhas];
        String linha = scanner.nextLine();
        int indice = 0;
        while (scanner.hasNextLine()){
            linha = scanner.nextLine();
            dadosFicheiro = linha.split(",");
            datas[indice] = dadosFicheiro[0];
            indice++;
        }
        return datas;
    }

    public static int[][] lerDados (String caminhoFicheiro,int numeroLinhas, int[][] dados) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoFicheiro));
        int[][] dadosNovos = new int[NUMERO_DADOS_DIFERENTES][numeroLinhas];
        String[] dadosFicheiro;
        String linha = scanner.nextLine();
        int indice = 0;
        while (scanner.hasNextLine()) {
            linha = scanner.nextLine();
            dadosFicheiro = linha.split(",");

            dadosNovos[0][indice] = Integer.parseInt(dadosFicheiro[1]);
            dadosNovos[1][indice] = Integer.parseInt(dadosFicheiro[2]);
            dadosNovos[2][indice] = Integer.parseInt(dadosFicheiro[3]);
            dadosNovos[3][indice] = Integer.parseInt(dadosFicheiro[4]);
            dadosNovos[4][indice] = Integer.parseInt(dadosFicheiro[NUMERO_DADOS_DIFERENTES]);
            indice++;
        }
        return dadosNovos;
    }



    public static void executaOpcao(String opcao, boolean[] jaLeuFicheiros, String[] datas, int[][] dados) throws FileNotFoundException, UnsupportedEncodingException {
        switch (opcao) {
            case "1":
                // Ler ficheiros
                String opcaoTipoFicheiro = selecionarTipoFicheiro();
                String caminhoFicheiro = selecionarFicheiro();
                int numLinhas = tamanhoLinhasFicheiro(caminhoFicheiro);
                switch (opcaoTipoFicheiro) {
                    case "1":
                        datas = new String[numLinhas];
                        datas = lerDatas(caminhoFicheiro,numLinhas,datas);
                        dados = new int[NUMERO_DADOS_DIFERENTES][numLinhas];
                        dados = lerDados(caminhoFicheiro,numLinhas,dados);
                        jaLeuFicheiros[0]=true;
                        break;
                    case "2":
                        datas = new String[numLinhas];
                        datas = lerDatas(caminhoFicheiro,numLinhas,datas);
                        dados = new int[NUMERO_DADOS_DIFERENTES][numLinhas];
                        dados = lerDados(caminhoFicheiro,numLinhas,dados);
                        jaLeuFicheiros[1]=true;
                        break;
                }
                pressioneEnterParaCont();
                break;
            case "2":
                // Ver dados diarios
                opcaoTipoFicheiro = selecionarTipoVisualizacao();
                verDadosDiarios(opcaoTipoFicheiro, jaLeuFicheiros, datas, dados);
                pressioneEnterParaCont();
                break;
            case "3":
                // Ver dados Semanais
                opcaoTipoFicheiro = selecionarTipoFicheiro();
                verDadosSemanais(opcaoTipoFicheiro,jaLeuFicheiros,datas,dados);
                pressioneEnterParaCont();
                break;
            case "4":
                opcaoTipoFicheiro=selecionarTipoFicheiro();
                verDadosMensais(opcaoTipoFicheiro,jaLeuFicheiros,datas,dados);
                pressioneEnterParaCont();
                break;
            case "0":
                System.out.println("OBRIGADO PELA PREFERÊNCIA!");
            default:
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.\n");
                pressioneEnterParaCont();
                break;
        }
    }

    public static void verDadosDiarios(String opcao, boolean[] jaLeuFicheiros, String[] datas, int[][] dados) throws FileNotFoundException, UnsupportedEncodingException {
        switch (opcao) {
            case "1":
                if (jaLeuFicheiros[0]) {
                    String[] leituraDatas = leituraIntervaloDatas();
                    mostrarDadosDiarios(leituraDatas, datas, dados);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroAcumuladoDiarios(diretorio, leituraDatas, datas, dados);
                    }
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    String[] leituraDatas = leituraIntervaloDatas();
                    mostrarDadosTotaisDiarios(leituraDatas, datas, dados);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroTotalDiarios(diretorio, leituraDatas, datas, dados);
                    }
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            default:
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.");
                break;
        }
    }

    public static void imprimirFicheiroAcumuladoDiarios(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados) {
        String nomeFicheiro = diretorio + "/dados_acumulados_diarios_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        int indexData1 = indexData(stringParaDate(leituraDeDatas[0]),datas);
        int indexData2 = indexData(stringParaDate(leituraDeDatas[1]),datas);

        ficheiroEscrita.println("Data,Novos Infetados,Novas Hospitalizações,Novos UCI,Novas Mortes");

        for (int i = 0; i <= indexData2-indexData1; i++) {
            int[] novosInfetados = dadosDiariosNovos(dados[1],leituraDeDatas,datas);
            int[] novosHospitalizacoes = dadosDiariosNovos(dados[2], leituraDeDatas, datas);
            int[] novosUCI = dadosDiariosNovos(dados[3], leituraDeDatas, datas);
            int[] novosMortes = dadosDiariosNovos(dados[4], leituraDeDatas, datas);
            if (novosInfetados[i]==-1 && novosHospitalizacoes[i]==-1 && novosUCI[i]==-1 && novosMortes[i]==-1) {
                ficheiroEscrita.println(datas[i] + "," + "Sem dados" + "," + "Sem dados" + "," + "Sem dados" + "," + "Sem dados");
            } else {
                ficheiroEscrita.println(datas[i + indexData1] + "," + novosInfetados[i] + "," + novosHospitalizacoes[i] + "," + novosUCI[i] + "," + novosMortes[i]);
            }
        }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroTotalDiarios(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados) {
        String nomeFicheiro = diretorio + "/dados_totais_diarios_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        int indexData1 = indexData(stringParaDate((leituraDeDatas[0])),datas);
        int indexData2 = indexData(stringParaDate((leituraDeDatas[1])),datas);

        ficheiroEscrita.println("Data,Total Infetados,Total Hospitalizações,Total UCI,Total Mortes");

        for (int i = 0; i <= indexData2-indexData1; i++) {
            int[] dadosInfetados = dados[1];
            int[] dadosHospitalizados = dados[2];
            int[] dadosUCI = dados[3];
            int[] dadosMortes = dados[4];
            ficheiroEscrita.println(datas[i+indexData1] + "," + dadosInfetados[i] + "," + dadosHospitalizados[i] + "," + dadosUCI[i] + "," + dadosMortes[i]);
        }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroAcumuladoSemanais(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados) {
        String nomeFicheiro = diretorio + "/dados_acumulados_semanais_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        int indexData1 = indexData(verificarSemanaSegunda(stringParaDate(leituraDeDatas[0])),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDate(leituraDeDatas[1])),datas);
        int numeroSemanas = calcularNumSemanas(stringParaDate(leituraDeDatas[0]),stringParaDate(leituraDeDatas[1]));

        if (numeroSemanas == 0) {
            ficheiroEscrita.println("As datas não contêm pelo menos uma semana completa.");
        } else {
            ficheiroEscrita.println("Data,Novos Infetados,Novas Hospitalizações,Novos UCI,Novas Mortes");

            for (int i = 0; i < numeroSemanas; i++) {
                int[] novosInfetados = dadosSemanaisNovos(dados[1],numeroSemanas,indexData2,indexData1);
                int[] novosHospitalizacoes = dadosSemanaisNovos(dados[2],numeroSemanas,indexData2,indexData1);
                int[] novosUCI = dadosSemanaisNovos(dados[3], numeroSemanas,indexData2,indexData1);
                int[] novosMortes = dadosSemanaisNovos(dados[4],numeroSemanas,indexData2,indexData1);
                ficheiroEscrita.println(datas[i+indexData1+7] + "," + datas[indexData1+2*i+7] + "," + novosInfetados[i] + "," + novosHospitalizacoes[i] + "," + novosUCI[i] + "," + novosMortes[i]);
            }
        }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroTotalSemanais(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados) {
        String nomeFicheiro = diretorio + "/dados_totais_semanais_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        int indexData1 = indexData(verificarSemanaSegunda(stringParaDate((leituraDeDatas[0]))),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDate((leituraDeDatas[1]))),datas);
        int numeroSemanas = calcularNumSemanas(stringParaDate((leituraDeDatas[0])),stringParaDate((leituraDeDatas[1])));

        if (numeroSemanas == 0) {
            ficheiroEscrita.println("As datas não contêm pelo menos uma semana completa.");
        } else {
           ficheiroEscrita.println("Data,Total Infetados,Total Hospitalizações,Total UCI,Total Mortes");
            for (int i = 0; i < numeroSemanas; i++) {
                int[] totaisInfetados = dadosTotaisSemanaisNovos(dados[1],numeroSemanas,indexData2,indexData1);
                int[] totaisHospitalizacoes = dadosTotaisSemanaisNovos(dados[2],numeroSemanas,indexData2,indexData1);
                int[] totaisUCI = dadosTotaisSemanaisNovos(dados[3], numeroSemanas,indexData2,indexData1);
                int[] totaisMortes = dadosTotaisSemanaisNovos(dados[4],numeroSemanas,indexData2,indexData1);
                ficheiroEscrita.println("Semana " + (1+i) + "," + totaisInfetados[i] + "," + totaisHospitalizacoes[i] + "," + totaisUCI[i] + "," + totaisMortes[i]);
            }
        }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void verDadosSemanais (String opcao, boolean[] jaLeuFicheiros, String[] datas, int[][] dados) {
        switch (opcao) {
            case "1":
                if (jaLeuFicheiros[0]) {
                    String[] leituraDatas = leituraIntervaloDatas();
                    mostrarDadosSemanais(leituraDatas, datas, dados);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroAcumuladoSemanais(diretorio, leituraDatas, datas, dados);
                    }
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    String[] leituraDatas = leituraIntervaloDatas();
                    mostrarDadosTotaisSemanais(leituraDatas, datas, dados);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroTotalSemanais(diretorio, leituraDatas, datas, dados);
                    }
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            default:
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.");
                break;
        }
    }

    public static void verDadosMensais (String opcao, boolean[] jaLeuFicheiros, String[] datas, int[][] dados) {
        switch (opcao) {
            case "1":
                if (jaLeuFicheiros[0]) {
                    mostrarDadosMensais(leituraIntervaloDatas(), datas, dados);
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    mostrarDadosTotaisMensais(leituraIntervaloDatas(), datas,dados);
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            default:
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.");
                break;
        }
    }

    public static String selecionarTipoFicheiro() {
        String selecaoUtilizador;
        do {
            // Apresentação do menu
            System.out.println("----------------------------");
            System.out.println("Por favor, escolha uma opção:");
            System.out.println("1. Carregar dados acumulados");
            System.out.println("2. Carregar dados totais");
            System.out.println("----------------------------");
            System.out.println();
            System.out.print("> ");

            selecaoUtilizador = kbScanner.nextLine();

            if (!selecaoUtilizador.equals("1") && !selecaoUtilizador.equals("2")) {
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.");
                pressioneEnterParaCont();
            }
        } while (!selecaoUtilizador.equals("1") && !selecaoUtilizador.equals("2"));

        return selecaoUtilizador;
    }

    public static String selecionarTipoVisualizacao() {
        String selecaoUtilizador;
        do {
        // Apresentação do menu
            System.out.println("-------------------------");
            System.out.println("Por favor, escolha uma opção:");
            System.out.println("1. Visualizar novos casos");
            System.out.println("2. Visualizar casos totais");
            System.out.println("-------------------------");
            System.out.println();
            System.out.print("> ");

            selecaoUtilizador = kbScanner.nextLine();

            if (!selecaoUtilizador.equals("1") && !selecaoUtilizador.equals("2")) {
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.");
            }
        } while (!selecaoUtilizador.equals("1") && !selecaoUtilizador.equals("2"));

        return selecaoUtilizador;
    }

//-------------------------------------------Funcionamento Aplicação--------------------------------------------------//

/*
                case "5":
                    long diasIntervalo1;
                    long diasIntervalo2;
                    String[] intervalo1;
                    String[] intervalo2;
                    do {
                        System.out.println("1º intervalo:");
                        intervalo1 = leituraDeDatas();
                        // calcular intervalo de dias do intervalo1
                        diasIntervalo1=calcularDiasEntreIntervalo(intervalo1);
                        System.out.println("\n2º intervalo:");
                        // calcular intervalo de dias do intervalo2
                        intervalo2 = leituraDeDatas();
                        diasIntervalo2 = calcularDiasEntreIntervalo(intervalo2);
                    } while (diasIntervalo1 <0 && diasIntervalo2<0);
                    analiseComparativaNovosCasos(diasIntervalo1,diasIntervalo2,datas,intervalo1,intervalo2,totaisInfetado,  totaisHospitalizados,  totaisUCI, obitos);
                    pressioneEnterParaCont();
                    //calcular as diferenças, media e desvio padrao
                    break;
                case "6":
                    System.out.println();
                    pressioneEnterParaCont();
                    break;
                case "7":
                    System.out.println();
                    pressioneEnterParaCont();
                    break;
                case "0":
                    // terminar a execução do programa
                    System.out.println("Obrigado pela preferência!");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    pressioneEnterParaCont();
                    break;
            }
        } while (!selecaoUtilizador.equals("0"));
    } */

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

    public static String menu (){
        String selecaoUtilizador;
        // Apresentação do menu
        System.out.println("\n------------------------------");
        System.out.println("Por favor, escolha uma opção:\n");
        System.out.println("1. Carregar ficheiros");
        System.out.println("2. Visualizar dados diários");
        System.out.println("3. Visualizar dados semanais");
        System.out.println("4. Visualizar dados mensais");
        System.out.println("5. Comparar intervalo de datas");
        System.out.println("6. Previsões sobre a pandemia");
        System.out.println("0. Sair");
        System.out.println("------------------------------\n");
        System.out.print("> ");

        selecaoUtilizador = kbScanner.nextLine();
        return selecaoUtilizador;
    }

    public static void pressioneEnterParaCont() {
        System.out.print("\nPressione ENTER para continuar... ");
        kbScanner.nextLine();
        System.out.println();
    }

    public static String guardarOuSair() {
        System.out.print("\nDeseja guardar as informações apresentadas? (S/N): ");
        String opcao = kbScanner.nextLine();
        String diretorio = "";
        switch (opcao) {
            case "S", "s":
                boolean eDiretorioValido = true;
                do {
                    eDiretorioValido = true;
                    System.out.print("\nInsira o caminho absoluto do diretório (pasta) onde deseja guardar o ficheiro: ");
                    diretorio = kbScanner.nextLine();
                    if (!(new File(diretorio).isDirectory())) {
                        eDiretorioValido = false;
                        System.out.println("ERRO: Diretório não encontrado. Por favor, insira um diretório existente.");
                    }
                } while (!eDiretorioValido);
                break;
            case "N", "n":
                System.out.println();
                break;
        }
        return diretorio;
    }

    public static String selecionarFicheiro() {
        String caminho;
        do {
            System.out.print("\nInsira o caminho absoluto do ficheiro: ");
            caminho = kbScanner.nextLine();
            if (!(new File(caminho)).isFile()) {
                System.out.println("ERRO: Ficheiro não encontrado. Por favor, insira o caminho absoluto de um ficheiro válido.");
            }
        } while (!(new File(caminho).isFile()));

        return caminho;
    }

    public static String[] leituraIntervaloDatas () {
        String[] leituraDatas = new String[2];
        boolean dataEValida;
        boolean dataFinalDepoisQueInicial;
        do {
            do {
                System.out.print("\nInsira a data inicial (AAAA-MM-DD) ou (DD-MM-AAAA): ");
                leituraDatas[0] = kbScanner.nextLine();
                if (!verificarData1(((leituraDatas[0]))) && !verificarData2((leituraDatas[0]))) {
                    dataEValida = false;
                    System.out.println("ERRO: Formato de data inválido. Por favor, insira a data no formato (AAAA-MM-DD) ou (DD-MM-AAAA).");
                } else {
                    dataEValida = true;
                }
            } while (!dataEValida);

            do {
                System.out.print("Insira a data final (AAAA-MM-DD) ou (DD-MM-AAAA): ");
                leituraDatas[1] = kbScanner.nextLine();

                if (!verificarData1((leituraDatas[1])) && !verificarData2((leituraDatas[1]))) {
                    dataEValida = false;
                    System.out.println("ERRO: Formato de data inválido. Por favor, insira a data no formato (AAAA-MM-DD) ou (DD-MM-AAAA).\n");
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

    public static String converterDatasEntreSi (String data) {
        String[] date = data.split("-");
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
        data = ano + "-" + mes + "-" + dia;
        return data;
    }

    //--------------------------------------------Analise Diária Novos Casos------------------------------------------//

    public static void mostrarDadosDiarios(String[] leituraDeDatas, String[] datas, int[][] dados) throws FileNotFoundException, UnsupportedEncodingException {
        int indexData1 = indexData(stringParaDate(leituraDeDatas[0]),datas);
        int indexData2 = indexData(stringParaDate(leituraDeDatas[1]),datas);

        System.out.printf("\nData %5s | Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n", "");

        for (int i = 0; i <= indexData2 - indexData1; i++) {
            int[] novosInfetados = dadosDiariosNovos(dados[1], leituraDeDatas, datas);
            int[] novosHospitalizacoes = dadosDiariosNovos(dados[2], leituraDeDatas, datas);
            int[] novosUCI = dadosDiariosNovos(dados[3], leituraDeDatas, datas);
            int[] novosMortes = dadosDiariosNovos(dados[4], leituraDeDatas, datas);
            if (novosInfetados[i] == -1 && novosHospitalizacoes[i] == -1 && novosUCI[i] == -1 && novosMortes[i] == -1) {
                System.out.printf("%s | %15s | %21.10s | %9.10s | %12.10s \n", datas[i], "Sem dados", "Sem dados", "Sem dados", "Sem dados");
            } else {
                System.out.printf("%s | %15s | %21.10s | %9.10s | %12.10s \n", datas[i + indexData1], novosInfetados[i], novosHospitalizacoes[i], novosUCI[i], novosMortes[i]);
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

    public static void mostrarDadosTotaisDiarios (String[] leituraDeDatas,String[] datas, int[][] dados) {
        int indexData1 = indexData(stringParaDate((leituraDeDatas[0])),datas);
        int indexData2 = indexData(stringParaDate((leituraDeDatas[1])),datas);

        System.out.printf("\nData %5s | Total Infetados | Total Hospitalizações | Total UCI | Total Mortes\n" , "" );
        for (int i = 0; i <= indexData2-indexData1; i++) {
            int[] dadosInfetados = dados[1];
            int[] dadosHospitalizados = dados[2];
            int[] dadosUCI = dados[3];
            int[] dadosMortes = dados[4];
            System.out.printf("%s | %15s | %21.10s | %9.10s | %12.10s \n", datas[i+indexData1],dadosInfetados[i],dadosHospitalizados[i],dadosUCI[i],dadosMortes[i]);
        }
    }

    //--------------------------------------------Analise Semanal Novos Casos-----------------------------------------------------//

    public static void mostrarDadosSemanais(String[] leituraDeDatas, String[] datas, int[][] dados)  {
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
                int[] novosInfetados = dadosSemanaisNovos(dados[1],numeroSemanas,indexData2,indexData1);
                int[] novosHospitalizacoes = dadosSemanaisNovos(dados[2],numeroSemanas,indexData2,indexData1);
                int[] novosUCI = dadosSemanaisNovos(dados[3], numeroSemanas,indexData2,indexData1);
                int[] novosMortes = dadosSemanaisNovos(dados[4],numeroSemanas,indexData2,indexData1);
                System.out.printf("%s - %s  %25s | %21.10s | %9.10s | %12.10s \n", datas[i+indexData1+7],datas[indexData1+2*i+7], novosInfetados[i],novosHospitalizacoes[i],novosUCI[i],novosMortes[i]);
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

    public static void mostrarDadosTotaisSemanais (String[] leituraDeDatas,String[] datas, int[][] dados){
        int indexData1 = indexData(verificarSemanaSegunda(stringParaDate((leituraDeDatas[0]))),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDate((leituraDeDatas[1]))),datas);
        int numeroSemanas = calcularNumSemanas(stringParaDate((leituraDeDatas[0])),stringParaDate((leituraDeDatas[1])));

        if (numeroSemanas == 0) {
            System.out.println("Introduza datas que contenham pelo menos 1 semana");
        } else {
            System.out.printf("%19s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n" , "" );
            for (int i = 0; i < numeroSemanas; i++) {
                int[] totaisInfetados = dadosTotaisSemanaisNovos(dados[1],numeroSemanas,indexData2,indexData1);
                int[] totaisHospitalizacoes = dadosTotaisSemanaisNovos(dados[2],numeroSemanas,indexData2,indexData1);
                int[] totaisUCI = dadosTotaisSemanaisNovos(dados[3], numeroSemanas,indexData2,indexData1);
                int[] totaisMortes = dadosTotaisSemanaisNovos(dados[4],numeroSemanas,indexData2,indexData1);
                System.out.printf("Semana " + (1+i) + ": %25s | %21.10s | %9.10s | %12.10s \n", totaisInfetados[i],totaisHospitalizacoes[i],totaisUCI[i],totaisMortes[i]);
            }
        }
    }

    public static int[] dadosTotaisSemanaisNovos (int[] dados, int numeroSemanas,int indexData2,int indexData1) {

        int[] dadosNovos =  new int[numeroSemanas];

        for (int j = 0; j < numeroSemanas ; j++) {
            if (indexData1<=indexData2) {
                dadosNovos[j] = dados[indexData1]  + dados[indexData1+1];
                indexData1 = indexData1 + 1;
            }
        }
        return dadosNovos;
    }


    // ------------------------------------------Analise Mensal Novos Casos-------------------------------------------------------//

    public static void mostrarDadosMensais (String[] leituraDeDatas, String[] datas, int[][] dados)  {
        // 4 semanas -> 1 mes
        // Dados para mostrar = acumulado ult dia do mes - acumulado pri dia do mes
        int indexData1 = indexData(primeiroDiaMesValido(stringParaDate(leituraIntervaloDatas()[0])),datas);
        int indexData2 = indexData(ultimoDiaMesValido(stringParaDate(leituraIntervaloDatas()[1])),datas);
        int numeroMeses = numeroMeses(leituraDeDatas);

        if (numeroMeses == 0) {
            System.out.println("Introduza datas que contenham pelo menos 1 mês");
        } else {
            System.out.printf("%16s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n" , "" );
            for (int i = 0; i < numeroMeses; i++) {
                int[] novosInfetados = dadosMensaisNovos(dados[1],numeroMeses,indexData2,indexData1);
                int[] novosHospitalizacoes = dadosMensaisNovos(dados[2],numeroMeses,indexData2,indexData1);
                int[] novosUCI = dadosMensaisNovos(dados[3], numeroMeses,indexData2,indexData1);
                int[] novosMortes = dadosMensaisNovos(dados[4],numeroMeses,indexData2,indexData1);
                System.out.printf("Mês " + (1+i) + ": %25s | %21.10s | %9.10s | %12.10s \n", novosInfetados[i],novosHospitalizacoes[i],novosUCI[i],novosMortes[i]);
            }
        }
    }

    public static int[] dadosMensaisNovos (int[] dados,int numeroMeses,int index2,int index1) {
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

   public static void mostrarDadosTotaisMensais(String[] leituraDeDatas,String[] datas,int[][] dados) {
       int indexData1 = indexData(primeiroDiaMesValido(stringParaDate(leituraDeDatas[0])),datas);
       int indexData2 = indexData(ultimoDiaMesValido(stringParaDate(leituraDeDatas[1])),datas);
       int numeroMeses = numeroMeses(leituraDeDatas);

       if (numeroMeses == 0) {
           System.out.println("Introduza datas que contenham pelo menos 1 mês");
       } else {
           System.out.printf("%16s Total Infetados | Total Hospitalizações | Total UCI | Total Mortes\n" , "" );
           for (int i = 0; i < numeroMeses; i++) {
               int[] novosInfetados = dadosMensaisTotaisNovos(dados[1],numeroMeses,indexData2,indexData1);
               int[] novosHospitalizacoes = dadosMensaisTotaisNovos(dados[2],numeroMeses,indexData2,indexData1);
               int[] novosUCI = dadosMensaisTotaisNovos(dados[3], numeroMeses,indexData2,indexData1);
               int[] novosMortes = dadosMensaisTotaisNovos(dados[4],numeroMeses,indexData2,indexData1);
               System.out.printf("Mês " + (1+i) + ": %25s | %21.10s | %9.10s | %12.10s \n", novosInfetados[i],novosHospitalizacoes[i],novosUCI[i],novosMortes[i]);
           }
       }
    }

    public static int[] dadosMensaisTotaisNovos (int[] dados, int numeroMeses, int index2, int index1) {
        int[] dadosNovos =  new int[numeroMeses];

        Calendar calendar = Calendar.getInstance();
        int diasNoMes = calendar.getActualMaximum(Calendar.MONTH);

        if (numeroMeses==1){
            for (int i = 0; i < diasNoMes; i++) {
                if (index1<=index2) {
                    dadosNovos[0]=dados[index1];
                    index1 = index1+i;
                }
            }
        } else {
            for (int i = 0; i < numeroMeses; i++) {
                for (int j = 0; j < diasNoMes; j++) {
                    if(index1<=index2) {
                        dadosNovos[i] = dados[index1];
                        index1 = index1 + j;
                    }
                }
                calendar.add(Calendar.MONTH,1);
            }
        }
        return dadosNovos;
    }

    //----------------------------------------------------------------------------------------------------------------//
        public static void analiseComparativaNovosCasos (long intervalo,long intervalo2,String[] datas,String[] intervaloinicial,String[] intervaloFinal,int[] acumuladoInfetados, int[] acumuladoHospitalizados, int[] acumuladoUCI, int[] acumuladoMortes) {
            // primeiro dia do primeiro intervalo
            // primeiro dia do segundo intervalo
            // numero de dias a comparar
            // fazer contas

            int numeroDiasAComparar = (int) intervalo;
            if(intervalo < intervalo2) {
                numeroDiasAComparar = (int) intervalo;
            } else if (intervalo2<intervalo) {
                numeroDiasAComparar = (int) intervalo2;
            }
            int indexData1 = indexData(stringParaDate(intervaloinicial[0]),datas);
            int indexData2 = indexData(stringParaDate(intervaloFinal[1]),datas);

            System.out.printf("\nDados %15s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n" , "" );
            for (int i = 0; i < NUMERO_DADOS_COMPARACAO; i++) {
                int[][] comparacaoNovosInfetados = comparacaoDadosDiariosNovos(indexData1,indexData2,acumuladoInfetados,numeroDiasAComparar);
                int[][] comparacaoNovosHospitalizacoes = comparacaoDadosDiariosNovos(indexData1,indexData2,acumuladoHospitalizados,numeroDiasAComparar);
                int[][] comparacaoNovosUCI = comparacaoDadosDiariosNovos(indexData1,indexData2, acumuladoUCI,numeroDiasAComparar);
                int[][] comparacaoNovosMortes = comparacaoDadosDiariosNovos(indexData1,indexData2, acumuladoMortes,numeroDiasAComparar);
                if (comparacaoNovosInfetados[i][i]==-1 && comparacaoNovosHospitalizacoes[i][i]==-1 && comparacaoNovosUCI[i][i]==-1 && comparacaoNovosMortes[i][i]==-1) {
                    System.out.printf("%s %25s | %21.10s | %9.10s | %12.10s \n", datas[i],"Sem dados","Sem dados","Sem dados","Sem dados");
                    System.out.println();
                } else {
                    for (int j = 0; j <= numeroDiasAComparar; j++) {
                        System.out.printf("%s 1ªIntervalo %25s | %21.10s | %9.10s | %12.10s \n", datas[j + indexData1], comparacaoNovosInfetados[0][j], comparacaoNovosHospitalizacoes[0][j], comparacaoNovosUCI[0][j], comparacaoNovosMortes[0][j]);
                        System.out.printf("%s 2ªIntervalo %25s | %21.10s | %9.10s | %12.10s \n", datas[j + indexData2], comparacaoNovosInfetados[1][j], comparacaoNovosHospitalizacoes[1][j], comparacaoNovosUCI[1][j], comparacaoNovosMortes[1][j]);
                        System.out.println();
                    }
                }
            }
        }

    public static int[][] comparacaoDadosDiariosNovos (int indexData1,int indexData2, int[] dados, int numeroDias) {
        int indice1 = 0;
        int indice2 = 0;
        int[][] dadosNovos =  new int[NUMERO_DADOS_COMPARACAO][numeroDias];

        for (int j = indexData1; j <= numeroDias ; j++) {
            dadosNovos[0][indice1] = dados[j]-dados[j-1];
            indice1++;
        }
        for (int i = indexData2; i <= numeroDias; i++) {
            dadosNovos[1][indice2]= dados[i]-dados[i-1];
            indice2++;
        }
        return dadosNovos;
    }

    //----------------------------------------------------------------------------------------------------------------//

    public static boolean verificarData1(String data) {
        return data.matches("\\d{2}-\\d{2}-\\d{4}");
    }

    public static boolean verificarData2(String data) {
        return data.matches("\\d{4}-\\d{2}-\\d{2}");
    }

     public static long calcularDiasEntreIntervalo(String[] intervalo)  {

         Date inicio = stringParaDate(intervalo[0]);
         Date fim = stringParaDate(intervalo[1]);

         int diffdias = 0;
         long diff = fim.getTime() - inicio.getTime();
         long diffDias = diff / (24 * 60 * 60 * 1000) + 1;
         diffdias = (int) diffDias;

         System.out.println(diffdias);
         return diffdias;
    }
}
