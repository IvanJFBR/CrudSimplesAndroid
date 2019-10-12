package com.ivstech.aprendizadoalura.dao;

import com.ivstech.aprendizadoalura.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public boolean verificaAluno(Aluno aluno) {
        if(getAlunoById(aluno) != null){
            return true;
        }

        return false;
    }

    public Aluno getAlunoById(Aluno aluno){
        for (Aluno a : alunos) {
            if (aluno != null && a.getId() == aluno.getId()) {
                return a;
            }
        }

        return null;
    }

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);

        //Contador para atualizar ID's
        contadorDeIds++;
    }

    public void updateAluno(Aluno aluno) {
        Aluno alunoRecebido = getAlunoById(aluno);

        if(alunoRecebido != null){
            alunos.set(alunos.indexOf(alunoRecebido), aluno);
        }
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoRecebido = getAlunoById(aluno);

        if(alunoRecebido != null){
            alunos.remove(alunoRecebido);
        }
    }
}
