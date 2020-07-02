<!DOCTYPE html>
<html>
<head>
	<title>Success</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Done!", "Consigner Inserted!", "success")
    	.then((value) => {
		  location.href="consignerMaster";
		});
    </script>

</body>
</html>