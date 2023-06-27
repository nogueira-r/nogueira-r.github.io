document.addEventListener("DOMContentLoaded", function() {
  const tabs = document.querySelectorAll(".tab-link");
  const sections = document.querySelectorAll(".project-section");

  tabs.forEach(function(tab) {
    tab.addEventListener("click", function(event) {
      event.preventDefault();

      // Remove "active" class from all tabs
      tabs.forEach(function(tab) {
        tab.classList.remove("active");
      });

      // Add "active" class to the clicked tab
      tab.classList.add("active");

      // Hide all sections
      sections.forEach(function(section) {
        section.classList.remove("active");
      });

      // Show the corresponding section based on the clicked tab's href
      const targetSectionId = tab.getAttribute("href");
      const targetSection = document.querySelector(targetSectionId);
      targetSection.classList.add("active");
    });
  });
});
