import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConsultaCep consultaCep = new ConsultaCep();

        System.out.println("Digite um numero de CEP:");
        String cep = sc.nextLine();

        try {
            Endereco novoEndereco = consultaCep.buscaEndereco(cep);
            System.out.println(novoEndereco);
            GeradorArquivo gerador = new GeradorArquivo();
            gerador.salvaJson(novoEndereco);
        } catch (RuntimeException | IOException e){
            System.out.println(e.getMessage());
            System.out.println("Finalizando a aplicacao");
        }
    }
}
