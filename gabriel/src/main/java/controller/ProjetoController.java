package controller;

import entity.Projeto;
import repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoRepository repository;

    @GetMapping
    public List<Projeto> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Projeto> salvar(@RequestBody Projeto projeto) {
        Projeto novo = repository.save(projeto);
        return new ResponseEntity<>(novo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@RequestBody Projeto projeto, @PathVariable Long id) {
        Optional<Projeto> projetoDesatualizado = repository.findById(id);
        if (projetoDesatualizado.isPresent()) {
            Projeto projetoAtualizado = projetoDesatualizado.get();
            projetoAtualizado.setNome_projeto(projeto.getNome_projeto());
            projetoAtualizado.setCliente(projeto.getCliente());
            repository.save(projetoAtualizado);
            return new ResponseEntity<>(projetoAtualizado, HttpStatus.OK);
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