<%@page import="java.sql.*,java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SCN Stock || Outward</title>
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
<body>
	<!-- w3-banner -->
	<div class="w3-banner-1 jarallax">
		
			<div class="w3layouts-header-top">
			<div class="head">
				<div class="container">
					<div class="navbar-top">
							<!-- Brand and toggle get grouped for better mobile display -->
							<div class="navbar-header">
							  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							  </button>
								 <div class="navbar-brand logo ">
									<h1><a href="dashboard">Imports & Exports</a></h1>
								</div>

							</div>

							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							 <ul class="nav navbar-nav link-effect-4" style="display:inline-block;">
							 	<li><a href="dashboard" >Dashboard</a></li> 
							 	<li class="active first-list"><a href="#">Outward</a></li>
							 	<li><a href="logout" >LogOut</a></li> 
							  </ul>
							</div><!-- /.navbar-collapse -->
						</div>
				</div>
			</div>
		</div>
	</div>
		<!--End of Header -->
		<div class="container">
		<br>
		<h3 class="card-title text-center">Edit Outward Challan</h3>
		<BR>
		<!--<div class="jumbotron" style="background-color:#839192 ;">-->
		
<%
	Set<String> qualities=(HashSet)session.getAttribute("qualities");
	Iterator iter = qualities.iterator();
	int q_g = (int)session.getAttribute("qualitygiven");
	int s_t = (int)session.getAttribute("stocktransfer");
	String selectedquality = "N/A";
	List<List> outward_options = new ArrayList<List>();
	if(request.getSession().getAttribute("selectedquality")!= null)
	{
		selectedquality = request.getSession().getAttribute("selectedquality").toString();
	}
     if (request.getSession().getAttribute("outward_options") != null) {
         outward_options = (ArrayList) request.getSession().getAttribute("outward_options");
     }
	List<List> deliveryCart = new ArrayList<List>();
     if (request.getSession().getAttribute("deliveryCart") != null) {
         deliveryCart = (ArrayList) request.getSession().getAttribute("deliveryCart");
     }
