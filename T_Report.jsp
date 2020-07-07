<%@page import="java.sql.*,java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SCN Stock ||  Transport Report</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta name="keywords" content="Stock Industry Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
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
								 
							  <div class="navbar-brand logo">
									<h1><a href="">Imports & Exports</a></h1>
								</div>
							</div>

							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							 <ul class="nav navbar-nav link-effect-4" style="display:inline-block;">
							 	<li><a href="dashboard" >Dashboard</a></li> 
							 	<li class="active first-list"><a href="#">Transportation Report</a></li>
							 	<li><a href="logout" >LogOut</a></li> 
							  </ul>
							</div><!-- /.navbar-collapse -->
						</div>
				</div>
			</div>
		</div>
	</div>

<!--End of Header-->
<br><br>
<%
int flag = (int)session.getAttribute("flag");
if(flag==0)
{%>
	<div class="container">
	<br>
	<h3 class="card-title text-center">Choose Date to Show Report</h3>
			<!--<div class="jumbotron" style="background-color:#839192 ;">-->
	<form action="T_Report" method="get">
	  	<div class="form-row">
	   	 <div class="form-group col-md-6">
	      <label for="From Date">From Date</label>
	      <input type="date" class="form-control" placeholder="Enter From Date" name="from_date" id="from_date">
	    </div>
	   	 <div class="form-group col-md-6">
	      <label for="To Date">To Date</label>
	      <input type="date" class="form-control" placeholder="Enter To Date" name="to_date" id="to_date">
	    </div>
	    </div>
	    <br>
	  <center>
	  &nbsp;&nbsp;&nbsp;
		<button class="btn btn-secondary" onclick="return show();">Show Report</button>
	  </center>
	 </form>
	</div>
	<br>
	</tbody>
	</table>
	</div>
	</div>
	<br><br>
<%
}
String from_date="2020-07-03";
String to_date="2020-07-03";
List<List> tranport_report_head = (ArrayList)session.getAttribute("tranport_report_head");
if(session.getAttribute("from")!=null){
	from_date = session.getAttribute("from").toString();
	to_date = session.getAttribute("to").toString();
}
if(flag==1)
{%>
	<div class="container">
	<br>
	<h3 class="card-title text-center">Choose Date to Show Report</h3>
			<!--<div class="jumbotron" style="background-color:#839192 ;">-->
	<form action="T_Report" method="GET">
	  	<div class="form-row">
	   	 <div class="form-group col-md-6">
	      <label for="From Date">From Date</label>
	      <input type="date" class="form-control" placeholder="Enter From Date" name="from_date" value="<%=from_date%>" id="from_date">
	    </div>
	   	 <div class="form-group col-md-6">
	      <label for="To Date">To Date</label>
	      <input type="date" class="form-control" placeholder="Enter To Date" name="to_date" value="<%=to_date%>" id="to_date">
	    </div>
	    </div>
	    <br>
	  <center>
	  &nbsp;&nbsp;&nbsp;
		<button class="btn btn-secondary" onclick="return show();">Show Report</button>
	  </center>
	 </form>
	</div>
	<br>
	</tbody>
	</table>
	</div>
	</div>
	<div class="container">
	<br>
	<%if(from_date.equals("") && to_date.equals(""))
	{%>
		<h3 class="card-title text-center">Total Transport Report</h3>
	<%}else if(from_date.equals(""))
	{
		String date=to_date;
		String[] dta = date.split("-");
		String y=dta[0];
		String m = dta[1];
		String d = dta[2];
		String da = d+"/"+m+"/"+y;
		%>
		<h3 class="card-title text-center">Transport Report upto <%=da%></h3>
	<%}else if(to_date.equals(""))
	{
		String date=from_date;
		String[] dta = date.split("-");
		String y=dta[0];
		String m = dta[1];
		String d = dta[2];
		String da = d+"/"+m+"/"+y;
		%>
		<h3 class="card-title text-center">Transport Report From <%=da%></h3>
	<%}else{
		String date=from_date;
		String[] dta = date.split("-");
		String y=dta[0];
		String m = dta[1];
		String d = dta[2];
		String da1 = d+"/"+m+"/"+y;

		date=to_date;
		dta = date.split("-");
		y=dta[0];
		m = dta[1];
		d = dta[2];
		String da2 = d+"/"+m+"/"+y;
	%>
		<h3 class="card-title text-center">Transport Report From <%=da1%> Upto <%=da2%></h3>
	<%}%>
	<br>
	<div class="container"> 
	<div class="table-responsive">
	<table class = "table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">Transport Name</th>
	      <th scope="col">From Station</th>
	      <th scope="col">Challan No.</th>
	      <th scope="col">Lorry No.</th>
	      <th scope="col">Arrival Date</th>
	      <th scope="col">Total Bales</th>
	     <!-- <th scope="col">Show Details</th>-->
	    </tr>
	  </thead>
	 <script type="text/javascript">
	 	function isSure()
	 	{
	 		if (confirm("Are you sure to delete this record?")) 
	 		{
	 			return true;
	 		}
	 		return false;
	 	}
	 </script>
	<tbody>
	<%
		for(List<String> row:tranport_report_head)
		{%>
			<tr>
				<td><font color="#000"><%=row.get(1)%></font></td>
				<td><font color="#000"><%=row.get(2)%></font></td>
				<td><font color="#000"><%=row.get(4)%></font></td>
				<td><font color="#000"><%=row.get(6)%></font></td>
				<% 
				String date=row.get(7);
				String[] dta = date.split("-");
				String y=dta[0];
				String m = dta[1];
				String d = dta[2];
				String da = d+"/"+m+"/"+y;
				%>
				<td><font color="#000"><%=da%></font></td>
				<td><font color="#000"><%=row.get(8)%></font></td>
			<!--<td><a href= "#"><button type="button" class="update">Show Details</button></a>
			</td>-->
			</tr>
			<%}
	%>
	</tbody>
	</table>
	</div>
	</div>
	</div>
<%}%>
<br><br>
<center><b>Go to <a href="S_Report">Stock Report</a></b></center>
<br><br>

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