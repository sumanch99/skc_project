<!DOCTYPE html>
<html>
<head>
	<title>Success</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Done!", "Consigner Successfully Updated!", "success")
    	.then((value) => {
		  location.href="qualityMaster";
		});
    </script>

</body>
</html>