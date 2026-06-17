package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.models.Funcionario;

public class FuncionarioViewTerminal implements ViewTerminalCRUD<Funcionario> {
    @Override
    public Funcionario create() {
        Funcionario f = new Funcionario();

        System.out.println("Cadastro de Funcionário");
        System.out.println("--------------------------------------------");
        System.out.print("Nome do funcionário: ");

        String nome = input.nextLine();
        f.setNome(nome);

        System.out.print("Id do Setor: ");
        int id = input.nextInt();

        input.nextLine();
        f.setSetorId(id);

        System.out.println("--------------------------------------------");
        return f;
    }

    @Override
    public Funcionario edit() {
        Funcionario f = create();
        Integer id = this.requestId();

        f.setId(id);
        return f;
    }
}
