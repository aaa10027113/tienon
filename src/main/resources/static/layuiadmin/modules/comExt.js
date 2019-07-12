layui.define(['jquery','layer'],function(exports){
	"use strict";//整个脚本都将以"严格模式"运行
	var $ = layui.jquery,
    layer = layui.layer,
	comExt = function(){
		 this.config = {
		            elem: undefined, //容器
		            data: undefined, //数据源
		            url: undefined, //数据源地址
		            type: 'GET', //读取方式
		            cached: false //是否使用缓存
		        };
	      };
      
	      
	
	 comExt.prototype.set = function (options) {//"prototype"代表私有方法
	      var that = this;
	      that.config.data = undefined;
	      $.comExtend(true, that.config, options);
	      return that;
	  };
	  
    //ajax
	comExt.prototype.ajax = function(e){
        var waiting = layer.msg('加载中', {
            icon: 16,
              shade: 0.01
            });
        var options = $.extend(
            {
                async:true,
                url:"",
                type:"post",
                contentType:'application/json;charset=utf-8',
                dataType:'json',
                timeout:30000
              },e);
          
        $.ajax($.extend(options,{
		        	beforSend:function(){
		                "function" == typeof e.beforSend && e.beforSend(params);
		              },
		        	success:function(data){
		              layer.close(waiting);
		              if(data.success == true){
			                
		              }else if(data.success == false){
		            	  if(data.msg){
		            		  layer.msg(data.msg);
		            	  }
		              }
		              "function" == typeof e.success && e.success(data);
		            },
		            error:function(xhr,textStatus,errorThrown){
		              layer.close(waiting);
		              var msg = "error",
		              status = xhr.status+"",
		              val;
		              switch(textStatus){
		              	case "timeout" :  
		              		msg = "请求超时";break;
		              	case "abort" :  
		              		msg = "请求中止，请检查网络";break;
		              	case "parsererror" :  
		              		msg = "返回数据解析错误";break;
		              	default:
		              		if(status.slice(0,1) == "4"){
		              			msg = "客户端异常，错误状态码：" + status;
		              		}else if(status.slice(0,1) == "5"){
		              			msg = "服务器异常，错误状态码：" + status;
		              		}
		              		break;
		              }
		              "function" == typeof e.error && (val = e.error());
		              //如果不需要默认的error提示，请在自定义的error内return false;
		              if(val == false){
		            	  return ;
		              }else if(val && val !== undefined){
		            	  msg = val;
		              }
		              layer.msg(msg);
		            },
        }))
      }
	
	/**
	 * form 表单数据返显
	 * @params parent jquery Object 上级菜单Dom 
	 * @params data Object
	 * @params form layui Object
	 */
	comExt.prototype.fillInput = function(parent,data,form){
		for(var key in data){
			var id= "#"+key,
			 	arr =['checkbox','radio'];
			var type =parent.find(id + " input").attr('type');
			if(type && arr.indexOf(type)>-1){
				id += "  input"; 
				var inputs = parent.find(id);
				$.each(inputs,function(i,elem){
					elem.checked = false;
				})
				if(data[key].length>1){
					data[key] = data[key].split(",");
					layui.each(data[key],function(i,item){
						inputs[item].checked = true ;
					})
				}else {
					 var index = data[key];
					inputs[index].checked = true ;
				}
			}else{
				parent.find(id).val(data[key]) ;
			}
		}
//		form.render();
	}
	
	/**
	 * 页面添加水印
	 */
	comExt.prototype.water = function(font,settings){
		    //默认设置  
		    var defaultSettings={  
		        watermark_txt:"默认水印",  
		        watermark_x:100,//水印起始位置x轴坐标  
		        watermark_y:20,//水印起始位置Y轴坐标  
		        watermark_rows:20,//水印行数  
		        watermark_cols:20,//水印列数  
		        watermark_x_space:100,//水印x轴间隔  
		        watermark_y_space:50,//水印y轴间隔  
		        watermark_color:'#000000',//水印字体颜色  
		        watermark_alpha:0.3,//水印透明度  
		        watermark_fontsize:'18px',//水印字体大小  
		        watermark_font:'微软雅黑',//水印字体  
		        watermark_width:120,//水印宽度  
		        watermark_height:80,//水印长度  
		        watermark_angle:45//水印倾斜度数  
		    };  
		    //采用配置项替换默认值，作用类似jquery.extend  
		    if(arguments.length===1&&typeof arguments[0] ==="object" )  
		    {  
		        var src=arguments[0]||{};
		        var key='';
		        for(key in src)  
		        {  
		            if(src[key]&&defaultSettings[key]&&src[key]===defaultSettings[key])  
		                continue;  
		            else if(src[key])  
		                defaultSettings[key]=src[key];  
		        }  
		    }  
		  
		    var oTemp = document.createDocumentFragment();  
		  
		    //获取页面最大宽度  
		    var page_width = Math.max(document.body.scrollWidth,document.body.clientWidth);  
		    //获取页面最大长度  
		    var page_height = Math.max(document.body.scrollHeight,document.body.clientHeight);  
		  
		    //如果将水印列数设置为0，或水印列数设置过大，超过页面最大宽度，则重新计算水印列数和水印x轴间隔  
		    if (defaultSettings.watermark_cols == 0 ||  
		   　　　　(parseInt(defaultSettings.watermark_x   
		　　　　+ defaultSettings.watermark_width *defaultSettings.watermark_cols   
		　　　　+ defaultSettings.watermark_x_space * (defaultSettings.watermark_cols - 1))   
		　　　　> page_width)) {  
		        defaultSettings.watermark_cols =   
		　　　　　　parseInt((page_width  
		　　　　　　　　　　-defaultSettings.watermark_x  
		　　　　　　　　　　+defaultSettings.watermark_x_space)   
		　　　　　　　　　　/ (defaultSettings.watermark_width   
		　　　　　　　　　　+ defaultSettings.watermark_x_space));  
		        defaultSettings.watermark_x_space =   
		　　　　　　parseInt((page_width   
		　　　　　　　　　　- defaultSettings.watermark_x   
		　　　　　　　　　　- defaultSettings.watermark_width   
		　　　　　　　　　　* defaultSettings.watermark_cols)   
		　　　　　　　　　　/ (defaultSettings.watermark_cols - 1));  
		    }  
		    //如果将水印行数设置为0，或水印行数设置过大，超过页面最大长度，则重新计算水印行数和水印y轴间隔  
		    if (defaultSettings.watermark_rows == 0 ||  
		   　　　　(parseInt(defaultSettings.watermark_y   
		　　　　+ defaultSettings.watermark_height * defaultSettings.watermark_rows   
		　　　　+ defaultSettings.watermark_y_space * (defaultSettings.watermark_rows - 1))   
		　　　　> page_height)) {  
		        defaultSettings.watermark_rows =   
		　　　　　　parseInt((defaultSettings.watermark_y_space   
		　　　　　　　　　　　+ page_height - defaultSettings.watermark_y)   
		　　　　　　　　　　　/ (defaultSettings.watermark_height + defaultSettings.watermark_y_space));  
		        defaultSettings.watermark_y_space =   
		　　　　　　parseInt((page_height   
		　　　　　　　　　　- defaultSettings.watermark_y   
		　　　　　　　　　　- defaultSettings.watermark_height   
		　　　　　　　　　　* defaultSettings.watermark_rows)   
		　　　　　　　　　/ (defaultSettings.watermark_rows - 1));  
		    }  
		    var x;  
		    var y;  
		    for (var i = 0; i < defaultSettings.watermark_rows; i++) {  
		        y = defaultSettings.watermark_y + (defaultSettings.watermark_y_space + defaultSettings.watermark_height) * i;  
		        for (var j = 0; j < defaultSettings.watermark_cols; j++) {  
		            x = defaultSettings.watermark_x + (defaultSettings.watermark_width + defaultSettings.watermark_x_space) * j;  
		  
		            var mask_div = document.createElement('div');  
		            mask_div.id = 'mask_div' + i + j;  
		            mask_div.appendChild(document.createTextNode(defaultSettings.watermark_txt));  
		            //设置水印div倾斜显示  
		            mask_div.style.webkitTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";  
		            mask_div.style.MozTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";  
		            mask_div.style.msTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";  
		            mask_div.style.OTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";  
		            mask_div.style.transform = "rotate(-" + defaultSettings.watermark_angle + "deg)";  
		            mask_div.style.visibility = "";  
		            mask_div.style.position = "absolute";  
		            mask_div.style.left = x + 'px';  
		            mask_div.style.top = y + 'px';  
		            mask_div.style.overflow = "hidden";  
		            mask_div.style.zIndex = "9999";  
		            mask_div.style.pointerEvents = 'none';  
		            //mask_div.style.border="solid #eee 1px";  
		            mask_div.style.opacity = defaultSettings.watermark_alpha;  
		            mask_div.style.fontSize = defaultSettings.watermark_fontsize;  
		            mask_div.style.fontFamily = defaultSettings.watermark_font;  
		            mask_div.style.color = defaultSettings.watermark_color;  
		            mask_div.style.textAlign = "center";  
		            mask_div.style.width = defaultSettings.watermark_width + 'px';  
		            mask_div.style.height = defaultSettings.watermark_height + 'px';  
		            mask_div.style.display = "block";  
		            oTemp.appendChild(mask_div);  
		        }
		    }
		    document.body.appendChild(oTemp);  
	}
	
	
	
	comExt.prototype.updateSelect = function(data){
		for(var key in data){
			var html ="";
			$.each(data[key],function(i,item){
				html += "<option value='"+ item.value +"'>"+item.text+"</option>";
			});
			var id= "#"+key;
			$(id).append(html);
		}
	}
	
	comExt.prototype.verify = function(){
		layui.use(['form'],function(){
			$('form').on('blur','*[ext-verify]',function(e){
				var _this = this,reg,result = false;
				var verify = {
						identity:[/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/ , "身份证号码格式不正确"],
						phone: [/^1\d{10}$/, "请输入正确的手机号"],
						range:['',"请输入规定范围内的数字"]
						},
					msg = "信息填写有误";
				var val = $(_this).val();
				var regName = $(_this).attr('ext-verify');
				if(val === "" )
					return ;	
				
				if(verify[regName]){
					reg = verify[regName][0];
					if(verify[regName][1]){
						msg = verify[regName][1];
					}
					result = reg.test(val);
				}else if(regName.indexOf("range")>-1){
					var range = regName;
					range = range.split("(");
					range = range[1].split(")");
					range = range[0].split(",");
					var min,max;
					if(range[0])
					min = Number(range[0]);
					if(range[1])
					max = Number(range[1]);
					if(min && max){
						if(max<=min){
							console.log("请设置range正确范围");
						}else if(val >= min && val <= max){
							result = true;
						}
					}else if(max && !min){
						while(val <= max){
							result = true;
						}
					}else if(min && !max){
						while(val >= min){
							result = true;
						}
					}
					
					msg = verify.range[1];
				}else{
					reg =new RegExp(regName);
					result = reg.test(val);
				}
				if(!result){
					layer.msg(msg,{icon:2});
					$(_this).addClass("layui-form-danger"); 
					_this.focus();
				}
				})
		})
	}
	
	comExt.prototype.trim = function(data){
		if ("object" == typeof(data)) {
			for (var key in data){
				data[key] = data[key].replace(/(^\s*)|(\s*$)/g, "");
			}
		} else if ("string" == typeof(data)) {
			return data.replace(/(^\s*)|(\s*$)/g, "");
		}
		
		return data;
	}
	
	//获取当前网站的IP地址和端口号
	comExt.prototype.getLocalHostPath = function(){
		//获取当前网址
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，
        var localhostPath = curWwwPath.substring(0, pos);
        return localhostPath;
	}
	
	//根据字典参数值获取字典参数名称
	comExt.prototype.getDictParam = function(dictTyId,dictCd) {
		var dictParamList = $.parseJSON(sessionStorage.getItem("dictParamList"));
		var dictNm = "";
		//从session中获取全部字典参数
		for(var i=0;i<dictParamList.length;i++){
			if(dictParamList[i].dictTyId == dictTyId && dictParamList[i].dictCd == dictCd){
				dictNm = dictParamList[i].dictNm;
				break;
			}
		}
		if(dictNm == "" || dictNm == null){
			dictNm = dictCd;
	    } 
		return dictNm;
	}
	
	//根据字典类型获取字典参数并填充至下拉框
	comExt.prototype.getDictParamList = function(selectArr) {
		if(selectArr == null || selectArr.length < 1){
			return ;
		}
		for(var i=0;i<selectArr.length;i++){
			var dictTyId = selectArr[i].title;
			
			if(dictTyId == null || dictTyId == ""){
				continue;
			}
			//填充下拉框
			createOption(selectArr[i],dictTyId);
		}
		//重新渲染
		layui.form.render();
	}
	
	
	//填充下拉框
	function createOption(selectObj,dictTyId){
		var dictParamList = $.parseJSON(sessionStorage.getItem("dictParamList"));
		if(!dictParamList){
			layui.msg("字典参数加载失败，请重新登录",{time:5*1000});
			return;
		}
		$(selectObj).html("<option value=''></option>");
		for(var i=0;i<dictParamList.length;i++){
			if(dictParamList[i].dictTyId == dictTyId){
				$(selectObj).append("<option value='"+dictParamList[i].dictCd+"'>"+dictParamList[i].dictNm+"</option>")
			}
		}
	}
	
	//获取年月日（YYYY-MM-DD）
	comExt.prototype.formatDate = function(date){
		var year = Number(date.getFullYear()) // 年
	    var month = (Number(date.getMonth()) + 1)<10 ? "0"+(Number(date.getMonth()) + 1) : (Number(date.getMonth()) + 1)// 月份（月份是从0~11，所以显示时要加1）
	    var day = (Number(date.getDate()))<10 ? "0"+(Number(date.getDate())):(Number(date.getDate())) // 日期
	    
	    var nowDate = year+"-"+month+"-"+day;
		return nowDate;
	}
	
	/**
	 * 改变窗口大小时，重置弹窗的宽高，防止超出可视区域
	 * @author tanghuakui
	 */
	comExt.prototype.full = function(index){
		layer.full(index);
        window.sessionStorage.setItem("indexChild",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layer.full(window.sessionStorage.getItem("indexChild"));
        })
	}
	
	/**
	 * 将json数组转为树形json
	 * @param jsonList json数组
	 * @param idstr 主键id
	 * @param pidstr 上级菜单id
	 * @author tanghuakui
	 */
	comExt.prototype.transJsonArray = function(jsonList,idstr,pidstr){  
        var result = [],temp = {};  
        for(var i = 0; i < jsonList.length; i++){  
            temp[jsonList[i][idstr]]=jsonList[i];//将children数组转成对象类型  
        }  
        for(var j=0; j<jsonList.length; j++){  
            var tempVp = temp[jsonList[j][pidstr]]; //获取每一个子对象的父对象  
            if(tempVp){//判断父对象是否存在，如果不存在直接将对象放到第一层  
                if(!tempVp["children"]) tempVp["children"] = [];//如果父元素的children对象不存在，则创建数组  
                tempVp["children"].push(jsonList[j]);//将本对象压入父对象的children数组  
            }else{  
                result.push(jsonList[j]);//将不存在父对象的对象直接放入一级目录  
            }  
        }  
        return result;  
    }
	
	
	var comExt= new comExt(); 
	comExt.verify();
	
	exports('comExt', comExt);
})