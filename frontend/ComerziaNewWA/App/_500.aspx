<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="_500.aspx.cs"
Inherits="ComerziaNewWA.App._500" %>

<!DOCTYPE html>
<html lang="en">
    <head runat="server">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>500 - Internal Server Error</title>
        <link
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
            rel="stylesheet"
        />
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
                max-width: 800px;
                text-align: center;
            }
            .error-code {
                font-size: 96px;
                font-weight: 700;
                color: #dc3545;
            }
            .error-message {
                font-size: 24px;
                color: #343a40;
            }
            .error-details {
                margin-top: 20px;
                text-align: left;
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                overflow: auto;
                max-height: 500px;
                border: 1px solid #dee2e6;
                font-family: monospace;
            }
            .error-title {
                font-size: 20px;
                font-weight: 600;
                color: #dc3545;
            }
            .back-home-btn {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <form id="form1" runat="server">
            <div class="container error-container">
                <div class="error-code">500</div>
                <div class="error-message">
                    Oops! Something went wrong on our end.
                </div>
                <p class="text-muted">
                    We're working to fix the problem. Please try again later.
                </p>
                <asp:Panel
                    ID="ErrorDetailsPanel"
                    runat="server"
                    Visible="false"
                >
                    <h4 class="error-title"><%= InnerExceptionTitle %></h4>
                    <pre class="error-details"><%= ExceptionDetails %></pre>
                </asp:Panel>
                <a href="/" class="btn btn-primary back-home-btn"
                    >Go Back to Home</a
                >
            </div>
        </form>

        <!-- Optional JavaScript -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
