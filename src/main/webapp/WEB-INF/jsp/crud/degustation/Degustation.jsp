
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.example.demo.model.*" %>
<!DOCTYPE html>
<html lang="en">
  <jsp:include page="/WEB-INF/jsp/element/head.jsp" />
<body>
  <!-- ======= Header ======= -->
  <jsp:include page="/WEB-INF/jsp/element/header.jsp" />
  <main id="main" class="main">
  
    
    <div style="margin-left: 100px;" class="pagetitle">
      <h1>Degustation</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Gestion des Fiches d'Enquetes</li> 
          <li class="breadcrumb-item active">Degustation</li>
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

                  <h5 class="card-title">Listes des Degustation</h5>
                  <div style="margin-top: 20px;" class="row">
                    
                  
                    <div style="margin-left: 20px ;" class="col-2">
                      <a href="/pageCategorieDegustation"><button style="background-color: #025ea7;" type="button" class="btn btn-primary"><i class="bx bxs-plus-circle"></i> Ajouter</button></a>
                    </div>

                    <div class="col-4"></div>

                  <div class="col-5">
                    <form action="/RechercheDn" method="post">
                      <div class="row">
                        <%
                            if(request.getAttribute("retour")!=null){                             
                        %>
                        <div class="col-6">
                          <input type="search" name="titre" id="titre"  class="form-control" placeholder="Recherche" value="<%= request.getAttribute("retour") %>">
                        </div>
                        <%}else{%>
                          <div class="col-6">
                            <input type="search" name="titre" id="titre"  class="form-control" placeholder="Recherche">
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
						            List<Degustation> listDn = new ArrayList<Degustation>();
					
						            listDn = (List<Degustation>)session.getAttribute("listDegustation");  

                        %>


                  <table style="margin-top: 20px;" class="table table-borderless">
                    <thead>
                      <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Titre</th>
                        <th scope="col">Debut</th>
                        <th scope="col">Fin</th>
                        <th scope="col">Modifier</th>
                        <th scope="col">Produits </th>
                     
                      </tr>
                    </thead>
                    <tbody>
                  		<%  for(Degustation s : listDn){ %>
                        
                        <tr>
                          <th><%= s.getId() %></th>
                           <th><%= s.getTitre()%></th> 
                           <th><%= s.getDebut() %></th> 
                           <th><%= s.getFin() %></th>
                        
                                          
                          <td> <button data-bs-toggle="modal" data-bs-target="#haha<%=s.getId()%>" style="background-color: #025ea7;" type="button" class="btn btn-primary"><i class="ri-edit-2-fill"></i></button></td>
                          
                           <div class="modal fade" id="haha<%= s.getId()%>" tabindex="-1">
                        <div class="modal-dialog modal-dialog-centered">
                          <div class="modal-content">
                            <div style="background-color: #025ea7;" class="modal-header">
                              <h5 class="modal-title" style="color: white;">Modification du fiche de Degustation Numero<%=s.getId() %></h5>
                            </div>
                            <form action="/UpdateDegustation" method="post">
                              <div class="modal-body">
                                <label for="">Titre</label>
                                <input style="margin-top: 20px;" class="form-control" type="text" name="titre" value="<%= s.getTitre() %>">
                                <label for="">Debut</label>
                                <input style="margin-top: 20px;" class="form-control" type="date" name="debut" value="<%= s.getDebut() %>">
                                <label for="">Fin</label>
                                <input style="margin-top: 20px;" class="form-control" type="date" name="fin" value="<%= s.getFin() %>">
                                <input type="hidden" name="id" value="<%= s.getId() %>">                  
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                                <input style="background-color: #025ea7;" type="submit" class="btn btn-primary" value="Enregistrer">
                                
                              </div>
                            </form>
                          </div>
                        </div>
                           </div>

                           <th><a href="produitDegustation?iddegustation=<%= s.getId()%>&idcategorie=<%=s.getIdcategorie()%>"><button style="background-color: #025ea7;" type="button" class="btn btn-primary">  <i class="ri-product-hunt-line"></i></button></a></th>
                        </tr>
                     
                      <% } %>
         
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
                        <a style="background-color: #025ea7;" class="page-link" href="/degustation?page=<%=current-1%>"><%=current%></a>
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
                    <li class="page-item"><a class="page-link" href="/degustation?page=<%=current%>"><%=current+1%></a></li>
                    <li class="page-item disabled"><a class="page-link" href="/degustation?page=<%=current+1%>" aria-disabled="true"><%=current+2%></a></li>
                    <li class="page-item">
                      <a class="page-link" href="/degustation?page=<%=next%>">Suivant</a>
                    </li>
                    <%} else {%>

                      <li class="page-item"><a class="page-link" href="/degustation?page=<%=current%>"><%=current+1%></a></li>
                      <li class="page-item"><a class="page-link" href="/degustation?page=<%=current+1%>"><%=current+2%></a></li>
                      <li class="page-item">
                        <a class="page-link" href="/degustation?page=<%=next%>">Next</a>
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
                        <a class="page-link" href="/degustation?page=<%=previous%>" tabindex="-1" >Precedent</a>
                      </li>
                    
                     
                      <li class="page-item"><a class="page-link" href="/degustation?page=<%=current-2%>"><%=current-1%></a></li>
  
                      <li class="page-item active" aria-current="page">
                        <a style="background-color: #025ea7;" class="page-link" href="/degustation?page=<%=current-1%>"><%=current%></a>
                      </li>

                      <li class="page-item disabled"><a class="page-link" href="/degustation?page=<%=current%>" aria-disabled="true"><%=current+1%></a></li>
  
                      <li class="page-item disabled">
                        <a class="page-link" href="#" aria-disabled="true">Suivant</a>
                      </li>
                    </ul>
                  </nav>
                  <%}else{%>

                  
                  
                <nav style="margin-left: 20px;margin-top: 30px;" aria-label="...">
                  <ul class="pagination">
                    <li class="page-item ">
                      <a class="page-link" href="/degustation?page=<%=previous%>" tabindex="-1" >Precedent</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/degustation?page=<%=current-3%>"><%=current-2%></a></li>
                   
                    <li class="page-item"><a class="page-link" href="/degustation?page=<%=current-2%>"><%=current-1%></a></li>

                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/degustation?page=<%=current-1%>"><%=current%></a>
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
                      <a class="page-link" href="/degustation?page=<%=previous%>" tabindex="-1">Precedent</a>
                    </li>
                      <%  if(current==2 && total==2){
                      %>

                    <li class="page-item"><a class="page-link" href="/degustation?page=<%=current-2%>"><%=current-1%></a></li>
                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/degustation?page=<%=current-1%>"><%=current%></a>
                    </li>
                    <li class="page-item disabled"><a class="page-link" href="/degustation?page=<%=current%>" aria-disabled="true"><%=current+1%></a></li>
                    <li class="page-item disabled">
                      <a class="page-link" href="/degustation?page=<%=next%>" aria-disabled="true">Suivant</a>
                    </li>
                    <%
                      }else{
                    %>

                    <li class="page-item"><a class="page-link" href="/degustation?page=<%=current-2%>"><%=current-1%></a></li>
                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/degustation?page=<%=current-1%>"><%=current%></a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/degustation?page=<%=current%>"><%=current+1%></a></li>
                    <li class="page-item">
                      <a class="page-link" href="/degustation?page=<%=next%>" >Suivant</a>
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