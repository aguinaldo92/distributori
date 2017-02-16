select A.distributore.id, count(*) as num 
from Acquista as A 
where A.persona.id = 1 
group by A.distributore.id 
order by num 