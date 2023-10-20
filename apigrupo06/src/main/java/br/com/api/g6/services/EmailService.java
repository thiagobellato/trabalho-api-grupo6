package br.com.api.g6.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.api.g6.entities.Pedido;
import br.com.api.g6.entities.Produto;
import br.com.api.g6.entities.Usuario;

@Configuration
@Service
public class EmailService {

	@Autowired
	static UsuarioService usuarioService;

	@Autowired
	static ProdutoService produtoService;

	@Autowired
	static PedidoService pedidoService;

	private static JavaMailSender emailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private Integer port;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		Properties prop = new Properties();
		emailSender.setHost(host);
		emailSender.setPort(port);
		emailSender.setUsername(username);
		emailSender.setPassword(password);
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		emailSender.setJavaMailProperties(prop);
		return emailSender;
	}

	public void envioEmail() {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo6apiserratec@gmail.com");
			helper.setTo("taamiresferreiraa38@gmail.com");
			helper.setSubject("Cadastro concluido!");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("	<body>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<h1>Cadastro realizado com sucesso!</h1>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<br/>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			Em caso de erro, favor contatar o suporte: serratecgrupo1@gmail.com");
			builder.append("		</div>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void envioEmailTeste() {
	 * MimeMessage mensagemCadastro = emailSender.createMimeMessage();
	 * try {
	 * MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
	 * helper.setFrom("grupo6apiserratec@gmail.com");
	 * helper.setTo("taamiresferreiraa38@gmail.com");
	 * helper.setSubject("VASCO 5 X 0 FORTALEZA!!!");
	 * 
	 * Double valor = 15.9;
	 * DecimalFormat df = new DecimalFormat("R$ ,##0.00");
	 * 
	 * StringBuilder builder = new StringBuilder();
	 * builder.append("<html> \r\n");
	 * builder.append("<body> \r\n");
	 * builder.append("<div align = \"center\"> \r\n");
	 * builder.append("<h1>Convite</h1> \r\n");
	 * builder.append("<br/> \r\n");
	 * builder.append("<center>");
	 * builder.append("<table border='2' cellpadding='4'> \r\n");
	 * builder.
	 * append("<tr> <th>Nome</th> <th>Email</th> <th>Perfis</th> <th>Data de Entrega</th> </tr> \r\n "
	 * );
	 * //List<Usuario> listaUsuarios = usuarioService.listarTodos();
	 * for (Usuario usuario : listaUsuarios) {
	 * builder.append("		    <tr>\r\n");
	 * builder.append("			<td>\r\n");
	 * builder.append(usuario.getNomeUsuario());
	 * builder.append("			</td>\r\n");
	 * builder.append("			<td>\r\n");
	 * builder.append(usuario.getEmail());
	 * builder.append("			</td>\r\n");
	 * builder.append("		    <td>\r\n");
	 * builder.append(usuario.getRoles());
	 * builder.append("			</td>\r\n");
	 * builder.append("			<td>\r\n");
	 * builder.append("			</td>\r\n");
	 * }
	 * 
	 * builder.append("		</table>\r\n");
	 * builder.append("		</center>\r\n");
	 * builder.append("		<table border='1' cellpadding='1'  >\r\n");
	 * builder.append("<tr><th>Valor Total</th></tr>\r\n");
	 * builder.append("			<td>\r\n");
	 * builder.append(df.format(valor));
	 * builder.append("			</td>\r\n");
	 * builder.append("		</table>\r\n");
	 * builder.append("	</body>\r\n");
	 * builder.append("</html>");
	 * 
	 * helper.setText(builder.toString(), true);
	 * emailSender.send(mensagemCadastro);
	 * 
	 * } catch (MessagingException e) {
	 * e.printStackTrace();
	 * }
	 * }
	 */

	public void envioEmailPedidoFinalizado(Usuario usuario,Integer idPedido) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo6apiserratec@gmail.com");
			helper.setTo(usuario.getEmail());
			helper.setSubject("PEDIDO REALIZADO COM SUCESSO!");

			LocalDate localDate = LocalDate.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataEntrega = localDate.plusDays(7).format(format);

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + //
					"    <head>\r\n" + //
					"        <style>\r\n" + //
					"        .container {\r\n" + //
					"            display: inline-block;\r\n" + //
					"            justify-content: center;\r\n" + //
					"            align-items: center;\r\n" + //
					"            position: relative;\r\n" + //
					"            top: -600px;\r\n" + //
					"            justify-items: center; \r\n" + //
					"            display: flex; \r\n" + //
					"            left: 10px;\r\n" + //
					"        }\r\n" + //
					"    </style>\r\n" + //
					"    </head>\r\n" + //
					"<body>\r\n" + //
					"    <header style=\"background-color: orange; width: 100%; height: 100px; padding: 0px; margin: 0px;\"> \r\n"
					+ //
					"        <p style=\"float: left; font-family: Arial, Helvetica, sans-serif; padding: 30px;\"> <b>N° do pedido:######</b></p>\r\n"
					+ //
					"        <h1 style=\"float: right; padding-right: 50px; font-family: Arial, Helvetica, sans-serif; font-size: 22px; line-height: 70px;\">Confirmação de Pedido</h1>\r\n"
					+ //
					"    </header>\r\n" + //
					"    <div style=\"margin-left: 30px;\">\r\n" + //
					"        <br>\r\n" + //
					"        <p style=\"size: 19px;\"><b>");
			builder.append(usuario.getNomeUsuario());
			builder.append(",</b></p>\r\n" + //
					"        <p>Obrigado por fazer seu pedido em nossa loja <b>G6 Tech Store</b>. Seu pedido #[");
			builder.append(idPedido);
			builder.append("] foi recebido e está em processo de verificação.</p>\r\n");
			Double valorTotal = 0.0;
			/*List<Produto> listaProdutos = produtoService.listar(idPedido);
			for (Produto produto : listaProdutos) {
				builder.append("		    <tr>\r\n");
				builder.append("			<td>\r\n");
				builder.append(produto.getNome());
				builder.append("			</td>\r\n");
				builder.append("			<td>\r\n");
				builder.append(produto.getQuantidade());
				builder.append("			</td>\r\n");
				builder.append("		    <td>\r\n");
				builder.append(produto.getValorUnitario());
				builder.append("			</td>\r\n");
				builder.append("			<td>\r\n");
				builder.append("			</td>\r\n");
				valorTotal += produto.getValorUnitario();
			}*/

			builder.append("Previsão para entrega: ");
			builder.append(dataEntrega);
			builder.append("        <p>Valor total: R$ ");
			builder.append(valorTotal); // PRECISA CRIAR
			builder.append("        <br>\r\n" + //
					"        <hr style=\"margin-right: 30px;\">\r\n" + //
					"        <p><b style=\"color: orange;\">#Dica:</b> Através do nosso WhatsApp você consegue também tirar dúvidas sobre o status do seu pedido.</p>\r\n"
					+ //
					"        <br>\r\n" + //
					"        <br>\r\n" + //
					"        <p style=\"color: orange; text-align: center; justify-items: center; margin: 0px; height: 100vh; display: flex; flex-direction: column; margin-top: 80px;\"><b>CONTE COM A GENTE!</b></p>\r\n"
					+ //
					"    </div>  \r\n" + //
					"    <div class=\"container\">\r\n" + //
					"    <img src=\"/outros_arquivos/imagens/icons8-instagram-96.png\" alt=\"instagram\" width=\"30\">\r\n" + //
					"    <img src=\"/outros_arquivos/imagens/icons8-whatsapp-ios-16-filled-96.png\" alt=\"WhatsApp\" width=\"27\">\r\n"
					+ //
					"    </div>\r\n" + //
					"</body>\r\n" + //
					"</html>");
			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void envioEmailContaDesativada() {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo6apiserratec@gmail.com");
			helper.setTo("julialimafc048@gmail.com");
			helper.setSubject("PEDIDO REALIZADO COM SUCESSO!");

			LocalDate localDate = LocalDate.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataEntrega = localDate.plusDays(7).format(format);

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + //
					"<head>\r\n" + //
					"    <style>\r\n" + //
					"        .container {\r\n" + //
					"            position: relative;\r\n" + //
					"            top: -600px;\r\n" + //
					"            left: 50px;\r\n" + //
					"        }\r\n" + //
					"    </style>\r\n" + //
					"</head>\r\n" + //
					"<body>\r\n" + //
					"    <header style=\"background-color: orange; width: 100%; height: 100px; padding: 0px; margin: 0px;\"> \r\n"
					+ //
					"       \r\n" + //
					"    </header>\r\n" + //
					"    <div style=\"margin-left: 30px;\">\r\n" + //
					"        <br>\r\n<p>Ei, <b>"); //
