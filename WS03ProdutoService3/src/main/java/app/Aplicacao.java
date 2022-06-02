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
        
        get("/inserirUsuario/:id/:nome/:email/:senha", (request, response) -> service.insert(request, response));
        
        get("/login/:email/:senha", (request, response) -> service.login(request, response));
        
        get("/logOut", (request, response) -> service.logOut());

        //get("/produto/:id", (request, response) -> Service.get(request, response));
        /*
        get("/produto/list/:orderby", (request, response) -> produtoService.getAll(request, response));

        get("/produto/update/:id", (request, response) -> produtoService.getToUpdate(request, response));
        
        post("/produto/update/:id", (request, response) -> produtoService.update(request, response));
           
        get("/produto/delete/:id", (request, response) -> produtoService.delete(request, response));*/

             
    }
}