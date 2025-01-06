document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doSaveButton = document.querySelector("#doSave");

  const hospitalIdInput = document.querySelector("#hospital_id");
  const hospitalNameInput = document.querySelector("#hospital_name");
  const hospitalAddrInput = document.querySelector("#hospital_addr");
  const hospitalDivInput = document.querySelector("#hospital_div");
  const hospitalEtcInput = document.querySelector("#hospital_etc");
  const hospitalMapImgInput = document.querySelector("#hospital_mapimg");
  const hospitalTelInput = document.querySelector("#hospital_tel");
  const hospitalLonInput = document.querySelector("#hospital_lon");
  const hospitalLatInput = document.querySelector("#hospital_lat");
  const hospitalTimeMonInput = document.querySelector("#hospital_time_mon");
  const hospitalTimeTueInput = document.querySelector("#hospital_time_tue");
  const hospitalTimeWedInput = document.querySelector("#hospital_time_wed");
  const hospitalTimeThuInput = document.querySelector("#hospital_time_thu");
  const hospitalTimeFriInput = document.querySelector("#hospital_time_fri");
  const hospitalTimeSatInput = document.querySelector("#hospital_time_sat");
  const hospitalTimeSunInput = document.querySelector("#hospital_time_sun");
  const hospitalTimeHolInput = document.querySelector("#hospital_time_hol");

  doSaveButton.addEventListener("click", function (event) {
    console.log("doSaveButton click");
    console.log("hospitalIdInput.value:", hospitalIdInput.value);

    if(isEmpty (hospitalIdInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalIdInput.focus();
      return;
    }

    if(isEmpty (hospitalNameInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalNameInput.focus();
      return;
    }

    if(isEmpty (hospitalAddrInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalAddrInput.focus();
      return;
    }

    if(isEmpty (hospitalDivInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalDivInput.focus();
      return;
    }

    if(isEmpty (hospitalEtcInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalEtcInput.focus();
      return;
    }

    if(isEmpty (hospitalMapImgInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalMapImgInput.focus();
      return;
    }

    if(isEmpty (hospitalTelInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalTelInput.focus();
      return;
    }

    if(isEmpty (hospitalLonInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalLonInput.focus();
      return;
    }

    if(isEmpty (hospitalLatInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalLatInput.focus();
      return;
    }

    if(isEmpty (hospitalTimeMonInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalTimeMonInput.focus();
      return;
    }

    if(isEmpty (hospitalTimeTueInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalTimeTueInput.focus();
      return;
    }

    if(isEmpty (hospitalTimeWedInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalTimeWedInput.focus();
      return;
    }

    if(isEmpty (hospitalTimeThuInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalTimeThuInput.focus();
      return;
    }

    if(isEmpty (hospitalTimeFriInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalTimeFriInput.focus();
      return;
    }

    if(isEmpty (hospitalTimeSatInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalTimeSatInput.focus();
      return;
    }

    if(isEmpty (hospitalTimeSunInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalTimeSunInput.focus();
      return;
    }

    if(isEmpty (hospitalTimeHolInput.value) === true){
      alert("병원 ID를 입력 하세요.");
      hospitalTimeHolInput.focus();
      return;
    }

    if(confirm("병원을 등록 하시겠습니까?") === false) return;

    $.ajax({
      type: "POST",
      url: "/ehr/hospital/doSave.do",
      async: true,
      dataType: "html",
      data: {
          "hospital_id"        : hospitalIdInput.value,
          "hospital_name"      : hospitalNameInput.value,
          "hospital_addr"      : hospitalAddrInput.value,
          "hospital_div"       : hospitalDivInput.value,
          "hospital_etc"       : hospitalEtcInput.value,
          "hospital_mapimg"    : hospitalMapImgInput.value,
          "hospital_tel"       : hospitalTelInput.value,
          "hospital_lon"       : hospitalLonInput.value,
          "hospital_lat"       : hospitalLatInput.value,
          "hospital_time_mon"  : hospitalTimeMonInput.value,
          "hospital_time_tue"  : hospitalTimeTueInput.value,
          "hospital_time_wed"  : hospitalTimeWedInput.value,
          "hospital_time_thu"  : hospitalTimeThuInput.value,
          "hospital_time_fri"  : hospitalTimeFriInput.value,
          "hospital_time_sat"  : hospitalTimeSatInput.value,
          "hospital_time_sun"  : hospitalTimeSunInput.value,
          "hospital_time_hol"  : hospitalTimeHolInput.value
      },
      success: function(response) {
          console.log("success response: " + response);
          const message = JSON.parse(response);

          if(1 == message.messageId){ //등록 성공
              alert(message.message);
              //목록으로 화면 이동.
              window.location.href = "/ehr/admin/adminRetrieveHospital.do"; 
          } else {
            alert(message.message);
          }
      },
      error: function(response) {
          console.log("error:" + response);
      }
    });
  })
});