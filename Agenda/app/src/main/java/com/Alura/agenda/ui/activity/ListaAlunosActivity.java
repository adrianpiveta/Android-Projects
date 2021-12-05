package com.Alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.Alura.agenda.R;
import com.Alura.agenda.ui.dao.AlunoDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity{
    String mensagem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de alunos.");

        AlunoDAO dao = new AlunoDAO();

        //TextView cria uma visualização de texto
        /*
        TextView aluno = new TextView(this);
        aluno.setText("zé almiro");
        setContentView(aluno);
        */

        //a classe R faz um mapeamento de todos os resorces
        setContentView(R.layout.activity_lista_alunos);

        ListView listaDeAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.todos()));

        View botaoNovoAluno = findViewById(R.id.lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaAlunosActivity.this,
                        FormularioAlunoActivity.class));
                finish();
            }
        });
        //Toast: texto momentaneo, aviso
        //Toast.makeText(this, "Olá by: Adrian", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {

        super.onResume();
        AlunoDAO dao = new AlunoDAO();
        ListView listaDeAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.todos()));

        View botaoNovoAluno = findViewById(R.id.lista_alunos_fab_novo_aluno);
    }
}
