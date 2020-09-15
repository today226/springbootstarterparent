<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/jqGrid/ui.jqgrid.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/jqGrid/jquery-ui.css">

    <script type="text/javascript"          src="${pageContext.request.contextPath}/js/common/jQuery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript"          src="${pageContext.request.contextPath}/js/common/jQuery/jquery-ui.js"></script>
    <script type="text/javascript"          src="${pageContext.request.contextPath}/js/common/jqGrid/i18n/grid.locale-kr.js"></script>
    <script type="text/javascript"          src="${pageContext.request.contextPath}/js/common/jqGrid/jquery.jqGrid.min.js"></script>

    <style type="text/css">
        .ui-jqgrid .ui-jqgrid-htable th { height: 28px;}
    </style>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>게시판 샘플</title>

    <script type="text/javascript">
        $(function(){

            fn_SearchList(); //그리드 조회 호출
        });

        <%--  조회--%>
        function search() {
            var jsonObj = {};

            if($("#selectId").val() != "C") {
                jsonObj.serviceImplYn = $("#selectId").val();
            }

            alert(JSON.stringify(jsonObj));

            $("#jqGrid").setGridParam({
                datatype : "json",
                postData : {"param" : JSON.stringify(jsonObj)},
                loadComplete : function(data) {

                },

                gridComplete : function() {

                }
            }).trigger("reloadGrid");
        }


        출처: https://pjh3749.tistory.com/154 [JayTech의 기술 블로그]
        //getDialogArguments();
        var strSelectedRowId = "";
        //그리드 기본 옵션
        var gridOptions = {
            // 그리드 초기 조회조건 세팅
            postData : {},
            url : "<c:url value='/board/list.do' />",
            colModel: [
                {width:150,	        label: "게시판번호",		name:"id",				sortable:false,	align:"center",	key:true}
                , {width:150,	        label: "제목",		        name:"title",			sortable:false,	align:"center"}
                , {width:150,	        label: "게시물 내역",	    name:"content",		    sortable:false,	align:"center"}
                /*
                , {hidden:true,		                           name:"sggCd"}
                , {hidden:true,		                           name:"sysRegno"}
                , {hidden:true,		                           name:"hiredsmsSeqno"}
                */
            ],
            mtype:       "post",
            datatype:    "json",
            width:       530,
            height:      120,
            rowNum:      100,
            rowList:     [10,20,30],
            pager:       '#jqGridPager',
            rownumbers : true,
            viewrecords: false,
            loadonce:    false,
            shrinkToFit: false,
            autowidth:   true,
            onInitGrid : function(){
            },
            onSelectRow: function(rowId, selected){

                //var selData = $("#jqGridList").getRowData(rowId);
                //strSelectedRowId = rowId;
                //if(selData != null){
                //fn_SearchListSecond(selData);
                //}
            },
            ondblClickRow: function(rowId){
            },
            loadComplete: function(data){
                //fn_SearchListSecond();
            },
            afterInsertRow:function(rowId,rowData,rowelem){
                //  $("#jqGridList").setCell(rowId,'rnum','',{"background-color":"rgb(192,192,192)"});
            },
            jsonReader: {
                root: "result",
                repeatitems: false
            }
        };

    </script>
</head>
<body >


<div class="popup clr-gr-pop" >
    <h1>게시판</h1>
    <div class="popContent">
        <h2>jqGrid 개시판 샘플</h2>

        <div class="area-grid clr-gr mb-30" style="height:150px;">
            <!-- 그리드영역 -->
            <table id="jqGridList"></table>
            <div id="jqGridPager"></div>
        </div><!-- .area-grid -->
    </div>
</div><!-- .popup -->

</body>
</html>