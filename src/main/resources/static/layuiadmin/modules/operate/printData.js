layui.define([ 'form', 'table', 'layer', 'laydate','comExt','layedit' ], function(
		exports) {
	var form = layui.form,
    layer = parent.layer === undefined ? layui.layer : top.layer,
    $ = layui.jquery,
    comExt = layui.comExt,
    table = layui.table;

	//初始化数据
    $(function(){
    	var applyNo = location.href.split("?")[1];
        $.post("/operate/printInfo",{applyNo},function(res){
            	console.log(res);
            	if(!res.success){
            		layer.alert(res.msg, {
            			icon: 5,
            			title: "错误"
            			});
            		return false;
            	}
            	var msg = res.obj;
            	$("#companyName").html(msg.companyName);
            	var date = msg.acceptDate.split("-");
            	$("#acceptYear").html(date[0]);
            	$("#acceptMounth").html(date[1]);
            	$("#acceptDay").html(date[2]);
            	//商标名称
            	if(msg.trademarkName.length<=8){
            		$("#trademarkName_center").html(msg.trademarkName);
            	}else if(msg.trademarkName.length<=16){
            		$("#trademarkName_top").html(msg.trademarkName.slice(0,8));
            		$("#trademarkName_center").html(msg.trademarkName.slice(9));
            	}else{
            		$("#trademarkName_top").html(msg.trademarkName.slice(0,8));
            		$("#trademarkName_center").html(msg.trademarkName.slice(8,16));
            		$("#trademarkName_down").html(msg.trademarkName.slice(16));
            	}
            	//受理类型
            	if(msg.acceptTypeName.length<=6){
            		$("#acceptTypeName_center").html(msg.acceptTypeName);
            	}else if(msg.trademarkName.length<=12){
            		$("#acceptTypeName_top").html(msg.acceptTypeName.slice(0,6));
            		$("#acceptTypeName_center").html(msg.acceptTypeName.slice(6));
            	}else{
            		$("#acceptTypeName_top").html(msg.acceptTypeName.slice(0,8));
            		$("#acceptTypeName_center").html(msg.acceptTypeName.slice(8,12));
            		$("#acceptTypeName_down").html(msg.acceptTypeName.slice(12,15)+"...");
            	}
            	//受理类别
            	if(msg.addType.length<=11){
            		$("#addType_center").html(msg.addType);
            	}else if(msg.trademarkName.length<=23){
            		$("#addType_top").html(msg.addType.slice(0,11));
            		$("#addType_center").html(msg.addType.slice(12));
            	}else{
            		$("#addType_top").html(msg.addType.slice(0,11));
            		$("#addType_center").html(msg.addType.slice(12,23));
            		$("#addType_down").html(msg.addType.slice(24,32)+"...");
            	}
            	//将字符串转换为二维码
            	var address = location.href.split("system")[0];
            	var qrcode = new QRCode(document.getElementById("code"), {
                	width : 100,
                	height : 100
                });
                
            	var text= address+"h5/pay.html?applyNo="+msg.applyNo;
            	qrcode.makeCode(text);
            	//------------------------------------
            	//表格数据反选
            	var temp="<tr><td><span>1</span></td>";
            	temp +="<td><span>"+msg.trademarkName+"</span></td>"
            		 +"<td><span>"+msg.acceptTypeName+"</span></td>"
            		 +"<td><span>"+msg.addType+"</span></td></tr>";
//            	$("tbody").append(temp);
            	
             },"json") 
             
//        });
    });
	//=======================================
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        comExt = layui.comExt,
        $ = layui.jquery,
        layedit = layui.layedit;

    $("#print1").click(function(){
    	preview();
    	location.reload();
    })
    function preview(){
        bdhtml=window.document.body.innerHTML;//获取当前页的html代码
        sprnstr="<!--startprint-->";//设置打印开始区域
        eprnstr="<!--endprint-->";//设置打印结束区域
        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
        window.document.body.innerHTML=prnhtml;
        window.print();
        window.document.body.innerHTML=bdhtml;
    }

    
    $('#close').on('click',function(){
 		layer.confirm("是否退出当前页面？",function(index){
 			//当你在iframe页面关闭自身时
			var index = parent.layer.getFrameIndex(window.name);
	 		parent.layer.close(index);
	 		layer.closeAll();
 		})
 	})
 	

// 	  exports('role/roleUpdate', {});
});