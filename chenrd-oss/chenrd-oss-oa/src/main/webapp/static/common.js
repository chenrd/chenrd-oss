var Home = {
    	startLoadingContainer: function() {
    		/* $(".loading-container").removeClass("loading-inactive"); */
    		$('#mask').show();
    		$('#mask-container').show();
    	},
    	stopLoadingContainer: function() {
    		/* $(".loading-container").addClass("loading-inactive"); */
    		$('#mask').hide();
    		$('#mask-container').hide();
    	},
    	datatableAjax: function(sSource, aoData, fnCallback, search) {
    		Home.startLoadingContainer();
    		$.ajax({ type: 'post', url: sSource, dataType: 'json', data: $.extend({}, {sEcho: aoData.sEcho, iDisplayLength: aoData.iDisplayLength, iDisplayStart: aoData.iDisplayStart}, search),
        		success: function(resp) { 
        			fnCallback(resp); 
        			Home.stopLoadingContainer();
        		}
    			,
    			error: function(resp) {
    				Home.stopLoadingContainer();
    			}
        	})
    	},
    	ajaxReq: function(req) {
    		if (req.prompt != undefined) {
    			bootbox.confirm(req.prompt, function (result) {
                    if (result) {
                    	if (req.type == undefined || req.type == 'get')
                    		Home.ajaxGetReq(req.url, req.func);
                    	else
                    		Home.ajaxPostReq(req.url, req.data, req.func);
                    }
                });
    		} else {
    			if (req.type == undefined || req.type == 'get')
    				Home.ajaxGetReq(req.url, req.func);
            	else
            		Home.ajaxPostReq(req.url, req.data, req.func);
    		}
    	},
    	ajaxGetReq: function(url, func) {
    		$.get(url, function(res) {
    			if (res != undefined && res != null && res != '') {
    				res = eval('('+res+')');
					if (res.statusCode != undefined && res.statusCode == 300) {
						Home.stopLoadingContainer();
						Home.errorCallback(res.message);
						return;
					}
				}
    			func();
    		})
    	},
    	ajaxPostReq: function(url, data, func) {
    		$.ajax({
    			url: url,
    			data: data,
    			type: 'post',
    			success: function(res) {
    				if (res != undefined && res != null && res != '') {
        				res = eval('('+res+')');
    					if (res.statusCode != undefined && res.statusCode == 300) {
    						Home.stopLoadingContainer();
    						Home.errorCallback(res.message);
    						return;
    					}
    				}
    				func();
    			}
    		})
    	},
    	deleteReloadCurrent: function(url, content, func) {
    		bootbox.confirm(content, function (result) {
                if (result) {
                    Home.reqReloadCurrent(url, func);
                }
            });
    	},
    	reqReloadCurrent: function(url, data, func) {
    		Home.startLoadingContainer();
    		$.get(url, (typeof data == 'object' ? data : {}), function(res) {
    			if (res != undefined && res != null && res != '') {
    				res = eval('('+res+')');
					if (res.statusCode != undefined && res.statusCode != 1000) {
						Home.stopLoadingContainer();
						Home.errorCallback(res.message);
						return;
					}
				}
    			if (typeof data == 'function') {
    				data();
    			} else {
    				func();
    			}
    		})
    	},
    	errorCallback: function(message) {
    		$('#modal-danger .modal-body').html(message);
       		$('#modal-danger-btn').click();
    	},
    	successCallback: function(message) {
    		$('#modal-success .modal-body').html(message);
       		$('#modal-success-btn').click();
    	},
    	callback: function(data, url)
    	{
    		if (data != undefined && data != null && data != '') {
				data = eval('('+data+')');
				if (data.statusCode != undefined && data.statusCode == 300) {
					Home.errorCallback(data.message);
					return;
				}
			}
    		//TODO 
    		
    	}
    }
    
    var language = { "search": "", "sSearch" : "搜索", "sUrl" : "", "sProcessing" : "正在加载数据...", "sLengthMenu" : "显示_MENU_条 ", "sZeroRecords" : "没有您要搜索的内容",
            "sInfo" : "从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条", "sInfoEmpty" : "记录数为0", "sInfoFiltered" : "(全部记录数 _MAX_  条)", "sInfoPostFix" : "",
            "oPaginate": { "sFirst" : "第一页", "sPrevious" : " 上一页 ", "sNext" : " 下一页 ", "sLast" : " 最后一页 " }
        	};
    
    var oTableOpts = {
    		"sEcho": "1",
            "bFilter" : false,// 搜索栏
            "bLengthChange" : false,// 每行显示记录数
            "iDisplayLength": 20,
            "bServerSide": true,
            "bPaginate": false,
            "sAjaxDataProp": "rows",
            "sPaginationType": "bootstrap",
            "sDom": "Tflt<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
            "oTableTools": {
                "aButtons": []
            },
            language: { "search": "", "sSearch" : "搜索", "sUrl" : "", "sProcessing" : "正在加载数据...", "sLengthMenu" : "显示_MENU_条 ", "sZeroRecords" : "没有您要搜索的内容",
                "sInfo" : "从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条", "sInfoEmpty" : "记录数为0", "sInfoFiltered" : "(全部记录数 _MAX_  条)", "sInfoPostFix" : "",
                "oPaginate": { "sFirst" : "第一页", "sPrevious" : " 上一页 ", "sNext" : " 下一页 ", "sLast" : " 最后一页 " }
            	}
            ,
            "bProcessing": true,
            "bDestroy":false
    }
    
    function errorCallback(message) {
   		$('#modal-danger .modal-body').html(message);
   		$('#modal-danger-btn').click();
    }
    
    function modalInfo(message, width) {
    	$('#modal-info .modal-dialog').width(width != undefined ? width : 300);
    	$('#modal-info .modal-body').html('<textarea style="margin-left: 20px;width:' + ($('#modal-info .modal-dialog').width() - 50) + 'px;background: white;border: none;" readonly="readonly">' + message + '</textarea>');
   		$('#modal-info-btn').click();
    }
    
    var parseParam=function(param, key){
        var paramStr="";
       	for (var p in param) {
       		paramStr+="&"+p+"="+encodeURIComponent(param[p]);
       	}
        return paramStr.substr(1);
    };
    
    function submitHandler(validator, form, submitButton) {
    	Home.startLoadingContainer();
		$(form[0]).ajaxSubmit({
			success: function(data) {
				Home.callback(data);
				Home.stopLoadingContainer();
			}
		});
	}
    
    function changeResults(data, arg1, arg2, arg3) {
    	if (!data || data == null) {
    		if (!arg3) return null;
    		return [arg3];
    	}
    	var idField = arg1 ? arg1 : 'id',
    		textField = arg2 ? arg2 : 'text';
    	$.each(data, function(i) {
    		$.extend(data[i], {id: data[i][idField], text: data[i][textField]});
    	})
    	if (arg3) data.unshift(arg3);
    	return data;
    }
    
    var empty = {id: '', text: '=请选择='};
    
    /**       
     * 对Date的扩展，将 Date 转化为指定格式的String       
     * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
     * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)       
     * eg:       
     * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
     * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
     * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
     * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
     * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18       
     */          
    Date.prototype.pattern=function(fmt) {           
        var o = {           
        "M+" : this.getMonth()+1, //月份           
        "d+" : this.getDate(), //日           
        "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
        "H+" : this.getHours(), //小时           
        "m+" : this.getMinutes(), //分           
        "s+" : this.getSeconds(), //秒           
        "q+" : Math.floor((this.getMonth()+3)/3), //季度           
        "S" : this.getMilliseconds() //毫秒           
        };           
        var week = {           
        "0" : "/u65e5",           
        "1" : "/u4e00",           
        "2" : "/u4e8c",           
        "3" : "/u4e09",           
        "4" : "/u56db",           
        "5" : "/u4e94",           
        "6" : "/u516d"          
        };           
        if(/(y+)/.test(fmt)){           
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
        }           
        if(/(E+)/.test(fmt)){           
            fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
        }           
        for(var k in o){           
            if(new RegExp("("+ k +")").test(fmt)){           
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
            }           
        }           
        return fmt;           
    }
    
    var toUri = function(obj) {
	    var uri = '';
	    for (var key in obj) {
	    	uri += '&' + key + '=' + obj[key];
	    }
	    return uri;
    }
           
    $(function(){
    	//修改密码
    	$('#updatePassword').click(function() {
    		bootbox.dialog({
                message: $("#update-password-html").html(),
                title: "修改密码",
                className: "modal-darkorange",
                buttons: {
                    success: {
                        label: "修改",
                        className: "btn-blue",
                        callback: function () {
                        	$(".modal-body form").ajaxSubmit({
                        		beforeSubmit: function() {
                        			if ($.trim($('.modal-body input[name="oldPassword"]').val()) == '') {
                        				alert('请出入旧密码');
                        				return false;
                        			}
                        			if ($.trim($('.modal-body input[name="newPassword"]').val()) == '' || $.trim($('.modal-body input[name="newPassword"]').val()).length < 6) {
                        				alert('新密码不能为空，且必须大于等于6个字符');
                        				return false;
                        			}
                        			if ($('.modal-body input[name="newPassword"]').val() != $('.modal-body input[name="confirmPassword"]').val()) {
                        				alert('两次输入密码不一致');
                        				return false;
                        			}
                        		},
                        		success: function(data) {
                        			if (data != undefined && data != null && data != '') {
                						data = eval('('+data+')');
                						if (data.statusCode != undefined && data.statusCode == 300) {
                							Home.errorCallback(data.message);
                							return;
                						}
                					}
                        			$('.modal-header .bootbox-close-button').click();
                        			Home.successCallback('修改密码成功');
                        		}
                        	});
                        	return false;
                        }
                    },
                    "取消": {
                        className: "btn-danger",
                        callback: function () { }
                    }
                }
            });
    	});
    });