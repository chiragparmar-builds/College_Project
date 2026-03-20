let currentPage = 0;
let keyword = "";

/* ================= LOAD USERS ================= */
function loadUsers(page = 0, key = "") {

    currentPage = page;
    keyword = key;

    const table = document.getElementById("userTableBody");

    table.innerHTML = `
    <tr><td colspan="5">
        <div class="skeleton" style="width:100%;height:30px;"></div>
    </td></tr>`;

    fetch(`/admin/users-data?page=${page}&keyword=${key}`, {
        cache: "no-store"
    })
    .then(res => {
        if (!res.ok) throw new Error("Server error");
        return res.json();
    })
    .then(data => {

        console.log("Users received:", data.users.length); // DEBUG

        let rows = "";

        if (!data.users || data.users.length === 0) {
            rows = `<tr><td colspan="5">No users found</td></tr>`;
        } else {

            data.users.forEach(u => {
                rows += `
                <tr>
                    <td>${u.id}</td>
                    <td>
                        <div style="display:flex;align-items:center;gap:10px;">
                            <div style="
                                width:35px;
                                height:35px;
                                border-radius:50%;
                                background:#c19a6b;
                                display:flex;
                                align-items:center;
                                justify-content:center;
                                color:black;
                                font-weight:bold;">
                                ${u.name.charAt(0)}
                            </div>
                            ${u.name}
                        </div>
                    </td>
                    <td>${u.email}</td>
                    <td>${u.role === 'ROLE_ADMIN' ? 'ADMIN' : 'USER'}</td>
                    <td>
                        <button onclick="viewUser(${u.id})">👁</button>
                        <button onclick="editUser(${u.id})">✏️</button>
                        <button onclick="deleteUser(${u.id})">🗑</button>
                    </td>
                </tr>`;
            });
        }

        table.innerHTML = rows;

        buildPagination(data.totalPages);
    })
    .catch(err => {
        console.error(err);
        table.innerHTML = `<tr><td colspan="5">Error loading users</td></tr>`;
    });
}

/* ================= PAGINATION ================= */
function buildPagination(totalPages) {

    let html = "";

    if (currentPage > 0) {
        html += `<a onclick="loadUsers(${currentPage-1}, '${keyword}')">Prev</a>`;
    }

    for (let i = 0; i < totalPages; i++) {
        html += `
        <a onclick="loadUsers(${i}, '${keyword}')"
           class="${i === currentPage ? 'active' : ''}">
           ${i + 1}
        </a>`;
    }

    if (currentPage < totalPages - 1) {
        html += `<a onclick="loadUsers(${currentPage+1}, '${keyword}')">Next</a>`;
    }

    document.getElementById("pagination").innerHTML = html;
}

/* ================= SEARCH ================= */
document.addEventListener("DOMContentLoaded", () => {

    const search = document.getElementById("searchInput");

    search.addEventListener("keyup", function () {

        clearTimeout(this.delay);

        this.delay = setTimeout(() => {
            loadUsers(0, this.value);
        }, 400);

    });

    loadUsers(0, ""); // INITIAL LOAD
});

/* ================= DELETE ================= */
function deleteUser(id) {

    Swal.fire({
        title: "Delete user?",
        text: "This cannot be undone",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#c19a6b"
    }).then(result => {

        if (result.isConfirmed) {

            fetch("/admin/delete-user", {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: "id=" + id
            })
            .then(() => {
                toast("User deleted");
                loadUsers(currentPage, keyword);
            });
        }

    });
}

/* ================= VIEW ================= */
function viewUser(id) {

    fetch("/admin/get-user?id=" + id)
        .then(res => res.json())
        .then(u => {

            Swal.fire({
                title: "👤 User Details",
                html: `
                    <p><b>${u.name}</b></p>
                    <p>${u.email}</p>
                    <p>${u.role}</p>
                `,
                background: "#1e293b",
                color: "#fff"
            });

        });
}

/* ================= EDIT ================= */
function editUser(id) {

    fetch("/admin/get-user?id=" + id)
        .then(res => res.json())
        .then(u => {

            Swal.fire({
                title: "Edit User",
                html: `
                    <input id="name" class="swal2-input" value="${u.name}">
                    <input id="email" class="swal2-input" value="${u.email}">
                `,
                showCancelButton: true,
                confirmButtonColor: "#c19a6b"
            }).then(result => {

                if (result.isConfirmed) {

                    let name = document.getElementById("name").value;
                    let email = document.getElementById("email").value;

                    fetch("/admin/update-user", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        },
                        body: `id=${id}&name=${name}&email=${email}&role=${u.role}`
                    })
                    .then(() => {
                        toast("User updated");
                        loadUsers(currentPage, keyword);
                    });

                }

            });

        });
}

/* ================= TOAST ================= */
function toast(msg) {

    Swal.fire({
        toast: true,
        position: "top-end",
        icon: "success",
        title: msg,
        showConfirmButton: false,
        timer: 2000
    });
}