<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, edu.stanford.nlp.trees.Tree, net.didion.jwnl.data.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dietfix Test Result</title>
</head>
<body>
	<h1 align="center">Result</h1>
	<table align="center" border="1" bordercolor="black" style="width: 500px; border-collapse: collapse; 
	border-spacing: 5px">
		<tr>
			<td>Token</td>
			<td>Lemma</td>
			<td>Tag</td>
		</tr>
		<%
			ArrayList<String> tag = (ArrayList<String>)request.getAttribute("tags");
			ArrayList<String> token = (ArrayList<String>)request.getAttribute("tokens");
			ArrayList<String> lemma = (ArrayList<String>)request.getAttribute("lemma");
			
			if(!tag.isEmpty()) {
				for(int i = 0; i < tag.size(); i++) {
					out.print("<tr>");
					out.print("<td>" + token.get(i) + "</td>");
					out.print("<td>" + lemma.get(i) + "</td>");
					out.print("<td>" + tag.get(i) + "</td>");
					out.print("</tr>");
				}
			}
			
		%>
	</table>
	<%
		String path = request.getAttribute("path").toString();
		try{
			Tree parseTree = (Tree)request.getAttribute("tree");
			
			if(!parseTree.isEmpty()) {
				out.print(parseTree.pennString());
			}
				
		}catch(Exception e) {
			out.print("<p>" + e.toString() + "</p>");
		}
		
		out.print("<p>" + path + "</p>");
		
		Synset[] set = (Synset[])request.getAttribute("word");
		
		out.print("<br/>" + set[0].getGloss());
		/*
		ArrayList<ArrayList<String>> synonyms = new ArrayList<ArrayList<String>>();
		synonyms = (ArrayList<ArrayList<String>>)request.getAttribute("synonyms");
		
		Iterator<ArrayList<String>> i = synonyms.iterator();
		
		while(i.hasNext()) {
			ArrayList<String> subSynonym = new ArrayList<String>();
			subSynonym = (ArrayList<String>)i.next();
			
			Iterator<String> i2 = subSynonym.iterator();
			
			while(i2.hasNext()) {
				out.print( "<br/>" + i2.next() + "<br/>" );
			}
		}*/
	%>
	<a href="index.html">Click me!</a>
</body>
</html>