package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grafo {
	private static Map<String, Set<String>> listaDeAdjacencia = new HashMap<String, Set<String>>();
	private static Map<Integer, Integer> histograma = new HashMap<Integer, Integer>();

	public static Map<String, Set<String>> getListaDeAdjacencia() {
		return listaDeAdjacencia;
	}

	public static Map<Integer, Integer> getHistograma() {
		return histograma;
	}

	public static void gerar_lista_de_adjacencia(String arquivo) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(arquivo));
			bf.readLine(); // vertices
			bf.readLine(); // arestas
			String linha = bf.readLine();
			int i = 1;
			while (linha != null) {
				String[] dados = linha.split(" ");

				adicionar_aresta(dados[0], dados[1]);
				adicionar_aresta(dados[1], dados[0]);

				System.out.println("Linha " + i + " lida...");
				i = i + 1;
				linha = bf.readLine();
			}
			bf.close();
		}catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
    }
	
	public static void gerar_dados_do_histograma(String saida) {
		for(Map.Entry<String, Set<String>> entry : listaDeAdjacencia.entrySet()) {
			adicionar_no_histograma(entry.getValue());			
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(saida));
			bw.write("Grau, Frequência");
			for(Map.Entry<Integer, Integer> entry : histograma.entrySet()){
				bw.newLine();
				bw.write(entry.getKey() + ", " + entry.getValue());
			}
			bw.close();
		}catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	private static void adicionar_aresta(String id1, String id2) {
		Set<String> set = listaDeAdjacencia.get(id1);
		if (set == null) set = new HashSet<String>();
		set.add(id2);
		listaDeAdjacencia.put(id1, set);
	}
	
	private static void adicionar_no_histograma(Set<String> adjacentes) {
		int tamanho = adjacentes.size();
		int quantidadeVertice;
		if(histograma.get(tamanho) == null) quantidadeVertice = 0;
		else quantidadeVertice = histograma.get(tamanho);
		quantidadeVertice += 1;
		histograma.put(tamanho, quantidadeVertice);
	}
	
	public static long numero_de_arestas() {
		long quantidadeDeArestas = 0;
		for(Map.Entry<String, Set<String>> entry : listaDeAdjacencia.entrySet()) {
			quantidadeDeArestas += entry.getValue().size();
		}
		return (quantidadeDeArestas / 2);
	}
}
