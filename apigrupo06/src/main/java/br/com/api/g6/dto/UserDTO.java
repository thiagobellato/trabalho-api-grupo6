package br.com.api.g6.dto;

import br.com.api.g6.entities.Role;

public class UserDTO {

	private EnderecoDTO enderecoDTO;
	private UsuarioDTO usuarioDTO;
	private Role role;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(EnderecoDTO enderecoDTO, UsuarioDTO usuarioDTO, Role role) {
		super();
		this.enderecoDTO = enderecoDTO;
		this.usuarioDTO = usuarioDTO;
		this.role = role;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}