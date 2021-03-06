package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds=1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizaId();
    }

    private void atualizaId() {
        contadorDeIds++;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado=null;
        for (Aluno a : alunos) {
            if(a.getId()== aluno.getId()){
                alunoEncontrado=a;
            }
        }
        if (alunoEncontrado!=null){
            int posicaodoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaodoAluno,aluno);
        }
    }

    public void remove(Aluno aluno){
            if(aluno!=null){
                alunos.remove(aluno);
            }
    }

}
