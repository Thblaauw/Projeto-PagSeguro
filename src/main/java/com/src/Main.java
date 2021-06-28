package com.src;

import java.util.List;

import com.GUI.View;
import com.mongodb.Connection;

public class Main {
	
	public static void main(String[] args) {
	    	
		//Connection con = new Connection();
		//List<Usuario> u = con.buscarUsuarioPorConta(1000);
		//u.get(0).print();
		Controller cont = new Controller();
		cont.renderMenu();
	    	
	}//main
}//end class
