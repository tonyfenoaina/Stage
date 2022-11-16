
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.example.demo.model.*" %>

<%   List<Detailmarque> listMarque = new ArrayList<Detailmarque>();
listMarque = (List<Detailmarque>)session.getAttribute("listMarque"); %>
<!DOCTYPE html>
<html lang="en">
  <jsp:include page="/WEB-INF/jsp/element/head.jsp" />
<body>
  <!-- ======= Header ======= -->
  <jsp:include page="/WEB-INF/jsp/element/header.jsp" />
  <main id="main" class="main">
  
    
    <div style="margin-left: 100px;" class="pagetitle">
      <h1>Resultat DN</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Resultat DN</li> 
          <li class="breadcrumb-item active">Statistique</li> 
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
                  <a class="icon" href="#" data-bs-toggle="dropdown">Filtre</a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Choix</h6>
                    </li>
                    <%int  iddn = (int)session.getAttribute("iddn");%>
                    <li><a class="dropdown-item" href="/resultatdnstat?iddn=<%=iddn%>">Dn</a></li>
                    <li><a class="dropdown-item" href="/resultatdnprix">Prix</a></li>
                    <li><a class="dropdown-item" href="/resultatdnrotation">Rotation</a></li>
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

                  <h5 class="card-title">Statistique de <%=session.getAttribute("enquete")%></h5>
                  <div style="margin-top: 20px;" class="row">
                    
                    
                    <div class="col-4"></div>

                </div>    
                <div id="pieChart"></div>
                <%
                  ArrayList<String> list = new ArrayList<String>();
                    list = (ArrayList<String>)request.getAttribute("nom"); 
                %>
                <script>
                  document.addEventListener("DOMContentLoaded", () => {
                    new ApexCharts(document.querySelector("#pieChart"), {
                      series: <%= request.getAttribute("data1")%>,
                      chart: {
                        height: 350,
                        type: 'pie',
                        toolbar: {
                          show: true
                        }
                      },
                      labels: [<%
                        for (int i = 0; i < list.size(); i++) {
                       %>   
                      '<%=list.get(i)%>',
                    <%}%>
]
                    }).render();
                  });
                </script>
             
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