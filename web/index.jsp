<%--
  Created by IntelliJ IDEA.
  User: Anass
  Date: 17/11/2019
  Time: 00:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>example Title</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>

    <style>
        table{
            width:100%;
        }
        #example_filter{
            float:right;
        }
        #example_paginate{
            float:right;
        }
        label {
            display: inline-flex;
            margin-bottom: .5rem;
            margin-top: .5rem;

        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <br>
        <br>
        <h2 class="section-heading mb-4">
            Basic Example
        </h2>
        <table id="example" class="table table-striped table-bordered" style="width:100%">
            <thead>
            <tr>
                <th>Coordinates</th>
                <th>Name</th>
                <th>Available</th>
                <th>Free</th>
                <th>Payment card</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${stations}" var="station" >
                <tr>
                    <td><c:out value="${station.lattitude}"/></td>
                    <td><c:out value="${station.name}"/></td>
                    <td><c:out value="${station.available}"/></td>
                    <td><c:out value="${station.free}"/></td>
                    <td><c:out value="${station.cardPaiement}"/></td>
                </tr>
            </c:forEach>

            <tr>
                <td>Garrett Winters</td>
                <td>Accountant</td>
                <td>Tokyo</td>
                <td>63</td>
                <td>2011/07/25</td>
            </tr>
            <tr>
                <td>Ashton Cox</td>
                <td>Junior Technical Author</td>
                <td>San Francisco</td>
                <td>66</td>
                <td>2009/01/12</td>
            </tr>
            <tr>
                <td>Cedric Kelly</td>
                <td>Senior Javascript Developer</td>
                <td>Edinburgh</td>
                <td>22</td>
                <td>2012/03/29</td>
            </tr>
            <tr>
                <td>Airi Satou</td>
                <td>Accountant</td>
                <td>Tokyo</td>
                <td>33</td>
                <td>2008/11/28</td>
            </tr>
            <tr>
                <td>Brielle Williamson</td>
                <td>Integration Specialist</td>
                <td>New York</td>
                <td>61</td>
                <td>2012/12/02</td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <th>Coordinates</th>
                <th>Name</th>
                <th>Available</th>
                <th>Free</th>
                <th>Payment card</th>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
</body>
</html>
