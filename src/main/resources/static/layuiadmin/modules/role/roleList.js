layui.define([ 'form', 'table', 'layer', 'laydate','comExt' ], function(
		exports) {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        comExt = layui.comExt,
        table = layui.table;

    var searchCondition="";
     function aad() {
    	return searchCondition;
	}
    //用户列表
    var tableIns = table.render({
        elem: '#roleList',
        url : '/role/listRoleInfo',
        cellMinWidth : 95,
        page : true,
        limits : [10,15,20,25],
        contentType:"application/json;charset=utf-8",
        id : "roleListTable",
        method : 'post',
        where: aad(),
        request: {
        	  pageName: 'page' //页码的参数名称，默认：page
        	  ,limitName: 'rows' //每页数据量的参数名，默认：limit
        	},
        response: {
        	   statusName: 'success' //数据状态的字段名称，默认：code
        	  ,statusCode: true //成功的状态码，默认：0
        	  ,msgName: 'msg' //状态信息的字段名称，默认：msg
        	  ,countName: 'obj/total' //数据总数的字段名称，默认：count
        	  ,dataName: 'obj/rows' //数据列表的字段名称，默认：data
        	} ,
        cols : [[
            {type: "radio", fixed:"left", width:50},
            {field: 'roleCode', title: '角色编码', minWidth:150, align:"center"},
            {field: 'roleName', title: '角色名称', minWidth:150, align:'center'},
            {field: 'description', title: '角色描述', minWidth:150, align:"center"},
            {field: 'status', title: '状态',  minWidth:150, align:'center',templet:function(d){
                return d.status == "0" ? "有效" : "无效";
            }
            },
            {title: '操作', width:125, templet:'#roleListBar',fixed:"right",align:"center"}
        ]]
    });
    
  
    //添加角色
    function addRole(edit){
    	
        var index = layui.layer.open({
            title : "添加角色",
            type : 2,
            anim: 3,
            content : "roleAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	
                   body.find(".roleCode").val(edit.roleCode);
                   body.find(".roleName").val(edit.roleName);
                   body.find(".status").val(edit.status);
                }
            }
        })
        comExt.full(index);
    }
    
    //添加服务资源
    function addServer(edit){
    	
        var index = layui.layer.open({
            title : "服务资源",
            type : 2,
            anim: 3,
            content : "roleServer.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	
                   body.find("#roleCode").val(edit.roleCode);
                   body.find("#roleName").val(edit.roleName);
                   form.render();
                }
            }
        })
        comExt.full(index);
    }
    
    //添加页面资源
    function addPage(edit){
    	
        var index = layui.layer.open({
            title : "页面资源",
            type : 2,
            anim: 3,
            content : "pageResource.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	
                    body.find(".roleCode").val(edit.roleCode);
                    body.find(".roleName").val(edit.roleName);
                  
                    form.render();
                }
            }
        })
        comExt.full(index);
    }
    
    
    
    $(".servet_btn").click(function(){
    	 var checkStatus = table.checkStatus('roleListTable'),
      data = checkStatus.data,
      newsId = [];
     if(data.length ==1) {
    	 addServer(data[0]);
     }else{
         layer.msg("请选择一个需要操作的角色",{time:5*1000});
     }     
    })
    
    
    $(".page_btn").click(function(){
   	 var checkStatus = table.checkStatus('roleListTable'),
      data = checkStatus.data,
      newsId = [];
      if(data.length ==1) {
    	addPage(data[0]);
      }else{
        layer.msg("请选择一个需要操作的角色",{time:5*1000});
     }     
    	
    })
    
     $(".addNews_btn").click(function(){
    	 addRole();
    })
    
    
    
    //修改角色
    //
    function updateRole(edit){
    	
        var index = layui.layer.open({
            title : "修改角色",
            type : 2,
            content : "roleUpdate.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	layui.comExt.fillInput(body,edit);
                }
            }
        })
        comExt.full(index);
    }
    
    
    $(".roleSearch").click(function(){
    	 var roleCode=$("#roleCode").val();
    	 var roleName=$("#roleName").val();
    	 search ={roleCode:roleCode,roleName:roleName};
    	 var searchCondition={searchCondition:search};
//         console.log(searchCondition);
    	 tableIns.reload({
    		  where: 
    			  searchCondition		  
    		  ,page: {
    		    curr: 1 //重新从第 1 页开始
    		  }
    		});
    })
    
    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的角色？', {icon: 3, title: '提示信息'}, function (index) {
                tableIns.reload();
                layer.close(index);
            })
        }else{
            layer.msg("请选择需要删除的角色",{time:5*1000});
        }
    })
    //删除角色
    function  deleteRole(data){
    	var roleCodesss = [];
    	roleCodesss.push(data.roleCode);
    	
    	var aReqData = {
				roleCodes :data.roleCode
			};
    	$.ajax({
            type: "post",
            url: "/role/removeRoleInfo",
            data: JSON.stringify(aReqData),
            dataType: "json",
            headers: {
            'Content-Type': 'application/json;charset=utf-8'
            },
            success: function(o){
            	if(o.success==true){
            		tableIns.reload();
                	layer.msg("删除操作成功");
                	
            	}else{
            		layer.msg(o.msg,{time:5*1000});
            	}
            	 
             },
        });
    }

    //列表操作
    table.on('tool(roleList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	updateRole(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此用户？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                _this.text(btnText);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
        	
        	layer.confirm('确定删除此角色？', {icon: 3, title:'提示'}, function(index){
      		  //do something
        		deleteRole(data);
      		});
        }
    });
    
    exports('role/roleList', {});
});
