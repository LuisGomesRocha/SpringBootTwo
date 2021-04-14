# SpringBootTwo

<p align="justify">🚀 Já temos muitos alunos e alunas cadastradas e agora temos muito acesso para visualizar os perfis. Além disso, temos um novo endpoint de listagem e essa lista só cresce. Para fechar, é mais do que importante a gente controlar o acesso a tais informações. Dado que as informações cadastradas dos alunos e alunas quase nunca muda, o que você faria para evitar que a recuperação dessa informação fosse feita sempre a partir do banco de dados? 
Na listagem é importante trabalharmos com dados paginados. Descreva em detalhes os passos de implementação que você faria para possibilitar que a aplicação cliente pudesse acessar as informações de paginada e porque realizar a paginação é importante. Para fechar, descreva como funciona o mecanismo de autenticação e autorização para uma API Rest através de tokens. o 🚀 </p>

<h1 align="center">
    <a href="https://spring.io/projects/spring-boot">🔗 Spring Boot </a>
</h1>
<p align="center">🚀 Formulário de proposta de solução - Spring Boot API Rest: Segurança da API, Cache e Monitoramento 🚀 </p>

<h4 align="center"> 
	🚧  🚀 Spring Boot...  🚧
</h4>


<h2 align="center"> Primeiro desafio: <a href=" https://github.com/LuisGomesRocha/SpringBootOne">🔗 SpringBootOne</a> </h2>

- [x] Cadastrar novos(as) alunos(as) (Vimos no SpringBootOne)
- [x] O nome do aluno não pode ter mais de 30 caracteres (Vimos no SpringBootOne)
- [x] O email do aluno pode ter no máximo 30 caracteres (Vimos no SpringBootOne)
- [x] Idade dos alunos precisa ser maior ou igual a 18 anos (Vimos no SpringBootOne)
- [x] Retornar um status 200 para a aplicação cliente em caso de sucesso ou 400 em caso de falha de validação (Vimos no SpringBootOne)
- [x] Detalhes de cada aluno(a) possam ser acessados (Vimos no SpringBootOne)
- [x] Identificação do(a) aluno(a) será feita pelo id do banco de dados e deve fazer parte do endereço de acesso (Vimos no SpringBootOne)
- [x] Para o detalhe, só precisamos exibir o nome e o email (Vimos no SpringBootOne)


<h1 align="center">
	SpringBootTwo
</h1>

- [x] Evitar que a recuperação dessa informação fosse feita sempre a partir do banco de dados?
- [x] Na listagem é importante trabalharmos com dados paginados e porque realizar a paginação é importante.
- [x] Autorização para uma API Rest através de tokens.


<p align="justify"> :robot: Já temos muitos alunos e alunas cadastradas e agora temos muito acesso para visualizar os perfis. Além disso, temos um novo endpoint de listagem e essa lista só cresce. Para fechar, é mais do que importante a gente controlar o acesso a tais informações. Dado que as informações cadastradas dos alunos e alunas quase nunca muda, o que você faria para evitar que a recuperação dessa informação fosse feita sempre a partir do banco de dados?   :robot: </p>

- [x] Evitar que a recuperação dessa informação fosse feita sempre a partir do banco de dados?

<p align="justify"> :robot: A ideia da anotação @Cacheable é que você pode usá-la para marcar os valores de retorno do método que serão armazenados no cache. Essa anotação pode ser aplicada tanto na declaração do método quanto na declaração do tipo. Quando aplicado na declaração do método, o valor de retorno do método que recebeu a anotação  armazenado em cache. Quando aplicado na declaração do tipo, então o valor de retorno de cada método é armazenado em cache.  :robot: </p>



