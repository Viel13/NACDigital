package br.com.fiap.view;

import org.apache.axis2.AxisFault;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.fiap.bo.PerfumeBOStub;
import br.com.fiap.bo.PerfumeBOStub.Buscar;
import br.com.fiap.bo.PerfumeBOStub.BuscarResponse;
import br.com.fiap.bo.PerfumeBOStub.Cadastrar;
import br.com.fiap.bo.PerfumeBOStub.Perfume;

public class deskView {

	protected Shell shell;
	private Text txtNome;
	private Text txtValor;
	private Text txtCodigo;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			deskView window = new deskView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label lblCadastroDeProdutos = new Label(shell, SWT.NONE);
		lblCadastroDeProdutos.setAlignment(SWT.CENTER);
		lblCadastroDeProdutos.setBounds(21, 10, 392, 15);
		lblCadastroDeProdutos.setText("Cadastro de Produtos");
		
		Label lblNome = new Label(shell, SWT.NONE);
		lblNome.setBounds(31, 31, 55, 15);
		lblNome.setText("Nome");
		
		Label lblValor = new Label(shell, SWT.NONE);
		lblValor.setBounds(31, 81, 55, 15);
		lblValor.setText("Valor");
		
		txtNome = new Text(shell, SWT.BORDER);
		txtNome.setBounds(21, 52, 126, 21);
		
		txtValor = new Text(shell, SWT.BORDER);
		txtValor.setBounds(21, 102, 126, 21);
		
		Label lblLblsuc = new Label(shell, SWT.NONE);
		lblLblsuc.setBounds(182, 105, 195, 15);
		
		Button btnCadastrar = new Button(shell, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PerfumeBOStub pbo = new PerfumeBOStub();
					Perfume p = new Perfume();	
					String nome = txtNome.getText();
					float valor = Float.parseFloat(txtValor.getText());
					
					p.setNome(nome);
					p.setValor(valor);
					
					Cadastrar cad = new Cadastrar();
					cad.setPerf(p);
					pbo.cadastrar(cad);
			lblLblsuc.setText("Cadastrado com sucesso");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblLblsuc.setText("Deu Ruim");
				
				}
			}
		});
		btnCadastrar.setBounds(239, 50, 75, 25);
		btnCadastrar.setText("Cadastrar");
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(10, 129, 403, 15);
		
		Label lblBuscarProduto = new Label(shell, SWT.NONE);
		lblBuscarProduto.setAlignment(SWT.CENTER);
		lblBuscarProduto.setBounds(123, 139, 176, 15);
		lblBuscarProduto.setText("Buscar Produto");
		
		Label lblCodigo = new Label(shell, SWT.NONE);
		lblCodigo.setBounds(31, 167, 55, 15);
		lblCodigo.setText("Codigo");
		
		txtCodigo = new Text(shell, SWT.BORDER);
		txtCodigo.setBounds(21, 188, 76, 21);
		
		Label lblNomeC = new Label(shell, SWT.NONE);
		lblNomeC.setBounds(217, 191, 120, 15);
		
		Label lblValorC = new Label(shell, SWT.NONE);
		lblValorC.setBounds(217, 215, 120, 15);
		
		Button btnConsultar = new Button(shell, SWT.NONE);
		btnConsultar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PerfumeBOStub pbo = new PerfumeBOStub();
					Buscar busca = new Buscar();
					busca.setCodigo(Integer.parseInt(txtCodigo.getText()));
					BuscarResponse bs = pbo.buscar(busca);
					Perfume p = bs.get_return();
					
					lblNomeC.setText(p.getNome());
					lblValorC.setText(String.valueOf(p.getValor()));
					
					//há produtos com o cod 1,2,20,22,23,24
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblNomeC.setText("Produto não encontrado");
				}
				
				
				
				
			}
		});
		btnConsultar.setBounds(22, 226, 75, 25);
		btnConsultar.setText("Consultar");
		
		
		
		

	}
}
