package fesa.ec;

import static spark.Spark.*;

import java.util.Date;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

/* Referências
 * https://mongodb.github.io/mongo-java-driver/3.1/driver/tutorials/perform-write-operations/
 * https://sparkjava.com/documentation#routes
 * 
 * Teste do serviço:
 * http://localhost:4567/newdoc/Corinthians
 * 
 * Note que criamos a pasta /libs onde colocamos as bibliotecas *.jar e adicionamos ao BuildPath do projeto...
 * 
 * Tutorial:
 * https://www.baeldung.com/spark-framework-rest-api
 * 
 */

public class Main {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		get("/hello", (req, res) -> "Hello World - " + new Date());
		get("/test/:name", (req, res) -> "out: " + req.params(":name"));
		
			get("/newdoc/:name", (req, res) -> { 
		
			
			MongoClient mongoClient = MongoClients.create(); // Conexão
			MongoDatabase database = mongoClient.getDatabase("fesa"); // Banco de Dados - Schema
			MongoCollection<Document> collection = database.getCollection("ec"); // Collection - Table
			
			Document document = new Document("chk", "Check Doc")  // Documento JSON - Registros...
					   .append("name", req.params(":name"))
		               .append("now", new Date()) 
		               .append("code", 3)
		               .append("status", "ok");

			collection.insertOne(document);
			return "ok";
			
		});
	}

}
