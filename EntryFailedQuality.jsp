<!DOCTYPE html>
<html>
<head>
	<title>Failed</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Oops!", "New Quality Insertion Failed!", "error")
		.then(() => {
		  location.href="qualityMaster";
		});
    </script>
</body>
</html>