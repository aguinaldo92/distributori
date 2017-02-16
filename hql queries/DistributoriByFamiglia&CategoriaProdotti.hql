SELECT D.id
FROM Distributore as D inner join D.prodottiErogatis as PE inner join PE.prodotto as EP inner join EP.famiglieProdottos as F inner join F.prodotto.categoria as C 
WHERE F.famiglia.nome = 'Prodotti normali' and C.nome = 'Bevande fredde'