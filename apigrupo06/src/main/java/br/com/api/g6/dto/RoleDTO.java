package br.com.api.g6.dto;

import br.com.api.g6.enums.TipoRoleEnum;

public class RoleDTO {

	private TipoRoleEnum name;

	public RoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleDTO(TipoRoleEnum name) {
		super();
		this.name = name;
	}

	public TipoRoleEnum getName() {
		return name;
	}

	public void setName(TipoRoleEnum name) {
		this.name = name;
	}

}
