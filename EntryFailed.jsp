<!DOCTYPE html>
<html>
<head>
	<title>Failed</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Oops!", "Record Insertion Failed!", "error")
		.then(() => {
		  location.href="inward";
		});
    </script>
</body>
</html>