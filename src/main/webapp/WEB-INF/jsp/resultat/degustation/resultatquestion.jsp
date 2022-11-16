
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
      <h1>Gestion de Fiche Degustation</h1> 
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Resultat Degustation</li> 
         
          <li class="breadcrumb-item ">Liste des questions</li> 
         
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
            
          
            <div class="col-1"></div>
          
            <div class="col-5">
              <div class="card top-selling overflow-auto">
            

                <div style="min-height: 600px; max-height: 600px;" class="card-body pb-0">
                  
                  <!-- <nav class="header-nav ms-auto">
                    <ul class="d-flex align-items-center">
                      <li class="nav-item d-block d-lg-none">
                        <a class="nav-link nav-icon search-bar-toggle " href="#">
                          <i class="bi bi-search"></i>
                        </a>
                      </li>
                    </ul>

                  </nav> -->
                  <h5 class="card-title">Produits</h5>
                  <div style="margin-top: 20px;" class="row">
 
                    <div class="col-4">
                     
                    </div>   
                  <div class="col-3">
                    
                    <img style="max-width: 100px;" src="assets/img/logoh.png" alt="">
                  </div>
                  <div class="col-2">
                     
                  </div> 
                  <div class="col-2"><a href="/resultatdegustation"><button type="submit" style="background-color: #025ea7;" class="btn btn-primary">Terminer</button></a></div>
                </div>
                    <table width="20px" style="margin-top: 10px;margin-left: 40px;" class="table">
                      <thead>
                        <tr>                         
                          <th scope="col">Question</th>                      
                          <th scope="col">Resultat</th>
                        </tr>
                      </thead>
                      <tbody >
                        <%
                        List<DetailQuestion> listquestion = new ArrayList<DetailQuestion>();
                          listquestion = (List<DetailQuestion>)session.getAttribute("listquestion");
      
                          for (DetailQuestion d : listquestion){
                       %>
                          <tr style="margin-top: 30px;">
                            <th ><%=d.getQuestion()%> 
                            <th>
                              <form action="/analysequestion" method="get">
                                <input type="hidden" name="id" value="<%=d.getId()%>">
                                <button type="submit" style="background-color: #025ea7;" class="btn btn-primary">
                                  <i style="color: aliceblue;" class="bi bi-bar-chart"></i>
                                  </button>
                              </form> 
                          </th>
                          </tr>
                          
                         
                        <% } %>
           
                      </tbody>
                    </table>

                  <%
                  int current1 = (int)request.getAttribute("current1");
                    int total1 = (int)request.getAttribute("total1");
                    int previous1 = current1-2;
                    int next1 = current1;
                   
                  %>

                  <%
                    if(current1==1){
                  %>
                  <nav style="margin-left: 20px;margin-top: 30px;" aria-label="...">
                    <ul class="pagination">
                      <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Precedent</a>
                      </li>

                      <li class="page-item active" aria-current="page">
                        <a style="background-color: #025ea7;" class="page-link" href="/paginationMarquedn?page1=<%=current1-1%>"><%=current1%></a>
                      </li>
               
                      <%	
                      if(total1==1){
                    %>
                      <li class="page-item disabled"><a class="page-link" href="#" aria-disabled="true"><%=current1+1%></a></li>
                      <li class="page-item disabled"><a class="page-link" href="#" aria-disabled="true"><%=current1+2%></a></li>
                      <li class="page-item disabled">
                        <a class="page-link" href="#" aria-disabled="true">Suivant</a>

                      </li>

                      <%
                      }else if(total1==2){
                    %>
                    <li class="page-item"><a class="page-link" href="/paginationMarquedn?page1=<%=current1%>"><%=current1+1%></a></li>
                    <li class="page-item disabled"><a class="page-link" href="/paginationMarquedn?page1=<%=current1+1%>" aria-disabled="true"><%=current1+2%></a></li>
                    <li class="page-item">
                      <a class="page-link" href="/paginationMarquedn?page1=<%=next1%>">Suivant</a>
                    </li>
                    <%} else {%>

                      <li class="page-item"><a class="page-link" href="/paginationMarquedn?page1=<%=current1%>"><%=current1+1%></a></li>
                      <li class="page-item"><a class="page-link" href="/paginationMarquedn?page1=<%=current1+1%>"><%=current1+2%></a></li>
                      <li class="page-item">
                        <a class="page-link" href="/paginationMarquedn?page1=<%=next1%>">Suivant</a>
                      </li>

                      <%}%>

                     
                    </ul>
                  </nav>

                  

                  <%} else if(current1==total1 && current1!=1){
                      if(total1==2){
                  %>
                  <nav style="margin-left: 20px;margin-top: 30px;" aria-label="...">
                    <ul class="pagination">
                      <li class="page-item ">
                        <a class="page-link" href="/paginationMarquedn?page1=<%=previous1%>" tabindex="-1" >Precedent</a>
                      </li>
                    
                     
                      <li class="page-item"><a class="page-link" href="/paginationMarquedn?page1=<%=current1-2%>"><%=current1-1%></a></li>
  
                      <li class="page-item active" aria-current="page">
                        <a style="background-color: #025ea7;" class="page-link" href="/paginationMarquedn?page1=<%=current1-1%>"><%=current1%></a>
                      </li>

                      <li class="page-item disabled"><a class="page-link" href="/paginationMarquedn?page1=<%=current1%>" aria-disabled="true"><%=current1+1%></a></li>
  
                      <li class="page-item disabled">
                        <a class="page-link" href="#" aria-disabled="true">Suivant</a>
                      </li>
                    </ul>
                  </nav>
                  <%}else{%>

                  
                  
                <nav style="margin-left: 20px;margin-top: 30px;" aria-label="...">
                  <ul class="pagination">
                    <li class="page-item ">
                      <a class="page-link" href="/paginationMarquedn?page1=<%=previous1%>" tabindex="-1" >Precedent</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/paginationMarquedn?page1=<%=current1-3%>"><%=current1-2%></a></li>
                   
                    <li class="page-item"><a class="page-link" href="/paginationMarquedn?page1=<%=current1-2%>"><%=current1-1%></a></li>

                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/paginationMarquedn?page1=<%=current1-1%>"><%=current1%></a>
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
                      <a class="page-link" href="/paginationMarquedn?page1=<%=previous1%>" tabindex="-1">Precedent</a>
                    </li>
                      <%  if(current1==2 && total1==2){
                      %>

                    <li class="page-item"><a class="page-link" href="/paginationMarquedn?page1=<%=current1-2%>"><%=current1-1%></a></li>
                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/paginationMarquedn?page1=<%=current1-1%>"><%=current1%></a>
                    </li>
                    <li class="page-item disabled"><a class="page-link" href="/paginationMarquedn?page1=<%=current1%>" aria-disabled="true"><%=current1+1%></a></li>
                    <li class="page-item disabled">
                      <a class="page-link" href="/paginationMarquedn?page1=<%=next1%>" aria-disabled="true">Suivant</a>
                    </li>
                    <%
                      }else{
                    %>

                    <li class="page-item"><a class="page-link" href="/paginationMarquedn?page1=<%=current1-2%>"><%=current1-1%></a></li>
                    <li class="page-item active" aria-current="page">
                      <a style="background-color: #025ea7;" class="page-link" href="/paginationMarquedn?page1=<%=current1-1%>"><%=current1%></a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/paginationMarquedn?page1=<%=current1%>"><%=current1+1%></a></li>
                    <li class="page-item">
                      <a class="page-link" href="/paginationMarquedn?page1=<%=next1%>" >Suivant</a>
                    </li>

                    <%}%>

                  </ul>
                </nav>
                <%}%>
                </div>

              </div>
            </div>

            <div class="col-1"></div>
            
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