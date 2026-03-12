function toggleProfile() {
    var profile = document.getElementById("profile");
    var toggleBtn = document.getElementById("toggleBtn");
    if (profile.style.display === "none") {
        profile.style.display = "block";
        toggleBtn.style.display = "none";
    } else {
        profile.style.display = "none";
        toggleBtn.style.display = "inline";
    }
}

function toggleTheme() {
    var body = document.body;
    body.classList.toggle("dark-theme");
}

function redirectToPage(){
    window.location.href = "/admin/show-messages";
}


function redirectToUserManagment() {
    window.location.href = "/admin/user-list";
}