<%@page import="org.com.location.model.solr.schema.WikiNews"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="org.com.location.*,java.util.List,java.util.Iterator,java.util.Set,java.util.Map,java.util.ArrayList,org.com.location.model.search.SearchResult,org.com.location.model.search.PreparedWikiNews" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%
    SearchResult searchResult = (SearchResult) request.getAttribute("results");
    List<PreparedWikiNews> preparedWikiNewsResults = searchResult.getPreparedWikiNewsResults();
    /*SearchResult searchResult = new SearchResult();
    List<PreparedWikiNews> preparedWikiNewsResults = new ArrayList<PreparedWikiNews>();
    for(int i=0;i<3;i++){
    	PreparedWikiNews p = new PreparedWikiNews();
    	p.setAuthor("author"+i);
    	p.setContent("content"+i);
    	p.setSummary("summary"+i);
    	p.setTitle("title"+i);
    	preparedWikiNewsResults.add(p);
    }*/
	String url = "http://localhost:8080";
    %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css"></style>
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
<!-- <script src="<%= url %>/mainpage_files/hideshow.js" type="text/javascript"></script> -->

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
<link rel="stylesheet" href="<%= url %>/mainpage_files/layout1.css">

<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<link rel="stylesheet" type="text/css" href="<%= url %>/mainpage_files/style.css">
<link rel="stylesheet" type="text/css"
	href="<%= url %>/mainpage_files/style(1).css">
