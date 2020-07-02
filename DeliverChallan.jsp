<%@page import="java.sql.*,java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SCN Stock || Delivery</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta name="keywords" content="SCN Stock Quality Master" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!--// css -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!-- font -->
<link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<!-- //font -->
<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/bootstrap.js"></script>
</head>
<body onload="window.print()">
	
<%
	List<List> deliveryCart = new ArrayList<List>();
    if (request.getSession().getAttribute("deliveryCart") != null) {
         deliveryCart = (ArrayList) request.getSession().getAttribute("deliveryCart");
    }
    Map<String,List> map = new  HashMap<>();
    if (request.getSession().getAttribute("balnobaltypepair") != null) {
         map = (HashMap) request.getSession().getAttribute("balnobaltypepair");
    }
    List<String> deliverdetails = new ArrayList<>();
    deliverdetails = (ArrayList) request.getSession().getAttribute("deliverdetails");
%>
<br>

		<div class="container">
		<h4 class="card-title text-left">Order No : <%=deliverdetails.get(1)%></h4>	
		<h4 class="card-title text-right">Date : <%=session.getAttribute("Date").toString()%></h4>	
		<h3 class="card-title text-center"><u>Delivery Order</u><br><BR>GORAMAL BALMOKAND<br>GSTN : 19AEWPK1394P1ZE</h3>

<%
String t_from = deliverdetails.get(2).toString();
String[] t_f_l = t_from.split("//");
String t_to = deliverdetails.get(3).toString();
String[] t_to_l = t_to.split("//");
%>
<br>
<form name="frm" method="GET" action="deliver">
<div class="form-row">
  	<div class="form-group col-md-4">
      <CENTER><b>FROM GODOWN</b></CENTER>
      <%=t_f_l[0]%><br>
      <%=t_f_l[1]%>
    </div>
    <div class="form-group col-md-4">
    	
    </div>
    <div class="form-group col-md-4">
      <CENTER><b>TO SHOP</b></CENTER>
      <%=t_to_l[0]%><br>
      <%=t_to_l[1]%>
    </div>
</div>
<br>
<div class="row justify-content-md-center">
<b>Please Deliver <%=deliverdetails.get(4)%> Bales to our Mutia <%=deliverdetails.get(5)%> as per details given below</b>
</div>


<br>
<div class="container" style="max-width: 70%;"> 
<div class="table-responsive">
<table class = "table" border="1" width=100%>
  <thead class="thead-dark">
    <tr>
      <th scope="col">Sl.</th>
      <th scope="col">Bal No.</th>
      <th scope="col">Consigner</th>
      <th scope="col">Quality</th>
      <th scope="col">HSN</th>
      <th scope="col">GST</th>
      <th scope="col">PCS</th>
      <th scope="col">Rate</th>
      <th scope="col">Amount</th>
    </tr>
  </thead>
<%
int i = 0;
int pcs=0;
float cgstValue=0;
float sgstValue=0;
float t_amount=0;
float total=0;
float r_off=0;
Map<String,List> bal_cost=new HashMap<>();
String strtaxiable_amount ="";
String strt_amount = "";
String strcgstValue ="";
String strsgstValue ="";
%>
<tbody>
	<%for(List row:deliveryCart)
		{
			i++;
		%>
		<tr>
			<tr>
			<td><font color="black"><%=i%></font></td>
			<td><font color="black"><%=row.get(2)%></font></td>
			<td><font color="black"><%=row.get(1)%></font></td>
			
			<td><font color="black"><%=row.get(3)%></font></td>
			<td><font color="black"><%=row.get(4)%></font></td>
			<%
			Float gst = Float.parseFloat(row.get(5).toString())+Float.parseFloat(row.get(6).toString());
			%>
			<td><font color="black"><%=gst%> %</font></td>
			<td><font color="black"><%=row.get(7)%></font></td>
			<td><font color="black">Rs. <%=row.get(8)%></font></td>
			<%
			pcs+=Integer.parseInt(row.get(7).toString());
			Float taxiable_amount=Float.parseFloat(row.get(7).toString())*Float.parseFloat(row.get(8).toString());
			Float total_tax =  taxiable_amount*gst/100;
			float total_amount = taxiable_amount+total_tax;
			t_amount+=taxiable_amount;
			total+=total_amount;
			cgstValue += taxiable_amount*Float.parseFloat(row.get(5).toString())/100;
			sgstValue += taxiable_amount*Float.parseFloat(row.get(6).toString())/100;
			strtaxiable_amount = String.format("%.2f", taxiable_amount);  
			%>
			<td><font color="black">Rs. <%=strtaxiable_amount%></font></td>
		</tr>
		<%
		List<Float> cost = new ArrayList<>();
		Float t_a = Float.parseFloat(strtaxiable_amount);
		Float cg = Float.parseFloat(row.get(5).toString());
		Float sg = Float.parseFloat(row.get(6).toString());
		cost.add(t_a);
		cost.add(cg);
		cost.add(sg);
		bal_cost.put(row.get(2).toString(),cost);
		%>
	<%}%>
	<tr>
		<%
		strt_amount = String.format("%.2f", t_amount);
		%>
		<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
		<td><font color="black">Total: </font></td><td>&nbsp;</td><td>&nbsp;</td>
		<td><font color="black"><%=pcs%></font></td><td>&nbsp;</td>
		<td><font color="black">Rs. <%=strt_amount%></font></td>
	</tr>
	<tr>
		<%
		strcgstValue = String.format("%.2f", cgstValue);
		%>
		<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
		<td><font color="black">CGST:</font></td><td><font color="black">Rs. <%=strcgstValue%></font></td>
	</tr>
	<tr>
		<%
		strsgstValue = String.format("%.2f", sgstValue);
		%>
		<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
		<td><font color="black">SGST:</font></td><td><font color="black">Rs. <%=strsgstValue%></font></td>
	</tr>
	<%
		float totalValue=t_amount+sgstValue+cgstValue;
		float netTotal = Math.round(totalValue);
		r_off = netTotal-totalValue;
		String sign;
		if(r_off>0)
			sign="+";
		else
			sign="-";
	%>
	<tr>
		<%
		String strr_off = String.format("%.2f", Math.abs(r_off));
		%>
		<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
		<td><font color="black">R/OFF:</font></td><td><font color="black"><%=sign %><%=strr_off%></font></td>
	</tr>
	<tr>
		<%
		String strnetTotal = String.format("%.2f", Math.abs(netTotal));
		%>
		<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
		<td><font color="black">Net Total: </font></td><td>&nbsp;</td><td>&nbsp;</td>
		<td>&nbsp;</td><td>&nbsp;</td>
		<td><font color="black">Rs. <%=strnetTotal%></font></td>
	</tr>
