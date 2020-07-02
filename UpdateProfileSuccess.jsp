<!DOCTYPE html>
<html>
<head>
	<title>Success</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Done!", "Profile Updated!", "success")
    	.then((value) => {
		  location.href="userProfile";
		});
    </script>

</body>
</html>