import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

enum Label {
    NORMAL,
    VIP;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

class Cliente {
    private String nome;
    private String codigo;
    private float sal_devedor;
    private Label label; 

    Cliente(String nome, String codigo, Label label) {
        this.nome = nome;
        this.codigo = codigo;
        this.label = label;
        this.sal_devedor = 0;
    }

    public void setSaldo(float val) throws IllegalArgumentException {
        if (val < 0) {
            throw new IllegalArgumentException("O saldo devedor não pode ser um valor negativo.");
        }
        this.sal_devedor += val;
    }

    public String getNome() {
        return this.nome;
    }

    public Label getLabel() {
        return this.label;
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

class ClienteVIP extends Cliente {
    private float desconto; // Novo atributo específico para clientes VIP

    ClienteVIP(String nome, String codigo, Label label, float desconto) {
        super(nome, codigo, label);
        this.desconto = desconto;
    }
}

class Mercantil {
    private ArrayList<Cliente> clientes;

    public Mercantil() {
        this.clientes = new ArrayList<>();
    }

    public void insert(Cliente cliente) throws IllegalArgumentException {
        boolean codigoExiste = false;

        for (Cliente c : clientes) {
            if (c.getCodigo().equals(cliente.getCodigo())) {
                codigoExiste = true;
                break;
            }
        }

        if (!codigoExiste) {
            this.clientes.add(cliente);
            System.out.println("Cliente adicionado com sucesso!");
        } else {
            throw new IllegalArgumentException("O código do cliente já existe. Não é possível adicionar.");
        }
    }

    public void showCliente(String cod) {
        boolean clienteEncontrado = false;

        for (Cliente cliente : clientes) {
            if (cod.equals(cliente.getCodigo())) {
                System.out.println("Cliente: " + cliente.getNome());
                System.out.println("Tipo: " + cliente.getLabel());
                System.out.println("Saldo Devedor: " + cliente.getSalDevedor());
                clienteEncontrado = true;
                break;
            }
        }

        if (!clienteEncontrado) {
            System.out.println("fail: cliente sem cadastro");
        }
    }

    public void exibirTodosClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
        } else {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : clientes) {
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Código: " + cliente.getCodigo());
                System.out.println("Tipo: " + cliente.getLabel());
                System.out.println("Saldo Devedor: " + cliente.getSalDevedor());
                System.out.println("-----------");
            }
        }
    }

    public void deleteCliente(String cod) {
        Cliente clienteRemovido = null;

        for (Cliente cliente : clientes) {
            if (cod.equals(cliente.getCodigo())) {
                clienteRemovido = cliente;
                break;
            }
        }

        if (clienteRemovido != null) {
            this.clientes.remove(clienteRemovido);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado para exclusão.");
        }
    }
    
