<%@ taglib prefix="s" uri="/struts-tags"%>


<!-- /. NAV TOP  -->
                <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
				<li class="text-center">
                    <img src="/distributori/vendor/img/find_user.png" class="user-image img-responsive"/>
					</li>
				
					
                    <li>
                        <a  href="index.html"><i class="fa fa-dashboard fa-3x"></i> Dashboard</a>
                    </li>
                      <li>
                         <s:a namespace="/gestore" action="showDipendenti"><i class="fa fa-table fa-3x"></i> Gestione Dipendenti</s:a>
                    </li>
                    <li>
                        <s:a namespace="/gestore" action="showCatalogo"><i class="fa fa-table fa-3x"></i> Gestione Catalogo</s:a>
                    </li>
						 <li><s:a namespace="/gestore" action="ListDistributoriGestore"><i class="fa fa-table fa-3x"></i>
					Gestione Distributori</s:a></li>
                </ul>
               
            </div>
            
        </nav>  
