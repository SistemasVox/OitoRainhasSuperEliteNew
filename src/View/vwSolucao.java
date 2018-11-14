package View;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JTextField;

import Model.JogoIndivíduoNxN;

import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.Color;

@SuppressWarnings("serial")
public class vwSolucao extends JFrame {

	private JTextField txtFO;
	private JPanel contentPane;
	private JScrollPane scrollPane = new JScrollPane();
	private JTextArea campoDeImpressao = new JTextArea();
	JTextArea txtInfo = new JTextArea();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwSolucao frame = new vwSolucao();
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
	public vwSolucao() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("A.I. - Intelig\u00EAncia Artificial Oito Rainhas");
		setBounds(500, 100, 742, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtFO = new JTextField();
		txtFO.setToolTipText("Situa\u00E7\u00E3o");
		txtFO.setText("?");
		txtFO.setHorizontalAlignment(SwingConstants.CENTER);
		txtFO.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFO.setEditable(false);
		txtFO.setColumns(10);
		txtFO.setBounds(480, 75, 38, 20);
		contentPane.add(txtFO);

		JLabel lblV = new JLabel("V: 1.2");
		lblV.setToolTipText("Versão do Software");
		lblV.setEnabled(false);
		lblV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblV.setBounds(670, 11, 46, 14);
		contentPane.add(lblV);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		
		JButton btnNewButton = new JButton("TOPO");
		btnNewButton.setToolTipText("Subir Barra de Rolagem");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		});
		btnNewButton.setBounds(528, 75, 89, 23);
		contentPane.add(btnNewButton);
		btnSair.setToolTipText("Fechar Janela");
		btnSair.setBounds(627, 75, 89, 23);
		contentPane.add(btnSair);

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

		JLabel lblSistemasVox = new JLabel("Solu\u00E7\u00E3o");
		lblSistemasVox
				.setToolTipText("Ver mais informa\u00E7\u00F5es do Software");
		lblSistemasVox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		lblSistemasVox
				.setFont(new Font("Vivaldi", Font.BOLD | Font.ITALIC, 39));
		lblSistemasVox.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemasVox.setBounds(378, 0, 338, 87);
		contentPane.add(lblSistemasVox);
		txtInfo.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 18));
		
		
		txtInfo.setBackground(UIManager.getColor("Button.background"));
		txtInfo.setForeground(Color.BLACK);
		txtInfo.setEditable(false);
		txtInfo.setWrapStyleWord(true);
		txtInfo.setLineWrap(true);
		txtInfo.setBounds(10, 0, 468, 98);
		contentPane.add(txtInfo);

	}


	public void populaCampoFilhos(JogoIndivíduoNxN solucao, String info) {
		
		campoDeImpressao.setText("                                                                      Problema das 8 Rainhas, Geração de filhos.\r\n");
		campoDeImpressao.setText(campoDeImpressao.getText() + "\n"+ solucao.toString());
		txtFO.setText(":)");
		txtFO.setToolTipText("Indivíduo encontrado.");
		txtInfo.setText(info);
		
	}
}
