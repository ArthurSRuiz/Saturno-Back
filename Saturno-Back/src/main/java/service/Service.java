package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class Service {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_DESCRICAO = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	
	public Object insert(Request request, Response response) throws ParseException {
		String login = request.queryParams("login");
		String nome = request.queryParams("nome");
		String senha = request.queryParams("senha");
		char genero = request.queryParams("genero").charAt(0);
		String email = request.queryParams("email");
		String dataNasc = request.queryParams("data nascimento");
		
		String resp = "";
		
		Usuario usuario = new Usuario(-1, login, nome, senha, genero, email, dataNasc, 0, 0);
		
		if(usuarioDAO.insert(usuario) == true) {
            resp = "Usuario (" + login + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Usuario (" + login + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}

	
	