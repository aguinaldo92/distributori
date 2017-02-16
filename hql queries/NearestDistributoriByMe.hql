select D.id 
from Distributore as D 
where abs(D.lat - :mylat) < 0.2 and abs(D.lon - :mylon) < 0.2