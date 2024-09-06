<%@page import="com.unisa.dao.ManagerOrdiniDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unisa.model.OrdineBean" %>
<%@ page import="java.text.DecimalFormat" %>

<%
    @SuppressWarnings("unchecked")
    List<OrdineBean> ordiniList = (List<OrdineBean>) request.getAttribute("ordini");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista degli Ordini</title>
</head>
<body>
    <h2>Lista degli Ordini</h2>
	
    <% if (ordiniList != null && !ordiniList.isEmpty()) { 
    	DecimalFormat df = new DecimalFormat("#.00");
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID Ordine</th>
                    <th>Data acquisto</th>
                    <th>Stato</th>
                    <th>Codice fattura</th>
                    <th>Totale (€)</th>
                </tr>
            </thead>
            <tbody>
                <% for (OrdineBean ordine : ordiniList) { %>
                    <tr>
                        <td><%= ordine.getIdOrdine() %></td>
                        <td><%= ordine.getDataAcquisto() %></td>
                        <td><%= ordine.getStato() %></td>
                        <td><%= ordine.getCodFattura() %></td>
                        <!-- Formatta il valore double per visualizzare fino alla seconda cifra decimale -->
                        <td><%= df.format(ManagerOrdiniDao.getPrezzoTotaleOrdine(ordine.getIdOrdine())) %></td>
                         
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>Non ci sono ordini disponibili per questo utente.</p>
    <% } %>

    <form action="ManagerOrdiniServlet" method="post">
    	 <input type="hidden" name="pathOrigin" value="ordini.jsp">
        <input type="hidden" name="action" value="visualizzaOrdiniUtente">
        <button type="submit">Aggiorna Lista Ordini</button>
    </form>
</body>
</html>