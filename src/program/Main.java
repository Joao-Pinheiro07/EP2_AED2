package program;

import java.util.Scanner;

import entities.Grafo;

public class Main {

	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o caminho do arquivo a ser lido: ");
		String arquivo = sc.nextLine();
		
		long start = System.currentTimeMillis();		
		Grafo.gerar_lista_de_adjacencia(arquivo);
		long check1 = System.currentTimeMillis();
		
		System.out.println();
		System.out.print("Digite o caminho do arquivo de saída: ");		
		String saida = sc.nextLine();
		
		long check2 = System.currentTimeMillis();
		Grafo.gerar_dados_do_histograma(saida);		
		long arestas = Grafo.numero_de_arestas();
		System.out.println("Número de arestas = " + arestas);	
		long end = System.currentTimeMillis();
		
		long tempo = (check1 - start) + (end - check2);
		System.out.println("Tempo de leitura: " + tempo + " milissegundos.");
		
		sc.close();		
	}

}
