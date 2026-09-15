package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import entity.Cliente;
import repository.ClienteRepository;

@RestController 
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = repository.save(cliente);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = repository.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
    Optional<Cliente> clienteDesatualizado = repository.findById(id);

    if (clienteDesatualizado.isPresent()) {
        Cliente clienteAtualizado = clienteDesatualizado.get();
        clienteAtualizado.setNome_cliente(cliente.getNome_cliente());

        repository.save(clienteAtualizado);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
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