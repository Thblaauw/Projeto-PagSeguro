package com.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class View implements ActionListener{
	
	JButton busca;
	JPanel resultados;
	
	public View(){
        JFrame frame = new JFrame("PagSeguro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);

        
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Nome");
        JTextField tf = new JTextField(50); // accepts up to 10 characters
        //JButton send = new JButton("Send");
       // JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.setBounds(0,0,1000, 50);
        //panel.add(send);
        //panel.add(reset);
        
        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("Agencia");
        JTextField tf2 = new JTextField(50); // accepts up to 10 characters
        //JButton send2 = new JButton("Send");
        //JButton reset2 = new JButton("Reset");
        panel2.add(label2); // Components Added using Flow Layout
        panel2.add(tf2);
        panel2.setBounds(0, 50, 1000, 50);
        //panel2.add(send2);
        //panel2.add(reset2);
        
        JPanel panel3 = new JPanel();
        JLabel label3 = new JLabel("Cheque especial liberado");
        JTextField tf3 = new JTextField(50); // accepts up to 10 characters
        //JButton send3 = new JButton("Send");
        //JButton reset3 = new JButton("Reset");
        panel3.add(label3); // Components Added using Flow Layout
        panel3.add(tf3, BorderLayout.CENTER);
        //panel3.add(send3);
        //panel3.add(reset3);
        
        JPanel panel4 = new JPanel();
        busca = new JButton("Pesquisar");
        busca.addActionListener(this);
        panel4.add(busca);
        
        panel.setBackground(Color.BLUE);
        panel2.setBackground(Color.GREEN);
        panel3.setBackground(Color.GRAY);
        panel4.setBackground(Color.PINK);
        //panel.setBackground(Color.BLUE);
        
        JPanel buscaPanel = new JPanel();
        buscaPanel.add(panel);
        buscaPanel.add(panel2);
        buscaPanel.add(panel3);
        buscaPanel.add(panel4);
        
        buscaPanel.setBackground(Color.CYAN);
        
        buscaPanel.setSize(1000, 500);
        //buscaPanel.setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel();
        mainPanel.add(buscaPanel, BorderLayout.NORTH);
        mainPanel.setSize(1000, 900);
        mainPanel.setBackground(Color.YELLOW);
        
        resultados = new JPanel();
        resultados.setBackground(Color.ORANGE);
        resultados.setBounds(0, 200, 1000, 700);
        
        mainPanel.add(resultados, BorderLayout.CENTER);
        
        
        
        frame.getContentPane().add( mainPanel);

        JTextArea textArea = new JTextArea(10,20);
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        
        frame.setVisible(true);
     }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==busca) {
			System.out.println("Busca");
			resultados.setBackground(Color.DARK_GRAY);
		}
	}
}
