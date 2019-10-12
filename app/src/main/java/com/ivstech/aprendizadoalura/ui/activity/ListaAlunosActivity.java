package com.ivstech.aprendizadoalura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ivstech.aprendizadoalura.R;
import com.ivstech.aprendizadoalura.dao.AlunoDAO;
import com.ivstech.aprendizadoalura.model.Aluno;

import java.util.List;

import static com.ivstech.aprendizadoalura.ui.activity.ConstantesActivities.CHAVE_ALUNO;
import static com.ivstech.aprendizadoalura.ui.activity.ConstantesActivities.TITULO_APPBAR;

public class ListaAlunosActivity extends AppCompatActivity {

    private AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;
    private ListView listaDeAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);

        listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);

        configuraFabNovoAluno();

        configuraLista();
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoALuno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoALuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {

        configuraAdapter(listaDeAlunos);

        configuraListenerDeCliquePorItem(listaDeAlunos);

        configuraListenerDeCliqueProlongadoPorItem(listaDeAlunos);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);

                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void configuraListenerDeCliqueProlongadoPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoSelecionado = (Aluno) adapterView.getItemAtPosition(position);
                dao.remove(alunoSelecionado);
                adapter.remove(alunoSelecionado);
                return false;
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1
        );
        listaDeAlunos.setAdapter(adapter);
    }
}
