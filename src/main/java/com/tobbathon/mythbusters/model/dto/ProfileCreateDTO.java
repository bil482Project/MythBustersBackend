package com.tobbathon.mythbusters.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProfileCreateDTO {

    @NotBlank(message = "Kullanıcı adı boş olamaz")
    @Size(min = 3, max = 50, message = "Kullanıcı adı 3-50 karakter olmalı")
    private String username;

    @NotBlank(message = "E-posta boş olamaz")
    @Email(message = "Geçerli bir e-posta adresi girin")
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 6, message = "Şifre en az 6 karakter olmalı")
    private String password;

    private Integer coin;

    private ProfileCreateDTO(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
    }

    public ProfileCreateDTO() {}

    public static class Builder {
        private String username;
        private String email;
        private String password;
        private Integer coin;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder coin(Integer coin) {
            this.coin = coin;
            return this;
        }

        public ProfileCreateDTO build() {
            // Zorunlu alanlar kontrolü (opsiyonel ama best practice)
            if (username == null || email == null || password == null || coin == null) {
                throw new IllegalStateException("Kullanıcı adı, e-posta ve şifre zorunludur.");
            }
            return new ProfileCreateDTO(this);
        }
    }

}