    public ArrayList<Cliente> getClientes() {
        return clientes;
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

    public void setQuantidade(int qtd){
        this.quantidade = this.quantidade - qtd;
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
    float total_vendas;

    public Item() {
        this.produtos = new ArrayList<>();
    }

    public void insert(Produto produto) throws IllegalArgumentException {
        boolean codigoExiste = false;
        
        for (Produto p : produtos) {
            if (p.getCod_prod().equals(produto.getCod_prod())) {
                codigoExiste = true;
                break;
            }
        }

        if (!codigoExiste) {
            this.produtos.add(produto);
            System.out.println("Produto adicionado com sucesso!");
        } else {
            throw new IllegalArgumentException("O código do produto já existe. Não é possível adicionar.");
        }
    }
    
    public void showProduto(String cod) throws IllegalArgumentException {
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
            throw new IllegalArgumentException("produto não encontrado");
        }
    }
    
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
    
    public void realizarVenda(Scanner scanner, Mercantil mercantil) throws IllegalArgumentException {
        System.out.println("Você escolheu a opção 3: Realização de Vendas");

        System.out.println("Digite o código do cliente:");
        String codigoClienteVenda = scanner.next();
        Cliente clienteVenda = null;

        for (Cliente cliente : mercantil.getClientes()) {
            if (codigoClienteVenda.equals(cliente.getCodigo())) {
                clienteVenda = cliente;
                break;
            }
        }
        if (clienteVenda != null) {
            boolean continuarVenda = true;
            float total = 0;

            while (continuarVenda) {
                System.out.println("Digite o código do produto:");
                String codigoProdutoVenda = scanner.next();
                Produto produtoVenda = null;

                for (Produto produto : this.getProdutos()) {
                    if (codigoProdutoVenda.equals(produto.getCod_prod())) {
                        produtoVenda = produto;
                        break;
                    }
                }
                if (produtoVenda != null && produtoVenda.getQuantidade() > 0) {
                    System.out.println("Quantidade desejada:");
                    int quantidadeDesejada = scanner.nextInt();

                if (quantidadeDesejada <= produtoVenda.getQuantidade()) {
                    float subtotal = quantidadeDesejada * produtoVenda.getValor_venda();
                    if (clienteVenda instanceof ClienteVIP) {
                        // Aplicar desconto de 10% para clientes VIP
                        subtotal *= 0.9; // 10% de desconto
                    }
                    total += subtotal;
                    System.out.println("Total da venda: " + subtotal);
                    System.out.println("Venda realizada com sucesso!");
                    produtoVenda.setQuantidade(quantidadeDesejada);
                } else {
                        throw new IllegalArgumentException("Quantidade insuficiente em estoque!");
                    }
                } else {
                    throw new IllegalArgumentException("Produto não encontrado ou sem estoque!");
                }

                System.out.println("Deseja realizar outra venda? (s/n)");
                String resposta = scanner.next();

                if (!resposta.equalsIgnoreCase("s")) {
                    continuarVenda = false;
                    System.out.println("1 - Pagamento á Vista 2 - Crediário");
                    int opc = scanner.nextInt();
                    if(opc == 2){
                        clienteVenda.setSaldo(total);
                    }
                }
            }
         } else {
                throw new IllegalArgumentException("Cliente não encontrado!");
            }
        }

        public void exibirTodosProdutos() throws IllegalArgumentException {
            if (produtos.isEmpty()) {
                throw new IllegalArgumentException("Não há produtos cadastrados.");
            } else {
                System.out.println("Lista de Produtos:");
                for (Produto produto : produtos) {
                    System.out.println("Nome: " + produto.getNome());
                    System.out.println("Código: " + produto.getCod_prod());
                    System.out.println("Valor de compra: " + produto.getValor_compra());
                    System.out.println("Valor de venda: " + produto.getValor_venda());
                    System.out.println("Quantidade: " + produto.getQuantidade());
                    System.out.println("-----------");
                }
            }
        }
        public void deleteProduto(String cod) throws IllegalArgumentException {
        Produto produtoRemovido = null;

            for (Produto produto : produtos) {
                if (cod.equals(produto.getCod_prod())) {
                    produtoRemovido = produto;
                    break;
                }
            }

            if (produtoRemovido != null) {
                this.produtos.remove(produtoRemovido);
                System.out.println("Produto removido com sucesso!");
            } else {
                throw new IllegalArgumentException("Produto não encontrado para exclusão.");
            }
        }
    }

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mercantil mercantil = new Mercantil();
        Item item = new Item(); 

