package controller;

import entity.Funcionario;
import repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping
    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Funcionario> salvar(@RequestBody Funcionario funcionario) {
        Funcionario novo = repository.save(funcionario);
        return new ResponseEntity<>(novo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@RequestBody Funcionario funcionario, @PathVariable Long id) {
        Optional<Funcionario> funcionarioDesatualizado = repository.findById(id);
        if (funcionarioDesatualizado.isPresent()) {
            Funcionario funcionarioAtualizado = funcionarioDesatualizado.get();
            funcionarioAtualizado.setNome_funcionario(funcionario.getNome_funcionario());
            funcionarioAtualizado.setProjeto(funcionario.getProjeto());
            repository.save(funcionarioAtualizado);
            return new ResponseEntity<>(funcionarioAtualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}