</head>
<body>

	<div id="haut" style="height: 100%;">
		<div class="container" style="min-height: 100px;">
			<div style="margin: 0 auto; max-width: 800px; padding-top: 10px;">
				<div id="container">
					<form id="form" method="POST" name="searchForm">
						<div style="float: left; margin-left: -50px; margin-top: 25px;">
							<a href="<%= url %>/search"><img src="<%= url %>/mainpage_files/home.png"
								height="40px" width="40px" /></a>
						</div>
						<div id="box">
	                        <div id="input">
	                            <input type="text" name="search" id="search"> <a
	                                id="glass" href="#"></a>
	                        </div>
                   		</div>
						<div id="dropdownContain" style="padding-top: 5px;">
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
                        <div id="featureDiv" style="padding-top: 5px;margin-left:-80px">
                            <nav id="menu-wrap">

                                <ul id="menu">
                                    <%

                                        Map<String,List<String>> searchSuggestions = searchResult.getSearchSuggestions();
                                        Set<String> keys = searchSuggestions.keySet();

                                        Iterator<String> keyIterator = keys.iterator();
                                        while(keyIterator.hasNext()) {
                                            String key = keyIterator.next();
                                    %>
                                    <li><a href=""><%= key %></a>
                                        <ul>
                                            <%
                                                List<String> data = searchSuggestions.get(key);
                                                if(data != null){
                                                    Iterator<String> dataIterator = data.iterator();
                                                    while(dataIterator.hasNext()){
                                                        String dataValue = dataIterator.next();

                                                        String dataValueArray[] = dataValue.split("\\|");

                                                        int  distance = 0;
                                                        int value = 7;
                                                        if(Integer.parseInt(dataValueArray[2]) == 7){
                                                            value = 6;
                                                        } else if(Integer.parseInt(dataValueArray[2]) == 6){
                                                            distance = searchResult.getQueryFilters().getDistance();
                                                        }

                                                        String url1 = url+"/search/suggestions/"+dataValueArray[0]+"/"+value+"/"+distance+"/1";

                                            %>
                                            <li><a href=""><%= dataValueArray[1] %></a>
                                                <ul>
                                                    <li><a href="<%= url1 %>">Search Around</a></li>
                                                    <%
                                                        if(Integer.parseInt(dataValueArray[2]) <= 5 && Integer.parseInt(dataValueArray[2]) > 1)
                                                        {
                                                            String url2 = url+"/search/suggestions/"+dataValueArray[0]+"/"+dataValueArray[2]+"/"+distance+"/1";
                                                    %>
                                                    <li><a href="<%= url2 %>">Search Within</a></li>
                                                    <%     }

                                                    %>
                                                </ul>
                                            </li> <%
                                                }
                                            }
                                        %>
                                        </ul>
                                    </li><%
                                    }

                                %>

                                </ul>
                            </nav>

                        </div>


                    </form>
				</div>

			</div>

		</div>
		<div style="width:100%">
			<aside id="sidebar" class="column">
				<% 
				if(preparedWikiNewsResults.size() > 0 && preparedWikiNewsResults.get(0) != null ){
					PreparedWikiNews wikiNews = preparedWikiNewsResults.get(0);
				%>
				<div id="resultDisplayDiv1" style="cursor: hand;">
					<h3 id="resultDisplayTitleh31">
						<div id="resultDisplayTitle1" class="titleStyle"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getTitle())) %></div>
					</h3>
					<ul class="toggle">
						<li id="resultDisplayAuthor1" class="icn_new_article"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getAuthor())) %></li>
						<li id="resultDisplaySummary1" class="icn_edit_article"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getSummary())) %></li>
					</ul>
				</div>
				<%
				}
				if(preparedWikiNewsResults.size() > 1 && preparedWikiNewsResults.get(1) != null ){
					PreparedWikiNews wikiNews = preparedWikiNewsResults.get(1);
				%>
				<div id="resultDisplayDiv2" style="cursor: hand;">
					<h3 id="resultDisplayTitleh32">
						<div id="resultDisplayTitle2" class="titleStyle"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getTitle())) %></div>
					</h3>
					<ul class="toggle">
						<li class="resultDisplayAuthor2" style="text-align:left;"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getAuthor())) %></li>
						<li class="resultDisplaySummary2"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getSummary())) %></li>
					</ul>
				</div>
				<%
				}
				if(preparedWikiNewsResults.size() > 2 && preparedWikiNewsResults.get(2) != null ){
					PreparedWikiNews wikiNews = preparedWikiNewsResults.get(2);
				%>
				<div id="resultDisplayDiv3" style="cursor: hand;">
					<h3 id="resultDisplayTitleh33">
						<div id="resultDisplayTitle3" class="titleStyle"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getTitle())) %></div>
					</h3>
					<ul class="toggle">
						<li class="resultDisplayAuthor3"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getAuthor())) %></li>
						<li class="resultDisplaySummary3"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getSummary())) %></li>
					</ul>
				</div>
				<%
				}
				if(preparedWikiNewsResults.size() > 3 && preparedWikiNewsResults.get(3) != null ){
					PreparedWikiNews wikiNews = preparedWikiNewsResults.get(3);
				%>
				<div id="resultDisplayDiv4" style="cursor: hand;">
					<h3 id="resultDisplayTitleh34">
						<div id="resultDisplayTitle4" class="titleStyle"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getTitle())) %></div>
					</h3>
					<ul class="toggle">
						<li class="resultDisplayAuthor4"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getAuthor())) %></li>
						<li class="resultDisplaySummary4"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getSummary())) %></li>
					</ul>
				</div>
				<%
				}
				if(preparedWikiNewsResults.size() > 4 && preparedWikiNewsResults.get(4) != null ){
					PreparedWikiNews wikiNews = preparedWikiNewsResults.get(4);
				%>
				<div id="resultDisplayDiv5" style="cursor: hand;">
					<h3 id="resultDisplayTitleh35">
						<div id="resultDisplayTitle5" class="titleStyle"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getTitle())) %></div>
					</h3>
					<ul class="toggle">
						<li class="resultDisplayAuthor5"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getAuthor())) %></li>
						<li class="resultDisplaySummary5"><%= StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(wikiNews.getSummary())) %></li>
					</ul>
				</div>
				<%
				}
				%>
				<div style="text-align: center; padding-top: 10px;">
					<div id="previousPageDiv"><a id="previousPageLink">Previous Page</a></div>
					<div id="nextPageDiv"><a id="nextPageLink">Next Page</a></div>
					<div id="resultCountDiv">Showing 5 out of 5</div>
					<div id="queryTime">Results gathered in 10 sec</div>
				</div>
			</aside>
			<!-- end of sidebar -->
			<section id="main" class="column">

				<!--<h4 class="alert_info">Welcome to the free MediaLoot admin panel template, this could be an informative message.</h4> -->

				<article class="module width_full">
					<header>
						<h3>
							<div id="newsArticleTitle" style="margin-top: -8px"
								class="titleStyle"></div>
						</h3>
					</header>
					<div class="module_content">
						<p></p>
						<p id="newsArticle" style="text-align: justify;">
							
						</p>
					</div>
				</article>
				<!-- end of styles article -->
				<div class="spacer"></div>
			</section>
		</div>
	</div>

	<script type='text/javascript'>
		function changeToDefault() {
			for ( var i = 1; i <= 5; i++) {
				$("#resultDisplayDiv" + i).css('background', '#FFFFFF');
				$("#resultDisplayDiv" + i).css('color', '#000000');
			}
		}

		function changeToSelected(divId) {
			$("#" + divId).css('background', '#2E9AFE');
			$("#" + divId).css('color', '#FFFFFF');
		}

		$(document).ready(function() {
			var currentUrl = window.location.pathname;
			var newUrl = location.href.substring(0, location.href.lastIndexOf("/")+1);
			var totalResultCount = <%= searchResult.getResultCount() %>;
			var queryTime = <%= searchResult.getQueryTime() %>;
			var currentPage = <%= searchResult.getCurrentPage() %>;
			

			$("#queryTime").text("Results gathered in "+ (queryTime) + " secs");
			var totalPages = Math.ceil(totalResultCount/5);
            $("#resultCountDiv").text("Page "+ currentPage +" of total "+ totalPages+" pages");
			if(currentPage > 1){
				$("#previousPageDiv").show();
				$("#previousPageLink").attr('href', newUrl+(currentPage-1));
			} else {
				$("#previousPageDiv").hide();
			}
			if(currentPage < totalPages){
				$("#nextPageDiv").show();
				$("#nextPageLink").attr('href', newUrl+(currentPage+1));
            }
            else {
				$("#nextPageLink").hide();
			}
			
			//$("#resultDisplayDiv1").hide();
			//$("#resultDisplayDiv2").hide();
			//$("#resultDisplayDiv3").hide();
			//$("#resultDisplayDiv4").hide();
			//$("#resultDisplayDiv5").hide();
			changeToDefault();
			//$("#resultDisplayDiv1").css('background','#2E9AFE');
			//$("#resultDisplayDiv1").css('color','#FFFFFF');

			changeToSelected("resultDisplayDiv1");
			var articleData = new Array();
			<%
				for(int i=0;i<preparedWikiNewsResults.size();i++){
					if(preparedWikiNewsResults.get(i) != null) {
			%>
					articleData[<%= (i) %>] = "<%=StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJava(preparedWikiNewsResults.get(i).getContent())) %>";
					<% }
				}
			%>
			$("#newsArticleTitle").text($("#resultDisplayTitle1").text());
			$("#newsArticle").text(articleData[0]);
			
			$("#resultDisplayDiv1").click(function() {
				changeToDefault();
				changeToSelected("resultDisplayDiv1");
				$("#newsArticleTitle").text($("#resultDisplayTitle1").text());
				$("#newsArticle").text(articleData[0]);
			});
			$("#resultDisplayDiv2").click(function() {
				changeToDefault();
				changeToSelected("resultDisplayDiv2");
				$("#newsArticleTitle").text($("#resultDisplayTitle2").text());
				$("#newsArticle").text(articleData[1]);
			});
			$("#resultDisplayDiv3").click(function() {
				changeToDefault();
				changeToSelected("resultDisplayDiv3");
				$("#newsArticleTitle").text($("#resultDisplayTitle3").text());
				$("#newsArticle").text(articleData[2]);
			});
			$("#resultDisplayDiv4").click(function() {
				changeToDefault();
				changeToSelected("resultDisplayDiv4");
				$("#newsArticleTitle").text($("#resultDisplayTitle4").text());
				$("#newsArticle").text(articleData[3]);
			});
			$("#resultDisplayDiv5").click(function() {
				changeToDefault();
				changeToSelected("resultDisplayDiv5");
				$("#newsArticleTitle").text($("#resultDisplayTitle5").text());
				$("#newsArticle").text(articleData[4]);
			});
		});

		$(document)
        .ready(
        function() {
        	var url = "<%= url %>/search/";
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