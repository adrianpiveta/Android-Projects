package com.Alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.Alura.agenda.R;
import com.Alura.agenda.ui.dao.AlunoDAO;
import com.Alura.agenda.ui.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Novo Aluno";
    private EditText campoEmail;
    private EditText campoNome;
    private EditText campoTelefone;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR); //como é constante, transforma em constante ctrl + alt+ c
        inicializaCampos();
        configuraBotao();
    }

    private void configuraBotao() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = criaAluno();

                salva(aluno, dao);
                startActivity(new Intent(FormularioAlunoActivity.this, ListaAlunosActivity.class));


                //Toast.makeText(FormularioAlunoActivity.this, aluno.getEmail() +aluno.getNome()+aluno.getTelefone() , Toast.LENGTH_SHORT).show();
                //Caso queira criar uma aviso flutuante
                //Toast.makeText(FormularioAlunoActivity.this, "clicado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inicializaCampos() {
        campoEmail= findViewById(R.id.activity_formulario_aluno_email);
        campoNome= findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone= findViewById(R.id.activity_formulario_aluno_telefone);
    }

    private void salva(Aluno aluno, AlunoDAO dao) {
        dao.salva(aluno);
        finish();
    }

    @NonNull
    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String telefone = campoTelefone.getText().toString();

        Aluno aluno = new Aluno(nome, telefone, email);
        return aluno;
    }
}