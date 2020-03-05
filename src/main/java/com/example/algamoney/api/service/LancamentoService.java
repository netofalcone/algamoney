package com.example.algamoney.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;


@Service
public class LancamentoService {
	
	@Autowired 
	private LancamentoRepository lancamentoRepository;	
	
	@Autowired	
	private PessoaRepository pessoaRepository;		
	
	 public Lancamento salvar( Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if (!pessoa.isPresent() || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
						
	}
	 
	 @GetMapping
		public List<Lancamento> listar() {			
			return lancamentoRepository.findAll();		
		}
	 
	
		public void remover(@PathVariable Long codigo) {
		  this.lancamentoRepository.deleteById(codigo);
		}



}
