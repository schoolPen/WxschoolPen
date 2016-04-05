<%
String path1 = request.getContextPath();
request.setAttribute("path1",path1);
String root1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1;
request.setAttribute("root1",root1);
%>
<script type="text/javascript">
  var contextPath='<%=root1%>';
</script>