### Carregar a dependência no pom.xml:
<p align="justify"> ⛹️‍♂️ Outra dependência que deveríamos colocar é de qual a ferramenta, o provedor de cache que quero utilizar para aplicação. Com a aplicação rodando em produção, o ideal é utilizar algum provedor de cache. O Spring suporte alguns provedores, mas para fins de desenvolvimento, não precisamos usar nenhum provedor. Se não declararmos um provedor, o Spring usa um padrão, que é onde ele guarda o cache em memória usando um REST map. Mas esse provider não é recomendado para usar em produção. ⛹️‍♂️ </p>

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

### Inserir a anotação @EnableCaching no arquivo SpringBootTwoApplication.java:

```
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class SpringBootTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTwoApplication.class, args);
	}

}
```
### No CadastroController selecionamos o método que vai receber o cache:

<p align="justify"> ⛹️‍♂️  Vou colocar na classe buscarTudo presente no CadastroController a anotação @Cacheable, para indicar ao Spring guardar o retorno desse método em cache. Essa anotação tem um atributo que precisamos preencher chamad value, que temos que passar uma string responsável para identificadar esse cache. Na nossa aplicação, é possivel ter vários métodos anotados com @Cacheable, e o Spring precisa saber como ele vai diferenciar um do outro. :robot: </p>

```
@GetMapping
@Cacheable(value = "buscarTudo")
	public ResponseEntity<Page<Cadastro>> buscarTudo(@RequestParam int pagina, @RequestParam int qtd) {
	Pageable cadastro = (Pageable) PageRequest.of(pagina, qtd);
	return ResponseEntity.status(HttpStatus.OK).body(cadastroService.buscarTudo(cadastro));

}
	
```

<p align="justify"> ⛹️‍♂️  Na listagem é importante trabalharmos com dados paginados. Descreva em detalhes os passos de implementação que você faria para possibilitar que a aplicação cliente pudesse acessar as informações de paginada e porque realizar a paginação é importante. :robot: </p>

- [x] Na listagem é importante trabalharmos com dados paginados e porque realizar a paginação é importante.

<p align="justify"> ⛹️‍♂️  A paginação pode ser utilizada quando é feita uma requisição de consulta. Serve para filtrar a quantidade de registros que você quer buscar melhorando a eficiência da aplicação. Exemplo, se você estiver realizando uma consulta em uma base de dados muito grande, além dos filtros da sua query, você também pode restringir a quantidade de registros que deseja retornar em um intervalo de páginas reduzindo o tempo de espera e o "peso" da requisição. :robot: </p>

<p align="justify"> ⛹️‍♂️ No método que vamos selecionar para implementar a paginação (tipo GET), teremos os parâmetros "passados" pelo Postman através da url, diferente do método POST, que tem um parâmetro, mas está com @RequestBody, ou seja, o parâmetro vem no corpo da requisição, no formato JSON, no método lista esses parâmetros não vêm no corpo da requisição. :robot: </p>

```
http://localhost:8080/api/cadastro/listar?pagina=0&qtd=0

```

<p align="justify"> ⛹️‍♂️ No geral, para esse tipo de parâmetro é interessante colocar uma anotação, que é o @RequestParam, para avisar ao Spring que é um parâmetro de request. Automaticamente, quando você coloca essa anotação, o Spring considera que o parâmetro é obrigatório. Se chamarmos esse endereço e não passarmos o parâmetro, o Spring vai jogar um erro 400 para o cliente dizendo que tem um parâmetro obrigatório que ele não enviou.Desta forma as variáveis página e quantidade serão obrigatórios, obrigando o cliente da API a sempre passar esses parâmetros, para sempre termos a paginação. :robot: </p>

	
<p align="justify"> ⛹️‍♂️ Para fazer paginação, precisamos criar uma variável do tipo pageable, que é uma classe do Spring data que serve justamente para fazer paginação. Para criar essa interface pageable, é preciso importar a org.springframework.data. No  CadastroController utilizamos um método estático chamado of, em que passamos a página e a quantidade. Com isso, ele cria um objeto do tipo pageable. :robot: </p>

### No CadastroController no método buscarTudo:

