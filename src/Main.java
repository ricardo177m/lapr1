/**
 * Projeto LAPR1 - Aplicação para a empresa MSP
 * 2021-2022 LEI, Turma 1DI
 *
 * Realizado por:
 * André Barros - 1211299
 * Tomás Russo - 1211288
 * João Caseiro - 1211334
 * Ricardo Moreira - 1211285
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;

public class Main {
    static final Scanner kbScanner = new Scanner(System.in);
    static final int NUMERO_DADOS_COMPARACAO = 2;
    static final int NUMERO_FICHEIRO_DIFERENTE = 3;
    static final int NUMERO_DADOS_DIFERENTES = 5;
    static final int NUMERO_ESTADOS_DIFERENTES = 5;

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

        // tests
        // boolean test = true;
        Tests.runTestes();
        
        // if(test) return;
        // end of tests

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
                acumuladoDatas = lerDatas(caminhoFicheiro, numLinhas);
                acumuladoDados = lerDados(caminhoFicheiro, numLinhas);
                totalDatas = lerDatas(caminhoFicheiro, numLinhas);
                totalDados = lerDados(caminhoFicheiro, numLinhas);
                jaleuFicheiros[0] = true;
                System.out.println("Ficheiro lido com sucesso!");
                pressioneEnterParaCont();
                opcaoTipoFicheiro = menu();
                executaOpcao(opcaoTipoFicheiro, jaleuFicheiros, acumuladoDatas, acumuladoDados, totalDatas, totalDados);
                break;
            case "2":
                numLinhas = tamanhoLinhasFicheiro(caminhoFicheiro);
                totalDatas = lerDatas(caminhoFicheiro, numLinhas);
                totalDados = lerDados(caminhoFicheiro, numLinhas);
                acumuladoDatas = lerDatas(caminhoFicheiro, numLinhas);
                acumuladoDados = lerDados(caminhoFicheiro, numLinhas);
                jaleuFicheiros[1] = true;
                System.out.println("Ficheiro lido com sucesso!");
                pressioneEnterParaCont();
                opcaoTipoFicheiro = menu();
                executaOpcao(opcaoTipoFicheiro, jaleuFicheiros, acumuladoDatas, acumuladoDados, totalDatas, totalDados);
                break;
        }
    }

//-------------------------------------------Funcionamento Aplicação----------------------------------------------//

    public static String[] lerDatas(String caminhoFicheiro, int numeroLinhas) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoFicheiro));
        String[] datas = new String[numeroLinhas];
        String linha = scanner.nextLine();
        int indice = 0;
        while (scanner.hasNextLine()) {
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

    public static double[][] lerDadosMatriz(String caminhoFicheiro, int numLinhas) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoFicheiro));
        double[][] dados = new double[NUMERO_ESTADOS_DIFERENTES][NUMERO_ESTADOS_DIFERENTES];
        String[] dadosFicheiro;
        String linha = scanner.nextLine();
        int indice = 0;
        while (scanner.hasNextLine()) {
            dadosFicheiro = linha.split("=");
            if (!linha.equals("")) {

                dados[indice][0] = Double.parseDouble(dadosFicheiro[1]);
                linha = scanner.nextLine();
                dadosFicheiro = linha.split("=");

                dados[indice][1] = Double.parseDouble(dadosFicheiro[1]);
                linha = scanner.nextLine();
                dadosFicheiro = linha.split("=");

                dados[indice][2] = Double.parseDouble(dadosFicheiro[1]);
                linha = scanner.nextLine();
                dadosFicheiro = linha.split("=");

                dados[indice][3] = Double.parseDouble(dadosFicheiro[1]);
                linha = scanner.nextLine();
                dadosFicheiro = linha.split("=");

                dados[indice][4] = Double.parseDouble(dadosFicheiro[1]);
                if (scanner.hasNextLine())
                    linha = scanner.nextLine();

                indice++;
            } else {
                linha = scanner.nextLine();
            }
        }
        return dados;
    }

    public static void executaOpcao(String opcao, boolean[] jaLeuFicheiros, String[] datasAcumulados, int[][] acumuladoDados, String[] datasTotais, int[][] dadosTotais) throws FileNotFoundException {
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
                    opcao = menu();
                    break;
                case "2":
                    // Ver dados diarios
                    opcaoTipoFicheiro = selecionarTipoVisualizacao();
                    verDadosDiarios(opcaoTipoFicheiro, jaLeuFicheiros, datasAcumulados, acumuladoDados, datasTotais, dadosTotais);
                    pressioneEnterParaCont();
                    opcao = menu();
                    break;
                case "3":
                    // Ver dados Semanais
                    opcaoTipoFicheiro = selecionarTipoVisualizacao();
                    verDadosSemanais(opcaoTipoFicheiro, jaLeuFicheiros, datasAcumulados, acumuladoDados, datasTotais, dadosTotais);
                    pressioneEnterParaCont();
                    opcao = menu();
                    break;
                case "4":
                    // Ver dados mensais
                    opcaoTipoFicheiro = selecionarTipoVisualizacao();
                    verDadosMensais(opcaoTipoFicheiro, jaLeuFicheiros, datasAcumulados, acumuladoDados, datasTotais, dadosTotais);
                    pressioneEnterParaCont();
                    opcao = menu();
                    break;
                case "5":
                    // Ver analise comparativa
                    opcaoTipoFicheiro = selecionarTipoVisualizacao();
                    verDadosComparativos(opcaoTipoFicheiro, jaLeuFicheiros, datasAcumulados, acumuladoDados, datasTotais, dadosTotais);
                    pressioneEnterParaCont();
                    opcao = menu();
                    break;
                case "6":
                    // Ver previsoes
                    verPrevisoes(jaLeuFicheiros, datasTotais, dadosTotais);
                    pressioneEnterParaCont();
                    opcao = menu();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.\n");
                    pressioneEnterParaCont();
                    opcao = menu();
                    break;
            }
        } while (!opcao.equals("0"));
        System.out.println("\nOBRIGADO PELA PREFERENCIA!");
    }

    public static void verDadosDiarios(String opcao, boolean[] jaLeuFicheiros, String[] datasAcumulados, int[][] dadosAcumaldos,String[] datasTotais, int[][] dadosTotais) {
        switch (opcao) {
            case "1":
                if (jaLeuFicheiros[0]) {
                    String[] leituraDatas;
                    do {
                        leituraDatas = leituraIntervaloDatas();
                        if (existeNoArrayDatas(datasAcumulados, leituraDatas)) {
                            int[] colunas = menuEscolherQtdDados();
                            mostrarDadosDiarios(leituraDatas, datasAcumulados, dadosAcumaldos, colunas);
                            String diretorio = guardarOuSair();
                            if (!diretorio.equals("")) {
                                imprimirFicheiroAcumuladoDiarios(diretorio, leituraDatas, datasAcumulados, dadosAcumaldos, colunas);
                            }
                        } else {
                            System.out.println("ERRO: Data(s) não existe(m) no ficheiro. Por favor, insira data(s) válida(s).");
                        }
                    } while (!existeNoArrayDatas(datasAcumulados,leituraDatas));
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    String[] leituraDatas;
                    do {
                        leituraDatas = leituraIntervaloDatas();
                        if (existeNoArrayDatas(datasTotais, leituraDatas)) {
                            int[] colunas = menuEscolherQtdDados();
                            mostrarDadosTotaisDiarios(leituraDatas, datasTotais, dadosTotais, colunas);
                            String diretorio = guardarOuSair();
                            if (!diretorio.equals("")) {
                                imprimirFicheiroTotalDiarios(diretorio, leituraDatas, datasTotais, dadosTotais, colunas);
                            }
                        } else {
                            System.out.println("ERRO: Data(s) não existe(m) no ficheiro. Por favor, insira data(s) válida(s).");
                        }
                    } while (!existeNoArrayDatas(datasTotais,leituraDatas));
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
                    String[] leituraDatas;
                    int numeroSemanas=0;
                    do {
                        leituraDatas = leituraIntervaloDatas();
                        if (existeNoArrayDatas(datasAcumulado, leituraDatas)) {
                            int[] colunas;
                            numeroSemanas = calcularNumSemanas(stringParaDateEConverterDatas(leituraDatas[0]), stringParaDateEConverterDatas(leituraDatas[1]));
                            if (numeroSemanas != -1 && numeroSemanas!=0) {
                                colunas = menuEscolherQtdDados();
                                mostrarDadosSemanais(leituraDatas, datasAcumulado, dadosAcumulado, colunas,numeroSemanas);
                                String diretorio = guardarOuSair();
                                if (!diretorio.equals("")) {
                                    imprimirFicheiroAcumuladoSemanais(diretorio, leituraDatas, datasAcumulado, dadosAcumulado, colunas);
                                }
                            } else {
                                System.out.println("Introduza datas que contenham pelo menos 1 semana.");
                            }
                        } else {
                            System.out.println("ERRO: Data(s) não existe(m) no ficheiro. Por favor, insira data(s) válida(s).");
                        }
                    } while (!existeNoArrayDatas(datasAcumulado,leituraDatas) || numeroSemanas == -1 || numeroSemanas ==0);
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    String[] leituraDatas;
                    int numeroSemanas=0;
                    do {
                        leituraDatas=leituraIntervaloDatas();
                        if (existeNoArrayDatas(datasTotal, leituraDatas)) {
                            int[] colunas;
                            numeroSemanas = calcularNumSemanas(stringParaDateEConverterDatas(leituraDatas[0]),stringParaDateEConverterDatas(leituraDatas[1]));
                            if (numeroSemanas !=-1 && numeroSemanas !=0) {
                                colunas=menuEscolherQtdDados();
                                mostrarDadosTotaisSemanais(leituraDatas, datasTotal, dadosTotal, colunas,numeroSemanas);
                                String diretorio = guardarOuSair();
                                if (!diretorio.equals("")) {
                                    imprimirFicheiroTotalSemanais(diretorio, leituraDatas, datasTotal, dadosTotal, colunas,numeroSemanas);
                                }
                            } else {
                                System.out.println("Introduza datas que contenham pelo menos 1 semana.");
                            }
                        } else {
                            System.out.println("ERRO: Data(s) não existe(m) no ficheiro. Por favor, insira data(s) válida(s).");
                        }
                    } while (!existeNoArrayDatas(datasTotal,leituraDatas) || numeroSemanas ==-1 || numeroSemanas==0);
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            default:
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.");
                break;
        }
    }

    public static void verDadosMensais(String opcao, boolean[] jaLeuFicheiros, String[] datasAcumulado, int[][] dadosAcumulado, String[] datasTotal, int[][] dadosTotal) {
        switch (opcao) {
            case "1":
                if (jaLeuFicheiros[0]) {
                    String[] leituraDatas;
                    int numeroMeses=0;
                    do {
                        leituraDatas = leituraIntervaloDatas();
                        if (existeNoArrayDatas(datasAcumulado, leituraDatas)) {
                            numeroMeses = numeroMeses(leituraDatas);
                            if (numeroMeses!=0) {
                                int[] colunas = menuEscolherQtdDados();
                                mostrarDadosMensais(leituraDatas, datasAcumulado, dadosAcumulado, colunas,numeroMeses);
                                String diretorio = guardarOuSair();
                                if (!diretorio.equals("")) {
                                    imprimirFicheiroNovosMensais(diretorio, leituraDatas, datasAcumulado, dadosAcumulado, colunas,numeroMeses);
                                }
                            } else {
                                System.out.println("Introduza datas que contenham pelo menos 1 mês.");
                            }
                        } else {
                            System.out.println("ERRO: Data(s) não existe(m) no ficheiro. Por favor, insira data(s) válida(s).");
                        }
                    } while (!existeNoArrayDatas(datasAcumulado,leituraDatas) || numeroMeses ==0);
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    String[] leituraDatas;
                    int numeroMeses=0;
                    do {
                        leituraDatas = leituraIntervaloDatas();
                        if (existeNoArrayDatas(datasTotal, leituraDatas)) {
                            numeroMeses=numeroMeses(leituraDatas);
                            if (numeroMeses!=0) {
                                int[] colunas = menuEscolherQtdDados();
                                mostrarDadosTotaisMensais(leituraDatas, datasTotal, dadosTotal, colunas,numeroMeses);
                                String diretorio = guardarOuSair();
                                if (!diretorio.equals("")) {
                                    imprimirFicheiroTotaisMensais(diretorio, leituraDatas, datasTotal, dadosTotal, colunas,numeroMeses);
                                }
                            } else {
                                System.out.println("Introduza datas que contenham pelo menos 1 mês.");
                            }
                        } else {
                            System.out.println("ERRO: Data(s) não existe(m) no ficheiro. Por favor, insira data(s) válida(s).");
                        }
                    } while (!existeNoArrayDatas(datasTotal,leituraDatas) || numeroMeses==0);
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            default:
                System.out.println("ERRO: Opção inválida. Por favor, selecione uma das opções apresentadas no menu.");
                break;
        }
    }

    public static void verDadosComparativos(String opcao, boolean[] jaLeuFicheiros, String[] datasAcumulado, int[][] dadosAcumulado, String[] datasTotal, int[][] dadosTotal) {
        switch (opcao) {
            case "1":
                if (jaLeuFicheiros[0]) {
                    System.out.println("1º intervalo:");
                    String[] intervalo1 = leituraIntervaloDatas();
                    System.out.println("\n2º intervalo:");
                    String[] intervalo2 = leituraIntervaloDatas();
                    int[] colunas = menuEscolherQtdDados();
                    analiseComparativaNovosCasos(intervalo1, intervalo2, datasAcumulado, dadosAcumulado, colunas);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroAcumuladosComparativos(diretorio, intervalo1, intervalo2, datasAcumulado, dadosAcumulado, colunas);
                    }
                } else {
                    System.out.println("ERRO: Ficheiro não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
                }
                break;
            case "2":
                if (jaLeuFicheiros[1]) {
                    System.out.println("1º intervalo:");
                    String[] intervalo1 = leituraIntervaloDatas();
                    System.out.println("\n2º intervalo:");
                    String[] intervalo2 = leituraIntervaloDatas();
                    int[] colunas = menuEscolherQtdDados();
                    analiseComparativaTotaisCasos(intervalo1, intervalo2, datasTotal, dadosTotal, colunas);
                    String diretorio = guardarOuSair();
                    if (!diretorio.equals("")) {
                        imprimirFicheiroTotaisComparativos(diretorio, intervalo1, intervalo2, datasTotal, dadosTotal, colunas);
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

    public static void verPrevisoes(boolean[] jaLeuFicheiro, String[] datas, int[][] dados) throws FileNotFoundException {
        // ler matriz do ficheiro
        // veririficar se o dia que ele introduziu pertence ao ficheiro
        // Se nao existir, escolher o dia mais proximo
        // Se existir, escolher o dia anterior
        // matriz elevada a diferença do dia escolhido pelo utilizador a multiplicar pelo vetor do dia escolhido
        int indexData1;
        if (jaLeuFicheiro[1]) {
            String matriz = selecionarMatriz();
            System.out.print("Introduza a data que pretende escolher para fazer a previsao no formato (AAAA-MM-DD) ou (DD-MM-AAAA): ");
            String dataEscolhe = kbScanner.nextLine();
            indexData1 = indexData(stringParaDateEConverterDatas(dataEscolhe), datas);
            String data;
            if (verificarData1(dataEscolhe) || verificarData2(dataEscolhe)) {
                if ((indexData1 - 1) == -1) {
                    data = datas[0];
                    mostrarPrevisao(data, datas, dados, matriz, dataEscolhe);
                }
                if (existeNoArrayData(datas,dataEscolhe)){
                    data = escolherDiaAnterior(indexData1, datas);
                    mostrarPrevisao(data, datas, dados, matriz, dataEscolhe);
                } else if (!verificarDiaExiste(datas,dataEscolhe)) {
                Date data1 = stringParaDateEConverterDatas(dataEscolhe);
                data = escolherDiaMaisProximo(data1, datas);
                mostrarPrevisao(data, datas, dados, matriz, dataEscolhe);
            }
        } else {
                System.out.println("Data invalida.");
            }
        } else {
            System.out.println("ERRO: Ficheiro de totais não carregado. Por favor, carregue o ficheiro selecionando a opção 1.");
        }
    }

    // todo teste
    public static void imprimirFicheiroAcumuladoDiarios(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas) {
        String nomeFicheiro = diretorio + "/dados_novos_diarios_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
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
                ficheiroEscrita.println(datas[i] + mostraSeExistir(colunas, 1, ",Sem dados") + mostraSeExistir(colunas, 2, ",Sem dados") + mostraSeExistir(colunas, 3, ",Sem dados") + mostraSeExistir(colunas, 4, ",Sem dados"));
            } else {
                ficheiroEscrita.println(datas[i + indexData1] + mostraSeExistir(colunas, 1, "," + novosInfetados[i]) + mostraSeExistir(colunas, 2, "," + novosHospitalizacoes[i]) + mostraSeExistir(colunas, 3, "," + novosUCI[i]) + mostraSeExistir(colunas, 4, "," + novosMortes[i]));
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
            ficheiroEscrita.println(datas[i+indexData1] + mostraSeExistir(colunas, 1, "," + dadosInfetados[i]) + mostraSeExistir(colunas, 2, "," + dadosHospitalizados[i]) + mostraSeExistir(colunas, 3, "," + dadosUCI[i]) + mostraSeExistir(colunas, 4, "," + dadosMortes[i]));
        }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroAcumuladoSemanais(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas) {
        String nomeFicheiro = diretorio + "/dados_novos_semanais_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
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
                ficheiroEscrita.println(datas[indexData1+(7*i)] + " - " + datas[(indexData1+(7*i))+6] + mostraSeExistir(colunas, 1, "," + novosInfetados[i]) + mostraSeExistir(colunas, 2, "," + novosHospitalizacoes[i]) + mostraSeExistir(colunas, 3, "," + novosUCI[i]) + mostraSeExistir(colunas, 4, "," + novosMortes[i]));
            }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroTotalSemanais(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas,int numeroSemanas) {
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
                ficheiroEscrita.println(datas[indexData1+(7*i)] + " - " + datas[(indexData1+(7*i))+6] + mostraSeExistir(colunas, 1, "," + totaisInfetados[i]) + mostraSeExistir(colunas, 2, "," + totaisHospitalizacoes[i]) + mostraSeExistir(colunas, 3, "," + totaisUCI[i]) + mostraSeExistir(colunas, 4, "," + totaisMortes[i]));
            }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroNovosMensais(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas,int numeroMeses) {
        String nomeFicheiro = diretorio + "/dados_novos_mensais_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        int indexData1 = indexData(primeiroDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[0])), datas);
        int indexData2 = indexData(ultimoDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[1])), datas);

        Date primeiroDiaValido = stringParaDateEConverterDatas(datas[indexData1]);

            String cabecalho = "Meses";

            for (int i = 1; i <= 4; i++) {
                if (existeNoArray(colunas, i)) {
                    switch (i) {
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
            }

            ficheiroEscrita.println(cabecalho);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(primeiroDiaValido);
            int numDias = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int currIndex=indexData1;
            for (int i = 0; i < numeroMeses; i++) {
                int[] novosInfetados = dadosMensaisNovos(primeiroDiaValido, dados[1], numeroMeses, indexData2, indexData1);
                int[] novosHospitalizacoes = dadosMensaisNovos(primeiroDiaValido, dados[2], numeroMeses, indexData2, indexData1);
                int[] novosUCI = dadosMensaisNovos(primeiroDiaValido, dados[3], numeroMeses, indexData2, indexData1);
                int[] novosMortes = dadosMensaisNovos(primeiroDiaValido, dados[4], numeroMeses, indexData2, indexData1);
                ficheiroEscrita.println(datas[currIndex] + "-" + datas[currIndex+numDias]  + mostraSeExistir(colunas, 1, "," + novosInfetados[i]) + mostraSeExistir(colunas, 2,"," + novosHospitalizacoes[i]) + mostraSeExistir(colunas, 3, "," + novosUCI[i]) + mostraSeExistir(colunas, 4, "," + novosMortes[i]));
                calendar.add(Calendar.MONTH,1);
                currIndex+=numDias;
            }


        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroTotaisMensais(String diretorio, String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas,int numeroMeses) {
        String nomeFicheiro = diretorio + "/dados_totais_mensais_" + leituraDeDatas[0] + "_a_" + leituraDeDatas[1] + ".csv";
        PrintWriter ficheiroEscrita;
        try {
            ficheiroEscrita = new PrintWriter(nomeFicheiro, "UTF-8");
        } catch (IOException e) {
            System.out.println("ERRO: Caminho especificado para criação de ficheiro inválido. Tente novamente.");
            return;
        }

        int indexData1 = indexData(primeiroDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[0])), datas);
        int indexData2 = indexData(ultimoDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[1])), datas);

        Date primeiroDiaValido = stringParaDateEConverterDatas(datas[indexData1]);

            String cabecalho = "Meses";

            for (int i = 1; i <= 4; i++) {
                if (existeNoArray(colunas, i)) {
                    switch (i) {
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
            }

            cabecalho += "\n";
            ficheiroEscrita.println(cabecalho);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(primeiroDiaValido);
            int numDias = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int currIndex=indexData1;
            for (int i = 0; i < numeroMeses; i++) {
                int[] novosInfetados = dadosMensaisNovos(primeiroDiaValido, dados[1], numeroMeses, indexData2, indexData1);
                int[] novosHospitalizacoes = dadosMensaisNovos(primeiroDiaValido, dados[2], numeroMeses, indexData2, indexData1);
                int[] novosUCI = dadosMensaisNovos(primeiroDiaValido, dados[3], numeroMeses, indexData2, indexData1);
                int[] novosMortes = dadosMensaisNovos(primeiroDiaValido, dados[4], numeroMeses, indexData2, indexData1);
                ficheiroEscrita.println(datas[currIndex] + "-" + datas[currIndex+numDias]  + mostraSeExistir(colunas, 1, "," + novosInfetados[i]) + mostraSeExistir(colunas, 2,"," + novosHospitalizacoes[i]) + mostraSeExistir(colunas, 3, "," + novosUCI[i]) + mostraSeExistir(colunas, 4, "," + novosMortes[i]));
                calendar.add(Calendar.MONTH,1);
                currIndex+=numDias;
            }

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroAcumuladosComparativos(String diretorio, String[] intervalo1, String[] intervalo2, String[] datas, int[][] dados, int[] colunas) {
        String nomeFicheiro = diretorio + "/dados_novos_comparativos_entre_" + intervalo1[0] + "_a_" + intervalo1[1] + "_e_" + intervalo2[0] + "_a_" + intervalo2[1] + ".csv";
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
        diasIntervalo1 = calcularDiasEntreIntervalo(intervalo1);
        // calcular intervalo de dias do intervalo2
        diasIntervalo2 = calcularDiasEntreIntervalo(intervalo2);

        int numeroDiasAComparar = (int) diasIntervalo1;
        if (diasIntervalo1 < diasIntervalo2) {
            numeroDiasAComparar = (int) diasIntervalo1;
        } else if (diasIntervalo2 < diasIntervalo1) {
            numeroDiasAComparar = (int) diasIntervalo2;
        }
        int indexData1 = indexData(stringParaDateEConverterDatas(intervalo1[0]), datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(intervalo2[0]), datas);

        String cabecalho = "Datas";

        for (int i = 1; i <= 4; i++) {
            if (existeNoArray(colunas, i)) {
                switch (i) {
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
        }

        ficheiroEscrita.println(cabecalho);
        int[][] comparacaoInfet = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[1], numeroDiasAComparar);
        int[][] comparacaoHosp = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[2], numeroDiasAComparar);
        int[][] comparacaoUCI = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[3], numeroDiasAComparar);
        int[][] comparacaoObi = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[4], numeroDiasAComparar);
        for (int j = 0; j < numeroDiasAComparar; j++) {

            if ((indexData1 + j) - 1 < 0) {
                ficheiroEscrita.println(datas[j + indexData1] + " 1ªIntervalo" + mostraSeExistir(colunas, 1, ",Sem dados") + mostraSeExistir(colunas, 2, ",Sem dados") + mostraSeExistir(colunas, 3, ",Sem dados") + mostraSeExistir(colunas, 4, ",Sem dados"));
            } else {
                ficheiroEscrita.println(datas[j + indexData1] + " 1ªIntervalo" + mostraSeExistir(colunas, 1, "," + comparacaoInfet[0][j]) + mostraSeExistir(colunas, 2, "," + comparacaoHosp[0][j]) + mostraSeExistir(colunas, 3, "," + comparacaoUCI[0][j]) + mostraSeExistir(colunas, 4, "," + comparacaoObi[0][j]));
            }
            if ((indexData2 + j) - 1 < 0) {
                ficheiroEscrita.println(datas[j + indexData2] + " 2ªIntervalo" + mostraSeExistir(colunas, 1, "," + "Sem dados") + mostraSeExistir(colunas, 2, "," + "Sem dados") + mostraSeExistir(colunas, 3, "," + "Sem dados") + mostraSeExistir(colunas, 4, "," + "Sem dados"));
            } else {
                ficheiroEscrita.println(datas[j + indexData2] + " 2ªIntervalo" + mostraSeExistir(colunas, 1, "," + comparacaoInfet[1][j]) + mostraSeExistir(colunas, 2, "," + comparacaoHosp[1][j]) + mostraSeExistir(colunas, 3, "," + comparacaoUCI[1][j]) + mostraSeExistir(colunas, 4, "," + comparacaoObi[1][j]));
            }

            ficheiroEscrita.println("Diferença entre dados" + mostraSeExistir(colunas, 1, "," + (comparacaoInfet[1][j]-comparacaoInfet[0][j])) + mostraSeExistir(colunas, 2, "," + (comparacaoHosp[1][j]-comparacaoHosp[0][j])) + mostraSeExistir(colunas, 3, "," + (comparacaoUCI[1][j]-comparacaoUCI[0][j])) + mostraSeExistir(colunas, 4, "," + (comparacaoObi[1][j]-comparacaoObi[0][j])));
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
        double desvioInfet1 = desvioPadrao(comparacaoInfet[0],mediaInfetado1);
        double desvioInfet2 = desvioPadrao(comparacaoInfet[1],mediaInfetado2);
        double desvioHosp1 = desvioPadrao(comparacaoHosp[0],mediaHosp1);
        double desvioHosp2 = desvioPadrao(comparacaoHosp[1],mediaHosp2);
        double desvioUCI1 = desvioPadrao(comparacaoUCI[0],mediaUCI1);
        double desvioUCI2 = desvioPadrao(comparacaoUCI[0],mediaUCI2);
        double desvioObitos1 = desvioPadrao(comparacaoObi[0],mediaObitos1);
        double desvioObitos2 = desvioPadrao(comparacaoObi[0],mediaObitos2);

        ficheiroEscrita.println("Media do 1ªIntervalo" + mostraSeExistir(colunas, 1, "," + mediaInfetado1) + mostraSeExistir(colunas, 2, "," + mediaHosp1) + mostraSeExistir(colunas, 3, "," + mediaUCI1) + mostraSeExistir(colunas, 4, "," + mediaObitos1));
        ficheiroEscrita.println("Media do 2ªIntervalo" + mostraSeExistir(colunas, 1, "," +  mediaInfetado2) + mostraSeExistir(colunas, 2, "," + mediaHosp2) + mostraSeExistir(colunas, 3, "," + mediaUCI2) + mostraSeExistir(colunas, 4, "," + mediaObitos2));
        ficheiroEscrita.println("Diferença entre dados" + mostraSeExistir(colunas, 1, "," + (mediaInfetado2-mediaInfetado1)) + mostraSeExistir(colunas, 2, "," + (mediaHosp2-mediaHosp1)) + mostraSeExistir(colunas, 3, "," + (mediaUCI2-mediaUCI1)) + mostraSeExistir(colunas, 4, "," + (mediaObitos2-mediaObitos1)));
        ficheiroEscrita.println("Desvio Padrão do 1ªIntervalo" + mostraSeExistir(colunas, 1, "," + desvioInfet1) + mostraSeExistir(colunas, 2, "," + "," + desvioHosp1) + mostraSeExistir(colunas, 3, "," + desvioUCI1) + mostraSeExistir(colunas, 4, "," + desvioObitos1));
        ficheiroEscrita.println("Desvio Padrão do 2ªIntervalo" + mostraSeExistir(colunas, 1, "," + desvioInfet2) + mostraSeExistir(colunas, 2, "," + desvioHosp2) + mostraSeExistir(colunas, 3, "," + desvioUCI2) + mostraSeExistir(colunas, 4,"," +  desvioObitos2));
        ficheiroEscrita.println("Diferença entre dados" + mostraSeExistir(colunas, 1, "," + (desvioInfet2-desvioInfet1)) + mostraSeExistir(colunas, 2, "," + (desvioHosp2-desvioHosp1)) + mostraSeExistir(colunas, 3, "," + (desvioUCI2-desvioUCI1)) + mostraSeExistir(colunas, 4, "," + (desvioObitos2-desvioObitos1)));

        System.out.println("Dados gravados no ficheiro com sucesso.");

        ficheiroEscrita.close();
    }

    public static void imprimirFicheiroTotaisComparativos(String diretorio, String[] intervalo1, String[] intervalo2, String[] datas, int[][] dados, int[] colunas) {
        String nomeFicheiro = diretorio + "/dados_totais_comparativos_entre_" + intervalo1[0] + "_a_" + intervalo1[1] + "_e_" + intervalo2[0] + "_a_" + intervalo2[1] + ".csv";
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
        diasIntervalo1 = calcularDiasEntreIntervalo(intervalo1);
        // calcular intervalo de dias do intervalo2
        diasIntervalo2 = calcularDiasEntreIntervalo(intervalo2);

        int numeroDiasAComparar = (int) diasIntervalo1;
        if (diasIntervalo1 < diasIntervalo2) {
            numeroDiasAComparar = (int) diasIntervalo1;
        } else if (diasIntervalo2 < diasIntervalo1) {
            numeroDiasAComparar = (int) diasIntervalo2;
        }
        int indexData1 = indexData(stringParaDateEConverterDatas(intervalo1[0]), datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(intervalo2[0]), datas);

        String cabecalho = "Datas";

        for (int i = 1; i <= 4; i++) {
            if (existeNoArray(colunas, i)) {
                switch (i) {
                    case 1:
                        cabecalho += ",Totais Infetados";
                        break;
                    case 2:
                        cabecalho += ",Totais Hospitalizações";
                        break;
                    case 3:
                        cabecalho += ",Totais UCI";
                        break;
                    case 4:
                        cabecalho += ",Totais Mortes";
                        break;
                }
            }
        }

        ficheiroEscrita.println(cabecalho);
        int[][] comparacaoInfet = comparacaoTotaisCasos(indexData1, indexData2, dados[1], numeroDiasAComparar);
        int[][] comparacaoHosp = comparacaoTotaisCasos(indexData1, indexData2, dados[2], numeroDiasAComparar);
        int[][] comparacaoUCI = comparacaoTotaisCasos(indexData1, indexData2, dados[3], numeroDiasAComparar);
        int[][] comparacaoObi = comparacaoTotaisCasos(indexData1, indexData2, dados[4], numeroDiasAComparar);
        for (int j = 0; j < numeroDiasAComparar; j++) {

            if ((indexData1 + j) - 1 < 0) {
                ficheiroEscrita.println(datas[j + indexData1] + " 1ªIntervalo" + mostraSeExistir(colunas, 1, ",Sem dados") + mostraSeExistir(colunas, 2, ",Sem dados") + mostraSeExistir(colunas, 3, ",Sem dados") + mostraSeExistir(colunas, 4, ",Sem dados"));
            } else {
                ficheiroEscrita.println(datas[j + indexData1] + " 1ªIntervalo" + mostraSeExistir(colunas, 1, "," + comparacaoInfet[0][j]) + mostraSeExistir(colunas, 2, "," + comparacaoHosp[0][j]) + mostraSeExistir(colunas, 3, "," + comparacaoUCI[0][j]) + mostraSeExistir(colunas, 4, "," + comparacaoObi[0][j]));
            }
            if ((indexData2 + j) - 1 < 0) {
                ficheiroEscrita.println(datas[j + indexData2] + " 2ªIntervalo" + mostraSeExistir(colunas, 1, "," + "Sem dados") + mostraSeExistir(colunas, 2, "," + "Sem dados") + mostraSeExistir(colunas, 3, "," + "Sem dados") + mostraSeExistir(colunas, 4, "," + "Sem dados"));
            } else {
                ficheiroEscrita.println(datas[j + indexData2] + " 2ªIntervalo" + mostraSeExistir(colunas, 1, "," + comparacaoInfet[1][j]) + mostraSeExistir(colunas, 2, "," + comparacaoHosp[1][j]) + mostraSeExistir(colunas, 3, "," + comparacaoUCI[1][j]) + mostraSeExistir(colunas, 4, "," + comparacaoObi[1][j]));
            }

            ficheiroEscrita.println("Diferença entre dados" + mostraSeExistir(colunas, 1, "," + (comparacaoInfet[1][j]-comparacaoInfet[0][j])) + mostraSeExistir(colunas, 2, "," + (comparacaoHosp[1][j]-comparacaoHosp[0][j])) + mostraSeExistir(colunas, 3, "," + (comparacaoUCI[1][j]-comparacaoUCI[0][j])) + mostraSeExistir(colunas, 4, "," + (comparacaoObi[1][j]-comparacaoObi[0][j])));
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
        double desvioInfet1 = desvioPadrao(comparacaoInfet[0],mediaInfetado1);
        double desvioInfet2 = desvioPadrao(comparacaoInfet[1],mediaInfetado2);
        double desvioHosp1 = desvioPadrao(comparacaoHosp[0],mediaHosp1);
        double desvioHosp2 = desvioPadrao(comparacaoHosp[1],mediaHosp2);
        double desvioUCI1 = desvioPadrao(comparacaoUCI[0],mediaUCI1);
        double desvioUCI2 = desvioPadrao(comparacaoUCI[0],mediaUCI2);
        double desvioObitos1 = desvioPadrao(comparacaoObi[0],mediaObitos1);
        double desvioObitos2 = desvioPadrao(comparacaoObi[0],mediaObitos2);

        ficheiroEscrita.println("Media do 1ªIntervalo" + mostraSeExistir(colunas, 1, "," + mediaInfetado1) + mostraSeExistir(colunas, 2, "," + mediaHosp1) + mostraSeExistir(colunas, 3, "," + mediaUCI1) + mostraSeExistir(colunas, 4, "," + mediaObitos1));
        ficheiroEscrita.println("Media do 2ªIntervalo" + mostraSeExistir(colunas, 1, "," +  mediaInfetado2) + mostraSeExistir(colunas, 2, "," + mediaHosp2) + mostraSeExistir(colunas, 3, "," + mediaUCI2) + mostraSeExistir(colunas, 4, "," + mediaObitos2));
        ficheiroEscrita.println("Diferença entre dados" + mostraSeExistir(colunas, 1, "," + (mediaInfetado2-mediaInfetado1)) + mostraSeExistir(colunas, 2, "," + (mediaHosp2-mediaHosp1)) + mostraSeExistir(colunas, 3, "," + (mediaUCI2-mediaUCI1)) + mostraSeExistir(colunas, 4, "," + (mediaObitos2-mediaObitos1)));
        ficheiroEscrita.println("Desvio Padrão do 1ªIntervalo" + mostraSeExistir(colunas, 1, "," + desvioInfet1) + mostraSeExistir(colunas, 2, "," + desvioHosp1) + mostraSeExistir(colunas, 3, "," + desvioUCI1) + mostraSeExistir(colunas, 4, "," + desvioObitos1));
        ficheiroEscrita.println("Desvio Padrão do 2ªIntervalo" + mostraSeExistir(colunas, 1, "," + desvioInfet2) + mostraSeExistir(colunas, 2, "," + desvioHosp2) + mostraSeExistir(colunas, 3, "," + desvioUCI2) + mostraSeExistir(colunas, 4,"," +  desvioObitos2));
        ficheiroEscrita.println("Diferença entre dados" + mostraSeExistir(colunas, 1, "," + (desvioInfet2-desvioInfet1)) + mostraSeExistir(colunas, 2, "," + (desvioHosp2-desvioHosp1)) + mostraSeExistir(colunas, 3, "," + (desvioUCI2-desvioUCI1)) + mostraSeExistir(colunas, 4, "," + (desvioObitos2-desvioObitos1)));

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
        boolean todosInteiros;
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


        if (dadosInteiros.length == 1 && dadosInteiros[0] == 0) {
            int[] array = {1, 2, 3, 4};
            return array;
        } else {
            return dadosInteiros;
        }
    }

    public static String menu() {
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
                    opcao = kbScanner.nextLine();
            }
        } while (!opcao.equals("S") && !opcao.equals("s") && !opcao.equals("N") && !opcao.equals("n"));
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

    public static String[] leituraIntervaloDatas() {
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

    public static int indexData(Date leituraDeDatas, String[] datas) {

        int len = datas.length;
        int i = 0;

        while (i < len) {
            if (leituraDeDatas.equals(stringParaDateEConverterDatas(datas[i]))) {
                return i;
            } else {
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

    public static String mostraSeExistir (int[] array, int opcao, String texto) {
        if (existeNoArray(array, opcao)) {
            return texto;
        } else {
            return "";
        }
    }

    public static String mostraSeExistir (int[] array, int opcao, int valor) {
        if (existeNoArray(array, opcao)) {
            return String.valueOf(valor);
        } else {
            return "";
        }
    }

    public static String mostraSeExistir (int[] array, int opcao, double valor) {
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

        if (date[0].length() == 4) {
            ano = Integer.parseInt(date[0]);
            mes = Integer.parseInt(date[1]);
            dia = Integer.parseInt(date[2]);

        } else if (date[2].length() == 4) {
            ano = Integer.parseInt(date[2]);
            mes = Integer.parseInt(date[1]);
            dia = Integer.parseInt(date[0]);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes - 1, dia, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static String converterDatasEntreSi (String data ) {
        String[] date = data.split("-");
        String ano;
        String mes;
        String dia;

        if (date[0].length() == 4) {
            ano = date[0];
            mes = date[1];
            dia = date[2];
            return dia + "-" + mes + "-" + ano;

        } else if (date[2].length() == 4) {
            ano = date[2];
            mes = date[1];
            dia = date[0];
            return ano + "-" + mes + "-" +dia;
        }
        return data;
    }

    public static boolean existeNoArrayDatas(String[] array, String[] datas) {
        boolean existe=false;
        boolean existe2=false;
        for (int i = 0; i < array.length; i++) {
            if ((array[i].equals(datas[0])) || array[i].equals(converterDatasEntreSi(datas[0]))) {
               existe=true;
            }
            if (array[i].equals(datas[1]) || array[i].equals(converterDatasEntreSi(datas[1]))) {
                existe2=true;
            }
        }
        if (existe && existe2){
            return true;
        } else
            return false;
    }

    public static boolean existeNoArrayData(String[] array, String datas) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i].equals(datas))) {
                return true;
            }
        }
        return false;
    }

    //--------------------------------------------Analise Diária Novos Casos------------------------------------------//

    public static void mostrarDadosDiarios(String[] leituraDeDatas, String[] datas, int[][] dados, int[] colunas) {

        int indexData1 = indexData(stringParaDateEConverterDatas(leituraDeDatas[0]), datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(leituraDeDatas[1]), datas);

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
            for (int i = 0; i <= indexData2 - indexData1; i++) {
                int[] novosInfetados = dadosDiariosNovos(dados[1], leituraDeDatas, datas);
                int[] novosHospitalizacoes = dadosDiariosNovos(dados[2], leituraDeDatas, datas);
                int[] novosUCI = dadosDiariosNovos(dados[3], leituraDeDatas, datas);
                int[] novosMortes = dadosDiariosNovos(dados[4], leituraDeDatas, datas);
                if (novosInfetados[i] == -1 && novosHospitalizacoes[i] == -1 && novosUCI[i] == -1 && novosMortes[i] == -1) {
                    System.out.printf(impressao, datas[i], mostraSeExistir(colunas, 1, "Sem dados"), mostraSeExistir(colunas, 2, "Sem dados"), mostraSeExistir(colunas, 3, "Sem dados"), mostraSeExistir(colunas, 4, "Sem dados"));
                } else {
                    System.out.printf(impressao, datas[i + indexData1], mostraSeExistir(colunas, 1, novosInfetados[i]), mostraSeExistir(colunas, 2, novosHospitalizacoes[i]), mostraSeExistir(colunas, 3, novosUCI[i]), mostraSeExistir(colunas, 4, novosMortes[i]));
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
            System.out.printf(impressao, datas[i+indexData1], mostraSeExistir(colunas, 1, dadosInfetados[i]),mostraSeExistir(colunas, 2, dadosHospitalizados[i]),mostraSeExistir(colunas, 3, dadosUCI[i]),mostraSeExistir(colunas, 4, dadosMortes[i]));
        }
    }


    //--------------------------------------------Analise Semanal Novos Casos-----------------------------------------------------//

    public static void mostrarDadosSemanais(String[] leituraDeDatas, String[] datas, int[][] dados,int[] colunas,int numeroSemanas)  {
        // mostrar dados das semanas entre as datas pretendidas, ou seja, por exemplo: dados da 1 semn, dados da 2 semn, dados da 3 semn
        // 2020-04-01 2020-04-02 -> Não é uma semana completa
        // 2020-04-1 2020-04-17 -> mostra 2 semanas 1-15
        // 1 semana, subtrair acumulado do ult dia semana com 1 dia semn
        int indexData1 = indexData(verificarSemanaSegunda(stringParaDateEConverterDatas(leituraDeDatas[0])),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDateEConverterDatas(leituraDeDatas[1])),datas);

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
                System.out.printf(impressao, datas[indexData1+(7*i)],datas[(indexData1+(7*i))+6], mostraSeExistir(colunas, 1, novosInfetados[i]),mostraSeExistir(colunas, 2, novosHospitalizacoes[i]),mostraSeExistir(colunas, 3, novosUCI[i]),mostraSeExistir(colunas, 4, novosMortes[i]));
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

    public static Date verificarSemanaSegunda(Date inicio) {

        Calendar data = Calendar.getInstance();
        data.setTime(inicio);

        while (data.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            data.add(Calendar.DAY_OF_WEEK, 1);
        }

        return (data.getTime());
    }

    public static Date verificarSemanaDomingo(Date fim) {
        Calendar dataFinal = Calendar.getInstance();

        dataFinal.setTime(fim);

        while (dataFinal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            dataFinal.add(Calendar.DAY_OF_WEEK, -(dataFinal.get(Calendar.DAY_OF_WEEK) - 1));
        }

        return (dataFinal.getTime());
    }


    public static int calcularNumSemanas(Date inicio, Date fim) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(verificarSemanaSegunda(inicio));
        cal2.setTime(verificarSemanaDomingo(fim));

        limparHoras(cal1);
        limparHoras(cal2);

        inicioDaSemana(cal1);
        inicioDaSemana(cal2);

        int numSemanas = 0;

        while (cal1.getTime().before(fim)) {
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

    public static void mostrarDadosTotaisSemanais (String[] leituraDeDatas,String[] datas, int[][] dados, int[] colunas,int numeroSemanas){
        int indexData1 = indexData(verificarSemanaSegunda(stringParaDateEConverterDatas((leituraDeDatas[0]))),datas);
        int indexData2 = indexData(verificarSemanaDomingo(stringParaDateEConverterDatas((leituraDeDatas[1]))),datas);

        if (numeroSemanas == -1) {
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
                System.out.printf(impressao, datas[indexData1+(7*i)],datas[(indexData1+(7*i))+6], mostraSeExistir(colunas, 1, totaisInfetados[i]),mostraSeExistir(colunas, 2, totaisHospitalizacoes[i]),mostraSeExistir(colunas, 3, totaisUCI[i]),mostraSeExistir(colunas, 4, totaisMortes[i]));
            }
        }
    }

    public static int[] dadosTotaisSemanaisNovos(int[] dados, int numeroSemanas, int indexData2, int indexData1) {

        int[] dadosNovos = new int[numeroSemanas];

        for (int j = 0; j < numeroSemanas; j++) {
            if (indexData1 <= indexData2) {
                dadosNovos[j] = dados[indexData1] + dados[indexData1 + 1];
                indexData1 = indexData1 + 1;
            }
        }
        return dadosNovos;
    }

    // ------------------------------------------Analise Mensal Novos Casos-------------------------------------------------------//

    public static void mostrarDadosMensais(String[] leituraDeDatas, String[] datas, int[][] dados,int[] colunas,int numeroMeses) {
        // 4 semanas -> 1 mes
        // Dados para mostrar = acumulado ult dia do mes - acumulado pri dia do mes
        int indexData1 = indexData(primeiroDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[0])), datas);
        int indexData2 = indexData(ultimoDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[1])), datas);

        Date primeiroDiaValido = stringParaDateEConverterDatas(datas[indexData1]);

            String cabecalho = "Meses                  ";
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
                            cabecalho += " | Novass Mortes";
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
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(primeiroDiaValido);
            int numDias = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int currIndex=indexData1;
            for (int i = 0; i < numeroMeses; i++) {
                int[] novosInfetados = dadosMensaisNovos(primeiroDiaValido, dados[1], numeroMeses, indexData2, indexData1);
                int[] novosHospitalizacoes = dadosMensaisNovos(primeiroDiaValido, dados[2], numeroMeses, indexData2, indexData1);
                int[] novosUCI = dadosMensaisNovos(primeiroDiaValido, dados[3], numeroMeses, indexData2, indexData1);
                int[] novosMortes = dadosMensaisNovos(primeiroDiaValido, dados[4], numeroMeses, indexData2, indexData1);
                System.out.printf(impressao, datas[currIndex],datas[currIndex+numDias], mostraSeExistir(colunas, 1, novosInfetados[i]),mostraSeExistir(colunas, 2, novosHospitalizacoes[i]),mostraSeExistir(colunas, 3, novosUCI[i]),mostraSeExistir(colunas, 4, novosMortes[i]));
                calendar.add(Calendar.MONTH,1);
                currIndex+=numDias;
            }

        }


    public static int[] dadosMensaisNovos(Date primeiroDia, int[] dados, int numeroMeses, int index2, int index1) {
        int[] dadosNovos = new int[numeroMeses];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(primeiroDia);

        int currIndex=index1;

        for (int i = 0; i < numeroMeses; i++) {
            int numDias = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            dadosNovos[i] = dados[currIndex + numDias - 1] - dados[currIndex];
            calendar.add(Calendar.MONTH, 1);
            currIndex += numDias;
        }
        return dadosNovos;
    }

    public static int numeroMeses(String[] leituraDeDatas) {

        Calendar mesesInicial = Calendar.getInstance();
        Calendar mesFinal = Calendar.getInstance();
        Date inicio = primeiroDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[0]));
        Date fim = ultimoDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[1]));
        mesesInicial.setTime(inicio);
        mesFinal.setTime(fim);

        int diferencaDeAnos = mesFinal.get(Calendar.YEAR) - mesesInicial.get(Calendar.YEAR);
        int diferencaDeMeses = mesFinal.get(Calendar.MONTH) - mesesInicial.get(Calendar.MONTH);

        int numeroMeses = diferencaDeAnos * 12 + diferencaDeMeses;
        return numeroMeses + 1;
    }


    public static Date primeiroDiaMesValido(Date inicio) {
        Calendar data = Calendar.getInstance();

        data.setTime(inicio);

        while (data.get(Calendar.DAY_OF_MONTH) != 1) {
            data.set(Calendar.DAY_OF_MONTH,1);
            data.add(Calendar.DAY_OF_MONTH, 1);
        }

        return ((data.getTime()));
    }

    public static Date ultimoDiaMesValido(Date fim) {

        Calendar dataFinal = Calendar.getInstance();

        dataFinal.setTime(fim);
        int diaFinalMes = dataFinal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (diaFinalMes != dataFinal.get(Calendar.DAY_OF_MONTH)) {
            // evitar bug: set fevereiro c/ dia 30
            dataFinal.set(Calendar.DAY_OF_MONTH, 1);
            dataFinal.add(Calendar.MONTH, -1);
            dataFinal.set(Calendar.DAY_OF_MONTH, dataFinal.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return dataFinal.getTime();
    }

    public static void mostrarDadosTotaisMensais(String[] leituraDeDatas, String[] datas, int[][] dados,int[] colunas,int numeroMeses) {
        int indexData1 = indexData(primeiroDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[0])), datas);
        int indexData2 = indexData(ultimoDiaMesValido(stringParaDateEConverterDatas(leituraDeDatas[1])), datas);

        Date primeiroDiaValido = stringParaDateEConverterDatas(datas[indexData1]);

            String cabecalho = "Meses                  ";
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
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(primeiroDiaValido);
            int numDias = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int currIndex=indexData1;
            for (int i = 0; i < numeroMeses; i++) {
                int[] novosInfetados = dadosMensaisTotaisNovos(primeiroDiaValido, dados[1], numeroMeses, indexData2, indexData1);
                int[] novosHospitalizacoes = dadosMensaisTotaisNovos(primeiroDiaValido, dados[2], numeroMeses, indexData2, indexData1);
                int[] novosUCI = dadosMensaisTotaisNovos(primeiroDiaValido, dados[3], numeroMeses, indexData2, indexData1);
                int[] novosMortes = dadosMensaisTotaisNovos(primeiroDiaValido, dados[4], numeroMeses, indexData2, indexData1);
                System.out.printf(impressao, datas[currIndex],datas[currIndex+numDias], mostraSeExistir(colunas, 1, novosInfetados[i]),mostraSeExistir(colunas, 2, novosHospitalizacoes[i]),mostraSeExistir(colunas, 3, novosUCI[i]),mostraSeExistir(colunas, 4, novosMortes[i]));
                calendar.add(Calendar.MONTH,1);
                currIndex+=numDias;
            }
        }


    public static int[] dadosMensaisTotaisNovos(Date data, int[] dados, int numeroMeses, int index2, int index1) {
        int[] dadosNovos = new int[numeroMeses];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.MILLISECOND, 0);

        int diasNoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        if (numeroMeses == 1) {
            for (int i = 0; i < diasNoMes; i++) {
                if (index1 <= index2) {
                    dadosNovos[0] = dadosNovos[0] + dados[index1];
                    index1++;

                }
            }
        } else {
            for (int i = 0; i < numeroMeses; i++) {
                for (int j = 0; j < diasNoMes; j++) {
                    if (index1 <= index2) {
                        dadosNovos[i] = dadosNovos[i] + dados[index1];
                        index1++;
                    }
                }
                calendar.add(Calendar.MONTH, 1);
                diasNoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
        }
        return dadosNovos;
    }

    //-------------------------------------Analise Comparativa Novos Casos--------------------------------------------//

    public static void analiseComparativaNovosCasos(String[] intervalo1, String[] intervalo2, String[] datas, int[][] dados, int[] colunas) {
        long diasIntervalo1;
        long diasIntervalo2;

        // calcular intervalo de dias do intervalo1
        diasIntervalo1 = calcularDiasEntreIntervalo(intervalo1);
        // calcular intervalo de dias do intervalo2
        diasIntervalo2 = calcularDiasEntreIntervalo(intervalo2);

        int numeroDiasAComparar = (int) diasIntervalo1;
        if (diasIntervalo1 < diasIntervalo2) {
            numeroDiasAComparar = (int) diasIntervalo1;
        } else if (diasIntervalo2 < diasIntervalo1) {
            numeroDiasAComparar = (int) diasIntervalo2;
        }
        int indexData1 = indexData(stringParaDateEConverterDatas(intervalo1[0]), datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(intervalo2[0]), datas);

        String cabecalho = "Datas                       ";
        String impressao = "";
        String impressaoMediaEDesvio = "";
        String tracinhos = "------------------------------";

        for (int i = 1; i <= 4; i++) {
            if (existeNoArray(colunas, i)) {
                switch (i) {
                    case 1:
                        cabecalho += " | %5s Novos Infetados";
                        impressao += " | %21s";
                        impressaoMediaEDesvio += " | %21.4f";
                        tracinhos += "------------------------";
                        break;
                    case 2:
                        cabecalho += " | Novas Hospitalizações";
                        impressao += " | %21.10s";
                        impressaoMediaEDesvio += " | %21.4f";
                        tracinhos += "------------------------";
                        break;
                    case 3:
                        cabecalho += " | Novos UCI";
                        impressao += " | %9.10s";
                        impressaoMediaEDesvio += " | %9.4f";
                        tracinhos += "------------";
                        break;
                    case 4:
                        cabecalho += " | Novas Mortes";
                        impressao += " | %12.10s";
                        impressaoMediaEDesvio += " | %12.4f";
                        tracinhos += "-------------";
                        break;
                }
            } else {
                impressao += "%s";
                impressaoMediaEDesvio += "%s";
            }
        }

        cabecalho += "\n";
        impressao += "\n";
        impressaoMediaEDesvio += "\n";

        System.out.printf(cabecalho, "");
        int[][] comparacaoInfet = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[1], numeroDiasAComparar);
        int[][] comparacaoHosp = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[2], numeroDiasAComparar);
        int[][] comparacaoUCI = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[3], numeroDiasAComparar);
        int[][] comparacaoObi = comparacaoDadosDiariosNovos(indexData1, indexData2, dados[4], numeroDiasAComparar);
        for (int j = 0; j < numeroDiasAComparar; j++) {

            if ((indexData1 + j) - 1 < 0) {
                System.out.printf("%s 1ªIntervalo      " + impressao, datas[j + indexData1], mostraSeExistir(colunas, 1, "Sem dados"), mostraSeExistir(colunas, 2, "Sem dados"), mostraSeExistir(colunas, 3, "Sem dados"), mostraSeExistir(colunas, 4, "Sem dados"));
            } else {
                System.out.printf("%s 1ªIntervalo      " + impressao, datas[j + indexData1], mostraSeExistir(colunas, 1, comparacaoInfet[0][j]), mostraSeExistir(colunas, 2, comparacaoHosp[0][j]), mostraSeExistir(colunas, 3, comparacaoUCI[0][j]), mostraSeExistir(colunas, 4, comparacaoObi[0][j]));
            }
            if ((indexData2 + j) - 1 < 0) {
                System.out.printf("%s 2ªIntervalo      " + impressao, datas[j + indexData2], mostraSeExistir(colunas, 1, "Sem dados"), mostraSeExistir(colunas, 2, "Sem dados"), mostraSeExistir(colunas, 3, "Sem dados"), mostraSeExistir(colunas, 4, "Sem dados"));
            } else {
                System.out.printf("%s 2ªIntervalo      " + impressao, datas[j + indexData2], mostraSeExistir(colunas, 1, comparacaoInfet[1][j]), mostraSeExistir(colunas, 2, comparacaoHosp[1][j]), mostraSeExistir(colunas, 3, comparacaoUCI[1][j]), mostraSeExistir(colunas, 4, comparacaoObi[1][j]));
            }

            System.out.printf("%sDiferença entre dados       " + impressao,"",mostraSeExistir(colunas, 1, comparacaoInfet[1][j]-comparacaoInfet[0][j]),mostraSeExistir(colunas, 2, comparacaoHosp[1][j]-comparacaoHosp[0][j]),mostraSeExistir(colunas, 3, comparacaoUCI[1][j]-comparacaoUCI[0][j]),mostraSeExistir(colunas, 4, comparacaoObi[1][j]-comparacaoObi[0][j]));
            System.out.println(tracinhos);

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
        double desvioInfet1 = desvioPadrao(comparacaoInfet[0],mediaInfetado1);
        double desvioInfet2 = desvioPadrao(comparacaoInfet[1],mediaInfetado2);
        double desvioHosp1 = desvioPadrao(comparacaoHosp[0],mediaHosp1);
        double desvioHosp2 = desvioPadrao(comparacaoHosp[1],mediaHosp2);
        double desvioUCI1 = desvioPadrao(comparacaoUCI[0],mediaUCI1);
        double desvioUCI2 = desvioPadrao(comparacaoUCI[0],mediaUCI2);
        double desvioObitos1 = desvioPadrao(comparacaoObi[0],mediaObitos1);
        double desvioObitos2 = desvioPadrao(comparacaoObi[0],mediaObitos2);

        System.out.printf("Media do 1ªIntervalo        " + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, mediaInfetado1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, mediaInfetado1)), mostraSeExistir(colunas, 2, mediaHosp1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, mediaHosp1)), mostraSeExistir(colunas, 3, mediaUCI1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, mediaUCI1)), mostraSeExistir(colunas, 4, mediaObitos1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, mediaObitos1)));
        System.out.printf("Media do 2ªIntervalo        " + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, mediaInfetado2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, mediaInfetado2)), mostraSeExistir(colunas, 2, mediaHosp2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, mediaHosp2)), mostraSeExistir(colunas, 3, mediaUCI2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, mediaUCI2)), mostraSeExistir(colunas, 4, mediaObitos2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, mediaObitos2)));
        System.out.printf("Diferença entre dados       " + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, mediaInfetado2-mediaInfetado1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, mediaInfetado2-mediaInfetado1)),mostraSeExistir(colunas, 2, mediaHosp2-mediaHosp1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, mediaHosp2-mediaHosp1)),mostraSeExistir(colunas, 3, mediaUCI2-mediaUCI1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, mediaUCI2-mediaUCI1)),mostraSeExistir(colunas, 4, mediaObitos2-mediaObitos1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, mediaObitos2-mediaObitos1)));
        System.out.println(tracinhos);
        System.out.printf("Desvio Padrão do 1ªIntervalo" + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, desvioInfet1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, desvioInfet1)), mostraSeExistir(colunas, 2, desvioHosp1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, desvioHosp1)), mostraSeExistir(colunas, 3, desvioUCI1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, desvioUCI1)), mostraSeExistir(colunas, 4, desvioObitos1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, desvioObitos1)));
        System.out.printf("Desvio Padrão do 2ªIntervalo" + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, desvioInfet2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, desvioInfet2)), mostraSeExistir(colunas, 2, desvioHosp2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, desvioHosp2)), mostraSeExistir(colunas, 3, desvioUCI2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, desvioUCI2)), mostraSeExistir(colunas, 4, desvioObitos2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, desvioObitos2)));
        System.out.printf("Diferença entre dados       " + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, desvioInfet2-desvioInfet1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, desvioInfet2-desvioInfet1)),mostraSeExistir(colunas, 2, desvioHosp2-desvioHosp1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, desvioHosp2-desvioHosp1)),mostraSeExistir(colunas, 3, desvioUCI2-desvioUCI1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, desvioUCI2-desvioUCI1)),mostraSeExistir(colunas, 4, desvioObitos2-desvioObitos1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, desvioObitos2-desvioObitos1)));

    }

    public static int[][] comparacaoDadosDiariosNovos(int indexData1, int indexData2, int[] dados, int numeroDias) {
        int indice1 = 0;
        int indice2 = 0;
        int[][] dadosNovos = new int[NUMERO_DADOS_COMPARACAO][numeroDias];

        for (int i = indexData1; i < indexData1 + numeroDias; i++) {
            for (int j = 0; j < numeroDias; j++) {
                if (i - 1 >= 0) {
                    dadosNovos[0][indice1] = dados[i] - dados[i - 1];
                }
            }
            indice1++;
        }
        for (int i = indexData2; i < indexData2 + numeroDias; i++) {
            for (int j = 0; j < numeroDias; j++) {
                if (i - 1 >= 0) {
                    dadosNovos[1][indice2] = dados[i] - dados[i - 1];
                }
            }
            indice2++;
        }
        return dadosNovos;
    }

    public static double mediaComparativa(int[] dados) {
        double media = 0;

        for (int i = 0; i < dados.length; i++) {
            media = media + dados[i];
        }
        return (media / dados.length);
    }

    public static double desvioPadrao(int[] dados, double media) {
        double desvio = 0;

        for (int i = 0; i < dados.length; i++) {
            desvio = desvio + Math.pow(dados[i] - media, 2);
        }
        return Math.sqrt(desvio / dados.length);
    }

    //-------------------------------------Analise Comparativa Totais Casos-------------------------------------------//

    public static void analiseComparativaTotaisCasos(String[] intervalo1, String[] intervalo2, String[] datas, int[][] dados, int[] colunas) {
        long diasIntervalo1;
        long diasIntervalo2;

        // calcular intervalo de dias do intervalo1
        diasIntervalo1 = calcularDiasEntreIntervalo(intervalo1);
        // calcular intervalo de dias do intervalo2
        diasIntervalo2 = calcularDiasEntreIntervalo(intervalo2);

        int numeroDiasAComparar = (int) diasIntervalo1;
        if (diasIntervalo1 < diasIntervalo2) {
            numeroDiasAComparar = (int) diasIntervalo1;
        } else if (diasIntervalo2 < diasIntervalo1) {
            numeroDiasAComparar = (int) diasIntervalo2;
        }
        int indexData1 = indexData(stringParaDateEConverterDatas(intervalo1[0]), datas);
        int indexData2 = indexData(stringParaDateEConverterDatas(intervalo2[0]), datas);

        String cabecalho = "Datas                       ";
        String impressao = "";
        String impressaoMediaEDesvio = "";
        String tracinhos = "------------------------------";

        for (int i = 1; i <= 4; i++) {
            if (existeNoArray(colunas, i)) {
                switch (i) {
                    case 1:
                        cabecalho += " | %5s Total Infetados";
                        impressao += " | %21s";
                        impressaoMediaEDesvio += " | %21.4f";
                        tracinhos += "------------------------";
                        break;
                    case 2:
                        cabecalho += " | Total Hospitalizações";
                        impressao += " | %21.10s";
                        impressaoMediaEDesvio += " | %21.4f";
                        tracinhos += "------------------------";
                        break;
                    case 3:
                        cabecalho += " | Total UCI";
                        impressao += " | %9.10s";
                        impressaoMediaEDesvio += " | %9.4f";
                        tracinhos += "------------";
                        break;
                    case 4:
                        cabecalho += " | Total Mortes";
                        impressao += " | %12.10s";
                        impressaoMediaEDesvio += " | %12.4f";
                        tracinhos += "-------------";
                        break;
                }
            } else {
                impressao += "%s";
                impressaoMediaEDesvio += "%s";
            }
        }

        cabecalho += "\n";
        impressao += "\n";
        impressaoMediaEDesvio += "\n";

        System.out.printf(cabecalho, "");
        int[][] comparacaoInfet = comparacaoTotaisCasos(indexData1, indexData2, dados[1], numeroDiasAComparar);
        int[][] comparacaoHosp = comparacaoTotaisCasos(indexData1, indexData2, dados[2], numeroDiasAComparar);
        int[][] comparacaoUCI = comparacaoTotaisCasos(indexData1, indexData2, dados[3], numeroDiasAComparar);
        int[][] comparacaoObi = comparacaoTotaisCasos(indexData1, indexData2, dados[4], numeroDiasAComparar);
        for (int j = 0; j < numeroDiasAComparar; j++) {

            if ((indexData1 + j) - 1 < 0) {
                System.out.printf("%s 1ªIntervalo      " + impressao, datas[j + indexData1], mostraSeExistir(colunas, 1, "Sem dados"), mostraSeExistir(colunas, 2, "Sem dados"), mostraSeExistir(colunas, 3, "Sem dados"), mostraSeExistir(colunas, 4, "Sem dados"));
            } else {
                System.out.printf("%s 1ªIntervalo      " + impressao, datas[j + indexData1], mostraSeExistir(colunas, 1, comparacaoInfet[0][j]), mostraSeExistir(colunas, 2, comparacaoHosp[0][j]), mostraSeExistir(colunas, 3, comparacaoUCI[0][j]), mostraSeExistir(colunas, 4, comparacaoObi[0][j]));
            }
            if ((indexData2 + j) - 1 < 0) {
                System.out.printf("%s 2ªIntervalo      " + impressao, datas[j + indexData2], mostraSeExistir(colunas, 1, "Sem dados"), mostraSeExistir(colunas, 2, "Sem dados"), mostraSeExistir(colunas, 3, "Sem dados"), mostraSeExistir(colunas, 4, "Sem dados"));
            } else {
                System.out.printf("%s 2ªIntervalo      " + impressao, datas[j + indexData2], mostraSeExistir(colunas, 1, comparacaoInfet[1][j]), mostraSeExistir(colunas, 2, comparacaoHosp[1][j]), mostraSeExistir(colunas, 3, comparacaoUCI[1][j]), mostraSeExistir(colunas, 4, comparacaoObi[1][j]));
            }

            System.out.printf("%sDiferença entre dados       " + impressao,"",mostraSeExistir(colunas, 1, comparacaoInfet[1][j]-comparacaoInfet[0][j]),mostraSeExistir(colunas, 2, comparacaoHosp[1][j]-comparacaoHosp[0][j]),mostraSeExistir(colunas, 3, comparacaoUCI[1][j]-comparacaoUCI[0][j]),mostraSeExistir(colunas, 4, comparacaoObi[1][j]-comparacaoObi[0][j]));
            System.out.println(tracinhos);

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
        double desvioInfet1 = desvioPadrao(comparacaoInfet[0],mediaInfetado1);
        double desvioInfet2 = desvioPadrao(comparacaoInfet[1],mediaInfetado2);
        double desvioHosp1 = desvioPadrao(comparacaoHosp[0],mediaHosp1);
        double desvioHosp2 = desvioPadrao(comparacaoHosp[1],mediaHosp2);
        double desvioUCI1 = desvioPadrao(comparacaoUCI[0],mediaUCI1);
        double desvioUCI2 = desvioPadrao(comparacaoUCI[0],mediaUCI2);
        double desvioObitos1 = desvioPadrao(comparacaoObi[0],mediaObitos1);
        double desvioObitos2 = desvioPadrao(comparacaoObi[0],mediaObitos2);

        System.out.printf("Media do 1ªIntervalo        " + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, mediaInfetado1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, mediaInfetado1)), mostraSeExistir(colunas, 2, mediaHosp1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, mediaHosp1)), mostraSeExistir(colunas, 3, mediaUCI1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, mediaUCI1)), mostraSeExistir(colunas, 4, mediaObitos1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, mediaObitos1)));
        System.out.printf("Media do 2ªIntervalo        " + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, mediaInfetado2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, mediaInfetado2)), mostraSeExistir(colunas, 2, mediaHosp2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, mediaHosp2)), mostraSeExistir(colunas, 3, mediaUCI2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, mediaUCI2)), mostraSeExistir(colunas, 4, mediaObitos2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, mediaObitos2)));
        System.out.printf("Diferença entre dados       " + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, mediaInfetado2-mediaInfetado1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, mediaInfetado2-mediaInfetado1)),mostraSeExistir(colunas, 2, mediaHosp2-mediaHosp1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, mediaHosp2-mediaHosp1)),mostraSeExistir(colunas, 3, mediaUCI2-mediaUCI1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, mediaUCI2-mediaUCI1)),mostraSeExistir(colunas, 4, mediaObitos2-mediaObitos1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, mediaObitos2-mediaObitos1)));
        System.out.println(tracinhos);
        System.out.printf("Desvio Padrão do 1ªIntervalo" + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, desvioInfet1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, desvioInfet1)), mostraSeExistir(colunas, 2, desvioHosp1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, desvioHosp1)), mostraSeExistir(colunas, 3, desvioUCI1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, desvioUCI1)), mostraSeExistir(colunas, 4, desvioObitos1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, desvioObitos1)));
        System.out.printf("Desvio Padrão do 2ªIntervalo" + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, desvioInfet2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, desvioInfet2)), mostraSeExistir(colunas, 2, desvioHosp2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, desvioHosp2)), mostraSeExistir(colunas, 3, desvioUCI2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, desvioUCI2)), mostraSeExistir(colunas, 4, desvioObitos2) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, desvioObitos2)));
        System.out.printf("Diferença entre dados       " + impressaoMediaEDesvio, mostraSeExistir(colunas, 1, desvioInfet2-desvioInfet1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 1, desvioInfet2-desvioInfet1)),mostraSeExistir(colunas, 2, desvioHosp2-desvioHosp1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 2, desvioHosp2-desvioHosp1)),mostraSeExistir(colunas, 3, desvioUCI2-desvioUCI1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 3, desvioUCI2-desvioUCI1)),mostraSeExistir(colunas, 4, desvioObitos2-desvioObitos1) == "" ? "" : Double.parseDouble(mostraSeExistir(colunas, 4, desvioObitos2-desvioObitos1)));

    }

    public static int[][] comparacaoTotaisCasos(int indexData1, int indexData2, int[] dados, int numeroDias) {
        int indice1 = 0;
        int indice2 = 0;
        int[][] dadosNovos = new int[NUMERO_DADOS_COMPARACAO][numeroDias];
        for (int i = indexData1; i < indexData1 + numeroDias; i++) {
            for (int j = 0; j < numeroDias; j++) {
                if (i - 1 >= 0) {
                    dadosNovos[0][indice1] = dados[i + 1] + dados[i];
                }
            }
            indice1++;
        }
        for (int i = indexData2; i < indexData2 + numeroDias; i++) {
            for (int j = 0; j < numeroDias; j++) {
                if (i - 1 >= 0) {
                    dadosNovos[1][indice2] = dados[i + 1] + dados[i];
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

    public static long calcularDiasEntreIntervalo(String[] intervalo) {

        Date inicio = stringParaDateEConverterDatas(intervalo[0]);
        Date fim = stringParaDateEConverterDatas(intervalo[1]);

        int diffdias = 0;
        long diff = fim.getTime() - inicio.getTime();
        long diffDias = diff / (24 * 60 * 60 * 1000) + 1;
        diffdias = (int) diffDias;

        return diffdias;
    }


    public static boolean verificarDiaExiste(String[] datas, String data) {
        boolean existe = false;
        String[] date = data.split("-");
        String ano = date[0];
        String mes = date[1];
        String dia = date[2];

        if (date[2].length() == 4) {
            ano = date[2];
            mes = date[1];
            dia = date[0];
        }
        data = dia + "-" + mes + "-" + ano;

        for (int i = 0; i < datas.length; i++) {
            if (data.equals(datas[i])) {
                existe = true;
            }
        }
        return existe;
    }

    public static String escolherDiaAnterior(int index, String[] datas) {
        return datas[index - 1];
    }

    public static String escolherDiaMaisProximo(Date data, String[] datas) {
        String diaMaisProximo = "";
        Date dataFinal = stringParaDateEConverterDatas(datas[datas.length - 1]);
        Date dataInicial = stringParaDateEConverterDatas(datas[0]);
        if (data.after(dataFinal)) {
            diaMaisProximo = datas[datas.length - 1];
        } else if (data.before(dataInicial)) {
            diaMaisProximo = datas[0];
        }
        return diaMaisProximo;
    }

    public static void mostrarPrevisao(String data, String[] datas, int[][] dados, String ficheiro, String dataEscolhida) throws FileNotFoundException {
        double[][] matriz;
        matriz = lerDadosMatriz(ficheiro, NUMERO_ESTADOS_DIFERENTES);
        String[] intervalo = new String[2];
        intervalo[0] = data;
        intervalo[1] = dataEscolhida;
        int index = indexData(stringParaDateEConverterDatas(data), datas);

        long diasDiferenca = (int) Math.abs(calcularDiasEntreIntervalo(intervalo)) - 1;

        double[][] matrizElevada = Matrizes.elevarMatriz(matriz, diasDiferenca);

        System.out.printf("\n%31s |Total Não Infetados | Total Infetados | Total Hospitalizações |    Total UCI    | Total Mortes\n", "");
        System.out.print("Previsão para o dia: " + dataEscolhida + " |");
        double[][] previsao = Matrizes.multiplicarMatrizes(matrizElevada, preencherArray(dados, index));
        System.out.printf("%19.1f | %15.1f | %21.1f | %15.1f | %12.1f\n", previsao[0][0], previsao[1][0], previsao[2][0], previsao[3][0], previsao[4][0]);

        double[][] matrizSemObi = matrizSemObito(matriz);
        double[][] matrizIdentidade = Matrizes.preencherDiagonalMatriz(1);
        double[][] iden = Matrizes.preencherDiagonalMatriz(1);

        //double[][] subtracaoIdenMatriz = subtrairMatrizes(matrizSemObi, matrizIdentidade);
        System.out.println("\n----------------------------------------------\n");
       /* for (int i = 0; i < subtracaoIdenMatriz.length; i++) {
            for (int j = 0; j < subtracaoIdenMatriz[i].length; j++) {
                System.out.print(subtracaoIdenMatriz[i][j] + " ");
            }
            System.out.println();
        } */
        System.out.println("\n----------------------x---------------------------\n");
        double[][] matrizL = new double[3][3];
        double[][] matrizU;
        matrizU = Matrizes.preencherDiagonalMatriz(1);
        double[][] matrizTest = {{2, -1, 3}, {-1, 0, 2}, {-1, 1, 0}};
        Matrizes.decomposicaoCrout(matrizL, matrizU, matrizTest);
        String[][] stringMatriz = {{"a", "0", "0"}, {"b", "c", "0"}, {"d", "e", "f"}};
        double[][] inversa = Matrizes.inversaL(matrizL,iden);

        System.out.println("\n----------------------string---------------------------\n");
        for (int i = 0; i < stringMatriz.length; i++) {
            for (int j = 0; j < stringMatriz[i].length; j++) {
                System.out.print(stringMatriz[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\n--------------------U-----------------------------\n");
        for (int i = 0; i < matrizU.length; i++) {
            for (int j = 0; j < matrizU[i].length; j++) {
                System.out.print(matrizU[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n--------------------L-----------------------------\n");
        for (int i = 0; i < matrizL.length; i++) {
            for (int j = 0; j < matrizL[i].length; j++) {
                System.out.print(matrizL[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n--------------------inversaL-----------------------------\n");
        for (int i = 0; i < matrizL.length; i++) {
            for (int j = 0; j < matrizL[i].length; j++) {
                System.out.print(matrizL[i][j] + " ");
            }
            System.out.println();
        }
    }

    // passar para o ficheiro Matrizes
    public static double[][] subtrairMatrizes(double[][] matriz, double[][] identidade) {
        double[][] sub = new double[matriz.length][matriz[0].length];
        for (int i = 0; i < sub.length; i++) {
            for (int j = 0; j < sub[i].length; j++) {
                sub[i][j] = Math.abs(identidade[i][j] - matriz[i][j]);
            }
        }
        return sub;
    }

    public static double[][] preencherArray(int[][] dados, int index) {
        double[][] array = new double[NUMERO_ESTADOS_DIFERENTES][1];
        for (int i = 0; i < array.length; i++) {
            array[i][0] = (dados[i][index]);
        }
        return array;
    }

    public static double[][] matrizSemObito(double[][] matriz) {
        double[][] matrizSemObito = new double[NUMERO_ESTADOS_DIFERENTES - 1][NUMERO_ESTADOS_DIFERENTES - 1];
        for (int i = 0; i < matrizSemObito.length; i++) {
            for (int j = 0; j < matrizSemObito[i].length; j++) {
                matrizSemObito[i][j] = matriz[i][j];
            }
        }
        return matrizSemObito;
    }
}
