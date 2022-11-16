
<header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
      <a href="#" class="logo d-flex align-items-center">
        <img src="assets/img/logoh.png" alt="">       
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->

   <!-- End Search Bar -->

    <nav class="header-nav ms-auto">
      <ul class="d-flex align-items-center">

        <li class="nav-item d-block d-lg-none">
          <a class="nav-link nav-icon search-bar-toggle " href="#">
            <i class="bi bi-search"></i>
          </a>
        </li><!-- End Search Icon-->

        <li class="nav-item dropdown">

          <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
            <i class="bi bi-bell"></i>
            <span class="badge bg-primary badge-number">4</span>
          </a><!-- End Notification Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications">
            <li class="dropdown-header">
              You have 4 new notifications
              <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="notification-item">
              <i class="bi bi-exclamation-circle text-warning"></i>
              <div>
                <h4>Lorem Ipsum</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>30 min. ago</p>
              </div>
            </li>

            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="notification-item">
              <i class="bi bi-x-circle text-danger"></i>
              <div>
                <h4>Atque rerum nesciunt</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>1 hr. ago</p>
              </div>
            </li>

            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="notification-item">
              <i class="bi bi-check-circle text-success"></i>
              <div>
                <h4>Sit rerum fuga</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>2 hrs. ago</p>
              </div>
            </li>

            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="notification-item">
              <i class="bi bi-info-circle text-primary"></i>
              <div>
                <h4>Dicta reprehenderit</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>4 hrs. ago</p>
              </div>
            </li>

            <li>
              <hr class="dropdown-divider">
            </li>
            <li class="dropdown-footer">
              <a href="#">Show all notifications</a>
            </li>

          </ul><!-- End Notification Dropdown Items -->

        </li><!-- End Notification Nav -->

        <li class="nav-item dropdown">

          <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
            <i class="bi bi-chat-left-text"></i>
            <span class="badge bg-success badge-number">3</span>
          </a><!-- End Messages Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow messages">
            <li class="dropdown-header">
              You have 3 new messages
              <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="message-item">
              <a href="#">
                <img src="assets/img/messages-1.jpg" alt="" class="rounded-circle">
                <div>
                  <h4>Maria Hudson</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>4 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="message-item">
              <a href="#">
                <img src="assets/img/messages-2.jpg" alt="" class="rounded-circle">
                <div>
                  <h4>Anna Nelson</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>6 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="message-item">
              <a href="#">
                <img src="assets/img/messages-3.jpg" alt="" class="rounded-circle">
                <div>
                  <h4>David Muldon</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>8 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="dropdown-footer">
              <a href="#">Show all messages</a>
            </li>

          </ul><!-- End Messages Dropdown Items -->

        </li><!-- End Messages Nav -->

        <li class="nav-item dropdown pe-3">

          <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
            <img src="assets/img/images.png" alt="Profile" class="rounded-circle" >
            <span class="d-none d-md-block dropdown-toggle ps-2">Tony Fenoaina</span>
          </a><!-- End Profile Iamge Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
            <li class="dropdown-header">
              <h6>Tony Fenoaina Randriamampianina</h6>
              <span>Web Developper</span>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>8
              
              <!-- <a class="dropdown-item d-flex align-items-center" >
                <button data-bs-toggle="modal" data-bs-target="deconnexion" style="background-color: #025ea7;" type="button" class="btn btn-primary"><i class="ri-logout-box-r-line"></i>deconnexion</button> 
                
                <div class="modal fade" id="deconnexion" tabindex="-1">
                  <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                      <div style="background-color: #025ea7;" class="modal-header">
                        <h5 class="modal-title" style="color: white;">Deconnexon %></h5>
                      </div>
                      <form action="/logout" method="post">
                        <div class="modal-body">
                            <p>Vous voulez vraiment vous Deconnecter?</p>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                          <input style="background-color: #025ea7;" type="submit" class="btn btn-primary" value="Deconnecter">
                          
                        </div>
                      </form>
                    </div>
                  </div>
                     </div>
              </a> -->
            </li>

          </ul><!-- End Profile Dropdown Items -->
        </li><!-- End Profile Nav -->

      </ul>
    </nav><!-- End Icons Navigation -->

  </header><!-- End Header -->

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link collapsed" href="/Dn">
          <i class="bi bi-grid"></i>
          <span>DN </span>
        </a>
      </li><!-- End Dashboard Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#components-navs" data-bs-toggle="collapse" href="#">
          <i class="bi bi-emoji-wink-fill"></i><span>Degustation</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="components-navs" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/cible">
              <i class="bi bi-circle"></i><span>Cible</span>
            </a>
          </li>
          <li>
            <a href="/degustation">
              <i class="bi bi-circle"></i><span>Fiche de Degustation</span>
            </a>
          </li>
        </ul>
      </li><!-- End Components Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-people-fill"></i><span>Utilisateurs</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="components-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/utilisateur">
              <i class="bi bi-circle"></i><span>Responsable</span>
            </a>
          </li>
          <li>
            <a href="/enqueteur">
              <i class="bi bi-circle"></i><span>Enqueteur</span>
            </a>
          </li>
          <li>
            <a href="/fonction">
              <i class="bi bi-circle"></i><span>Fonction</span>
            </a>
          </li>
        </ul>
      </li>

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-geo-alt"></i><span>Emplacements</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="forms-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/ville">
              <i class="bi bi-circle"></i><span>Ville</span>
            </a>
          </li>
          <li>
            <a href="/quartier">
              <i class="bi bi-circle"></i><span>Quartier</span>
            </a>
          </li>
         
        </ul>
      </li><!-- End Forms Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#tables-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-card-checklist"></i><span>Suivie</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="tables-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/listproduit_fiche">
              <i class="bi bi-circle"></i><span>Produit</span>
            </a>
          </li>
          <li>
            <a href="marque_list_fiche">
              <i class="bi bi-circle"></i><span>Marque</span>
            </a>
          </li>
        </ul>
      </li><!-- End Tables Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#charts-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-bar-chart"></i><span>Statistiques</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="charts-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/categorieEvolution">
              <i class="bi bi-circle"></i><span>Evolution des Marques</span>
            </a>
          </li>
          <li>
            <a href="/marque_list">
              <i class="bi bi-circle"></i><span>Evolution des Produits</span>
            </a>
          </li>
        </ul>
      </li><!-- End Charts Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#icons-nav" data-bs-toggle="collapse" href="#">
          <i class="bx bx-basket"></i><span>Produits</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="icons-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/categorie">
              <i class="bi bi-circle"></i><span>Gestion des Produits</span>
            </a>
          </li>      
        </ul>
      </li><!-- End Icons Nav -->

      <li class="nav-heading">Pages</li>

      <li class="nav-item">
        <a class="nav-link collapsed" href="/Dn">
          <i class="bi bi-grid"></i>
          <span> Fiche DN </span>
        </a>
      </li>
     
      <li class="nav-item">
        <a class="nav-link collapsed" href="/degustation">
          <i class="bi bi-emoji-wink-fill"></i>
          <span>Fiche Degustation</span>
        </a>
      </li><!-- End F.A.Q Page Nav -->

      

      <li class="nav-item">
        <a class="nav-link collapsed" href="/ficheenqueteur">
          <i class="bi bi-card-list"></i>
          <span>Suivie Enqueteur</span>
        </a>
      </li><!-- End Register Page Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="/listproduit_fiche">
          <i class="bx bx-chart"></i>
          <span>Suivie Produit</span>
        </a>
      </li><!-- End Login Page Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="/marque_list">
          <i class="bx bx-line-chart"></i>
          <span>Evolution Produit</span>
        </a>
      </li><!-- End Login Page Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="/resultatdn">
          <i class="bx bx-line-chart"></i>
          <span>Resultat DN</span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link collapsed" href="/resultatdegustation">
          <i class="bx bx-line-chart"></i>
          <span>Resultat Degustation</span>
        </a>
      </li><!-- End Login Page Nav -->

    
    </ul>

  </aside><!-- End Sidebar-->