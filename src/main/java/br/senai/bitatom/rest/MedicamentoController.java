package br.senai.bitatom.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.senai.bitatom.model.entity.Medicamento;
import br.senai.bitatom.model.entity.repository.MedicamentoRepository;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin("http://localhost:4200")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Medicamento post(@RequestBody @Valid Medicamento medicamento) {
		return medicamentoRepository.save(medicamento);
	}
	
	@GetMapping("{id}")
	public Medicamento get(@PathVariable Integer id) {
		return medicamentoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento não encontrado"));
	}
	
	@GetMapping
	public List<Medicamento> getAll() {
		return medicamentoRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		medicamentoRepository
		.findById(id)
		.map(medicamento -> {
			medicamentoRepository.delete(medicamento);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento não encontrado"));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@PathVariable Integer id, @RequestBody Medicamento medicamentoAtualizado) {
		medicamentoRepository
		.findById(id)
		.map(medicamento -> {
			medicamentoAtualizado.setId(medicamento.getId());
			return medicamentoRepository.save(medicamentoAtualizado);
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento não encontrado"));
	}
}
