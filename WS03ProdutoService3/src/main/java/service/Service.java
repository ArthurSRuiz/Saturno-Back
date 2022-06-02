package service;

import java.util.Scanner;

import org.eclipse.jetty.util.security.Credential.MD5;

import java.time.LocalDate;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class Service {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DESCRICAO = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	private Usuario usuarioCorrente = null;
	
	public static String criptografar(String pwd) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
        if (md != null) {
            return new String(hexCodes(md.digest(pwd.getBytes())));
        }
        return null;
}
	
	private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;

        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 2,
                                    hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
}
	
	
	public Object insert(Request request, Response response) throws ParseException, NoSuchAlgorithmException{
		String id = request.params(":id");
		String nome = request.params(":nome");
		String senha = request.params(":senha");
		char genero = request.params(":genero").charAt(0);
		String email = request.params(":email");
		String dataNasc = request.params(":dataNasc");
		
		String resp = "";
		
		Usuario usuario = new Usuario(id, nome, criptografar(senha), 'N', email, "01/01/1900", 0, 0);
		
		if(usuarioDAO.insert(usuario) == true) {
            resp = "Usuario (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Usuario (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		return resp;
	}
	
	public Object login(Request request, Response response) throws ParseException, NoSuchAlgorithmException {
		String senha = request.queryParams(":senha");
		String email = request.queryParams(":email");
		String resp = "";
		
		usuarioCorrente  = usuarioDAO.Login(email, criptografar(senha));
		
		if(usuarioCorrente != null) {
			resp = "Logado com sucesso";
		}
		else {
			resp = "Email ou senha Incorrentos";
		}
			
		return resp;
	}
	
	public String logOut() {
		usuarioCorrente = null;
		return "Deslogado com sucesso!";
	}
}

	
	