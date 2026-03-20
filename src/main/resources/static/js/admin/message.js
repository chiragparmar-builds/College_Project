let currentPage = 0;
let keyword = "";

/* LOAD MESSAGES */
let filterRead = "";

function loadMessages(page = 0, key = "") {

    currentPage = page;
    keyword = key;
    filterRead = document.getElementById("filterRead").value;

    fetch(`/admin/messages-data?page=${page}&keyword=${key}&isRead=${filterRead}`)
        .then(res => res.json())
        .then(data => {

            let rows = "";

            data.messages.forEach(m => {

                rows += `
                <tr class="${!m.read ? 'unread' : ''}">
                    <td>${m.cid}</td>
                    <td>${m.name}</td>
                    <td>${m.email}</td>
                    <td>${m.message.substring(0, 30)}...</td>
                    <td>
                        <button onclick="viewMessage(${m.cid}, '${m.message}')">👁</button>
                        <button onclick="deleteMessage(${m.cid})">🗑</button>
                    </td>
                </tr>`;
            });

            document.getElementById("messageTableBody").innerHTML = rows;
            buildPagination(data.totalPages);
        });
}

/* PAGINATION */
function buildPagination(totalPages) {

    let html = "";

    for (let i = 0; i < totalPages; i++) {
        html += `
        <a onclick="loadMessages(${i}, '${keyword}')"
           class="${i === currentPage ? 'active' : ''}">
           ${i + 1}
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
            loadMessages(0, this.value);
        }, 400);
    });

    loadMessages();
});

/* VIEW MESSAGE */
function viewMessage(id, message) {

    fetch("/admin/read-message", {
        method: "POST",
        headers: {"Content-Type":"application/x-www-form-urlencoded"},
        body: "id=" + id
    });

    Swal.fire({
        title: "📩 Message",
        text: message,
        background: "#020617",
        color: "#fff"
    });

    loadMessages(currentPage, keyword);
}
/* DELETE */
function deleteMessage(id) {

    Swal.fire({
        title: "Delete message?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#c19a6b"
    }).then(res => {

        if (res.isConfirmed) {

            fetch("/admin/delete-message", {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: "id=" + id
            })
            .then(() => {
                toast("Deleted");
                loadMessages(currentPage, keyword);
            });
        }
    });
}

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

function loadUnreadCount() {

    fetch("/admin/unread-count")
        .then(res => res.text())
        .then(count => {
            document.getElementById("unreadBadge").innerText = count;
        });
}

document.getElementById("filterRead").addEventListener("change", () => {
    loadMessages(0, keyword);
});