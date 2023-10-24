package br.com.api.g6.dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoDTO {

   private LocalDate data;

   private Boolean ativo;

   private Integer qnt_produto;

   private List<Integer> id_produto; 

   private Integer id_usuario;

   public PedidoDTO() {
      super();
   }

   public PedidoDTO(LocalDate data, Boolean ativo, Integer qnt_produto, List<Integer> id_produto, Integer id_usuario) {
      this.data = data;
      this.ativo = ativo;
      this.qnt_produto = qnt_produto;
      this.id_produto = id_produto;
      this.id_usuario = id_usuario;
   }

   public LocalDate getData() {
      return data;
   }

   public void setData(LocalDate data) {
      this.data = data;
   }

   public Boolean getAtivo() {
      return ativo;
   }

   public void setAtivo(Boolean ativo) {
      this.ativo = ativo;
   }

   public Integer getQnt_produto() {
      return qnt_produto;
   }

   public void setQnt_produto(Integer qnt_produto) {
      this.qnt_produto = qnt_produto;
   }

   public List<Integer> getId_produto() {
      return id_produto;
   }

   public void setId_produto(List<Integer> id_produto) {
      this.id_produto = id_produto;
   }

   public Integer getId_usuario() {
      return id_usuario;
   }

   public void setId_usuario(Integer id_usuario) {
      this.id_usuario = id_usuario;
   }
}
