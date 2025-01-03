document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doRetrieveButton = document.querySelector("#doRetrieveBtn");
  const moveToRegButton = document.getElementById("moveToRegBtn");

  const searchDivSelect = document.querySelector("#searchDiv");
  const searchWordInput = document.querySelector("#searchWord");
  const pageSizeSelect = document.querySelector("#pageSize");

  const memberForm = document.querySelector("#memberForm");

  const rows = document.querySelectorAll("#listTable>tbody>tr");

  const hospitalMngmodal = document.getElementById("hospitalMngmodal");
  const hospitalRegmodal = document.getElementById("hospitalRegmodal");
  const closeBtn = document.querySelectorAll(".close-btn");;

  rows.forEach(function (row) {
    row.addEventListener("dblclick", function (event) {
      let cells = row.getElementsByTagName("td");
      const hospital_id = cells[0].innerText;
      console.log(`hospital_id: ${hospital_id}`);

      // 서버에서 회원 정보를 가져오는 AJAX 요청
      fetch(`/ehr/admin/adminHospitalSelectOne.do?hospital_id=${hospital_id}`)
        .then(response => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json();
        })
        .then(data => {
          // 모달에 데이터를 채우기
          document.getElementById("hospital_id").value = data.hospital_id;
          document.getElementById("hospital_name").value = data.hospital_name;
          document.getElementById("hospital_addr").value = data.hospital_addr;
          document.getElementById("hospital_div").value = data.hospital_div;
          document.getElementById("hospital_etc").value = data.hospital_etc;
          document.getElementById("hospital_mapimg").value = data.hospital_mapimg;
          document.getElementById("hospital_tel").value = data.hospital_tel;
          document.getElementById("hospital_lon").value = data.hospital_lon;
          document.getElementById("hospital_lat").value = data.hospital_lat;
          document.getElementById("hospital_time_mon").value = data.hospital_time_mon;
          document.getElementById("hospital_time_tue").value = data.hospital_time_tue;
          document.getElementById("hospital_time_wed").value = data.hospital_time_wed;
          document.getElementById("hospital_time_thu").value = data.hospital_time_thu;
          document.getElementById("hospital_time_fri").value = data.hospital_time_fri;
          document.getElementById("hospital_time_sat").value = data.hospital_time_sat;
          document.getElementById("hospital_time_sun").value = data.hospital_time_sun;
          document.getElementById("hospital_time_hol").value = data.hospital_time_hol;

          // 모달 표시
          hospitalMngmodal.style.display = "flex";
          document.body.classList.add("modal-open");
        })
        .catch(error => {
          console.log("Error fetching member data:", error);
          alert("회원 정보를 가져오는데 실패했습니다.");
        });
    });
  });

  
  // 조회 버튼
  doRetrieveButton.addEventListener('click', function (event) {
    event.preventDefault();//버블링 방지
    console.log("doRetrieveButton click");

    memberForm.action = "/ehr/admin/adminRetrieveHospital.do";

    console.log("pageSizeSelect.value:", pageSizeSelect.value);
    console.log("searchDivSelect.value:", searchDivSelect.value);
    console.log("searchWordInput.value:", searchWordInput.value);
    console.log("pageNo.value:", memberForm.pageNo.value);

    memberForm.submit();
  });

  moveToRegButton.addEventListener("click", function () {
    console.log("moveToRegButton click");

    hospitalRegmodal.style.display = "flex";
    document.body.classList.add("modal-open");
  });

  // 모달 닫기 버튼
  closeBtn.forEach(btn => {
    btn.addEventListener("click", function () {
      hospitalMngmodal.style.display = "none";
      hospitalRegmodal.style.display = "none";
      document.body.classList.remove("modal-open");
    });
  });

  // 배경 클릭 시 모달 닫기
  window.addEventListener("click", function (event) {
    if (event.target === modal) {
      hospitalMngmodal.style.display = "none";
      hospitalRegmodal.style.display = "none";
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