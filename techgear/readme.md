### O projeto consiste em:
- 1 interface (Display), a qual gera um contrato para todos os displays.
- 1 classe abstrata (Produto), representa algum produto da loja, seja ele virtual ou físico.
- 4 classes (DisplayLoja, DisplayGerenciarCategorias, DisplayGerenciarLoja, DisplayGerenciarProdutos) que implementam Display, as quais se comunicam com o usuário final.
- 2 classes (ProdutoFisico, ProdutoVirtual) que estendem Produto, as quais possuem atributos e métodos únicos.
- 1 classe (Categoria), a qual representa uma categoria de produtos da loja.
- 1 classe (Loja), que representa a loja em si.
- 1 classe (Input) que é responsável por pegar o input do usuário.
---
Neste projeto, você desenvolverá um sistema de Commerce utilizando Java, que possibilitará a criação
e gestão de diversas classes fundamentais para o funcionamento de uma loja. Entre estas, destacam-
se DisplayLoja, Loja, Categoria, ProdutoFisico e ProdutoVirtual. O sistema será projetado para ser
operado por funcionários do Commerce, otimizando o processo de venda dos produtos.

A arquitetura do sistema gira em torno da classe Loja, que agrega múltiplas instâncias de Categoria.
Cada Categoria pode conter um ou mais produtos, que são representados por duas classes distintas:
ProdutoFisico e ProdutoVirtual. Essas classes de produtos armazenam informações cruciais sobre os
itens à venda, tais como preço, nome, descrição, identificação do produto, marca e outras características
pertinentes. Para efetivar a implementação deste sistema, é necessário desenvolver as classes DisplayLoja,
Categoria, Loja, ProdutoFisico e ProdutoVirtual, atendendo aos requisitos mínimos previstos (campos
e métodos descritos para cada uma). É encorajado expandir a funcionalidade do sistema por meio da
adição de métodos, campos e classes adicionais.

No início do programa, a função main deve definir três variáveis do tipo String, contendo os caminhos
para os arquivos: categorias.txt, produtoFisico.txt e produtoVirtual.txt. Após a configuração inicial
na função main, seu código deve criar uma instância de DisplayLoja, passando as três Strings com os
caminhos dos arquivos de dados ao construtor. A classe DisplayLoja irá carregar os dados e disponibilizar
a interface (linha de comando) para comunicação com o usuário, com busca de produtos, compra, inclusão e
remoção, etc.
