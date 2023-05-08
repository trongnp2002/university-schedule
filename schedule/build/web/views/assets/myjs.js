/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function displayByIdStu(id) {
    const btnv = document.getElementById('viewStu');
    const btnu = document.getElementById('updateStu');
    const btnd = document.getElementById('deleteStu');
    const btnc = document.getElementById('createStu');
    btnv.style.display = "none";
    btnu.style.display = "none";
    btnd.style.display = "none";
    btnc.style.display = "none";
    const btnn = document.getElementById(id);
    if (btnn !== null) {
        btnn.style.display = "block";
    }
}

function displayByIdAcc(id) {
    const btnv = document.getElementById('viewAcc');
    const btnd = document.getElementById('deleteAcc');
    const btnc = document.getElementById('createAcc');
    btnv.style.display = "none";
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
function checkReportA(report, select) {
    if (select === "CREATE") {
        displayByIdAcc('createAcc');
    }
    if (select === 'SELECT') {
        displayByIdAcc('viewAcc');
    }
    if (select === 'DELETE') {
        displayByIdAcc('deleteAcc');
    }
    if (report !== "") {
        alert(report);
    }
}
function checkReportS(report, select) {
    if (select === "CREATE") {
        displayByIdStu('createStu');
    }
    if (select === 'UPDATE') {
        displayByIdStu('updateStu');
    }
    if (select === 'SELECT') {
        displayByIdStu('viewStu');
    }
    if (select === 'DELETE') {
        displayByIdStu('deleteStu');
    }
    if (report !== "") {
        alert(report);
    }
}

function displayByIdDC(id) {
    const btnd = document.getElementById('department');
    const btnc = document.getElementById('course');
    const btnm = document.getElementById('major');
    btnd.style.display = "none";
    btnc.style.display = "none";
    btnm.style.display = "none";

    const btnn = document.getElementById(id);
    if (btnn !== null) {
        btnn.style.display = "block";
    }
}


function closePop(id) {
    const popbt = document.getElementById(id);
    popbt.style.display = "none";
}

function openPop(id) {
    const popbt = document.getElementById('popf');
    document.getElementById("theIdPop").value = id;
    popbt.style.display = "flex";
}


function openPopA(id) {
    const popbt = document.getElementById(id);
    popbt.style.display = "flex";
}
function checkDeleteAgree(id, service) {
    if (confirm('Do you want to delete: ' + id)) {
        if (service === 'student') {
            window.location = "/schedule/admin_student/sdelete?id=" + id;
        }
        if (service === 'professor') {
            window.location = "/schedule/admin_professor/sdelete?id=" + id;
        }
        if (service === 'account') {
            window.location = "/schedule/admin_account/sdelete?id=" + id;
        }
        if (service === 'department') {
            window.location = "/schedule/admin_department/sdelete?id=" + id;
        }
        if (service === 'building') {
            window.location = "/schedule/admin_architecture/buildingDelete?id=" + id;
        }
        if (service === 'room') {
            window.location = "/schedule/admin_architecture/roomDelete?id=" + id;
        }
        if (service === 'course') {
            window.location = "/schedule/admin_course/sdelete?id=" + id;
        }
        if (service === 'major') {
            window.location = "/schedule/admin_major/sdelete?id=" + id;
        }
        if (service === 'classes') {
            window.location = "/schedule/admin_class/sdelete?id=" + id;
        }


    }


}
function displayInputId() {
    var userRoll = document.getElementById("roll").value;
    if (userRoll === "1") {
        document.getElementById("stuIdInput").style.display = "table-row";
        document.getElementById("proIdInput").style.display = "none";

    }
    if (userRoll === "2") {
        document.getElementById("proIdInput").style.display = "table-row";
        document.getElementById("stuIdInput").style.display = "none";


    }
}

function checkRepass(passid, repassid, textReport, rowReport) {
    var pass = document.getElementById(passid).value;
    var re_pass = document.getElementById(repassid).value;
    var report = document.getElementById(rowReport);
    if (pass !== re_pass) {
        report.style.display = "table-row";
        document.getElementById(textReport).innerHTML = "Your re-password not correct";
    } else {
        report.style.display = "none";
        document.getElementById(textReport).innerHTML = "";
    }
}

function checkReportD(report, select) {
    if (select === 'DEPARTMENT') {
        displayByIdDC('department');
    }
    if (select === 'COURSE') {
        displayByIdDC('course');
    }
    if (select === 'BUILDING') {
        displayByIdArchi('building');
    }
    if (select === 'ROOM') {
        displayByIdArchi('room');
    }
    if (select === 'MAJOR') {
        displayByIdDC('major');
    }
    if (report !== "") {
        alert(report);
    }
}

function checkReportClass(report, isOpenPop) {
    if (report !== "") {
        alert(report);
    }
    if (isOpenPop === "true") {
        openPopA('popa');
    }
}
function displayByIdArchi(id) {
    const btnv = document.getElementById('building');
    const btnd = document.getElementById('room');
    btnv.style.display = "none";
    btnd.style.display = "none";
    const btnn = document.getElementById(id);
    if (btnn !== null) {
        btnn.style.display = "block";
    }
}
function openPopF() {
    const popbt = document.getElementById('popf');
    popbt.style.display = "flex";
}

