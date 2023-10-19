package br.com.api.g6.services;

import java.text.DecimalFormat;
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

import br.com.api.g6.entities.Usuario;

@Configuration
@Service
public class EmailService {
	@Autowired
	UsuarioService usuarioService;
	private JavaMailSender emailSender;

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
			helper.setTo("tfbellato@hotmail.com");
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

	public void envioEmailTeste() {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo6apiserratec@gmail.com");
			helper.setTo("tfbellato@hotmail.com");
			helper.setSubject("VASCO 5 X 0 FORTALEZA!!!");

			LocalDate localDate = LocalDate.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataEntrega = localDate.plusDays(7).format(format);

			Double valor = 15.9;
			DecimalFormat df = new DecimalFormat("R$ ,##0.00");

			StringBuilder builder = new StringBuilder();
			builder.append("<html> \r\n");
			builder.append("<body> \r\n");
			builder.append("<div align = \"center\"> \r\n");
			builder.append("<h1>Convite</h1> \r\n");
			builder.append("<br/> \r\n");
			builder.append("<center>");
			builder.append("<table border='2' cellpadding='4'> \r\n");
			builder.append("<tr> <th>Nome</th> <th>Email</th> <th>Perfis</th> <th>Data de Entrega</th> </tr> \r\n ");
			List<Usuario> listaUsuarios = usuarioService.listarTodos();
			for (Usuario usuario : listaUsuarios) {
				builder.append("		    <tr>\r\n");
				builder.append("			<td>\r\n");
				builder.append(usuario.getNomeUsuario());
				builder.append("			</td>\r\n");
				builder.append("			<td>\r\n");
				builder.append(usuario.getEmail());
				builder.append("			</td>\r\n");
				builder.append("		    <td>\r\n");
				builder.append(usuario.getRoles());
				builder.append("			</td>\r\n");
				builder.append("			<td>\r\n");
				builder.append(dataEntrega);
				builder.append("			</td>\r\n");
			}

			builder.append("		</table>\r\n");
			builder.append("		</center>\r\n");
			builder.append("		<table border='1' cellpadding='1'  >\r\n");
			builder.append("<tr><th>Valor Total</th></tr>\r\n");
			builder.append("			<td>\r\n");
			builder.append(df.format(valor));
			builder.append("			</td>\r\n");
			builder.append("		</table>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>");

			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void envioEmailCarrouselTeste() {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo6apiserratec@gmail.com");
			helper.setTo("julialimafc048@gmail.com");
			helper.setSubject("Teste do carrousel!");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + //
					"\r\n" + //
					"<head>\r\n" + //
					"   <style>\r\n" + //
					"      * {\r\n" + //
					"         padding: 0;\r\n" + //
					"         margin: 0;\r\n" + //
					"         box-sizing: border-box;\r\n" + //
					"      }\r\n" + //
					"\r\n" + //
					"      .conteinerCarrousel {\r\n" + //
					"         position: relative;\r\n" + //
					"         padding: 20px;\r\n" + //
					"         max-width: 640px;\r\n" + //
					"         margin: 0 auto;\r\n" + //
					"         border: 3px solid var(--cor04);\r\n" + //
					"         border-radius: 5px;\r\n" + //
					"         box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.466);\r\n" + //
					"      }\r\n" + //
					"\r\n" + //
					"      .carrousel {\r\n" + //
					"         overflow-x: auto;\r\n" + //
					"         -ms-overflow-style: none;\r\n" + //
					"      }\r\n" + //
					"\r\n" + //
					"      .carrousel::-webkit-scrollbar {\r\n" + //
					"         display: none;\r\n" + //
					"      }\r\n" + //
					"\r\n" + //
					"      .galeria {\r\n" + //
					"         display: flex;\r\n" + //
					"         flex-flow: row nowrap;\r\n" + //
					"         gap: 15px;\r\n" + //
					"      }\r\n" + //
					"\r\n" + //
					"      .setaEsquerda,\r\n" + //
					"      .setaDireita {\r\n" + //
					"         position: absolute;\r\n" + //
					"         top: 0;\r\n" + //
					"         left: 0;\r\n" + //
					"         right: auto;\r\n" + //
					"         bottom: 0;\r\n" + //
					"         font-size: 20px;\r\n" + //
					"         line-height: 250px;\r\n" + //
					"         width: 30px;\r\n" + //
					"         border: none;\r\n" + //
					"         color: black;\r\n" + //
					"         opacity: 1;\r\n" + //
					"      }\r\n" + //
					"\r\n" + //
					"      .setaDireita {\r\n" + //
					"         left: auto;\r\n" + //
					"         right: 0;\r\n" + //
					"      }\r\n" + //
					"\r\n" + //
					"      .item {\r\n" + //
					"         width: 400px;\r\n" + //
					"         height: 400px;\r\n" + //
					"         flex-shrink: 0;\r\n" + //
					"         opacity: 1;\r\n" + //
					"         background-color: var(--cor04);\r\n" + //
					"         padding: 1.5px;\r\n" + //
					"      }\r\n" + //
					"   </style>\r\n" + //
					"</head>\r\n" + //
					"\r\n" + //
					"<body>\r\n" + //
					"   <div class=\"conteinerCarrousel\"\r\n" + //
					"      id=\"FOTOS\">\r\n" + //
					"      <button class=\"setaEsquerda control\"\r\n" + //
					"         aria-label=\"Previous image\">setaesquerda</button>\r\n" + //
					"      <button class=\"setaDireita control\"\r\n" + //
					"         aria-label=\"Next image\">setadireita</button>\r\n" + //
					"      <div class=\"carrousel\">\r\n" + //
					"         <div class=\"galeria\">\r\n" + //
					"            <img src=\"logo_g6_tech_store.png\"\r\n" + //
					"               alt=\"Teste carrousel\"\r\n" + //
					"               class=\"item currentItem\">\r\n" + //
					"            <img src=\"logo_g6_tech_store.png\"\r\n" + //
					"               alt=\"Teste carrousel\"\r\n" + //
					"               class=\"item\">\r\n" + //
					"            <img src=\"logo_g6_tech_store.png\"\r\n" + //
					"               alt=\"Teste carrousel\"\r\n" + //
					"               class=\"item\">\r\n" + //
					"            <img src=\"logo_g6_tech_store.png\"\r\n" + //
					"               alt=\"Teste carrousel\"\r\n" + //
					"               class=\"item\">\r\n" + //
					"         </div>\r\n" + //
					"      </div>\r\n" + //
					"   </div>\r\n" + //
					"   <script>\r\n" + //
					"      const controls = document.querySelectorAll(\".control\");\r\n" + //
					"      let currentItem = 0;\r\n" + //
					"      const items = document.querySelectorAll(\".item\");\r\n" + //
					"      const maxItems = items.length;\r\n" + //
					"\r\n" + //
					"      controls.forEach((control) => {\r\n" + //
					"         control.addEventListener(\"click\", (e) => {\r\n" + //
					"            isLeft = e.target.classList.contains(\"setaEsquerda\");\r\n" + //
					"\r\n" + //
					"            if (isLeft) {\r\n" + //
					"               currentItem -= 1;\r\n" + //
					"            } else {\r\n" + //
					"               currentItem += 1;\r\n" + //
					"            }\r\n" + //
					"\r\n" + //
					"            if (currentItem >= maxItems) {\r\n" + //
					"               currentItem = 0;\r\n" + //
					"            }\r\n" + //
					"\r\n" + //
					"            if (currentItem < 0) {\r\n" + //
					"               currentItem = maxItems - 1;\r\n" + //
					"            }\r\n" + //
					"\r\n" + //
					"            items.forEach((item) => item.classList.remove(\"currentItem\"));\r\n" + //
					"\r\n" + //
					"            items[currentItem].scrollIntoView({\r\n" + //
					"               behavior: \"smooth\",\r\n" + //
					"               inline: \"center\",\r\n" + //
					"               block: \"nearest\"\r\n" + //
					"            });\r\n" + //
					"\r\n" + //
					"            items[currentItem].classList.add(\"currentItem\");\r\n" + //
					"         });\r\n" + //
					"      });\r\n" + //
					"   </script>\r\n" + //
					"</body>\r\n" + //
					"\r\n" + //
					"</html>");
			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}