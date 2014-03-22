<%--
  Created by IntelliJ IDEA.
  User: ravioactive
  Date: 11/27/13
  Time: 2:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String url = "http://localhost:8080"; %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css"></style>

    ﻿﻿ ﻿﻿﻿
    <meta charset="utf-8">
    <title>Location Based News Search</title>
    <!-- Include jQuery -->
    <script src="<%= url %>/mainpage_files/jquery.min.js" type="text/javascript"></script>
    <link href="<%= url %>/mainpage_files/jquery-ui.css" rel="stylesheet"
          type="text/css">
    <script src="<%= url %>/mainpage_files/jquery-ui.min.js"></script>

    <script type="text/javascript" src="<%= url %>/mainpage_files/jquery.noty.js"></script>
    <script src="<%= url %>/mainpage_files/inline.js"></script>
    <script src="<%= url %>/mainpage_files/top.js"></script>
    <script src="<%= url %>/mainpage_files/topCenter.js"></script>
    <script type="text/javascript" src="<%= url %>/mainpage_files/default.js"></script>
    <script src="<%= url %>/mainpage_files/jquery.pageslide.js"
            type="text/javascript"></script>
    <div id="pageslide" style="display: none;"></div>
    <script src="<%= url %>/mainpage_files/jquery.stellar.js" type="text/javascript"></script>

    <link href="<%= url %>/mainpage_files/jquery.pageslide.css" media="screen"
          rel="stylesheet" type="text/css">
    <!-- Mobile Specific Metas
            ================================================== -->
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- CSS
            ================================================== -->
    <link rel="stylesheet" href="<%= url %>/mainpage_files/base.css">
    <link rel="stylesheet" href="<%= url %>/mainpage_files/skeleton.css">
    <link rel="stylesheet" href="<%= url %>/mainpage_files/layout.css">
    <%--<link href="<%= url %>/mainpage_files/css" rel="stylesheet" type="text/css">--%>
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%= url %>/mainpage_files/style.css">
    <link rel="stylesheet" type="text/css"
          href="<%= url %>/mainpage_files/style(1).css">
</head>
<body>
<div id="haut" style="height: 100%;">
    <div class="container" style="height: 80%;">
        <div id="space"></div>
        <div
                style="width: 120%; margin-bottom: -10px; height: 110px; margin-bottom: 25px; padding-top: 5px; padding-bottom: 30px; margin-top: -20px;"
                class="choix">
            <img src="<%= url %>/mainpage_files/news.gif" class="logoChoix" id="7"
                 style="opacity: 1; display: inline; margin-top: 0px; width: 22%; margin-left: 38%;">
        </div>

        <div style="margin: 0 auto; max-width: 800px; padding-top: 80px;">
            <div id="container">
                <form id="form" method="GET" name="searchForm" action="">
                    <div id="box">
                        <div id="input">
                            <input type="text" name="search" id="search"> <a
                                id="glass" href="#"></a>
                        </div>
                    </div>
                    <div id="dropdownContain" style="padding-top: 20px;">
                        <input id="advancedOptionValue" type="text" value="unchecked"
                               style="display: none;" />
                        <div class="demoClass">
                            <div style="color: #0000FF; font-weight: bold; height: 20px">
                                <img
                                        style="height: 70%; float: left; padding-top: 4px; cursor: hand;"
                                        src="<%= url %>/mainpage_files/down_arrow.gif" />
                                <div style="height: 100%; cursor: hand;">Advanced Search</div>
                            </div>
                        </div>
                        <div id="advancedSearchDiv"
                             style="padding-left: 10px; padding-top: 10px;">
                            <div style="float: left; padding-top: 3px; height: 30px">Location:</div>
                            <div style="padding-left: 80px;">
                                <input type="text" name="location" id="location">
                            </div>
                            <div style="float: left; padding-top: 3px; height: 30px">Key
                                Words:</div>
                            <div style="padding-left: 80px;">
                                <input type="text" name="keyWords" id="keyWords">
                            </div>
                            <div style="float: left; padding-top: 3px; height: 30px">Distance:</div>
                            <div style="padding-left: 80px;">
                                <input type="text" name="distance" id="distance">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript'>
    $(document)
            .ready(
            function() {
            	//var url = window.location.href+"/";
	            
                var downSrc = "<%= url %>/mainpage_files/down_arrow.gif";
                var upSrc = "<%= url %>/mainpage_files/UpArrow.png";
                $("#advancedSearchDiv").hide();
                var isChecked = $("#advancedOptionValue").val("unchecked");
                //$("#advancedSearch").prop('checked', false);
                $("#location").val("");
                $("#keyWords").val("");
                $("#distance").val("");
                $("#glass")
                        .on('click',
                        		function(event) {
							//event.preventDefault();
							var url = "<%= url %>/search/";
							var query = $("#search")
									.val();
							var isChecked = $(
									"#advancedOptionValue")
									.val();
							if (isChecked == "checked") {
								if(isNaN($("#distance").val())){
									alert("please enter numerical value in distance");
								} else if($("#keyWords")
										.val() == "") {
									alert("please enter keywords value");
								} else if($("#location")
										.val() == "") {
									alert("please enter location value");
								} else {
									url += "advanced/";
									url += $("#location")
													.val()
											+"/"+$("#keyWords")
													.val()
											+"/"+$("#distance")
													.val()+"/1";
									window.location.href = url;
								}
								
							} else {
								if(query == ""){
									alert("Enter query to search");
								} else {
									url+=query+"/1";
									window.location.href = url;
									//alert(url);
								}
							}
							//alert(url);
							});


                $(".demoClass").click(
                        function() {
                            var isChecked = $(
                                    "#advancedOptionValue").val();
                            if (isChecked == "unchecked") {
                                $("#advancedSearchDiv").show();
                                $("#advancedOptionValue").val(
                                        "checked");
                                $('img[src="' + downSrc + '"]')
                                        .attr('src', upSrc);
                                $("#search").attr("disabled", "disabled");
                            } else {
                                $("#advancedSearchDiv").hide();
                                $("#advancedOptionValue").val(
                                        "unchecked");
                                $('img[src="' + upSrc + '"]').attr(
                                        'src', downSrc);
                                $("#search").removeAttr("disabled");
                            }
                        });
            });
</script>
</body>
</html>