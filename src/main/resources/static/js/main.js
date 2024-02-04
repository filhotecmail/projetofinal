// Função para atualizar os valores
function atualizarValores() {
    var entradaTotal = 0;
    var saidaTotal = 0;

    // Iterar sobre as linhas da tabela
    document.querySelectorAll('table tbody tr').forEach(function(row) {
        // Obter os valores da coluna "tipo" e "valor"
        var tipo = row.querySelector('td:nth-child(4)').innerText;
        var valor = parseFloat(row.querySelector('td:nth-child(3)').innerText);

        // Atualizar os totais com base no tipo
        if (tipo.toUpperCase() === 'ENTRADA') {
            entradaTotal += valor;
        } else if (tipo.toUpperCase() === 'SAIDA') {
            saidaTotal += valor;
        }
    });

    // Atualizar os elementos HTML com os novos totais
    document.querySelector('.incomes').innerText = entradaTotal.toFixed(2);
    document.querySelector('.expenses').innerText = saidaTotal.toFixed(2);
    document.querySelector('.total').innerText = (entradaTotal - saidaTotal).toFixed(2);
}

// Chamar a função inicialmente e sempre que houver mudanças na tabela
document.addEventListener('DOMContentLoaded', function() {
    atualizarValores();
});


