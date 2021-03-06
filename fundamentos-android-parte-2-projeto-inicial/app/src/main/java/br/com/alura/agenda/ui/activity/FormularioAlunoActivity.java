package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;
    private static final String TITULO_APPBAR_NOVO_ALUNO="Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edição de aluno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent dados = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

        //Verifica se aluno existe
        aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
        if(dados.hasExtra(CHAVE_ALUNO)){
            //aluno é retomado como um parâmetro
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoEmail.setText(aluno.getEmail());
            campoTelefone.setText(aluno.getTelefone());
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
        }
        else{
            aluno = new  Aluno();
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId=item.getItemId();
        if(itemId ==  R.id.acticity_formulario_aluno_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    //verifica o carregamento do formulario, finalizando com edição ou salvamento
    private void finalizaFormulario() {
                preencheAluno();
                if (aluno.temIdValido()) {
                    dao.edita(aluno);
                } else {
                    dao.salva(aluno);
                }
                finish();
            }


                     /*ew View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno alunoCriado = criaAluno();
                salva(alunoCriado);
            )}
        }*/

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
        //return new Aluno(nome, telefone, email);
    }
}
