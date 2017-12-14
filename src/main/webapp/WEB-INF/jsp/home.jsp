<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="icon" href="<c:url value="/resources/images/icone.png"/>">
        <title>IA2 - Trabalho G2</title>

        <!-- Bootstrap core CSS -->
        <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">

        <style type="text/css">
            .form-group{
                padding: 10px;
            }

            .mochila {
                width: 400px;
                height: 400px;
            }

            .mochilaIdeal{
                cursor: pointer;
            }
        </style>

    </head>

    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button id="hideForm" type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar" style="display: block;">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<c:url value="/"/>">Knapsack Problem</a>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <spring:url var="urlForm" value="/busca"/>
            <form:form id="form" action="${urlForm}" method="POST" modelAttribute="form">
                <div class="row">
                    <div class="col-md-3">
                        <h4>Geral</h4>
                        <div class="row col-md-12">
                            <label for="populacao">População</label>
                            <form:input type="number" path="populacao" class="form-control" id="populacao" placeholder="População"/>
                        </div>
                        <div class="row col-md-12">
                            <label for="iteracoes">Iterações</label>
                            <form:input type="number" path="iteracoes" class="form-control" id="iteracoes" placeholder="Iterações"/>
                        </div>
                        <div class="row col-md-12">
                            <label for="intGer">Int. de geração</label>
                            <form:input type="number" path="intGer" class="form-control" id="intGer" placeholder="Intervalo de geração"/>
                        </div>
                        <div class="row col-md-12">
                            <label for="txMutacao">Tx. de mutação (%)</label>
                            <form:input type="number" path="txMutacao" class="form-control" id="txMutacao" placeholder="Taxa de mutação (%)"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <h4>Mochila</h4>
                        <div class="row col-md-12">
                            <label for="utilizarVlIdeal">Utilizar Valor ideal?</label>
                            <br /> <form:radiobutton path="utilizaVlIdeal" value="S" onclick="exibeIdeal(true);" />Sim 
                            <br /> <form:radiobutton path="utilizaVlIdeal" value="N" onclick="exibeIdeal(false);" />Não 
                        </div>
                        <div class="row col-md-12 ideal">
                            <label for="vlIdeal">Valor ideal</label>
                            <form:input type="number" path="vlIdeal" class="form-control" id="vlIdeal" placeholder="Valor ideal"/>
                        </div>
                        <div class="row col-md-12 ideal">
                            <label for="txAceitacao">Tx. de aceitação (%)</label>
                            <form:input type="number" path="txAceitacao" class="form-control" id="txAceitacao" placeholder="Taxa de aceitação (%)"/>
                        </div>
                        <div class="row col-md-12">
                            <label for="pesoMaxMochila">Peso máximo</label>
                            <form:input type="number" path="pesoMaxMochila" class="form-control" id="pesoMaxMochila" placeholder="Peso Máximo"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <h4>Objetos</h4>
                        <div class="row col-md-12">
                            <label for="pesoMinObj">Peso mínimo</label>
                            <form:input type="number" path="pesoMinObj" class="form-control" id="pesoMinObj" placeholder="Peso mínimo"/>
                        </div>
                        <div class="row col-md-12">
                            <label for="pesoMaxObj">Peso máximo</label>
                            <form:input type="number" path="pesoMaxObj" class="form-control" id="pesoMaxObj" placeholder="Peso máximo"/>
                        </div>
                        <div class="row col-md-12">
                            <label for="vlMinObj">Valor mínimo</label>
                            <form:input type="number" path="vlMinObj" class="form-control" id="vlMinObj" placeholder="Valor mínimo"/>
                        </div>
                        <div class="row col-md-12">
                            <label for="vlMaxObj">Valor máximo</label>
                            <form:input type="number" path="vlMaxObj" class="form-control" id="vlMaxObj" placeholder="Valor máximo"/>
                        </div>
                    </div>
                </div>
                <br />
                <form:button type="submit" class="btn btn-primary btn-lg btn-block">Calcular</form:button>
            </form:form>
            <div class="row">
                <div class="col-md-12 main">
                    <c:if test="${empty resultado}">
                        <div class="row placeholders">
                            <!--<img src="<c:url value="/resources/images/mochila.png"/>" class="mochila"/>-->
                        </div>
                    </c:if>

                    <c:if test="${not empty resultado}">
                        <h2 class="sub-header">Resultado</h2>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Iteração</th>
                                        <th>Valor</th>
                                        <th>Peso</th>
                                        <th>Fitness</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${resultado}" var="result" varStatus="status">
                                        <tr class="${status.index eq 0 ? 'mochilaIdeal' : ''}">
                                            <td>${result.iteracao}</td>
                                            <td>${result.valor}</td>
                                            <td>${result.peso}</td>
                                            <td>${result.fitness}</td>
                                        </tr>
                                        <c:if test="${status.index eq 0 and not empty result.itens}">
                                            <c:forEach items="${result.itens}" var="item" varStatus="status">
                                                <tr class="itemMochila">
                                                    <td>Item:  <b>${status.index + 1}</b></td>
                                                    <td><b>${item.valor}</b></td>
                                                    <td><b>${item.peso}</b></td>
                                                    <td>*</td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/bootstrap.js"/>"></script>

        <script type="text/javascript">
            $(".mochilaIdeal").on('click', function () {
                var itens = $(".itemMochila");

                itens.each(function (i, it) {
                    var hasHide = $(it).hasClass('hide');

                    if (hasHide) {
                        $(it).removeClass('hide');
                    } else {
                        $(it).addClass('hide');
                    }
                });
            });

            function exibeIdeal(exibirIdeal) {
                if (exibirIdeal) {
                    $(".ideal").show();
                } else {
                    $(".ideal").hide();
                }
            }

            $("#hideForm").on('click', function () {
                if ($("#form").is(':visible')) {
                    $("#form").hide();
                } else {
                    $("#form").show();
                }
            });
        </script>
    </body>
</html>
