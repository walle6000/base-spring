$(document).ready(function() {
	$( "#button_login" ).button();
	
	$( "#button_login" ).click(function(){
		if(!$(" #userName").val()||!$(" #password").val()){
		$( "#dialog" ).dialog( "open" );
		event.preventDefault();
		}
	});
		
	$( "#dialog" ).dialog({
		autoOpen: false,
		width: 400,
		buttons: [
			{
				text: "确定",
				click: function() {
					$( this ).dialog( "close" );
				}
			},
			{
				text: "取消",
				click: function() {
					$( this ).dialog( "close" );
				}
			}
		]
	});
	
	jQuery.extend(jQuery.jgrid,{ parse:function(jsstring) { return JSON.parse(jsstring); } }); 
	
	
	jQuery("#list1").jqGrid({
	   	url:'agent.do?method=famload',
		datatype: "json",
		mtype: 'GET',
	   	colNames:['编号','主题', /*'级别',*/ '日期'/*, '员工','接收人','状态'*/],
	   	colModel:[
	   		{name:'transactionId',index:'transactionId', width:55, align:"center",sortable:false},
	   		/*{name:'transactionName',index:'transactionName', width:180,sortable:false},*/
	   		{name:'transactionSubject',index:'transactionSubject', width:180,sortable:false},
	   		/*{name:'level',index:'level', width:50, align:"center"},*/
	   		{name:'transactionDate',index:'transactionDate', width:100, align:"center"}
	   		/*{name:'staffName',index:'staffName', width:80, align:"center"},		
	   		{name:'recipient',index:'recipient', width:80,align:"center"},		
	   		{name:'status',index:'status', width:90, align:"center"}*/	
	   	],
	   	
	 	rownumbers : false,
		rowNum : 10,
		rowList : [ 10, 15, 20],
		pager : "#pager1",
		autowidth : true,
		width : "100%",
		height : "auto",
		multiselect : false,
		hidegrid : true,
		viewrecords : true,
		jsonReader : {
			root : "rows",
			page : "page",
			total : "total",
			records : "records",
			repeatitems : false
		}
	});
	jQuery("#list1").jqGrid('navGrid','#pager1',{view:true,edit:false,add:false,del:false,search:false});
	
	/*jQuery("#list2").jqGrid({
	   	url:'agent.do?method=famload',
		datatype: "json",
		mtype: 'GET',
	   	colNames:['编号','主题', '级别', '日期', '员工','接收人','状态'],
	   	colModel:[
	   		{name:'transactionId',index:'transactionId', width:55, align:"center",sortable:false},
	   		{name:'transactionName',index:'transactionName', width:180,sortable:false},
	   		{name:'level',index:'level', width:50, align:"center"},
	   		{name:'transactionDate',index:'transactionDate', width:100, align:"center"}
	   		{name:'staffName',index:'staffName', width:80, align:"center"},		
	   		{name:'recipient',index:'recipient', width:80,align:"center"},		
	   		{name:'status',index:'status', width:90, align:"center"}	
	   	],
	   	
	 	rownumbers : false,
		rowNum : 10,
		rowList : [ 10, 15, 20],
		pager : "#pager2",
		autowidth : true,
		width : "100%",
		height : "auto",
		multiselect : false,
		hidegrid : true,
		viewrecords : true,
		jsonReader : {
			root : "rows",
			page : "page",
			total : "total",
			records : "records",
			repeatitems : false
		}
	});
	jQuery("#list2").jqGrid('navGrid','#pager2',{view:true,edit:false,add:false,del:false,search:false});
	
	
	
	jQuery("#list3").jqGrid({
	   	url:'agent.do?method=famload',
		datatype: "json",
		mtype: 'GET',
	   	colNames:['编号','主题', '级别', '日期', '员工','接收人','状态'],
	   	colModel:[
	   		{name:'transactionId',index:'transactionId', width:55, align:"center",sortable:false},
	   		{name:'transactionName',index:'transactionName', width:180,sortable:false},
	   		{name:'level',index:'level', width:50, align:"center"},
	   		{name:'transactionDate',index:'transactionDate', width:100, align:"center"}
	   		{name:'staffName',index:'staffName', width:80, align:"center"},		
	   		{name:'recipient',index:'recipient', width:80,align:"center"},		
	   		{name:'status',index:'status', width:90, align:"center"}	
	   	],
	   	
	 	rownumbers : false,
		rowNum : 10,
		rowList : [ 10, 15, 20],
		pager : "#pager3",
		autowidth : true,
		width : "100%",
		height : "auto",
		multiselect : false,
		hidegrid : true,
		viewrecords : true,
		jsonReader : {
			root : "rows",
			page : "page",
			total : "total",
			records : "records",
			repeatitems : false
		}
	});
	jQuery("#list3").jqGrid('navGrid','#pager3',{view:true,edit:false,add:false,del:false,search:false});*/
	
	i=0;
	while($("#dynamic_list_"+i).attr("id")){
		//alert("The "+i + "object is" + $("#dynamic_list_"+i).attr("id"));
		var type = $("#dynamic_list_"+i).attr("title");
		jQuery("#dynamic_list_"+i).jqGrid({
		   	url:'transactions.do?method=transactionLoad&type='+type,
			datatype: "json",
			mtype: 'GET',
		   	colNames:['编号','主题', /*'级别',*/ '日期'/*, '员工','接收人','状态'*/],
		   	colModel:[
		   		{name:'transactionId',index:'transactionId', width:55, align:"center",sortable:false},
		   		{name:'transactionSubject',index:'transactionSubject', width:180,sortable:false},
		   		/*{name:'level',index:'level', width:50, align:"center"},*/
		   		{name:'transactionDate',index:'transactionDate', width:100, align:"center"}
		   		/*{name:'staffName',index:'staffName', width:80, align:"center"},		
		   		{name:'recipient',index:'recipient', width:80,align:"center"},		
		   		{name:'status',index:'status', width:90, align:"center"}*/	
		   	],
		   	
		 	rownumbers : false,
			rowNum : 10,
			rowList : [ 10, 15, 20],
			pager : "#dynamic_pager_"+i,
			autowidth : true,
			width : "100%",
			height : "auto",
			multiselect : false,
			hidegrid : true,
			viewrecords : true,
			jsonReader : {
				root : "rows",
				page : "page",
				total : "total",
				records : "records",
				repeatitems : false
			}
		});
		jQuery("#dynamic_list_"+i).jqGrid('navGrid','#dynamic_pager_'+i,{view:true,edit:false,add:false,del:false,search:false});
		i++;
	}
	
	$( "#accordion" ).accordion({
      heightStyle: "content",
      collapsible: false
    });
	
	
}); 