//			builder.append(Usuario.getNomeUsuario());
			builder.append(",</b></p>\r\n" + //
					"        <br>\r\n" + //
					"        <p>Esperamos que está mensagem <b>não seja o nosso adeus definitivo</b>, mas se você realmente deseja desativar sua conta, vamos sentir sua falta.</p>\r\n"
					+ //
					"        <br>\r\n" + //
					"        <p><a href=\"https://www.google.com.br/\">Clique aqui</a> caso deseja nos abandonar! &#x1F61E;</p>\r\n"
					+ //
					"        <br>\r\n" + //
					"        <div>\r\n" + //
					"            <p style=\"color: black; justify-items: center; margin: 0px; height: 100vh; display: flex; flex-direction: column; margin-top: 80px;\"><b>Nos siga nas redes sociais!</b></p>\r\n"
					+ //
					"    </div>  \r\n" + //
					"    <div class=\"container\">\r\n" + //
					"    <img src=\"/outros_arquivos/imagens/icons8-instagram-96.png\" alt=\"instagram\" width=\"30\">\r\n" + //
					"    <img src=\"/outros_arquivos/imagens/icons8-whatsapp-ios-16-filled-96.png\" alt=\"WhatsApp\" width=\"27\">\r\n"
					+ //
					"        </div>\r\n" + //
					"</body>\r\n" + //
					"</html>");

			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}