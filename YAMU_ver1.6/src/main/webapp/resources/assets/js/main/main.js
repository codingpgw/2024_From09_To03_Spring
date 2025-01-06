document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const prevButton = document.querySelector(".fa-angle-left");
  const nextButton = document.querySelector(".fa-angle-right");
  const slideInputs = document.querySelectorAll("input[name=\"slide\"]");

  const listBox = document.getElementById("listbox");
  const button = document.querySelector(".pl");

  let currentIndex = 0;

  // Update checked radio button
  const updateSlide = (index) => {
    slideInputs[index].checked = true;
  };

  // Handle previous button click
  prevButton.addEventListener("click", () => {
    currentIndex = (currentIndex - 1 + slideInputs.length) % slideInputs.length;
    updateSlide(currentIndex);
  });

  // Handle next button click
  nextButton.addEventListener("click", () => {
    currentIndex = (currentIndex + 1) % slideInputs.length;
    updateSlide(currentIndex);
  });

  document.querySelectorAll(".listbox .list").forEach((item) => {
    item.addEventListener("click", (e) => {
      button.textContent = e.target.textContent;
      document.getElementById("listbox").style.display = "none";
      button.style.background = "url('/ehr/resources/assets/images/angle-down-solid.svg') 93% no-repeat";
      button.style.backgroundSize = "25px 25px";
    });
  });

  document.addEventListener("click", function (e) {

    if (listBox.style.display === "block" && !listBox.contains(e.target) && !button.contains(e.target)) {
      listBox.style.display = "none";
      button.style.background = "url('/ehr/resources/assets/images/angle-down-solid.svg') 93% no-repeat";
      button.style.backgroundSize = "25px 25px";
    }
  });
});

function select() {
  const listBox = document.getElementById("listbox");
  const button = document.querySelector(".pl");

  if (listBox.style.display === "block") {
    listBox.style.display = "none";
    button.style.background = "url('/ehr/resources/assets/images/angle-down-solid.svg') 93% no-repeat"; // 화살표 방향 위로
    button.style.backgroundSize = "25px 25px";
  } else {
    listBox.style.display = "block";
    button.style.background = "url('/ehr/resources/assets/images/angle-up-solid.svg') 93% no-repeat"; // 화살표 방향 아래로
    button.style.backgroundSize = "25px 25px";
  }
}