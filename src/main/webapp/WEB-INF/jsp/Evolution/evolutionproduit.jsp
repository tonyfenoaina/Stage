
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
      <h1>Gestion des Produits</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Evolution des Produits par marque</li> 
          <li class="breadcrumb-item"><a href="categorie">Categorie</a> </li>
          <li class="breadcrumb-item active">Marque</li> 
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

                  <h5 class="card-title">Evolution</h5>
                  <div style="margin-top: 20px;" class="row">
                    
                    
                    <div class="col-4"></div>

                  <div class="col-5">
                    <form action="/Evolution_produitparmois" method="get">
                      <div class="row">
                       <% if (request.getAttribute("mois1")!=null) {%>
                        <div class="col-5">
                          <input type="month" name="mois1" id="nom"  class="form-control" placeholder="Mois 1" value="<%=request.getAttribute("mois1")%>">
                        </div>
                        <div class="col-5">
                          <input type="month" name="mois2" id="nom"  class="form-control" placeholder="Mois 2" value="<%=request.getAttribute("mois2")%>">
                        </div>     
                        <%}else{%>
                          <div class="col-5">
                            <input type="month" name="mois1" id="nom"  class="form-control" placeholder="Mois 1">
                          </div>
                          <div class="col-5">
                            <input type="month" name="mois2" id="nom"  class="form-control" placeholder="Mois 2">
                          </div> 
                          <%}%>
                        <div class="col-2">
                          <input class="btn btn-primary" style="margin-top: 0px;background-color: #025ea7;color: white;" type="submit" value="Analyser">    
                        </div>
                      </div>       
                    </form>
                  </div>
                </div>    
                <div id="columnChart"></div>
                <script>
                  document.addEventListener("DOMContentLoaded", () => {
                    new ApexCharts(document.querySelector("#columnChart"), {
                      series: [
                      <% if (request.getAttribute("mois1")!=null) { %>
                        {
                            name: '<%=request.getAttribute("mois1")%>',
                             data: <%=request.getAttribute("data1")%>
                            }, {
                            name: '<%=request.getAttribute("mois2")%>',
                            data: <%=request.getAttribute("data2")%>
                            },                        
                      <%} %>  
                      ],
                      chart: {
                        type: 'bar',
                        height: 350
                      },
                      plotOptions: {
                        bar: {
                          horizontal: false,
                          columnWidth: '55%',
                          endingShape: 'rounded'
                        },
                      },
                      dataLabels: {
                        enabled: false
                      },
                      stroke: {
                        show: true,
                        width: 2,
                        colors: ['transparent']
                      },
                      xaxis: {
                        categories: [
                        <%ArrayList<String> listn = new ArrayList<String>();
                        listn = (ArrayList<String>)request.getAttribute("nomProduit");
                          for (int index = 0; index < listn.size(); index++) {%>  
                        '<%=listn.get(index)%>',
                      <% }%>
                      ],
                      },
                      yaxis: {
                        title: {
                          text: 'DN (pourcentage)'
                        }
                      },
                      fill: {
                        opacity: 1
                      },
                      tooltip: {
                        y: {
                          formatter: function(val) {
                            return "DN " + val + "%"
                          }
                        }
                      }
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