package com.Alura.agenda;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends Activity {
    String mensagem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
