<!DOCTYPE html>
<html lang="en">
<head>
	<%@page import="java.util.*"%>
<title>SCN Stock || Inward Details</title>
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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
	function validate_lr()
	{
		var c_lr_no=document.getElementById("c_lr_no").value;
		arr=c_lr_no.split("/");
		if(arr[1]==null)
			{
				swal("Provide LR No. in correct format!", "format: xx/n", "warning");
				return false;
			}
		else if(arr[1]=="")
		{
			swal("Provide LR No. in correct format!", "format: xx/n", "warning");
			return false;
		}
		else if (isNaN(arr[1])) 
		{
			swal("Provide LR No. in correct format!", "format: xx/n", "warning");
			return false;
		}
		return true;
		
		
	}
</script>
</head>
<body>
	<!-- w3-banner -->
	<div class="w3-banner-1 jarallax">
		
			<div class="w3layouts-header-top">
				<!--
				<div class="container">
					<div class="w3-header-top-grids">
						<div class="w3-header-top-left">
							<p>Goralmal Balmokand</p>
						</div>
						<div class="w3-header-top-right">
							<div class="agileinfo-social-grids">
								<ul>
									<li><a href="#"><i class="fa fa-facebook"></i></a></li>
									<li><a href="#"><i class="fa fa-twitter"></i></a></li>
									<li><a href="#"><i class="fa fa-rss"></i></a></li>
									<li><a href="#"><i class="fa fa-vk"></i></a></li>
								</ul>
							</div>
							<div class="clearfix"> </div>
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>-->
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
							 	<li class="active first-list"><a href="inward_head">Inward Details</a></li>
							 	<li><a href="logout" >LogOut</a></li> 
							  </ul>
							</div><!-- /.navbar-collapse -->
						</div>
				</div>
			</div>
		</div>
	</div>
		<!--End of Header -->

<% 
     List<String> inwarddetails = new ArrayList<>();

     if (request.getSession().getAttribute("inwarddetails") != null) {
         inwarddetails = (ArrayList ) request.getSession().getAttribute("inwarddetails");
     }
     List<List> inward = new ArrayList<>();
     inward = (ArrayList )request.getSession().getAttribute("inwardheaddetails");
%>
<%
	int currentBales = (int)session.getAttribute("currentBales");
	int totalBales = (int)session.getAttribute("totalBales");
%>
<% 
     ArrayList c_codes = new ArrayList();

     if (request.getSession().getAttribute("c_codes") != null) {
         c_codes = (ArrayList ) request.getSession().getAttribute("c_codes");
     }
     Iterator iter = c_codes.iterator();
%>
<br><br>
		<div class="container">
		<div class="table-responsive">
		<table class = "table">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Transport Name</th>
		      <th scope="col">From Station</th>
		      <th scope="col">Dispatch Date</th>
		      <th scope="col">Challan No.</th>
		      <th scope="col">Challan Date</th>
		      <th scope="col">Lorry No.</th>
		      <th scope="col">Arrival Date</th>
		      <th scope="col">Total Bales</th>
		      <th scope="col">Remaining Bales</th>
		    </tr>
		  </thead>
		<tbody>
		<%
			String id="";
			for(List<String> row:inward)
			{%>
				<tr>
					<%for(String element : row)
				{
					if(element==row.get(0))
					{
						id=element;
						continue;
					}
					%>

					<td><font color="black"><%=element%></font></td>
				<%}%>
				<td><font color="red"><%=(totalBales-currentBales)%></font></td>
				</tr>
				<%}
		%>
		</tbody>
		</table>
		</div>

		<br>
		<h3 class="card-title text-center">Inward Details</h3>
		<br>
		<!--<div class="jumbotron" style="background-color:#839192 ;">-->
		
<input type="hidden" name="inward_id" value="<%=id%>">
 <input type="hidden" name="remainingBales" value="<%=(totalBales-currentBales)%>">
<form action="updatethisinwarddetails" method="POST">
 	<input type="hidden" name="inwarddetailsid" value="<%=inwarddetails.get(0)%>">

  <div class="form-row">
    <div class="form-group col-md-3">
      <label for = "Consigner ID">Consigner Code</label>
      <select style="width: 250px;height:33px;margin: 0px;border-color:#8080AA;" name="c_code" required="">
      	
      	<% 	String x = (String)inwarddetails.get(2);
      		while(iter.hasNext())
      		{
	      		 String ch =iter.next().toString();
	      		 String option = ch.substring(1,ch.length()-1);
		         if(option.equals(x))
		         {%>
		         	<option selected=""><%=inwarddetails.get(2)%></option>
		         <%}
		         else
		         {%>
			        <option><%=option%></option>
		     	<%}
		     }%>
	  </select>
    </div>
    <div class="form-group col-md-3">
      <label for="Consigner Bill_No.">Consigner Bill No.</label>
      <input type="text" class="form-control" placeholder="Enter Consigner Bill_No." name="c_bill_no" value="<%=inwarddetails.get(3)%>" required>
    </div>
    <div class="form-group col-md-3">
      <label for="Consigner Bill Date">Consigner Bill Date</label>
      <input type="date" class="form-control" placeholder="Enter Consigner Bill Date" name="c_bill_date" value="<%=inwarddetails.get(4)%>" required="">
    </div>
        <div class="form-group col-md-3">
      <label for="Consigner LR No.">Consigner LR No.</label>
      <input type="text" class="form-control" placeholder="Enter Consigner LR No." name="c_lr_no" id="c_lr_no" value="<%=inwarddetails.get(5)%>" disabled="">
    </div>
  </div>
  <center>
  &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-secondary">Update</button>
  <button type="reset" class="btn btn-danger">Clear</button>
</center>
</form>
</div>
</div>
<br><br>
</form>
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