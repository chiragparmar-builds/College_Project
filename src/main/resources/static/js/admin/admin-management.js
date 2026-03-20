let currentPage = 0;
let keyword = "";

/* LOAD USERS */
function loadAdmins(page = 0, key = "") {

    currentPage = page;
    keyword = key;

    const table = document.getElementById("adminTableBody");

    table.innerHTML = `<tr><td colspan="6">Loading...</td></tr>`;

    fetch(`/admin/users-data?page=${page}&keyword=${key}`)
        .then(res => res.json())
        .then(data => {

            let rows = "";

            data.users.forEach(u => {

                rows += `
                <tr>
                    <td>${u.id}</td>

                    <td>
                        <div style="display:flex;align-items:center;gap:10px;">
                            <div class="avatar">${u.name.charAt(0)}</div>
                            ${u.name}
                        </div>
                    </td>

                    <td>${u.email}</td>

                    <td>
                        <span class="${u.role === 'ROLE_ADMIN' ? 'role-admin' : 'role-user'}">
                            ${u.role === 'ROLE_ADMIN' ? 'ADMIN' : 'USER'}
                        </span>
                    </td>

                    <td>
                        <span class="${u.is_Alive == 1 ? 'status-alive' : 'status-dead'}">
                            ${u.is_Alive == 1 ? '🟢 Alive' : '🔴 Dead'}
                        </span>
                    </td>
                    <td>
                        <button onclick="changeRole(${u.id}, '${u.role}')">🔄 Role</button>
                        <button onclick="viewUser(${u.id})">👁</button>
                        <button onclick="deleteUser(${u.id})">🗑</button>
                    </td>
                </tr>`;
            });

            table.innerHTML = rows;
            buildPagination(data.totalPages);
        });
}

/* CHANGE ROLE */
function changeRole(id, currentRole) {

    let newRole = currentRole === "ROLE_ADMIN" ? "ROLE_USER" : "ROLE_ADMIN";

    Swal.fire({
        title: `Change role to ${newRole.replace("ROLE_", "")}?`,
        icon: "question",
        showCancelButton: true,
        confirmButtonColor: "#c19a6b"
    }).then(res => {

        if (res.isConfirmed) {

            fetch("/admin/change-role", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `id=${id}&role=${newRole}`
            })
            .then(() => {
                toast("Role updated");
                loadAdmins(currentPage, keyword);
            });
        }
    });
}

/* VIEW */
function viewUser(id) {

    fetch("/admin/get-user?id=" + id)
        .then(res => res.json())
        .then(u => {

            Swal.fire({
                title: "User Info",
                html: `
                    <p><b>${u.name}</b></p>
                    <p>${u.email}</p>
                    <p>${u.role}</p>
                `,
                background:"#020617",
                color:"#fff"
            });
        });
}

/* DELETE */
function deleteUser(id) {

    Swal.fire({
        title: "Delete user?",
        icon: "warning",
        showCancelButton: true
    }).then(res => {

        if (res.isConfirmed) {

            fetch("/admin/delete-user", {
                method: "DELETE",
                headers: {"Content-Type":"application/x-www-form-urlencoded"},
                body: "id=" + id
            })
            .then(() => {
                toast("Deleted");
                loadAdmins(currentPage, keyword);
            });
        }
    });
}

/* PAGINATION */
function buildPagination(totalPages) {

    let html = "";

    for (let i = 0; i < totalPages; i++) {
        html += `
        <a onclick="loadAdmins(${i}, '${keyword}')"
           class="${i === currentPage ? 'active' : ''}">
           ${i+1}
        </a>`;
    }

    document.getElementById("pagination").innerHTML = html;
}

/* SEARCH */
document.addEventListener("DOMContentLoaded", () => {

    const search = document.getElementById("searchInput");

    search.addEventListener("keyup", function () {

        clearTimeout(this.delay);

        this.delay = setTimeout(() => {
            loadAdmins(0, this.value);
        }, 400);
    });

    loadAdmins();
});

/* TOAST */
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