package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.models.Registro;

import java.time.LocalDate;

public class RegistroView implements TerminalCRUD<Registro>{
    @Override
    public Registro create() {
        int itemId, funcId, depositoId, quantidade;

        Registro r = new Registro();
        r.setData(LocalDate.now());

        System.out.print("Item ID: ");
        itemId = input.nextInt();
        r.setItemId(itemId);
        input.nextLine();

        System.out.print("Funcionário ID: ");
        funcId = input.nextInt();
        r.setFuncionarioId(funcId);
        input.nextLine();

        System.out.print("Depósito ID: ");
        depositoId = input.nextInt();
        r.setDepositoId(depositoId);
        input.nextLine();

        boolean eSaida = confirmExec("É uma saída ?");
        System.out.print("Quantidade: ");
        quantidade = input.nextInt();
        input.nextLine();

        if (eSaida){
            quantidade =  -1 * quantidade;
        }

        r.setQuantidade(quantidade);

        System.out.print("Descrição: ");
        String descricao = input.nextLine();
        r.setDescricao(descricao);

        return r;
    }

    @Override
    public Registro edit() {
        int id = requestId();
        Registro r = create();

        r.setId(id);

        return r;
    }
}
