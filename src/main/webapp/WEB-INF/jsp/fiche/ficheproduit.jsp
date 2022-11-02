
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
      <h1>Fiche de Produit</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Produit</li> 
          <li class="breadcrumb-item active">Fiche</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">

          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

              <img src="assets/img/panier.jpg" alt="Profile" class="rounded-circle">
              <h2><%=request.getAttribute("nom")%></h2>
              <h3>Habibo</h3>
              
            </div>
          </div>

        </div>

        <div class="col-xl-5">

          <div class="card">
            <div class="card-body ">
              <!-- Bordered Tabs -->
              <%
              Rotation_prix listMoyen = new Rotation_prix();
              Rotation_prix listMax  = new Rotation_prix();
              Rotation_prix listMin = new Rotation_prix();

              listMoyen = (Rotation_prix) request.getAttribute("listMoyen");
              listMax = (Rotation_prix) request.getAttribute("listMax");
              listMin = (Rotation_prix) request.getAttribute("listMin");
              
              %>
              <div>
              
                <div class="tab-pane fade show active profile-overview" id="profile-overview">
                   <h5 style="color: WHITE;" class="card-title">Detail Produits</h5>

                  <div class="row">
                    <div style="margin-left: 20px;" class="col-lg-6 col-md-4 label ">Nom Produit :</div>
                    <div class="col-lg-5 col-md-8"><%=request.getAttribute("nom")%></div>
                  </div>
                  <% if(listMoyen!=null) {%>
                  <div class="row">
                    <div style="margin-left: 20px;" class="col-lg-6 col-md-4 label">Dn courrante :</div>
                    <div class="col-lg-5 col-md-8"><%=request.getAttribute("CurrentDn")%>%</div>
                  </div>

                  <div class="row">
                    <div style="margin-left: 20px;" class="col-lg-6 col-md-4 label">Prix Moyen Courante :</div>
                    <div class="col-lg-5 col-md-8"><%=listMoyen.getPrix()%> ARIARY</div>
                  </div>

                  <div class="row">
                    <div style="margin-left: 20px;" class="col-lg-6 col-md-4 label">Prix plus elevee Courante :</div>
                    <div class="col-lg-5 col-md-8"><%=listMax.getPrix()%> ARIARY</div>
                  </div>

                  <div class="row">
                    <div style="margin-left: 20px;" class="col-lg-6 col-md-4 label">Prix moins elevee Courante :</div>
                    <div class="col-lg-5 col-md-8"><%=listMin.getPrix()%> ARIARY</div>
                  </div>

                  <div class="row">
                    <div style="margin-left: 20px;" class="col-lg-6 col-md-4 label">Rotation Moyenne :</div>
                    <div class="col-lg-5 col-md-8"><%=listMoyen.getRotation()%> pck</div>
                  </div>

                  <div class="row">
                    <div style="margin-left: 20px;" class="col-lg-6 col-md-4 label">Rotation plus elevee : </div>
                    <div class="col-lg-5 col-md-8"><%=listMax.getRotation()%> pck</div>
                  </div>

                  <div class="row">
                    <div style="margin-left: 20px;" class="col-lg-6 col-md-4 label">Rotation moins elevee : </div>
                    <div class="col-lg-5 col-md-8"><%=listMin.getRotation()%> pck</div>
                  </div>

                  <div class="row">
                    <div style="margin-left: 20px;" class="col-lg-6 col-md-4 label">Derniere mise a jours  :</div>
                    <div class="col-lg-5 col-md-8"> <%=listMin.getMois()%></div>
                  </div>
                  <%}else{%>
                    <div class="row">
                      <div style="margin-left: 20px;" class="col-lg-6 col-md-4 label">Cette produit n'est pas encore mise en enquete</div>
                      <div class="col-lg-5 col-md-8"> </div>
                    </div>
                    <%}%>
                  <p style="margin-left: 300px" class="card-text">
                    <a href="/concurrence_produit" class="btn btn-primary">Analyse de la concurrence</a></p>
                </div>
              </div><!-- End Bordered Tabs -->

            </div>
          </div>

        </div>
      </div>
    </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <jsp:include page="/WEB-INF/jsp/element/footer.jsp"/>

</body>

</html>