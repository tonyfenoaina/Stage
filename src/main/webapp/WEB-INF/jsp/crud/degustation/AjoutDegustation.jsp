
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
      <h1>Gestion de Fiche DN</h1> 
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Gestion des Fiche DN</li> 
          <li class="breadcrumb-item ">Nouvelle Fiche de Dn</li> 
          <li class="breadcrumb-item active">Choisir Categorie</li>
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
            
            <div class="col-2">
            </div>
            
            <div class="col-6">
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

                  <h5 class="card-title">Nouvelle Enquete de Degustation</h5>
                  <form action="/AjouterDegustation" method="post" class="row g-3" style="margin-top: 10px;">
                    <div class="col-md-1">
                     
                    </div>
                    <div class="col-md-10">
                      <input name="titre" type="text" class="form-control" placeholder="Titre">
                    </div>
                    <div class="col-md-1"> </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-5">
                      <label for="" class="form-label">Debut</label>
                      <input name="debut" type="date" class="form-control" placeholder="Debut">
                    </div>
                    <div class="col-md-5">
                      <label class="form-label" for="">Fin</label>
                      <input name="fin" type="date" class="form-control" placeholder="Fin">
                    </div>

                    <div class="col-md-1">          
                    </div>

                     <div class="col-md-4"> 
                    </div>
                   
                    <div class="col-md-2">
                     
                    </div>
                    <div class="text-center">
                      <button style="background-color: #025ea7;" type="submit" class="btn btn-primary">Valider</button>
                      
                    </div>
                  </form>
                  
                 
                
                </div>
                <div class="col-md-2">
                     .
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