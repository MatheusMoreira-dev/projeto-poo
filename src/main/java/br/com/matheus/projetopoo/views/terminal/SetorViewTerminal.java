package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.models.Setor;

public class SetorViewTerminal implements ViewTerminal<Setor> {
    @Override
    public Setor create() {
        Setor s = new Setor();
        System.out.print("Nome: ");
        s.setNome(input.nextLine());
        return s;
    }

    @Override
    public Setor edit() {
        Setor s = new Setor();
        System.out.print("Novo Nome: ");
        s.setNome(input.nextLine());
        return s;
    }
}
