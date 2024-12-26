<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css" integrity="sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="/ehr/resources/assets/css/hospital/nav.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <div id="test">
    <nav class="navbar navbar-light bg-light">
      <div class="container-fluid">
        
        <span class="navbar-brand">Navbar</span>
  
        <form class="search-box" action="" method="post">
          <input class="search-txt" type="text" name="search" placeholder="검색어를 입력하세요.">
          <button class="search-btn" type="submit">
            <i class="fa-solid fa-magnifying-glass"></i>
          </button>
        </form>
  
        <button class="navbar-toggler" type="button">
          <span class="navbar-toggler-icon">
            <div class="user-menu">
              <div class="login-box">
                <h4><a href="#">로그인</a></h4>
              </div>
              <div class="container">
                <div class="menu-item1">메뉴1</div>
                <div class="menu-item2">메뉴2</div>
                <div class="menu-item3">메뉴3</div>
                <div class="menu-item4">메뉴4</div>
                <div class="box">뭐 넣지</div>
              </div>
            </div> 
          </span>
        </button>

      </div>
    </nav>
    
    <div class="menu-box">
      <ul class="simple-menu">
        <li><a href="#">test</a></li>
        <li><a href="#">test</a></li>
        <li><a href="#">test</a></li>
        <li><a href="#">test</a></li>
        <li><a href="#">test</a></li>
        <li><a href="#">test</a></li>
      </ul>
    </div>
  </div>
  
  

  
  <script>
    document.getElementsByClassName('navbar-toggler')[0].addEventListener('click',function(){
      document.getElementsByClassName('user-menu')[0].classList.toggle('show');
    })
  </script>
</body>
</html>