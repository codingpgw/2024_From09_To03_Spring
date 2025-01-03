document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const navMenus = document.querySelectorAll('.left-nav-menu');
  const currentPath = window.location.pathname;

  // URL과 id 매핑
  navMenus.forEach(menu => {
    const menuPath = menu.getAttribute("data-path"); // HTML에 data-path 추가
    if (currentPath.includes(menuPath)) {
      navMenus.forEach(m => m.classList.remove("active")); // 모든 active 제거
      menu.classList.add("active"); // 현재 active 설정
    }
  });
  
  const showHospitalPageButton = document.querySelector("#showHospitalPage");
  const showMemberPageButton = document.querySelector("#showMemberPage");
  const showCommunityPageButton = document.querySelector("#showCommunityPage");
  const logOutButton = document.querySelector("#logOutBtn");

  showHospitalPageButton.addEventListener("click", function (event) {
    console.log("showMemberPage click");
    window.location.href = "/ehr/admin/adminRetrieveHospital.do";
  });

  showMemberPageButton.addEventListener("click", function (event) {
    console.log("showMemberPage click");
    window.location.href = "/ehr/admin/adminRetrieveMember.do";
  });

  showCommunityPageButton.addEventListener("click", function (event) {
    console.log("showCommunityPage click");
    window.location.href = "/ehr/admin/adminRetrieveCommunity.do";
  });

  logOutButton.addEventListener("click", function (event) {
    console.log("logOutButton click");
    
    if (confirm("로그아웃 하시겠습니까?") == false) return;

    window.location.href = "/ehr/login/logout.do";
  });

  // navMenus.forEach(menu => {
  //   menu.addEventListener("click", () => {
  //     navMenus.forEach(m => m.classList.remove("active"));
  //     menu.classList.add("active");
  //   })
  // })
});