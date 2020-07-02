<!DOCTYPE html>
<html>
<head>
	<title>Failed</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Oops!", "User Registration Error Occured!", "error")
		.then(() => {
		  location.href="admindashboard";
		});
    </script>
</body>
</html>