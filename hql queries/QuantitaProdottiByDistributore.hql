select P.nome, P.stabilimento.produttore.nome, DP.scaffale, DP.quantita
from ProdottiErogati as DP inner join DP.prodotto as P 
where DP.distributore.id = 2 
order by DP.quantita