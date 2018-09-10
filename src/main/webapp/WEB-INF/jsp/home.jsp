<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="/js/angular.min.js"></script>
<script type="text/javascript" src="/js/internal/app.js"></script>
<script type="text/javascript" src="/js/internal/apiController.js"></script>
<script type="text/javascript" src="/js/internal/apiService.js"></script>
<link href="/js/bootstrap-4.1.0-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
</head>
<body ng-app="apiApp" ng-controller="ApiController as ap">
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Create API</a>
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="#">Sign out</a>
        </li>
      </ul>
    </nav>
<div class="alert alert-danger custom-alert" ng-if="ap.message">
 {{ap.message}}
</div>
<div class="alert alert-danger custom-alert" ng-if="ap.sucessMessage">
 <b>Classes has been created</b>  <a href="create/download/{{ap.sucessMessage}}" target="_blank">Download Zip</a>
</div>
<div class="container">
<form>
  <div class="form-group row " style="padding-top: 80px;">
    <label for="staticEmail" class="col-sm-2 col-form-label">Name</label>
    <div class="col-sm-10">
     <input type="text" class="form-control" id="name" ng-model="ap.apiObj.name" placeholder="Name">
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Package Name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputPassword" ng-model="ap.apiObj.packageName" placeholder="Package Name">
    </div>
  </div>
    <div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Api Name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputPassword" ng-model="ap.apiObj.apiPath"  placeholder="Api Name">
    </div>
  </div>
</form>

<div class="card">
  <div class="card-header">
    Fields
  </div>
  <div class="card-body">
		   <div class="form-inline padding-top-10 " ng-repeat="field in ap.apiObj.fields" >
			  <div class="form-group mx-sm-3">
			    <label for="inputPassword2" class="mx-sm-3">Name</label>
			    <input type="text" class="form-control" id="inputPassword2" placeholder="Field Name" ng-model="field.name">
			  </div>
			  <div class="form-group mx-sm-3">
			    <label  class="mx-sm-3">Type</label>
			    <select class="form-control" ng-model="field.fieldType" ng-options="item as  item.Name for item in ap.feildType" >
			    </select>
			    
			  </div>
			    <div class="form-group mx-sm-3">
			    <label ng-if="$first"> Primary Key <input type="checkbox" ng-model="field.pk" class="form-control margin-left-10"></input> </label>
			    </div>
			  <button type="button" ng-if="ap.apiObj.fields.length > 1" class="btn btn-primary margin-right-10" ng-click="ap.removeField($index);">-</button>
			  <button ng-if="$last" type="button" class="btn btn-primary" ng-click="ap.addField();">+</button>
		</div>
  </div>
</div>

 <button type="button" class="btn btn-primary margin-top-10" ng-click="ap.createApi();">Create Api</button>
</div>


</body>
</html>