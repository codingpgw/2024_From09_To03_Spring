document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doUpdateButton = document.querySelector("#doUpdate");
  const doDeleteButton = document.querySelector("#doDelete");

  const hospitalIdInput = document.querySelector("#hospital_id");
  const hospitalNameInput = document.querySelector("#hospital_name");
  const hospitalAddrInput = document.querySelector("#hospital_addr");
  const hospitalDivInput = document.querySelector("#hospital_div");
  const hospitalEtcInput = document.querySelector("#hospital_etc");
  const hospitalMapImgInput = document.querySelector("#hospital_mapimg");
  const hospitalTelInput = document.querySelector("#hospital_tel");
  const hospitalLonInput = document.querySelector("#hospital_lon");
  const hospitalLatInput = document.querySelector("#hospital_lat");
  const hospitalMonInput = document.querySelector("#hospital_time_mon");
  const hospitalTueInput = document.querySelector("#hospital_time_tue");
  const hospitalWedInput = document.querySelector("#hospital_time_wed");
  const hospitalThuInput = document.querySelector("#hospital_time_thu");
  const hospitalFriInput = document.querySelector("#hospital_time_fri");
  const hospitalSatInput = document.querySelector("#hospital_time_sat");
  const hospitalSunInput = document.querySelector("#hospital_time_sun");
  const hospitalHolInput = document.querySelector("#hospital_time_hol");

  doUpdateButton.addEventListener("click", function (event) {
    event.preventDefault();
    console.log("doUpdateButton click");

    if (isEmpty(hospitalIdInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalIdInput.focus();
      return;
    }

    if (isEmpty(hospitalNameInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalNameInput.focus();
      return;
    }

    if (isEmpty(hospitalAddrInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalAddrInput.focus();
      return;
    }

    if (isEmpty(hospitalDivInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalDivInput.focus();
      return;
    }

    if (isEmpty(hospitalEtcInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalEtcInput.focus();
      return;
    }

    if (isEmpty(hospitalMapImgInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalMapImgInput.focus();
      return;
    }

    if (isEmpty(hospitalTelInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalTelInput.focus();
      return;
    }

    if (isEmpty(hospitalLonInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalLonInput.focus();
      return;
    }

    if (isEmpty(hospitalLatInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalLatInput.focus();
      return;
    }

    if (isEmpty(hospitalMonInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalMonInput.focus();
      return;
    }

    if (isEmpty(hospitalTueInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalTueInput.focus();
      return;
    }

    if (isEmpty(hospitalWedInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalWedInput.focus();
      return;
    }

    if (isEmpty(hospitalThuInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalThuInput.focus();
      return;
    }

    if (isEmpty(hospitalFriInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalFriInput.focus();
      return;
    }

    if (isEmpty(hospitalSatInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalSatInput.focus();
      return;
    }

    if (isEmpty(hospitalSunInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalSunInput.focus();
      return;
    }

    if (isEmpty(hospitalHolInput.value) === true) {
      alert('병원 ID를 입력 하세요.');
      hospitalHolInput.focus();
      return;
    }

    if (confirm('회원 정보를 수정 하시겠습니까?') === false) return;

    $.ajax({
      type: "POST",
      url: "/ehr/hospital/doUpdate.do",
      async: true,
      dataType: "html",
      data: {
        "hospital_id": hospitalHolInput,
        "hospital_name": hospitalSunInput,
        "hospital_addr": hospitalSatInput,
        "hospital_div": hospitalFriInput,
        "hospital_etc": hospitalThuInput,
        "hospital_mapimg": hospitalWedInput,
        "hospital_tel": hospitalTueInput,
        "hospital_lon": hospitalMonInput,
        "hospital_lat": hospitalLatInput,
        "hospital_time_mon": hospitalLonInput,
        "hospital_time_tue": hospitalTelInput,
        "hospital_time_wed": hospitalMapImgInput,
        "hospital_time_thu": hospitalEtcInput,
        "hospital_time_fri": hospitalDivInput,
        "hospital_time_sat": hospitalAddrInput,
        "hospital_time_sun": hospitalNameInput,
        "hospital_time_hol": hospitalIdInput
      },
      success: function (response) {
        console.log("success response:" + response);
        const message = JSON.parse(response);
        if (1 == message.messageId) { // 수정 성공
          alert(message.message);
          //목록으로 화면 이동.
          window.location.href = '/ehr/admin/adminRetrieveHospital.do';
        } else {
          alert(message.message);
        }

      },
      error: function (response) {
        console.log("error:" + response);
      }
    });
  });

  doDeleteButton.addEventListener("click", function (event) {
    event.preventDefault();
    console.log("doDeleteButton click");

    console.log("hospitalIdInput.value: ", hospitalIdInput.value);

    if (confirm("병원을 삭제 하시겠습니까?") === false) return;

    $.ajax({
      type: "POST",
      url: "/ehr/hospital/doDelete.do",
      async: true,
      dataType: "html",
      data: {
        hospital_id: hospitalIdInput.value,
      },
      success: function (response) {
        console.log("success response: " + response);
        const message = JSON.parse(response);

        if (1 == message.messageId) {
          alert(message.message);
          window.location.href = "/ehr/admin/adminRetrieveMember.do"
        } else {
          alert(message.message);
        }
      },
      error: function (response) {
        console.log("error: " + response);
      },
    });
  });
});