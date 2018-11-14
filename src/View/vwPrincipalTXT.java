package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import Model.JogoIndivíduoNxN;
import Testes.Testes8x8;

import javax.swing.JTextArea;

@SuppressWarnings({ "serial", "unused" })
public class vwPrincipalTXT extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtTamanho;
	private JTextField txtNmeroDeGeracoes;
	private JTextField txtTaxadeMutacao;
	private JTextField txtTaxadeCross;
	private JTextField txtFO;
	private JScrollPane scrollPane = new JScrollPane();
	private JTextArea campoDeImpressao = new JTextArea();
	private ArrayList<JogoIndivíduoNxN> populacaoPai = new ArrayList<JogoIndivíduoNxN>();
	private ArrayList<JogoIndivíduoNxN> filhosGeracoes = new ArrayList<JogoIndivíduoNxN>();
	private JogoIndivíduoNxN solucao;
	private Random random = new Random();
	private int nGeracoes;
	private int custoOperacional = 0;
	private int cuzamentos, mutacao;
	protected String elitismo;
	private int j;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwPrincipalTXT frame = new vwPrincipalTXT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public vwPrincipalTXT() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("A.I. - Intelig\u00EAncia Artificial Oito Rainhas");
		setBounds(100, 100, 742, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblV = new JLabel("V: 1.2");
		lblV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		});
		lblV.setToolTipText("Versão do Software");
		lblV.setEnabled(false);
		lblV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblV.setBounds(670, 11, 46, 14);
		contentPane.add(lblV);
		
		JButton btnXForce = new JButton("X-Force");
		btnXForce.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnXForce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do {
					custoOperacional += filhosGeracoes.size();
					//Resetar População Iniciar
					populacaoPai.clear();
					for (int i = 0; i < filhosGeracoes.size(); i++) {
						populacaoPai.add(filhosGeracoes.get(i));
					}
					//Ordenação
					ordenacao(populacaoPai);
					//Selcionar, Cruzar, Mutar
					if (elitismo.equals("\nCom Elitismo.")) {
						selecaoComElitismo();
					} else {
						selecaoSemElitismo();
					}
					
				} while (procuraIndividuo(filhosGeracoes) == false);
				imprimirSolucao();
				btnXForce.setEnabled(false);
			}
		});
		btnXForce.setToolTipText("Usar For\u00E7a Bruta at\u00E9 encontrar um indiv\u00EDduo que atenda.");
		btnXForce.setBounds(314, 75, 89, 23);
		btnXForce.setVisible(false);
		contentPane.add(btnXForce);

		JButton btnCruzamento = new JButton("Cuzamento");
		btnCruzamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null,	"Você quer usar Elistismo ou não?") == 0) {
					cuzamentos = 0;
					mutacao = 0;
					selecaoComElitismo();
					ordenacao(populacaoPai);
					ordenacao(filhosGeracoes);
					elitismo = "\nCom Elitismo.";
					populaCampoFilhos();
					btnXForce.setVisible(true);	
					btnCruzamento.setEnabled(false);
					
				} else {
					cuzamentos = 0;
					mutacao = 0;
					selecaoSemElitismo();
					ordenacao(populacaoPai);
					ordenacao(filhosGeracoes);
					elitismo = "\nSem Elitismo.";
					populaCampoFilhos();
					btnXForce.setVisible(true);
					btnCruzamento.setEnabled(false);
					
				}
			}
		});
		btnCruzamento.setVisible(false);
		

		btnCruzamento.setToolTipText("Selecionar, Cruzar, Mutar.");
		btnCruzamento.setBounds(413, 75, 105, 23);
		contentPane.add(btnCruzamento);

		JButton btnAvaSel = new JButton("AvaCel");
		btnAvaSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ordenacao(populacaoPai);
				btnAvaSel.setEnabled(false);
				btnAvaSel.setToolTipText("Indivíduos avaliados e ordenados.");
				btnCruzamento.setVisible(true);
				populaCampo();

			}
		});
		btnAvaSel.setVisible(false);
		btnAvaSel
				.setToolTipText("Avalia\u00E7\u00E3o e Ordena\u00E7\u00E3o dos Indiv\u00EDduos");
		btnAvaSel.setBounds(528, 75, 89, 23);
		contentPane.add(btnAvaSel);

		JButton btnCriar = new JButton("Criar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < Integer.parseInt(txtTamanho.getText()); i++) {
					populacaoPai.add(new JogoIndivíduoNxN());
				}
				btnAvaSel.setVisible(true);
				btnCriar.setEnabled(false);
				btnCriar.setToolTipText("Polulação Inicial só cria uma vez, na inicialização do programa.");
				txtTamanho.setEnabled(false);
				//Travar Campos
				txtNmeroDeGeracoes.setEnabled(false);
				txtTaxadeCross.setEnabled(false);
				txtTaxadeMutacao.setEnabled(false);
			}
		});
		btnCriar.setToolTipText("Criar Popula\u00E7\u00E3o Inicial");
		btnCriar.setBounds(627, 75, 89, 23);
		contentPane.add(btnCriar);

		JLabel lblN = new JLabel("N =");
		lblN.setToolTipText("N\u00FAmeros de Rainhas");
		lblN.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblN.setEnabled(false);
		lblN.setBounds(10, 54, 38, 22);
		contentPane.add(lblN);

		JLabel lblT = new JLabel("T =");
		lblT.setToolTipText("Tamanho da Popula\u00E7\u00E3o");
		lblT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblT.setEnabled(false);
		lblT.setBounds(10, 76, 38, 22);
		contentPane.add(lblT);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setToolTipText("N\u00FAmeros de Rainhas");
		textField.setText("8");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		textField.setColumns(10);
		textField.setBounds(43, 57, 38, 20);
		contentPane.add(textField);

		txtTamanho = new JTextField();
		txtTamanho.setToolTipText("Tamanho da Popula\u00E7\u00E3o");
		txtTamanho.setText("50");
		txtTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		txtTamanho.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTamanho.setColumns(10);
		txtTamanho.setBounds(43, 78, 38, 20);
		contentPane.add(txtTamanho);

		JLabel lblNg = new JLabel("NG = ");
		lblNg.setToolTipText("N\u00FAmero de Gera\u00E7\u00F5es");
		lblNg.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNg.setEnabled(false);
		lblNg.setBounds(91, 54, 49, 22);
		contentPane.add(lblNg);

		JLabel lblTx = new JLabel("TX =");
		lblTx.setToolTipText("Taxa de Muta\u00E7\u00E3o");
		lblTx.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTx.setEnabled(false);
		lblTx.setBounds(91, 76, 49, 22);
		contentPane.add(lblTx);

		txtNmeroDeGeracoes = new JTextField();
		txtNmeroDeGeracoes.setToolTipText("N\u00FAmero de Gera\u00E7\u00F5es");
		txtNmeroDeGeracoes.setText("500");
		txtNmeroDeGeracoes.setHorizontalAlignment(SwingConstants.CENTER);
		txtNmeroDeGeracoes.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNmeroDeGeracoes.setColumns(10);
		txtNmeroDeGeracoes.setBounds(138, 54, 38, 20);
		contentPane.add(txtNmeroDeGeracoes);

		txtTaxadeMutacao = new JTextField();
		txtTaxadeMutacao.setToolTipText("Taxa de Muta\u00E7\u00E3o");
		txtTaxadeMutacao.setText("0.1");
		txtTaxadeMutacao.setHorizontalAlignment(SwingConstants.CENTER);
		txtTaxadeMutacao.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTaxadeMutacao.setColumns(10);
		txtTaxadeMutacao.setBounds(138, 75, 38, 20);
		contentPane.add(txtTaxadeMutacao);

		JLabel lblTc = new JLabel("TC =");
		lblTc.setToolTipText("Taxa de Crossover");
		lblTc.setHorizontalAlignment(SwingConstants.LEFT);
		lblTc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTc.setEnabled(false);
		lblTc.setBounds(186, 54, 49, 22);
		contentPane.add(lblTc);

		JLabel lblFO = new JLabel("FO =");
		lblFO.setToolTipText("Found, j\u00E1 encontrou?");
		lblFO.setHorizontalAlignment(SwingConstants.LEFT);
		lblFO.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFO.setEnabled(false);
		lblFO.setBounds(186, 73, 49, 22);
		contentPane.add(lblFO);

		txtTaxadeCross = new JTextField();
		txtTaxadeCross.setToolTipText("Taxa de Crossover");
		txtTaxadeCross.setText("0.9");
		txtTaxadeCross.setHorizontalAlignment(SwingConstants.CENTER);
		txtTaxadeCross.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTaxadeCross.setColumns(10);
		txtTaxadeCross.setBounds(234, 56, 38, 20);
		contentPane.add(txtTaxadeCross);

		txtFO = new JTextField();
		txtFO.setToolTipText("Situa\u00E7\u00E3o");
		txtFO.setText("?");
		txtFO.setHorizontalAlignment(SwingConstants.CENTER);
		txtFO.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFO.setEditable(false);
		txtFO.setColumns(10);
		txtFO.setBounds(234, 78, 38, 20);
		contentPane.add(txtFO);

		scrollPane.setBounds(10, 103, 706, 325);
		contentPane.add(scrollPane);
		campoDeImpressao.setWrapStyleWord(true);
		campoDeImpressao.setLineWrap(true);
		campoDeImpressao
				.setToolTipText("Campo de Impress\u00E3o dos Indiv\u00EDduos ");
		campoDeImpressao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		campoDeImpressao.setEditable(false);
		scrollPane.setViewportView(campoDeImpressao);
		campoDeImpressao
				.setText("                                                                      Problema das 8 Rainhas, By: Marcelo Vieira.\r\n");

		JLabel lblSistemasVox = new JLabel("Sistema VOX");
		lblSistemasVox
				.setToolTipText("Ver mais informa\u00E7\u00F5es do Software");
		lblSistemasVox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vwAbout ab = new vwAbout();
				ab.setVisible(true);
			}
		});
		lblSistemasVox
				.setFont(new Font("Vivaldi", Font.BOLD | Font.ITALIC, 39));
		lblSistemasVox.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemasVox.setBounds(282, 0, 338, 98);
		contentPane.add(lblSistemasVox);

	}

	protected void imprimirSolucao() {
		for (int i = 0; i < filhosGeracoes.size(); i++) {
			if (filhosGeracoes.get(i).getConflitos() == 0) {
				solucao = filhosGeracoes.get(i);
				break;
			}
		}
		vwSolucao imp = new vwSolucao();
		String info = "Média de Custo sem AG: "+ pontuar(175826)
				+"\nCusto Operacional com AG: " + pontuar(custoOperacional)
				+ elitismo + " Após: "+ custoOperacional / Integer.parseInt(txtNmeroDeGeracoes.getText().replaceAll(",", ".")) +" gerações.";
		imp.populaCampoFilhos(solucao, info);
		imp.setVisible(true);
	}
	private String pontuar(int n) {
		String aux = "";
		int p = String.valueOf(n).length() - 1;
		for (int i = 0; i < String.valueOf(n).length(); i++) {
			if (p % 3 == 0) {
				aux += String.valueOf(n).substring(i, i + 1) + ".";
			} else {
				aux += String.valueOf(n).substring(i, i + 1);
			}
			p--;
		}

		return aux;
	}
	public boolean procuraIndividuo(ArrayList<JogoIndivíduoNxN> pfilhosGeracoes) {
		for (int i = 0; i < pfilhosGeracoes.size(); i++) {
			if (pfilhosGeracoes.get(i).getConflitos() == 0) {
				return true;
			}
		}
		return false;		
	}
	protected void selecaoComElitismo() {		
		nGeracoes = Integer.parseInt(txtNmeroDeGeracoes.getText().replaceAll(",", "."));
		filhosGeracoes.clear();
		do {
			cruzar(populacaoPai.get(random.nextInt(populacaoPai.size()/2)), populacaoPai.get(random.nextInt(populacaoPai.size())));
			//cruzar(populacaoPai.get(0), populacaoPai.get(random.nextInt(populacaoPai.size())));
		} while (filhosGeracoes.size() != nGeracoes);
	}
	protected void selecaoSemElitismo() {
		nGeracoes = Integer.parseInt(txtNmeroDeGeracoes.getText().replaceAll(",", "."));
		filhosGeracoes.clear();
		do {
			cruzar(populacaoPai.get(random.nextInt(populacaoPai.size())), populacaoPai.get(random.nextInt(populacaoPai.size())));
		} while (filhosGeracoes.size() != nGeracoes);
	}
	private int randInter(int min, int max) {
		return min + (random.nextInt(max - min));

	}
	private void cruzar(JogoIndivíduoNxN pai1, JogoIndivíduoNxN pai2) {
		if (random.nextDouble() < Double.parseDouble(txtTaxadeCross.getText().replaceAll(",", "."))) {
			//Misturar material genético
			
			//Material genético do pai 1
			int i = random.nextInt(pai1.getMatriz().length);
			int genetico1 [] = new int [pai1.getMatriz().length];
			for (int j = 0; j < pai1.getMatriz().length; j++) {
				genetico1 [j] = pai1.getNum(i, j);
			}
			
			//Material genético do pai 2
			int ii = random.nextInt(pai2.getMatriz().length);
			int genetico2 [] = new int [pai2.getMatriz().length];
			for (int j = 0; j < pai2.getMatriz().length; j++) {
				genetico2 [j] = pai2.getNum(ii, j);
			}
			//Gerando dois filhos, com dois pais.
			
			JogoIndivíduoNxN filho1 = new JogoIndivíduoNxN(pai1, genetico2, i);
			JogoIndivíduoNxN filho2 = new JogoIndivíduoNxN(pai2, genetico1, ii);
			
			mutacao(filho1);
			mutacao(filho2);
			filho1.atualizaConflito();
			filhosGeracoes.add(filho1);
			filho2.atualizaConflito();
			filhosGeracoes.add(filho2);
			cuzamentos ++;
			cuzamentos ++;
		} else {
			mutacao(pai1);
			mutacao(pai2);
			pai1.atualizaConflito();
			pai2.atualizaConflito();
			filhosGeracoes.add(pai1);
			filhosGeracoes.add(pai2);
		}
	}

	private void mutacao(JogoIndivíduoNxN filho) {
		if (random.nextDouble() < Double.parseDouble(txtTaxadeMutacao.getText().replaceAll(",", "."))) {
			mutacao++;
			for (int m = 0; m < random.nextInt(filho.getMatriz().length); m++) {
				int posi = random.nextInt(filho.getMatriz().length);
				for (int i = 0; i < filho.getMatriz().length; i++) {
					if (posi == i) {
						filho.setNum(posi, i, 1);
					} else {
						filho.setNum(posi, i, 0);
					}
				}
			}
		}
	}

	protected void ordenacao(ArrayList<JogoIndivíduoNxN> lista) {
		HeapSort.sort(lista);
	}

	private void populaCampo() {
		campoDeImpressao.setText("                                                                      Problema das 8 Rainhas, By: Marcelo Vieira.\r\n");
		int menor = populacaoPai.get(0).getConflitos();
		String campodeImpressaoFull = campoDeImpressao.getText();
		for (int i = 0; i < populacaoPai.size(); i++) {
			campodeImpressaoFull += populacaoPai.get(i).toString(i);
			if (populacaoPai.get(i).getConflitos() < menor) {
				menor = populacaoPai.get(i).getConflitos();
			}
		}
		if (menor == 0) {
			txtFO.setText(":)");
		} else if (menor == 1) {
			txtFO.setText(":|");
		} else {
			txtFO.setText(":(");
		}
		campoDeImpressao.setText(campodeImpressaoFull);
	}
	protected void populaCampoFilhos() {
		String info = "Número de Indivíduos: "+ txtNmeroDeGeracoes.getText() + "."
				+"\nCuzamentos: " + cuzamentos + ". " + (cuzamentos * 100) / Integer.parseInt(txtNmeroDeGeracoes.getText()) + "%."
				+ "\nMutações: " + mutacao + ". " + (mutacao * 100) / Integer.parseInt(txtNmeroDeGeracoes.getText()) + "%."
				+ elitismo;
		ordenacao(filhosGeracoes);
		vwFilhos filhos = new vwFilhos();
		filhos.populaCampoFilhos(filhosGeracoes, info);
		filhos.setVisible(true);

	}
}
