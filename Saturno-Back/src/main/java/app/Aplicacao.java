package app;

import static spark.Spark.*;

import java.text.ParseException;

import dao.TarefaDAO;
import dao.UsuarioDAO;
import model.Tarefa;
import model.Usuario;
import service.Service;


public class Aplicacao {
	
	private static Service service = new Service();
	
    public static void main(String[] args) throws ParseException {
    	UsuarioDAO user = new UsuarioDAO();
    	TarefaDAO tarefa = new TarefaDAO();
        port(6789);
        
        //staticFiles.location("/public");
        
        //post("/produto/insert", (request, response) -> service.insert(request, response));
        tarefa.insert(new Tarefa(21212, "Alolololololoolaaaaa", "22/02/2023", "Teste", 23123));
        user.insert(new Usuario(231234, "Carlos", "Alberto", "marco", 'M', "Algo@hotmail.com", "22/02/1980", 0, 0));

        /*get("/produto/:id", (request, response) -> produtoService.get(request, response));
        
        get("/produto/list/:orderby", (request, response) -> produtoService.getAll(request, response));

        get("/produto/update/:id", (request, response) -> produtoService.getToUpdate(request, response));
        
        post("/produto/update/:id", (request, response) -> produtoService.update(request, response));
           
        get("/produto/delete/:id", (request, response) -> produtoService.delete(request, response));*/

             
    }
}