package com.src;

import java.awt.event.ActionListener;
import java.util.List;

import com.GUI.Terminal;
import com.mongodb.Connection;

public class Controller{
	
	Connection database;
	Terminal term;
	//int menu_selecao;
	
	public Controller() {
		database = new Connection();
		term = new Terminal(this);
	}
	
	public void renderMenu() {
		int sel = 0;
		
		while(sel == 0)
			sel = term.printMenu();
	}
	
	public List<Usuario> buscarUsuarios(String[] busca){
		if(busca[2].length() == 0) {
			return database.buscarUsuarios(busca[0], busca[1]);
		}
		else {
			if(busca[2] == "Liberado")
				return database.buscarUsuariosComCheque(true, busca[0], busca[1]);
			else
				return database.buscarUsuariosComCheque(false, busca[0], busca[1]);
		}
	}

}
