package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.bean.Perfume;

public interface PerfumeDAO extends GenericDAO<Perfume, Integer> {

	List<Perfume> listar();

}
