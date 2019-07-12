layui.define([ 'form', 'table', 'layer', 'laydate','comExt','manager','treetable'],
		function(exports) {
	    var $ = layui.jquery,
	    	comExt = layui.comExt,
	    	table = layui.table,
	    	treetable = layui.treetable,
	    	add = layui.manager.add, // 新增
	    	update = layui.manager.update, // 修改
	    	deleteRow = layui.manager.deleteRow; // 删除
	
	    var tableData ;
	    
	    var renderTable = function () {
	    	var data = {
 	            id: ''
 			};
	    	$.ajax({
		        url: '/pageResource/queryList',
		        type: 'post',
		        data:JSON.stringify(data),
		        dataType: 'json',
		        headers : {
					'Content-Type' : 'application/json;charset=utf-8'
				},
		        success: function(res){
		        	tableData = res.obj;
		        	createInput(tableData);//在页面创建一个隐藏的input标签
		            initTable(res.obj);
		        }
		      });
	    	}
	    renderTable();
	    
	    function createInput(tableData){
	    	var count = 0;
       	 	for(var i=0; i<tableData.length; i++){
                var jsonObj = tableData[i];
                if (jsonObj.superiorId == "") {
					count++;
				}
            }
       	 	var muiDiv = $(".form-container")[0]; 
       	 	var input = document.createElement("input");
       	　　	input.setAttribute("type","hidden") ;
       	　　	input.setAttribute("id","superiorIdCount") ;
       	　　	input.setAttribute("value", count) ;
       	　　	muiDiv.appendChild(input);
	    }
	    
	    var initTable = function (data){
	    	treetable.render({
	    		data:data,
		    	treeColIndex: 1,//树形图标显示在第几列
	            treeSpid: '',//最上级的上级菜单id
	            treeIdName: 'id',//id字段的名称
	            treePidName: 'superiorId',//pid字段的名称
	            treeDefaultClose: true,//是否默认折叠
	            treeLinkage: true,//上级菜单展开时是否自动展开所有子级
	            elem: '#test-table-resetPage',
	            url: '/pageResource/queryList',
	            page: false,
		        cols: [[
		            {type: "radio", width:50},
				    {field: 'id', title: '主键编号', minWidth:200, align:"left"},
				    {field: 'resourceName', title: '图标', minWidth:250, align:"center"},
				    {field: 'displayName', title: '描述', minWidth:150, align:"center"},
				    {field: 'functionUrl', title: '地址', minWidth:150, align:"center"},
				    {field:	'status',title:'状态', minWidth:150, align:"center",templet:function(d){return d.status == "1" ? "启用" : "停用" ;}},
				    {title:"操作",align:'center', width:100, toolbar:"#test-table-bar"}
		        ]]
		    });
	    }


	    $(".treeTable-icon").click(function(){
	    	alert("ssss");
	    	initTable(tableData);
	    });
	    	
	    // 新增页面资源
	    $(".add-user").click(function() {
           add('资源新增','resourceAdd.html','');
         });
	    
	    // 页面资源修改（加上属性名对应的id及可实现数据返现）
		$('.update-resource').on('click', function(){
			update('资源修改','resourceUpdate.html','','test-table-resetPage');
		});

	    // 监听table内工具条
	    table.on('tool(test-table-resetPage)', function(obj) {
	    	 layer.confirm('确定删除此页面资源吗？', {icon: 3, title:'提示'}, function(index){
	    		 deletePageResource(obj.data);
	    	 });
	    });
	    
	    function deletePageResource(data){
  			comExt.ajax({
  	            type: "post",
  	            url: "/pageResource/delete",
  	            data: JSON.stringify(data),
  	            dataType: "json",
  	            headers: {
  	            'Content-Type': 'application/json;charset=utf-8'
  	            },
  	            success: function(o){
  	            	if(o.success==true){
  	            		layer.msg("操作成功");
  	            		renderTable();
  	            	}else {
  	            		layer.closeAll();
  	            	}
  	            }
  	        });
  		}
	    
	    
	    exports('resource/resourceList', {});
	    
	  });