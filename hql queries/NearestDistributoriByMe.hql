select D.id 
from Distributore as D 
where abs(D.lat - :mylat) < 20.0 and abs(D.lon - :mylon) < 20.0