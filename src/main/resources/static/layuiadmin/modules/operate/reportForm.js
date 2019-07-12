layui.define([ 'form', 'table', 'layer', 'laydate','comExt' ], function(
		exports) {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laydate = layui.laydate;
        $ = layui.jquery,
        comExt = layui.comExt,
        table = layui.table;
     //开始时间
     laydate.render({
    	elem: '#beginTime',
    	value:new Date(new Date()-7000*60*60*24)
    });
     //结束时间
     laydate.render({
   	  elem: '#endTime',
   	  value:new Date()
   });
   /*  laydate.render({
    	 elem: '#endTime'
     });*/
     var searchCondition="";
     function aad() {
    	 var beginTime=$("#beginTime").val();
       	 if(beginTime!="" && null!=beginTime){
       		 beginTime = beginTime+" 00:00:00";
       	 }
       	 
       	 var endTime=$("#endTime").val();
       	 if(endTime!="" && null!=endTime){
       		endTime = endTime+" 23:59:59";
       	 }
       	 search ={beginTime:beginTime,endTime:endTime};
       	 searchCondition={searchCondition:search};
       	 return searchCondition;
	};
    //商标列表
    var tableIns = table.render({
        elem: '#reportList',
        url : '/operate/reportList',
        cellMinWidth : 95,
//        page : true,
//        limits : [10,15,20,25],
        contentType:"application/json;charset=utf-8",
        id : "roleListTable",
        method : 'post',
        where: aad(),
        request: {
//        	  pageName: 'page' //页码的参数名称，默认：page
//        	  ,limitName: 'rows' //每页数据量的参数名，默认：limit
        	},
        response: {
        	   statusName: 'success' //数据状态的字段名称，默认：code
        	  ,statusCode: true //成功的状态码，默认：0
        	  ,msgName: 'msg' //状态信息的字段名称，默认：msg
        	  ,countName: 'obj/total' //数据总数的字段名称，默认：count
        	  ,dataName: 'obj/rows' //数据列表的字段名称，默认：data
        	} ,
        cols : [[
          {field: 'num', title: '序号', width:80, align:"center",templet:'#num'},
          {field: 'humanName', title: '申请人名称', width:150, align:'center'},
          {field: 'acceptDate', title: '申请日期', width:150, align:"center"},
          {field: 'acceptTypeName', title: '业务类型', width:200, align:"center"},
          {field: 'addType', title: '类别', width:485, align:"center"},
          {field: 'amt', title: '金额', width:150, align:"center"},
          
        ]],
//        done: function (res, curr, count) {
//            exportData=res.data;
//            alert(exportData);
//        	return
//        }
    });
    
  //重新加载查询结果
    $(".queryCommit").click(function(){
   	 var beginTime=$("#beginTime").val();
   	 if(beginTime!="" && null!=beginTime){
   		 beginTime = beginTime+" 00:00:00";
   	 }
   	 
   	 var endTime=$("#endTime").val();
   	 if(endTime!="" && null!=endTime){
   		endTime = endTime+" 23:59:59";
   	 }
   	 search ={beginTime:beginTime,endTime:endTime};
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

   var beginTime=$("#beginTime").val();
	if(beginTime!="" && null!=beginTime){
		beginTime = beginTime+" 00:00:00";
	}
	
	var endTime=$("#endTime").val();
	if(endTime!="" && null!=endTime){
		endTime = endTime+" 23:59:59";
	}
   document.getElementById('export').href = "http://www.baidu.com";
    $("#export").click(function(){
    	var beginTime=$("#beginTime").val();
    	if(beginTime!="" && null!=beginTime){
    		beginTime = beginTime+" 00:00:00";
    	}
    	
    	var endTime=$("#endTime").val();
    	if(endTime!="" && null!=endTime){
    		endTime = endTime+" 23:59:59";
    	}
    	window.location.href="/operate/download?beginTime="+beginTime+"&endTime="+endTime;
	   /* $.ajax({
            type: "post",
            url: "/operate/download",
            data: {beginTime,endTime},
            dataType: "json",
            headers: {
            'Content-Type': 'application/json;charset=utf-8'
            },
            success: function(msg){
            	
             },   
        });*/

    })
    
    exports('role/roleList', {});
});

