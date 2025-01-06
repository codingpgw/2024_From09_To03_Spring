document.addEventListener("DOMContentLoaded", function () {
  document.querySelectorAll(".qna-toggle").forEach((button) => {
    button.addEventListener("click", function () {
      const qna = this.closest(".qna");
      qna.classList.toggle("active");

      const iconDown = this.querySelector(".fa-chevron-down");
      const iconUp = this.querySelector(".fa-chevron-up");

      iconDown.style.display = qna.classList.contains("active") ? "none" : "block";
      iconUp.style.display = qna.classList.contains("active") ? "block" : "none";
    });
  });
});



function pageDoRetrieve(url, pageNo) {
  console.log("pageDoRetrieve click");

  let memberForm = document.memberForm;
  memberForm.pageNo.value = pageNo;
  memberForm.action = url;

  memberForm.submit();
}

// top qna 고정
//showContent(document.querySelector(".tabs-button"), "tab1");


/*  배너   */


