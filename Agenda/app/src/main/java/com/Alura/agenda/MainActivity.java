package com.Alura.agenda;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
                "Daniel","Pivete")) ;
        TextView primeiroAluno = findViewById(R.id.textView);
        TextView segundoAluno = findViewById(R.id.textView2);
        TextView terceiroAluno = findViewById(R.id.textView3);

        primeiroAluno.setText(alunos.get(0));
        segundoAluno.setText(alunos.get(1));
        terceiroAluno.setText(alunos.get(2));

        //Toast: texto momentãneo, aviso
        //Toast.makeText(this, "Olá by: Adrian", Toast.LENGTH_SHORT).show();
    }
}
