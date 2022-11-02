
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
      <h1>Suivie des Utilisateurs</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item ">Suivie</li> 
          <li class="breadcrumb-item active">Utilisateur</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

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
              <h5 class="card-title">Suivie Utilisateur</h5>
              <div style="margin-top: 20px;" class="row">   
                <div class="col-1"></div>
     
              <div style="margin-bottom: 10px;" class="col-5">
                <form action="/suivie" method="get">
                  <input type="hidden" name="id" value="<%= session.getAttribute("idenqueteur")%>">
                  <div class="row">
                    <%
                        if(request.getAttribute("retour")!=null){                             
                    %>
                    <div class="col-6">
                      <input type="search" name="nom" id="nom"  class="form-control" placeholder="Recherche" value="<%= request.getAttribute("retour") %>">
                    </div>
                    <%}else{%>
                      <div class="col-6">
                        <input type="date" name="date" id="nom"  class="form-control" placeholder="Recherche">
                      </div>
                    <%}%>
                    <div class="col-2">
                      <input class="btn btn-primary" style="margin-top: 0px;background-color: #025ea7;color: white;" type="submit" value="Rechercher">    
                    </div>

                  </div>                      
                </form>

               
              </div>
              <div style="margin-left: 50px;" >
                <script type="text/javascript">
                  var map = null;
            
                  function initMap()
                  {
                    map = new Microsoft.Maps.Map('#myMap', {
                            credentials: 'Amusif-Uc4Rs9pZBrU1EXmZMK2VG7--zQw5PJ8r72kIL9fP4EFS7WQw370dDbXIJ',
                            center: new Microsoft.Maps.Location(-19.000000,47.060000 ),
                            zoom: 7,
                            mapTypeId: Microsoft.Maps.MapTypeId.SATELLITE
                            
                        });   
                          <%
                              ArrayList<Ficheenqueteur> list = new ArrayList<Ficheenqueteur>();
                                list = (ArrayList<Ficheenqueteur>)session.getAttribute("emplacement");

                                  for (int i = 0; i < list.size(); i++) {
                                    
                          %>           
                         var couleur = 'red'
                         var pushpin = new Microsoft.Maps.Pushpin(new Microsoft.Maps.Location("<%=list.get(i).getLatitude()%>","<%=list.get(i).getLongitude()%>"),{color: couleur });
                        var layer = new Microsoft.Maps.Layer();
                          layer.add(pushpin);
                        map.layers.insert(layer);
                        var infobox<%=i%> = new Microsoft.Maps.Infobox(new Microsoft.Maps.Location("<%=list.get(i).getLatitude()%>","<%=list.get(i).getLongitude()%>"), {
                      description: '<div style="padding:5px"><b><h5><%=list.get(i).getQuartier()%></h5></b></br></br></br><input type="submit" value="Affecter"></div>',
                      visible: false
                      });

                      infobox<%=i%> .setMap(map);

                        Microsoft.Maps.Events.addHandler(pushpin, 'click', function () {
                          infobox<%=i%>.setOptions({ visible: true });
                        })

                        <%
                            }
                        %>


                        
                  }   
                  </script>
                <script src="https://www.bing.com/api/maps/mapcontrol?callback=initMap" async defer></script>
      
                <div id='myMap' style='position:relative;width:1000px;height:800px;'></div>
                
              </div>
              
            </div>
             
              
             
            </div>

          </div>
        </div>
        
        <!-- End Top Selling -->

       

      </div>
    </div><!-- End Left side columns -->

    <section class="section dashboard">
      <div class="row">

        <!-- Left side columns -->
        <div class="col-lg-1"></div>
        <div class="col-lg-6">
         
          
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