package com.mongodb;

import com.exceptions.UsuarioIncompletoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.Filters.*;

import com.src.Usuario;

import org.bson.Document;

import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;




public class Connection {
	
	private String connectionString = "mongodb+srv://thomas:Heyyou123!@cluster0.0ql7h.mongodb.net/PagSeguro.PagSeguro?retryWrites=true&w=majority";
	private MongoClient mongoClient;
	private MongoCollection<Document> usuarios;
	
    
    
    public Connection() {
    	//Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
    	try  {
    		MongoClient mongoClient = MongoClients.create(connectionString);
        	this.mongoClient = mongoClient;
        	List<String> databases = mongoClient.listDatabaseNames().into(new ArrayList<>());
        	usuarios = mongoClient.getDatabase("PagSeguro").getCollection("PagSeguro");
        	//System.out.println(databases);
        }catch(Exception e) {}
    }
    
    public void inserirUsuario(Usuario u) {
    	try {
    		usuarios.insertOne(u.toDocument());
    	}catch(Exception e) {}
    }
    
    public List<Usuario> buscarUsuarioPorConta(int filtro) {
    	FindIterable<Document> cursor = usuarios.find(Filters.eq("numero_conta", filtro));
    	
    	List<Usuario> output = new ArrayList<Usuario>();
    	for(Document d : cursor) {
    		output.add(new Usuario(d));
    	}
    	
    	return output;
    }

    public List<Usuario> buscarUsuarios(String ...args) {
    	FindIterable<Document> cursor;
    	if (args.length == 1) {
			cursor = usuarios.find(Filters.in("nome", args[0]));
		}
		else if(args.length == 2) {
			cursor = usuarios.find(Filters.and(Filters.in("Nome", args[0]),
												Filters.in("agencia", Integer.parseInt(args[1]))));
		}
		else
			return null;
    	
		List<Usuario> output = new ArrayList<Usuario>();
    	for(Document d : cursor) {
    		output.add(new Usuario(d));
    	}
    	
    	return output;
    }
    
	public List<Usuario> buscarUsuariosComCheque(boolean cheque, String ...args ) {
		FindIterable<Document> cursor;
		if(cheque) {
			if(args.length == 0) {
				cursor = usuarios.find(Filters.eq("cheque_especial_liberado", cheque));
			}
			if (args.length == 1) {
				cursor = usuarios.find(Filters.and(Filters.in("nome", args[0]),
														Filters.eq("cheque_especial_liberado", cheque)));
			}
			else if(args.length == 2) {
				cursor = usuarios.find(Filters.and(Filters.in("nome", args[0]),
													Filters.eq("cheque_especial_liberado", cheque), 
													Filters.in("agencia", Integer.parseInt(args[1]))));
			}
			else {
				return null;
			}
		}
		else {
			if (args.length == 1) {
				cursor = usuarios.find(Filters.in("nome", args[0]));
			}
			else if(args.length == 2) {
				cursor = usuarios.find(Filters.and(Filters.in("Nome", args[0]),
													Filters.in("agencia", Integer.parseInt(args[1]))));
			}
			else
				return null;
		}
		
		List<Usuario> output = new ArrayList<Usuario>();
    	for(Document d : cursor) {
    		output.add(new Usuario(d));
    	}
    	
    	return output;
	}//end busca
	
	public void updateDeUsuario(Usuario u) {

			usuarios.updateOne(new Document("_id", u.getId()), 
					Updates.combine(
					Updates.set("nome", u.getNome()),
					Updates.set("numero_conta", u.getNumero_conta()),
					Updates.set("agencia", u.getAgencia()),
					Updates.set("cheque_especial_liberado", u.isCheque_especial_liberado()),
					Updates.set("saldo", u.getSaldo()),
					Updates.set("cheque_especial", u.getCheque_especial()),
					Updates.set("taxa", u.getTaxa())));
		
	    }
	 
	public void deletarUsuario(Usuario u) {
			usuarios.deleteOne(Filters.eq("_id", u.getId()));
		}

}//end class
