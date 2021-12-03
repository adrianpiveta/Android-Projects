package com.Alura.agenda.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.Alura.agenda.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity{
    String mensagem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de alunos.");

        //TextView cria uma visualização de texto
        /*
        TextView aluno = new TextView(this);
        aluno.setText("zé almiro");
        setContentView(aluno);
        */

        //a classe R faz um mapeamento de todos os resorces
        setContentView(R.layout.activity_main);

        List<String> alunos = new ArrayList<>(Arrays.asList("Adrian",
                "Daniel","Pivete","Adrian",
                "Daniel","Pivete","Adrian",
                "Daniel","Pivete","Adrian",
                "Daniel","Pivete","Pivete","Adrian",
                "Daniel","Pivete","Adrian",
                "Daniel","Pivete","Pivete","Adrian",
                "Daniel","Pivete","Adrian",
                "Daniel","Pivete")) ;
        ListView listaDeAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        listaDeAlunos.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));

        //Toast: texto momentaneo, aviso
        //Toast.makeText(this, "Olá by: Adrian", Toast.LENGTH_SHORT).show();
    }
}
