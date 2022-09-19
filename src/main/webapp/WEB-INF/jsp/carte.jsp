
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
      <h1>Gestion des emplacements</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item active">Ville</li> 
          <li class="breadcrumb-item active">Ville</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">

        <!-- Left side columns -->
        <div class="col-lg-1"></div>
        <div class="col-lg-6">
         
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
            }   
            </script>
          <script src="https://www.bing.com/api/maps/mapcontrol?callback=initMap" async defer></script>

          <div id='myMap' style='position:relative;width:1000px;height:800px;'></div>
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