```
@GetMapping
	@Cacheable(value = "buscarTudo")
	public ResponseEntity<Page<Cadastro>> buscarTudo(@RequestParam int pagina, @RequestParam int qtd) {
		Pageable cadastro = (Pageable) PageRequest.of(pagina, qtd);
		return ResponseEntity.status(HttpStatus.OK).body(cadastroService.buscarTudo(cadastro));
}
```

<p align="justify"> ⛹️‍♂️ Por sua vez dentro do CadastroRepository verificamos a existencia de vários findall (devido a extenção da classe JpaRepository para o  CadastroRepository), dentre eles têm um que recebe um pageable como parâmetro. Então, podemos passar esse paginação como parâmetro para o método, que o Spring data automaticamente vai saber que você quer fazer paginação e vai usar os parâmetros página e quantidade para saber qual o primeiro registro e quantos registros ele vai carregar.:robot: </p> 
	
### No CadastroRepository:
```
CadastroRepository
@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

	Optional<Cadastro> findByEmail(String email);
	Page<Cadastro> findAll(Pageable pageable);

}
```

<p align="justify"> ⛹️‍♂️ Quando usamos paginação, o retorno do método findall não é mais um list, e sim outra classe chamada page, contendo a lista com os registros,  informações do número de páginas, qual a página atual, quantos elementos tem no total. :robot: </p> 

```	
{
  "content": [
       {
          "id": 1,
          "nome": "Aluno",
          "email": "aluno@email.com",
          "idade": 22,
          "enabled": true,
          "username": "aluno@email.com",
          "credentialsNonExpired": true,          
	  "accountNonExpired": true,
          "authorities": [],
          "accountNonLocked": true
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 1,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalElements": 7,
    "totalPages": 7,
    "number": 0,
    "size": 1,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}

```

<p align="justify"> ⛹️‍♂️ Por fim necessitamos configurar nosso CadastroService para receber um page "Page<Cadastro> paginaCadastro = cadastroRepository.findAll(cadastro);" alterando assim o retorno do método, que agora não vai ser mais um list, vai ser um page. ⛹️‍♂️ </p> 
	
### No CadastroService:

```
public Page<Cadastro> buscarTudo(Pageable cadastro) {
	Page<Cadastro> paginaCadastro = cadastroRepository.findAll(cadastro);
	return paginaCadastro;	
}
```

<p align="justify"> 👮 Para fechar, descreva como funciona o mecanismo de autenticação e autorização para uma API Rest através de tokens 👮</p> 

- [x] Autorização para uma API Rest através de tokens.

