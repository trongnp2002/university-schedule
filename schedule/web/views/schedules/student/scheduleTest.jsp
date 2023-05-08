<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        List<StudentSchedule> studentSchedule = new ArrayList();
        if(request.getAttribute("schedule") != null){
        studentSchedule = (List<StudentSchedule>)request.getAttribute("schedule");
            }
        %>

        <table border="1">
            <thead>
                <tr>
                    <th>Slot</th>
                    <th>Monday </th>
                    <th>Tuesday </th>
                    <th>Wednesday </th>
                    <th>Thursday </th>
                    <th>Friday </th>
                    <th>Saturday </th>
                    <th>Sunday </th>
                </tr>
            </thead>
            <tbody>
                <%
                     DayOfWeek dow[] = DayOfWeek.values();
                    for(int i =1; i <= 4; i++){ %>
                <tr> 
                    <td>Slot <%=i%> </td>
                    <%   for(int j = 0; j < dow.length ; j++){
                      Boolean haveCourse = false;          
                    for(int k = 0; k < studentSchedule.size(); k++){
                    %>
                    <%
                    if(studentSchedule.get(k).getSlotId() == i &&  studentSchedule.get(k).getDayOfWeek().equalsIgnoreCase(String.valueOf(dow[j]))){
                    haveCourse = true;
                    %>
                    <td><%=studentSchedule.get(k).getCourseCode()%><BR>
                        <p style="color: red">(<%=studentSchedule.get(k).getAttendanceStatus()%>)</p>
                        </td>
                    <%}}%>
                    <%
                            if(haveCourse == false){ %>
                    <td><div style="width: 120px; background-color: #b7b7b7"></div></td>
                        <%}}%>
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
