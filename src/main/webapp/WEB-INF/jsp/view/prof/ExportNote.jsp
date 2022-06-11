<%@page import="com.gsnotes.web.models.NiveauModel"%>
<%@ page import="com.gsnotes.services.impl.NiveauServiceImpl" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.gsnotes.bo.Niveau" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gsnotes.services.INiveauService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">

            <jsp:include page="../fragments/usermenu.jsp" />

        </div>
    </nav>
    <br>
    <br>
    <div>
        <f:form action="${pageContext.request.contextPath}/prof/exportNotes" method="POST" modelAttribute="niveauModel">
            <div class="form-group">
                <label>choisir un niveau</label>
                <f:select path="idNiveau" class="form-control" >
                    <f:option value="NONE" label="choisir un niveau" />
                    <f:options items="${ListNiveau}" itemValue="idNiveau" itemLabel="titre" />
                </f:select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </f:form>
        <%--@elvariable id="notesDeliberation" type=""--%>
        <%--<f:form action="${pageContext.request.contextPath}/prof/exportNotes" method="POST" modelAttribute="niveauModel" >
            <div class="row">
                <f:select  path="idNiveau" class="form-select" aria-label="Default select example">
                    <f:option value="NONE" label="--- Select ---" />
                    <f:options items="${ListNiveau}" itemValue="idNiveau" itemLabel="titre" />
                </f:select>
            </div>
            <div style="text-align: right">
                <button type="submit" class="btn btn-primary">Exporter</button>
            </div>
        </f:form>--%>
    </div>
</div>
</body>
</html>