%>
<%if(q_g==0&&s_t==0)
{%>
	<form action="outward" method="post" class="form-inline justify-content-center">
	  <div class="form-row">
	  	
	    <div class="form-group col-md-12">
	      <b>Quality: </b>
	      <select style="width: 350px;height:33px;margin: 0px;border-color:#8080AA;" name="quality" required>
	      	<option></option>
	      	<%while(iter.hasNext())
	      	{%>
	      		<option><%=iter.next().toString()%></option>
	      	<%}%>
	      </select>
	    </div>
	</div>
	    <div>
	   <center>
	  &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-secondary">GO</button>
	  <button type="reset" class="btn btn-danger">Clear</button>
	</center>
	</div>
	</form>


<br>
<hr style="border: 2px solid black;" />
<%
String date = session.getAttribute("Date").toString();
String[] dtarr = date.split("-");
String yyyy=dtarr[0];
String mm = dtarr[1];
String dd = dtarr[2];
String datef = dd+"/"+mm+"/"+yyyy;
%>

		<div class="container">
		<h4 class="card-title text-left">Order No : <%=session.getAttribute("OrderNo").toString()%></h4>	
		<h4 class="card-title text-right">Date : <%=datef%></h4>	
		<h3 class="card-title text-center"><u>Delivery Order</u></h3>
</div>

<br>
<div class="container"> 
<div class="table-responsive">
<table class = "table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Bal No.</th>
      <th scope="col">Consigner</th>
      <th scope="col">Quality</th>
      <th scope="col">HSN</th>
      <th scope="col">GST</th>
      <th scope="col">PCS</th>
      <th scope="col">Rate</th>
      <th scope="col">Total Amount</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <script type="text/javascript">
	function isSure()
	{
		if (confirm("Are you sure you want to delete it?")) 
		{
			return true;
		}
		return false;
	}
</script>
<tbody>
		<%for(List row:deliveryCart)
		{%>
			<tr>
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
			Float taxiable_amount=Float.parseFloat(row.get(7).toString())*Float.parseFloat(row.get(8).toString());
			Float total_tax =  taxiable_amount*gst/100;
			int amount = Math.round(taxiable_amount+total_tax);

			%>
			<td><font color="black">Rs. <%=amount%></font></td>
			<td><a href= "deleteoutward?inward_lr_details_id=<%=row.get(0)%>"><button type="button" class="update" onclick="return isSure();">Delete</button></a></td>
			</tr>
		<%}%>
</tbody>
</table>
</div>
</div>
<br><br>
<div>
   <center>
  &nbsp;&nbsp;&nbsp;<button onclick="location.href='generatechallan?order_no=<%=session.getAttribute("OrderNo").toString()%>'" class="btn btn-secondary">Generate Stock Transfer Challan</button>
</center>
</div>
<br><br>

<%}%>
</div>
<br>
<%
if(q_g==1)
{%>
	<form action="outward" method="post" class="form-inline justify-content-center">
	<div class="form-row">
	    <div class="form-group col-md-12">
	      <b>Quality: </b>
	      <select style="width: 350px;height:33px;margin: 0px;border-color:#8080AA;" name="quality">
	      	<%while(iter.hasNext())
	      	{
	      		String ch = iter.next().toString();
	      		if(ch.equals(selectedquality))
	      		{%>
					<option selected><%=ch%></option>
	      		<%}
	      		else{
	      		%>
	      			<option><%=ch%></option>
	      		<%}
	      }%>
	      </select>
		</div>
	</div>
	<div>
	   <center>
	  &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-secondary">GO</button>
	  <button type="reset" class="btn btn-danger">Clear</button>
	</center>
	</div>
	</form>
	<br>
	<div class="container"> 
	<div class="table-responsive">
	<table class = "table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">SELECT</th>
	      <th scope="col">BILL DATE</th>
	      <th scope="col">BILL NO</th>
	      <th scope="col">CONSIGNER</th>
	      <th scope="col">BALE/CARTOON NO.</th>
	      <th scope="col">TYPE</th>
	      <th scope="col">PCS</th>
	      <th scope="col">QUALITY</th>
	      <th scope="col">RATE</th>
	    </tr>
	  </thead>
	</script>
	<tbody>
		<form action="outward" method="POST">
		<%for(List row:outward_options)
		{%>
			<tr>
			<td><input type="checkbox" name="inward_lr_details_id" 
				value="<%=row.get(0)%>"></td>
			<td><font color="black"><%=row.get(3)%></font></td>
			<td><font color="black"><%=row.get(2)%></font></td>
			<td><font color="black"><%=row.get(1)%></font></td>
			<td><font color="black"><%=row.get(4)%></font></td>
			<td><font color="black"><%=row.get(5)%></font></td>
			<td><font color="black"><%=row.get(6)%></font></td>
			<td><font color="black"><%=row.get(7)%></font></td>
			<td><font color="black"><%=row.get(8)%></font></td>
			</tr>
		<%}%>
		
	</tbody>
	</table>
	</div>
	</div>

	<br><br>
	<div>
	   <center>
	  &nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-secondary" value="SEND FOR STOCK TRANSFER">
	</center>
	</div>
	</form>
<%}%>
<br>
	<!-- //contact -->  
	<!-- map 
	<div class="map w3layouts">  
		<iframe src="https://www.google.com/maps/place//data=!4m2!3m1!1s0x3a0277b0a5ce85b3:0x6405ec053fc0d096?utm_source=mstt_1"></iframe>
	</div>
	//map -->  

	<!-- footer -->
	<div class="agileits-w3layouts-footer">
			<div class="container">
				<div class="col-md-4 w3-agile-grid">
					<div class="w3-address-grid">
							<div class="w3-address-left">
								<i class="fa fa-phone" aria-hidden="true"></i>
							</div>
							<div class="w3-address-right">
								<h6>Phone Number</h6>
								<p>Suman Chakraborty: (+91)8918316046</p>
								<p>Sinjon Nath: (+91)8013896502</p>
							</div>
							<div class="clearfix"> </div>
						</div>
					
				</div>
				<div class="col-md-4 w3-agile-grid">
					  <div class="w3-address-grid">
							<div class="w3-address-left">
								<i class="fa fa-envelope" aria-hidden="true"></i>
							</div>
							<div class="w3-address-right">
								<h6>Email Address</h6>
								<p><a href="mailto:sch.suman@gmail.com"> sch.suman@gmail.com</a>
								<span><a href="mailto:sinjonnath@gmail.com">sinjonnath@gmail.com</a></span>
								</p>
							</div>
							<div class="clearfix"> </div>
						</div>
				</div>
				<div class="col-md-4 w3-agile-grid">
					<div class="w3-address">
						<div class="w3-address-grid">
							<div class="w3-address-left">
								<i class="fa fa-map-marker" aria-hidden="true"></i>
							</div>
							<div class="w3-address-right">
								<h6>Location</h6>
								<p>Suman Chakraborty<br>Manihar Co-Opt. Housing Society Limited-7/6, Calcutta Greens Phase 2,Survey Park,Kolkata-700075 
								<span>Sinjon Nath<br>8/1,M.M. Dutta Road,New Barrackpur,Kolkata-700131 </span>
								</p>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<div class="copyright">
			<div class="container">
				<p>&copy; 2020 Stock Industry. All rights reserved | Design by <a href="cv\Suman_Chakraborty_Resume.pdf" target="_blank">SUMAN CHAKRABORTY</a> & <a href="cv\sinjon_resume.pdf" target="_blank">SINJON NATH</a></p>
			</div>
		</div>
	<!-- //footer -->
	<!-- ResponsiveTabs -->
	<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$('#horizontalTab').easyResponsiveTabs({
				type: 'default', //Types: default, vertical, accordion           
				width: 'auto', //auto or any width like 600px
				fit: true   // 100% fit in a container
			});
		});
	</script>
	<!-- //ResponsiveTabs --> 

	<script src="js/jarallax.js"></script>
	<script src="js/SmoothScroll.min.js"></script>
	<script type="text/javascript">
		/* init Jarallax */
		$('.jarallax').jarallax({
			speed: 0.5,
			imgWidth: 1366,
			imgHeight: 768
		})
	</script>
</body>	
</html>