package br.com.api.g6.dto;

public class UserDTO {

	private EnderecoDTO enderecoDTO;
	private UsuarioDTO usuarioDTO;
	private RoleDTO roleDTO;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(EnderecoDTO enderecoDTO, UsuarioDTO usuarioDTO, RoleDTO roleDTO) {
		super();
		this.enderecoDTO = enderecoDTO;
		this.usuarioDTO = usuarioDTO;
		this.roleDTO = roleDTO;
	}

	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}

	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

}