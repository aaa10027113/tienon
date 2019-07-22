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
                    function buttonColor(color,status) {
                        return `<button class="layui-btn layui-btn-xs layui-btn-radius ${color}">${status}</button>`
                    }
                    switch (d.status) {
                        case "00": return buttonColor("","退款成功");
                        case "01": return buttonColor("layui-btn-normal","受理中");
                        case "02": return buttonColor("layui-btn-danger","退款失败");
                        case "03": return buttonColor("layui-btn-warm","订单超时");
                        default:return buttonColor(" layui-btn-primary","未知状态");
                    }
                }
            },
            {title: '操作', width:125,fixed:"right",align:"center" ,templet:function(d){
                var disabledClass = "";
                if(d.status != "01"){
                    disabledClass = "layui-btn-disabled";
                }
                    return `<a class="layui-btn layui-btn-xs ${disabledClass}" lay-event="refush">刷新</a>`;
                }}
        ]]
    });

    function searchData(){
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
    }

    $(".refundOrderSearch").click(function(){
        searchData()
    });
    
    function refushRefundOrder(data){
        console.log(data)
        searchData();
    }
    //列表操作
    table.on('tool(refundOrderList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'refush'){ //刷新
        	refushRefundOrder(data);
        }
    });
    
    exports('refund/refundOrderList', {});
});
