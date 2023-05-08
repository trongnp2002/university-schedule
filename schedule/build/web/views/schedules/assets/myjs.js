function chuyenVeHome(role){
    var url = "/schedule/student/scheduleStudent";
    if(role === "2"){
        url = "/schedule/professor/professorSchedule";
    }
    window.location = url;
}