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
     // 开始时间
     laydate.render({
    	elem: '#beginTime'
    });
     // 结束时间
     laydate.render({
   	  elem: '#endTime'
   });
    // 商标列表
    var tableIns = table.render({
        elem: '#operateList',
        url : '/business/apply/listApply',
        cellMinWidth : 95,
        page : true,
        limits : [10,15,20,25],
        contentType:"application/json;charset=utf-8",
        id : "roleListTable",
        method : 'post',
        where: aad(),
        request: {
        	  pageName: 'page' // 页码的参数名称，默认：page
        	  ,limitName: 'rows' // 每页数据量的参数名，默认：limit
        	},
        response: {
        	   statusName: 'success' // 数据状态的字段名称，默认：code
        	  ,statusCode: true // 成功的状态码，默认：0
        	  ,msgName: 'msg' // 状态信息的字段名称，默认：msg
        	  ,countName: 'obj/total' // 数据总数的字段名称，默认：count
        	  ,dataName: 'obj/rows' // 数据列表的字段名称，默认：data
        	} ,
        cols : [[
           {type: "checkbox", fixed:"left", width:50},
          {field: 'applyNo', title: '申请序号', minWidth:150, align:"center"},
          {field: 'acceptDate', title: '受理日期', minWidth:150, align:'center'},
          {field: 'companyName', title: '公司名称', minWidth:200, align:"center"},
          {field: 'trademarkName', title: '商标名称', minWidth:150, align:"center"},
          {field: 'amt', title: '总金额', minWidth:125, align:"center"},
          {field: 'deleteFlag', title: '删除标记', minWidth:100, align:"center",templet:function(d){
        	  if(d.deleteFlag=="1"){
        		  return '已删除';
        	  }else{
        		  return '';
        	  }
          }},
          {title: '打印', minWidth:100,fixed:"right",align:"center",templet:function(d){
        	  if(d.deleteFlag=="1"){
        		  return '<button class="layui-btn layui-btn-xs layui-btn-disabled">回执</button>';
        	  }else{
        		  return '<button class="layui-btn layui-btn-xs"  lay-event="print">回执</button>';
        	  }
          }}
        ]]
    });
    
    // 重新加载查询结果
    $(".queryCommit").click(function(){
   	 var companyName=$("#companyName").val();
   	 var humanName=$("#humanName").val();
   	 var acceptType=$("#acceptType option:checked").val().split(";")[0];
   	 var trademarkName=$("#trademarkName").val();
   	 var beginTime=$("#beginTime").val();
   	 if(beginTime!="" && null!=beginTime){
   		 beginTime = beginTime+" 00:00:00";
   	 }
   	 var endTime=$("#endTime").val();
   	 if(endTime!="" && null!=endTime){
   		endTime = endTime+" 23:59:59";
   	 }
   	 search ={companyName:companyName,humanName:humanName,acceptType:acceptType,beginTime:beginTime,endTime:endTime};
   	 var searchCondition={searchCondition:search};
   	 tableIns.reload({
   		  where: 
   			  searchCondition		  
   		  ,page: {
   		    curr: 1 // 重新从第 1 页开始
   		  }
   		});
   })

   // 点击新增按钮
    $(".addApply_btn").click(function(){
    	addApply();
    })
    
    // 新增商标
    function addApply(edit){
    	
        var index = layui.layer.open({
            title : "新增商标注册",
            type : 2,
            anim: 3,
            content : "addApply.html",
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
    
    // 受理类型列表
    $(function(){
    	$.ajax({
            type: "post",
            url: "/business/apply/selectMenuInfo",
            data: {},
            dataType: "json",
            headers: {
            'Content-Type': 'application/json;charset=utf-8'
            },
            success: function(msg){
            	if(msg!=null){
            		var value ="";
            	　　　for (var i = 0; i < msg.length; i++) {
            	　　　　// 如果在select中传递其他参数，可以在option 的value属性中添加参数
            	　　　	value += "<option value='"+msg[i].applyTypeNo+";"+msg[i].applyTypePrice+"'>"+msg[i].applyTypeName+"</option>";
            	　　	}
            	　$("#acceptType").append(value);
            		form.render('select');
            	}
             },   
        });
    });
    
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
    
    // 打印回执
    function printData(data){
		if(!data){
			var checkStatus = table.checkStatus('operateList');
			console.log(checkStatus.data);
			if(checkStatus.data.length >1) {
		         layer.msg("只能打印一个回执数据");
		         return;
		         }
			data = checkStatus.data[0];
		}
		if(data == null ){
			layer.msg("请选择需要打印的数据");
			return false;
		}
		var index = layui.layer.open({
            title : "打印回执",
            type : 2,
            anim: 3,
            content : "/views/business/printData.html?"+data.applyNo,
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

    // 打印收据
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
    		content : "/views/business/printShouju.html?"+data.applyNo,
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
    
    // 批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('roleListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
            	if(data[i].status=="00"){
            		layer.confirm('选中的订单含有支付成功的订单，支付成功的订单不允许删除！！！', {icon: 3, title: '提示信息'});
            		return;
            	}
                newsId.push(data[i].applyNo);
            }
            layer.confirm('确定要删除选中的商标受理信息吗？', {icon: 3, title: '提示信息'}, function (index) {
            	$.ajax({
                    type: "post",
                    url: "/business/apply/updateByapplyNo",
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

    // 列表操作
    table.on('tool(operateList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'print'){ // 打印回执
        	printData(data);
        }
        if(layEvent === 'printShouju'){ // 打印回执
        	printShouju(data);
        }
    });
    
    exports('role/roleList', {});
});