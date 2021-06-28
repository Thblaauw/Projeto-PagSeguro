package com.src;
import java.util.Arrays;
import java.util.HashMap;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.exceptions.UsuarioIncompletoException;

public class Usuario {
	private ObjectId id;
	private String nome;
	private int numero_conta;
	private int agencia;
	private boolean cheque_especial_liberado;
	private String saldo;
	private String cheque_especial;
	private String taxa;
	
	public Usuario(String nome, 
					int conta, 
					int agencia,
					boolean cheque,
					String saldo,
					String cheque_valor,
					String taxa) {
		
		this.id = new ObjectId();
		this.nome = nome;
		this.numero_conta = conta;
		this.agencia = agencia;
		this.cheque_especial_liberado = cheque;
		this.saldo = saldo;
		this.cheque_especial = cheque_valor;
		this.taxa = taxa;
	}
	
	public Usuario(Document d) {
		this.id = d.getObjectId("_id");
		this.nome = d.getString("nome");
		this.numero_conta = d.getInteger("numero_conta");
		this.agencia = d.getInteger("agencia");
		this.cheque_especial_liberado = d.getBoolean("cheque_especial_liberado");
		this.saldo = d.getString("saldo");
		this.cheque_especial = d.getString("cheque_especial");
		this.taxa = d.getString("taxa");
	}
	
	public Document toDocument() throws UsuarioIncompletoException{
		Document doc = new Document();
		
		doc.append("_id", this.id);
		
		if(this.nome.isBlank())
			throw new UsuarioIncompletoException();
		doc.append("nome", this.nome);
		
		if(this.numero_conta == 0)
			throw new UsuarioIncompletoException();
		doc.append("numero_conta", this.numero_conta);
		
		if(this.agencia == 0)
			throw new UsuarioIncompletoException();
		doc.append("agencia", this.agencia);
		
		doc.append("cheque_especial_liberado", this.cheque_especial_liberado);
		
		if(this.saldo.isBlank())
			throw new UsuarioIncompletoException();
		doc.append("saldo", this.saldo);
		
		if(this.cheque_especial.isBlank())
			throw new UsuarioIncompletoException();
		doc.append("cheque_especial", this.cheque_especial);
		
		if(this.taxa.isBlank())
			throw new UsuarioIncompletoException();
		doc.append("taxa", this.taxa);
		
		
		
		return doc;
	}

	public HashMap<String, String> print(){
		HashMap<String, String> output = new HashMap<String, String>();
		
		output.put("nome", this.nome);
		
		StringBuilder agencia = new StringBuilder();
		agencia.append(Integer.toString(this.agencia)  + " / ");
		StringBuilder conta = new StringBuilder(Integer.toString(this.numero_conta));
		conta.insert(conta.length() - 1, '-');
		agencia.append(conta);
		output.put("numero_conta", agencia.toString());
		
		StringBuilder saldo = new StringBuilder("R$ " + this.saldo);
		saldo.insert(saldo.length()-2, ',');
		
		output.put("saldo", saldo.toString());
		
		if(this.cheque_especial_liberado)
			output.put("cheque_especial_liberado", "Liberado");
		else
			output.put("cheque_especial_liberado", "Nao Liberado");
		
		StringBuilder cheqE = new StringBuilder(this.cheque_especial);
		
		for(int i = 0; i < cheqE.length(); i++) {
			if(cheqE.charAt(i) == ',')
				cheqE.replace(i, i+1, "");
			else if(cheqE.charAt(i) == '.')
				cheqE.replace(i, i+1, "");
		}
		
		double cheq = Integer.parseInt(cheqE.toString());
		cheq /= 100.00f;
		
		StringBuilder tx = new StringBuilder(this.taxa);
		
		for(int i = 0; i < tx.length(); i++) {
			if(tx.charAt(i) == ',')
				tx.replace(i, i+1, "");
			else if(tx.charAt(i) == '.')
				tx.replace(i, i+1, "");
		}
		
		cheq *= (Double.parseDouble(tx.toString()) / 10000.00f);
		output.put("cheque_especial", Double.toString(cheq));
		
		return output;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero_conta() {
		return numero_conta;
	}

	public void setNumero_conta(int numero_conta) {
		this.numero_conta = numero_conta;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public boolean isCheque_especial_liberado() {
		return cheque_especial_liberado;
	}

	public void setCheque_especial_liberado(boolean cheque_especial_liberado) {
		this.cheque_especial_liberado = cheque_especial_liberado;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getCheque_especial() {
		return cheque_especial;
	}

	public void setCheque_especial(String cheque_especial) {
		this.cheque_especial = cheque_especial;
	}

	public String getTaxa() {
		return taxa;
	}

	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}
	
	public ObjectId getId() {
		return id;
	}
}
