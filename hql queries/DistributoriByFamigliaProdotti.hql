SELECT  D.id
FROM Distributore as D inner join D.prodottiErogatis as PE inner join PE.prodotto as EP inner join EP.famiglieProdottos as F 
WHERE F.famiglia.nome = 'Prodotti locali'