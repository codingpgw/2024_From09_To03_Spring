document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doRetrieveButton = document.querySelector("#doRetrieveBtn");
  console.log(doRetrieveButton);

  const pageNoInput = document.querySelector("#pageNo");
  const searchDivSelect = document.querySelector("#searchDiv");
  const searchWordInput = document.querySelector("#searchWord");
  const pageSizeSelect = document.querySelector("#pageSize");

  const memberForm = document.querySelector("#memberForm");

  //모든 rows: header를 제외한 데이터 영역 모두 선택
  const rows = document.querySelectorAll("#listTable>tbody>tr");

  //각 row에 event감지 및 처리
  rows.forEach(function (row) {
    row.addEventListener("dblclick", function (event) {
      let cells = row.getElementsByTagName("td");
      // memId 배열에서 추출
      const memId = cells[1].innerText;
      console.log(`memId:${memId}`);

      if (confirm('회원 상세 조회 하시겠습니까?') === false) return;

      // MemberController.java
      // '/member/doSelectOne.do'를 get방식으로 memId를 전달!
      window.location.href = "/ehr/member/doSelectOne.do?memId=" + memId;
    });
  });

  doRetrieveButton.addEventListener('click', function (event) {
    event.preventDefault();//버블링 방지
    console.log("doRetrieveButton click");

    memberForm.action = "/ehr/member/doRetrieve.do";

    console.log("pageSizeSelect.value:", pageSizeSelect.value);
    console.log("searchDivSelect.value:", searchDivSelect.value);
    console.log("searchWordInput.value:", searchWordInput.value);
    console.log("pageNo.value:", memberForm.pageNo.value);

    memberForm.submit();
  });

  let moveToRegButton = document.getElementById("moveToRegBtn");

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

function pageDoRetrieve (url, pageNo) {
  console.log("pageDoRetrieve click");

  let memberForm = document.memberForm;
  memberForm.pageNo.value = pageNo;
  memberForm.action = url;

  memberForm.submit();
}