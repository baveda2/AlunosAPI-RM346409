package br.com.andrebaveda.AlunosAPI.controller;

import br.com.andrebaveda.AlunosAPI.dto.AlunoDTO;
import br.com.andrebaveda.AlunosAPI.dto.NovoAlunoDTO;
import br.com.andrebaveda.AlunosAPI.entity.Aluno;
import br.com.andrebaveda.AlunosAPI.repository.AlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("alunos")
public class AlunosController {

    private final AlunoRepository alunoRepository;

    public AlunosController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    private List<AlunoDTO> alunoDTOList;

    @GetMapping
    public List<Aluno> listarAlunos(){
        return alunoRepository.findAll();
    }

    @GetMapping ("{id}")
    public Aluno getAlunoById(@PathVariable(name = "id") int id){
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno adicionarAluno(@RequestBody Aluno novoAluno) {
        return alunoRepository.save(novoAluno);
    }

    @PutMapping("{id}")
    public Aluno atualizarNomeDoAluno(
            @RequestBody Aluno novoAluno,
            @PathVariable Integer id
    ) {
        Aluno alunoEncontrado = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        alunoEncontrado.setNome(novoAluno.getNome());

        return alunoRepository.save(alunoEncontrado);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAluno(@PathVariable int id) {
        alunoRepository.deleteById(id);
    }

    private  AlunoDTO buscarAlunoPorId(@PathVariable int id){
        return alunoDTOList.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
