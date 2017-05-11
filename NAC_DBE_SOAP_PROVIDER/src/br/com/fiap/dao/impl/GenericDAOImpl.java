package br.com.fiap.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.com.fiap.dao.GenericDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.exception.IdNotFoundException;

public class GenericDAOImpl<T,K> implements GenericDAO<T, K>{

	protected EntityManager em;
	
	//.class da entidade (usado na busca)
	private Class<T> classe;
	
	//Construtor
	@SuppressWarnings("unchecked")
	public GenericDAOImpl(EntityManager em) {
		this.setEm(em);
		classe = (Class<T>) ((ParameterizedType)getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public void cadastrar(T entity) {
		getEm().persist(entity);
	}

	@Override
	public void alterar(T entity) {
		getEm().merge(entity);
	}

	@Override
	public void remover(K codigo) throws IdNotFoundException {
		T entidade = pesquisar(codigo);
		if (entidade == null){
			throw new IdNotFoundException("Entidade não encontrada");
		}
		getEm().remove(entidade);
	}

	@Override
	public T pesquisar(K codigo) {
		return getEm().find(classe, codigo);
	}
	
	public void salvar() throws DBException{
		try {
			getEm().getTransaction().begin();
			getEm().getTransaction().commit();
		}catch(Exception e){
			if (getEm().getTransaction().isActive())
				getEm().getTransaction().rollback();
			throw new DBException("Erro no commit", e);
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
