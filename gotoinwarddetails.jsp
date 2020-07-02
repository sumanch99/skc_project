<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<body>
	<script type="text/javascript">
		swal("Done!", "Record Added Successfully!", "success")
    	.then((value) => {
		  location.href="editinwarddetails";
		});
	</script>
</body>
</html>