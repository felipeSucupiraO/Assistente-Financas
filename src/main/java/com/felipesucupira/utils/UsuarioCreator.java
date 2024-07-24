package com.felipesucupira.utils;

import com.felipesucupira.Usuario;
import com.felipesucupira.mediator.Mediator;;

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