</tbody>
</table>
</div>
</div>
<br><br>
<div class="container"> 
<div class="table-responsive">
<table class = "table" border="1">
  <thead class="thead-dark">
    <tr>
      <th scope="col">HSN</th>
      <th scope="col">No. Of Bales/Cartoons</th>
      <th scope="col">Taxiable Value</th>
      <th scope="col">CGST</th>
      <th scope="col">CGST Value</th>
      <th scope="col">SGST</th>
      <th scope="col">SGST Value</th>
      <th scope="col">Total Tax Amount</th>
    </tr>
  </thead>
<%
int t_bales=0;
int t_cartoons = 0;
String type="";
Map<String,List> map2=new HashMap();
for(List row2:deliveryCart)
{
	List<List> val  = new ArrayList<>();
	List<String> col;
	String hsn = row2.get(4).toString();
	col = new ArrayList<>();
	for(List row:deliveryCart)
	{
		if(hsn.equals(row.get(4).toString()))
		{
			String bal_no = row.get(2).toString();
			if((map.get(bal_no)).get(0).toString().equals("Bale"))
			{
				t_bales++;
			}
			else
			{
				t_cartoons++;
			}
			col.add(bal_no);
		}
	}
	val.add(col);
	col=new ArrayList<>();
	Integer x = new Integer(t_bales);
	col.add(x.toString());
	Integer y = new Integer(t_cartoons);
	col.add(y.toString());
	val.add(col);
	map2.put(hsn,val);
	t_bales = 0;
	t_cartoons = 0;
}
%>
<%
	Set<String> keys = map2.keySet();
	float baleCgst = 0;
	float baleSgst = 0;
	float cartoonCgst = 0;
	float cartoonSgst = 0;
	float baleC=0;
	float baleS=0;
	float cartoonC=0;
	float cartoonS=0;
