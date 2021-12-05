package com.Alura.agenda.ui.dao;

import com.Alura.agenda.ui.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {


    private final static List<Aluno> alunos = new ArrayList<Aluno>();

    public void salva(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> todos() {
        return new ArrayList<Aluno>(alunos);
    }
}
