var gridOptions = {
	url : '/Devch/jqGrid/actiontwo.do',
	mtype: "post",
	datatype: "json",
	colModel: [
	           {label: 'CODE_ID', name:'CODE_ID',key:true, widh:75},
	           {label: 'CODE_ID_NM', name:'CODE_ID_NM', widh:130},
	           {label: 'CODE_ID_DC', name:'CODE_ID_DC', widh:150},
	           {label: 'CL_CODE', name:'CL_CODE', widh:45}
	           ],
	viewrecords: true,
	width:780,
	height: 250,
	rowNum: 10,
	rowList:[10,20,30],
	pager: "#jqGridPager",
	caption:"Master Grid",
	sortname: "sidx",
	sortorder: "sord",
	loadonce:false,
	onSelectRow: function(rowid, selected){
		if(rowid != null){
			jQuery("#jqGridDetails").jqGrid('setGridParam',{url:"/Devch/jqGrid/actionthree.do?rowid="+rowid, page:1, datatype:"json"});
			jQuery("#jqGridDetails").jqGrid('setCaption', 'Detail Grid::'+rowid);
			jQuery("#jqGridDetails").trigger("reloadGrid");
		}
	},
	jsonReader: {
		root: "rows",
		page: "page",
		records: "records",
		total: "total",
		repeatitems: "false"
	},
	postData:{
		test: function() {
				return $("#searchCodeNm").val();
		} 
	}
};

$(document).ready(function(){
	
	$("#actiontwo").click(function(){
		alert("actiontwo clicked123");
		getField2();
	});
	
	function getField2(){
		jQuery(gridOptions).attr('rowNum', jQuery('option:selected[role="option"]').val());
		jQuery("#jqGrid").jqGrid('setGridParam', gridOptions);
		jQuery("#jqGrid").trigger("reloadGrid");
	}
	
	function getField(){
		
		$("#jqGrid").jqGrid(gridOptions).navGrid('#jqGridPager', {search:true, edit:false, add:false, del:false, searchtext:"search"}) ;
		
	};
	
	$("#jqGridDetails").jqGrid({
		url:'empty.json',
		mtype: "GET",
		datatype:"json",
		page:1,
		colModel:[
		          {label: 'FRST_REGIST_PNTTM', name:'FRST_REGIST_PNTTM', widt:75},
		          {label: 'FRST_REGISTER_ID', name:'FRST_REGISTER_ID', widt:130},
		          {label: 'LAST_UPDT_PNTTM', name:'LAST_UPDT_PNTTM', widt:150},
		          {label: 'LAST_UPDUSR_ID', name:'LAST_UPDUSR_ID', widt:45}
		          ],
	    width:780,
	    rowNum:5,
	    loadonce:true,
	    height: '100',
	    viewrecords:true,
	    caption:'Detail grid::',
	    pager:'#jqGridDetailsPager',
	    jsonReader: {
			root: "rows",
			page: "page",
			records: "records",
			total: "total",
			repeatitems: "false"
		}
		
	});
	
	function clearSelection(){
		jQuery("#jqGridDetails").jqGrid('setGridParam', {url:"empty.json", datatype:'json'});
		jQuery("#jqGridDetails").jqGrid('setCaption', 'Detail Grid:: none');
		jQuery("#jqGridDetails").trigger("reloadGrid");
	}

	getField();
});

