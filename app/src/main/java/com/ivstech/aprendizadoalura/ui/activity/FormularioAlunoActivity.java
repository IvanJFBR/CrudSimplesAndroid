package com.ivstech.aprendizadoalura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ivstech.aprendizadoalura.R;
import com.ivstech.aprendizadoalura.dao.AlunoDAO;
import com.ivstech.aprendizadoalura.model.Aluno;

import static com.ivstech.aprendizadoalura.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita Aluno";
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private String nome;
    private String telefone;
    private String email;
    private AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        inicializacaoDosCampos();

        configuraBotaoSalvar();

        carregarAluno();
    }

    private void carregarAluno() {
        Intent dados = getIntent();

        if(dados.hasExtra(CHAVE_ALUNO)){
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            if(aluno != null){
                preencheCampos();
                setTitle(TITULO_APPBAR_EDITA_ALUNO);
            }
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
        }
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dao.verificaAluno(aluno)){
                    autalizaAluno();
                } else {
                    criaAluno();
                }
                finish();
            }
        });
    }

    private void criaAluno() {
        getFormularioInfo();

        dao.salva(new Aluno(nome, telefone, email));
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void autalizaAluno() {
        getFormularioInfo();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);

        dao.updateAluno(aluno);
    }

    public void preencheCampos(){
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    public void getFormularioInfo(){
        nome = campoNome.getText().toString();
        telefone = campoTelefone.getText().toString();
        email = campoEmail.getText().toString();
    }
}
