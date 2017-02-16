select D.indirizzo, D.stato
from Distributore as D inner join D.dipendente as Dip 
where Dip.personaId = 2 
order by D.stato