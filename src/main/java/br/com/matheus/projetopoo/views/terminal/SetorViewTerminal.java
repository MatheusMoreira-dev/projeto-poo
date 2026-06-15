package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.controllers.SetorController;
import br.com.matheus.projetopoo.models.Setor;

import java.util.List;
import java.util.Optional;

public class SetorViewTerminal implements ViewTerminal<Setor> {
    @Override
    public Setor create() {
        Setor s = new Setor();

        return s;
    }

    @Override
    public Optional<Setor> getItem() {
        return Optional.empty();
    }

    @Override
    public List<Setor> getAll() {
        return List.of();
    }

    @Override
    public Setor edit() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public void exit() {

    }
}
