# Consulta CEP

Aplicação Java de linha de comando que consulta um endereço a partir de um CEP utilizando a API pública [ViaCEP](https://viacep.com.br/), exibe o resultado no console e salva os dados em um arquivo `.json`.

## ✨ Funcionalidades

- Consulta de endereço por CEP em tempo real via API REST
- Exibição do endereço encontrado no console
- Geração automática de um arquivo JSON com o resultado (nomeado com o próprio CEP)
- Tratamento de erros de rede e de escrita de arquivo

## 🛠️ Tecnologias utilizadas

- **Java** (HttpClient nativo — `java.net.http`)
- **Gson** — serialização e desserialização de JSON
- **ViaCEP API** — fonte dos dados de endereço

## 📁 Estrutura do projeto

```
.
├── Main.java            # Ponto de entrada da aplicação
├── ConsultaCep.java      # Responsável por buscar o endereço na API ViaCEP
├── Endereco.java          # Record que representa o endereço retornado
└── GeradorArquivo.java   # Responsável por salvar o endereço em um arquivo .json
```

## ⚙️ Como funciona

1. O usuário informa um CEP pelo terminal.
2. `ConsultaCep` faz uma requisição HTTP GET para `https://viacep.com.br/ws/{cep}/json`.
3. A resposta em JSON é convertida em um objeto `Endereco` usando Gson.
4. O endereço é exibido no console.
5. `GeradorArquivo` salva o objeto `Endereco` em um arquivo `{cep}.json`, formatado (pretty print).

## ▶️ Como executar

### Pré-requisitos
- JDK 11 ou superior (para suporte ao `java.net.http.HttpClient`)
- Biblioteca [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) no classpath

### Compilando e executando manualmente

```bash
# Compilar (ajuste o caminho do gson.jar conforme sua máquina)
javac -cp gson.jar *.java

# Executar
java -cp .:gson.jar Main
```

### Exemplo de uso

```
Digite um numero de CEP:
01001000
Endereco[cep=01001-000, logradouro=Praça da Sé, complemento=lado ímpar, bairro=Sé, localidade=São Paulo, uf=SP]
```

Após a execução, será gerado um arquivo `01001-000.json` no diretório do projeto:

```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP"
}
```

## ⚠️ Tratamento de erros

- CEPs inválidos ou falhas de conexão são capturados e exibem uma mensagem amigável, sem interromper a aplicação de forma abrupta.
- Erros ao gravar o arquivo JSON também são tratados e reportados ao usuário.

## 🚧 Possíveis melhorias futuras

- Validação do formato do CEP antes de realizar a requisição
- Tratamento específico para CEP inexistente (a API ViaCEP retorna `"erro": true`)
- Testes unitários com mock da API
- Suporte a consulta de múltiplos CEPs em lote

## 📄 Licença

Projeto de estudo, livre para uso e modificação.