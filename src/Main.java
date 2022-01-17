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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;

public class Main {
    static final Scanner kbScanner = new Scanner(System.in);
    static final int NUMERO_DADOS_COMPARACAO = 2;
    static final int NUMERO_FICHEIRO_DIFERENTE =3;
    static final int NUMERO_DADOS_DIFERENTES = 5;
    static final int NUMERO_ESTADOS_DIFERENTES =5;

    public static void main(String[] args) throws FileNotFoundException {
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

        System.out.println("\n\n                      Bem-vindo!                                  ");
        System.out.println("Para continuar, necessita de carregar pelo menos um ficheiro. \n");
        String opcaoTipoFicheiro = selecionarTipoFicheiro();
        String caminhoFicheiro = selecionarFicheiro();
        switch (opcaoTipoFicheiro) {
            case "1":
                int numLinhas = tamanhoLinhasFicheiro(caminhoFicheiro);
                acumuladoDatas = new String[numLinhas];
                acumuladoDatas = lerDatas(caminhoFicheiro,numLinhas);
                acumuladoDados = new int[NUMERO_DADOS_DIFERENTES][numLinhas];
                acumuladoDados = lerDados(caminhoFicheiro,numLinhas);
                totalDatas = new String[numLinhas];
                totalDatas = lerDatas(caminhoFicheiro,numLinhas);
                totalDados = new int[NUMERO_DADOS_DIFERENTES][numLinhas];
                totalDados = lerDados(caminhoFicheiro,numLinhas);
                jaleuFicheiros[0] = true;
                System.out.println("Ficheiro lido com sucesso!");
                pressioneEnterParaCont();
                opcaoTipoFicheiro=menu();
                executaOpcao(opcaoTipoFicheiro,jaleuFicheiros,acumuladoDatas,acumuladoDados,totalDatas,totalDados);
                break;
            case "2":
                numLinhas = tamanhoLinhasFicheiro(caminhoFicheiro);
                totalDatas = new String[numLinhas];
                totalDatas = lerDatas(caminhoFicheiro,numLinhas);
                totalDados = new int[NUMERO_DADOS_DIFERENTES][numLinhas];
                totalDados = lerDados(caminhoFicheiro,numLinhas);
                acumuladoDatas = new String[numLinhas];
                acumuladoDatas = lerDatas(caminhoFicheiro,numLinhas);
                acumuladoDados = new int[NUMERO_DADOS_DIFERENTES][numLinhas];
                acumuladoDados = lerDados(caminhoFicheiro,numLinhas);
                jaleuFicheiros[1] = true;
                System.out.println("Ficheiro lido com sucesso!");
                pressioneEnterParaCont();
                opcaoTipoFicheiro=menu();
                executaOpcao(opcaoTipoFicheiro,jaleuFicheiros,acumuladoDatas,acumuladoDados,totalDatas,totalDados);
                break;
        }
    }

//-------------------------------------------Funcionamento Aplicação----------------------------------------------//

