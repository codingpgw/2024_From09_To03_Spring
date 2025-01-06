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

  const communityMngModal = document.getElementById("communityMngModal");
  const communityRegModal = document.getElementById("communityRegModal");
  const closeBtn = document.querySelectorAll(".close-btn");

  rows.forEach(function (row) {
    row.addEventListener("dblclick", function (event) {
      let cells = row.getElementsByTagName("td");
      const cmnNo = cells[5].innerText;
      console.log(`cmnNo: ${cmnNo}`);

      // 서버에서 회원 정보를 가져오는 AJAX 요청
      fetch(`/ehr/admin/adminCommunitySelectOne.do?cmnNo=${cmnNo}`)
        .then(response => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json();
        })
        .then(data => {
          // 모달에 데이터를 채우기
          document.getElementById("cmnNo").value = data.cmnNo;
          document.getElementById("title").value = data.title;
          document.getElementById("view").value = data.view;
          document.getElementById("memId").value = data.memId;
          document.getElementById("regDt").value = data.regDt;
          document.getElementById("modDt").value = data.modDt;
          document.getElementById("content").value = data.content;

          // 모달 표시
          communityMngModal.style.display = "flex";
          document.body.classList.add("modal-open");
        })
        .catch(error => {
          console.log("Error fetching member data:", error);
          alert("글 정보를 가져오는데 실패했습니다.");
        });
    });
  });

  // 조회 버튼
  doRetrieveButton.addEventListener("click", function (event) {
    event.preventDefault();//버블링 방지
    console.log("doRetrieveButton click");

    memberForm.action = "/ehr/admin/adminRetrieveCommunity.do";

    console.log("pageSizeSelect.value:", pageSizeSelect.value);
    console.log("searchDivSelect.value:", searchDivSelect.value);
    console.log("searchWordInput.value:", searchWordInput.value);
    console.log("pageNo.value:", memberForm.pageNo.value);

    memberForm.submit();
  });

  // 등록 버튼
  moveToRegButton.addEventListener("click", function () {
    console.log("moveToRegButton click");

    communityRegModal.style.display = "flex";
    document.body.classList.add("modal-open");
  });

  // 모달 닫기 버튼
  closeBtn.forEach(btn => {
    btn.addEventListener("click", function () {
      console.log("closeBtn click");
      communityMngModal.style.display = "none";
      communityRegModal.style.display = "none";
      document.body.classList.remove("modal-open");
    });
  });

  // 배경 클릭 시 모달 닫기
  window.addEventListener("click", function (event) {
    if (event.target === communityMngModal || event.target === communityRegModal) {
      console.log("closeBtn click");
      communityMngModal.style.display = "none";
      communityRegModal.style.display = "none";
      document.body.classList.remove("modal-open");
    }
  });
});

function pageDoRetrieve(url, pageNo) {
  console.log("pageDoRetrieve click");

  let memberForm = document.memberForm;
  memberForm.pageNo.value = pageNo;
  memberForm.action = url;

  memberForm.submit();
}