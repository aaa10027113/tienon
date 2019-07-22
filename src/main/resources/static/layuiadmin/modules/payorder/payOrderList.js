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
           {field: 'applyNo', title: '申请序号', minWidth:150, align:"center"},
           {field: 'orderNo', title: '订单编号', minWidth:150, align:'center'},
           {field: 'payTime', title: '支付时间', minWidth:150, align:"center"},
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
		          	}else if(d.status=="99"){
		          		return d.status="未知状态";
		          	}
        	   }
          },
          {title: '打印', minWidth:170,fixed:"right",align:"center",templet:function(d){
        	  if(d.status=="00"){
        		  return '<button class="layui-btn layui-btn-md"   lay-event="print">回执</button>'+
     	  		 '<button class="layui-btn layui-btn-md"   lay-event="printShouju">收据</button>';
        	  }else{
        		  return '<button class="layui-btn layui-btn-md"   lay-event="print">回执</button>'+
     	  		 '<button class="layui-btn layui-btn-md layui-btn-disabled">收据</button>';
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
   			  searchCondition		  
   		  ,page: {
   		    curr: 1 //重新从第 1 页开始
   		  }
   		});
   })

    
    $(".servet_btn").click(function(){
    	 var checkStatus = table.checkStatus('orderListTable'),
      data = checkStatus.data,
      newsId = [];
     if(data.length ==1) {
    	 addServer(data[0]);
     }else{
         layer.msg("请选择一个需要操作的商标",{time:5*1000});
     }     
    })
    
    
    $(".page_btn").click(function(){
   	 var checkStatus = table.checkStatus('orderListTable'),
      data = checkStatus.data,
      newsId = [];
      if(data.length ==1) {
    	addPage(data[0]);
      }else{
        layer.msg("请选择一个需要操作的订单",{time:5*1000});
     }     
    	
    })
    
    //列表操作
    table.on('tool(payOrderList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'print'){ //打印回执
        	printData(data);
        }
        if(layEvent === 'printShouju'){ //打印回执
        	printShouju(data);
        }
        
    });
    
    exports('payorder/payOrderList', {});
});