    public static String[] lerDatas(String caminhoFicheiro, int numeroLinhas) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoFicheiro));
        String[] datas = new String[numeroLinhas];
        String linha = scanner.nextLine();
        int indice = 0;
        while (scanner.hasNextLine()){
            linha = scanner.nextLine();
            String[] dadosFicheiro = linha.split(",");
            datas[indice] = dadosFicheiro[0];
            indice++;
        }
        return datas;
    }

    public static int[][] lerDados(String caminhoFicheiro, int numeroLinhas) throws FileNotFoundException {
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
            dadosNovos[4][indice] = Integer.parseInt(dadosFicheiro[5]);
            indice++;
        }
        return dadosNovos;
    }

   /* public static int[][] lerDadosMatriz(String caminhoFicheiro,int numLinhas) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoFicheiro));
        int[][] dados = new int[NUMERO_ESTADOS_DIFERENTES][NUMERO_DADOS_DIFERENTES];
        String linha;
        int indice=0;
        while (scanner.hasNextLine()) {
            dados[0][indice] = Integer.parseInt(dadosFicheiro[1]);
            dados[1][indice] = Integer.parseInt(dadosFicheiro[2]);
            dados[2][indice] = Integer.parseInt(dadosFicheiro[3]);
            dados[3][indice] = Integer.parseInt(dadosFicheiro[4]);
            dados[4][indice] = Integer.parseInt(dadosFicheiro[5]);
            linha = scanner.nextLine();
            indice++;
        }
        return dados;
    } */

    public static void executaOpcao(String opcao,boolean[] jaLeuFicheiros, String[] datasAcumulados,int[][] acumuladoDados, String[] datasTotais, int[][] dadosTotais) throws FileNotFoundException {
        do {
            switch (opcao) {
                case "1":
                    // Ler ficheiros
                    String opcaoTipoFicheiro = selecionarTipoFicheiro();
                    String caminhoFicheiro = selecionarFicheiro();
                    int numLinhas = tamanhoLinhasFicheiro(caminhoFicheiro);
                    switch (opcaoTipoFicheiro) {
                        case "1":
                            datasAcumulados = new String[numLinhas];
                            datasAcumulados = lerDatas(caminhoFicheiro, numLinhas);
                            acumuladoDados = new int[NUMERO_DADOS_DIFERENTES][numLinhas];
                            acumuladoDados = lerDados(caminhoFicheiro, numLinhas);
                            jaLeuFicheiros[0] = true;
                            break;
                        case "2":
                            datasTotais = new String[numLinhas];
                            datasTotais = lerDatas(caminhoFicheiro, numLinhas);
                            dadosTotais = new int[NUMERO_DADOS_DIFERENTES][numLinhas];
                            dadosTotais = lerDados(caminhoFicheiro, numLinhas);
                            jaLeuFicheiros[1] = true;
                            break;
                    }
                    System.out.println("Ficheiro lido com sucesso!");
                    pressioneEnterParaCont();
                    opcao=menu();
                    break;
                case "2":
                    // Ver dados diarios
                    opcaoTipoFicheiro = selecionarTipoVisualizacao();
                    verDadosDiarios(opcaoTipoFicheiro, jaLeuFicheiros, datasAcumulados, acumuladoDados, datasTotais, dadosTotais);
                    pressioneEnterParaCont();
                    opcao=menu();
                    break;
                case "3":
                    // Ver dados Semanais
                    opcaoTipoFicheiro = selecionarTipoFicheiro();
                    verDadosSemanais(opcaoTipoFicheiro, jaLeuFicheiros, datasAcumulados, acumuladoDados,datasTotais,dadosTotais);
                    pressioneEnterParaCont();
                    opcao=menu();
                    break;
                case "4":
                    // Ver dados mensais
                    opcaoTipoFicheiro = selecionarTipoFicheiro();
                    verDadosMensais(opcaoTipoFicheiro, jaLeuFicheiros, datasAcumulados, acumuladoDados,datasTotais,dadosTotais);
                    pressioneEnterParaCont();
                    opcao=menu();
                    break;
                case "5":
                    // Ver analise comparativa
                    opcaoTipoFicheiro = selecionarTipoFicheiro();
                    verDadosComparativos(opcaoTipoFicheiro,jaLeuFicheiros,datasAcumulados,acumuladoDados,datasTotais,dadosTotais);
                    pressioneEnterParaCont();
                    opcao=menu();
                    break;
                case "6":
                    // Ver previsoes
                    verPrevisoes(jaLeuFicheiros,datasTotais,dadosTotais);
                    pressioneEnterParaCont();
                    opcao=menu();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.\n");
                    pressioneEnterParaCont();
                    opcao=menu();
                    break;
            }
        } while (!opcao.equals("0"));
        System.out.println("\nOBRIGADO PELA PREFERENCIA!");
    }

    public static void verDadosDiarios(String opcao, boolean[] jaLeuFicheiros, String[] datasAcumulados, int[][] dadosAcumaldos,String[] datasTotais, int[][] dadosTotais) {
        switch (opcao) {
            case "1":
                if (jaLeuFicheiros[0]) {
                    String[] leituraDatas = leituraIntervaloDatas();
                    int[] colunas = menuEscolherQtdDados();
                    mostrarDadosDiarios(leituraDatas, datasAcumulados, dadosAcumaldos, colunas);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroAcumuladoDiarios(diretorio, leituraDatas, datasAcumulados, dadosAcumaldos, colunas);
                    }
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    String[] leituraDatas = leituraIntervaloDatas();
                    int[] colunas = menuEscolherQtdDados();
                    mostrarDadosTotaisDiarios(leituraDatas,datasTotais,dadosTotais, colunas);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroTotalDiarios(diretorio,leituraDatas,datasTotais,dadosTotais, colunas);
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

    public static void verDadosSemanais (String opcao, boolean[] jaLeuFicheiros, String[] datasAcumulado, int[][] dadosAcumulado, String[] datasTotal, int[][] dadosTotal) {
        switch (opcao) {
            case "1":
                if (jaLeuFicheiros[0]) {
                    String[] leituraDatas = leituraIntervaloDatas();
                    int[] colunas = menuEscolherQtdDados();
                    mostrarDadosSemanais(leituraDatas, datasAcumulado, dadosAcumulado, colunas);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroAcumuladoSemanais(diretorio,leituraDatas,datasAcumulado,dadosAcumulado, colunas);
                    }
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    String[] leituraDatas = leituraIntervaloDatas();
                    int[] colunas = menuEscolherQtdDados();
                    mostrarDadosTotaisSemanais(leituraDatas, datasTotal,dadosTotal, colunas);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroTotalSemanais(diretorio,leituraDatas,datasAcumulado,dadosAcumulado, colunas);
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

    public static void verDadosMensais (String opcao, boolean[] jaLeuFicheiros, String[] datasAcumulado, int[][] dadosAcumulado, String[] datasTotal, int[][] dadosTotal) {
        switch (opcao) {
            case "1":
                if (jaLeuFicheiros[0]) {
                    mostrarDadosMensais(leituraIntervaloDatas(), datasAcumulado, dadosAcumulado);
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    mostrarDadosTotaisMensais(leituraIntervaloDatas(), datasTotal,dadosTotal);
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            default:
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.");
                break;
        }
    }

    public static void verDadosComparativos (String opcao, boolean[] jaLeuFicheiros, String[] datasAcumulado,int[][] dadosAcumulado, String[] datasTotal, int[][] dadosTotal) {
        switch (opcao) {
            case "1":
                if (jaLeuFicheiros[0]) {
                    System.out.println("1º intervalo:");
                    String[] intervalo1 = leituraIntervaloDatas();
                    System.out.println("\n2º intervalo:");
                    String[] intervalo2 = leituraIntervaloDatas();
                    int[] colunas = menuEscolherQtdDados();
                    analiseComparativaNovosCasos(intervalo1, intervalo2, datasTotal,dadosTotal, colunas);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroComparativaNovosCasos(diretorio, intervalo1, intervalo2, datasTotal,dadosTotal);
                    }
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    analiseComparativaTotaisCasos(datasTotal,dadosTotal);
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            default:
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.");
                break;
        }
    }

    public static void  verPrevisoes (boolean[] jaLeuFicheiro,String[] datas,int[][] dados) throws FileNotFoundException {
        // ler matriz do ficheiro
        // veririficar se o dia que ele introduziu pertence ao ficheiro
        // Se nao existir, escolher o dia mais proximo
        // Se existir, escolher o dia anterior
        // matriz elevada a diferença do dia escolhido pelo utilizador a multiplicar pelo vetor do dia escolhido
        boolean dataExiste;
        int indexData1;
        if  (jaLeuFicheiro[1]) {
            System.out.print("Introduza a data que pretende escolher para fazer a previsao no formato (AAAA-MM-DD) ou (DD-MM-AAAA): ");
            String data = kbScanner.nextLine();
            dataExiste = verificarDiaExiste(datas,data);
            indexData1 = indexData(stringParaDateEConverterDatas(data),datas);
            String matriz = selecionarMatriz();
            if (dataExiste) {
                if((indexData1-1)==-1){
                    data=datas[0];
                    mostrarPrevisao(data, datas, dados, matriz);
                } else {
                    data = escolherDiaAnterior(indexData1, datas);
                    mostrarPrevisao(data, datas, dados, matriz);
                }
            } else {
                data = escolherDiaMaisProximo(indexData1,datas);
                mostrarPrevisao(data,datas,dados,matriz);
            }
        } else {
            System.out.println("ERRO: Ficheiro de totais não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
        }
    }

    public static void imprimirFicheiroAcumuladoDiarios(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas) {
        String nomeFicheiro = diretorio + "/dados_acumulados_diarios_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        int indexData1 = indexData(stringParaDateEConverterDatas(leituraDeDatas[0]),datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(leituraDeDatas[1]),datas);

        String cabecalho = "Data";

        for (int opcao : colunas) {
            switch (opcao) {
                case 1:
                    cabecalho += ",Novos Infetados";
                    break;
                case 2:
                    cabecalho += ",Novas Hospitalizações";
                    break;
                case 3:
                    cabecalho += ",Novos UCI";
                    break;
                case 4:
                    cabecalho += ",Novas Mortes";
                    break;
            }
        }

        ficheiroEscrita.println(cabecalho);

        for (int i = 0; i <= indexData2-indexData1; i++) {
            int[] novosInfetados = dadosDiariosNovos(dados[1],leituraDeDatas,datas);
            int[] novosHospitalizacoes = dadosDiariosNovos(dados[2], leituraDeDatas, datas);
            int[] novosUCI = dadosDiariosNovos(dados[3], leituraDeDatas, datas);
            int[] novosMortes = dadosDiariosNovos(dados[4], leituraDeDatas, datas);
            if (novosInfetados[i]==-1 && novosHospitalizacoes[i]==-1 && novosUCI[i]==-1 && novosMortes[i]==-1) {
                ficheiroEscrita.println(datas[i] + mostraStrSeExistir(colunas, 1, ",Sem dados") + mostraStrSeExistir(colunas, 2, ",Sem dados") + mostraStrSeExistir(colunas, 3, ",Sem dados") + mostraStrSeExistir(colunas, 4, ",Sem dados"));
            } else {
                ficheiroEscrita.println(datas[i + indexData1] + mostraStrSeExistir(colunas, 1, "," + novosInfetados[i]) + mostraStrSeExistir(colunas, 2, "," + novosHospitalizacoes[i]) + mostraStrSeExistir(colunas, 3, "," + novosUCI[i]) + mostraStrSeExistir(colunas, 4, "," + novosMortes[i]));
            }
        }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroTotalDiarios(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas) {
        String nomeFicheiro = diretorio + "/dados_totais_diarios_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        int indexData1 = indexData(stringParaDateEConverterDatas((leituraDeDatas[0])),datas);
        int indexData2 = indexData(stringParaDateEConverterDatas((leituraDeDatas[1])),datas);

        String cabecalho = "Data";

        for (int opcao : colunas) {
            switch (opcao) {
                case 1:
                    cabecalho += ",Total Infetados";
                    break;
                case 2:
                    cabecalho += ",Total Hospitalizações";
                    break;
                case 3:
                    cabecalho += ",Total UCI";
                    break;
                case 4:
                    cabecalho += ",Total Mortes";
                    break;
            }
        }

        ficheiroEscrita.println(cabecalho);

        for (int i = 0; i <= indexData2-indexData1; i++) {
            int[] dadosInfetados = dados[1];
            int[] dadosHospitalizados = dados[2];
            int[] dadosUCI = dados[3];
            int[] dadosMortes = dados[4];
            ficheiroEscrita.println(datas[i+indexData1] + mostraStrSeExistir(colunas, 1, "," + dadosInfetados[i]) + mostraStrSeExistir(colunas, 2, "," + dadosHospitalizados[i]) + mostraStrSeExistir(colunas, 3, "," + dadosUCI[i]) + mostraStrSeExistir(colunas, 4, "," + dadosMortes[i]));
        }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroAcumuladoSemanais(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas) {
        String nomeFicheiro = diretorio + "/dados_acumulados_semanais_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        int indexData1 = indexData(verificarSemanaSegunda(stringParaDateEConverterDatas(leituraDeDatas[0])),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDateEConverterDatas(leituraDeDatas[1])),datas);
        int numeroSemanas = calcularNumSemanas(stringParaDateEConverterDatas(leituraDeDatas[0]),stringParaDateEConverterDatas(leituraDeDatas[1]));

        if (numeroSemanas == 0) {
            ficheiroEscrita.println("As datas não contêm pelo menos uma semana completa.");
        } else {
            String cabecalho = "Semana";

            for (int opcao : colunas) {
                switch (opcao) {
                    case 1:
                        cabecalho += ",Novos Infetados";
                        break;
                    case 2:
                        cabecalho += ",Novas Hospitalizações";
                        break;
                    case 3:
                        cabecalho += ",Novos UCI";
                        break;
                    case 4:
                        cabecalho += ",Novas Mortes";
                        break;
                }
            }

            ficheiroEscrita.println(cabecalho);

            for (int i = 0; i < numeroSemanas; i++) {
                int[] novosInfetados = dadosSemanaisNovos(dados[1],numeroSemanas,indexData2,indexData1);
                int[] novosHospitalizacoes = dadosSemanaisNovos(dados[2],numeroSemanas,indexData2,indexData1);
                int[] novosUCI = dadosSemanaisNovos(dados[3], numeroSemanas,indexData2,indexData1);
                int[] novosMortes = dadosSemanaisNovos(dados[4],numeroSemanas,indexData2,indexData1);
                ficheiroEscrita.println(datas[indexData1+(7*i)] + " - " + datas[(indexData1+(7*i))+6] + mostraStrSeExistir(colunas, 1, "," + novosInfetados[i]) + mostraStrSeExistir(colunas, 2, "," + novosHospitalizacoes[i]) + mostraStrSeExistir(colunas, 3, "," + novosUCI[i]) + mostraStrSeExistir(colunas, 4, "," + novosMortes[i]));
            }
        }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroTotalSemanais(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas) {
        String nomeFicheiro = diretorio + "/dados_totais_semanais_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        int indexData1 = indexData(verificarSemanaSegunda(stringParaDateEConverterDatas((leituraDeDatas[0]))),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDateEConverterDatas((leituraDeDatas[1]))),datas);
        int numeroSemanas = calcularNumSemanas(stringParaDateEConverterDatas((leituraDeDatas[0])),stringParaDateEConverterDatas((leituraDeDatas[1])));

        if (numeroSemanas == 0) {
            ficheiroEscrita.println("As datas não contêm pelo menos uma semana completa.");
        } else {
            String cabecalho = "Semana";

            for (int opcao : colunas) {
                switch (opcao) {
                    case 1:
                        cabecalho += ",Total Infetados";
                        break;
                    case 2:
                        cabecalho += ",Total Hospitalizações";
                        break;
                    case 3:
                        cabecalho += ",Total UCI";
                        break;
                    case 4:
                        cabecalho += ",Total Mortes";
                        break;
                }
            }

            ficheiroEscrita.println(cabecalho);
            for (int i = 0; i < numeroSemanas; i++) {
                int[] totaisInfetados = dadosTotaisSemanaisNovos(dados[1],numeroSemanas,indexData2,indexData1);
                int[] totaisHospitalizacoes = dadosTotaisSemanaisNovos(dados[2],numeroSemanas,indexData2,indexData1);
                int[] totaisUCI = dadosTotaisSemanaisNovos(dados[3], numeroSemanas,indexData2,indexData1);
                int[] totaisMortes = dadosTotaisSemanaisNovos(dados[4],numeroSemanas,indexData2,indexData1);
                ficheiroEscrita.println(datas[indexData1+(7*i)] + " - " + datas[(indexData1+(7*i))+6] + mostraStrSeExistir(colunas, 1, "," + totaisInfetados[i]) + mostraStrSeExistir(colunas, 2, "," + totaisHospitalizacoes[i]) + mostraStrSeExistir(colunas, 3, "," + totaisUCI[i]) + mostraStrSeExistir(colunas, 4, "," + totaisMortes[i]));
            }
        }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroComparativaNovosCasos(String diretorio, String[] intervalo1, String[] intervalo2, String[] datas, int[][] dados) {
        String nomeFicheiro = diretorio + "/dados_comparativos_novos_casos_entre_" + intervalo1[0] + "_a_" + intervalo1[1] + "_e_" + intervalo2[0] + "_a_" + intervalo2[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        long diasIntervalo1;
        long diasIntervalo2;

        // calcular intervalo de dias do intervalo1
        diasIntervalo1=calcularDiasEntreIntervalo(intervalo1);
        diasIntervalo2 = calcularDiasEntreIntervalo(intervalo2);

        int numeroDiasAComparar = (int) diasIntervalo1;
        if(diasIntervalo1 < diasIntervalo2) {
            numeroDiasAComparar = (int) diasIntervalo1;
        } else if (diasIntervalo2<diasIntervalo1) {
            numeroDiasAComparar = (int) diasIntervalo2;
        }
        int indexData1 = indexData(stringParaDateEConverterDatas(intervalo1[0]),datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(intervalo2[0]),datas);

        ficheiroEscrita.println("Datas,Novos Infetados,Novas Hospitalizações,Novos UCI,Novas Mortes");
        int[][] comparacaoInfet = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[1], numeroDiasAComparar);
        int[][] comparacaoHosp = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[2], numeroDiasAComparar);
        int[][] comparacaoUCI = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[3], numeroDiasAComparar);
        int[][] comparacaoObi = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[4], numeroDiasAComparar);
        for (int j = 0; j < numeroDiasAComparar; j++) {
            if ((indexData1+j) - 1 < 0) {
                ficheiroEscrita.println(datas[j + indexData1] + " 1ªIntervalo,Sem dados,Sem dados,Sem dados,Sem dados");
            } else {
                ficheiroEscrita.println(datas[j + indexData1] + " 1ªIntervalo," + comparacaoInfet[0][j] + "," + comparacaoHosp[0][j] + "," + comparacaoUCI[0][j] + "," + comparacaoObi[0][j]);
            }
            if ((indexData2+j)-1<0) {
                ficheiroEscrita.println(datas[j + indexData2] + " 2ªIntervalo,Sem dados,Sem dados,Sem dados,Sem dados");
            } else {
                ficheiroEscrita.println(datas[j + indexData2] + " 2ªIntervalo," + comparacaoInfet[1][j] + "," + comparacaoHosp[1][j] + "," + comparacaoUCI[1][j] + "," + comparacaoObi[1][j]);
            }
        }
        // media e desvio Padrão dos novos casos
        double mediaInfetado1 = mediaComparativa(comparacaoInfet[0]);
        double mediaInfetado2 = mediaComparativa(comparacaoInfet[1]);
        double mediaHosp1 = mediaComparativa(comparacaoHosp[0]);
        double mediaHosp2 = mediaComparativa(comparacaoHosp[1]);
        double mediaUCI1 = mediaComparativa(comparacaoUCI[0]);
        double mediaUCI2 = mediaComparativa(comparacaoUCI[1]);
        double mediaObitos1 = mediaComparativa(comparacaoObi[0]);
        double mediaObitos2 = mediaComparativa(comparacaoObi[1]);

        ficheiroEscrita.println("Media do 1ªIntervalo," + mediaInfetado1 + "," + mediaHosp1 + "," + mediaUCI1 + "," + mediaObitos1);
        ficheiroEscrita.println("Media do 2ªIntervalo," + mediaInfetado2 + "," + mediaHosp2 + "," + mediaUCI2 + "," + mediaObitos2);
        ficheiroEscrita.println("Desvio Padrão do 1ªIntervalo," + desvioPadrao(comparacaoInfet[0],mediaInfetado1) + "," + desvioPadrao(comparacaoHosp[0],mediaHosp1) + "," + desvioPadrao(comparacaoUCI[0],mediaUCI1) + "," + desvioPadrao(comparacaoObi[0],mediaObitos1));
        ficheiroEscrita.println("Desvio Padrão do 2ªIntervalo," + desvioPadrao(comparacaoInfet[1],mediaInfetado2) + "," + desvioPadrao(comparacaoHosp[1],mediaHosp2) + "," + desvioPadrao(comparacaoUCI[1],mediaUCI2) + "," + desvioPadrao(comparacaoObi[1],mediaObitos2));


        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    //-------------------------------------------Menus da Aplicação---------------------------------------------------//
    public static String selecionarTipoFicheiro() {
        String selecaoUtilizador;
        do {
            // Apresentação do menu
            System.out.println("| ------------------------------- |");
            System.out.println("| Por favor, escolha uma opção:   |");
            System.out.println("| 1. Carregar dados acumulados    |");
            System.out.println("| 2. Carregar dados totais        |");
            System.out.println("| ------------------------------- |");
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
            System.out.println("\n| ----------------------------- |");
            System.out.println("| Por favor, escolha uma opção: |");
            System.out.println("| 1. Visualizar novos casos     |");
            System.out.println("| 2. Visualizar casos totais    |");
            System.out.println("| ----------------------------- | ");
            System.out.println();
            System.out.print("> ");

            selecaoUtilizador = kbScanner.nextLine();

            if (!selecaoUtilizador.equals("1") && !selecaoUtilizador.equals("2")) {
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.");
            }
        } while (!selecaoUtilizador.equals("1") && !selecaoUtilizador.equals("2"));

        return selecaoUtilizador;
    }

    public static int[] menuEscolherQtdDados () {
        boolean todosInteiros = true;
        int[] dadosInteiros;

        do {
            todosInteiros = true;
            System.out.println("\nIntroduza o(s) dado(s) que quer visualizar. Separe as opções por vírgula (Ex.: 1,2,3)");
            System.out.println("1. Infetados");
            System.out.println("2. Hospitalizações");
            System.out.println("3. UCI");
            System.out.println("4. Mortes");
            System.out.println("0. Todos");
            System.out.print("\n> ");
            String tiposDados = kbScanner.nextLine();

            String[] dados = tiposDados.trim().split(",");
            dadosInteiros = new int[dados.length];

            try {
                for (String opcao : dados) {
                    if (Integer.parseInt(opcao) < 0 || Integer.parseInt(opcao) > 4) {
                        todosInteiros = false;
                    }
                }
            } catch (NumberFormatException e) {
                todosInteiros = false;
            }

            if (!todosInteiros) {
                System.out.println("ERRO: Opções inválidas. Insira um conjunto de opções válido, separados apenas por vírgula (Ex.: 1,2,3).");
            } else {
                for (int i = 0; i < dados.length; i++) {
                    dadosInteiros[i] = Integer.parseInt(dados[i]);
                }
            }
        } while (!todosInteiros);

        Arrays.sort(dadosInteiros);

        if (dadosInteiros.length == 1 && dadosInteiros[0] == 0) {
            int[] array = {1, 2, 3, 4};
            return array;
        } else {
            return dadosInteiros;
        }
    }

    public static String menu (){
        String selecaoUtilizador;
        // Apresentação do menu
        System.out.println("\n| ------------------------------- |");
        System.out.println("| Por favor, escolha uma opção:   |");
        System.out.println("| 1. Carregar ficheiros           |");
        System.out.println("| 2. Visualizar dados diários     |");
        System.out.println("| 3. Visualizar dados semanais    |");
        System.out.println("| 4. Visualizar dados mensais     |");
        System.out.println("| 5. Comparar intervalo de datas  |");
        System.out.println("| 6. Previsões sobre a pandemia   |");
        System.out.println("| 0. Sair                         |");
        System.out.println("| ------------------------------- |\n");
        System.out.print("> ");

        selecaoUtilizador = kbScanner.nextLine();
        return selecaoUtilizador;
    }

    //-------------------------------------------Tratamento Ficheiros-------------------------------------------------//

    public static int tamanhoLinhasFicheiro(String filePath) throws FileNotFoundException {
        Scanner file = new Scanner(new File(filePath));

        int linhas = 0;

        while (file.hasNextLine()) {
            linhas++;
            file.nextLine();
        }
        return linhas - 1;
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
        do {
            switch (opcao) {
                case "S":
                case "s":
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
                case "N":
                case "n":
                    System.out.println();
                    break;
                default:
                    System.out.println("\nInsira uma opção correta.");
                    System.out.print("\nDeseja guardar as informações apresentadas? (S/N): ");
                    opcao= kbScanner.nextLine();
            }
        } while(!opcao.equals("S") && !opcao.equals("s") && !opcao.equals("N") && !opcao.equals("n"));
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

    public static String selecionarMatriz() {
        String caminho;
        do {
            System.out.print("\nInsira o caminho absoluto do ficheiro que contém a matriz: ");
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

            if (stringParaDateEConverterDatas(leituraDatas[0]).after(stringParaDateEConverterDatas(leituraDatas[1]))) {
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
            if (leituraDeDatas.equals(stringParaDateEConverterDatas(datas[i]))) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

    public static boolean existeNoArray(int[] array, int valor) {
        for (int pos : array) {
            if (pos == valor) {
                return true;
            }
        }

        return false;
    }

    public static String mostraStrSeExistir (int[] array, int opcao, String texto) {
        if (existeNoArray(array, opcao)) {
            return texto;
        } else {
            return "";
        }
    }

    public static String mostraIntSeExistir (int[] array, int opcao, int valor) {
        if (existeNoArray(array, opcao)) {
            return String.valueOf(valor);
        } else {
            return "";
        }
    }

    public static String mostraDoubleSeExistir (int[] array, int opcao, double valor) {
        if (existeNoArray(array, opcao)) {
            return String.valueOf(valor);
        } else {
            return "";
        }
    }

    public static Date stringParaDateEConverterDatas(String leituraDeData) {
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

    public static void mostrarDadosDiarios(String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas) {

        int indexData1 = indexData(stringParaDateEConverterDatas(leituraDeDatas[0]),datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(leituraDeDatas[1]),datas);

        String cabecalho = "Data %5s";
        String impressao = "%s";

        for (int i = 1; i <= 4; i++) {
            if (existeNoArray(colunas, i)) {
                switch (i) {
                    case 1:
                        cabecalho += " | Novos Infetados";
                        impressao += " | %15s";
                        break;
                    case 2:
                        cabecalho += " | Novas Hospitalizações";
                        impressao += " | %21.10s";
                        break;
                    case 3:
                        cabecalho += " | Novos UCI";
                        impressao += " | %9.10s";
                        break;
                    case 4:
                        cabecalho += " | Novas Mortes";
                        impressao += " | %12.10s";
                        break;
                }
            } else {
                impressao += "%s";
            }
        }

        cabecalho += "\n";
        impressao += "\n";

        System.out.printf(cabecalho, "");
        for (int i = 0; i <= indexData2-indexData1; i++) {
            int[] novosInfetados = dadosDiariosNovos(dados[1],leituraDeDatas,datas);
            int[] novosHospitalizacoes = dadosDiariosNovos(dados[2], leituraDeDatas, datas);
            int[] novosUCI = dadosDiariosNovos(dados[3], leituraDeDatas, datas);
            int[] novosMortes = dadosDiariosNovos(dados[4], leituraDeDatas, datas);
            if (novosInfetados[i]==-1 && novosHospitalizacoes[i]==-1 && novosUCI[i]==-1 && novosMortes[i]==-1) {
                System.out.printf(impressao, datas[i], mostraStrSeExistir(colunas, 1, "Sem dados"),mostraStrSeExistir(colunas, 2, "Sem dados"),mostraStrSeExistir(colunas, 3, "Sem dados"),mostraStrSeExistir(colunas, 4, "Sem dados"));
            } else {
                System.out.printf(impressao, datas[i + indexData1], mostraIntSeExistir(colunas, 1, novosInfetados[i]), mostraIntSeExistir(colunas, 2, novosHospitalizacoes[i]), mostraIntSeExistir(colunas, 3, novosUCI[i]), mostraIntSeExistir(colunas, 4, novosMortes[i]));
            }
        }
    }

    public static int[] dadosDiariosNovos (int[] dados,String[] leituraDeDatas, String[] datas) {
        int indexData1 = indexData(stringParaDateEConverterDatas(leituraDeDatas[0]),datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(leituraDeDatas[1]),datas);
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

    public static void mostrarDadosTotaisDiarios (String[] leituraDeDatas,String[] datas, int[][] dados, int[] colunas) {
        int indexData1 = indexData(stringParaDateEConverterDatas((leituraDeDatas[0])),datas);
        int indexData2 = indexData(stringParaDateEConverterDatas((leituraDeDatas[1])),datas);

        String cabecalho = "Data %5s";
        String impressao = "%s";

        for (int i = 1; i <= 4; i++) {
            if (existeNoArray(colunas, i)) {
                switch (i) {
                    case 1:
                        cabecalho += " | Total Infetados";
                        impressao += " | %15s";
                        break;
                    case 2:
                        cabecalho += " | Total Hospitalizações";
                        impressao += " | %21.10s";
                        break;
                    case 3:
                        cabecalho += " | Total UCI";
                        impressao += " | %9.10s";
                        break;
                    case 4:
                        cabecalho += " | Total Mortes";
                        impressao += " | %12.10s";
                        break;
                }
            } else {
                impressao += "%s";
            }
        }

        cabecalho += "\n";
        impressao += "\n";

        System.out.printf(cabecalho, "" );
        for (int i = 0; i <= indexData2-indexData1; i++) {
            int[] dadosInfetados = dados[1];
            int[] dadosHospitalizados = dados[2];
            int[] dadosUCI = dados[3];
            int[] dadosMortes = dados[4];
            System.out.printf(impressao, datas[i+indexData1], mostraIntSeExistir(colunas, 1, dadosInfetados[i]),mostraIntSeExistir(colunas, 2, dadosHospitalizados[i]),mostraIntSeExistir(colunas, 3, dadosUCI[i]),mostraIntSeExistir(colunas, 4, dadosMortes[i]));
        }
    }

    //--------------------------------------------Analise Semanal Novos Casos-----------------------------------------------------//

    public static void mostrarDadosSemanais(String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas)  {
        // mostrar dados das semanas entre as datas pretendidas, ou seja, por exemplo: dados da 1 semn, dados da 2 semn, dados da 3 semn
        // 2020-04-01 2020-04-02 -> Não é uma semana completa
        // 2020-04-1 2020-04-17 -> mostra 2 semanas 1-15
        // 1 semana, subtrair acumulado do ult dia semana com 1 dia semn
        int indexData1 = indexData(verificarSemanaSegunda(stringParaDateEConverterDatas(leituraDeDatas[0])),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDateEConverterDatas(leituraDeDatas[1])),datas);
        int numeroSemanas = calcularNumSemanas(stringParaDateEConverterDatas(leituraDeDatas[0]), stringParaDateEConverterDatas(leituraDeDatas[1]));

        if (numeroSemanas == 0) {
            System.out.println("Introduza datas que contenham pelo menos 1 semana");
        } else {
            String cabecalho = "Semanas                ";
            String impressao = "%s - %s";

            for (int i = 1; i <= 4; i++) {
                if (existeNoArray(colunas, i)) {
                    switch (i) {
                        case 1:
                            cabecalho += " | Novos Infetados";
                            impressao += " | %15s";
                            break;
                        case 2:
                            cabecalho += " | Novas Hospitalizações";
                            impressao += " | %21.10s";
                            break;
                        case 3:
                            cabecalho += " | Novos UCI";
                            impressao += " | %9.10s";
                            break;
                        case 4:
                            cabecalho += " | Novas Mortes";
                            impressao += " | %12.10s";
                            break;
                    }
                } else {
                    impressao += "%s";
                }
            }

            cabecalho += "\n";
            impressao += "\n";

            System.out.printf(cabecalho , "" );
            for (int i = 0; i < numeroSemanas; i++) {
                int[] novosInfetados = dadosSemanaisNovos(dados[1],numeroSemanas,indexData2,indexData1);
                int[] novosHospitalizacoes = dadosSemanaisNovos(dados[2],numeroSemanas,indexData2,indexData1);
                int[] novosUCI = dadosSemanaisNovos(dados[3], numeroSemanas,indexData2,indexData1);
                int[] novosMortes = dadosSemanaisNovos(dados[4],numeroSemanas,indexData2,indexData1);
                System.out.printf(impressao, datas[indexData1+(7*i)],datas[(indexData1+(7*i))+6], mostraIntSeExistir(colunas, 1, novosInfetados[i]),mostraIntSeExistir(colunas, 2, novosHospitalizacoes[i]),mostraIntSeExistir(colunas, 3, novosUCI[i]),mostraIntSeExistir(colunas, 4, novosMortes[i]));
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

    public static void mostrarDadosTotaisSemanais (String[] leituraDeDatas,String[] datas, int[][] dados, int[] colunas){
        int indexData1 = indexData(verificarSemanaSegunda(stringParaDateEConverterDatas((leituraDeDatas[0]))),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDateEConverterDatas((leituraDeDatas[1]))),datas);
        int numeroSemanas = calcularNumSemanas(stringParaDateEConverterDatas((leituraDeDatas[0])), stringParaDateEConverterDatas((leituraDeDatas[1])));

        if (numeroSemanas == 0) {
            System.out.println("Introduza datas que contenham pelo menos 1 semana");
        } else {
            String cabecalho = "Semanas                ";
            String impressao = "%s - %s";

            for (int i = 1; i <= 4; i++) {
                if (existeNoArray(colunas, i)) {
                    switch (i) {
                        case 1:
                            cabecalho += " | Total Infetados";
                            impressao += " | %15s";
                            break;
                        case 2:
                            cabecalho += " | Total Hospitalizações";
                            impressao += " | %21.10s";
                            break;
                        case 3:
                            cabecalho += " | Total UCI";
                            impressao += " | %9.10s";
                            break;
                        case 4:
                            cabecalho += " | Total Mortes";
                            impressao += " | %12.10s";
                            break;
                    }
                } else {
                    impressao += "%s";
                }
            }

            cabecalho += "\n";
            impressao += "\n";

            System.out.printf(cabecalho , "" );
            for (int i = 0; i < numeroSemanas; i++) {
                int[] totaisInfetados = dadosTotaisSemanaisNovos(dados[1],numeroSemanas,indexData2,indexData1);
                int[] totaisHospitalizacoes = dadosTotaisSemanaisNovos(dados[2],numeroSemanas,indexData2,indexData1);
                int[] totaisUCI = dadosTotaisSemanaisNovos(dados[3], numeroSemanas,indexData2,indexData1);
                int[] totaisMortes = dadosTotaisSemanaisNovos(dados[4],numeroSemanas,indexData2,indexData1);
                System.out.printf(impressao, datas[indexData1+(7*i)],datas[(indexData1+(7*i))+6], mostraIntSeExistir(colunas, 1, totaisInfetados[i]),mostraIntSeExistir(colunas, 2, totaisHospitalizacoes[i]),mostraIntSeExistir(colunas, 3, totaisUCI[i]),mostraIntSeExistir(colunas, 4, totaisMortes[i]));
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
        int indexData1 = indexData(primeiroDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[0])),datas);
        int indexData2 = indexData(ultimoDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[1])),datas);
        int numeroMeses = numeroMeses(leituraDeDatas);

        if (numeroMeses == 0) {
            System.out.println("Introduza datas que contenham pelo menos 1 mês");
        } else {
            System.out.printf("%16s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n" , "" );
            for (int i = 0; i < numeroMeses; i++) {
                int[] novosInfetados = dadosMensaisNovos(leituraDeDatas[0],dados[1],numeroMeses,indexData2,indexData1);
                int[] novosHospitalizacoes = dadosMensaisNovos(leituraDeDatas[0],dados[2],numeroMeses,indexData2,indexData1);
                int[] novosUCI = dadosMensaisNovos(leituraDeDatas[0],dados[3], numeroMeses,indexData2,indexData1);
                int[] novosMortes = dadosMensaisNovos(leituraDeDatas[0],dados[4],numeroMeses,indexData2,indexData1);
                System.out.printf("Mês " + (1+i) + ": %25s | %21.10s | %9.10s | %12.10s \n", novosInfetados[i],novosHospitalizacoes[i],novosUCI[i],novosMortes[i]);
            }
        }
    }

    public static int[] dadosMensaisNovos (String leituraDatas, int[] dados,int numeroMeses,int index2,int index1) {
        int[] dadosNovos =  new int[numeroMeses];
        Date data = stringParaDateEConverterDatas(leituraDatas);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.MILLISECOND,0);

        int diasNoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        if (numeroMeses==1){
            dadosNovos[0]=dados[index2]-dados[index1];
        } else {
            for (int i = 0; i < numeroMeses; i++) {
                calendar.add(Calendar.MONTH,1);
                if(index1<=index2) {
                    dadosNovos[i] = dados[index1 + diasNoMes] - dados[index1];
                    diasNoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    index1 = (index1) + diasNoMes;
                }
            }
        }
        return dadosNovos;
    }

    public static int numeroMeses(String[] leituraDeDatas)  {

        Calendar mesesInicial = Calendar.getInstance();
        Calendar mesFinal = Calendar.getInstance();
        Date inicio = primeiroDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[0]));
        Date fim = ultimoDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[1]));
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

        while (dataFinal.get(Calendar.DAY_OF_MONTH) != 31 && dataFinal.get(Calendar.DAY_OF_MONTH) != 30 && dataFinal.get(Calendar.DAY_OF_MONTH) != 28 && dataFinal.get(Calendar.DAY_OF_MONTH)!=29) {
            dataFinal.add(Calendar.DAY_OF_MONTH,-1);
        }

        return dataFinal.getTime();
    }

    public static void mostrarDadosTotaisMensais(String[] leituraDeDatas,String[] datas,int[][] dados) {
        int indexData1 = indexData(primeiroDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[0])),datas);
        int indexData2 = indexData(ultimoDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[1])),datas);
        int numeroMeses = numeroMeses(leituraDeDatas);

        if (numeroMeses == 0) {
            System.out.println("Introduza datas que contenham pelo menos 1 mês");
        } else {
            System.out.printf("%16s Total Infetados | Total Hospitalizações | Total UCI | Total Mortes\n" , "" );
            for (int i = 0; i < numeroMeses; i++) {
                int[] novosInfetados = dadosMensaisTotaisNovos(leituraDeDatas[0],dados[1],numeroMeses,indexData2,indexData1);
                int[] novosHospitalizacoes = dadosMensaisTotaisNovos(leituraDeDatas[0],dados[2],numeroMeses,indexData2,indexData1);
                int[] novosUCI = dadosMensaisTotaisNovos(leituraDeDatas[0],dados[3], numeroMeses,indexData2,indexData1);
                int[] novosMortes = dadosMensaisTotaisNovos(leituraDeDatas[0],dados[4],numeroMeses,indexData2,indexData1);
                System.out.printf("Mês " + (1+i) + ": %25s | %21.10s | %9.10s | %12.10s \n", novosInfetados[i],novosHospitalizacoes[i],novosUCI[i],novosMortes[i]);
            }
        }
    }

    public static int[] dadosMensaisTotaisNovos (String leituraDatas,int[] dados, int numeroMeses, int index2, int index1) {
        int[] dadosNovos =  new int[numeroMeses];
        Date data = stringParaDateEConverterDatas(leituraDatas);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.MILLISECOND,0);

        int diasNoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int auxIndex=index1;

        if (numeroMeses==1){
            for (int i = 0; i < diasNoMes; i++) {
                if (index1<=index2) {
                    index1 = index1+i;
                    dadosNovos[0]=dadosNovos[0]+dados[index1];

                }
            }
        } else {
            for (int i = 0; i < numeroMeses; i++) {
                index1=auxIndex+diasNoMes;
                for (int j = 0; j < diasNoMes; j++) {
                    if (index1 <= index2) {
                        dadosNovos[i] = dadosNovos[i]+dados[index1];
                        index1 = index1 + j;
                    }
                }
                data = primeiroDiaMesValido(stringParaDateEConverterDatas(leituraDatas));
                calendar.setTime(data);
                diasNoMes=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
        }
        return dadosNovos;
    }

    //-------------------------------------Analise Comparativa Novos Casos--------------------------------------------//
    public static void analiseComparativaNovosCasos (String[] intervalo1, String[] intervalo2, String[] datas,int[][] dados, int[] colunas) {
        long diasIntervalo1;
        long diasIntervalo2;

        // calcular intervalo de dias do intervalo1
        diasIntervalo1=calcularDiasEntreIntervalo(intervalo1);
        diasIntervalo2 = calcularDiasEntreIntervalo(intervalo2);

        int numeroDiasAComparar = (int) diasIntervalo1;
        if(diasIntervalo1 < diasIntervalo2) {
            numeroDiasAComparar = (int) diasIntervalo1;
        } else if (diasIntervalo2<diasIntervalo1) {
            numeroDiasAComparar = (int) diasIntervalo2;
        }
        int indexData1 = indexData(stringParaDateEConverterDatas(intervalo1[0]),datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(intervalo2[0]),datas);

        String cabecalho = "Datas                       ";
        String impressao = "";
        String impressaoMediaDesvio = "";

        for (int i = 1; i <= 4; i++) {
            if (existeNoArray(colunas, i)) {
                switch (i) {
                    case 1:
                        cabecalho += " | Novos Infetados";
                        impressao += " | %21s";
                        impressaoMediaDesvio += " | %21.4f";
                        break;
                    case 2:
                        cabecalho += " | Novas Hospitalizações";
                        impressao += " | %21.10s";
                        impressaoMediaDesvio += " | %21.4f";
                        break;
                    case 3:
                        cabecalho += " | Novos UCI";
                        impressao += " | %9.10s";
                        impressaoMediaDesvio += " | %9.4f";
                        break;
                    case 4:
                        cabecalho += " | Novas Mortes";
                        impressao += " | %12.10s";
                        impressaoMediaDesvio += " | %12.4f";
                        break;
                }
            } else {
                impressao += "%s";
                impressaoMediaDesvio += "%s";
            }
        }

        cabecalho += "\n";
        impressao += "\n";

        System.out.printf("\nDatas                        | %5s Novos Infetados | Novas Hospitalizações | Novos UCI | Novas Mortes\n" , "" );
        int[][] comparacaoInfet = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[1], numeroDiasAComparar);
        int[][] comparacaoHosp = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[2], numeroDiasAComparar);
        int[][] comparacaoUCI = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[3], numeroDiasAComparar);
        int[][] comparacaoObi = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[4], numeroDiasAComparar);
        for (int j = 0; j < numeroDiasAComparar; j++) {
            if ((indexData1+j) - 1 < 0) {
                System.out.printf("%s 1ªIntervalo      " + impressao, datas[j + indexData1],mostraStrSeExistir(colunas, 1, "Sem dados"),mostraStrSeExistir(colunas, 2, "Sem dados"),mostraStrSeExistir(colunas, 3, "Sem dados"),mostraStrSeExistir(colunas, 4, "Sem dados"));
            } else {
                System.out.printf("%s 1ªIntervalo      " + impressao, datas[j + indexData1], mostraIntSeExistir(colunas, 1, comparacaoInfet[0][j]), mostraIntSeExistir(colunas, 2, comparacaoHosp[0][j]), mostraIntSeExistir(colunas, 3, comparacaoUCI[0][j]), mostraIntSeExistir(colunas, 4, comparacaoObi[0][j]));
            }
            if ((indexData2+j)-1<0) {
                System.out.printf("%s 2ªIntervalo      " + impressao, datas[j + indexData2],mostraStrSeExistir(colunas, 1, "Sem dados"),mostraStrSeExistir(colunas, 2, "Sem dados"),mostraStrSeExistir(colunas, 3, "Sem dados"),mostraStrSeExistir(colunas, 4, "Sem dados"));
            } else {
                System.out.printf("%s 2ªIntervalo      " + impressao, datas[j + indexData2],mostraIntSeExistir(colunas, 1, comparacaoInfet[1][j]), mostraIntSeExistir(colunas, 2, comparacaoHosp[1][j]), mostraIntSeExistir(colunas, 3, comparacaoUCI[1][j]), mostraIntSeExistir(colunas, 4, comparacaoObi[1][j]));
            }
        }
        // media e desvio Padrão dos novos casos
        double mediaInfetado1 = mediaComparativa(comparacaoInfet[0]);
        double mediaInfetado2 = mediaComparativa(comparacaoInfet[1]);
        double mediaHosp1 = mediaComparativa(comparacaoHosp[0]);
        double mediaHosp2 = mediaComparativa(comparacaoHosp[1]);
        double mediaUCI1 = mediaComparativa(comparacaoUCI[0]);
        double mediaUCI2 = mediaComparativa(comparacaoUCI[1]);
        double mediaObitos1 = mediaComparativa(comparacaoObi[0]);
        double mediaObitos2 = mediaComparativa(comparacaoObi[1]);

        System.out.printf("Media do 1ªIntervalo        " + impressaoMediaDesvio, mostraDoubleSeExistir(colunas, 1, mediaInfetado1),mostraDoubleSeExistir(colunas, 2, mediaHosp1),mostraDoubleSeExistir(colunas, 3, mediaUCI1),mostraDoubleSeExistir(colunas, 4, mediaObitos1));
        System.out.printf("Media do 2ªIntervalo        " + impressaoMediaDesvio, mostraDoubleSeExistir(colunas, 1, mediaInfetado2),mostraDoubleSeExistir(colunas, 2, mediaHosp2),mostraDoubleSeExistir(colunas, 3, mediaUCI2),mostraDoubleSeExistir(colunas, 4, mediaObitos2));
        System.out.printf("Desvio Padrão do 1ªIntervalo" + impressaoMediaDesvio, desvioPadrao(comparacaoInfet[0],mediaInfetado1),desvioPadrao(comparacaoHosp[0],mediaHosp1),desvioPadrao(comparacaoUCI[0],mediaUCI1),desvioPadrao(comparacaoObi[0],mediaObitos1));
        System.out.printf("Desvio Padrão do 2ªIntervalo" + impressaoMediaDesvio, desvioPadrao(comparacaoInfet[1],mediaInfetado2),desvioPadrao(comparacaoHosp[1],mediaHosp2),desvioPadrao(comparacaoUCI[1],mediaUCI2),desvioPadrao(comparacaoObi[1],mediaObitos2));

    }

    public static int[][] comparacaoDadosDiariosNovos (int indexData1,int indexData2, int[] dados, int numeroDias) {
        int indice1 = 0;
        int indice2 = 0;
        int[][] dadosNovos =  new int[NUMERO_DADOS_COMPARACAO][numeroDias];

        for (int i = indexData1; i < indexData1+numeroDias ; i++) {
            for (int j = 0; j < numeroDias; j++) {
                if (i-1>=0){
                    dadosNovos[0][indice1] = dados[i] - dados[i - 1];
                }
            }
            indice1++;
        }
        for (int i = indexData2; i < indexData2+numeroDias; i++) {
            for (int j = 0; j < numeroDias; j++) {
                if (i-1>=0){
                    dadosNovos[1][indice2] = dados[i] - dados[i - 1];
                }
            }
            indice2++;
        }
        return dadosNovos;
    }

    public static double mediaComparativa (int[] dados) {
        double media=0;

        for (int i = 0; i < dados.length; i++) {
            media = media + dados[i];
        }
        return (media / dados.length);
    }

    public static double desvioPadrao (int[] dados,double media) {
        double desvio=0;

        for (int i = 0; i < dados.length; i++) {
            desvio = desvio + Math.pow(dados[i]-media,2);
        }
        return Math.sqrt(desvio/dados.length);
    }

    //-------------------------------------Analise Comparativa Totais Casos-------------------------------------------//

    public static void analiseComparativaTotaisCasos (String[] datas,int[][] dados) {
        long diasIntervalo1;
        long diasIntervalo2;
        String[] intervalo1;
        String[] intervalo2;
        do {
            System.out.println("1º intervalo:");
            intervalo1 = leituraIntervaloDatas();
            // calcular intervalo de dias do intervalo1
            diasIntervalo1=calcularDiasEntreIntervalo(intervalo1);
            System.out.println("\n2º intervalo:");
            // calcular intervalo de dias do intervalo2
            intervalo2 = leituraIntervaloDatas();
            diasIntervalo2 = calcularDiasEntreIntervalo(intervalo2);
        } while (diasIntervalo1 <0 && diasIntervalo2<0);

        int numeroDiasAComparar = (int) diasIntervalo1;
        if(diasIntervalo1 < diasIntervalo2) {
            numeroDiasAComparar = (int) diasIntervalo1;
        } else if (diasIntervalo2<diasIntervalo1) {
            numeroDiasAComparar = (int) diasIntervalo2;
        }
        int indexData1 = indexData(stringParaDateEConverterDatas(intervalo1[0]),datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(intervalo2[0]),datas);

        System.out.printf("\nDados                        | %5s Total Infetados | Total Hospitalizações | Total UCI | Total Mortes\n" , "" );
        int[][] comparacaoInfet = comparacaoTotaisCasos(indexData1, indexData2, dados[1], numeroDiasAComparar);
        int[][] comparacaoHosp = comparacaoTotaisCasos(indexData1, indexData2, dados[2], numeroDiasAComparar);
        int[][] comparacaoUCI = comparacaoTotaisCasos(indexData1, indexData2, dados[3], numeroDiasAComparar);
        int[][] comparacaoObi = comparacaoTotaisCasos(indexData1, indexData2, dados[4], numeroDiasAComparar);
        for (int j = 0; j < numeroDiasAComparar; j++) {
            if ((indexData1+j) - 1 < 0) {
                System.out.printf("%s 1ªIntervalo       | %21s | %21.10s | %9.10s | %12.10s \n", datas[j + indexData1], "Sem dados", "Sem dados", "Sem dados", "Sem dados");
                System.out.printf("%s 2ªIntervalo       | %21s | %21.10s | %9.10s | %12.10s \n", datas[j + indexData2], comparacaoInfet[1][j], comparacaoHosp[1][j], comparacaoUCI[1][j], comparacaoObi[1][j]);
                System.out.println("----------------------------------------------------------------------------------------------------" );
            } else if((indexData2+j)-1<0) {
                System.out.printf("%s 1ªIntervalo       | %21s | %21.10s | %9.10s | %12.10s \n", datas[j + indexData1], comparacaoInfet[0][j], comparacaoHosp[0][j], comparacaoUCI[0][j], comparacaoObi[0][j]);
                System.out.printf("%s 2ªIntervalo       | %21s | %21.10s | %9.10s | %12.10s \n", datas[j + indexData2], "Sem dados", "Sem dados", "Sem dados", "Sem dados");
                System.out.println("----------------------------------------------------------------------------------------------------" );
            }else {
                System.out.printf("%s 1ªIntervalo       | %21s | %21.10s | %9.10s | %12.10s \n", datas[j + indexData1], comparacaoInfet[0][j], comparacaoHosp[0][j], comparacaoUCI[0][j], comparacaoObi[0][j]);
                System.out.printf("%s 2ªIntervalo       | %21s | %21.10s | %9.10s | %12.10s \n", datas[j + indexData2], comparacaoInfet[1][j], comparacaoHosp[1][j], comparacaoUCI[1][j], comparacaoObi[1][j]);
                System.out.println("----------------------------------------------------------------------------------------------------" );
            }
        }
        // media e desvio Padrao dos totais casos
        double mediaInfetado1 = mediaComparativa(comparacaoInfet[0]);
        double mediaInfetado2 = mediaComparativa(comparacaoInfet[1]);
        double mediaHosp1 = mediaComparativa(comparacaoHosp[0]);
        double mediaHosp2 = mediaComparativa(comparacaoHosp[1]);
        double mediaUCI1 = mediaComparativa(comparacaoUCI[0]);
        double mediaUCI2 = mediaComparativa(comparacaoUCI[1]);
        double mediaObitos1 = mediaComparativa(comparacaoObi[0]);
        double mediaObitos2 = mediaComparativa(comparacaoObi[1]);

        System.out.printf("Media do 1ªIntervalo         | %21.4f | %21.4f | %9.4f | %12.4f \n", mediaInfetado1,mediaHosp1,mediaUCI1,mediaObitos1);
        System.out.printf("Media do 2ªIntervalo         | %21.4f | %21.4f | %9.4f | %12.4f \n",  mediaInfetado2,mediaHosp2,mediaUCI2,mediaObitos2);
        System.out.printf("Desvio Padrão do 1ªIntervalo | %21.4f | %21.4f | %9.4f | %12.4f \n", desvioPadrao(comparacaoInfet[0],mediaInfetado1),desvioPadrao(comparacaoHosp[0],mediaHosp1),desvioPadrao(comparacaoUCI[0],mediaUCI1),desvioPadrao(comparacaoObi[0],mediaObitos1));
        System.out.printf("Desvio Padrão do 2ªIntervalo | %21.4f | %21.4f | %9.4f | %12.4f \n", desvioPadrao(comparacaoInfet[1],mediaInfetado2),desvioPadrao(comparacaoHosp[1],mediaHosp2),desvioPadrao(comparacaoUCI[1],mediaUCI2),desvioPadrao(comparacaoObi[1],mediaObitos2));

    }

    public static int[][] comparacaoTotaisCasos (int indexData1, int indexData2,int[] dados,int numeroDias) {
        int indice1=0;
        int indice2=0;
        int[][] dadosNovos = new int[NUMERO_DADOS_COMPARACAO][numeroDias];
        for (int i = indexData1; i < indexData1+numeroDias ; i++) {
            for (int j = 0; j < numeroDias; j++) {
                if (i-1>=0){
                    dadosNovos[0][indice1] = dados[i+1] + dados[i];
                }
            }
            indice1++;
        }
        for (int i = indexData2; i < indexData2+numeroDias; i++) {
            for (int j = 0; j < numeroDias; j++) {
                if (i-1>=0){
                    dadosNovos[1][indice2] = dados[i+1] + dados[i];
                }
            }
            indice2++;
        }
        return dadosNovos;
    }


    public static boolean verificarData1(String data) {
        return data.matches("\\d{2}-\\d{2}-\\d{4}");
    }

    public static boolean verificarData2(String data) {
        return data.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public static long calcularDiasEntreIntervalo(String[] intervalo)  {

        Date inicio = stringParaDateEConverterDatas(intervalo[0]);
        Date fim = stringParaDateEConverterDatas(intervalo[1]);

        int diffdias = 0;
        long diff = fim.getTime() - inicio.getTime();
        long diffDias = diff / (24 * 60 * 60 * 1000) + 1;
        diffdias = (int) diffDias;

        System.out.println(diffdias);
        return diffdias;
    }

    // (matriz identidade - matriz sem obito)inversa disto

    public static boolean verificarDiaExiste(String[] datas,String data) {
        boolean existe = false;
        String[] date = data.split("-");
        String ano = date[0];
        String mes = date[1];
        String dia = date[2];

        if (date[2].length() == 4) {
            ano=date[2];
            mes=date[1];
            dia=date[0];
        }
        data = dia + "-" + mes + "-" + ano;

        for (int i = 0; i < datas.length; i++) {
            if (data.equals(datas[i])) {
                existe = true;
            }
        }
        return  existe;
    }

    public static String escolherDiaAnterior(int index,String[] datas) {
        return datas[index-1];
    }
    public static String escolherDiaMaisProximo (int index,String[] datas) {
        String diaMaisProximo="";
        if (index > datas.length) {
            diaMaisProximo = datas[datas.length-1];
        } else {
            diaMaisProximo = datas[0];
        }
        return diaMaisProximo;
    }

    public static void mostrarPrevisao (String data,String[] datas, int[][] dados,String ficheiro) throws FileNotFoundException {
        int[][] matriz = new int[NUMERO_ESTADOS_DIFERENTES][NUMERO_ESTADOS_DIFERENTES] ;
        //matriz = lerMatiz(ficheiro,NUMERO_ESTADOS_DIFERENTES);
        System.out.println("previsao");
    }
}
