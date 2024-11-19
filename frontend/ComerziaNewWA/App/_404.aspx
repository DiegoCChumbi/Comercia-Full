<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="404.aspx.cs" Inherits="ComerziaNewWA.App._404" %>

<!DOCTYPE html>
<html lang="en">
<head runat="server">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Page Not Found</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f8f9fa;
            margin: 0;
        }
        .error-container {
            max-width: 600px;
            text-align: center;
        }
        .error-code {
            font-size: 96px;
            font-weight: 700;
            color: #6c757d;
        }
        .error-message {
            font-size: 24px;
            color: #343a40;
        }
        .back-home-btn {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="container error-container">
            <div class="error-code">404</div>
            <div class="error-message">Oops! Page Not Found</div>
            <p class="text-muted">The page you're looking for doesn't exist or has been moved.</p>
            <a href="/" class="btn btn-primary back-home-btn">Go Back to Home</a>
        </div>
    </form>

    <!-- Optional JavaScript; choose one of the two! -->
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
