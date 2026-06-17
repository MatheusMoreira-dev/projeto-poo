package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.models.Deposito;

public class DepositoViewTerminal implements ViewTerminalCRUD<Deposito> {
    @Override
    public Deposito create() {
        Deposito d = new Deposito();
        System.out.print("Nome: ");
        d.setNome(input.nextLine());
        return d;
    }

    @Override
    public Deposito edit() {
        Integer id = this.requestId();
        Deposito d = create();

        d.setId(id);

        return d;
    }
}
