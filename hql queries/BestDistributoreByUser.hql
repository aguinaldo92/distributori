select D.id 
from Distributore as D inner join D.acquistas as A 
where A.persona.id = 19 
group by A.distributore.id 
order by count(*) desc