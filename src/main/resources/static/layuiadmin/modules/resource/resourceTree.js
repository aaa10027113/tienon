layui.extend({
	layuiXtree:'../../../layuiadmin/lib/layuiXtree'
}).define([ 'form', 'tree', 'layer','comExt','layuiXtree'],
		function(exports) {
	var layer = parent.layer === undefined ? layui.layer : top.layer,
		comExt = layui.comExt,
		layuiXtree = layui.layuiXtree,
		form = layui.form,
		$ = layui.jquery,
		index = parent.layer.getFrameIndex(window.name),//获取窗口索引
		tree = layui.tree,
		parent$ = window.parent.layui.jquery;;
	
	// 同步(绑定)layui的tree的搜索(过滤)框
    // treeId: tree所在的容器的id
    // filter: 对应的搜索框的selector或者dom对象,尽量要确保是唯一的节点，或者真的是要控制这个树的input
    // callback: 回调 参数(树节点jquery对象, 输入框对象, 匹配到的节点数量)
    tree.syncLayuiTreeFilter = function (treeId, filter, callback) {
      var treeElem = $('#' + treeId), filterElem = $(filter);
      if (!filterElem.length || !filterElem.length) {
        return;
      }
      filterElem.unbind('change').change(function (event) {
        var that = this;
        var value = $(that).val().trim();
        var HIDE = 'layui-hide';
        var hintClass = 'search_hit';
        // 先恢复现场
        treeElem.find('.' + HIDE).removeClass(HIDE);
        treeElem.find('.' + hintClass).removeClass(hintClass).each(function (index, item) {
          item = $(item);
          item.html(item.data('textOld')).data('textOld', null);
        });
        // 如果有值筛选开始
        if (value) {
          layui.each(treeElem.find('cite'), function (index, elem) {
            elem = $(elem);
            var textTemp = elem.text();
            if (textTemp.indexOf(value) === -1) {
              // 不存在就隐藏
              elem.closest('li').addClass(HIDE);
            } else {
              // 命中就添加一个class
              elem.addClass(hintClass)
                .data('textOld', textTemp)
                .html(textTemp.replace(new RegExp(value, 'g'), '<span class="search_hint_text">' + value + '</span>'));
            }
          });
          layui.each(treeElem.find('.' + hintClass), function (index, elem) {
            elem = $(elem);
            elem.parents('li').removeClass(HIDE);//显示上级和搜索出的机构
            //找到搜索出机构下所有机构
            var nodeList = elem.parents('a').siblings('ul').find('.layui-hide');
            //展开搜索出机构下所有机构
            getChildNode(nodeList);
            elem.parents('ul').each(function (i, item) {
              if (!$(item).hasClass('layui-show')) {
                $(item).parent('li').find('>i').click();//展开到搜索出的机构
              }
            });
            //elem.parents('ul').parent('li').removeClass(HIDE);
          });
        }
        typeof callback === 'function' && callback.call(that, treeElem, filterElem, treeElem.find('.' + hintClass).length);
      });
    };
    
    /**
     * 移除机构属性
     */
    function getChildNode(nodeList){
        //先找到子结点
        var HIDE = 'layui-hide';
        for(var i = 0;i < nodeList.length;i++){
        	nodeList[i].className = nodeList[i].className.replace( new RegExp( "(\\s|^)" + HIDE + "(\\s|$)" )," " );
        }
    }

    /**
     * 初始化树
     */
	$.ajax({
        url: '/pageResource/queryList',
        type: 'post',
        data:JSON.stringify({id: ''}),
        dataType: 'json',
        headers : {
			'Content-Type' : 'application/json;charset=utf-8'
		},
        success: function(res){
        	tableData = res.obj;
        	var treeValueStr = JSON.stringify(comExt.transJsonArray(tableData,"id","superiorId"));
        	treeValueStr = treeValueStr.replace(new RegExp('displayName','g'),"name");
        	var treeValue = $.parseJSON(treeValueStr);//将json数组字符串转为json数组
        	
        	tree({
        	      elem: '#demo1' //指定元素
        	      , target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
        	      , nodes: treeValue
        	      , click: function(obj){//将选中机构返回上级
        	    	    parent$('#superiorId').val(obj.id);
        	            parent.layer.close(index);
        	    	  }
        	    });
        }
      });
    	
    	/*var key = 'test'*/
    /*function parseJson(arr,key) {
    		arr = arr.slice();
    		function toParse(arr) {
    		arr.forEach(function (item) {
    		    if (item.displayName && Array.isArray(item.displayName)) {
    		        item[key] = item.displayName
    		        toParse(item[key])
    		    }
    		    delete item.displayName
    		})
    		return arr;
    	}
        return toParse(arr)
    }*/
    
    
    $("#searchTree").bind("input propertychange", function() {
    	tree.syncLayuiTreeFilter('demo1', '[name="searchTree"]', function (treeElem, filterElem, hitNumbers) {
    	      /*console.log('hitNumbers', hitNumbers);
    	      layer.msg('找到' + hitNumbers + '个节点');*/
    	    });
    });
    
 	 exports('resource/resourceTree', {});	  

})