<p align="justify"> 👮 Inicialmente será adicionado ao pom.xml duas dependência: starter-security, que é o módulo de segurança do Spring Boot e a jsonwebtoken JWT (JSON Web Token) método RCT 7519 padrão da indústria para realizar autenticação entre duas partes por meio de um token assinado que autentica uma requisição web. 👮 </p> 

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.9.0</version>
</dependency>
```

### AutenticacaoService

<p align="justify"> 👮 Com a criação da classe SecurityConfigurations, implementamos a anotação @EnableWebSecurity junto com a extensão do método WebSecurityConfigurerAdapter que juntos fornecem o principio básico da segurança web da API, sendo capaz em apenas algumas linhas de código exigir que o usuário seja autenticado antes de acessar qualquer URL em nosso aplicativo, criar um usuário com o nome de usuário e senha, ativa a autenticação HTTP básica e baseada em formulário Spring Security redenrizado automaticamente (uma página de login e uma página de logout), login-processing-url só será processado para HTTP POST e a página de login só será processada para HTTP GET.👮 </p> 

<p align="justify"> 👮 Através da anotação @Configuration é indicado ao spring que determinada classe possui métodos que expõe novos beans, sendo ela no caso implementada na  SecurityConfigurations através do configure(HttpSecurity http), permitindo acesso livre ao método listar (.antMatchers(HttpMethod.GET, "/api/cadastro/listar").permitAll()), h2-console (.antMatchers(HttpMethod.GET, "/h2-console/*").permitAll()) e auth (.antMatchers(HttpMethod.POST, "/auth").permitAll()).👮 </p> 
	

```
@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private CadastroRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Cadastro> cadastro = repository.findByEmail(email);
		if (cadastro.isPresent()) {
			return cadastro.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidos!");
	}
}
```

AutenticacaoViaTokenFilter

```
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private CadastroRepository repository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, CadastroRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		if (valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Cadastro cadastro = repository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(cadastro, null, cadastro.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
```

SecurityConfigurations

<p align="justify"> 👮 Com a criação da classe SecurityConfigurations, implementamos a anotação @EnableWebSecurity junto com a extensão do método WebSecurityConfigurerAdapter que juntos fornecem o principio básico da segurança web da API, sendo capaz em apenas algumas linhas de código exigir que o usuário seja autenticado antes de acessar qualquer URL em nosso aplicativo, criar um usuário com o nome de usuário e senha, ativa a autenticação HTTP básica e baseada em formulário Spring Security redenrizado automaticamente (uma página de login e uma página de logout), login-processing-url só será processado para HTTP POST e a página de login só será processada para HTTP GET.👮 </p> 

<p align="justify"> 👮 Através da anotação @Configuration é indicado ao spring que determinada classe possui métodos que expõe novos beans, sendo ela no caso implementada na  SecurityConfigurations através do configure(HttpSecurity http), permitindo acesso livre ao método listar (.antMatchers(HttpMethod.GET, "/api/cadastro/listar").permitAll()), h2-console (.antMatchers(HttpMethod.GET, "/h2-console/*").permitAll()) e auth (.antMatchers(HttpMethod.POST, "/auth").permitAll()).👮 </p> 
	
```
@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private CadastroRepository cadastroRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configuracoes de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/cadastro/listar").permitAll()
		.antMatchers(HttpMethod.GET, "/h2-console/*").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, cadastroRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	//Configuracoes de recursos estaticos(js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
}
```

TokenService

```
@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Cadastro logado = (Cadastro) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API Spring Boot II")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
```





	só para colocarmos coisas relacionadas a segurança dentro desse pacote. E vou criar uma classe chamada, por exemplo, securityConfigurations. A ideia é que dentro dessa classe estarão todas as configurações de segurança do nosso projeto. 
	
	. Vou criar uma nova classe, colocar no pacote security, 

	
	
	👮</p> 


[02:43] É uma classe Java, não tem nada a ver com Spring. Tenho que habilitar a parte do Spring security. Para fazer isso, fazemos na própria classe. Existe uma anotação chamada @EnableWebSecurity. Como essa é uma classe que tem configurações, precisamos colocar a anotação @Configuration. O Spring vai carregar e ler as configurações que estiverem dentro dessa classe.

[03:20] Além disso, vamos ter que herdar essa classe de outra classe do Spring chamada web security configurer adapter. Essa classe tem alguns métodos para fazer as configurações que vamos sobrescrever posteriormente.

[03:38] É isso. Nós colocamos a dependência do Spring security no projeto, criamos a classe, anotada com @EnableWebSecurity, com @Configuration. Dentro, depois, vamos colocar as configurações de segurança. Por enquanto está vazio, mas só de ter feiro isso já habilitamos a parte de segurança. Por padrão, o Spring bloqueia todo acesso à nossa API. Tudo está restrito até que eu faça a configuração e libere o que precisa ser liberado.

[04:05] Já podemos testar. No Postman, vou tentar disparar uma requisição para aquele nosso endpoint/tópicos, que era o endpoint que trazia uma lista com todos os tópicos do projeto. Ele não voltou a lista. Devolveu o código 401. Não tenho autorização. Ou seja, o Spring security está habilitado e o padrão dele é bloquear tudo. Com isso, terminamos nessa aula. No próximo vídeo vamos começar a fazer as configurações, para ensinar ao Spring o que é público e o que não é.
