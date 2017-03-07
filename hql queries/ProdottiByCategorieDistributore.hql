select P
from Distributore as D inner join D.categorieFornites as CF inner join CF.categoria as C1 inner join C1.prodottos as P inner join P.categoria as C2
where D.id = :idDistributore and C1.id = C2.id and P.nome != 'vuoto' 
order by C1.nome, P.nome