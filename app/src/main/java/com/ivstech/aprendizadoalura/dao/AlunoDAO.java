package com.ivstech.aprendizadoalura.dao;

import android.util.Log;

import com.ivstech.aprendizadoalura.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public boolean verificaAluno(Aluno aluno) {
        for (Aluno a : alunos) {
            if (aluno != null && a.getId() == aluno.getId()) {
                return true;
            }
        }

        return false;
    }

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);

        //Contador para atualizar ID's
        contadorDeIds++;
    }

    public void updateAluno(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                alunos.set(alunos.indexOf(a), aluno);
                break;
            }
        }
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
