document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doRetrieveButton = document.querySelector("#doRetrieveBtn");
  const moveToRegButton = document.getElementById("moveToRegBtn");

  const searchDivSelect = document.querySelector("#searchDiv");
  const searchWordInput = document.querySelector("#searchWord");
  const pageSizeSelect = document.querySelector("#pageSize");

  const memberForm = document.querySelector("#memberForm");

  //모든 rows: header를 제외한 데이터 영역 모두 선택
  const rows = document.querySelectorAll("#listTable>tbody>tr");

  const modal = document.getElementById("memberModal");
  const closeBtn = document.querySelectorAll(".close-btn");;

  rows.forEach(function (row) {
    row.addEventListener("dblclick", function (event) {
      let cells = row.getElementsByTagName("td");
      const memId = cells[0].innerText;
      console.log(`memId: ${memId}`);

      // 서버에서 회원 정보를 가져오는 AJAX 요청
      fetch(`/ehr/admin/adminSelectOne.do?memId=${memId}`)
        .then(response => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json();
        })
        .then(data => {
          // 모달에 데이터를 채우기
          document.getElementById("memId").value = data.memId;
          document.getElementById("name").value = data.name;
          document.getElementById("email").value = data.email;
          document.getElementById("phone").value = data.phone;
          document.getElementById("password").value = data.password;
          document.getElementById("jumin").value = data.jumin;
          document.getElementById("memDiv").value = data.memDiv;

          // 모달 표시
          modal.style.display = "flex";
          document.body.classList.add("modal-open");
        })
        .catch(error => {
          console.log("Error fetching member data:", error);
          alert("회원 정보를 가져오는데 실패했습니다.");
        });
    });
  });

  // 모달 닫기 버튼
  closeBtn.forEach(btn => {
    btn.addEventListener("click", function () {
      modal.style.display = "none";
      document.body.classList.remove("modal-open");
    });
  });

  // 배경 클릭 시 모달 닫기
  window.addEventListener("click", function (event) {
    if (event.target === modal) {
      modal.style.display = "none";
      document.body.classList.remove("modal-open");
    }
  });
  //각 row에 event감지 및 처리
  // rows.forEach(function (row) {
  //   row.addEventListener("dblclick", function (event) {
  //     let cells = row.getElementsByTagName("td");
  //     // memId 배열에서 추출
  //     const memId = cells[0].innerText;
  //     console.log(`memId:${memId}`);

  //     if (confirm('회원 상세 조회 하시겠습니까?') === false) return;

  //     const pages = document.querySelectorAll('.content-page');
  //     pages.forEach(page => page.style.display = 'none');
  //     const memberPage = document.getElementById("memberMngPage");
  //     memberPage.style.display = 'block';

  //     // MemberController.java
  //     // '/member/doSelectOne.do'를 get방식으로 memId를 전달!
  //     window.location.href = "/ehr/admin/adminSelectOne.do?memId=" + memId;
  //   });
  // });

  doRetrieveButton.addEventListener('click', function (event) {
    event.preventDefault();//버블링 방지
    console.log("doRetrieveButton click");

    memberForm.action = "/ehr/admin/adminRetrieve.do";

    console.log("pageSizeSelect.value:", pageSizeSelect.value);
    console.log("searchDivSelect.value:", searchDivSelect.value);
    console.log("searchWordInput.value:", searchWordInput.value);
    console.log("pageNo.value:", memberForm.pageNo.value);

    memberForm.submit();
  });

  moveToRegButton.addEventListener("click", function () {
    console.log("moveToRegButton click");
    moveToReg();
  });

  function moveToReg() {
    console.log("moveToReg()");
    if (confirm('회원 등록 화면으로 이동 하시겠습니까?') == false) return;

    window.location.href = "/ehr/member/member_reg_index.do";
  }
});

function pageDoRetrieve(url, pageNo) {
  console.log("pageDoRetrieve click");

  let memberForm = document.memberForm;
  memberForm.pageNo.value = pageNo;
  memberForm.action = url;

  memberForm.submit();
}

function openModalWithMemberData(memberData) {
  const modal = document.querySelector("#memberModal");

  // 모달의 input fields를 업데이트
  document.querySelector("#memId").value = memberData.memId;
  document.querySelector("#password").value = memberData.password;
  document.querySelector("#name").value = memberData.name;
  document.querySelector("#email").value = memberData.email;
  document.querySelector("#phone").value = memberData.phone;
  document.querySelector("#jumin").value = memberData.jumin;
  document.querySelector("#memDiv").value = memberData.memDiv;

  // 모달을 표시
  modal.style.display = "block";
}