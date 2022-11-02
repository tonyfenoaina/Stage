
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.example.demo.model.*" %>
<!DOCTYPE html>
<html lang="en">
  <jsp:include page="/WEB-INF/jsp/element/head.jsp" />
<body>
  <!-- ======= Header ======= -->
  <jsp:include page="/WEB-INF/jsp/element/header.jsp" />
  <main  id="main" class="main">
  
   
    <div style="margin-left: 100px;" class="pagetitle">
      <h1>Fiche des Produits</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Fiche des produits</li> 
          <li class="breadcrumb-item active">Liste des produits</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">

        <!-- Left side columns -->
        <div class="col-lg-12">
          <div class="row">

            <!-- Sales Card -->
           

            <!-- Customers Card -->
           <!-- End Customers Card -->

            <!-- Reports -->
           <!-- End Reports -->

            <!-- Recent Sales -->
          <!-- End Recent Sales -->

            <!-- Top Selling -->
            
            <div class="col-1">
            </div>
            
            <div class="col-9">
              <div class="card top-selling overflow-auto">

            

                <div class="card-body pb-0">
                  
                  <!-- <nav class="header-nav ms-auto">
                    <ul class="d-flex align-items-center">
                      <li class="nav-item d-block d-lg-none">
                        <a class="nav-link nav-icon search-bar-toggle " href="#">
                          <i class="bi bi-search"></i>
                        </a>
                      </li>
                    </ul> -->

                  </nav>

                  <h5 class="card-title">Listes des Produits</h5>
                  <div style="margin-top: 20px;" class="row">
       
                    <div class="col-4"></div>

                   

                  <div class="col-5">
                    <form action="RechercheProduit_fiche" method="post">
                      <div class="row">
                        <%
                            if(request.getAttribute("retour")!=null){                             
                        %>
                        <div class="col-6">
                          <input type="search" name="nom" id="nom"  class="form-control" placeholder="Recherche" value="<%= request.getAttribute("retour") %>">
                        </div>
                        <%}else{%>
                          <div class="col-6">
                            <input type="search" name="nom" id="nom"  class="form-control" placeholder="Recherche">
                          </div>
                        <%}%>
                        <div class="col-2">
                          <input class="btn btn-primary" style="margin-top: 0px;background-color: #025ea7;color: white;" type="submit" value="Rechercher">    
                        </div>

                      </div>                                
                    </form>
                  </div>
                </div>
                 
                  

                 
						            <% 	 
						            List<Detailproduit> listProduit = new ArrayList<Detailproduit>();
					
						            listProduit = (List<Detailproduit>)session.getAttribute("listProduit");  
                                   
                                                      	
                        %>


                  <table style="margin-top: 20px;" class="table table-borderless">
                    <thead>
                      <tr>
            
                        <th scope="col"></th>
                        <th scope="col">NOM</th>   
                        <th scope="col">Marque</th>   
                        <th scope="col">Categorie</th>       
                        <th scope="col">Fiche</th>      
                      </tr>
                    </thead>
                    <tbody>
                  		<%  for(Detailproduit s : listProduit){ 
                   
                        %> 
                        <tr>
                          <th></th>
                           <th><%= s.getMarque() %> <%= s.getNom() %></th>
                           <th><%= s.getMarque() %></th>
                           <th> <%= s.getCategorie() %></th>
                          <th><a href="/fiche_produit?nom=<%=s.getMarque()+" "+s.getNom()%>&idcategorie=<%=s.getIdcategorie()%>&idproduit=<%=s.getId()%>"><button style="background-color: #025ea7;" type="button" class="btn btn-primary"><i class="bi bi-card-list"></i></button></a></th>
                       </tr>
                        
                        <%
                        	}                  
                        %>
 
                    </tbody>
                  </table>
                  <%
                    int current = (int)request.getAttribute("current");
                    int total = (int)request.getAttribute("total");
                    int previous = current-2;
                    int next = current;                   
                  %>

                  <%
                    if(current==1){
                  %>
                  <nav style="margin-left: 20px;margin-top: 30px;" aria-label="...">
                    <ul class="pagination">
                      <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Precedent</a>
                      </li>
                      <li class="page-item active" aria-current="page">
                        <a style="background-color: #025ea7;" class="page-link" href="/listproduit_fiche?page=<%=current-1%>"><%=current%></a>
                      </li>
               
                      <%
                      if(total==1){
                    %>
                      <li class="page-item disabled"><a class="page-link" href="#" aria-disabled="true"><%=current+1%></a></li>
                      <li class="page-item disabled"><a class="page-link" href="#" aria-disabled="true"><%=current+2%></a></li>
                      <li class="page-item disabled">
                        <a class="page-link" href="#" aria-disabled="true">Suivant</a>

                      </li>

                      <%
                      }else if(total==2){
                    %>
                    <li class="page-item"><a class="page-link" href="/listproduit_fiche?page=<%=current%>"><%=current+1%></a></li>
                    <li class="page-item disabled"><a class="page-link" href="/listproduit_fiche?page=<%=current+1%>" aria-disabled="true"><%=current+2%></a></li>
                    <li class="page-item">
                      <a class="page-link" href="/listproduit_fiche?page=<%=next%>">Suivant</a>
                    </li>
                    <%} else {%>

                      <li class="page-item"><a class="page-link" href="/listproduit_fiche?page=<%=current%>"><%=current+1%></a></li>
                      <li class="page-item"><a class="page-link" href="/listproduit_fiche?page=<%=current+1%>"><%=current+2%></a></li>
                      <li class="page-item">
                        <a class="page-link" href="/listproduit_fiche?page=<%=next%>">Next</a>
                      </li>

                      <%}%>

                     
                    </ul>
                  </nav>

                  

                  <%} else if(current==total && current!=1){
                      if(total==2){
                  %>
                  <nav style="margin-left: 20px;margin-top: 30px;" aria-label="...">
                    <ul class="pagination">
                      <li class="page-item ">
                        <a class="page-link" href="/listproduit_fiche?page=<%=previous%>" tabindex="-1" >Precedent</a>
                      </li>
                    
                     
                      <li class="page-item"><a class="page-link" href="/listproduit_fiche?page=<%=current-2%>"><%=current-1%></a></li>
  
                      <li class="page-item active" aria-current="page">
                        <a style="background-color: #025ea7;" class="page-link" href="/listproduit_fiche?page=<%=current-1%>"><%=current%></a>
                      </li>

                      <li class="page-item disabled"><a class="page-link" href="/listproduit_fiche?page=<%=current%>" aria-disabled="true"><%=current+1%></a></li>
  
                      <li class="page-item disabled">
                        <a class="page-link" href="#" aria-disabled="true">Suivant</a>
                      </li>
                    </ul>
                  </nav>
                  <%}else{%>

                  
                  
                <nav style="margin-left: 20px;margin-top: 30px;" aria-label="...">
                  <ul class="pagination">
                    <li class="page-item ">
                      <a class="page-link" href="/listproduit_fiche?page=<%=previous%>" tabindex="-1" >Precedent</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/listproduit_fiche?page=<%=current-3%>"><%=current-2%></a></li>
                   
                    <li class="page-item"><a class="page-link" href="/listproduit_fiche?page=<%=current-2%>"><%=current-1%></a></li>

                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/listproduit_fiche?page=<%=current-1%>"><%=current%></a>
                    </li>

                    <li class="page-item disabled">
                      <a class="page-link" href="#" aria-disabled="true">Suivant</a>
                    </li>
                  </ul>
                </nav>

                <%}}else{%>

                <nav style="margin-left: 20px;margin-top: 30px;" aria-label="...">
                  <ul class="pagination">
                    <li class="page-item ">
                      <a class="page-link" href="/listproduit_fiche?page=<%=previous%>" tabindex="-1">Precedent</a>
                    </li>
                      <%  if(current==2 && total==2){
                      %>

                    <li class="page-item"><a class="page-link" href="/listproduit_fiche?page=<%=current-2%>"><%=current-1%></a></li>
                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/listproduit_fiche?page=<%=current-1%>"><%=current%></a>
                    </li>
                    <li class="page-item disabled"><a class="page-link" href="/listproduit_fiche?page=<%=current%>" aria-disabled="true"><%=current+1%></a></li>
                    <li class="page-item disabled">
                      <a class="page-link" href="/listproduit_fiche?page=<%=next%>" aria-disabled="true">Suivant</a>
                    </li>
                    <%
                      }else{
                    %>

                    <li class="page-item"><a class="page-link" href="/listproduit_fiche?page=<%=current-2%>"><%=current-1%></a></li>
                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/listproduit_fiche?page=<%=current-1%>"><%=current%></a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/listproduit_fiche?page=<%=current%>"><%=current+1%></a></li>
                    <li class="page-item">
                      <a class="page-link" href="/listproduit_fiche?page=<%=next%>" >Suivant</a>
                    </li>

                    <%}%>

                  </ul>
                </nav>
                <%}%>
                </div>

              </div>
            </div>
            
            <!-- End Top Selling -->

           

          </div>
        </div><!-- End Left side columns -->

        <!-- Right side columns -->
       <!-- End Right side columns -->

      </div>
    </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <jsp:include page="/WEB-INF/jsp/element/footer.jsp"/>

</body>

</html>