package ec.edu.ups.p58.pw.helloworld.business;

import io.opentracing.Span;
import io.opentracing.Tracer;
import ec.edu.ups.p58.pw.helloworld.dao.CarroDAO;
import ec.edu.ups.p58.pw.helloworld.model.Carro;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.eclipse.microprofile.opentracing.Traced;

@Stateless
public class GestionCarros {

	@Inject
	private CarroDAO daoCarro;

	@Inject
    private Tracer tracer;
	
	@Traced
	public void guardarCarro(Carro carro) {
		Span prepareGuardarCarroSpan = tracer.buildSpan("prepare-guardarCarro-service").start();
		Carro car = daoCarro.read(carro.getCodigo());
		if (car != null){
			daoCarro.update(carro);
		}else {
			daoCarro.insert(carro);
		}
		prepareGuardarCarroSpan.finish();
	}

	@Traced
	public void actualizarCarro(Carro carro) throws Exception {
		Span prepareActualizarCarroSpan = tracer.buildSpan("prepare-actualizarCarro-service").start();
		Carro car = daoCarro.read(carro.getCodigo());
		if (car != null){
			daoCarro.update(carro);
		}else {
			throw new Exception("Carro no existe");
		}
		prepareActualizarCarroSpan.finish();
	}

	@Traced
	public Carro getCarroPorCodigo(int codigo) throws Exception{
		
		if(codigo<0)
			throw new Exception("Codigo incorrecto");
			
		return daoCarro.getCarroPorCodigo(codigo);
	}

	@Traced
	public void borrarCarro(int codigo){
		Span prepareActualizarCarroSpan = tracer.buildSpan("prepare-eliminarCarro-service").start();
		daoCarro.remove(codigo);
		prepareActualizarCarroSpan.finish();
	}
	
	@Traced
	public List<Carro> getCarros(){
		return daoCarro.getAll();
	}
	
}
