package br.com.api.g6.dto;

public class MessageResponseDTO {

   private String message;
   // private String token;

   public MessageResponseDTO(String message) {
      super();
      this.message = message;
      // this.token = token;
   }

   // public String getToken() {
   // return token;
   // }

   // public void setToken(String token) {
   // this.token = token;
   // }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }
}
