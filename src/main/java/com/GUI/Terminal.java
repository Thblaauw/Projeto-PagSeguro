package com.GUI;


import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.src.Controller;
import com.src.Usuario;

public class Terminal {
	
	String[] busca = {"", "", ""};
	int menu_selecao;
	Scanner scan;
	
	Controller cont;
	
	String menu = "Bem Vindo a busca do PagSeguro: " + '\n' +
			"1 - Atualizar termos de busca:" + '\n' +
			"2 - Buscar" + '\n' +
			"3 - Buscar usuario por conta";
	
	String menu_atualizacao = "Atualizacao de termos de busca: " + '\n' +
			"1 - Nome:" + '\n' +
			"2 - Agenca" + '\n' +
			"3 - Cheque Especial Liberado";
	
	String busca_conta = "Por favor entre o numero da conta (somente numeros): ";
	
	public Terminal(Controller c) {
		cont = c;
		menu_selecao = 0;
		scan = new Scanner(System.in);
		
		//System.out.println(menu);
	}
	
	public void printBusca() {
		System.out.println("Termos de busca:");
		System.out.println("Nome: " + busca[0]);
		System.out.println("Agencia: " + busca[1]);
		System.out.println("Cheque Especial Liberado: " + busca[2]);
	}
	
	public int printMenu() {
		switch(menu_selecao) {
		case 0:
			System.out.println(menu);
			break;
			
		case 1:
			System.out.println(menu_atualizacao);
			break;
		case 2:
			this.printResultados(cont.buscarUsuarios(busca));
			break;
		}
		
		menu_selecao = (int) scan.nextLong();
		
		return 0;
	}
	
	public void printResultados(List<Usuario> l) {
		System.out.println("------------------------------");
		for(Usuario u : l) {
			HashMap<String, String> temp = u.print();
			for(String key : temp.keySet()) {
				System.out.println(key + ": " + temp.get(key));
			}
			System.out.println();
		}
		System.out.println("------------------------------");
	}
}
