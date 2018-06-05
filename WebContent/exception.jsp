<%@ page import="it.focus.model.UserBean" %>
  <%UserBean user = (UserBean) request.getAttribute("userBean");
      %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <%@include file="meta.jsp" %>
        <%@include file="head.jsp" %>
          <title>Exception - focus.it</title>
    </head>

    <body>
      <%@include file="nav.jsp" %>
        <div class="container boxmargin text-center">
          <h1>Errore - Exception</h1> Qualcosa � andato storto!
          <br />
          <br />
          <% Exception e = (Exception) request.getAttribute("exception");%>
            <%= e.toString()%>
              <br>
              <a href='index.jsp'>Torna alla home</a>
        </div>
        <%@include file="footer.jsp" %>
    </body>

    </html>
