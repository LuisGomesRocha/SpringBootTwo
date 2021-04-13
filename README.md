# SpringBootTwo
Formulário de proposta de solução - Spring Boot API Rest: Segurança da API, Cache e Monitoramento

Já temos muitos alunos e alunas cadastradas e agora temos muito acesso para visualizar os perfis. Além disso, temos um novo endpoint de listagem e essa lista só cresce. Para fechar, é mais do que importante a gente controlar o acesso a tais informações. 

Dado que as informações cadastradas dos alunos e alunas quase nunca muda, o que você faria para evitar que a recuperação dessa informação fosse feita sempre a partir do banco de dados? 
Na listagem é importante trabalharmos com dados paginados. Descreva em detalhes os passos de implementação que você faria para possibilitar que a aplicação cliente pudesse acessar as informações de paginada e porque realizar a paginação é importante.
Para fechar, descreva como funciona o mecanismo de autenticação e autorização para uma API Rest através de tokens. 






<h1 align="center">
    <a href="https://www.java.com/pt-BR/">🔗 Spring Boot </a>
</h1>
<p align="center">🚀 Formulário de proposta de solução - Spring Boot API Rest: Segurança da API, Cache e Monitoramento 🚀 </p>

<h4 align="center"> 
	🚧  🚀 Spring Boot...  🚧
</h4>

### Features 
<h2 align="center"> Primeiro desafio: <a href=" https://github.com/LuisGomesRocha/SpringBootOne">🔗 SpringBootOne</a> </h2>

- [x] Cadastrar novos(as) alunos(as) (Vimos no SpringBootOne)
- [x] O nome do aluno não pode ter mais de 30 caracteres (Vimos no SpringBootOne)
- [x] O email do aluno pode ter no máximo 30 caracteres (Vimos no SpringBootOne)
- [x] Idade dos alunos precisa ser maior ou igual a 18 anos (Vimos no SpringBootOne)
- [x] Retornar um status 200 para a aplicação cliente em caso de sucesso ou 400 em caso de falha de validação (Vimos no SpringBootOne)
- [x] Detalhes de cada aluno(a) possam ser acessados (Vimos no SpringBootOne)
- [x] Identificação do(a) aluno(a) será feita pelo id do banco de dados e deve fazer parte do endereço de acesso (Vimos no SpringBootOne)
- [x] Para o detalhe, só precisamos exibir o nome e o email (Vimos no SpringBootOne)


- [ok] Evitar que a recuperação dessa informação fosse feita sempre a partir do banco de dados?
- [ok] Na listagem é importante trabalharmos com dados paginados e porque realizar a paginação é importante.
- [ok] Autorização para uma API Rest através de tokens.


 


<p align="justify"> :robot: Usando o que foi visto durante o curso,  descreva todos os passos que você faria desde conseguir tratar a requisição feita para determinado endereço até retornar as informações do(a) aluno(a) em formato JSON.  :robot: </p>

- [x] O nome do aluno não pode ter mais de 30 caracteres
- [x] O email do aluno pode ter no máximo 30 caracteres
- [x] Idade dos alunos precisa ser maior ou igual a 18 anos

<p align="justify"> :robot: Inicialmente vou criar minha classe cadastro "SpringBootOne/domain/Cadastro", adiciono os atributos necessários e também adiciono um id. Uso o @Entity em cima da classe e o @Id em cima do atributo id. @Entity explica para o Hibernate que aquela classe vai ser uma tabela no banco e o @Id explica que aquele atributo vai ser a chave primaria na tabela. Utilizo tambem @GeneratedValue(strategy = GenerationType.IDENTITY) para que o id seja incrementado de forma automática. Os outros atributos eu não preciso mapear porque ele já vai mapear para colunas do mesmo nome.:robot: </p>

```
@Entity
public class Cadastro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
```
  

<p align="justify"> :robot: Utilizando no atributo nome as anotações: @NotBlank(message = "nome em branco!") 	@Size(min = 3, max = 30, message = "Nome deve conter de 3 a 30 caracteres!") 	@Column (unique = true), não vamos permitir que o atributo receba um valor vazio ou nulo, bem como deve possuir o minimo de 3 caracteres e máximo de 30, sendo o valor inserido único no banco de dados, não sendo aceito caso seja repetido ("unique"). :robot: </p>

```
	@NotBlank(message = "nome em branco!")
	@Size(min = 3, max = 30, message = "Nome deve conter de 3 a 30 caracteres!")
	@Column (unique = true) 
	private String nome;
```

<p align="justify"> :robot: Utilizando no atributo email as anotações: @NotBlank, @Size e @Email não permitindo que o atributo receba um valor vazio ou nulo, bem como deve possuir o minimo de 3 caracteres e máximo de 30 e por fim para verificar se o valor corresponde a um endereço de e-mail válido.:robot: </p>

```
@Size(min = 3, max = 30, message = "Email deve conter de 3 a 30 caracteres!")
	@NotBlank(message = "email em branco!")
	@Email(message = "email inválido")
	private String email;
 ```

