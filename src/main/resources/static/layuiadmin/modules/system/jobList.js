layui.define([ 'form', 'table', 'layer', 'laydate','comExt' ], function(
		exports) {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laydate = layui.laydate;
        $ = layui.jquery,
        comExt = layui.comExt,
        table = layui.table;

    var searchCondition="";
     function aad() {
    	return searchCondition;
	};
     //开始时间
     laydate.render({
    	elem: '#beginTime'
    });
     //结束时间
     laydate.render({
   	  elem: '#endTime'
   });
   /*  laydate.render({
    	 elem: '#endTime'
     });*/
    //商标列表
    var tableIns = table.render({
        elem: '#operateList',
        url : '/system/job/queryList',
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
           {type: "checkbox", fixed:"left", width:50},
//          {type: "radio", fixed:"left", width:50},
          {field: 'id', title: '编号', width:110, align:"center"},
          {field: 'beanName', title: 'bean名称', width:150, align:'center'},
          {field: 'methodName', title: '方法名称', width:150, align:"center"},
          {field: 'parameter', title: '参数', width:150, align:"center"},
          {field: 'cron', title: 'cron表达式', width:150, align:"center"},
          {field: 'description', title: '备注', width:150, align:"center"},
          {field: 'status', title: '状态', width:131, align:"center",templet:function(d){
        	  if(d.status=="0"){
                  return '<button class="layui-btn layui-btn-xs layui-bg-red">暂停</button>';
        	  }else{
                  return '<button class="layui-btn layui-btn-xs layui-bg-blue">正常</button>';
        	  }
          }},
          {title: '操作', minWidth:170,fixed:"right",align:"center",templet:function(d){
        	  if(d.status=="0"){
        		  return '<button class="layui-btn layui-btn-xs layui-badge-rim" lay-event="updateJob">修改</button>'+
                  '<button class="layui-btn layui-btn-xs layui-bg-blue" lay-event="start">启动</button>';
        	  }else{
        		  return '<button class="layui-btn layui-btn-xs layui-bg-red"  lay-event="stop">停止</button>';
        	  }
          }}
        ]]
    });
    
  //重新加载查询结果
    $(".queryCommit").click(function(){
   	 var beanName=$("#beanName").val();
   	 search ={beanName:beanName};
   	 var searchCondition={searchCondition:search};
//     console.log(searchCondition);
   	 tableIns.reload({
   		  where: 
   			  searchCondition		  
   		  ,page: {
   		    curr: 1 //重新从第 1 页开始
   		  }
   		});
   })

   //点击新增按钮
    $(".addJobs_btn").click(function(){
    	 addJob();
    })
    
    //新增定时任务
    function addJob(edit){
    	
        var index = layui.layer.open({
            title : "新增定时任务",
            type : 2,
            anim: 3,
            content : "addJob.html",
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
         layer.msg("请选择一个需要操作的商标",{time:5*1000});
     }     
    })
    
    
    $(".page_btn").click(function(){
   	 var checkStatus = table.checkStatus('roleListTable'),
      data = checkStatus.data,
        newsId = [];
      if(data.length ==1) {
    	addPage(data[0]);
      }else{
        layer.msg("请选择一个需要操作的商标",{time:5*1000});
     }     
    	
    })
    
    
    
    
    //修改定时任务
    function updateJob(data){
		if(!data){
			var checkStatus = table.checkStatus('operateList');
			console.log(checkStatus.data);
			if(checkStatus.data.length >1) {
		         layer.msg("只能修改一条数据");
		         return;
		         }
			data = checkStatus.data[0];
		}
		if(data == null ){
			layer.msg("请选择需要修改的数据");
			return false;
		}
		var index = layui.layer.open({
            title : "定时任务修改",
            type : 2,
            anim: 3,
            content : "/views/system/job/updateJob.html?"+data.id,
            success : function(layero, index){
                var body = layer.getChildFrame('body', index);
                comExt.fillInput(body,data,form);
                setTimeout(function(){
                    layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        comExt.full(index);
		layui.sessionData("index",index);
	}

    
    //打印收据
    function printShouju(data){
    	if(!data){
    		var checkStatus = table.checkStatus('operateList');
    		console.log(checkStatus.data);
    		if(checkStatus.data.length >1) {
    			layer.msg("只能打印一条收据数据");
    			return;
    		}
    		data = checkStatus.data[0];
    	}
    	if(data == null ){
    		layer.msg("请选择需要打印的数据");
    		return false;
    	}
    	var index = layui.layer.open({
    		title : "打印收据",
    		type : 2,
    		anim: 3,
    		content : "/views/system/operate/printShouju.html?"+data.applyNo,
    		success : function(layero, index){
    			var body = layer.getChildFrame('body', index);
    			comExt.fillInput(body,data,form);
    			setTimeout(function(){
    				layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
    					tips: 3
    				});
    			},500)
    		}
    	})
    	comExt.full(index);
    	layui.sessionData("index",index);
    }
    
    
    
    //批量删除
    $(".delAll_btn").click(function(){
    	
        var checkStatus = table.checkStatus('roleListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
            	if(data[i].status!="0"){
            		layer.confirm('选中的定时任务中含有已启动的项目，请先停止后删除！！！', {icon: 3, title: '提示信息'});
            		return;
            	}
                newsId.push(data[i].id);
            }
            layer.confirm('确定要删除选中的定时任务吗？', {icon: 3, title: '提示信息'}, function (index) {
            	$.ajax({
                    type: "post",
                    url: "/system/job/updateByapplyNo",
                    data: JSON.stringify(newsId),
                    dataType: "json",
                    headers: {
                    	'Content-Type': 'application/json;charset=utf-8'
                    },
                    success: function(o){
                    	if(o.success==true){
                    		layer.msg("操作成功");
                    		location.reload();
                    	}else{
                    		layer.msg(o.msg,{time:5*1000});
                    		tableIns.reload();
                            layer.close(index);
                    	}
                     },   
                });
                
            })
        }else{
            layer.msg("请选择需要删除的商标受理信息",{time:5*1000});
        }
    })

    //列表操作
    table.on('tool(operateList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'updateJob'){ //打印回执
            updateJob(data);
        }
        if(layEvent === 'printShouju'){ //打印回执
        	printShouju(data);
        }
        
    });
    
    exports('role/roleList', {});
});

