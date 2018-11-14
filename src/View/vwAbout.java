/*
 * Classe: vwAbout do tipo: View = Janela.
 * Função: Apresentar a janela de informações para o Usuário.
 * Última atualização: v2.1 05/05/2018 15:00
 */
package View;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vwAbout extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    static vwAbout frame;

    public vwAbout() {
    	//Design da Classe vwAbout, imagem de ícone e título.
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\workspace\\OitoRainhasSuperEliteNew\\img\\a.png")); // ÍCONE DO vwAbout.
        setTitle("Sobre");// TÚTILO DO vwAbout
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Se clicar no X da janela o Programa fecha.
        setBounds(300, 90, 720, 460);// Tamanho da Janela do vwAbout.
        
        contentPane = new JPanel();//  Criando um Objeto da Janela do vwAbout.
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// Definindo o tamanho da Borda da Janela vwAbout.
        setContentPane(contentPane);// Colocando a Janela no Painel.
        contentPane.setLayout(null);// Colocando o Layout NULLO.
        
        JLabel lblSair = new JLabel("");// Criando o TEXTO do LOGO.
        lblSair.setToolTipText("Sair");// Definindo uma descrição para TEXTO LOGO.
        //Criando um Evento para quando clicar na LOGO.
        lblSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
            	//Fechar Janela vwAbout se clicar na LOGO.
                dispose();
            }
        });
        lblSair.setBounds(65, 11, 512, 107);// Definindo TAMANHO da LOGO.
        contentPane.add(lblSair);// Colocando a LOGO no PAINEL = JANELA.
        
        JLabel lblFundo = new JLabel("");// Criando FUNDO da Janela vwAbout.
        lblFundo.setBackground(UIManager.getColor("Button.background"));//  Definindo plano de FUNDO da Janela vwAbout. 
        lblFundo.setBounds(0, 0, 704, 422);// Tamanho da Janela vwAbout.
        lblFundo.setIcon(new ImageIcon("C:\\workspace\\OitoRainhasSuperEliteNew\\img\\ag1400.png"));// ÍCONE DO vwAbout.
        contentPane.add(lblFundo);// Colocando a Fundo no Painel.
        
        //Área aonde se encontra nossa descrição do trabalho etc.
        JTextArea txtpnSoftwareAgendaTelefnica = new JTextArea();// Objeto
        txtpnSoftwareAgendaTelefnica.setWrapStyleWord(true);// Estilo de Letra
        txtpnSoftwareAgendaTelefnica.setLineWrap(true);// Quebra de linha
        txtpnSoftwareAgendaTelefnica.setBackground(UIManager.getColor("Button.background"));// Plano de Fundo
        txtpnSoftwareAgendaTelefnica.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));// Fonte
        txtpnSoftwareAgendaTelefnica.setEditable(false);// Desativar Edição do mesmo
        //Nossa descrição do projeto e Software.
        txtpnSoftwareAgendaTelefnica.setText("Software 8 Rainhas A.I  Inteligência Artificial.\r\nCriado e desenvolvido por Marcelo Vieira,  7º Período.\r\n\r\nLink source:\r\nhttps://www.dropbox.com/sh/e7xs017mlrv8twh/AACjV7JWr1OXsCrvFn-x10oea?dl=0\r\n\r\nEsse projeto foi possível graças ao projeto de código aberto AgendaTelefonicaVOX\r\ne outros softwares de código aberto.\r\nCopyright © 2013-2018 Sistema VOX. Todos os direitos reservados.");
        txtpnSoftwareAgendaTelefnica.setBounds(31, 102, 546, 309);// Tamanho
        contentPane.add(txtpnSoftwareAgendaTelefnica);// Colocando no Painel.
        setTitle("Sobre");// Título da Janela.
    }
}
