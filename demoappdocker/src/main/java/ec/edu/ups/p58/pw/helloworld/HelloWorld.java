package ec.edu.ups.p58.pw.helloworld;

import java.util.List;

import javax.inject.Inject;
//import javax.json.Json;
//import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
//import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.opentracing.Traced;

import ec.edu.ups.p58.pw.helloworld.business.GestionCarros;
import ec.edu.ups.p58.pw.helloworld.model.Carro;


@Path("/")
public class HelloWorld {
	//@Inject
	//HelloService helloService;
	
	@Inject
	GestionCarros gestionCarros;

	/*@GET
	@Path("/json")
	@Traced(operationName = "hello-json")
	@Counted(description = "Contador saludo 1", absolute = true)
	@Timed(name = "saludo1-time", description = "Tiempo de procesamiento de saludo 1", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_JSON })
	@Operation(description = "Invocar a endpoint HelloWorld con respuesta en JSON", summary = "call getHelloWorldJSON")
	@APIResponse(responseCode = "200", description = "Saludo respuesta",
				content = @Content(mediaType = MediaType.APPLICATION_JSON,
					schema = @Schema(implementation = String.class)))
	public Response getHelloWorldJSON() {
		JsonObject resp = Json.createObjectBuilder().add("resultado", helloService.createHelloMessage("World")).build();
		return Response.ok(resp).build();
	}*/

	
	@POST
	@Path("/guardar")
	@Traced(operationName = "guardarCarro-json")
	@Counted(description = "Contador guardarCarro", absolute = true)
	@Timed(name = "guardar-time", description = "Tiempo de procesamiento de guardarCarro", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(description = "Invocar a endpoint guardarCarro con respuesta en JSON", summary = "call getGuardarCarroJSON")
	@APIResponse(responseCode = "200", description = "guardarCarro",
	content = @Content(mediaType = MediaType.APPLICATION_JSON,
		schema = @Schema(implementation = ErrorMessage.class)))
	public Response guardarCarro(Carro carro) {
		//JsonObject resp = Json.createObjectBuilder().add("resultado", helloService.guardarCarro(carro)).build();
		//return Response.ok(resp).build();
		try{
			this.gestionCarros.guardarCarro(carro);
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.CREATED)
					.entity(error)
					.build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
		
		
	}
	
	@PUT
	@Path("/actualizar")
	@Traced(operationName = "actualizarCarro-json")
	@Counted(description = "Contador actualizarCarro", absolute = true)
	@Timed(name = "actualizar-time", description = "Tiempo de procesamiento de actualizarCarro", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(description = "Invocar a endpoint actualizarCarro con respuesta en JSON", summary = "call getActualizarCarroJSON")
	@APIResponse(responseCode = "200", description = "actualizarCarro",
	content = @Content(mediaType = MediaType.APPLICATION_JSON,
		schema = @Schema(implementation = ErrorMessage.class)))
	public Response actualizarCarro(Carro carro) {
		try{
			this.gestionCarros.actualizarCarro(carro);
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.CREATED)
					.entity(error)
					.build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
		
	}
	
	
	@DELETE
	@Path("/eliminar")
	@Traced(operationName = "eliminarCarro-json")
	@Counted(description = "Contador eliminarCarro", absolute = true)
	@Timed(name = "eliminar-time", description = "Tiempo de procesamiento de eliminarCarro", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(description = "Invocar a endpoint eliminarCarro con respuesta en JSON", summary = "call getEliminarJSON")
	@APIResponse(responseCode = "200", description = "eliminarCarro",
	content = @Content(mediaType = MediaType.APPLICATION_JSON,
		schema = @Schema(implementation = ErrorMessage.class)))
	public Response eliminarCarro(@QueryParam("codigo") int codigo) {
		try{
			this.gestionCarros.borrarCarro(codigo);
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.CREATED)
					.entity(error)
					.build();
		}catch (Exception e) {
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
	}
	
	
	
	@GET
	@Path("/getPorCodigo")
	@Traced(operationName = "carroPorCodigo-json")
	@Counted(description = "Contador carroPorCodigo 1", absolute = true)
	@Timed(name = "carroPorCodigo-time", description = "Tiempo de procesamiento de carroPorCodigo 1", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(description = "Invocar a endpoint getPorCodigo con respuesta en JSON", summary = "call getCarroPorCodigoJSON")
	@APIResponse(responseCode = "200", description = "getCarroPorCodigo respuesta",
				content = @Content(mediaType = MediaType.APPLICATION_JSON,
					schema = @Schema(implementation = Carro.class)))
	public Response getCarroPorCodigo(@QueryParam("codigo") int codigo) {
		try{
			Carro carro = this.gestionCarros.getCarroPorCodigo(codigo);
			return Response.ok(carro).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "Carro no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	
	
	@GET
	@Path("/getCarros")
	@Traced(operationName = "getCarros-json")
	@Counted(description = "Contador getCarros 1", absolute = true)
	@Timed(name = "getCarros-time", description = "Tiempo de procesamiento de getCarros 1", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_JSON })
	@Operation(description = "Invocar a endpoint getCarros con respuesta en JSON", summary = "call getCarrosJSON")
	@APIResponse(responseCode = "200", description = "getCarros respuesta",
				content = @Content(mediaType = MediaType.APPLICATION_JSON,
					schema = @Schema(implementation = String.class, type = SchemaType.ARRAY)))
	@APIResponse(responseCode = "404", description = "No se registran carros",
				content = @Content(mediaType = MediaType.APPLICATION_JSON,
					schema = @Schema(implementation = ErrorMessage.class)))
	public Response getCarros(){
		List<Carro> carros = this.gestionCarros.getCarros();
		if(carros.size()>0)
			return Response.ok(carros).build();
		
		ErrorMessage error = new ErrorMessage(6, "No se registran carros");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
	}
	
	/*@GET
	@Path("/xml")
	@Traced(operationName = "hello-xml")
	@Counted(description = "Contador saludo 2", absolute = true)
	@Timed(name = "saludo2-time", description = "Tiempo de procesamiento de saludo 2", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_XML })
	@Operation(description = "Invocar a endpoint HelloWorld con respuesta en XML", summary = "call getHelloWorldXML")
	@APIResponse(responseCode = "200", description = "Saludo respuesta",
				content = @Content(mediaType = MediaType.APPLICATION_XML,
					schema = @Schema(implementation = String.class)))
	public String getHelloWorldXML() {
		return "<xml><result>" + helloService.createHelloMessage("World") + "</result></xml>";
	}*/
	
	/*@GET
	@Path("/echo/{texto}")
	@Traced(operationName = "echo-operation")
	@Counted(description = "Contador echo", absolute = true)
	@Timed(name = "echo-time", description = "Tiempo de procesamiento de echo", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_JSON })
	@Operation(description = "Simple echo con respuesta en formato JSON", summary = "echo jaxrs")
	@APIResponse(responseCode = "200", description = "Echo respuesta",
			content = @Content(mediaType = MediaType.APPLICATION_JSON,
			schema = @Schema(implementation = String.class)))
	public Response replyEcho(
			@Parameter(description = "Texto para respuesta de echo", required = true)
			@PathParam("texto") String texto) {
		JsonObject resp = Json.createObjectBuilder().add("echo",  texto ).build();
		return Response.ok(resp).build();
	}
	
	@GET
	@Path("/cliente1")
	@Traced(operationName = "cliente-simple-operation")
	@Produces(MediaType.TEXT_PLAIN)
	@Operation(description = "Cliente simple JAX RS", summary = "cliente jaxrs")
	@APIResponse(responseCode = "200", description = "Cliente respuesta",
			content = @Content(mediaType = MediaType.TEXT_PLAIN,
			schema = @Schema(implementation = String.class)))
	public String clienteServicio() {
		Client client = ClientBuilder.newClient();
		String response = client.target("http://localhost:8080/helloworld-mp/rest/json").request().get(String.class);
		return response;
	}*/

}
