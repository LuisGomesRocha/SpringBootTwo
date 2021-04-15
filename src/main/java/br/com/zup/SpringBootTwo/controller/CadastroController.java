package br.com.zup.SpringBootTwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.SpringBootTwo.domain.Cadastro;
import br.com.zup.SpringBootTwo.dto.CadastroRequest;
import br.com.zup.SpringBootTwo.dto.CadastroResponse;
import br.com.zup.SpringBootTwo.service.CadastroService;

@RestController
@RequestMapping(value = "/api/cadastro")
public class CadastroController {

	@Autowired
	CadastroService cadastroService;

	// Criar Cadastro
	@PostMapping
	public ResponseEntity<CadastroResponse> novoCadastro(@Validated @RequestBody CadastroRequest cadastroRequest)
			throws Exception {

		Cadastro cadastro = cadastroRequest.toModel();
		cadastroService.cadastrar(cadastro);
		CadastroResponse cadastroResponse = cadastro.toResponse();

		return ResponseEntity.status(HttpStatus.OK).body(cadastroResponse);

	}

	// Buscar Cadastro
	@GetMapping("/{id}")
	public ResponseEntity<CadastroResponse> buscar(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(cadastroService.buscar(id).toResponse());

	}

	@GetMapping("/listar")
	@Cacheable(value = "buscarTudo")
	public ResponseEntity<Page<Cadastro>> buscarTudo(@RequestParam int pagina, @RequestParam int qtd) {
		Pageable cadastro = (Pageable) PageRequest.of(pagina, qtd);
		return ResponseEntity.status(HttpStatus.OK).body(cadastroService.buscarTudo(cadastro));

	}

}
