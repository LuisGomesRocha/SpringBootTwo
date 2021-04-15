package br.com.zup.SpringBootTwo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.zup.SpringBootTwo.domain.Cadastro;
import br.com.zup.SpringBootTwo.repository.CadastroRepository;

@Service
public class CadastroService {

	
	@Autowired
	CadastroRepository cadastroRepository;

	// Cadastrar

	public Cadastro cadastrar(Cadastro cadastro) throws Exception {

		return cadastroRepository.save(cadastro);

	}

	// Buscar
	public Cadastro buscar(Long id) {

		return cadastroRepository.findById(id).get();

	}	

	public Page<Cadastro> buscarTudo(Pageable cadastro) {
		
		Page<Cadastro> paginaCadastro = cadastroRepository.findAll(cadastro);
		return paginaCadastro;		
		
	}

	
}
