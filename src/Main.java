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
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final Scanner keyboardScanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> datas = new ArrayList<String>();
        ArrayList<Integer> acumuladoNaoInfetados = new ArrayList<Integer>();
        ArrayList<Integer> acumuladoInfetados = new ArrayList<Integer>();
        ArrayList<Integer> acumuladoHospitalizados = new ArrayList<Integer>();
        ArrayList<Integer> acumuladoUCI = new ArrayList<Integer>();
        ArrayList<Integer> acumuladoMortes = new ArrayList<Integer>();

        LerFicheiro(SelecionarFicheiro(), datas, acumuladoNaoInfetados, acumuladoInfetados, acumuladoHospitalizados, acumuladoUCI, acumuladoMortes);

        for (int i = 0; i < datas.size(); i++) {
            System.out.println(acumuladoMortes.get(i));
        }
    }

    public static void LerFicheiro(String filePath, ArrayList<String> datas, ArrayList<Integer> acumuladoNaoInfetados,
                                   ArrayList<Integer> acumuladoInfetados, ArrayList<Integer> acumuladoHospitalizados,
                                   ArrayList<Integer> acumuladoUCI, ArrayList<Integer> acumuladoMortes) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));

        String linha = scanner.nextLine();
        String[] dados;

        while (scanner.hasNextLine()) {
            linha = scanner.nextLine();
            dados = linha.split(",");
            datas.add(dados[0]);
            acumuladoNaoInfetados.add(Integer.parseInt(dados[1]));
            acumuladoInfetados.add(Integer.parseInt(dados[2]));
            acumuladoHospitalizados.add(Integer.parseInt(dados[3]));
            acumuladoUCI.add(Integer.parseInt(dados[4]));
            acumuladoMortes.add(Integer.parseInt(dados[5]));
        }
    }

    public static String SelecionarFicheiro() {
        System.out.print("Insira o caminho absoluto do ficheiro: ");
        return keyboardScanner.nextLine();
    }
}

