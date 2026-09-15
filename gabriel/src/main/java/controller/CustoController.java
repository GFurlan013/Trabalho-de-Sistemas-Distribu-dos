package controller;

import entity.Custo;
import repository.CustoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/custo")
public class CustoController {

    @Autowired
    private CustoRepository repository;

    @GetMapping
    public List<Custo> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Custo> salvar(@RequestBody Custo custo) {
        Custo novo = repository.save(custo);
        return new ResponseEntity<>(novo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Custo> atualizar(@RequestBody Custo custo, @PathVariable Long id) {
        Optional<Custo> custoDesatualizado = repository.findById(id);
        if (custoDesatualizado.isPresent()) {
            Custo custoAtualizado = custoDesatualizado.get();
            custoAtualizado.setValor(custo.getValor());
            custoAtualizado.setProjeto(custo.getProjeto());
            repository.save(custoAtualizado);
            return new ResponseEntity<>(custoAtualizado, HttpStatus.OK);
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