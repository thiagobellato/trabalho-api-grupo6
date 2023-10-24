package br.com.api.g6.entities;

import javax.persistence.Embeddable;

@Embeddable
public class PedidoProduto {
   
   private Integer qnt_item;

   public PedidoProduto() {
   }

   public PedidoProduto(Integer qnt_item) {
      this.qnt_item = qnt_item;
   }

   public Integer getQnt_item() {
      return qnt_item;
   }

   public void setQnt_item(Integer qnt_item) {
      this.qnt_item = qnt_item;
   }
}
