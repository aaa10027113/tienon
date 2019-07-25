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
     
    var tableIns = table.render({
        elem: '#payOrderList',
        url : '/paymentOnline/queryPayOrderList',
        cellMinWidth : 95,
        page : true,
        limits : [10,15,20,25],
        contentType:"application/json;charset=utf-8",
        id : "orderListTable",
        method : 'post',
        where: aad(),
        request: {
        	  pageName: 'page', //页码的参数名称，默认：page
        	  limitName: 'rows' //每页数据量的参数名，默认：limit
        },
        response: {
        	  statusName: 'success', //数据状态的字段名称，默认：code
        	  statusCode: true, //成功的状态码，默认：0
        	  msgName: 'msg',//状态信息的字段名称，默认：msg
        	  countName: 'obj/total', //数据总数的字段名称，默认：count
        	  dataName: 'obj/rows'//数据列表的字段名称，默认：data
        } ,
        cols : [[
           {type: "checkbox", fixed:"left", width:50},
           {field: 'applyNo', title: '申请序号', minWidth:150, align:"center"},
           {field: 'orderNo', title: '订单编号', minWidth:150, align:'center'},
           {field: 'payTime', title: '支付时间', minWidth:150, align:"center"},
           {field: 'deleteFlag', title: '是否删除', minWidth:150, align:"center",templet:function(d){
        	  if(d.deleteFlag=="1"){
        		  return '是';
        	  }else{
        		  return '';
        	  }
          }},
           {field: 'amt', title: '支付金额', minWidth:150, align:"center"},
           {field: 'status', title: '支付状态',  minWidth:125, align:'center',
        	   templet:function(d){
		          	if(d.status=="00"){
		          		return d.status="支付成功";
		          	}else if(d.status=="01"){
		          		return d.status="待支付";
		          	}else if(d.status=="02"){
		          		return d.status="支付失败";
		          	}else if(d.status=="03"){
		          		return d.status="订单超时";
		          	}else if(d.status=="04"){
		          		return d.status="不确定";
		          	}else if(d.status=="05"){
		          		return d.status="无需支付";
		          	}else if(d.status=="99"){
		          		return d.status="未知状态";
		          	}
        	   }
          },
          {title: '操作', minWidth:125,fixed:"right",align:"center",templet:function(d){
        	  if(d.status=="00"){
        		  return '<button class="layui-btn layui-btn-xs"   lay-event="refund">退款</button>'+
        		  '<button class="layui-btn layui-btn-xs layui-btn-normal"   lay-event="printShouju">收据打印</button>';
        	  }else{
        		  return '<button class="layui-btn layui-btn-xs layui-btn-disabled" >退款</button>'+
        		  '<button class="layui-btn layui-btn-xs layui-btn-disabled">收据打印</button>';
        	  }
          }}
        ]]
    });
    
  //重新加载查询结果
    $(".queryCommit").click(function(){
   	 var applyNo=$("#applyNo").val();
   	 var orderNo=$("#orderNo").val();
   	 var status=$("#status").val();
   	
   	 search ={applyNo:applyNo,orderNo:orderNo,status:status};
   	 var searchCondition={searchCondition:search};
   	 tableIns.reload({
   		  where: 
   			  searchCondition,		  
   		  page: {
   			  
   		    curr: 1 //重新从第 1 页开始
   		  }
   		});
   })
    
    //列表操作
    table.on('tool(payOrderList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'refund'){ //退款
        	refund(data);
        }
        if(layEvent === 'printShouju'){ //打印回执
        	printShouju(data);
        }
        
    });
    
    
    
  //打印收据
    function printShouju(data){
    	if(!data){
    		var checkStatus = table.checkStatus('payOrderList');
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
    function getReason(applyNo){
		var refundReasons;
		var password;
		layer.prompt({
			formType: 2,
			value: '',
			title: '退款原因',
			area: ['510px', '200px'] //自定义文本域宽高
		}, function(value, index0, elem){
			refundReasons = value;
			layer.prompt({
				formType: 1,
				value: '',
				title: '请输入操作密码',
				area: ['510px', '200px'] //自定义文本域宽高
			}, function(value1, index1, elem1){
				password = value1;
				var data = {"applyNo":applyNo,"refundReasons":refundReasons,"password":password};
				// alert(JSON.stringify(data))
				$.ajax({
					type: "post",
					url: "/refundOrder/refundPayOrderByApplyNo",
					// data: "applyNo="+applyNo+"&reason="+reason+"&password="+password,
					data: JSON.stringify(data),
					dataType: "json",
					headers: {
						'Content-Type': 'application/json;charset=utf-8'
					},
					success: function(msg){
						if(msg.success==true){
							location.reload();
							layer.msg("操作成功");
						}else{
							layer.confirm(msg.msg, {icon: 3, title: '错误信息'})
							tableIns.reload();
							layer.close(index1);
						}
					},

				});
				layer.close(index1);
			});
			// return reason;
			layer.close(index0);
		});

	}

    //退款
    function refund(data){
    	// debugger
		if(data){
			console.log(data);
			var applyNo = data.applyNo;
			layer.confirm('确定要对此订单进行退款吗？', {icon: 3, title: '提示信息'}, function (index) {
				// debugger
				//-------------------------------------
				// alert(reason)
				layer.close(index);
				getReason(applyNo);

				//-------------------------------------

                
            })
		}
    }
    
    exports('payorder/payOrderList', {});
});

