import java.util.Scanner;
import java.util.ArrayList;

class Cliente {
    private String nome;
    private String codigo;
    private float sal_devedor;

    Cliente(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.sal_devedor = 0;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public float getSalDevedor() {
        return this.sal_devedor;
    }

    public String toString() {
        return this.nome;
    }
}

class Mercantil {
    private ArrayList<Cliente> clientes;

    public Mercantil() {
        this.clientes = new ArrayList<>();
    }

    public void insert(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void showCliente(String cod) {
        boolean clienteEncontrado = false;

        for (Cliente cliente : clientes) {
            if (cod.equals(cliente.getCodigo())) {
                System.out.println("Cliente: " + cliente.getNome());
                System.out.println("Saldo Devedor: " + cliente.getSalDevedor());
                clienteEncontrado = true;
                break;
            }
        }

        if (!clienteEncontrado) {
            System.out.println("fail: cliente sem cadastro");
        }
    }
}

class Produto {
    private String nome;
    private String cod_prod;
    private float valor_compra;
    private float valor_venda;
    private int quantidade;

    Produto(String nome, String cod, float valor_compra, float valor_venda, int quantidade) {
        this.nome = nome;
        this.cod_prod = cod;
        this.valor_compra = valor_compra;
        this.valor_venda = valor_venda;
        this.quantidade = quantidade;
    }
    public String getNome(){
        return this.nome;
    }
    
    public String getCod_prod(){
        return this.cod_prod;
    }
    
    public float getValor_compra(){
        return this.valor_compra;
    }
    
     public float getValor_venda(){
        return this.valor_venda;
    }
    
     public int getQuantidade(){
        return this.quantidade;
    }
}

class Item {
    private ArrayList<Produto> produtos;

    public Item() {
        this.produtos = new ArrayList<>();
    }

    public void insert(Produto produto) {
        this.produtos.add(produto);
    }
    public void showProduto(String cod) {
        boolean produtoEncontrado = false;

        for (Produto produto : produtos) {
            if (cod.equals(produto.getCod_prod())) {
                System.out.println("Produto: " + produto.getNome());
                System.out.println("Quantidade: " + produto.getQuantidade());
                produtoEncontrado = true;
                break;
            }
        }

        if (!produtoEncontrado) {
            System.out.println("fail: produto não encontrado");
        }
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mercantil mercantil = new Mercantil();
        Item item = new Item(); // Crie um objeto Item para inserir produtos

        while (true) {
            System.out.println("");
            System.out.println("+----- SysBudega - Controle de Estoque e Vendas -----+");
            System.out.println("|                                                    |");
            System.out.println("|            1. Cadastro de Clientes                 |");
            System.out.println("|            2. Cadastro de Produtos                 |");
            System.out.println("|            3. Realização de Vendas                 |");
            System.out.println("|            4. Buscar Produto                       |");
            System.out.println("|            5. Buscar Cliente                       |");
            System.out.println("|            6. Geração de Relatórios                |");
            System.out.println("|            7. Sair                                 |");
            System.out.println("|                                                    |");
            System.out.println("+----- SysBudega - Controle de Estoque e Vendas -----+");
            System.out.println("");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Implementar a funcionalidade de cadastro de usuários
                    System.out.println("Você escolheu a opção 1: Cadastro de Clientes");
                    System.out.println("Nome do cliente");
                    String nomeCliente = scanner.next();
                    System.out.println("Código do cliente");
                    String codigoCliente = scanner.next();
                    mercantil.insert(new Cliente(nomeCliente, codigoCliente));
                    break;
                case 2:
                    // Implementar a funcionalidade de cadastro de produtos
                    System.out.println("Você escolheu a opção 2: Cadastro de Produtos");
                    System.out.println("Nome do produto");
                    String nomeProduto = scanner.next();
                    System.out.println("Código do produto");
                    String codigoProduto = scanner.next();
                    System.out.println("Valor de compra");
                    float valorCompra = scanner.nextFloat();
                    System.out.println("Valor de venda");
                    float valorVenda = scanner.nextFloat();
                    System.out.println("Quantidade de produtos");
                    int quantidadeProduto = scanner.nextInt();
                    item.insert(new Produto(nomeProduto, codigoProduto, valorCompra, valorVenda, quantidadeProduto));
                    break;
                case 3:
                    // Implementar a funcionalidade de realização de vendas
                    System.out.println("Você escolheu a opção 3: Realização de Vendas");
                    break;
                case 4:
                    // Implementar a funcionalidade de pesquisa de produtos
                    System.out.println("Você escolheu a opção 4: Buscar Produto");
                    System.out.println("Digite o código do produto");
                    String codigoProdutoConsulta = scanner.next();
                    item.showProduto(codigoProdutoConsulta);
                    break;
                case 5:
                    // Implementar a funcionalidade de contas a receber
                    System.out.println("Você escolheu a opção 5: Buscar Cliente");
                    System.out.println("Digite o código do cliente");
                    String codigoClienteConsulta = scanner.next();
                    mercantil.showCliente(codigoClienteConsulta);
                    break;
                case 6:
                    // Implementar a funcionalidade de geração de relatórios
                    System.out.println("Você escolheu a opção 6: Geração de Relatórios");
                    break;
                case 7:
                    System.out.println("Saindo do sistema. Até logo!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
