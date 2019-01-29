<%@ include file = "header2.jsp" %>

<%
Class.forName("com.mysql.jdbc.Driver");
Connection con =
     DriverManager.getConnection("jdbc:mysql://localhost/rooms", "root", "2346");
%>
<% String hName = request.getParameter("hName");
   session.setAttribute("hotlName", hName);
%>

<div class="row">
	
  <div class="col-md-6">
    <div style="border-top: 1px solid #f8f8f8;
  border: 1px solid rgba(0,0,0,0.2); width:500px; height:550px; margin-top:60px; margin-left:30px;>
<form action="AddRoom" method="post">
  <div class="form-group">
    <label for="exampleFormControlInput1">Hotel Name</label>
    <input type="text" class="form-control"  placeholder=" <%= hName%>" value="<%= hName%>" disabled >
  
    <label for="exampleFormControlInput2">Room Number</label>
    <input type="text" class="form-control"  placeholder="Room Number" name="roomNo">
 
  <label for="inputState">Room Size</label>
      <select id="inputState" class="form-control" name="roomSize">
        <option selected>1 Adult</option>
        <option>2 Adults</option>
        <option>3 Adults</option>
      </select>
   
  <label for="inputState">Room Type</label>
      <select id="inputState" class="form-control" name="roomType">
        <option selected>AC</option>
        <option>NON-AC</option>
        <option>COOLER</option>
      </select>
    
  <label for="inputState">TV</label>
      <select id="inputState" class="form-control" name="tv">
        <option selected>YES</option>
        <option>NO</option>
      </select>
   
  <label for="inputState">Complimentary Breakfast</label>
      <select id="inputState" class="form-control" name="breakfast">
        <option selected>YES</option>
        <option>NO</option>
      </select>
   
  <label for="inputState">Free Wifi</label>
      <select id="inputState" class="form-control" name="wifi">
        <option selected>YES</option>
        <option>NO</option>
      </select>
   
    <label for="exampleFormControlInput4">Price</label>
    <input type="text" class="form-control"  placeholder="INR" name="price">
  </div>
  <button type="submit" value="send" class="btn btn-primary btn-lg">Add Room</button>
</form>
</div>
  </div>

<% 

Statement stmt = con.createStatement();
String sql1 = "select * from hotelrooms where hotelName = '"+(String)session.getAttribute("hotlName")+"' and  partnerName='"+(String)session.getAttribute("partnerName")+"'";
ResultSet rslt = stmt.executeQuery(sql1);
while (rslt.next()) {%>

  <div class="col-md-6">
  	<div class="container">
       <div class="row">
        
   <div class="col-md-12"> 
<div class="caption" style="border-top: 1px solid #f8f8f8;
    border: 1px solid rgba(0,0,0,0.2); width:300px;">
    <hr style="border-top: 1px solid #f8f8f8;
    border-bottom: 1px solid rgba(0,0,0,0.2); width:50px;"> 
   
   
     <strong>Hotel Name :   </strong>
    <td> <%=rslt.getString("hotelName") %> </td><br>
     
    <strong>RoomNo :   </strong>
    <td> <%=rslt.getString("roomNo") %>  </td><br>
    <strong>Room Size :   </strong>
    <td> <%=rslt.getString("roomSize") %> </td><br>
    <strong>Type :   </strong>
    <td> <%=rslt.getString("AC") %> </td><br>
    <strong>TV :   </strong>
    <td> <%= rslt.getString("TV") %>  </td><br>
    <strong>Complimentary Breakfast :   </strong>
    <td> <%=rslt.getString("breakfast") %>  </td><br>
    <strong>wifi :  </strong>
    <td> <%=rslt.getString("wifi") %>  </td><br>
    <strong>Price :  </strong>
    <td> <%=rslt.getString("price") %>  </td><br>
</div>
</div>
<%}%>

    </div>
</div>
  </div>
</div>
<%@ include file = "footer.jsp" %>