select P.id, P.nome, C1.nome 
from Distributore as D inner join D.categorieFornites as CF inner join CF.categoria as C1 inner join C1.prodottos as P 
where D.id = 2 
order by C1.nome, P.nome