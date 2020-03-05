package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired	
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa){
		Pessoa pessoaSalva = this.pessoaRepository.findById(codigo).orElseThrow(()
				-> new EmptyResultDataAccessException( 1));
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return this.pessoaRepository.save(pessoaSalva);
	}
	
	
	
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		return this.pessoaRepository.findById(codigo)
		   .map(pessoa -> ResponseEntity.ok(pessoa))
		      .orElse(ResponseEntity.notFound().build());
		}



	

}
