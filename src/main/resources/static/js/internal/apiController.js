/**
 * apiController
 * 
 * @author Atinder
 * @version 1.0
 * @since 2018-10-09
 */
angular.module('apiApp').controller('ApiController', ApiController);

ApiController.$inject = [ '$scope', 'apiService' ];

function ApiController($scope, apiService) {

	var vm = this;
	vm.employees = [];
	vm.message="";
	vm.sucessMessage="";
	vm.feildType=[{value: '0',Name:'Select Type'},
		{value: 'Long',Name:'Long'},
	  {value: 'long',Name:'long'},
	  {value: 'Date',Name:'Date'},
	  {value: 'String',Name:'String'},
	  {value: 'int',Name:'int'},
	  {value: 'Integer',Name:'Integer'},
	  {value: 'BigDecimal',Name:'BigDecimal'},
	  {value: 'boolean',Name:'boolean'},
	  {value: 'float',Name:'float'},
	  {value: 'double',Name:'double'},
	  {value: 'short',Name:'short'},
	  {value: 'char',Name:'char'}
	
	  ];
	
	
	function createFieldObj(){
		return {
			name:"",
			type:"",
			fieldType:vm.feildType[0],
			pk:false
		};
	}
	
	vm.setupData=function(){
		vm.apiObj={
				name:"",
				packageName:"",
				apiPath: "",
				fields:[createFieldObj()]
		};
		
		
	}
	
	
	
	vm.addField=function(){
		vm.apiObj.fields.push(createFieldObj());
	}
	vm.removeField=function(index){
		vm.apiObj.fields.splice(index, 1);     
	}
	
	vm.createApi=function(){
		vm.message="";
		vm.sucessMessage="";
		if(vm.apiObj.name == ""){
			vm.message="Please enter entity name.";
			return;
		}
		
		angular.forEach(vm.apiObj.fields,function(obj){
			if(obj.name == ""){
				vm.message="Please enter field name.";
				return false;
			}
			if(obj.fieldType.value == 0){
				vm.message="Please select field type.";
				return false;
			}
			
			obj.type=obj.fieldType.value;
		})
		if(vm.message != ""){
			return;
		}
		
		return apiService.generateAPI(vm.apiObj).then(
				function(data) {
					vm.sucessMessage=data.parentFolder;
					vm.setupData();

				});
	};

	vm.setupData();

}