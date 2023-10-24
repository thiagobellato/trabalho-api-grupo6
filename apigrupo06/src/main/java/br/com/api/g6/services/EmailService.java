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
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.api.g6.controllers.CarrouselController;
import br.com.api.g6.dto.UsuarioRequestCadastroDTO;
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

	@Autowired
	static CarrouselController carrouselController;

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

	public void envioEmailCadastro(String email, UsuarioRequestCadastroDTO usuario) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo6apiserratec@gmail.com");
			helper.setTo(usuario.getEmail());
			// helper.setTo("julialimafc048@gmail.com");
			helper.setSubject("Cadastro concluido!");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + //
					"\r\n" + //
					"<head>\r\n" + //
					"   <style>\r\n" + //
					"      body {\r\n" + //
					"         text-align: center;\r\n" + //
					"         display: flex;\r\n" + //
					"         justify-content: center;\r\n" + //
					"         align-items: center;\r\n" + //
					"         background-color: orange;\r\n" + //
					"         height: 100px;\r\n" + //
					"         color: white;\r\n" + //
					"         flex-direction: column;\r\n" + //
					"         box-sizing: border-box;\r\n" + //
					"         margin-top: 40vh;\r\n" + //
					"         font-size: 1.2em;\r\n" + //
					"      }\r\n" + //
					"\r\n" + //
					"      .container {\r\n" + //
					"         position: relative;\r\n" + //
					"         top: -600px;\r\n" + //
					"         left: 50px;\r\n" + //
					"      }\r\n" + //
					"\r\n" + //
					"      strong {\r\n" + //
					"         text-decoration: underline;\r\n" + //
					"      }\r\n" + //
					"\r\n" + //
					"      img {\r\n" + //
					"         width: 50px;\r\n" + //
					"         height: 50px;\r\n" + //
					"      }\r\n" + //
					"      img.logo {\r\n" + //
					"         width: 150px;\r\n" + //
					"         height: 150px;\r\n" + //
					"      }\r\n" + //
					"   </style>\r\n" + //
					"</head>\r\n" + //
					"\r\n" + //
					"<body>\r\n" + //
					"   <h1>Cadastro realizado com sucesso!</h1>");
			builder.append("<img class = \"logo\"S src=\"cid:logo_g6\">");
			builder.append("<p>\r\n" + //
					"      Em caso de erro, favor contatar o suporte: <strong>grupo6apiserratec@gmail.com</strong>\r\n"
					+ //
					"   </p>\r\n" + //
					"   <div class=\"container\">");
			builder.append("<img src=\"cid:icons8-whatsapp\">");
			builder.append("<img src=\"cid:icons8-instagram\">");
			builder.append("</div>\r\n" + //
					"</body>\r\n" + //
					"\r\n" + //
					"</html>");

			helper.setText(builder.toString(), true);
			ClassPathResource logo_g6 = new ClassPathResource("img/logo_g6.png");
			helper.addInline("logo_g6", logo_g6);
			ClassPathResource iconWhatsapp = new ClassPathResource("img/icons8-whatsapp.png");
			helper.addInline("icons8-whatsapp", iconWhatsapp);

			ClassPathResource iconInstagram = new ClassPathResource("img/icons8-instagram.png");
			helper.addInline("icons8-instagram", iconInstagram);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailPedidoFinalizado(Usuario usuario, List<Produto> produtos) {

		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo6apiserratec@gmail.com");
			helper.setTo(usuario.getEmail());
			// helper.setTo("julialimafc048@gmail.com");
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
					"        <p style=\"float: left; font-family: Arial, Helvetica, sans-serif; padding: 30px;\"> <b>N° do pedido:");
			// builder.append(idPedido);
			builder.append("</b></p>\r\n" + //
					"        <h1 style=\"float: right; padding-right: 50px; font-family: Arial, Helvetica, sans-serif; font-size: 22px; line-height: 70px;\">Confirmação de Pedido</h1>\r\n"
					+ //
					"    </header>\r\n" + //
					"    <div style=\"margin-left: 30px;\">\r\n" + //
					"        <br>\r\n" + //
					"        <p style=\"size: 19px;\"><b>");
			builder.append(usuario.getNomeUsuario());
			builder.append(",</b></p>\r\n" + //
					"        <p>Obrigado por fazer seu pedido em nossa loja <b>G6 Tech Store</b>. Seu pedido número #");
			// builder.append(idPedido);
			builder.append(" foi recebido e está em processo de verificação.</p>\r\n");
			Double valorTotal = 0.0;

			// List<Produto> listaProdutos = produtoService.listar(idPedido);
			for (Produto produto : produtos) {
				builder.append(" <tr>\r\n");
				builder.append(" <td>\r\n");
				builder.append(produto.getNome());
				builder.append(" </td>\r\n");
				builder.append(" <td>\r\n");
				builder.append(produto.getQuantidade());
				builder.append(" </td>\r\n");
				builder.append(" <td>\r\n");
				builder.append(produto.getValorUnitario());
				builder.append(" </td>\r\n");
				builder.append(" <td>\r\n");
				builder.append(" </td>\r\n");
				valorTotal += produto.getValorUnitario();
			}

			builder.append("        <br><p>Valor total: R$ ");
			builder.append(valorTotal);
			builder.append("<br>Previsão para entrega: ");
			builder.append(dataEntrega);
			builder.append("        <br>\r\n" + "        <hr style=\"margin-right: 30px;\">\r\n"
					+ "        <p><b style=\"color: orange;\">#Dica:</b> Através do nosso WhatsApp você consegue também tirar dúvidas sobre o status do seu pedido.</p>\r\n"
					+ "        <p style=\"color: orange; text-align: center; justify-items: center; margin: 0px; display: flex; flex-direction: column;\"><b>CONTE COM A GENTE!</b></p>\r\n"
					+ " <img src=\"cid: logo\"> \r\n   </div>  \r\n" + //
					"    <div class=\"container\">\r\n");
			builder.append("<img src=\"cid: icon-instagram\">\r\n");
			builder.append("<img src=\"cid: icon-whatsapp\">\r\n");
			builder.append("    </div>\r\n" + "</body>\r\n" + "</html>");

			helper.setText(builder.toString(), true);

			ClassPathResource imgLogo = new ClassPathResource("img/logo_g6.png");
			helper.addInline("logo_g6", imgLogo);

			ClassPathResource iconInstagram = new ClassPathResource("img/icons8-instagram.png");
			helper.addInline("icons8-instagram", iconInstagram);

			ClassPathResource iconWhatsapp = new ClassPathResource("img/icons8-whatsapp.png");
			helper.addInline("icons8-whatsapp", iconWhatsapp);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void envioEmailContaDesativada(Usuario usuario) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			// helper.setFrom("grupo6apiserratec@gmail.com");
			helper.setTo("julialimafc048@gmail.com");
			helper.setTo(usuario.getEmail());
			helper.setSubject("CONTA DESATIVADA!");

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
			builder.append(usuario.getNomeUsuario());
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
					"    <img src=\"cid: icon-instagram\">\r\n" + //
					"    <img src=\"cid: icon-whatsapp\">\r\n" + //
					"        </div>\r\n" + //
					"</body>\r\n" + //
					"</html>");

			helper.setText(builder.toString(), true);

			ClassPathResource iconInstagram = new ClassPathResource("img/icons8-instagram.png");
			helper.addInline("icons8-instagram", iconInstagram);

			ClassPathResource iconWhatsapp = new ClassPathResource("img/icons8-whatsapp.png");
			helper.addInline("icons8-whatsapp", iconWhatsapp);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailPromocional() {

		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo6apiserratec@gmail.com");
			// helper.setTo(usuario.getEmail());
			helper.setTo("julialimafc048@gmail.com");
			helper.setSubject("Cadastro concluido!");

			StringBuilder builder = new StringBuilder();
			builder.append(carrouselController.index());

			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}