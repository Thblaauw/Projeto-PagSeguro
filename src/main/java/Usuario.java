import org.bson.Document;

import com.exceptions.UsuarioIncompletoException;

public class Usuario {
	private String nome;
	private int numero_conta;
	private int agencia;
	private boolean cheque_especial_liberado;
	private String saldo;
	private String cheque_especial;
	private String taxa;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, 
					int conta, 
					int agencia,
					boolean cheque,
					String saldo,
					String cheque_valor,
					String taxa) {
		
		this.nome = nome;
		this.numero_conta = conta;
		this.agencia = agencia;
		this.cheque_especial_liberado = cheque;
		this.saldo = saldo;
		this.cheque_especial = cheque_valor;
		this.taxa = taxa;
	}
	
	public Document toDocument() throws UsuarioIncompletoException{
		Document doc = new Document();
		
		if(this.nome.isBlank())
			throw new UsuarioIncompletoException();
		doc.append("nome", this.nome);
		
		if(this.numero_conta != 0)
			throw new UsuarioIncompletoException();
		doc.append("numero_conta", this.numero_conta);
		
		if(this.agencia != 0)
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
}
