/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function displayById(id) {
    const btnv = document.getElementById('view');
    const btnu = document.getElementById('update');
    const btnd = document.getElementById('delete');
    const btnc = document.getElementById('create');
    btnv.style.display = "none";
    btnu.style.display = "none";
    btnd.style.display = "none";
    btnc.style.display = "none";
    const btnn = document.getElementById(id);
    if (btnn !== null) {
        btnn.style.display = "block";
    }
}
function eventOff() {
    const btn = document.getElementById('profileUser');
    const display = window.getComputedStyle(btn).display;
    if (display === "block") {
        btn.style.display = "none";
    }

}
function displayProfile() {
    const btn = document.getElementById('profileUser');
    const display = window.getComputedStyle(btn).display;
    if (display === "block") {
        btn.style.display = "none";
    } else {
        btn.style.display = "block";
    }
}



function displayMenu() {
    const btn = document.getElementById('theMenu');
    const display = window.getComputedStyle(btn).display;
    if (display === "block") {
        btn.style.display = "none";
    } else {
        btn.style.display = "block";
    }
}
function checkReport(report, select) {
    if (select === "CREATE") {
        displayById('create');
    }
    if (select === 'UPDATE') {
        displayById('update');
    }
    if (select === 'SUBMIT') {
        displayById('view');
    }
    if (select === 'DELETE') {
        displayById('delete');
    }
    if (report !== "") {
        alert(report);
    }
}
function closePop() {
    const popbt = document.getElementById('popf');
    popbt.style.display = "none";
}
function openPop(studentID) {
    const popbt = document.getElementById('popf');
    document.getElementById("theIdPop").value = studentID;
    popbt.style.display = "flex";
}
function checkDeleteAgree(id) {
    var search = document.getElementById("theIdSel").value;
    var option = document.getElementById('selOption').value;
    if (confirm('Are you sure to delete ' + id)) {
        window.location = "/schedule/studentcontrol?id=" + id + "&search=" + search + "&option=" + option;
    }
}
    