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
			builder.append("<img class = \"logo\" src=\"cid:logo_g6\">");
			builder.append("<p>\r\n" + //
					"      Em caso de erro, favor contatar o suporte: <strong>grupo6apiserratec@gmail.com</strong>\r\n"
					+ //
					"   </p>\r\n" + //
					"   <div class=\"container\">");
			builder.append("<img src=\"cid:icons8-whatsapp\" >");
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
					"<body>\r\n" + //
					"    <header style=\"background-color: orange; width: 100%; height: 100px; padding: 0px; margin: 0px;\">\r\n"
					+ //
					"        <h1\r\n" + //
					"            style=\"float: right; padding-right: 50px; font-family: Arial, Helvetica, sans-serif; font-size: 22px; line-height: 70px;\">\r\n"
					+ //
					"            Confirmação de Pedido</h1>\r\n" + //
					"    </header>\r\n" + //
					"    <div style=\"margin-left: 30px;\">\r\n" + //
					"        <br>\r\n" + //
					"        <p style=\"size: 19px;\"><b>Fulano, </b></p>\r\n");
			builder.append(usuario.getNomeUsuario());
			builder.append("<img class = \"logo\" src=\"cid:logo_g6\">");
			builder.append(
					"<p>obrigado por fazer seu pedido em nossa loja <b>G6 Tech Store</b>. Seu pedido foi recebido e está em processo de verificação.</p>\r\n<ul>");
			Double valorTotal = 0.0;

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

			builder.append("</ul>\r\n        <br><p>Valor total: R$ ");
			builder.append(valorTotal);
			builder.append("<br>Previsão para entrega: ");
			builder.append(dataEntrega);
			builder.append("<hr>\r\n" + //
					"        <p><b style=\"color: orange;\">#Dica:</b> Através do nosso WhatsApp você consegue também tirar dúvidas sobre o\r\n"
					+ //
					"            status do seu pedido.</p>\r\n" + //
					"        <br>\r\n" + //
					"        <br>\r\n" + //
					"        <p\r\n" + //
					"            style=\"color: orange; text-align: center; justify-items: center; display: flex; flex-direction: column;\">\r\n"
					+ //
					"            <b>CONTE COM A GENTE!</b></p>\r\n" + //
					"    </div>\r\n" +
					"    <div class=\"container\">\r\n");
			builder.append("<img src=\"cid: icon-instagram\">\r\n");
			builder.append("<img src=\"cid: icon-whatsapp\">\r\n");
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
	
	public static void envioEmailContaDesativada(Usuario usuario) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo6apiserratec@gmail.com");
			helper.setTo("julialimafc048@gmail.com");
			// helper.setTo(usuario.getEmail());
			helper.setSubject("CONTA DESATIVADA!");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + //
					"<head>\r\n" + //
					"    <style>\r\n" + //
					"        body {\r\n" + //
					"            font-family: Arial, Helvetica, sans-serif;\r\n" + //
					"            text-align: center;\r\n" + //
					"            display: flex;\r\n" + //
					"            justify-content: center;\r\n" + //
					"            align-items: center;\r\n" + //
					"            height: 100px;\r\n" + //
					"            flex-direction: column;\r\n" + //
					"            box-sizing: border-box;\r\n" + //
					"            margin-top: 40vh;\r\n" + //
					"            font-size: 1.2em;\r\n" + //
					"            background-color: rgba(255, 166, 0, 0.138);\r\n" + //
					"        }\r\n" + //
					"        .container {\r\n" + //
					"            display: flex;\r\n" + //
					"            justify-content: center;\r\n" + //
					"            align-items: center;\r\n" + //
					"            margin-top: 20px;\r\n" + //
					"        }\r\n" + //
					"        .container img {\r\n" + //
					"            width: 50px;\r\n" + //
					"            height: 50px;\r\n" + //
					"            margin: 0px 3px;\r\n" + //
					"        }\r\n" + //
					"        .logo {\r\n" + //
					"            width: 150px;\r\n" + //
					"            height: 150px;\r\n" + //
					"        }\r\n" + //
					"        a {\r\n" + //
					"            text-decoration: none;\r\n" + //
					"            color: orange;\r\n" + //
					"            font-weight: bolder;\r\n" + //
					"        }\r\n" + //
					"    </style>\r\n" + //
					"</head>\r\n" + //
					"\r\n" + //
					"<body>\r\n" + //
					"    <br>\r\n" + //
					"    <h1>Ei,</h1>\r\n" + //
					"    <p>Esperamos que está mensagem <strong>não seja o nosso adeus definitivo,<br><br></strong> mas se você realmente\r\n"
					+ //
					"        deseja desativarsua conta, vamos sentir sua falta.</p>\r\n" + //
					"    <br>\r\n" + //
					"    <p><a href=\"http://www.pudim.com.br/\">Clique aqui</a> caso deseja nos abandonar! <span\r\n" + //
					"            style=\"font-size: 2em;\">&#x1F61E;</span></p>\r\n" + //
					"    <br>\r\n");
			builder.append("<img class = \"logo\" src=\"cid:logo_g6\">");
			builder.append(" <p><b>Nos siga nas redes sociais!</b></p>\r\n" + //
					"        <div class=\"container\">");
			builder.append("<img src=\"cid:icons8-whatsapp\" >");
			builder.append("<img src=\"cid:icons8-instagram\">");
			builder.append("        </div>\r\n" + //
					"</body>\r\n" + //
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
}