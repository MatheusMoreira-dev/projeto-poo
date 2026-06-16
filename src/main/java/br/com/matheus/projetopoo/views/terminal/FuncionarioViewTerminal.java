package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.models.Funcionario;

public class FuncionarioViewTerminal implements ViewTerminal<Funcionario>{
    @Override
    public Funcionario create() {
        Funcionario f = new Funcionario();

        System.out.println("Cadastro de Funcionário");
        System.out.println("--------------------------------------------");
        System.out.print("Nome do funcionário: ");
        f.setNome(input.nextLine());

        System.out.print("Id do Setor: ");
        f.setSetorId(input.nextInt());
        System.out.println("--------------------------------------------");
        return f;
    }

    @Override
    public Funcionario edit() {
        return create();
    }
}
