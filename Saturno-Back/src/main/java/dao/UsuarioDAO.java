package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO extends DAO {	
	public UsuarioDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Usuario usuario) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO usuario (codigo, login, nome, senha, genero, email, dataNasc, qtd_criada, qtd_finalizada) "
		               + "VALUES (" + usuario.getId() + ", '" + usuario.getLogin() + "', '"
		               + usuario.getNome() + "', '" + usuario.getSenha() + "', '" + usuario.getGenero() + "', '" + usuario.getEmail() + "', '" + usuario.getDataNasc() + "', " + usuario.getQtdCriadas() + ", " 
		               + usuario.getQtdFinalizadas() + ");";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Usuario get(int id) {
		Usuario usuario = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Usuario WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 usuario = new Usuario(rs.getInt("id"), rs.getString("login"), rs.getString("nome"), 
	                				   rs.getString("senha"), 
	        			               rs.getString("genero").toCharArray()[0],
	        			               rs.getString("email"), rs.getString("dataNasc"), rs.getInt("qtd_criadas"), rs.getInt("qtd_finalizadas"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	
	public List<Usuario> get() {
		return get("");
	}

	
	public List<Usuario> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Usuario> getOrderByLogin() {
		return get("login");		
	}
	
	
	public List<Usuario> getOrderByNome() {
		return get("nome");		
	}
	
	public List<Usuario> getOrderBydataNasc(){
		return get("dataNasc");
	}
	
	public List<Usuario> getOrderByQtdCriadas(){
		return get("qtd_criadas");
	}
	
	public List<Usuario> getOrderByQtdFinalizada(){
		return get("qtd_finalizada");
	}
	
	private List<Usuario> get(String orderBy) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Usuario u = new Usuario(rs.getInt("id"), rs.getString("login"), rs.getString("nome"), 
     				   rs.getString("senha"), 
		               rs.getString("genero").toCharArray()[0],
		               rs.getString("email"), rs.getString("dataNasc"), rs.getInt("qtd_criadas"), rs.getInt("qtd_finalizadas"));
	            usuarios.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	
	
	public boolean update(Usuario usuario) {
		boolean status = false;
		try {  
			String sql = "UPDATE usuario SET login = '" + usuario.getLogin() + "', "
					   + "nome = " + usuario.getNome() + ", " 
					   + "senha = " + usuario.getSenha() + ","
					   + "genero = " + usuario.getGenero() + ","
					   + "email = " + usuario.getEmail() + ","
					   + "dataNasc = " + usuario.getDataNasc() + ","
					   + "qtd_criadas = " + usuario.getQtdCriadas() + ","
					   + "qtd_finalizadas = " + usuario.getQtdFinalizadas() + ","
					   + "WHERE id = " + usuario.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}