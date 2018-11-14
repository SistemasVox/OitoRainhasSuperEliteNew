package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import Model.JogoIndivíduoNxN;

@SuppressWarnings("serial")
public class vwPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtTamanho;
	private JTextField txtNmeroDeGeraes;
	private JTextField txtTaxadeMutacao;
	private JTextField txtTaxadeCross;
	private JTextField txtI;
	private JScrollPane scrollPane = new JScrollPane();// Barra de rolagem da Tabela de Jogos.
	private JTable table = new JTable();// Tebela do vwPerceptron.
	private static DefaultTableModel model;// Tabela de Jogos.
	private int vetors[][];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwPrincipal frame = new vwPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vwPrincipal() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("A.I. - Intelig\u00EAncia Artificial Oito Rainhas");
		setBounds(100, 100, 742, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        JLabel lblSistemasVox = new JLabel("Sistema VOX");// Criando a LOGO Principal do Proframa.
        lblSistemasVox.setToolTipText("Ver mais informa\u00E7\u00F5es do Software");// Definindo uma descrição para a LOGO.
        // Criando um Evento para quando clicar na LOGO.
        lblSistemasVox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                vwAbout ab = new vwAbout();// Criando um Objeto do tipo About.
                ab.setVisible(true);// Tornando a JANELA About VISÍVEL.
            }
        });
        lblSistemasVox.setFont(new Font("Vivaldi", Font.BOLD | Font.ITALIC, 39));// Definindo o Tipo de Letra FONTE da LOGO.
        lblSistemasVox.setHorizontalAlignment(SwingConstants.CENTER);// Definindo a Letra para ser Centralizada.
        lblSistemasVox.setBounds(282, 0, 338, 98);// Definindo TAMANHO da LOGO.
        contentPane.add(lblSistemasVox);// Colocando a LOGO no PAINEL = JANELA.
        
        JLabel lblV = new JLabel("V: 1.0");// Definindo texto para Versão do SOFTWARE.
        lblV.setToolTipText("Versão do Software");;// Definindo uma descrição para Versão do SOFTWARE.
        lblV.setEnabled(false);// Desabilitando a seleção do mesmo
        lblV.setHorizontalAlignment(SwingConstants.RIGHT);// Definindo a Letra para ser Centralizada.
        lblV.setBounds(670, 11, 46, 14);// Definindo o TAMANHO
        contentPane.add(lblV);// Colocando TEXTO Versão do Software no PAINEL = JANELA.
        
        //Criando o BOTÃO CALCULAR.
        JButton btnCalcular = new JButton("Calcular");//Criando um Objeto do TIPO BOTÃO.
        btnCalcular.setToolTipText("Calcular Jogos.");// Definindo uma descrição para BOTÃO.
        btnCalcular.setBounds(627, 66, 89, 23);// Definindo TAMANHO
        contentPane.add(btnCalcular);
        
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
        
        txtNmeroDeGeraes = new JTextField();
        txtNmeroDeGeraes.setToolTipText("N\u00FAmero de Gera\u00E7\u00F5es");
        txtNmeroDeGeraes.setText("50");
        txtNmeroDeGeraes.setHorizontalAlignment(SwingConstants.CENTER);
        txtNmeroDeGeraes.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtNmeroDeGeraes.setColumns(10);
        txtNmeroDeGeraes.setBounds(138, 54, 38, 20);
        contentPane.add(txtNmeroDeGeraes);
        
        txtTaxadeMutacao = new JTextField();
        txtTaxadeMutacao.setToolTipText("Taxa de Muta\u00E7\u00E3o");
        txtTaxadeMutacao.setText("0.01");
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
        
        JLabel label_5 = new JLabel("b =");
        label_5.setHorizontalAlignment(SwingConstants.CENTER);
        label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label_5.setEnabled(false);
        label_5.setBounds(186, 73, 49, 22);
        contentPane.add(label_5);
        
        txtTaxadeCross = new JTextField();
        txtTaxadeCross.setToolTipText("Taxa de Crossover");
        txtTaxadeCross.setText("0.9");
        txtTaxadeCross.setHorizontalAlignment(SwingConstants.CENTER);
        txtTaxadeCross.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtTaxadeCross.setColumns(10);
        txtTaxadeCross.setBounds(234, 56, 38, 20);
        contentPane.add(txtTaxadeCross);
        
        txtI = new JTextField();
        txtI.setToolTipText("Beta");
        txtI.setText("?");
        txtI.setHorizontalAlignment(SwingConstants.CENTER);
        txtI.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtI.setEditable(false);
        txtI.setColumns(10);
        txtI.setBounds(234, 78, 38, 20);
        contentPane.add(txtI);
        
        scrollPane.setBounds(10, 103, 706, 325);// Difinindo o Tamanho do scrollPane = TABELA DE Jogos.
        contentPane.add(scrollPane);// Colocando a scrollPane no Painel.
        table.setToolTipText("Tabela de Jogos");// Definindo uma descrição para a TABELA DE Jogos.
        table.setColumnSelectionAllowed(true);// Permitindo que selecione TODAS AS COLUNAS DA TABELA DE Jogos.
        table.setCellSelectionEnabled(true);// Permitindo que selecione TODAS AS LINHAS DA TABELA DE Jogos.
        table.setSurrendersFocusOnKeystroke(true);// Permitindo que selecione TODAS AS LINHAS DA TABELA DE Jogos.
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));// Definindo o Tipo de Letra FONTE da TABELA DE Jogos.
        scrollPane.setViewportView(table);// Colocando a TABELA dentro do SCROLLPANE
        //scrollPane.setVisible(false);// Ocultando a Tabela até que se click no botão CALCULAR.
        
        populaTabela();
	}

	@SuppressWarnings("null")
	private void populaTabela() {
		//Cabeçalho
		table.setModel(model = new DefaultTableModel(new Object[][]{}, new String[]{"X", "X", "X", "X", "X", "X", "X", "X", "Nota"}));
		//Criação de um Objeto de tipo Jogo.
		ArrayList<JogoIndivíduoNxN> jogos = new ArrayList<JogoIndivíduoNxN>();
		JogoIndivíduoNxN jogo = new JogoIndivíduoNxN();
		jogos.add(jogo);
		JogoIndivíduoNxN jogo2 = new JogoIndivíduoNxN();
		jogos.add(jogo2);
		int individo = 1;		
		model.addRow(new Object[]{" ", " ", " ", "By:", "Marcelo", "Vieira", " ", " ", " "});

		for (int i = 0; i < jogos.size(); i++) {
			model.addRow(new Object[]{"---", "---", "---", "---", "---", "---", "---", "---", individo + "º Indivíduo"});
			vetors = jogos.get(i).getMatriz();
			for (int j = 0; j < vetors.length; j++) {
				model.addRow(new Object[]{vetors[j][0], vetors[j][1], vetors[j][2], vetors[j][3], vetors[j][4], vetors[j][5], vetors[j][6], vetors[j][7]});
			}
			individo ++;
			model.addRow(new Object[]{" ", " ", " ",  " ",  " ",  " ", " ", " ", " "});
		}
		
				
	}
	
}