<p align="justify"> :robot: Para que o atributos idade nao receba valores nulos ou em branco, bem como a  idade cadastrada sejam maior ou igual a 18 vamos utilizar as anotações @NotBlank e @Min. :robot: </p>

 ```
	@NotNull(message = "Data em branco!")
	@Min(value = 18 , message = "Idade necessita ser maior ou igual a 18 anos")
	private Integer idade;
 ```
 
 - [x] Cadastrar novos(as) alunos(as)
 
<p align="justify"> :robot: Inicialmente os dados serão injetados pelo método POST pelo Postman no endereço mapeado no CadastroController como "@RequestMapping(value = "/api/cadastro")", a requisição será recebida pelo controller pela função novoCadastro e atribuindo as características do objeto implementado pelo CadastroRequest (evitando que minha entidade descrita na classe Cadastro seja exposta). Ainda no CadastroController, teremos a injeção de dependencia CadastroRepository através da anotação @Autowired, passando para a função "cadastroService.cadastrar(cadastro)" que por sua vez vai utilizar o método "save" do cadastroRepository (que recebeu do JpaRepository os métodos HTTP), salvando os dados (uma vez validados), no banco de dados.:robot: </p>


CadastroRequest
```
public class CadastroRequest {
	
	@NotBlank(message = "nome em branco!")
	@Size(min = 3, max = 30, message = "Nome deve conter de 3 a 30 caracteres!")
	private String nome;
	@Size(min = 3, max = 30, message = "Email deve conter de 3 a 30 caracteres!")
	@NotBlank(message = "email em branco!")
	@Email(message = "email inválido")
	private String email;
	@NotNull(message = "Data em branco!")
	@Min(value = 18 , message = "Idade necessita ser maior ou igual a 18 anos")
	private Integer idade;
	
	public Cadastro toModel() {
		return new Cadastro(this.nome, this.email, this.idade);
	}
```

CadastroRepositori

```
@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

}
```
CadastroService
```
@Service
public class CadastroService {

	@Autowired
	CadastroRepository cadastroRepository;

	// Cadastrar

	public Cadastro cadastrar(Cadastro cadastro) throws Exception {

		return cadastroRepository.save(cadastro);
	}
 ```
 
 CadastroController

 ```
@RestController
@RequestMapping(value = "/api/cadastro")
public class CadastroController {

	@Autowired
	CadastroService cadastroService;
	

	// Criar Cadastro
	@PostMapping
	public ResponseEntity<CadastroResponse> novoCadastro(@Validated  @RequestBody CadastroRequest cadastroRequest) throws Exception {

		Cadastro cadastro = cadastroRequest.toModel();
		cadastroService.cadastrar(cadastro);
		CadastroResponse cadastroResponse = cadastro.toResponse();
		
		return ResponseEntity.status(HttpStatus.OK).body(cadastroResponse);
		
	}
 ```
 
 - [x] Retornar um status 200 para a aplicação cliente em caso de sucesso ou 400 em caso de falha de validação

<p align="justify"> :robot: O retorno 200 será implementado no retorno do método "novoCadastro" implementado no CadastroController, e o retorno 400 em caso de falha foi implementado na classe ServiceExceptionHandler no método "validation".:robot: </p>

 ```
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
    	
    	List <String> msgs = new ArrayList<>();
    	
    	for (FieldError x : e.getBindingResult().getFieldErrors()) {
            msgs.add(x.getDefaultMessage());            
    		
        }
    	
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), msgs, System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
 ```
 E
 
  ```     
    	// Criar Cadastro
	@PostMapping
	public ResponseEntity<CadastroResponse> novoCadastro(@Validated  @RequestBody CadastroRequest cadastroRequest) throws Exception {

		Cadastro cadastro = cadastroRequest.toModel();
		cadastroService.cadastrar(cadastro);
		CadastroResponse cadastroResponse = cadastro.toResponse();
		
		return ResponseEntity.status(HttpStatus.OK).body(cadastroResponse);
		
	}
  ``` 
  
- [x] Detalhes de cada aluno(a) possam ser acessados
- [x] Identificação do(a) aluno(a) será feita pelo id do banco de dados e deve fazer parte do endereço de acesso

<p align="justify"> :robot: Para que os detalhes de cada aluno(a) possam foi implementado a função "buscar", fazendo a pesquisa no banco de dados através do "id" do aluno.:robot: </p>

  ``` 
	// Buscar Cadastro
	@GetMapping("/{id}")

	public ResponseEntity<CadastroResponse> buscar(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(cadastroService.buscar(id).toResponse());

	}
  ``` 
  
  - [x] Para o detalhe, só precisamos exibir o nome e o email
  
  <p align="justify"> :robot: Uma vez feito o salvamento dos alunos será retornado para o usuário apenas email e nome, implementados pela classe CadastroResponse :robot: </p>
 
   ``` 
 public CadastroResponse toResponse() {
		return new CadastroResponse(this.email, this.nome);
	}
	
  ``` 