%>
<tbody>
	<%for(String key:keys)
		{
			int t=-1;
			List<String> x = (ArrayList)(map2.get(key)).get(1);
			int b=Integer.parseInt(x.get(0));
			int c=Integer.parseInt(x.get(1));
			List bal_no = (ArrayList)(map2.get(key)).get(0);
			if(b>0)
				t=1;
			if(c>0)
				t=0;
			float baleValue = 0;
			float cartoonValue = 0;
			for(Object z:bal_no)
			{
				if((map.get(z)).get(0).toString().equals("Bale"))
				{
					baleValue += ((Float)(bal_cost.get(z)).get(0)).floatValue();
					float cgst=((Float)(bal_cost.get(z)).get(1)).floatValue();
					float sgst = ((Float)(bal_cost.get(z)).get(2)).floatValue();
					baleCgst = baleValue*cgst/100;
					baleSgst = baleValue*sgst/100;
					baleC = cgst;
					baleS = sgst;
				}
				else
				{
					cartoonValue += ((Float)(bal_cost.get(z)).get(0)).floatValue();
					float cgst=((Float)(bal_cost.get(z)).get(1)).floatValue();
					float sgst = ((Float)(bal_cost.get(z)).get(2)).floatValue();
					cartoonCgst = cartoonValue*cgst/100;
					cartoonSgst = cartoonValue*sgst/100;
					cartoonC = cgst;
					cartoonS = sgst;
				}
			}
		String y = bal_no.get(0).toString();
		if(b>0&&c>0)
		{%>
			<tr>
			<td><font color="black"><%=key%></font></td>
			<td><font color="black"><%=x.get(0)%> Bales</font></td>
			<%
			String strbaleValue = String.format("%.2f", baleValue);
			String strbaleCgst = String.format("%.2f", baleCgst);
			String strbaleSgst = String.format("%.2f", baleSgst);
			String strtotalb = String.format("%.2f", baleCgst+baleSgst);
			%>
			<td><font color="black"><%=strbaleValue%></font></td>
			
			<td><font color="black"><%=baleC%> %</font></td>
			<td><font color="black"><%=strbaleCgst%></font></td>
			<td><font color="black"><%=baleS%> %</font></td>
			<td><font color="black"><%=strbaleSgst%></font></td>
			
			<td><font color="black"><%=strtotalb%></font></td>
			</tr>
			<tr>
			<td><font color="black"><%=key%></font></td>
			<td><font color="black"><%=x.get(1)%> Cartoons</font></td>
			<%
			String strcartoonValue = String.format("%.2f", cartoonValue);
			String strcartoonCgst = String.format("%.2f", cartoonCgst);
			String strcartoonSgst = String.format("%.2f", cartoonSgst);
			String strtotalc = String.format("%.2f", cartoonCgst+cartoonSgst);
			%>
			<td><font color="black"><%=strcartoonValue%></font></td>
			<td><font color="black"><%=cartoonC%> %</font></td>
			<td><font color="black"><%=strcartoonCgst%></font></td>
			<td><font color="black"><%=cartoonS%> %</font></td>
			<td><font color="black"><%=strcartoonSgst%></font></td>
			<td><font color="black"><%=strtotalc%></font></td>
			</tr>
		<%}
		else
		{
			if(t==1)
			{%>
			<tr>
			<td><font color="black"><%=key%></font></td>
			<td><font color="black"><%=x.get(0)%>Bales</font></td>
			<%
			String strbaleValue = String.format("%.2f", baleValue);
			String strbaleCgst = String.format("%.2f", baleCgst);
			String strbaleSgst = String.format("%.2f", baleSgst);
			String strtotalb = String.format("%.2f", baleCgst+baleSgst);
			%>
			<td><font color="black"><%=strbaleValue%></font></td>
			<td><font color="black"><%=baleC%> %</font></td>
			<td><font color="black"><%=strbaleCgst%></font></td>
			<td><font color="black"><%=baleS%> %</font></td>
			<td><font color="black"><%=strbaleSgst%></font></td>
			<td><font color="black"><%=strtotalb%></font></td>
			</tr>
			<%}
			else if(t==0)
			{%>
			<tr>
			<td><font color="black"><%=key%></font></td>
			<td><font color="black"><%=x.get(1)%>Cartoons</font></td>
			<%
			String strcartoonValue = String.format("%.2f", cartoonValue);
			String strcartoonCgst = String.format("%.2f", cartoonCgst);
			String strcartoonSgst = String.format("%.2f", cartoonSgst);
			String strtotalc = String.format("%.2f", cartoonCgst+cartoonSgst);
			%>
			<td><font color="black"><%=strcartoonValue%></font></td>
			<td><font color="black"><%=cartoonC%> %</font></td>
			<td><font color="black"><%=strcartoonCgst%></font></td>
			<td><font color="black"><%=cartoonS%> %</font></td>
			<td><font color="black"><%=strcartoonSgst%></font></td>
			<td><font color="black"><%=strtotalc%></font></td>
			</tr>
			<%}%>
		<%}%>
	<%}%>
	<%
	Float tot =Float.parseFloat(strcgstValue)+Float.parseFloat(strsgstValue);
	String strtot = String.format("%.2f", tot);
	%>
	<tr bgcolor="black">
		<td><font color="white"></font></td>
		<td><font color="white">TOTAL :</font></td>
		<td><font color="white"><%=strt_amount%> </font></td>
		<td><font color="white"></font></td>
		<td><font color="white"><%=strcgstValue%></font></td>
		<td><font color="white"></font></td>
		<td><font color="white"><%=strsgstValue%> </font></td>
		<td><font color="white"><%=strtot%></font></td>
	</tr>
</tbody>
</table>
</div>

<br>
<div  class="form-inline justify-content-center">
      <b>Vehicle No.</b>
      <%=deliverdetails.get(6)%>
    </div>
    <div>
    	<br>
   <center>
  &nbsp;&nbsp;&nbsp;
</center>
</div>
</div>
</div></BR>
<br><br>
</form>
</body>	
</html>