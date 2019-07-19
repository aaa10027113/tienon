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
        elem: '#refundOrderList',
        url : '/refundOrder/queryList',
        cellMinWidth : 95,
        page : true,
        limits : [10,15,20,25],
        contentType:"application/json;charset=utf-8",
        id : "refundOrderListTable",
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
            {field: 'applyNo', title: '申请序号', minWidth:150, align:"center"},
            {field: 'orderNo', title: '订单编号', minWidth:150, align:'center'},
            {field: 'refundNo', title: '退款编号', minWidth:150, align:"center"},
            {field: 'refundTime', title: '退款时间', minWidth: 150, align: 'center'},
            {field: 'refundReasons', title: '退款原因', minWidth: 300, align: 'center'},
            {
                field: 'refundTime', title: '退款状态',  minWidth:150, align:'center',templet:function(d){
                    switch (d.status) {
                        case "00": return "退款成功";
                        case "01": return "受理中";
                        case "02": return "退款失败";
                        case "03": return "订单超时";
                        default:return "未知状态";
                    }
                }
            },
            {title: '操作', width:125,fixed:"right",align:"center" ,templet:function(d){
                if(d.status == "01"){
                    return `<a class="layui-btn layui-btn-xs" lay-event="refush">刷新</a>`;
                }else {
                    return `<a class="layui-btn layui-btn-xs layui-btn-disabled" lay-event="refush">刷新</a>`;
                }
            }}
        ]]
    });
    

    

    
    $(".refundOrderSearch").click(function(){
    	 var applyNo=$("#applyNo").val();
    	 var orderNo=$("#orderNo").val();
    	 var refundNo=$("#refundNo").val();
    	 search ={applyNo:applyNo,orderNo:orderNo,refundNo:refundNo};
    	 var searchCondition={searchCondition:search};
    	 tableIns.reload({
    		  where: 
    			  searchCondition		  
    		  ,page: {
    		    curr: 1 //重新从第 1 页开始
    		  }
    		});
    })
    

    //列表操作
    table.on('tool(refundOrderList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	updaterefundOrder(data);
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
        		deleterefundOrder(data);
      		});
        }
    });
    
    exports('refund/refundOrderList', {});
});
