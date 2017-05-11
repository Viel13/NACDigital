package br.com.fiap.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.bean.Perfume;
import br.com.fiap.dao.PerfumeDAO;
import br.com.fiap.dao.impl.PerfumeDAOImpl;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.EntityManagerFactorySingleton;

public class PerfumeBO {
	private EntityManagerFactory fabrica = 
			EntityManagerFactorySingleton.getInstance();
	
	public void cadastrar(Perfume perf){
		EntityManager em = fabrica.createEntityManager();
		PerfumeDAO dao = new PerfumeDAOImpl(em);
		dao.cadastrar(perf);
		try {
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	public Perfume buscar(int codigo){
		EntityManager em = fabrica.createEntityManager();
		PerfumeDAO dao = new PerfumeDAOImpl(em);
		Perfume perf = dao.pesquisar(codigo);
		em.close();
		return perf;
	}
	
	public List<Perfume> listar(){
		EntityManager em = fabrica.createEntityManager();
		PerfumeDAO dao = new PerfumeDAOImpl(em);
		List<Perfume> lista = dao.listar();
		em.close();
		return lista;
	}
		
	
}
