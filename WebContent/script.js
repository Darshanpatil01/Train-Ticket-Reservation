
// Simple form validation (optional enhancement)
document.addEventListener("DOMContentLoaded", function () {

    const forms = document.querySelectorAll("form");

    forms.forEach(form => {
        form.addEventListener("submit", function (e) {

            const inputs = form.querySelectorAll("input[required]");
            let valid = true;

            inputs.forEach(input => {
                if (input.value.trim() === "") {
                    valid = false;
                    input.style.border = "1px solid red";
                } else {
                    input.style.border = "1px solid #ccc";
                }
            });

            if (!valid) {
                e.preventDefault();
                alert("Please fill all required fields!");
            }
        });
    });

});


// Confirm before logout
const logoutLinks = document.querySelectorAll(".logout");

logoutLinks.forEach(link => {
    link.addEventListener("click", function (e) {
        const confirmLogout = confirm("Are you sure you want to logout?");
        if (!confirmLogout) {
            e.preventDefault();
        }
    });
});


// Smooth hover effect for cards
const cards = document.querySelectorAll(".card");

cards.forEach(card => {
    card.addEventListener("mouseenter", () => {
        card.style.transform = "scale(1.03)";
    });

    card.addEventListener("mouseleave", () => {
        card.style.transform = "scale(1)";
    });
});