
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
      <h1>Resultat Question</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Resultat Question</li> 
          <li class="breadcrumb-item active">Question</li> 
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

                  <h5 class="card-title">Resultat de la  question</h5>
                  <div style="margin-top: 20px;" class="row">
                    
                    
                    <div class="col-4"></div>

                
                </div>    

                <div  id="pieChart" style="min-height: 500px;margin-top: 10px;" class="echart"></div>

                <script>
                  document.addEventListener("DOMContentLoaded", () => {
                    echarts.init(document.querySelector("#pieChart")).setOption({
                      title: {
                        text: 'Comment jugez-vous la couleur?',
                        subtext: '',
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
                            name: 'tres intense'
                          },
                          {
                            value: 735,
                            name: 'intense'
                          },
                          {
                            value: 580,
                            name: 'moyen'
                          },
                          {
                            value: 484,
                            name: 'faible'
                          },
                          {
                            value: 300,
                            name: 'tres faible'
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