        while (true) {
            try {
                System.out.println("");
                System.out.println("+----- SysBudega - Controle de Estoque e Vendas -----+");
                System.out.println("|                                                    |");
                System.out.println("|            1. Cadastro de Clientes                 |");
                System.out.println("|            2. Cadastro de Produtos                 |");
                System.out.println("|            3. Realização de Vendas                 |");
                System.out.println("|            4. Buscar Produto                       |");
                System.out.println("|            5. Buscar Cliente                       |");
                System.out.println("|            6. Exibir todos os produtos             |");
                System.out.println("|            7. Exibir todos os clientes             |");
                System.out.println("|            8. Deletar cliente                      |");
                System.out.println("|            9. Deletar produto                      |");
                System.out.println("|            0. Sair                                 |");
                System.out.println("|                                                    |");
                System.out.println("+----- SysBudega - Controle de Estoque e Vendas -----+");
                System.out.println("");

                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        try {
                            System.out.println("Digite 1 - Cliente Comum ou Digite 2 - Cliente VIP");
                            int op = scanner.nextInt();

                            switch(op){
                                case 1:
                                    System.out.println("Você escolheu a opção 1: Cadastro de Clientes");
                                    System.out.println("Nome do cliente");
                                    String nomeCliente1 = scanner.next();
                                    System.out.println("Código do cliente");
                                    String codigoCliente1 = scanner.next();
                                    Label label1 = Label.NORMAL;
                                    mercantil.insert(new Cliente(nomeCliente1, codigoCliente1, label1));
                                    break;

                                case 2:
                                    System.out.println("Você escolheu a opção 2: Cadastro de Clientes VIP");
                                    System.out.println("Nome do cliente");
                                    String nomeCliente2 = scanner.next();
                                    System.out.println("Código do cliente");
                                    String codigoCliente2 = scanner.next();
                                    Label label2 = Label.VIP;
                                    int desconto = 10; // 10% de desconto para clientes vips
                                    mercantil.insert(new ClienteVIP(nomeCliente2, codigoCliente2, label2, desconto));
                                    break;

                                default:
                                    System.out.println("Opção inválida");
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Insira um valor numérico.");
                            scanner.next(); 
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try {
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
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Insira um valor numérico.");
                            scanner.next(); 
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 3:
                        try {
                            item.realizarVenda(scanner, mercantil);
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Insira um valor numérico.");
                            scanner.next(); // Limpa o buffer do scanner
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("Você escolheu a opção 4: Buscar Produto");
                            System.out.println("Digite o código do produto");
                            String codigoProdutoConsulta = scanner.next();
                            item.showProduto(codigoProdutoConsulta);
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Insira um valor numérico.");
                            scanner.next(); // Limpa o buffer do scanner
                        }
                        break;

                    case 5:
                        try {
                            System.out.println("Você escolheu a opção 5: Buscar Cliente");
                            System.out.println("Digite o código do cliente");
                            String codigoClienteConsulta = scanner.next();
                            mercantil.showCliente(codigoClienteConsulta);
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Insira um valor numérico.");
                            scanner.next(); // Limpa o buffer do scanner
                        }
                        break;

                    case 6:
                        try {
                            System.out.println("Você escolheu a opção 6: Exibir todos os produtos");
                            item.exibirTodosProdutos();
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 7:
                        try {
                            System.out.println("Você escolheu a opção 7: Exibir todos os clientes");
                            mercantil.exibirTodosClientes();
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                    case 8:
                        try {
                            System.out.println("Você escolheu a opção 9: Excluir Cliente");
                            System.out.println("Digite o código do cliente que deseja excluir:");
                            String codigoClienteExclusao = scanner.next();
                            mercantil.deleteCliente(codigoClienteExclusao);
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Insira um valor numérico.");
                            scanner.next(); // Limpa o buffer do scanner
                        }
                        break;
                    case 9:
                        try {
                            System.out.println("Você escolheu a opção 10: Excluir Produto");
                            System.out.println("Digite o código do produto que deseja excluir:");
                            String codigoProdutoExclusao = scanner.next();
                            item.deleteProduto(codigoProdutoExclusao);
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Insira um valor numérico.");
                            scanner.next(); // Limpa o buffer do scanner
                        }
                        break;
                    case 0:
                        System.out.println("Saindo do sistema. Até logo!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }
    }
}