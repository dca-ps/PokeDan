package com.dassumpca.pokedanapp.Enum;

public enum ColorEnum {
        black("#212121"),
        blue("#1976d2"),
        brown("#6d4c41"),
        gray("#78909c"),
        green("#66bb6a"),
        pink("#f06292"),
        purple("#ba68c8"),
        red("#f44336"),
        white("#fafafa"),
        yellow("#ffee58");

        private String cor;
        ColorEnum(String cor) {
                this.cor = cor;
        }

        public String getCor() {
                return cor;
        }
}
