
/**
 * ajax 请求统一处理
 */
var AjaxUtils = {
	/**
	 * 完成处理,主要用于操作回调函数
	 */
	loadding : function(type){
 		var loader = $("<div class='weui-ajax-loading'  ><p class='weui-toast_content'>正在加载.....</p></div>"); 
 		$("body").append(loader);
 		if(type==1){
 			$("#ajax-loading").show();
 		}else{
 			 $(".weui-ajax-loading").remove();
 		}
	},
	httpGet : function(requestUrl ,callback){
		$.getJSON( requestUrl,callback);
	},	
	httpPost: function(url, data, callback) {
		$.ajax({
			type : 'post',
			url : url,
			cache : false,
			data : JSON.stringify(data),
			contentType : "application/json",
			dataType : "json",
			success : function(result) {
				callback(result);
			},
			error : function() {
				
			}
		});
	},	
	 
	/**
	 * 表单请求
	 */
	formRequest : function(form ,  callback){
        var data = $("#"+form).serialize();
        var url = $("#"+form).attr("action");
        $.post(url, data, function (respData) {
            if (respData.code == "0000") {
            	callback(respData);
            } else {
            	callback(respData);
                $.toast(respData.msg);
            }
        }, 'json');
	},
	 
	loadData: function(url, data, callback) {
		AjaxUtils.loadding(1);
		$.post(url, '', function (respData) {
			 
			AjaxUtils.loadding(0);
	        if (respData.code == "0000") {
	        	callback(respData);
	        } else if(respData.code == "1010"){
	        	window.location.href="login.html";
	        	
	        }  else {
	        	 $.toast(respData.msg);	 
	        }
	    });
	}

};

 