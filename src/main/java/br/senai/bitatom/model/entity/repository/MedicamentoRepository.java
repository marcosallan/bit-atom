package br.senai.bitatom.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.bitatom.model.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {}
