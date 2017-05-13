package br.com.fiap.main;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.bo.PerfumeBOStub;
import br.com.fiap.bo.PerfumeBOStub.Buscar;
import br.com.fiap.bo.PerfumeBOStub.BuscarResponse;
import br.com.fiap.bo.PerfumeBOStub.Cadastrar;
import br.com.fiap.bo.PerfumeBOStub.Listar;
import br.com.fiap.bo.PerfumeBOStub.ListarResponse;
import br.com.fiap.bo.PerfumeBOStub.Perfume;

public class View {

	public static void main(String[] args) throws Exception{
		PerfumeBOStub pbo = new PerfumeBOStub();
		int op = 0;
		op = Integer.parseInt(JOptionPane.showInputDialog("O que deseja fazer? \n 1-Cadastrar \n 2-Buscar \n 3-Listar"));
		
		if(op == 1){
		Perfume p  = new Perfume();
		
		p.setNome(JOptionPane.showInputDialog("Digite o nome do perfume"));
		p.setValor(Float.parseFloat(JOptionPane.showInputDialog("Digite o valor")));
		
		Cadastrar cad = new Cadastrar();
		
		cad.setPerf(p);
		try{
		pbo.cadastrar(cad);
		System.out.println("Cadastrado com sucesso");
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		}else if (op == 2){
			Perfume p = new Perfume();
			
			p.setCod(Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto")));
			Buscar buscar = new Buscar();
			
			buscar.setCodigo(p.getCod());
			BuscarResponse pp =  pbo.buscar(buscar);
			Perfume pz = pp.get_return();
			
	
			System.out.println(pz.getCod());
			System.out.println(pz.getNome());
			System.out.println(pz.getValor());
			
			
		}else if (op == 3){
			
			Listar listar = new Listar();
			
			ListarResponse ls = pbo.listar(listar);
			Perfume[] lp = ls.get_return();
			for(Perfume pp: lp){
				System.out.println("Produtos cadastrados " + pp.getNome());
			}
			
			
			
		}else
			JOptionPane.showInputDialog("Valor invalido");
		

	}

}
