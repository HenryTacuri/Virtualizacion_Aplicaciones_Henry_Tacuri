package ec.edu.ups.p58.pw.helloworld.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.p58.pw.helloworld.model.Carro;

@Stateless
public class CarroDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Carro carro) {
		em.persist(carro);
	}
	
	public void update(Carro carro) {
		em.merge(carro);
	}
	
	public void remove(int codigo) {
		Carro carro = em.find(Carro.class, codigo);
		em.remove(carro);
	}
	
	public Carro read(int codigo) {
		Carro carro = em.find(Carro.class, codigo);
		return carro;
	}
	
	public List<Carro> getAll(){
		String jpql = "SELECT c FROM Carro c";
		Query q = em.createQuery(jpql, Carro.class);
		return q.getResultList();
	}
	
	public Carro getCarroPorCodigo(int codigo){
		String jpql = "SELECT c FROM Carro c WHERE c.codigo = :codigo";
		Query q = em.createQuery(jpql, Carro.class);
		q.setParameter("codigo", codigo);
		List<Carro> carros = q.getResultList();
		if(carros.size()>0)
			return carros.get(0);
		return null;
	}
	
}
