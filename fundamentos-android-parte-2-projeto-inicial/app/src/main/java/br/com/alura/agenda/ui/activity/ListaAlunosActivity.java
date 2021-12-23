package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;
import static br.com.alura.agenda.ui.activity.ConstantesActivities.TITULO_APPBAR;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {


    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraListenerDeCliquePorItem();
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));
        dao.salva(new Aluno("ze","4444","aaa@a"));
        dao.salva(new Aluno("jose","4444","aaa@a"));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.acticity_lista_alunos_menu, menu);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear(); //remove todos dados
        adapter.addAll(dao.todos()); //adiciona os dados
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void configuraListenerDeCliquePorItem() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        final List<Aluno> alunos = dao.todos();
        configuraAdapter(listaDeAlunos);
        configuraListenerDeCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.acticity_lista_alunos_remover) {
            //sem o if, qualquer coisa no menu executa a mesma ação
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

     private void remove(Aluno aluno){
        try {
            dao.remove(aluno);
            adapter.remove(aluno);
        } catch (Exception e){

        }
    }
    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                //Log.i("aluno", ""+ alunos.get(posicao));
                //Toast.makeText(ListaAlunosActivity.this, "Clicado", Toast.LENGTH_SHORT)
                //        .show();
                //O retorno vai ser sempre o esperado
                Aluno alunoEscolhido =(Aluno) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno alunoEscolhido) {
        Intent goToFormActivity=new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class);
        //para transferir objetos, deve ser serializável a classe do objeto
        goToFormActivity.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(goToFormActivity);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter =  new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaDeAlunos.setAdapter(adapter);
    }
}
