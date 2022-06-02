package app;

import static spark.Spark.*;

import java.text.ParseException;

import spark.*;
import service.Service;


public class Aplicacao {
	
	private static Service service = new Service();
	
    public static void main(String[] args) throws ParseException {
        port(6790);
        after((Filter) (request,response) -> {
			response.header("Access-Control-Allow-Origin","*");
			response.header("Access-Control-Allow-Methods", "GET");
			response.header("Access-Control-Allow-Methods", "POST");
		});
        
        get("/inserirUsuario/:id/:nome/:email/:senha/:genero/:dataNasc", (request, response) -> service.insertUsuario(request, response));
        
        get("/updateUsuario/:id/:nome/:email/:senha/:genero/:dataNasc", (request, response) -> service.updateUsuario(request, response));
        
        get("/deletarUsuario/:id", (request, response) -> service.deleteUsuario(request, response));
        
        get("/inserirTarefa/:id/:nome/:descricao/:data_limite/:idusuario", (request, response) -> service.insertTarefa(request, response));
        
        get("/updateTarefa/:id/:nome/:descricao/:data_limite/:idusuario", (request, response) -> service.updateTarefa(request, response));
        
        get("/deletarTarefa/:id", (request, response) -> service.deleteTarefa(request, response));
        
        get("/login/:email/:senha", (request, response) -> service.login(request, response));
        
        get("/getTarefasUsuario/:id", (request, response) -> service.getTarefabyUserId(request, response));
        
        get("/logOut", (request, response) -> service.logOut());             
    }
}