/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Familia
 */
public class CFC {

    /**
     * Busca em Profundidade *
     */
    public void BuscaEmProfundidade(List<Integer>[] graph, int v, boolean[] visited, List<Integer> comp) {
        visited[v] = true;
        for (int i = 0; i < graph[v].size(); i++) {
            if (!visited[graph[v].get(i)]) {
                BuscaEmProfundidade(graph, graph[v].get(i), visited, comp);
            }
        }
        comp.add(v);
    }

    /**
     * função para definir a ordem *
     */
    public List<Integer> Ordenando(List<Integer>[] graph, boolean[] visited) {
        int V = graph.length;
        List<Integer> ordem = new ArrayList<Integer>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                BuscaEmProfundidade(graph, i, visited, ordem);
            }
        }
        return ordem;
    }

    public List<Integer>[] getTranspose(List<Integer>[] graph) {
        int V = graph.length;
        List<Integer>[] g = new List[V];
        for (int i = 0; i < V; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int v = 0; v < V; v++) {
            for (int i = 0; i < graph[v].size(); i++) {
                g[graph[v].get(i)].add(v);
            }
        }
        return g;
    }

    /**
     * Função para obter as F conexas
     *
     * @param graph *
     */
    public List<List<Integer>> getFconexas(List<Integer>[] graph) {
        int V = graph.length;
        boolean[] visited = new boolean[V];
        List<Integer> order = Ordenando(graph, visited);

        List<Integer>[] reverseGraph = getTranspose(graph);

        visited = new boolean[V];

        Collections.reverse(order);

        List<List<Integer>> SCComp = new ArrayList<>();
        for (int i = 0; i < order.size(); i++) {
            int v = order.get(i);
            if (!visited[v]) {
                List<Integer> comp = new ArrayList<>();
                BuscaEmProfundidade(reverseGraph, v, visited, comp);
                SCComp.add(comp);
            }
        }
        return SCComp;
    }

    /**
     * main
     *
     * @param args *
     */
    public static void main(String[] args) {
        //Scanner scan = new Scanner(System.in);
        CFC cfc = new CFC();

        //System.out.println("Informe o numero de vértices");
        /**
         * Numero de Vertices Testei com 8
         */
        //int Vertices = scan.nextInt();
        int Vertices = 8;
        List<Integer>[] g = new List[Vertices];
        for (int i = 0; i < Vertices; i++) {
            g[i] = new ArrayList<Integer>();
        }

        //System.out.println("\nInforme a quantidade de arestas");
        //int Arestas = scan.nextInt();
        int Arestas = 14;
        /**
         * Quantidade de arestas Testei com 14
         */
        System.out.println("Inserindo " + Arestas + " x, y cordenadas para as arestas");
        //for (int i = 0; i < Arestas; i++) {
        //    int x = scan.nextInt();
        //    int y = scan.nextInt();

        // Adicionando arestas 
        g[0].add(1);
        g[1].add(2);
        g[2].add(3);
        g[3].add(2);
        g[3].add(7);
        g[7].add(3);
        g[2].add(6);
        g[7].add(6);
        g[5].add(6);
        g[6].add(5);
        g[1].add(5);
        g[4].add(5);
        g[4].add(0);
        g[1].add(4);

        //g[x].add(y);
        // }
        System.out.println("\nComponetes F Conexas : ");
        /**
         * Mostrando os resutados*
         */
        List<List<Integer>> fConexas = cfc.getFconexas(g);
        System.out.println("F Conexas: " + fConexas);
        System.out.println("Número de F Conexas: " + fConexas.size());
    }
}
