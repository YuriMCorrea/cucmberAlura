# language: pt

Funcionalidade: Propondo lances

Cenario: Propondo um unico lance valido
	Dado um leilao valido
	Quando propoe um lance
	Entao o lance eh aceito
	
Cenario: Propondo varios lances validos
	Dado um lance de 10.00 reais do usuario "fulano"
	E um lance de 15.00 reais do usuario "beltrano"
	Quando propoe varios lances ao leilao
	Entao os lances sao aceitos
	
Cenario: Propondo um lance invalido
	Dado um lance invalido de <valor> reais do <usuario>
	Quando propoe ao leilao
	Entao o lance nao eh aceito

Exemplos:
    | valor |    usuario |
    |   0.0 |   "fulano" |
    |  -1.0 | "beltrano" |
## Nesse caso todos os passos rodam 2x, pois o mesmo cenario é testado 2x com as variaveis de cada linha 
    
Cenario: Propondo varios lances validos
	Dado um lance de 10.00 reais do usuario "fulano"
	E um lance de 15.00 reais do usuario "fulano"
	Quando propoe varios lances ao leilao
	Entao o segundo lance nao eh aceito
	
Cenario: Propondo sequencia de lances
	Dado dois lances
	  | valor  |    usuario |
    |  10.0  |   "fulano" |
    |  15.0  | "beltrano" |
	Quando propoe varios lances ao leilao
	Entao o segundo lance nao eh aceito

## Nesse caso todos os passos rodam 1x, pois o mesmo passo recebe a tabela de dados e executa a ação necessária 
	
	