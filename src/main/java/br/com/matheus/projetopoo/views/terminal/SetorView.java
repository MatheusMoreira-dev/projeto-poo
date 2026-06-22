package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.models.Setor;

public class SetorView implements TerminalCRUD<Setor> {
    @Override
    public Setor create() {
        clearConsole();

        Setor s = new Setor();
        System.out.print("Nome: ");
        s.setNome(input.nextLine());
        return s;
    }

    @Override
    public Setor edit() {
        clearConsole();

        Integer id = this.requestId();
        Setor s = this.create();

        s.setId(id);
        return s;
    }
}
