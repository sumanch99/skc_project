<!DOCTYPE html>
<html>
<head>
	<title>Success</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Done!", "Added to Cart!", "success")
    	.then((value) => {
		  location.href="outward";
		});
    </script>

</body>
</html>