
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
      <h1>Gestion des Utilisateurs</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Gestion des Utilisateurs</li> 
          <li class="breadcrumb-item active">Enqueteur</li>
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

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>

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

                  <h5 class="card-title">Listes des Enqueteur</h5>
                  <div style="margin-top: 20px;" class="row">
                    
                  
                    <div style="margin-left: 20px ;" class="col-2">
                      <button data-bs-toggle="modal" data-bs-target="#ville" style="background-color: #025ea7;" type="button" class="btn btn-primary"><i class="bx bxs-plus-circle"></i> Ajouter</button>
                    </div>

                    <div class="modal fade" id="ville" tabindex="-1">
                      <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                          <div style="background-color: #025ea7;" class="modal-header">
                            <h5 class="modal-title" style="color: white;">Nouvelle Enqueteur</h5>
                            
                          </div>
                          <form action="/AjouterEnqueteur" method="post">
                            <div class="modal-body">
                              <label for="">Nom</label>
                              <input style="margin-top: 20px;" class="form-control" type="text" name="nom" placeholder="Nom ...">
                              <label for="">Prenom</label>
                              <input style="margin-top: 20px;" class="form-control" type="text" name="prenom" placeholder="Prenom ...">
                              <label for="">Matricule</label>
                              <input style="margin-top: 20px;" class="form-control" type="text" name="matricule" placeholder="Matricule ...">
                              <label for="">Mot de passe </label>
                              <input style="margin-top: 20px;" class="form-control" type="text" name="motdepasse" placeholder="Mot de passe...">
                             
                            </div>

                            <div class="modal-footer">
                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                              <input style="background-color: #025ea7;" type="submit" class="btn btn-primary" value="Enregistrer">
                              
                            </div>
                          </form>
                        </div>
                      </div>
                    </div>

                    <div class="col-4"></div>

                   

                  <div class="col-5">
                    <form action="/RechercheEnqueteur" method="post">
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
						            List<Enqueteur> listEnqueteur = new ArrayList<Enqueteur>();
					
						            listEnqueteur = (List<Enqueteur>)session.getAttribute("listEnqueteur");  
                                   
                                                      	
                        %>


                  <table style="margin-top: 20px;" class="table table-borderless">
                    <thead>
                      <tr>
                        <th scope="col">Matricule</th>
                        <th scope="col">NOM</th>
                        <th scope="col">Prenom</th>
                        <th scope="col">Modifier</th>
                        <th scope="col">Etat</th>
                        
                      </tr>
                    </thead>
                    <tbody>
                  		<%  for(Enqueteur s : listEnqueteur){ %>
                        
                        
                        
                        <tr>
                          <th><%= s.getMatricule() %></th>
                           <th><%= s.getNom() %></th>    
                           <th><%= s.getPrenom() %></th>                     
                          <td> <button data-bs-toggle="modal" data-bs-target="#haha<%=s.getId()%>" style="background-color: #025ea7;" type="button" class="btn btn-primary"><i class="ri-edit-2-fill"></i></button></td>
                          
                      <div class="modal fade" id="haha<%= s.getId()%>" tabindex="-1">
                        <div class="modal-dialog modal-dialog-centered">
                          <div class="modal-content">
                            <div style="background-color: #025ea7;" class="modal-header">
                              <h5 class="modal-title" style="color: white;">Modification de la Ville numero <%=s.getId() %></h5>
                            </div>
                            <form action="/UpdateEnqueteur" method="post">
                              <div class="modal-body">
                                <label for="">Nom</label>
                                <input style="margin-top: 20px;" class="form-control" type="text" name="nom" value="<%= s.getNom() %>">
                                <label for="">Prenom</label>
                                <input style="margin-top: 20px;" class="form-control" type="text" name="prenom" value="<%= s.getPrenom() %>">
                                <label for="">Matricule</label>
                                <input style="margin-top: 20px;" class="form-control" type="text" name="matricule" value="<%= s.getMatricule() %>">
                                <label for="">Mot de passe </label>
                                <input style="margin-top: 20px;" class="form-control" type="text" name="motdepasse" value="<%= s.getMotedepasse() %>">
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
                      <% 
                      if (s.getEtat().equals("active")){ 
                        
                        %>
                      <td> <button data-bs-toggle="modal" data-bs-target="#desactiver<%=s.getId() %>" style="background-color: #025ea7;" type="button" class="btn btn-primary"><i class="bi bi-toggle-on"></i> </button></td> 
                             
                      <div class="modal fade" id="desactiver<%=s.getId() %>" tabindex="-1">
                        <div class="modal-dialog modal-dialog-centered">
                          <div class="modal-content">
                            <div style="background-color: #025ea7;" class="modal-header">
                              <h5 class="modal-title" style="color: white;">Desactiver <%=s.getNom() %></h5>
                            </div>
                           <form action="/DesactiveEnqueteur" method="get">
                            <div class="modal-body">
                              <p>Vous-voulez vraiment desactiver <%=s.getNom()%> ?</p>
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                              <input type="hidden" name="id" value="<%=s.getId()%>">
                              <input style="background-color: #025ea7;" type="submit" class="btn btn-primary" value="Desactiver">                            
                            </div>
  
                           </form>
                             
                           
                          </div>
                        </div>
                      </div>
                        </tr>
                        
                        <%
                        	}
                      		else {               
                        %>
                        
                    
                        <td> <button data-bs-toggle="modal" data-bs-target="#act<%=s.getId() %>" style="background-color: #025ea7;" type="button" class="btn btn-primary"><i class="bi bi-toggle-off"></i> </button></td> 
                             
                    <div class="modal fade" id="act<%=s.getId() %>" tabindex="-1">
                      <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                          <div style="background-color: #025ea7;" class="modal-header">
                            <h5 class="modal-title" style="color: white;">Activer <%=s.getNom() %></h5>
                          </div>
                         <form action="/ActiveEnqueteur" method="get">
                          <div class="modal-body">
                            <p>Vous-voulez vraiment activer <%=s.getNom()%> ?</p>
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            <input type="hidden" name="id" value="<%=s.getId()%>">
                            <input style="background-color: #025ea7;" type="submit" class="btn btn-primary" value="Activer">                            
                          </div>

                         </form>
                           
                         
                        </div>
                      </div>
                    </div>
                      
                      <% }} %>
         
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
                        <a style="background-color: #025ea7;" class="page-link" href="/pageville?page=<%=current-1%>"><%=current%></a>
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
                    <li class="page-item"><a class="page-link" href="/pageville?page=<%=current%>"><%=current+1%></a></li>
                    <li class="page-item disabled"><a class="page-link" href="/pageville?page=<%=current+1%>" aria-disabled="true"><%=current+2%></a></li>
                    <li class="page-item">
                      <a class="page-link" href="/pageville?page=<%=next%>">Suivant</a>
                    </li>
                    <%} else {%>

                      <li class="page-item"><a class="page-link" href="/pageville?page=<%=current%>"><%=current+1%></a></li>
                      <li class="page-item"><a class="page-link" href="/pageville?page=<%=current+1%>"><%=current+2%></a></li>
                      <li class="page-item">
                        <a class="page-link" href="/pageville?page=<%=next%>">Next</a>
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
                        <a class="page-link" href="/pageville?page=<%=previous%>" tabindex="-1" >Precedent</a>
                      </li>
                    
                     
                      <li class="page-item"><a class="page-link" href="/pageville?page=<%=current-2%>"><%=current-1%></a></li>
  
                      <li class="page-item active" aria-current="page">
                        <a style="background-color: #025ea7;" class="page-link" href="/pageville?page=<%=current-1%>"><%=current%></a>
                      </li>

                      <li class="page-item disabled"><a class="page-link" href="/pageville?page=<%=current%>" aria-disabled="true"><%=current+1%></a></li>
  
                      <li class="page-item disabled">
                        <a class="page-link" href="#" aria-disabled="true">Suivant</a>
                      </li>
                    </ul>
                  </nav>
                  <%}else{%>

                  
                  
                <nav style="margin-left: 20px;margin-top: 30px;" aria-label="...">
                  <ul class="pagination">
                    <li class="page-item ">
                      <a class="page-link" href="/pageville?page=<%=previous%>" tabindex="-1" >Precedent</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/pageville?page=<%=current-3%>"><%=current-2%></a></li>
                   
                    <li class="page-item"><a class="page-link" href="/pageville?page=<%=current-2%>"><%=current-1%></a></li>

                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/pageville?page=<%=current-1%>"><%=current%></a>
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
                      <a class="page-link" href="/pageville?page=<%=previous%>" tabindex="-1">Precedent</a>
                    </li>
                      <%  if(current==2 && total==2){
                      %>

                    <li class="page-item"><a class="page-link" href="/pageville?page=<%=current-2%>"><%=current-1%></a></li>
                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/pageville?page=<%=current-1%>"><%=current%></a>
                    </li>
                    <li class="page-item disabled"><a class="page-link" href="/pageville?page=<%=current%>" aria-disabled="true"><%=current+1%></a></li>
                    <li class="page-item disabled">
                      <a class="page-link" href="/pageville?page=<%=next%>" aria-disabled="true">Suivant</a>
                    </li>
                    <%
                      }else{
                    %>

                    <li class="page-item"><a class="page-link" href="/pageville?page=<%=current-2%>"><%=current-1%></a></li>
                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/pageville?page=<%=current-1%>"><%=current%></a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/pageville?page=<%=current%>"><%=current+1%></a></li>
                    <li class="page-item">
                      <a class="page-link" href="/pageville?page=<%=next%>" >Suivant</a>
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