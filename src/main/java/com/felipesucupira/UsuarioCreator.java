package com.felipesucupira;

public class UsuarioCreator {
    public static Mediator mediator;
    public static Usuario usuario;

    public static Usuario usuarioCreator(String nome, String senha) {
        usuario = new Usuario(nome, senha);
        mediator = new Mediator(usuario);
        return usuario;
    }

    public static Mediator getMediator() {
        return mediator;
    }
}
