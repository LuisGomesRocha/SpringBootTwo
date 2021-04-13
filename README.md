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

<p align="justify"> ⛹️‍♂️ No método que vamos selecionar para implementar a paginação (tipo GET), teremos os parâmetros "passados" pelo Postman através da url, diferente do método POST, que tem um parâmetro, mas está com @RequestBody, ou seja, o parâmetro vem no corpo da requisição, no formato JSON, no método lista esses parâmetros não vêm no corpo da requisição. Eles vêm na própria url, como parâmetros de url. :robot: </p>
	
	

	
	[02:18] 

[02:47] No geral, para esse tipo de parâmetro é interessante colocar uma anotação, que é o @RequestParam, para avisar ao Spring que é um parâmetro de request. Automaticamente, quando você coloca essa anotação, o Spring considera que o parâmetro é obrigatório. Se chamarmos esse endereço e não passarmos o parâmetro, o Spring vai jogar um erro 400 para o cliente dizendo que tem um parâmetro obrigatório que ele não enviou.
Só que no caso, o nome do curso vai ser opcional. Então, nessa anotação tem um atributo chamado required, que vai ser igual a falso.

Já o int página e int quantidade também vou colocar o request param, para avisar para o Spring que são parâmetros de url, mas vou colocar como obrigatórios. Quero obrigar o cliente que está chamando a API a sempre passar os parâmetros de página e quantidade, para sempre termos a paginação.

[03:48] Nosso método agora tem três parâmetros, o nome do curso, a página e a quantidade. Na consulta não mudamos nada na implementação. Os parâmetros vão chegar, mas ele vai ignorar. Como faço para pegar esses parâmetros e na hora de chamar o repository, chamar o findall, ou o findbycurso, quero fazer a paginação.

	
	





@GetMapping
	@Cacheable(value = "buscarTudo")
	public ResponseEntity<Page<Cadastro>> buscarTudo(@RequestParam int pagina, @RequestParam int qtd) {
		Pageable cadastro = (Pageable) PageRequest.of(pagina, qtd);
		return ResponseEntity.status(HttpStatus.OK).body(cadastroService.buscarTudo(cadastro));

}




