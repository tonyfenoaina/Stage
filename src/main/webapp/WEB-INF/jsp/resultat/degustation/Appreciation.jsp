
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
      <h1>Statistique Appreciation</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Statistique Appreciation</li> 
          <li class="breadcrumb-item active">Produit</li> 
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
                      <h6>Choix</h6>
                    </li>
                    <li><a class="dropdown-item" href="/Evolution_produit">Dn</a></li>
                    <li><a class="dropdown-item" href="/Evolution_prix">Prix</a></li>
                    <li><a class="dropdown-item" href="/Evolution_rotation">Rotation</a></li>
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

                  <h5 class="card-title">Appreciation dn numero 150</h5>
                  <div style="margin-top: 20px;" class="row">
                    
                    
                    <div class="col-4"></div>

                
                </div>    

                <div  id="pieChart" style="min-height: 500px;margin-top: 10px;" class="echart"></div>

                <script>
                  document.addEventListener("DOMContentLoaded", () => {
                    echarts.init(document.querySelector("#pieChart")).setOption({
                      title: {
                        text: 'Appreciation du produit',
                        subtext: 'Candia Entier',
                        left: 'center'
                      },
                      tooltip: {
                        trigger: 'item'
                      },
                      legend: {
                        orient: 'vertical',
                        left: 'left'
                      },
                      series: [{
                        name: 'Access From',
                        type: 'pie',
                        radius: '50%',
                        data: [{
                            value: 1048,
                            name: 'tres mauvais'
                          },
                          {
                            value: 735,
                            name: 'mauvais'
                          },
                          {
                            value: 580,
                            name: 'moyen'
                          },
                          {
                            value: 484,
                            name: 'bonne'
                          },
                          {
                            value: 300,
                            name: 'tres b'
                          }
                        ],
                        emphasis: {
                          itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                          }
                        }
                      }]
                    });
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